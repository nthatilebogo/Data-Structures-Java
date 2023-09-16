/**
 * Name: Onthatile
 * Student Number: u20589507
 */

public class MoveToFrontOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and move the accessed node
     * to the front, then recalculate all data fields.
     * @return The node with its data value before it has been moved to the front,
     * otherwise 'null' if the key does not exist.
     */
    @Override
    public ListNode searchNode(Integer key) {

        //Your code goes here
    	ListNode node = root;
    	ListNode prev = null;
    	if (node.equals(null)) {
    		return null;
    	}
    	while (node != null) {
			if (node.key.equals(key)) {
				if (prev == null) {
					return node;
				} else {
					if (node.next == null) {
						prev.next = null;
						node.next = root;
						root = node;
						ListNode r = new ListNode(node.key,node.data);
						calculateData();
						return r;
					} else {
						prev.next = node.next;
						node.next = root;
						root = node;
						ListNode r = new ListNode(node.key,node.data);
						calculateData();
						return r;
					}
				}
			} else {
				prev = node;
				node = node.next;
			}
		}
    	return null;
    }


}