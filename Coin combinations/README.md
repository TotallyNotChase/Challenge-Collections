# The Problem
Find and print all the **distinct** combinations of changes (in dimes, nickels and pennies) that can represent a given amount of money (in cents)
* **Dimes** represent 10 cents
* **Nickels** represent 5 cents
* **Pennies** represent 1 cent

## A general case
Find and print all the **distinct** combination of *given* changes that can represent a given amount of money

*The change names and values are **not** constant in this case and instead are provided by the user*

# My Solution
* Initialize a change Dictionary given by user to know what to call each change value
* Initialize a change value array from the dictionary to know which values we have at hand


* In `waysOfRepresenting(int money)` or `WaysOfRepresenting(int money)`
* Iterate the `changeValuesArray` backwards to determine which change to start from
* Since the array is sorted in ascending order, starting backwards guarantees biggest values will be checked first
* If value is 17 and maximum change value is 10 (i.e dime), it'll start from 10
* If value is 9 and maximum change value is 10 (i.e dime), it'll have to start from a lesser value


* In `waysOfRepresenting(int[] progressArray, int money, int nextIndex)` or `WaysOfRepresenting(int[] progressArray, int money, int nextIndex)`
* Recursively step through all **plausible** combinations
* Each time a **new array** is created and passed with the current progress
* This is done to *avoid change in the current scope's array*
* Each progress value gets **substracted** from money
* Next recursive call must check the **next index slot**


* Example- `changeValuesArray = { 1, 5, 10 }`, `money` at starting = `17`
* **(i)** --- At first, we start off with `i = 1`, as `17 / 10 = 1`
* **(ii)** --- Now the next call checks for the next change value, in this case `5`
* Remaining `money = 17 - 10 * 1 = 7`, start off with `i = 1`, as `7 / 5 = 1`
* **(iii)** --- Now the next call checks for the next change value, in this case `1`
* Remaining `money = 7 - 5 * 1 = 2`, since `changeValue = 1`, we know we'll need `2` of this change
* *One solution is found!*


* Now the function returns to the previous call, in this case **(ii)** -> `changeValue = 5`
* Keep in mind the **2nd slot** of `progressArray` is still `1`, since we still have **1 changes** of value `10` at hand
* Hence, `money = 17 - 10 * 1 = 7`, just like last time in **(ii)**
* We started off with `i = 1`, as `7 / 5 = 1`, now we will check for `i = 0`
* Remaining money = `7 - 5 * 0 = 7`, since `changeValue = 1`, we know we'll need `7` of this change
* *Another solution is found!**


* Now the function goes back to the *very first* for loop, in this case **(i)** -> `changeValue = 10`
* `money` is now `17`, as this is a **full reset**
* Since we started off with `i = 1`, we'll now check for `i = 0`
* Just like before the inner recursion loops will iterate again, this time with **0 changes** of value `10`


* and so on.....

# Usage
* Initialize the `Coins` class from the `main`/`Main` method by passing in the dictionary of **change values** and **change names** you require
* Now, call the method `waysOfRepresenting`/`WaysOfRepresenting` and pass in the amount of money
