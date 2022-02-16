package algos.sorting.divideandconquer;

import io.vavr.Tuple2;

import java.util.stream.IntStream;

public class MergeSort {

    public static void main(String[] args) {

    }

    private static int[] sort(int[] input) {
        if(input.length == 1)
            return input;

        if(input.length == 2) {
            sortPair(input);
        }

        Tuple2<int[], int[]> partitions = partition(input);
        int[] left = partitions._1;
        int[] right = partitions._2;

        return merge(sort(left), sort(right));
        /*if(left.length == 1 && right.length == 1) {
            if(left[0] <= right[0]){
                return new int[] {left[0], right[0]};
            } else {
                return new int[] {right[0], left[0]};
            }
        }*/
    }

    private static int[] merge(int[] left, int[] arr) {
        // TODO
        return new int[0];
    }

    private static int[] sortPair(int[] input) {
        if(input[0] <= input[1]) {
            int temp = input[0];
            input[0] = input[1];
            input[1] = temp;
        }
        return input;
    }

    private static Tuple2<int[], int[]> partition(int[] input) {
        int mid = (input.length-1) / 2;
        int[] left = IntStream.range(0, mid+1)
                .map(i -> input[i])
                .toArray();
        int[] right = IntStream.range(mid+1, input.length)
                .map(i -> input[i])
                .toArray();
        Tuple2<int[], int[]> leftAndRight = new Tuple2<int[], int[]>(left, right);

        return leftAndRight;
    }
}
