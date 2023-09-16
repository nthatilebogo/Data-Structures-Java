/* You must complete this class such that objects of this class can be used as nodes in the min-max d-heap.
Read the comments to determine what each aspect of the class is supposed to do.
You may add any additional features (methods, variables) which may aid you in your
task, BUT you are not allowed to remove or change the names or properties of any of the features you were given.

Importing Java's built in data structures will result in a mark of 0.
*/
@SuppressWarnings("unchecked")
public class Node<T>
{
	public Node(T data_, int key_)
	{
		/* You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking. */ 
		this.data = data_;
		this.key = key_;
	}		
	
	public T getData() 
	{
		/* This method returns the data stored in the node */
		return data;
	}
	
	public String toString()
	{
		/* This method returns the String representation of the data stored in the node */
		return data.toString();
	}

	/* Add more methods if necessary */
	public int getKey()
	{
		return key;
	}
	/* Node attributes: */
	protected T data;  // Data stored in the node
	protected int key; // Key, i.e. priority of the node.
	
	/* Add more variables if necessary */
	protected Node<T> next;
	protected int index;
	
}
