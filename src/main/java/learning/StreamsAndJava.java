import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StreamsAndJava {
    public static void main(String[] args) {
        Predicate<String> greaterThan3 = s -> s.length() > 3;
        System.out.println(" check greaterThan5 for Ren {} :::: " + greaterThan3.test("Ren"));
        System.out.println(" check greaterThan5 for Ren {} :::: " + greaterThan3.test("This is Ren"));

        // Method references
        Consumer<String> getUpperCase = s -> System.out.println(s.toUpperCase());
        getUpperCase.accept("Ren");

        // Streams can be created from list , Array or I/o
        List<String> foodList = Arrays.asList("milk", "bread", "eggs", "preserve");
        Stream<String> foodStream = foodList.stream();

        String[] foodStrings = { "milk", "bread", "eggs", "preserve" };
        Stream<String> arrayFoodStream = Arrays.stream(foodStrings);

        Stream<String> exampleStream = Stream.of("milk", "bread", "eggs", "preserve");

        // from any of the above stream , get characters more than 3
        List<String> finalOutputFrmStream = exampleStream.filter(greaterThan3).map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("finalOutputFrmStream === " + finalOutputFrmStream);

        // intermediate : filter , map
        // terminal : forEach , collectors.collect , reduce

        // Match
        System.out.println(" All Match ---> " + foodStream.allMatch(f -> f != null));
        System.out.println(" Any Match ---> " + arrayFoodStream.anyMatch(f -> f.length() > 5));
        Stream<String> testStream = Stream.of("foo", "bar", "baz");
        System.out.println(" None Match ---> " + testStream.noneMatch(f -> f.length() > 5));

        // Reduce
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // calculate sum
        System.out.println("Sum using reduce === " + numbers.stream().reduce((a, b) -> a + b).get());
        System.out.println("Sum using reduce with identity === " + numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println("Find Max === " + numbers.stream().reduce((a, b) -> a > b ? a : b).get());

        // Collectors

        List<Integer> oddNumbers = numbers.stream().filter(num -> num % 2 == 1).collect(Collectors.toList());
        System.out.println("oddNumbers === " + oddNumbers);
        List<Integer> evenNumbers = numbers.stream().filter(num -> num % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("evenNumbers === " + evenNumbers);
        Set<Integer> multiplesOfThree = numbers.stream().filter(num -> num % 3 == 0).collect(Collectors.toSet());
        System.out.println("multiplesOfThree === " + multiplesOfThree);

        // Note there are unmodifyable set and list

        // Collectors Advanced
        // toMap , here we need to provide keyMapper and valueMapper
        Map<String, Integer> mapFrmStreams = foodList.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        // Function.identity() returns I/p as O/P ie here each stream entry becomes the
        // key
        System.out.println("mapFrmStreams === " + mapFrmStreams);
        // NOTE : toMap and to Set throws illegal Argument exception for duplicates
        int numOfmultiplesOfThree = numbers.stream().filter(num -> num % 3 == 0)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Set::size));
        System.out.println("numOfmultiplesOfThree using collectingAndThen === " + numOfmultiplesOfThree);
        List<String> listWithDuplicates = Arrays.asList("a", "b", "c", "D", "e");
        System.out.println("Collectors.joining === "
                + listWithDuplicates.stream().map(String::toLowerCase).collect(Collectors.joining()));
        System.out.println("Collectors.counting === "
                + listWithDuplicates.stream().map(String::toLowerCase).collect(Collectors.counting()));
        System.out.println("Collectors.averagingDouble === "
                + foodList.stream().collect(Collectors.averagingDouble(String::length)));
    }

}