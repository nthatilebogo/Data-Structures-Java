Objective: To implement and use binary search trees.

boolean isEmpty()
This function will determine whether the binary search tree is empty or not. It returns
true if the binary search tree is empty and false otherwise.

BSTNode<T> clone()
This function will create a new binary tree (with its own nodes) that is a clone of the
original binary search tree. This function should return the root node of the new binary tree.

BSTNode<T> mirror()
This function will create a new binary tree (with its own nodes) that is the mirror
image across the vertical axis of the original binary search tree. This function will return
the root node of the new binary tree.

void insert(T element)
This function will insert the given element in the binary search tree. element will 
be inserted in the correct position in the binary search tree to maintain the sorted order
required by a binary search tree. No duplicates.

boolean deleteByMerge(T element)
This function will delete the given element from the binary search tree. It returns
true if element is removed successfully and false otherwise. This function will use the
delete by merging strategy.

boolean deleteByCopy(T element)
This function will delete the given element from the binary search tree. It returns
true if element is removed successfully and false otherwise. This function will use the
delete by copying strategy.

T search(T element)                       
This function will find the given element in the binary search tree. If element is
found in the binary search tree it should return the element and otherwise it will return
null.

T getPredecessor(T element)
This function will find and return the element stored in the predecessor of the node
containing the given element in the binary search tree. A predecessor of a node N is the
node with the largest element which is less than the element stored in node N. Return null
if:
 The tree is empty
 element is not found in the tree
 element is found in the tree but it does not have a predecessor 

T getSuccessor(T element)
This function will find and return the element stored in the successor of the node
containing the given element in the binary search tree. A successor of a node N is the node
with the smallest element which is greater than the element stored in node N. Return null
in the following scenarios:
 The tree is empty
 element is not found in the tree
 element is found in the tree but it does not have a successor
