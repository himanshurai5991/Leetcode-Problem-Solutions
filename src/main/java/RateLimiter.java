import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {

    private final ConcurrentHashMap<String, ArrayDeque<Instant>> requests =
            new ConcurrentHashMap<>();

    private final int limit;
    private final Duration window;
    private final Clock clock;
    private final ConcurrentHashMap<String, ReentrantLock> locks =
            new ConcurrentHashMap<>();

    RateLimiter(int limit, Duration window, Clock clock) {
        this.limit = limit;
        this.window = window;
        this.clock = clock;
    }

    void removeOldRequests(Instant now, ArrayDeque<Instant> requests) {

        while (!requests.isEmpty() && now.toEpochMilli() - requests.peekFirst().toEpochMilli() >= window.toMillis()) {
            requests.pollFirst();
        }


    }

    boolean allowRequest(String customerId) {
        // TODO
        ReentrantLock lock = locks.computeIfAbsent(customerId, k -> new ReentrantLock());
        lock.lock();

        try {
            Instant now = clock.instant();
            ArrayDeque<Instant> currentCustomerRequests = requests.computeIfAbsent(customerId, k -> new ArrayDeque<>());
            removeOldRequests(now, currentCustomerRequests);
            if (currentCustomerRequests.size() >= limit) {
                return false;
            }

            currentCustomerRequests.addLast(now);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void cleanupCustomer(String customerId) {
        ReentrantLock lock =
                locks.computeIfAbsent(
                        customerId,
                        key -> new ReentrantLock()
                );

        lock.lock();

        try {
            ArrayDeque<Instant> customerRequests =
                    requests.get(customerId);

            if (customerRequests == null) {
                return;
            }

            Instant now = clock.instant();

            removeOldRequests(now, customerRequests);

            if (customerRequests.isEmpty()) {
                requests.remove(customerId);
            }

            // IMPORTANT:
            // We intentionally do NOT remove locks.remove(customerId).
        } finally {
            lock.unlock();
        }
    }
}
