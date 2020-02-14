
public class ArrayUpdater {

    static int[] getUpdatedArray(int[] inputArray, int index, int value) {
        /** 
         * Creates a new array with data from given array
         * Increments value of given index with given value
         * Returns the new array;
         */
        int[] updatedArray = new int[inputArray.length];
        System.arraycopy(inputArray, 0, updatedArray, 0, inputArray.length);
        updatedArray[index] += value;
        return updatedArray;
    }
}
