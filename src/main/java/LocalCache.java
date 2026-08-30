import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * An in-memory cache with a fixed capacity, LRU eviction and optional per-entry TTL.
 * All operations are O(1) and synchronized, so a single instance can be shared across threads.
 */
public class LocalCache<K, V> {

    private static final long NO_EXPIRY = -1L;

    private static class Entry<V> {
        final V value;
        final long expiresAt;

        Entry(V value, long expiresAt) {
            this.value = value;
            this.expiresAt = expiresAt;
        }

        boolean isExpired(long now) {
            return expiresAt != NO_EXPIRY && now >= expiresAt;
        }
    }

    private final int capacity;
    private final long defaultTtlMillis;
    private final LinkedHashMap<K, Entry<V>> map;

    private long hits;
    private long misses;
    private long evictions;

    public LocalCache(int capacity) {
        this(capacity, NO_EXPIRY);
    }

    /**
     * @param capacity         maximum number of entries kept; the least recently used one is dropped when full
     * @param defaultTtlMillis time to live applied to entries added without an explicit ttl, or -1 to never expire
     */
    public LocalCache(int capacity, long defaultTtlMillis) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.defaultTtlMillis = defaultTtlMillis;
        // access order = true makes LinkedHashMap move a key to the tail on every get/put,
        // so the head is always the least recently used entry.
        this.map = new LinkedHashMap<>(16, 0.75f, true);
    }

    public synchronized void put(K key, V value) {
        put(key, value, defaultTtlMillis);
    }

    public synchronized void put(K key, V value, long ttlMillis) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        long expiresAt = ttlMillis == NO_EXPIRY ? NO_EXPIRY : System.currentTimeMillis() + ttlMillis;
        map.put(key, new Entry<>(value, expiresAt));
        evictIfNeeded();
    }

    /**
     * @return the cached value, or null if the key is absent or its entry has expired
     */
    public synchronized V get(K key) {
        Entry<V> entry = map.get(key);
        if (entry == null) {
            misses++;
            return null;
        }
        if (entry.isExpired(System.currentTimeMillis())) {
            map.remove(key);
            misses++;
            return null;
        }
        hits++;
        return entry.value;
    }

    /**
     * Returns the cached value, computing and storing it with the given loader on a miss.
     * The loader runs while the cache lock is held, so it should be cheap and must not call back into the cache.
     */
    public synchronized V getOrLoad(K key, Function<K, V> loader) {
        V value = get(key);
        if (value != null) {
            return value;
        }
        value = loader.apply(key);
        put(key, value);
        return value;
    }

    public synchronized boolean containsKey(K key) {
        return get(key) != null;
    }

    public synchronized V remove(K key) {
        Entry<V> entry = map.remove(key);
        return entry == null ? null : entry.value;
    }

    public synchronized void clear() {
        map.clear();
    }

    /**
     * @return number of entries, ignoring any that have expired but not yet been touched
     */
    public synchronized int size() {
        purgeExpired();
        return map.size();
    }

    /** Drops every entry whose ttl has elapsed. */
    public synchronized void purgeExpired() {
        long now = System.currentTimeMillis();
        map.entrySet().removeIf(e -> e.getValue().isExpired(now));
    }

    public synchronized String stats() {
        return "hits=" + hits + ", misses=" + misses + ", evictions=" + evictions + ", size=" + map.size();
    }

    private void evictIfNeeded() {
        if (map.size() <= capacity) {
            return;
        }
        purgeExpired();
        while (map.size() > capacity) {
            K oldest = map.keySet().iterator().next();
            map.remove(oldest);
            evictions++;
        }
    }

    public static void main(String[] args) throws Exception {
        LocalCache<String, Integer> cache = new LocalCache<>(3);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);

        cache.get("a");             // "a" becomes the most recently used, so "b" is next to go
        cache.put("d", 4);          // capacity exceeded -> evicts "b"

        System.out.println("a = " + cache.get("a"));
        System.out.println("b = " + cache.get("b"));
        System.out.println("d = " + cache.get("d"));
        System.out.println(cache.stats());

        LocalCache<String, String> shortLived = new LocalCache<>(10, 100);
        shortLived.put("token", "abc123");
        System.out.println("token = " + shortLived.get("token"));
        Thread.sleep(150);
        System.out.println("token after ttl = " + shortLived.get("token"));

        LocalCache<Integer, Integer> squares = new LocalCache<>(5);
        System.out.println("square(7) = " + squares.getOrLoad(7, n -> n * n));
        System.out.println("square(7) = " + squares.getOrLoad(7, n -> n * n));
        System.out.println(squares.stats());
    }
}
