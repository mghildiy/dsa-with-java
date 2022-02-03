package algos.sorting.incremental;

// O(n2) time complexity
public class BubbleSort {

    public static void main(String[] args) {
        int[] intArray = new int[] {100, 23, 45, 45, 8, -31, 5, 77};

        // number of operations per pass = goTill + 1
        // n - 1, n - 2, n - 3,  ....3, 2, 1
        // total = (n-1) + (n-2)....+3+2+1
        // n*n+....
        // its a stable sort as order of duplicates is maintained
        for(int goTill = intArray.length - 2; goTill >= 1; --goTill) {
            for(int i = 0; i <= goTill; i++) {
                swap(intArray, i, i+1);
            }
        }

        System.out.println(intArray);

        for(int i = 0; i < intArray.length ; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if(arr[i] > arr[j]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
