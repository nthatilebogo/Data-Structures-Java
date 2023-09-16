public class Trie {	
	protected char[] letters;
	protected Node root = null;
	private int numPtrs;

	public Trie(char[] letters) {
		this.letters = letters;
		this.numPtrs = letters.length + 1;
	}


	//Provided Helper functions
	
	private int index(char c) {
		for (int k = 0; k < letters.length; k++) {
			if (letters[k] == (c)) {
				return k+1;
			}
		}
		return -1;
	}
	
	private char character(int i) {
		if (i == 0) {
			return '#';
		} else {
			return letters[i-1];
		}
	}
	
	private String nodeToString(Node node, boolean debug) {
		if (node.isLeaf) {
			return node.key;
		}
		else {
			String res = "";
			for (int k = 0; k < node.ptrs.length; k++) {
				if (node.ptrs[k] != null) {
					res += " (" + character(k) + ",1) ";
				} else if (debug) {
					res += " (" + character(k) + ",0) ";
				}
			}
			return res;
		}
	}

	public void print(boolean debug) {
		Queue queue = new Queue();
		Node n = root;
		if (n != null) {
			n.level = 1;
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				System.out.print("Level " + n.level + " [");
				System.out.print(nodeToString(n, debug));
				System.out.println("]");
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null) {
						n.ptrs[k].level = n.level+1;
						queue.enq(n.ptrs[k]);
					}
				}
			}
		}
	}


	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	
	// Function to insert the given key into the trie at the correct position.
	public void insert(String key) {

		// Your code goes here
		//Case 1
		if (root == null) {
			root = new Node(numPtrs);
			char first_let = key.charAt(0);
			int i = this.index(first_let);
			root.ptrs[i] = new Node(key,numPtrs);
		} else {
			ins(root, key, 0);
		}
		
	}

	// Function to determine if a node with the given key exists.
	public boolean contains(String key) {

		// Your code goes here
		int i=0;
		return this.con(root,key,i);
	}
	

	// Function to print all the keys in the trie in alphabetical order.
	public void printKeyList() {

		// Your code goes here
		
		Node n = root;
		ptr(n);
		System.out.println();
	}

	
	//Helper functions
	protected void end(Node n, int i) {
		//System.out.println("endword=" + (n.key.length()-1));
		//System.out.println("i="+i);
		if (i == (n.key.length()-1)) {
			n.endOfWord = true;
		}
	}
	protected void lvl(Node n, Node n2) {
		n2.level = n.level+1;
	}
	private void ins(Node n, String key,int i) {
		
		char ch = key.charAt(i);
		//System.out.println("key=" + key);
		//System.out.println("ch=" + ch);
		int c = this.index(ch);
		
		if (n.ptrs[c] == null) {
			Node n_new = new Node(key,numPtrs);
			n.ptrs[c] = n_new;
			//update new node
			//endword
			end(n_new, i);
			//level
			lvl(n, n_new);
			return;
		} else if (n.ptrs[c].isLeaf) {
			Node n_new = null;
			Node n2 = n.ptrs[c];
			String k = n2.key;
			Node node_key= new Node(key, numPtrs);
			//endword
			end(node_key, i);
			char ch3;
			int c3;
			if (n2.endOfWord) {
				n.ptrs[c] = new Node(numPtrs);
				n_new = n.ptrs[c];
				
				ch = key.charAt(i);
				c = this.index(ch);
				
				n_new.ptrs[c] = node_key;
				n_new.ptrs[0] = n2;
				
				//update new node
				//endword
				end(n_new.ptrs[c], i);
				//level
				lvl(n, n_new);
				
			} else if (node_key.endOfWord) {
				n.ptrs[c] = new Node(numPtrs);
				n_new = n.ptrs[c];
				
				ch = k.charAt(i);
				c = this.index(ch);
				
				n_new.ptrs[c] = n2;
				n_new.ptrs[0] = node_key;
				
				//update new node
				//endword
				end(n_new.ptrs[c], i);
				//level
				lvl(n, n_new);
			} else {
				// check smallest word
				int sml = k.length();
				if (sml > key.length()) {
					sml = key.length();
				}
				boolean end = false;
				while ((sml > i) && (k.charAt(i) == key.charAt(i))) {
					ch3 = key.charAt(i);
					c3 = this.index(ch3);
					n.ptrs[c3] = new Node(numPtrs);
					n_new = n.ptrs[c3];
					//update new node
					//endword
					end(n2, i);
					end(node_key, i);
					if (n2.endOfWord) {
						ch3 = key.charAt(i+1);
						c3 = this.index(ch3);
						n_new.ptrs[c3] = node_key;
						n_new.ptrs[0] = n2;
						//update new node
						//endword
						end(node_key, i+1);
						end = true;
					} else if (node_key.endOfWord) {
						ch3 = k.charAt(i+1);
						c3 = this.index(ch3);
						n_new.ptrs[c3] = n2;
						n_new.ptrs[0] = node_key;
						//update new node
						//endword
						end(n2, i+1);
						end = true;
					}
					
					//level
					lvl(n, n_new);
					n = n_new;
					i++;
				}
				if (!end) {
					ch = key.charAt(i);
					char ch2 = k.charAt(i);
					c = this.index(ch);
					int c2 = this.index(ch2);
					
					n_new.ptrs[c] = node_key;
					n_new.ptrs[c2] = new Node(k, numPtrs);
					
					//update new node
					//endword
					end(n_new.ptrs[c], i);
					end(n_new.ptrs[c2], i);
				}
				
			}
			
			return;
		} else {
			ins(n.ptrs[c], key, ++i);
		}
		
	}

	private boolean con(Node n , String key, int i) {
		char ch = key.charAt(i);
		int c = this.index(ch);
		
		if (c == -1) {
			return false;
		}
		
		if (n.ptrs[c] != null) {
			if (!(n.ptrs[c].isLeaf)) {
				if (key.length() == (i+1)) {
					if (n.ptrs[c].ptrs[0] != null) {
						if (n.ptrs[c].ptrs[0].key  == key) {
							return true;
						}
					}
				} else {
					return con(n.ptrs[c],key,++i);
				} 
			} else {
				if (key == n.ptrs[c].key) {
					return true;
				}
			}
		} else {
			return false;
		} 
		return false;
	}

	private void ptr(Node n) {
		if (n.isLeaf) {		// check if node is leaf
			System.out.print(n);
			System.out.print(" ");
		} else {			// node is not leaf
			for (int i = 0; i < n.ptrs.length; i++) {
				if (n.ptrs[i] != null) {	//check if ptr points to node
					ptr(n.ptrs[i]);
				}
			}
		}
		return;
	}
	
}