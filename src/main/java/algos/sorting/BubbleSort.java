package algos.sorting;

public class BubbleSort {

    public static void main(String[] args) {
        int[] intArray = new int[] {100, 23, 45, 45, 8, -31, 5, 77};

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
        int temp = arr[i];
        if(arr[i] > arr[j]) {
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
