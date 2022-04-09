package algos.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fibonacci {

    private static Map<Integer, Long> numberByFibonacci = new HashMap<>();
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        IntStream.range(0,50).forEach(i -> System.out.println(i + "->" + findFibonacci(i)));
        System.out.println("Total execution time:"+ (System.nanoTime() - startTime));
    }

    private static long findFibonacci(int n) {
        if(numberByFibonacci.containsKey(n))
            return numberByFibonacci.get(n);

        if(n <= 1)
            return 1;

        numberByFibonacci.put(n, findFibonacci(n-1) + findFibonacci(n-2));

        return numberByFibonacci.get(n);
        //return findFibonacci(n-1) + findFibonacci(n-2);
    }
}
