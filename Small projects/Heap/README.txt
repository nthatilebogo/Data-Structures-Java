Objectives:
To learn how to implement different types of binary heaps.

Heap.java

boolean isEmpty()
This function will determine whether the heap is empty or not. It returns true if the
heap is empty and false otherwise.

MinHeap.java

void insert(T elem)
Insert the given element elem into the heap. If the element is already in the heap, insert
it again as duplicates are allowed. Restore the heap property after insertion.

T removeMin()
Remove the minimum element from the heap and return it. Restore the heap property
after removal. If the heap is empty, return null.

void delete(T elem)
Delete the given element elem from the heap. Restore the heap property after deletion.
If the element elem is not in the heap, do nothing and return.

MaxHeap.java

void insert(T elem)
Insert the given element elem into the heap. If the element is already in the heap, insert
it again as duplicates are allowed. Restore the heap property after insertion.

T removeMax()
Remove the maximum element from the heap and return it. Restore the heap property
after removal. If the heap is empty, return null.

void delete(T elem)
Delete the given element elem from the heap. Restore the heap property after deletion.
If the element elem is not in the heap, do nothing and return. 