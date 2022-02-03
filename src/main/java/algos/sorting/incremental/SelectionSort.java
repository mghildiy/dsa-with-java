package algos.sorting.incremental;

// O(n2) time complexity
public class SelectionSort {
    public static void main(String[] args) {
        int[] intArray = new int[]{100, 23, 45, 45, 8, -31, 5, 77};

        // number of operations per pass = goTill
        // n,n - 1 , n - 2,  ....3, 2, 1
        // total = n*n+....
        // main diff with bubble sort is that number of swaps are far less as we sawp only when a pass ends
        // its a unstable sort as order of duplicates is not maintained
        for(int goTill = intArray.length-1; goTill >= 1; goTill--) {
            int maxValueIndex = 0;
            for(int currentIndex = 1; currentIndex <= goTill; currentIndex++) {
                maxValueIndex = updateMaxValueIndex(intArray, maxValueIndex, currentIndex);
            }
            swap(intArray, maxValueIndex,goTill);
        }
        for(int i = 0; i < intArray.length ; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static int updateMaxValueIndex(int[] arr, int maxValueIndex, int currentIndex) {
        if(arr[currentIndex] > arr[maxValueIndex])
            return currentIndex;
        return maxValueIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        if(arr[i] > arr[j]) {
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
