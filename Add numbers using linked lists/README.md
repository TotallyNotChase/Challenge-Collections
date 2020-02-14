# The Problem
The "Add 2 Numbers represented by Linked Lists" problem states-
> Given *two numbers* represented by *two* Linked Lists in reverse order, write a function that returns the sum in form of a Linked List.

### Constraints
* The input Linked Lists **must** be *non-empty*.
* The input Linked Lists **should** represent *positive integers*.
* The input Linked Lists **should** represent the number in *reverse order*.

  e.g `347` would be represented as `7->4->3`
* The input Linked Lists **should not** contain any leading `0`s.

  e.g `0000347` to `7->4->3->0->0->0->0` is **invalid**.

## A General Case of the same Problem
> Given *multiple numbers* represented by *multiple* Linked Lists in reverse order, write a function that returns the sum in form of a Linked List.

The only difference in this case is that it should be able to sum any amount of input Linked Lists.

# My solution
* Initialize the `HEAD` of the result List. Here it's named `resultList`.
* Initialize a variable `resultValue` to keep track of the sum of current digits.
* Initialize a variable `overflow` to keep track of the carry of the sum.
* An outer loop wraps the main code.

  The loop stops when *all Linked Lists are exhausted* AND `overflow` is 0.
* Run another inner loop to iter through all array slots.

  This inner loop extracts the `val` (value) from each array slot's Linked List and adds it to `resultValue`. Ignores any Linked Lists that have already been exhausted, i.e equals `NULL`.

  Each Linked List is also progressed through using `->next`.

  Hence if the **0th** slot of said array contains the Linked List `7->4->3`, then in the first iteration of the **outer loop**, `val` would be `7`. In the next iteration, it'd be `4` and so on.

* Any previous `overflow` is now added to `resultValue`, which sets `overflow` back to 0.
* `resultValue` is passed onto a function which strips all digits except one, depending on the **base of the number system** used (defaults to 10 i.e Decimal Number System).

  For example, `34` would be stripped to `4` for Decimal Number System.

  The remaining value is assigned to `overflow`.
* The same function, assigns the stripped value of `resultValue` to a `Node` and appends it to `resultList` and returns the `HEAD` back.
* `resultValue` is now reset to `0`.
* The loop resets

The end result should be a Linked List that contains the sum of the number in reverse order. Here's an example-

![image](https://user-images.githubusercontent.com/44284917/74551824-ef246b80-4f79-11ea-8200-20b787989f06.png)

# Usage
## For `C`-
The code contains documentation as to how you can use it. Everything you would change/mess around with is in `testcase()` with suitable examples. I'll paste the comments from there-
* First Declare all the Pointers to Linked Lists that you'd like to use and assign them to `NULL`
* Then use `createList()` to create an entire Linked List at once.

  This function requires a pointer to `HEAD`, so it can add nodes to the list

  It also needs the **dataset** itself, which should be an `int` array

  The final param is the **length** of this array

* Lastly use `sumLinkedListNumbers()` and pass all the Pointers to Linked Lists in an array
  Pass in the length of the array as well

* You can then use `printList()` to print the result

* If you'd like, you can also use `printListArray()`and pass in the array of pointer to lists to print them all at once. This may be useful to make sure the input lists were created correctly

* Don't forget to use `destroyList()` or `destroyListArray()`, depending on context, to free the memory you used up.
## For `Java`-
Same as above except you don't need to do any GC and you can use `.add()` to add nodes.
