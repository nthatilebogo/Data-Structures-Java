/* Complete this class to implement a fully functional min-max d-heap. Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your task,
BUT you are not allowed to remove or change the names or properties of any of the features you were given.

Importing Java's built in data structures will result in a mark of 0.
*/
@SuppressWarnings("unchecked")
public class MinMaxDHeap<T>
{
	public MinMaxDHeap(int d)
	{
		/* Parameter d specifies the order of your min-max heap. If d = 2, you should construct a binary heap, 
		   if d = 3, you should construct a ternary heap, etc. You may implement this constructor to suit your 
		   needs, or you may add additional constructors. This is the constructor which will be used for marking. */ 
		this.d = d;
	}
	
	/* Insertion */
	public void insert(T data, int key)
	{
		/* Insert a Node object according to its key (priority).
			 The Node object has to be initialised with the given data/key values.
		   Refer to the assignment spec for insertion algorithm details. */
		Node<T> n = new Node<T>(data,key);
		if (root_arr == null) {
			root_arr = new Node<T>(data,key);
			root_arr.next = null;
			root_arr.index = 0;
			len++;
			return;
		}
		int k = len;
		
		arr(len-1).next = n;
		n.index = len;
		len++;
		
			if (n == root) {
				return;
			}
				if (max_lvl(k)) {	//determine if inserted in max level
					if (p(k).key < arr(k).key) {
						if (gp(k) != null) {
							while ((k > d) && (gp(k).key < arr(k).key)) {
								if (gp(k) != null) {
									k = swap(gp(k),arr(k));
								}
							}
						}
					} else {
						k = swap(p(k),arr(k));
						if (gp(k) != null) {
							while ((k > d) && (gp(k).key > arr(k).key)) {
								k = swap(gp(k),arr(k));
							}
						}
					}
				} else if (min_lvl(k)) {	//determine if inserted in min level
					if (p(k).key > arr(k).key) {
						if (gp(k) != null) {
							while ((k > d) && (gp(k).key > arr(k).key)) {
								k = swap(gp(k),arr(k));
							}
						}
					} else {
						k = swap(p(k),arr(k));
						if (gp(k) != null) {
							while ((k > d) && (gp(k).key < arr(k).key)) {
								k = swap(gp(k),arr(k));
							}
						}
					}
				}
		
	}
	

	/* Read-only access */
	public T peekMin()
	{
		/* Return the data of the min priority Node. Min-max heap should not be modified by this function. */
		if (root_arr == null) {
			return null;
		}
		Node<T> n = new Node<T>(null,1000); 
		for (int i = 0; i < len; i++) {
			if (arr(i).key <= n.key) {
				n = arr(i);
			}
		}
		return n.data;
		
	}
	
	public T peekMax()
	{
		/* Return the data of the max priority Node. Min-max heap should not be modified by this function. */
		if (root_arr == null) {
			return null;
		}
		Node<T> n = new Node<T>(null,0); 
		for (int i = 0; i < len; i++) {
			if (arr(i).key >= n.key) {
				n = arr(i);
			}
		}
		return n.data;
	}
	
	public String toString()
	{
		/* Return a breadth-first traversal representation of the Min-Max d-heap by constructing 
		   a comma-separated string of the data stored in the heap. To construct the string,
       iterate over the heap, and append each Node object by invoking the toString() method.
       NB: The output format should contain no spaces and/or new line characters. 
       Individual nodes must be comma-separated. Eg., if alphabetical characters A, B, and C 
       were stored in the min-max heap in this order, you should return the string "A,B,C" 
       */
		String s = "";
		if (root_arr != null) {
			s += arr(0).data.toString();
			for (int i = 1; i < len; i++) {
				if (arr(i) != null) {
					s += ",";
					s += arr(i).data.toString();
				}
			}
		}
		
		
		return s;
	}
	
	
	/* Deletion */
	public T deleteMin()
	{
		/* Remove the Node with the min priority, and return its data. 
			 Min-max heap has to be restructured accordingly: see spec for details. */
		T r = peekMin();
		Node<T> n = find(peekMin());
		if (n != null) {
			int k = replace(n, arr(len-1));
			len--;
			d_Min(k, arr(k));
			return r;
		}
	
		return null;
	}
	
