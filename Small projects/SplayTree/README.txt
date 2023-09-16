Objectives
To learn how self-adjusting trees work and how to implement this
functionality using different strategies.


SplayTree.java

boolean insert(T elem)
This function will insert the given element into the tree, but only if it is not already
in the tree. Returns true if the element was successfully inserted into the tree and false
otherwise. The insert will follow normal binary search tree rules of having larger elements
on the right and smaller elements on the left.

boolean contains(T elem)
This function should determine whether the given element is present in the tree. Returns
true if the element is found in the tree and false otherwise.

void access(T elem, SplayType type)
This function will first determine whether the given element is in the tree. If so, the
function should use the provided enumeration SplayType to determine which self-adjusting
strategy type has been given to use on the tree to move the node with the given element. The
appropriate method below should then be invoked to adjust the tree. If the given element
is not found in the tree, it should simply be inserted into the tree (with no splaying or
semi-splaying).

void splay(Node<T> node)
This function will splay the tree so that the given node is the root of the tree when
the function returns.

void semisplay(Node<T> node)
This function will semi-splay the tree so that the given node is moved towards the
root of the tree when the function returns.