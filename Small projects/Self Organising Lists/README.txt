Objective: to implement a self organising list

OrganisingList.java

boolean contains(Integer key)
If a node exists with the given key, return true, otherwise return false.

void insert(Integer key)
Create a new node with the given key and insert it at the back of the list. The
calculateData() method will be used to update all the node’s data fields after an
insert. If the key already exists, then do nothing.

void delete(Integer key)
Delete the node with the given key. calculateData() should be called to update the
data fields of all nodes. See description below. If the key does not exist, do nothing.


MoveToFrontOrganisingList.java

ListNode searchNode(Integer key)
Search the node with the given key. If found, the node should then be moved to the front
of the list. Since the data fields depend on the order of the list, calculateData() needs to
be called again. The value returned should be the node with its data field value before it
was moved. If the node with the given key does not exist, return null.


TransposeOrganisingList.java

ListNode searchNode(Integer key)
Search the node with the given key. If found, the node should then be swapped with
it’s predecessor, unless it is the root. Since the data fields depend on the order of the list,
calculateData() needs to be called again. The value returned should be the node with its
data field value before it was moved. If the node with the given key does not exist, return
null.


Recursive Calculation and Traversal of Linked List

OrganisingList.java

public static dataRec(ListNode node)
Recursively calculate the data value for the given node, setting the data field to the
calculated value before returning it.

public static sumRec(ListNode node)
Recursively calculate the sum of keys starting with the given node to the end of the list.
