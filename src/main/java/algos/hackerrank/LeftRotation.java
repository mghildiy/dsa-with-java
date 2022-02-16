package algos.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftRotation {

    public static void main(String[] args) {
        // time O(n2)...space O(1)
        int[] arr = new int[] {1, 2, 3, 4, 5};
        int d = 4;
        int change = d % arr.length;
        for(int i = 1; i <= change; i++) {
            int first = arr[0];
            for(int j = 0; j < arr.length - 1; j++) {
                arr[j] = arr[j+1];
            }
            arr[arr.length - 1] = first;
        }

        for(int j = 0; j < arr.length; j++) {
            System.out.print(arr[j]);
            System.out.print(" ");
        }
        System.out.println(" ");

        int[] input = new int[] {1, 2, 3, 4, 5};
        anotherMethod(input, 4);
    }

    // time O(n)...space O(n)
    private static void anotherMethod(int[] arr, int d) {
        System.out.println("Another method");
        int change = d % arr.length;
        int[] output = new int[arr.length];
        for(int i = 0; i <= arr.length - 1; i++) {
            if(i >= 0 && i <= change - 1) {
                output[i-change+arr.length] = arr[i];
            } else {
                output[i-change] = arr[i];
            }
        }
        
        for(int j = 0; j < output.length; j++) {
            System.out.print(output[j]);
            System.out.print(" ");
        }
    }
}
