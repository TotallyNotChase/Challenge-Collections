import java.util.Arrays;
import java.util.Map;

public class Coins {

    private int[] changeArray;                          // Keeps track of the values of each change - used for calculation
    private boolean combinationFound;                   // Keeps track of success - i.e solutions found or not
    private Map<Integer, String> changeDictionary;      // Keeps track of the names given to each change - used for printing

    public Coins(Map<Integer, String> changeDictionary)  throws IllegalArgumentException {
     // Constructor for initiallizing the names and values of coin change
        int i = 0;
        this.changeArray = new int[changeDictionary.size()];
        this.changeDictionary = changeDictionary;
        for(Map.Entry<Integer, String> item : changeDictionary.entrySet()) {
            changeArray[i++] = item.getKey();
        }
        // Sorting the array in acsending order -- Important for the algorithm
        Arrays.sort(this.changeArray);
        if (this.changeArray[0] <= 0) {
            throw new IllegalArgumentException("Change value cannot be 0");
        }
    }
    
    private void printHandler(int[] changeResult) {
        // Pretty print change array
        for (int i = changeResult.length - 1; i > -1; i--) {
            System.out.print(Integer.toString(changeResult[i]) + " " + changeDictionary.get(changeArray[i]));
            System.out.print(i == 0 ? "\n" : ", ");
        }
    }

    public void waysOfRepresenting(int money) {
        /** 
         * Overloaded method abstracting the actual algorithm
         * Sets up the actual algorithm
         */
        int[] progressArray = new int[] {0, 0, 0};
        combinationFound = false;
        for (int i = this.changeArray.length - 1; i > -1; i-- ) {
         // Iterates the changeValuesArray backwards to determine which change to start from

            /** 
             * Since the array is sorted in ascending order, starting backwards guarantees biggest values will be checked first
             * If value is 17 and maximum change value is 10 (i.e dime), it'll start from 10
             * If value is 9 and maximum change value is 10 (i.e dime), it'll have to start from a lesser value 
             */
            if (money >= this.changeArray[i]) {
                waysOfRepresenting(progressArray, money, i);
                break;
            }
        }
        if (!combinationFound) {
         // Sometimes there are no possible combinations to represent given amount
            printHandler(progressArray);
            System.out.println("No solutions found...");
        }
    }

    private void waysOfRepresenting(int[] progressArray, int money, int nextIndex) {
     // Actual method that handles the recursive algorithm
        if (nextIndex < 0) {
         // All indexes are exhausted and no results are found, invalid path
            return;
        }
        int currentChange = this.changeArray[nextIndex];
        if (money == 0 || currentChange == 1) {
            /** 
             * Money is exhausted, solution has been found
             * Current change is 1, no need for additional calculation since money / 1 = money
             */
            printHandler(ArrayUpdater.getUpdatedArray(progressArray, nextIndex, money));
            combinationFound = true;
            return;
        }
        for (int i = money / currentChange; i >= 0; i--) {
            /** 
             * Recursively step through all plausible combinations
             * Each time a new array is created and passed with the current progress
             * This is done to avoid change in the current scope's array
             * Each progress value gets substracted from money
             * Next recursive call must check the next index slot
             * 
             * Example- changeValuesArray = { 1, 5, 10 }, money at starting = 17
             * (i) -- At first, we start off with i = 1, as 17 / 10 = 1
             * (ii) -- Now the next call checks for the next change value, in this case 5
             * Remaining money = 17 - 10 * 1 = 7, start off with i = 1, as 7 / 5 = 1
             * (iii) -- Now the next call checks for the next change value, in this case 1
             * Remaining money = 7 - 5 * 1 = 2, since changeValue = 1, we know we'll need 2 of this change
             * One solution is found!
             * 
             * Now the function returns to the previous call, in this case (ii) -> changeValue = 5
             * Keep in mind the 2nd slot of progressArray is still 1, since we still have 1 changes of value 10 at hand
             * Hence, money = 17 - 10* 1 = 7, just like last time in (ii)
             * We started off with i = 1, as 7 / 5 = 1, now we will check for i = 0
             * Remaining money = 7 - 5 * 0 = 7, since changeValue = 1, we know we'll need 7 of this change
             * Another solution is found!
             * 
             * Now the function goes back to the very first for loop, in this case (i) -> changeValue = 10
             * Money is now 17, as this is a full reset
             * Since we started off with i = 1, we'll check for i = 0
             * Just like before the inner recursion loops will iterate again, this time with 0 changes of value 10
             * 
             * and so on.....
             */
            waysOfRepresenting(ArrayUpdater.getUpdatedArray(progressArray, nextIndex, i), money  - currentChange * i, nextIndex - 1);
        }
    }
}
