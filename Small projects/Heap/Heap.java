@SuppressWarnings("unchecked")
public abstract class Heap<T extends Comparable<T>> {

    int capacity;
    Comparable<T> mH[];
    int currentSize;

    public Heap(int capacity) {
        this.capacity = capacity;
        mH = new Comparable[capacity+1]; //Use index positions 1 to capacity 
        currentSize = 0;
    }

    protected int getCapacity(){
        return capacity;
    }

    protected int getCurrentSize(){
        return currentSize;
    }

    public void display() {
        for(int i = 1; i <= currentSize; i++) {
            System.out.print(mH[i] + " ");
        }
        System.out.println("");
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    protected boolean isEmpty() {

        //Your code goes here
    	if (mH[1] == null) {
			return true;
		} else {
			return false;
		}
    }

    public abstract void insert(T elem);


    //Helper functions
    
    //return element of child
    public Comparable<T> Lchild_el(int i) {
    	int pos = 2*i;
    	if (pos < this.capacity) {
    		return mH[pos];
		}
    	return null;
    }
    //return element of child
    public Comparable<T> Rchild_el(int i) {
    	int pos = (2*i) + 1;
    	if (pos < this.capacity) {
    		return mH[pos];
		}
    	return null;
    }
    //return element of parent
    public Comparable<T> parent_el(int i) {
    	int pos = i/2;
    	if (i>1) {	//there's a parent
    		return mH[pos];
		}
    	return null;
    }
    
    //return index of child
    public int Lchild_i(int i) {
    	int pos = (2*i);
    	if (pos < this.capacity) {
    		return pos;
		}
    	return 0;
    }
    //return index of child
    public int Rchild_i(int i) {
    	int pos = (2*i) + 1;
    	if (pos < this.capacity) {
    		return pos;
		}
    	return 0;
    }
    //return index of parent
    public int parent_i(int i) {
    	int pos = i/2;
    	if (i>1) {	//there's a parent
    		return pos;
		}
    	return 0;
    }
    
    //insert element into heap
    public boolean ins(Comparable<T> el) {
    	boolean inserted = false; 
    	for (int i = 1; i < mH.length; i++) {
			if (mH[i] == null) {
				mH[i] = el;
				inserted = true;
				addCurrentSize();
				return inserted;
			}
		}
    	return inserted;
    } 
    
    //add currentSize
    protected void addCurrentSize(){
        currentSize++;
    }
    //subtract currentSize
    protected void subCurrentSize(){
        currentSize--;
    }
    
    //swap elements 
    public boolean swap(int i1, int i2) {
    	boolean swapped = false;
    	Comparable<T> temp = null;
    	temp = mH[i1];
    	mH[i1] = mH[i2];
    	mH[i2] = temp;
    	swapped = true;
    	return swapped;
    }
    
    //remove min element
    public Comparable<T> replace(int i1, int i2) {
    	Comparable<T> temp = null;
    	temp = mH[i1];
    	mH[i1] = mH[i2];
    	mH[i2] = null;
    	//update current size
    	subCurrentSize();
    	
    	return temp;
    }

}