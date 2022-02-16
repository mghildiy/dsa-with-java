package algos.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println("Value found at index:"+ searchIteratively(arr,1));
        System.out.println("Value found at index:"+ searchIteratively(arr,8));
        System.out.println("Value found at index:"+ searchIteratively(arr,4));
        System.out.println("Value found at index:"+ searchIteratively(arr,0));
        System.out.println("Value found at index:"+ searchIteratively(arr,9));

        System.out.println("Value found at index:"+ searchRecursively(arr,1, 0 ,7));
        System.out.println("Value found at index:"+ searchRecursively(arr,8,0 ,7));
        System.out.println("Value found at index:"+ searchRecursively(arr,4, 0 ,7));
        System.out.println("Value found at index:"+ searchRecursively(arr,0, 0 ,7));
        System.out.println("Value found at index:"+ searchRecursively(arr,9, 0 ,7));
    }

    private static int searchRecursively(int[] arr, int value, int start, int end) {
        if(start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if(arr[mid] == value)
            return mid;
        if(arr[mid] < value){
            start = mid + 1;
        } else {
            end = mid - 1;
        }
        return searchRecursively(arr, value, start, end);
    }

    private static int searchIteratively(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(arr[mid] == value)
                return mid;
            if(arr[mid] < value){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
