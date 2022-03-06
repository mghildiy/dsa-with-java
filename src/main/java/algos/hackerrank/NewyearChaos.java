package algos.hackerrank;

import java.util.Arrays;
import java.util.List;

public class NewyearChaos {

    public static void main(String[] args) {
        minimumBribes(Arrays.asList(1 ,2 ,5 ,3 ,7 ,8 ,6 ,4));
    }

    //1 2 5 3 7 8 6 4
    public static void minimumBribes(List<Integer> q) {
        int totalBriberies = 0;
        for(int i = 0; i < q.size(); i++) {
            int diff = q.get(i) - (i+1);
            if(diff > 2) {
                System.out.println("Too chaotic");
                return;
            }
            if(diff == 1 || diff == 2) {
                totalBriberies += diff;
            }
        }
        System.out.println(totalBriberies);
    }
}
