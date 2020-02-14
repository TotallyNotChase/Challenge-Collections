using System;
using System.Collections.Generic;

namespace Coin_Change_Permutation
{
    internal class Coins
    {
        private Dictionary<int, string> changeNamesLookup;      // Keeps track of the names given to each change - used for printing
        private int[] changeValuesArray;                        // Keeps track of the values of each change - used for calculation
        private bool solutionsFound;                            // Keeps track of success - i.e solutions found or not
        public Coins(Dictionary<int, string> changeDict)
        {
            // Constructor for initiallizing the names and values of coin change
            if (changeDict.ContainsKey(0))
            {
                throw new ArgumentException("Value of change cannot be 0");
            }
            this.changeNamesLookup = changeDict;
            this.changeValuesArray = new int[changeNamesLookup.Count];
            int i = 0;
            foreach (int key in this.changeNamesLookup.Keys)
            {
                this.changeValuesArray[i++] = key;
            }
            // Sorting the array in acsending order -- Important for the algorithm
            Array.Sort(this.changeValuesArray);
        }

        private void PrintChangeArray(int[] changeResultArray)
        {
            // Pretty print change array
            for (int i = changeResultArray.Length - 1; i >= 0; i--)
            {
                Console.Write(changeResultArray[i] + " " + changeNamesLookup[this.changeValuesArray[i]]);
                Console.Write(i == 0 ? "\n" : ", ");
            }
        }

        public void WaysOfRepresenting(int money)
        {
            /** 
             * Overloaded method abstracting the actual algorithm
             * Sets up the actual algorithm
             */
            solutionsFound = false;
            int[] progressArray = new int[] { 0, 0, 0 };
            for (int i = this.changeValuesArray.Length - 1; i >= 0; i--)
            {
                // Iterates the changeValuesArray backwards to determine which change to start from

                /** 
                 * Since the array is sorted in ascending order, starting backwards guarantees biggest values will be checked first
                 * If value is 17 and maximum change value is 10 (i.e dime), it'll start from 10
                 * If value is 9 and maximum change value is 10 (i.e dime), it'll have to start from a lesser value 
                 */
                if (money >= this.changeValuesArray[i])
                {
                    WaysOfRepresenting(progressArray, money, i);
                    break;
                }
            }
            if (!solutionsFound)
            {
                // Sometimes there are no possible combinations to represent given amount
                PrintChangeArray(progressArray);
                Console.WriteLine("No Solutions found...");
            }
        }

        private void WaysOfRepresenting(int[] progressArray, int money, int nextIndex)
        {
            // Actual method that handles the recursive algorithm
            if (nextIndex < 0)
            {
                // All indexes are exhausted and no results are found, invalid path
                return;
            }
            int currentChangeValue = this.changeValuesArray[nextIndex];
            if (money == 0 || currentChangeValue == 1)
            {
                /** 
                 * Money is exhausted, solution has been found
                 * Current change is 1, no need for additional calculation since money / 1 = money
                 */
                PrintChangeArray(ArrayUpdater.GetUpdatedArray(progressArray, nextIndex, money));
                solutionsFound = true;
                return;
            }
            for (int i = money / currentChangeValue; i >= 0; i--)
            {
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
                WaysOfRepresenting(ArrayUpdater.GetUpdatedArray(progressArray, nextIndex, i), money - currentChangeValue * i, nextIndex - 1);
            }
        }
    }
}
