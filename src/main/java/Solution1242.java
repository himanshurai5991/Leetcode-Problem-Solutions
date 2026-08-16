import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

interface HtmlParser {
    List<String> getUrls(String url);
}

public class Solution1242 {

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String host = getHost(startUrl);

        Set<String> visited = ConcurrentHashMap.newKeySet();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        visited.add(startUrl);
        queue.offer(startUrl);

        int numThreads = 8; // fixed worker pool
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        AtomicInteger pending = new AtomicInteger(1);

        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                try {
                    while (true) {
                        String url = queue.poll(100, TimeUnit.MILLISECONDS);

                        if (url == null) {
                            if (pending.get() == 0) {
                                break;
                            }
                            continue;
                        }

                        try {
                            for (String nextUrl : htmlParser.getUrls(url)) {
                                if (getHost(nextUrl).equals(host) && visited.add(nextUrl)) {
                                    queue.offer(nextUrl);
                                    pending.incrementAndGet();
                                }
                            }
                        } finally {
                            pending.decrementAndGet();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return new ArrayList<>(visited);
    }

    private String getHost(String url) {
        // url format: http://news.yahoo.com/news/topics/
        int start = url.indexOf("//") + 2;
        int end = url.indexOf("/", start);
        return end == -1 ? url.substring(start) : url.substring(start, end);
    }
}