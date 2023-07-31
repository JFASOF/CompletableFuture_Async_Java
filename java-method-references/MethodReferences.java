import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferences {
        public static void main(String[] args) {
                // Using a method reference to access println
                Stream.of(3, 1, 4, 1, 5, 9)
                                .forEach(x -> System.out.println(x));
                Stream.of(3, 1, 4, 1, 5, 9)
                                .forEach(System.out::println);
                Consumer<Integer> printer = System.out::println;
                Stream.of(3, 1, 4, 1, 5, 9)
                                .forEach(printer);
                // Using a method reference to a static method
                Stream.generate(Math::random)
                                .limit(10)
                                .forEach(System.out::println);

                // Invoking a multiple-argument instance method from a class reference
                List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

                List<String> sorted = strings.stream()
                                .sorted((s1, s2) -> s1.compareTo(s2))
                                .collect(Collectors.toList());
                System.out.println(sorted);
                List<String> sorted2 = strings.stream()
                                .sorted(String::compareTo)
                                .collect(Collectors.toList());
                System.out.println(sorted2);
                // Invoking the length method on String using a method reference
                Stream.of("this", "is", "a", "stream", "of", "strings")
                                .map(String::length)
                                .forEach(System.out::println);

                // Lambda expression equivalents for method references
                Stream.of("this", "is", "a", "stream", "of", "strings")
                                .map(s -> s.length())
                                .forEach(x -> System.out.println(x));
        }
}
