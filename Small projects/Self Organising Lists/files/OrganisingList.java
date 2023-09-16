/**
 * Name: Onthatile
 * Student Number: u20589507
 */

abstract class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    
    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    
    //Recursive functions

    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {

        //Your code goes here
    	if(node.equals(null)) {
    		return 0;
    	}
    	if(node.next == null) {
    		return node.key;
    	}
    	else {
    		return node.key + sumRec(node.next);
    	}
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {

        //Your code goes here
    	if(node == null) {
    		return 0;
    	}
    	if(node.next == null) {
    		node.data = node.key;
    		return node.key;
    	}
    	else {
    		int d = (node.key*sumRec(node)) - dataRec(node.next);
    		node.data = d;
    		return d;
    	}
    }


    //Organising List functions

    /**
     * Retrieve the node with the specified key, move the node as required and recalculate all data fields.
     * @return The node with its data value before it has been moved, otherwise 'null' if the key does not exist.
     * Implement only in specific subclass!
     */
    public abstract ListNode searchNode(Integer key);

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public boolean contains(Integer key) {

        //Your code goes here
    	ListNode node = root;
    	if (node == null) {
    		return false;
    	}
    	while (node != null) {
			if (node.key.equals(key)) {
				return true;
			} else {
				node = node.next;
			}
		}
    	return false;
    }

    /**
     * Insert a new key into the linked list.
     * New nodes should be inserted at the back.
     * calculateData() should be called after insertion.
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {

        //Your code goes here
    	ListNode node = root;
    	ListNode prev = null;
    	if (root == null) {
    		root = new ListNode(key);
    		calculateData();
    		return;
    	}
    	while (node != null) {
			if (node.key.equals(key)) {
				return;
			} else {
				prev = node;
				node = node.next;
			}
		}
    	if (prev != null) {
    		prev.next = new ListNode(key);
		}
    	calculateData();
    }
	
    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {

        //Your code goes here
    	ListNode node = root;
    	ListNode prev = null;
    	if (node == null) {
    		return;
    	}
    	while (node != null) {
			if (node.key.equals(key)) {
				if (prev == null) {
					
					if (node.next == null) {
						root = null;
					}
					else {
						prev = node.next;
						node.next = null;
						root = prev;
					}
				}
				else {
					if (node.next == null) {
						prev.next = null;
					} else {
						prev.next = node.next;
						node.next = null;
					}
				}
				calculateData();	
				return;
			} else {
				prev = node;
				node = node.next;
			}
		}

    }


    //Helper functions

    


}