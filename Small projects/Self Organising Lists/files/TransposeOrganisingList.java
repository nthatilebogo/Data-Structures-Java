/**
 * Name: Onthatile
 * Student Number: u20589507
 */

public class TransposeOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate all data fields.
     * @return The node with its data value before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    @Override
    public ListNode searchNode(Integer key) {

	//Your code goes here
    	ListNode node = root;
    	ListNode prev = null;
    	ListNode prev2 = null;
    	if (node == null) {
    		return null;
    	}
    	while (node != null) {
			if (node.key.equals(key)) {
				if (prev == null) {
					return node;
				} else {
					if (node.next == null) {
						if (prev2 == null) {
							node.next = prev;
							prev.next = null;
							root = node;
							ListNode r = new ListNode(node.key,node.data);
							calculateData();
							return r;
						} else {
							prev2.next = node;
							node.next = prev;
							prev.next = null;
							ListNode r = new ListNode(node.key,node.data);
							calculateData();
							return r;
						}
					} else {
						if (prev2 == null) {
							
							
							prev.next = node.next;
							node.next = prev;
							root = node;
							ListNode r = new ListNode(node.key,node.data);
							calculateData();
							return r;
							
						} else {
							prev.next = node.next;
							node.next = prev;
							prev2.next = node;
							ListNode r = new ListNode(node.key,node.data);
							calculateData();
							return r;
						}
						
					}
				}
			} else {
				prev2 = prev;
				prev = node;
				node = node.next;
			}
		}
    	return null;
    }	
    
}