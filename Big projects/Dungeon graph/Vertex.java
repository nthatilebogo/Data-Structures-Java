/**
 * Name: Onthatile
 * Student Number: u20589507
 */

public class Vertex {
   
    public Coordinates coords;
    
    public Vertex [] adjacenciesList;
    
    public char tile;
    public double distance;
    public boolean visited;
    public Vertex predecessor;
    
    public Vertex() {
        // TODO: Your code here...
    	this.visited = false;
    	this.distance = Double.POSITIVE_INFINITY;
    	this.adjacenciesList = new Vertex[0];
    }
    public void addAdj(Vertex v) {
    	//create new array

    	Vertex [] Arr;
    	int count;
    	if (this.adjacenciesList == null) {
			Arr = new Vertex[1];
			count = 0;
		} else {
			Arr = new Vertex[this.adjacenciesList.length+1];
			count = this.adjacenciesList.length;
			for (int i = 0; i < this.adjacenciesList.length; i++) {
				Arr[i] = this.adjacenciesList[i];
			}
		}
  
        // Assigning new array to original array
        // created above
        this.adjacenciesList = Arr;
        this.adjacenciesList[count] = v;
    }
    public Vertex retAdj(int i) {
    	return this.adjacenciesList[i];
    }
}
