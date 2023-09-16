

@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    @Override
    public void insert(T elem) {

        //Your code goes here
    	//insert el
    	if (ins(elem)) {
    		// element is inserted so maintain min heap property
    		//System.out.println("inserted " + elem);
    		if (this.getCapacity() > 1) {	//check if its not root
				boolean maintained = false;
				int i = currentSize;
				int temp = 0;
				while (!maintained) {
					if (i == 1) {
						//System.out.println("i == 1");
						maintained = true;
					} else if (parent_el(i).compareTo((T)mH[i]) > 0) { //parent is bigger
						//swap
						temp = parent_i(i);
						//System.out.println("swap: " + parent_el(i) + " with " + mH[i]);
						swap(parent_i(i), i);
						//update i
						i = temp;
					} else {	// parent is smaller
						//System.out.println(parent_el(i) + " < " + mH[i]);
						maintained = true;
					}
				}//while
			}//if (this.getCapacity() > 1)
		}
    }

    public T removeMin() {

        //Your code goes here
    	Comparable<T> temp = null; 
    	if (capacity <= 1) {
			if (capacity == 1) {
				temp = mH[1];
				mH[1] = null;
				subCurrentSize();
				return null;
			} else {
				return null;
			} 
		}
    	
    	
    	//replace root with last el
    	temp = replace(1, currentSize);
    	
    	if (capacity == 1) {	//no need to maintain heap property 
			return (T) temp;
		}
    	
    	boolean maintained = false;
    	int i = 1;
    	int temp_i = 0;
    	while (!maintained) {
			//check if both left child and right child exist
    		if ((Lchild_el(i) != null) && (Rchild_el(i) != null)) {	//both exist
    			//System.out.println(Lchild_el(i) +" and " + Rchild_el(i) + " both exist.");
				//check if both children are equal to each other
    			if (Lchild_el(i).equals(Rchild_el(i))) { //L==R
    				//System.out.println(Lchild_el(i) +" = " + Rchild_el(i) );
					//check if L<C
    				if (Lchild_el(i).compareTo((T)mH[i]) < 0) {	//L<C
    					//swap with left child	
    					temp_i = Lchild_i(i);
    					//System.out.println(Lchild_el(i) + " < " + mH[i]);
						//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
						swap(Lchild_i(i), i);
					} else {	//C<L
						//maintained
						maintained = true;
					}//else 
    				
				} else {
					//find smallest child
					String smallest = "";
					
					if (Lchild_el(i).compareTo((T)Rchild_el(i)) < 0) { //L<R
						smallest = "L";
					}else {
						smallest = "R";
					}
					
					if (smallest == "L") {
						//compare left child with el
						if (Lchild_el(i).compareTo((T)mH[i]) < 0) {	//L<C
							temp_i = Lchild_i(i);
							//System.out.println(Lchild_el(i) + " < " + mH[i]);
							//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
							swap(Lchild_i(i), i);
						} else {	//C<L
							//maintained
							maintained = true;
						}//else 
					} else if (smallest == "R") {
						//compare left child with el
						if (Rchild_el(i).compareTo((T)mH[i]) < 0) {	//R<C
							temp_i = Rchild_i(i);
							//System.out.println(Rchild_el(i) + " < " + mH[i]);
							//System.out.println("swap: " + Rchild_el(i) + " with " + mH[i]);
							swap(Rchild_i(i), i);
						} else {	//C<R
							//maintained
							maintained = true;
						}//else 
					} else {
						//System.out.println("something went wrong");
					}//else
				}//else
			} else if (Lchild_el(i) != null) {		//only left child exist
				//check if L<C
				if (Lchild_el(i).compareTo((T)mH[i]) < 0) {	//L<C
					//swap with left child	
					temp_i = Lchild_i(i);
					//System.out.println(Lchild_el(i) + " < " + mH[i]);
					//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
					swap(Lchild_i(i), i);
				} else {	//C<L
					//maintained
					maintained = true;
				}//else 
			} else {		// both dont exist do nothing
				return (T)temp;
			}//else
    		
    		i = temp_i;
		}//while
    	return (T) temp;
    }

    public void delete(T elem) {

	//Your code goes here
    	//find index
    	int index = 0;
    	for (int i = 1; i < currentSize; i++) {
			if (mH[i].equals(elem)) {	//found elem
				index = i;
			}
		}
    	
    	if (index > 0) {	// can delete now
    		
        	if (capacity <= 1) {
    			if (capacity == 1) {
    				mH[1] = null;
    				subCurrentSize();
    				return;
    			} else {
    				return;
    			} 
    		}
        	
        	
        	//replace root with last el
        	replace(index, currentSize);
        	
        	if (capacity == 1) {	//no need to maintain heap property 
    			return;
    		}
        	
        	//maintain heap property
        	boolean maintained = false;
        	int temp_i = 0;
        	while (!maintained) {
    			//check if both left child and right child exist
        		if ((Lchild_el(index) != null) && (Rchild_el(index) != null)) {	//both exist
        			//System.out.println(Lchild_el(i) +" and " + Rchild_el(i) + " both exist.");
    				//check if both children are equal to each other
        			if (Lchild_el(index).equals(Rchild_el(index))) { //L==R
        				//System.out.println(Lchild_el(i) +" = " + Rchild_el(i) );
    					//check if L<C
        				if (Lchild_el(index).compareTo((T)mH[index]) < 0) {	//L<C
        					//swap with left child	
        					temp_i = Lchild_i(index);
        					//System.out.println(Lchild_el(i) + " < " + mH[i]);
    						//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
    						swap(Lchild_i(index), index);
    					} else {	//C<L
    						//maintained
    						maintained = true;
    					}//else 
        				
    				} else {
    					//find smallest child
    					String smallest = "";
    					
    					if (Lchild_el(index).compareTo((T)Rchild_el(index)) < 0) { //L<R
    						smallest = "L";
    					}else {
    						smallest = "R";
    					}
    					
    					if (smallest == "L") {
    						//compare left child with el
    						if (Lchild_el(index).compareTo((T)mH[index]) < 0) {	//L<C
    							temp_i = Lchild_i(index);
    							//System.out.println(Lchild_el(i) + " < " + mH[i]);
    							//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
    							swap(Lchild_i(index), index);
    						} else {	//C<L
    							//maintained
    							maintained = true;
    						}//else 
    					} else if (smallest == "R") {
    						//compare left child with el
    						if (Rchild_el(index).compareTo((T)mH[index]) < 0) {	//R<C
    							temp_i = Rchild_i(index);
    							//System.out.println(Rchild_el(i) + " < " + mH[i]);
    							//System.out.println("swap: " + Rchild_el(i) + " with " + mH[i]);
    							swap(Rchild_i(index), index);
    						} else {	//C<R
    							//maintained
    							maintained = true;
    						}//else 
    					} else {
    						//System.out.println("something went wrong");
    					}//else
    				}//else
    			} else if (Lchild_el(index) != null) {		//only left child exist
    				//check if L<C
    				if (Lchild_el(index).compareTo((T)mH[index]) < 0) {	//L<C
    					//swap with left child	
    					temp_i = Lchild_i(index);
    					//System.out.println(Lchild_el(i) + " < " + mH[i]);
    					//System.out.println("swap: " + Lchild_el(i) + " with " + mH[i]);
    					swap(Lchild_i(index), index);
    				} else {	//C<L
    					//maintained
    					maintained = true;
    				}//else 
    			} else {		// both dont exist do nothing
    				return;
    			}//else
        		
        		index = temp_i;
    		}//while
		} 
		return;
		
    }


    //Helper functions


}