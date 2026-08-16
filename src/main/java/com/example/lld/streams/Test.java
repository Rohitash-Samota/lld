package com.example.lld.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 4, 3, 4, 5);
        System.out.println(numbers);
        // This keeps insertion order because no sorting is
        List<Integer> filterList = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(filterList); // Fix 2: Actual output is [2, 4]

        // Fix 1: Corrected spelling to .distinct()
        List<Integer> distinctList = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList); // Outputs: [1, 2, 3, 4, 5]

        List<Integer> sortedList = numbers.stream()
                .map(a -> a * 2)
                .peek(a -> System.out.print(a + " "))
                .collect(Collectors.toList());
        System.out.println(sortedList); // sorted list

        // peek use for action any like logs and map use for action using lambda
        // function
        //
        List<Integer> limitList = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.err.println(limitList);

        // also use skip
        List<Integer> skipList = numbers.stream()
                .skip(2)
                .collect(Collectors.toList());
        System.err.println(skipList);

        int step = 2; // This replaces the missing 'b' variable
        List<Integer> itrateList = Stream.iterate(0, a -> a <= 10, a -> a + step)
                .collect(Collectors.toList());
        System.out.println(itrateList);
        // terminal Opration -> count max .get min and limit and so on
        // parallelstream use for multiple threading
    }
}
