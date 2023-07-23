import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureExample
 * //runAsync -> that takes in no args and return no result
 * CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
 * //some computation
 * });
 * 
 * //supplyAsync -> that takes in no args and returns a result
 * CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
 * // some computation
 * // task does not have any input, but it produces an output supplyAsync:hello
 * return "supplyAsync:hello";
 * });
 * //thenAccept -> CompletableFuture is completed when the original CompletableFuture is completed, and the Consumer’s action is performed with the result of the original CompletableFuture.
 * CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello World");
future.thenAccept(s -> System.out.println(s));
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Running task async...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Running task asynchronously...");
                return "supplyAsync:hello";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        });
        try {
            System.out.println("supplyAsync: " + future2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //you want to perform an action with that result but don't need to return any other value.
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        future3.thenAccept(i -> System.out.println(i));

        if(!future3.isDone()){future3.complete(1);}

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            // some computation
            return 5;
        });
        int result = future4.get();

        CompletableFuture<Integer> future5 = CompletableFuture.supplyAsync(() -> {
            // some computation
            return 5;
        });
        //default value passed to it if the future is not done yet.
        int result2 = future5.getNow(0);

        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> " World");
        CompletableFuture<String> combinedFuture = future6.thenCombine(future7, (s1, s2) -> s1 + s2);
        System.out.println(combinedFuture.get());

        /*can be useful in situations where you have a series of asynchronous operations that depend on each other, and you want to chain them together to form a single pipeline. */
        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> "Hello" ); 
        CompletableFuture<String> future9 = future8.thenCompose(s -> CompletableFuture 
                                        .supplyAsync(() -> s + " World" ));
        System.out.println(future9.get());
    }
}