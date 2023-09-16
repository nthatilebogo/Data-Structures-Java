import java.util.Arrays;
@SuppressWarnings("unchecked")
public class Sort {

	////// Implement the functions below this line //////

	/********** HEAP **********/
	public static <T extends Comparable<? super T>> void heapsort(T[] data, boolean debug) {
		// Your code here
		//find last non leaf element
		int num = (int) (data.length/2)-1;
		int last =0;
		for (int i = num; i >= 0; i--) {
			movedown(data, i, 0, debug);
		}
		//phase 2
		for (int i = data.length-1; i >= 1; --i) {
			//swap elements
			T t = data[i];
			data[i] = data[0];
			data[0] = t;
			//movedown
			movedown(data, 0, i, debug);
		}
		
	}

	private static <T extends Comparable<? super T>> void movedown(T[] data, int first, int last, boolean debug) {
		// Your code here
		boolean f = true; 
		int c1;
		int c2;
		int sml;
		int big;
		if (last == 0) {
			while (f) {
				c1 = (int) (first*2)+1;
				c2 = (int) (first*2)+2;
				
				//check if child exist and choose biggest child
				sml = first;
				if (data.length>c1) {
					if (data[first].compareTo(data[c1]) < 0) {		//first child is bigger than parent
						sml = c1;
					}
					if (data.length>c2) {
						if (data[sml].compareTo(data[c2]) < 0) {	//second child is bigger than parent
							sml = c2;
						}
					}
					//swap elements
					if (sml != first) {
						T t = data[first];
						data[first] = data[sml];
						data[sml] = t;
						//recursion
						first = sml;
					} else {
						f = false;
					}
					
				} else {
					f = false;
				}
			}
		} else {
			while (f) {
				c1 = (int) (first*2)+1;
				c2 = (int) (first*2)+2;
				
				//check if child exist and choose biggest child
				big = first;
				if (last>c1) {
					if (data[first].compareTo(data[c1]) < 0) {		//first child is bigger than parent
						big = c1;
					}
					if (last>c2) {
						if (data[big].compareTo(data[c2]) < 0) {	//second child is bigger than parent
							big = c2;
						}
					}
					//swap elements
					if (big != first) {
						T t = data[first];
						data[first] = data[big];
						data[big] = t;
						//recursion
						first = big;
					} else {
						f = false;
					}
					
				} else {
					f = false;
				}
			}
		}
		
		
		
		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug) {
		// Your code here
		if (((last-first)+1) >= 2) {
			//
			int mid = (first+last)/2;
			int it3 = mid+1;
			mergesort(data, first, mid, debug);
			mergesort(data, it3, last, debug);
			merge(data, first, last, debug);
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug) {
		// Your code here
		
		int mid = (first+last)/2;
		int it1 = first;
		int it2 = first;
		int it3 = mid+1;
		T[] temp = (T[]) new Comparable[data.length];
		while (it1<=last) {
			if ((it3 <= last) && (it2 <= mid)) {
				if (data[it2].compareTo(data[it3]) < 0) {
					temp[it1++] = data[it2++];
				} else {
					temp[it1++] = data[it3++];
				}
			} else if (it3 <= last) {
				temp[it1++] = data[it3++];
			} else {
				temp[it1++] = data[it2++];
			}
		}
		//overwrite temp into data
		for (int i = first; i <= last; i++) {
			data[i] = temp[i];
		}
		
		
		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}