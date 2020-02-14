namespace Coin_Change_Permutation
{
    internal class ArrayUpdater
    {
        /** 
         * Creates a new array from given array
         * Increments the value of given index with given value in new array
         * Returns the new array
         */
        internal static int[] GetUpdatedArray(int[] inputArray, int index, int value)
        {
            int[] updatedArray = (int[])inputArray.Clone();
            updatedArray[index] += value;
            return updatedArray;
        }
    }
}