	public T deleteMax()
	{
		/* Remove the Node with the max priority, and return its data. 
			 Min-max heap has to be restructured accordingly: see spec for details. */
		T r = peekMax();
		Node<T> n = find(peekMax());
		if (n != null) {
			int k = replace(n, arr(len-1));
			len--;
			d_Max(k, arr(k));
			return r;
		}
		
		return null;
	}
	
	/* Construction */
	public void construct(Node<T>[] arr)
	{
		/* Given an array of Node objects in arbitrary order, construct a min-max heap by 
       applying Floyd's algorithm modified for min-max d-heaps. */
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]  != null) {
				this.insert(arr[i].data, arr[i].key);
			}
		}
	}
	
	public void changeD(int newD)
	{
		/* Given a new order d, restructure the current min-max d-heap such that it is a d-heap with d = newD. */
		this.d = newD;
	}
		
	/* Clearing */
	public void clear()
	{
		/* Clear the min-max heap by removing all nodes. */
		root_arr = null;
		root = null;
	}
	
	private int d; // The d-order of the min-max d-heap
	//var
	Node<T> root_arr = null;
	Node<T> root = null;
	int len = 0;
	//methods
	int pow(int base, int exp) {
		if (exp == 0) {
			return 1;
		} else {
			return base * pow(base, --exp);
		}
	}
	boolean max_lvl(int k) {
		int tot = 0;
		int i = 0;
		k++;
		while(tot<k) {
			tot = tot + pow(d,i);
			if (k <= tot) {
				if (i % 2 == 0) {
					return false;
				} else {
					return true;
				}
			}
			i++;
		}
		return false;
	}
	boolean min_lvl(int k) {
		int tot = 0;
		int i = 0;
		k++;
		//System.out.println("max:" + k);
		while(tot<k) {
			tot = tot + pow(d,i);
			if (k <= tot) {
				if (i % 2 == 0) {
					return true;
				} else {
					return false;
				}
			}
			i++;
		}
		return false;
	}

	Node<T> gc(int k, int p1, int p2) {
		int i = (int) (d*k)+p1;
		int g = (int) (d*i)+p2;
 		return arr(g); 
	}
	Node<T> c(int k, int p) {
		int i = (int) (d*k)+ p;
		
		return arr(i); 
	}
	Node<T> p(int k) {
		int i = (int) (k-1)/d;
		
		return arr(i); 
	}
	Node<T> gp(int k) {
		int i = (int) (k-1)/d;
		int g = (int) (i-1)/d;
		if (i==0) {
			return null;
		}
		return arr(g); 
	}

	
	
	int swap(Node<T> n,Node<T> n2) {
		Node<T> t_next = null;
		Node<T> t2_next = null;
		int t = 0;
		int i = 0;
		int i2 = 0;
		Node<T> n_prev = null;
		Node<T> n2_prev = null;
				
		while (n != arr(i)) {
			if (n != arr(i)) {
				i++;
			}
		}
		
		while (n2 != arr(i2)) {
			if (n2 != arr(i2)) {
				i2++;
			}
		}
		
		if ((i-1) >= 0) {
			if (arr(i-1) !=  n2) {
				n_prev = arr(i-1);
			}
		}
		if ((i2-1) >= 0) {
			if (arr(i2-1) != n) {
				n2_prev = arr(i2-1);
			}
		}
		
		
		if (n2.next != null) {
			if (n2.next != n) {
				t_next = n2.next;
			}
		}
		if (n.next != null) {
			if (n.next != n2) {
				t2_next = n.next;
			}
		}
		t = n2.index;
		
		if (n_prev != null) {
			n_prev.next = n2;
			
		}
		if (n2_prev != null) {
			n2_prev.next = n;	
		}
		
		if (t_next == null) {
			n.next = n2;
		} else {
			n.next = t_next;
		}
		if (t2_next == null) {
			n2.next = n;
		} else {
			n2.next = t2_next;
		}
		
		
		n2.index = n.index;
		n.index = t;
		if (i == 0) {
			root_arr = n2;
		} else if (i2 == 0) {
			root_arr = n;
		}
		return i;
	}

	int replace(Node<T> n,Node<T> n2) {
		Node<T> t_next = null;
		int i = 0;
		int i2 = 0;
		Node<T> n_prev = null;
		Node<T> n2_prev = null;
				
		while (n != arr(i)) {
			if (n != arr(i)) {
				i++;
			}
		}
		
		while (n2 != arr(i2)) {
			if (n2 != arr(i2)) {
				i2++;
			}
		}
		
		if ((i-1) >= 0) {
			n_prev = arr(i-1);
		}
		if ((i2-1) >= 0) {
			n2_prev = arr(i2-1);
		}
		
		if (n2.next != null) {
			t_next = n2.next;
		}
		
		
		if (n.next != null) {
			n2.next = n.next;
		} else {
			n2.next = null;
		}
		if ((i-1) >= 0) {
			n_prev.next = n2;
		}
		
		
		if (t_next == null) {
			if (n2_prev != null) {
				n2_prev.next = null;
			} 
		} else {
			if (n2_prev != null) {
				n2_prev.next = t_next;
			}	
		}
		n.next = null;
		
		n2.index = n.index;
		
		if (i == 0) {
			
			if (n.key == n2.key) {
				root_arr = null;
			} else {
				root_arr = n2;
			} 	
		}
		return i;
	}
	
	
	Node<T> arr(int i) {
		Node<T> n = null;
		if (i >= len) {
			return n;
		}
		n = root_arr;
		if (i == 0) {
			return n;
		}
		while (n.index != i) {
			n = n.next;
		}
		return n;
	}

	Node<T> find(T k) {
		Node<T> n = null;
		n = root_arr;
		if (n != null) {
			while (!(n.data.equals(k))) {
				if (n.data != k) {
					n = n.next;
				}
			}
		}
		return n;
	}

	void d_Min(int k, Node<T> n) {
		boolean b_Else = true;
		boolean bN = false;
		
			Node<T> sml = null;
			for (int i = 1; i <= d; i++) {
				if (c(k,i) != null) {
					if (sml == null) {
						sml = c(k,i); 
					} else {
						if (c(k,i).key < sml.key) {
							sml = c(k,i); 
						}
					}
				}	
			}
			if (sml != null) {
				if (sml.key < n.key) {
					k = swap(sml,n);
					b_Else = false;
					bN = true;
				}
			} else {
				return;
			}
				
			
			if (b_Else) {
				sml = null;
				for (int i = 1; i <= d; i++) {
					for (int j = 1; j <= d; j++) {
						if (gc(k,i,j) != null) {
							if (sml == null) {
								sml = gc(k,i,j); 
							} else {
								if (gc(k,i,j).key < sml.key) {
									sml = gc(k,i,j); 
								}
							}
						}	
					}
				}
				if (sml != null) {
					if (sml.key < n.key) {
						k = swap(sml,n);
						b_Else = false;
						bN = true;
					}
				} else {
					return;
				}
			}
			
			if ((c(k,1) != null) && (bN)) {
				d_Min(k, arr(k));
			} else {
				return;
			}
	}

	void d_Max(int k, Node<T> n) {
		boolean b_Else = true;
		boolean bN = false;
		Node<T> lrg = null;
		for (int i = 1; i <= d; i++) {
			if (c(k,i) != null) {
				if (lrg == null) {
					lrg = c(k,i); 
				} else {
					if (c(k,i).key > lrg.key) {
						lrg = c(k,i); 
					}
				}
			}	
		}
		if (lrg != null) {
			if (lrg.key > n.key) {
				k = swap(lrg,n);
				b_Else = false;
				bN = true;
			}
		} else {
			return;
		}
			
		
		if (b_Else) {
			lrg = null;
			for (int i = 1; i <= d; i++) {
				for (int j = 1; j <= d; j++) {
					if (gc(k,i,j) != null) {
						if (lrg == null) {
							lrg = gc(k,i,j); 
						} else {
							if (gc(k,i,j).key > lrg.key) {
								lrg = gc(k,i,j); 
							}
						}
					}	
				}
			}
			if (lrg != null) {
				if (lrg.key > n.key) {
					k = swap(lrg,n);
					b_Else = false;
					bN = true;
				}
			} else {
				return;
			}
		}
		
		if ((c(k,1) != null) && (bN)) {
			d_Max(k, arr(k));
		} else {
			return;
		}
	}
}
