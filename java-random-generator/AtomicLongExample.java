import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExample {
    public static void main(String[] args) {
        AtomicLong idCounter = new AtomicLong(100);
        long timestamp = System.currentTimeMillis();
        long nextLong = idCounter.incrementAndGet();
        String randomId = String.valueOf(timestamp) + String.valueOf(nextLong);
        System.out.println(randomId);
    }
}
