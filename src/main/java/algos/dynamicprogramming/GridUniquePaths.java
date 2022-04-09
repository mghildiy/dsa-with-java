package algos.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class GridUniquePaths {

    public static void main(String[] args) {
        Map<String, Long> cache = new HashMap();
        System.out.println(numUniquePaths(18,18, cache));
    }

    private static long numUniquePaths(int m, int n, Map<String, Long> cache) {
        if(cache.containsKey(m+":"+n)) return cache.get(m+":"+n);
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return 1;
        long numPathsDown = numUniquePaths(m-1, n, cache);
        long numPathsRight = numUniquePaths(m, n-1, cache);
        cache.put(m+":"+n, numPathsDown + numPathsRight);

        return cache.get(m+":"+n);
    }
}
