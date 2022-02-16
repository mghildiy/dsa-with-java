package algos.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        List<Integer> sums = new ArrayList<>();
        for(int i=0; i<arr.size()-2;i++) {
            for(int j=0; j< arr.get(i).size()-2; j++){
                sums.add(hgSum(arr, i, j));
            }
        }
        return Collections.max(sums);
    }

    private static int hgSum(List<List<Integer>> arr, int r, int c) {
        return arr.get(r).get(c)
                +arr.get(r).get(c+1)
                +arr.get(r).get(c+2)
                +arr.get(r+1).get(c+1)
                +arr.get(r+2).get(c)
                +arr.get(r+2).get(c+1)
                +arr.get(r+2).get(c+2);
    }

}

public class HourGlassSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\work\\learning\\dsa\\dsa-with-java\\src\\main\\java\\algos\\hackerrank\\input"));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
