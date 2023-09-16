import java.util.Random;

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}

	SkipList()
	{
		this(4);
	}

	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}

	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty()
	{
		//Your code goes here
		if (root[0] ==  null) {
			return true;
		}
		return false;
	}

	public void insert(T key)
	{
		//Your code goes here
		//System.out.println("inserting " + key);
		
		//new node
		int levels = chooseLevel()+1;
		//System.out.println("levels: " + levels);
		SkipListNode<T> nNode = new SkipListNode<T>(key,levels);
		if (isEmpty()) {
			for (int i = 0; i < levels; i++) {
				root[i] = nNode; 
			}
		}  
		
		if (search(key) == key) {
			return ;
		} 
		//variables
		boolean found = false;
		int countlvl = this.maxLevel;
		SkipListNode<T> cur = null;
		int j = countlvl-1;
		while (found == false) {
			if (root[j] != null) {
				//System.out.println("j:" + j);
				cur = root[j];
				found = true;	
			}
			j--;
		}
		SkipListNode<T> prev = null;
		countlvl = cur.next.length -1;
		//System.out.println("countlvl: " + countlvl);
		found = false;
		
		while (!found) {		// while correct pred not found
			
			if (cur.key.compareTo(key) < 0){	//cur<target
				//System.out.println("cur<target = " + cur.key +"<"+ key );
				//System.out.println("countlvl: " + countlvl);
				if (cur.next[countlvl] != null) { 
					prev = cur;
					cur = cur.next[countlvl];
					//System.out.println("prev: " + prev.key);
					//System.out.println("cur: " + cur.key);
				} else {	// cur next is null
					if (prev != null) { 	// cur is not first element
						if (countlvl > 0) {
							countlvl--;
							cur = prev;		// go to previous level
						} else {		// found curr and prev
							
							found = true; 
						}
					} else {
						
						if (countlvl > 0) {
							countlvl--;
						} else {	// found curr
							prev = cur;
							found = true;
						}
					}//else
				} //else
			} else if (cur.key.compareTo(key) > 0){	//cur>target
				//System.out.println("cur>target = " + cur.key +">"+ key );
				//System.out.println("countlvl: " + countlvl);
				if (prev != null) { 	// cur is not first element
					if (countlvl > 0) {
						countlvl--;
						cur = prev;		// go to previous level
					} else {		//found cur and pre
						//System.out.println("found cur and pre");
						found = true; 	//does not exist
					}
				} else {		
					if (countlvl == 0) {
						found =true;
					} else if (countlvl>0){
						//start from root again
						countlvl--;
						cur = root[countlvl];
					}//elseif
				}//else
				
			}//else if
		}//while loop
		
		//insert
		if(prev == null) {		//no predecessor
			//System.out.println("h");
			for (int i = 0; i < levels; i++) {
				nNode.next[i] = root[i];
				root[i] = nNode; 
			}
		} else if ((cur.next[0] == null) && (cur.key.compareTo(key) < 0)) {	//no successor
			for (int i = 0; i < levels; i++) { //for each level
				SkipListNode<T> node = root[i];
				if (node == null) {  //root points to null
					root[i] = nNode;
				} else {
					found = false;
					while(!found) {
						if (node.next[i] == null) { // found last el of i level
							node.next[i] = nNode;
							found = true;
						} else {
							node = node.next[i];
						}//else
					}// while
				}//else	
			}//for
		} else {
			for (int i = 0; i < levels; i++) { //for each level
				SkipListNode<T> node = root[i];
				
				if ((prev.next.length-1) >= i) {
					//System.out.println((prev.next.length-1) + ">=" + i);
					if (prev.next[i] != null) {
						nNode.next[i] = prev.next[i];
						prev.next[i] = nNode;
					}
				} else {
					prev = null;
					if (node == null) {  //root points to null
						root[i] = nNode;
					} else {
						//System.out.println("h");
						boolean inserted = false;
						while(!inserted) {
							//System.out.println("i: " + i);
							//System.out.println("node: " + node.key);
							
							
							if ((node.key.compareTo(key) > 0) && (prev == null)) { //current node is successor from the root
								root[i] = nNode;
								nNode.next[i] = node;
								inserted = true;
							} else if ((node.key.compareTo(key) > 0) && (prev != null)){ //current node is successor from prev node
								prev = nNode;
								nNode.next[i] = node;
								inserted = true;
							} else if ((node.key.compareTo(key) < 0)) {	//potential predecessor
								if (node.next[i] == null) {	//node is predecessor and points to null
									node.next[i] = nNode;
									inserted = true;
								} else {	//node is predecessor and points to another node(potential predecessor)
									prev = node;
									node = node.next[i];
								}
							}
							
							/*if (node.next[i] == null) { // found last el of i level
								node.next[i] = nNode;
							} else if (node.next[i].key.compareTo(key) > 0) {  //if next node is successor
								nNode.next[i] = node.next[i];
								node.next[i] = nNode;
							}*///else if
						}// while
					}//else
				}//else
				
			}//for
		}
		
	}	

	public T first()
	{
		//Your code goes here
		if (this.isEmpty()) {		// if it is empty
			return null;
		} else {
			return this.root[0].key;
		}
	}

	public T last()
	{
		//Your code goes here 
		if (this.isEmpty()) {		// if it is empty
			return null;
		} 
		//variables
		boolean found = false;
		//boolean found2 = false;
		int countlvl = this.maxLevel-1;
		SkipListNode<T> cur = root[countlvl];
		
		while (!found) {
			//cur = root[countlvl];
		
			if (cur == null) {	//if root at current level
				if (countlvl == 0) {
					found = true;
				}else if (countlvl > 0) {
					countlvl--;
					cur = root[countlvl];
				}
			} else {
				if (cur.next.length == 0) {
					if (countlvl == 0) {
						found = true;
					}else if (countlvl > 0) {
						countlvl--;
					}
				} else {
					if (cur.next[countlvl] != null) {
						cur = cur.next[countlvl];		// point to next node on countlvl
					} else {
						
						if (countlvl == 0) {
							found = true;
						}else if (countlvl > 0) {
							countlvl--;
						}
					}//else
				}
				
			}
		}
		return cur.key;
	}	

	public T search(T key)
	{
		//Your code goes here
		if (this.isEmpty()) {		// if it is empty
			return null;
		} 
		//variables
		boolean found = false;
		int countlvl = this.maxLevel;
		SkipListNode<T> cur = null;
		SkipListNode<T> prev = null;
		int j = countlvl-1;
		while (found == false) {
			if (root[j] != null) {
				cur = root[j];
				found = true;	
			}//if
			j--;
		}//while
		countlvl = cur.next.length -1;
		found = false;
		while (!found) {		// while target not found
			
			if (cur.key.compareTo(key) == 0) {	// target found
				return cur.key;
			} else if (cur.key.compareTo(key) < 0){	//cur<target
				
				if (cur.next[countlvl] != null) { 
					prev = cur;
					cur = cur.next[countlvl];
					
				} else {	// cur next is null
					if (prev != null) { 	// cur is not first element
						if (countlvl == 0) {
							return null;
						} else if (countlvl > 0) {
							countlvl--;
							cur = prev;		// go to previous level
						} 
					} else {
						if (countlvl > 0) {
							countlvl--;
						} else {
							return null; //does not exist
						}
					}//else
				} //else
			} else if (cur.key.compareTo(key) > 0){	//cur>target
				if (prev != null) { 	// cur is not first element
					if (countlvl > 0) {
						countlvl--;
						cur = prev;		// go to previous level
					} else {
						return null; 	//does not exist
					}
				} else {
					if (countlvl > 0) {
						countlvl--;
					} else {
						return null; //does not exist
					}
				}//else
			}// else if
		}//while loop
		return null;
	}
	
	public boolean delete(T key) {
		// your code goes here
		SkipListNode<T> dNode = search_node(key);
		if (dNode == null) {
			//System.out.println("cant find node");
			return false;
		}
		
		int countlvl = dNode.next.length;
		int count = dNode.next.length;
		
		for (int i = 0; i < countlvl; i++) { 	// for each level of dNode
			SkipListNode<T> node = root[i];
			//System.out.println("level: " + i);
			if (node == dNode) {	// dNode is first node, root points to it at level i
				//System.out.println("h");
				if (dNode.next[i] == null) { // deleted node points to null at level i
					root[i] = null;
					count--;
				} else { 	// deleted node points to a node at level i
					root[i] = dNode.next[i];
					dNode.next[i] = null;
					count--;
				}
			} else {	//go to next node
				while (!(node.next[i] == dNode)) {
					//System.out.println(node.key + " to " + node.next[i].key);
					node = node.next[i]; 
				}
				
				if (node.next[i] == dNode) {	// found prev node 
					//System.out.println("h");
					if (dNode.next[i] == null) { // deleted node points to null at level i
						node.next[i] = null;
						count--;
					} else { 	// deleted node points to a node at level i
						node.next[i] = dNode.next[i];
						dNode.next[i] = null;
						count--;
					}
				}//if
			}//else
			
		}//for
		
		//System.out.println("count: " + count);
		return count==0;
	}
	
	
	public String getPathToLastNode() {
		// your code goes here
		String str = "";
		SkipListNode<T> node = null;
		
		for (int i = (maxLevel-1); i >= 0; i--) {
			//System.out.println("level: " + i);
			if (node != null) {
				//System.out.println("h");
				while (!(node.next[i] == null)) {
					//System.out.println(node.key + " to " + node.next[i].key);
					str += "[" + node.next[i].key + "]";
					node = node.next[i]; 
				}//while				}
			}else if (root[i] != null) {	// initial stage, starting from the root
				//System.out.println("h2");
				node = root[i];
				str += "[" + node.key + "]";
				while (!(node.next[i] == null)) {
					//System.out.println(node.key + " to " + node.next[i].key);
					str += "[" + node.next[i].key + "]";
					node = node.next[i]; 
				}//while
			}
			
		}
		return str;
	}
	
	//helper functions
	
	public SkipListNode<T> search_node(T key)
	{
		//Your code goes here
		if (this.isEmpty()) {		// if it is empty
			return null;
		} 
		//variables
		boolean found = false;
		int countlvl = this.maxLevel;
		SkipListNode<T> cur = null;
		SkipListNode<T> prev = null;
		int j = countlvl-1;
		while (found == false) {
			if (root[j] != null) {
				cur = root[j];
				found = true;	
			}//if
			j--;
		}//while
		countlvl = cur.next.length -1;
		found = false;
		while (!found) {		// while target not found
			
			if (cur.key.compareTo(key) == 0) {	// target found
				return cur;
			} else if (cur.key.compareTo(key) < 0){	//cur<target
				
				if (cur.next[countlvl] != null) { 
					prev = cur;
					cur = cur.next[countlvl];
					
				} else {	// cur next is null
					if (prev != null) { 	// cur is not first element
						if (countlvl == 0) {
							return null;
						} else if (countlvl > 0) {
							countlvl--;
							cur = prev;		// go to previous level
						} 
					} else {
						if (countlvl > 0) {
							countlvl--;
						} else {
							return null; //does not exist
						}
					}//else
				} //else
			} else if (cur.key.compareTo(key) > 0){	//cur>target
				//System.out.println("cur>target = " + cur.key +">"+ key );
				//System.out.println("countlvl: " + countlvl);
				
				if (prev != null) { 	// cur is not first element
					if (countlvl > 0) {
						countlvl--;
						cur = prev;		// go to previous level
					} else {
						return null; 	//does not exist
					}
				} else {
					if (countlvl > 0) {
						countlvl--;
						cur = root[countlvl];
					} else {
						return null; //does not exist
					}
				}//else
				
			}// else if
		}//while loop
		return null;
	}
	
	
}