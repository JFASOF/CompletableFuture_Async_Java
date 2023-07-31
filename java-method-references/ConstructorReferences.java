import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructorReferences {
    public static void main(String[] args) {
        // Converting a list of people to a list of names
        List<String> names = Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace",
                "Karen Spärck Jones");
        List<Person> people = names.stream()
                .map(name -> new Person(name))
                .collect(Collectors.toList());

        System.out.println(people);
        // alternatively
        List<Person> people2 = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println(people2);

        // Converting a list to a stream and back

        Person before = new Person("Grace Hopper");
        List<Person> people3 = Stream.of(before)
                .collect(Collectors.toList());
        Person after = people3.get(0);
        System.out.println(Objects.equals(after, before));
        before.setName("Grace Murray Hopper");
        System.out.println(Objects.equals("Grace Murray Hopper", after.getName()));

        // Using the copy constructor
        people = Stream.of(before)
                .map(Person::new)
                .collect(Collectors.toList());

        // Creating an array of Person references
        Person[] people5 = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(people5);
    }

}

class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    // A copy constructor for Person
    public Person(Person p) {
        this.name = p.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}