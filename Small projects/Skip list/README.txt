Objective: learning how to implement and use skip lists.

boolean isEmpty()
This function will determine whether the skip list is empty or not. It returns true if
the skip list is empty and false otherwise.

void insert(T key)
This function will insert the given key in the skip list. The key should be inserted in the
correct position in the skip list to maintain the increasing order. The key will also be added
to all the levels as determined by the chooseLevel() method.

T first()
This function will determine the first key in the skip list. It returns the first key value
in the skip list.

T last()
This function should determine the last key in the skip list. It returns the last key value
in the skip list.