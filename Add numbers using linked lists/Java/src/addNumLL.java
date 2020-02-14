import java.util.LinkedList;

public class addNumLL {
    
    @SafeVarargs
    static boolean isEnd(int currentIndex, LinkedList<Integer>... linkedLists) {
        // Checks if all lists have been exhausted
        int nullCount = 0;
        for (LinkedList<Integer> currentList: linkedLists) {
            try {
                currentList.get(currentIndex);
            } catch (IndexOutOfBoundsException e) {
                nullCount++;
            }
        }
        return nullCount == linkedLists.length;
    }
    
    private static int appendResultValueAndGetOverflow(LinkedList<Integer> resultList, int resultValue) {
        /**
         * Appends a Node with one digit split from resultValue
         *
         * Creates Node with data = resultValue % BASE (10)
         * Assigns resultValue / BASE (10) to overflow
         */
        int remainder = resultValue % 10;
        int quotient = resultValue / 10;
        resultList.add(remainder);
        return quotient;
    }
    
    @SafeVarargs
    private static LinkedList<Integer> sumTwoNumLL(LinkedList<Integer>... linkedLists) {
        /** 
         * Accepts an array of Linked Lists
         *
         * Returns a pointer to result Linked List
         */
        LinkedList<Integer> resultList = new LinkedList<Integer>();
        int resultValue = 0, overflow = 0, i = 0;
        while(!isEnd(i, linkedLists) || overflow != 0)
        {
         // Loop only breaks once all lists are exhausted AND overflow is 0
            for (LinkedList<Integer> currentList : linkedLists) {
             // Adding in values to resval, ignoring NULL(s)
                try {
                    resultValue += currentList.get(i);
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
            // Adding in overflow, if any 
            resultValue += overflow;
            // Putting resultValue through a function to strip overflow
            overflow = appendResultValueAndGetOverflow(resultList, resultValue);
            resultValue = 0;
            i++;
        }
        return resultList;
    }
    
    private static void printList(LinkedList<Integer> linkedList) {
        // Pretty print the Linked List
        System.out.print("HEAD->");
        for (int item : linkedList) {
            System.out.print(item);
            System.out.print("->");
        }
        System.out.println("NULL");
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<Integer>();
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l1.add(7);
        l1.add(4);
        l1.add(3);
        l2.add(7);
        l2.add(4);
        l2.add(3);
        LinkedList<Integer> resultIntegers = sumTwoNumLL(l1, l2);
        printList(resultIntegers);
    }
}
