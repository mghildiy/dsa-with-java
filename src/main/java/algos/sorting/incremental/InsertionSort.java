package algos.sorting.incremental;

// O(n2) time complexity
public class InsertionSort {

    public static void main(String[] args) {
        int[] intArray = new int[]{100, 23, 45, 45, 8, -31, 5, 77};
        //int[] intArray = new int[]{-1,0,12,34,55,66,77};

        /*for(int indexToInsert = 1; indexToInsert <= intArray.length - 1; indexToInsert++) {
            int elementToInsert = intArray[indexToInsert];
            int lastIndex = indexToInsert;
            for (int currentIndex = indexToInsert - 1; currentIndex >= 0; currentIndex--) {
                if(elementToInsert >= intArray[currentIndex]) {
                    intArray[lastIndex] = elementToInsert;
                    break;
                } else {
                    intArray[lastIndex] = intArray[currentIndex];
                    lastIndex = currentIndex;
                }
            }
            intArray[lastIndex] = elementToInsert;
        }*/

        for(int indexToInsert = 1; indexToInsert <= intArray.length - 1; indexToInsert++){
            int elementToInsert = intArray[indexToInsert];
            int currentIndex = indexToInsert - 1;
            while(currentIndex >= 0) {
                // if we find element in sorted portion greater than elementToInsert, we shift the array
                if(intArray[indexToInsert] > intArray[currentIndex]) { // n times for worst case => O(n*n)
                    shift(intArray, currentIndex+1, indexToInsert-1); // n times for worst case => O(n*n)
                    intArray[currentIndex+1] = elementToInsert;
                    break;
                }
                currentIndex -= 1;
            }
            // if we reach start of the sorted portion then it means we have to place elementToInsert at start
            if(currentIndex < 0) {
                shift(intArray, 0, indexToInsert-1);
                intArray[0] = elementToInsert;
            }
        }

        for(int i = 0; i < intArray.length ; i++) {
            System.out.println(intArray[i]);
        }
    }

    public static void shift(int[] arr, int start, int end) {
        for(int i = end; i >= start; i--) {
            arr[i+1] = arr[i];
        }
    }
}
