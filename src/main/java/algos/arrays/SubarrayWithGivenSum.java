package algos.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubarrayWithGivenSum {

    public static void main(String[] args) {
        //System.out.println(subarraySum(new int[] {1,2,3,7,5}, 5, 12));
        System.out.println(subarraySum(new int[] {142,112,54,69,148,45,63,158,38,60,124,142,130,179,117,36,191,43,89,
                107,41,143,65,49,47,6,91,130,171,151,7,102,194,149,30,24,85,155,157,41,167,177,132,109,145,40,27,124,
                138,139,119,83,130,142,34,116,40,59,105,131,178,107,74,187,22,146,125,73,71,30,178,174,98,113},
                74, 665));
    }

    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        ArrayList<Integer> output = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            int sumSoFar = 0;
            int j = i;
            while(sumSoFar < s && j < arr.length) {
                sumSoFar += arr[j++];
                if(sumSoFar == s) {
                    output.add(i+1);
                    output.add(j);
                    return  output;
                }
            }
        }
        output.add(-1);
        return output;
    }
}
