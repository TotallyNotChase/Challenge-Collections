#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<time.h>

typedef struct ListNode {
    // Basic Node data structure of a Linked List
    int val;
    struct ListNode* next;
}node_t;

static const int BASE = 10;         // Denotes the Base of the number system used

node_t* appendNode(node_t* headref, node_t* newNode)
{
    // Append given node to given HEAD
    if (headref == NULL)
    {
        return newNode;
    }
    node_t* temp = headref;
    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    temp->next = newNode;
    return headref;

}

node_t* createNode(int data, char* errorLocation)
{
    // Create and return a node with given data
    node_t* newNode = malloc(sizeof(*newNode));
    if (newNode == NULL)
    {
        fprintf(stderr, "An error occurred while allocating memory for node_t in %s\n", errorLocation);
        exit(EXIT_FAILURE);
    }
    newNode->val = data;
    newNode->next = NULL;
    return newNode;
}

node_t* createList(int data[], int length)
{
    // Calls createNode and appendNode iteratively to assign whole array of data to list
    node_t* linkedList = NULL;
    for (int i = 0; i < length; i++)
    {
        node_t* newNode = createNode(data[i], "createList");
        linkedList = appendNode(linkedList, newNode);
    }
    return linkedList;
}

void printList(node_t* headref)
{
    // Basic Linked List printing
    if (headref == NULL)
    {
        printf("\nLinked List is empty!\n");
        return;
    }
    printf("HEAD->");
    while (headref != NULL)
    {
        printf("%d->", headref->val);
        headref = headref->next;
    }
    printf("NULL\n");
}

void printListArray(node_t* linkedListArray[], int length)
{
    // Calls printList iteratively to print whole array of lists
    for (int i = 0; i < length; i++)
    {
        printList(linkedListArray[i]);
    }
}

node_t* deleteNode(node_t* headref, char* errorLocation)
{
    // Deletes the very last node of a linked list
    if (headref == NULL)
    {
        return headref;
    }
    node_t* temp = headref;
    node_t* prevNode = temp;
    if (prevNode == NULL)
    {
        fprintf(stderr, "An error occured while allocating memory for node_t in %s\n", errorLocation);
        exit(EXIT_FAILURE);
    }
    while (temp->next != NULL)
    {
        prevNode = temp;
        temp = temp->next;
    }
    prevNode->next = NULL;
    free(temp);
    return headref;
}

node_t* destroyList(node_t* headref)
{
    // Deletes each node of a list from reverse and finally frees the HEAD
    while (headref->next != NULL)
    {
        headref = deleteNode(headref, "destroyList");
    }
    free(headref);
    return NULL;
}

void destroyListArray(node_t** linkedListArray[], int length)
{
    // Calls destroyList iteratively to print whole array of lists
    for (int i = 0; i < length; i++)
    {
        *linkedListArray[i] = destroyList(*linkedListArray[i]);
    }
}

bool isEnd(node_t* linkedListArray[], int length)
{
    // Checks if all lists have been exhausted
    int nullCount = 0;
    for (int i = 0; i < length; i++)
    {
        if (linkedListArray[i] == NULL)
        {
            nullCount++;
        }
    }
    return nullCount == length;
}

node_t* resvalAppend(node_t* resultList, int resultValue, int* overflow)
{
    /**
    * Appends a Node with one digit split from resultValue
    *
    * Creates Node with data = resultValue % BASE
    * Assigns resultValue / BASE to overflow
    */
    div_t splitDigits = div(resultValue, BASE);
    *overflow = splitDigits.quot;
    node_t* newNode = createNode(splitDigits.rem, "resvalAppend");
    resultList = appendNode(resultList, newNode);
    return resultList;
}

node_t* sumLinkedListNumbers(node_t* linkedListArray[], int length)
{
    /** 
    * Accepts an array of pointers to HEAD of Linked Lists
    * along with the number of Linked Lists
    *
    * Returns a pointer to result Linked List
    */
    int resultValue = 0, overflow = 0;
    node_t* resultList = NULL;
    while (!isEnd(linkedListArray, length) || overflow != 0)
    {
        // Loop only breaks once all lists are exhausted AND overflow is 0
        for (int i = 0; i < length; i++)
        {
            if (linkedListArray[i] != NULL)
            {
                // Adding in values to resval, ignoring NULL(s)
                resultValue += linkedListArray[i]->val;
                linkedListArray[i] = linkedListArray[i]->next;
            }
        }
        // Adding in overflow, if any
        resultValue += overflow;
        overflow = 0;
        // Putting resultValue through a function to strip overflow
        resultList = resvalAppend(resultList, resultValue, &overflow);
        resultValue = 0;
    }
    return resultList;
}

void testcase()
{
    // Mess with inputs here
    /**
    * First Declare all the Pointers to Linked Lists that you'd like to use and assign them to NULL
    * Then use createList to create an entire Linked List at once instead of doing multiple appendNode
    *
    * createList requires the address of the pointer to HEAD, so it can modify the list
    * It also needs the dataset itself, which should be an int array
    * The final param is the length of this array
    *
    * Lastly use addNumbers and pass all the Pointers to Linked Lists in an array
    * Pass in the length of the array as well
    *
    * You can then use printList to print the result
    *
    * If you'd like, you can also use printListArray and pass in the array of pointer to lists
    * to print them all at once
    * This may be useful to make sure the input lists were created correctly
    *
    * Use destroyList or destroyListArray, depending on context, to free the memory you used up
    */
    // Here's an example adding 12 lists
    node_t* l1 = NULL, * l2 = NULL, * l3 = NULL, * l4 = NULL, * l5 = NULL, * l6 = NULL, * l7 = NULL, * l8 = NULL, * l9 = NULL, * l10 = NULL, * l11 = NULL, * l12 = NULL;
    node_t* resultList;
    l1 = createList((int[]) { 9 }, 1);
    l2 = createList((int[]) { 9 }, 1);
    l3 = createList((int[]) { 9 }, 1);
    l4 = createList((int[]) { 9 }, 1);
    l5 = createList((int[]) { 9 }, 1);
    l6 = createList((int[]) { 9 }, 1);
    l7 = createList((int[]) { 9, 9 }, 2);
    l8 = createList((int[]) { 9, 9 }, 2);
    l9 = createList((int[]) { 9, 9 }, 2);
    l10 = createList((int[]) { 9, 9 }, 2);
    l11 = createList((int[]) { 9, 9 }, 2);
    l12 = createList((int[]) { 9, 9 }, 2);
    node_t* listArray[] = {
        l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12
    };
    printf("Input Lists:-\n");
    printListArray(listArray, 12);
    resultList = sumLinkedListNumbers(listArray, 12);
    printf("Output List:-\n");
    printList(resultList);
    // ResultList is freed and assigned to NULL
    resultList = destroyList(resultList);
    // The pointer to all Linked Lists, l1, l2.... are freed and assigned to NULL
    // This can be done one by one as well by doing `l1 = destroyList(l1)` <-> No need to pass address in this case
    destroyListArray((node_t** []) { &l1, &l2, &l3, &l4, &l5, &l6, &l7, &l8, &l9, &l10, &l11, &l12 }, 12);
}

int main()
{
    clock_t t0 = clock();
    testcase();
    clock_t t1 = clock();
    double time = (double)(t1 - t0) / CLOCKS_PER_SEC;
    printf("Time: %f\n", time);
    return 0;
}