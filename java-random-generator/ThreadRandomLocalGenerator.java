import java.util.Spliterator.OfInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ThreadRandomLocalGenerator {

    public static void main(String[] args) {
        var randomNum = ThreadLocalRandom.current().ints(100, 200).distinct().boxed().collect(Collectors.toList());
        System.out.println(randomNum.iterator().next());
    }
}
