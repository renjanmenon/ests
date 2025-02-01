import java.util.Arrays;
import java.util.List;
import java.util.Map;

import handsOn.Student;

public class StreamsAndJavaHandsOn {
    public static void main(String[] args) {
        // This is how your code will be called.
        // Your answer should be the largest value in the numbers array.
        // You can edit this code to try different testing cases.
        List<Student> students = Arrays.asList(
                new Student("Amine", "Ousmane", "Computer Science",
                        Map.of("Algorithms", 90, "Data Structures", 80, "Calculus", 85)),
                new Student("Lily-Ann", "Smith", "Mathematics",
                        Map.of("Algorithms", 80, "Data Structures", 75, "Calculus", 88)),
                new Student("Li", "Wei", "Computer Science",
                        Map.of("Algorithms", 92, "Data Structures", 89, "Calculus", 88)),
                new Student("Jessica", "Rodriguez", "Mathematics",
                        Map.of("Algorithms", 85, "Data Structures", 80, "Calculus", 89)));
        String major = "Computer Science";
       int highestScoreWithinMajorCs = students.stream().filter(s -> major.equals(s.getMajor()))
                .map(s -> s.getGrades()).flatMap(grades -> grades.values().stream()).max(Integer::compareTo).orElseGet(() -> 0);
                System.out.println(highestScoreWithinMajorCs);
    }

}
