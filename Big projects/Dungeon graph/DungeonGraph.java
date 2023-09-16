/**
 * Name: Onthatile
 * Student Number: u20589507
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
@SuppressWarnings("unchecked")

/**
 * You may add your own classes and function, but you may not modify any of
 * the given attribute names or given method signatures.
 */
public class DungeonGraph {

    /** 
     * =============================
     * ===        TASK 1         ===
     * =============================
     */
	int maxR;
	int maxC;
	char [][] dungeon;
	Vertex [][] V_dungeon;
	Vertex [] verticesList;

    public DungeonGraph() {
        // TODO: Your code here...
    	this.verticesList = new Vertex[0];
    }

    /**
     * Create a new graph to represent the given dungeon.
     */
    public void createGraph(String filename) {
        // TODO: Your code here...
    	//count rows and columns
    	this.dungeon = null;
    	this.V_dungeon = null;
    	this.verticesList = null;
    	this.verticesList = new Vertex[0];
    	
    	int c = 0;
    	int r = 0;
    	//read file
    	File file = new File(filename);
    	  
    	BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			try {
				while ((st = br.readLine()) != null) {
					if (st.length() > 0) {
						r++;
						if (st.length() != c) {
							c = st.length();
						}
					}
				}
				
				//initalize maxRow&Column
				maxR = r;
				maxC = c;
				//System.out.println("maxR: " + maxR);
				//System.out.println("maxC: " + maxC);
				this.dungeon = new char[r][c];
				this.V_dungeon = new Vertex[r][c];
				//store in 2d array
				br = new BufferedReader(new FileReader(file));
				int row = 0;
				while ((st = br.readLine()) != null) {	
					
					if (st.length() > 0) {
						if (st.length() != c) {
							c = st.length();
						}
						for (int i = 0; i < st.length(); i++) {		//columns
							// store chars in 2D array
							//System.out.print(st.charAt(i));
							
							this.dungeon[row][i] = st.charAt(i);
							
							//if (this.dungeon[row][i] != '#') {
								this.addVert(this.dungeon[row][i], row, i);
							//}
						}
						//System.out.println();
						row++;
					}
				}
				
				//add vertices
				for (int i = 0; i < maxR; i++) {
					for (int j = 0; j < maxC; j++) {
						if (this.V_dungeon[i][j].tile != '#') {	//check if tile is not wall
							//check for adjacencies
							//left
							//System.out.println("tile: " + this.V_dungeon[i][j]);
							if ((j-1) >= 0) {	//not out of bounds
								if (this.V_dungeon[i][j-1].tile != '#') {
									this.V_dungeon[i][j].addAdj(this.V_dungeon[i][j-1]);
									//System.out.println("left: " + this.V_dungeon[i][j-1]);
								}
							}
							//top
							if ((i-1) >= 0) {	//not out of bounds
								if (this.V_dungeon[i-1][j].tile != '#') {
									this.V_dungeon[i][j].addAdj(this.V_dungeon[i-1][j]);
									//System.out.println("top: " + this.V_dungeon[i-1][j]);
								}
							}
							//right
							if ((j+1) < maxC) {	//not out of bounds
								if (this.V_dungeon[i][j+1].tile != '#') {
									//this.V_dungeon[i][j].addAdj(this.V_dungeon[i][j+1]);
									this.V_dungeon[i][j].addAdj(this.getVertex(i, j+1));
									//System.out.println("right: " + this.getVertex(i, j+1));
								}
							}
							
							//bottom
							if ((i+1) < maxR) {	//not out of bounds
								if (this.V_dungeon[i+1][j].tile != '#') {
									this.V_dungeon[i][j].addAdj(this.V_dungeon[i+1][j]);
									//System.out.println("bottom: " + this.V_dungeon[i+1][j]);
								}
							}
							//System.out.println();
						}
						
					}
				}
				
				this.setAdjTel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    /**
     * Return the vertex with the given coordinates (row, col)
     * If the vertex does not exist, return null.
     * If the coordinates are out of bounds, return null.
     */
    public Vertex getVertex(Integer row, Integer col) {
        // TODO: Your code here...
    	if ((row>=0) && (col>=0)) {
    		if ((row<maxR) && (col<maxC)) {
    			if (this.V_dungeon[row][col].tile != '#') {
    				return this.V_dungeon[row][col];
				} 
    		}
		}
        return null; // Stub line, you can safely remove when required
    }
    
    /**
     * Return a string representing a depth-first traversal of the graph. 
     * The traversal must start from the Entrance vertex. For each tile, 
     * visit the adjacent vertices in the following order: left, up, right, down. 
     * For each vertex, output its coordinates (comma-separated).
     */
    public String toString() {
        // TODO: Your code here...
    	//Get Entrance vertex
    	
    	Vertex ent = this.getDoor();
    	String str = this.toString2(ent);
    	this.reset();
    	return str;
    }

    /**
     * Return the vertices adjacent to the given vertex.
     * The vertices in the returned array must be sorted in 
     * the following order: left, top, right, bottom.
     * Return an empty array if there are no adjacent vertices.
     */
    public Vertex[] getAdjacentVertices(Vertex vertex) {
        // TODO: Your code here...
    	if (vertex.adjacenciesList == null) {
			return null;
		}
        return vertex.adjacenciesList; 
    }

    /**
     * Return the vertex corresponding to the dungeon entrance.
     */
    public Vertex getDoor() {
        // TODO: Your code here...
    	for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxC; j++) {
				if (this.V_dungeon[i][j].tile == 'E') {	//check if tile is not wall
					return this.V_dungeon[i][j];
				}
			}
    	}
        return null; // Stub line, you can safely remove when required
    }

    /**
     * Return the vertex corresponding to the key tile.
     */
    public Vertex getKey() {
        // TODO: Your code here...
    	for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxC; j++) {
				if (this.V_dungeon[i][j].tile == 'K') {	//check if tile is not wall
					return this.V_dungeon[i][j];
				}
			}
    	}
    	
        return null; // Stub line, you can safely remove when required
    }

    /**
     * Return the vertex corresponding to the treasure tile.
     */    
    public Vertex getTreasure() {
        // TODO: Your code here...
    	for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxC; j++) {
				if (this.V_dungeon[i][j].tile == 'T') {	//check if tile is not wall
					return this.V_dungeon[i][j];
				}
			}
    	}
    	
        return null; // Stub line, you can safely remove when required
    }

    
    public void addVert(char c, int row, int col) {
    	
        Vertex v = new Vertex();
        // set tile
        v.tile = c;
        //set coords
        v.coords = new Coordinates(row, col);
        
        if (c != '#') {
    		//create new array
        	Vertex [] Arr;
        	int count = 0;
        	if (this.verticesList.length == 0) {
    			Arr = new Vertex[1];
    			count = 0;
    		} else {
    			Arr = new Vertex[this.verticesList.length+1];
    			count = this.verticesList.length;
    			//System.out.println("count: " + count);
    			for (int i = 0; i < count; i++) {
    	            Arr[i] = this.verticesList[i];
    	        }
    		}
        	// Assigning new array to original array
            // created above
            this.verticesList = Arr;
            
            //System.out.println(count + ":" + v.tile);
        	this.verticesList[count] = v;
		}
        
        this.V_dungeon[row][col] = v;
        
    }
    
    public void reset() {
		for(Vertex vertex : this.verticesList) {
			if (vertex != null) {
				vertex.visited = false;
				vertex.predecessor = null;
				vertex.distance = Double.POSITIVE_INFINITY;
			}
		}
	}
    
    public String toString2(Vertex v) {
    	
    	String str = "";
    	if (v != null) {
    		if (v.equals(getDoor())) {
        		str += v.coords.getEntCoords();
        		//it is visited
        		v.visited = true;
    		} else {
    			str += v.coords.getCoords(); 
        		//it is visited
        		v.visited = true;
    		}
        	
        	
        	
        	if (!(v.adjacenciesList.equals(null))) {
        		for (int k = 0; k < v.adjacenciesList.length; k++) {
        			
        			if (!(v.adjacenciesList[k] == null)) {
        				if (!v.adjacenciesList[k].visited) {
            				str = str + toString2(v.adjacenciesList[k]);
            			}
    				}
        		}
    		}
		}
    	 
    	
    	return str;
    }
    
    /** 
     * =============================
     * ===        TASK 2         ===
     * =============================
     */

    /**
     * Return the vertices along the shortest path from the start vertex 
     * to the end vertex, as identified by the given coordinates. The start 
     * and the end vertex must be included. If no path exists, return an empty array.
     */
    public Vertex[] getShortestPath(Coordinates start, Coordinates end) {
        // TODO: Your code here...
    	Vertex [] list = new Vertex[0]; 
    	
    	if ((start == null) || (end == null)) {
			return list;
		}
    	
    	this.getVertex(start.row, start.col).distance = 0;
    	//System.out.println(this.getVertex(end.row, end.col).distance);
    	while (check()) {
    		//System.out.println("check");
			for (int i = 0; i < this.verticesList.length; i++) {
				//System.out.println(this.verticesList[i].coords.getEntCoords());
				for (int j = 0; j < this.verticesList[i].adjacenciesList.length; j++) {
					double v = this.verticesList[i].distance;
					double u = this.verticesList[i].adjacenciesList[j].distance;
					//System.out.println(u + " > " + (v+1));
					if (this.verticesList[i].adjacenciesList[j].tile == 'X') {
						if ((u) > (v + 2)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+2;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					} else {
						if ((u) > (v + 1)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+1;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					}
				}
			}
		}
    	//see pred
    	//System.out.println("pred of (1,0) is (1,1) : " + this.getVertex(1, 0).predecessor.coords.getEntCoords());
    	 
    	if (this.getVertex(end.row, end.col).distance != Double.POSITIVE_INFINITY) {
    		list = findList(this.getVertex(start.row, start.col),this.getVertex(end.row, end.col),list);
    		this.reset();
			return list;
		}
    	
        return list; // Stub line, you can safely remove when required
    }

	/**
     * Return an array of vertices that make up the shortest path from the entrance 
     * to the key to the treasure and back to the entrance, in order from start to end.
     * The starting and the ending vertex (entrance) should be included in the path.
     * If there is no path, return an empty array.
     */
    public Vertex[] getShortestPath() {
        // TODO: Your code here...
    	//E to K
    	Vertex [] list = new Vertex[0];
    	Vertex [] listEK = new Vertex[0];
    	if ((this.getDoor() != null) && (this.getKey() != null)) {
    		listEK = this.getShortestPath(this.getDoor().coords, this.getKey().coords);
		}
    	//System.out.println("EK len: " + listEK.length);
    	if (listEK.length == 0) {
			return list;
		}
    	//K to T
    	Vertex [] listKT = new Vertex[0];
    	if ((this.getTreasure() != null) && (this.getKey() != null)) {
    		listKT = this.getShortestPath(this.getKey().coords, this.getTreasure().coords);
		}
    	
    	//System.out.println("KT len: " + listKT.length);
    	if (listKT.length == 0) {
			return list;
		}
    	
    	//T to E
    	Vertex [] listTE = new Vertex[0];
    	if ((this.getTreasure() != null) && (this.getDoor() != null)) {
    		listTE = this.getShortestPath(this.getTreasure().coords, this.getDoor().coords);
		}
    	//System.out.println("TE len: " + listTE.length);
    	if (listTE.length == 0) {
			return list;
		}
    	
    	//append E-K-T-E
    	//append E-K
    	for (int i = 0; i < listEK.length; i++) {
			list = addVert_L(list, listEK[i]);
		}
    	//System.out.println("append listEK len: " + list.length);
    	//append K-T
    	for (int i = 1; i < listKT.length; i++) {
    		list = addVert_L(list, listKT[i]);
		}
    	//append T-E
    	for (int i = 1; i < listTE.length; i++) {
    		list = addVert_L(list, listTE[i]);
		}
    	
    	
        return list; // Stub line, you can safely remove when required
    }


    /**
     * Return the length of the shortest path from the given starting vertex coordinates, 
     * to the end vertex coordinates. The start and end vertices should be part of the path.
     * If no path exists, return null.
     */
    public Integer getShortestPathLength(Coordinates start, Coordinates end) {
        // TODO: Your code here...
    	if ((start == null) || (end == null)) {
			return null;
		}
    	
    	this.getVertex(start.row, start.col).distance = 0;
    	
    	while (check()) {
			for (int i = 0; i < this.verticesList.length; i++) {
				for (int j = 0; j < this.verticesList[i].adjacenciesList.length; j++) {
					double v = this.verticesList[i].distance;
					double u = this.verticesList[i].adjacenciesList[j].distance;
					if (this.verticesList[i].adjacenciesList[j].tile == 'X') {
						if ((u) > (v + 2)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+2;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					} else {
						if ((u) > (v + 1)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+1;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					}
				}
			}
		}
    	int i = 0;
    	if (this.getVertex(end.row, end.col).distance != Double.POSITIVE_INFINITY) {
    		i = getLen(this.getVertex(start.row, start.col),this.getVertex(end.row, end.col));
    		this.reset();
			return i;
		}
        return null; // Stub line, you can safely remove when required
    }

	/**
     * Return the string representing the shortest path from start vertex to end vertex by 
     * indicating the succession of steps (left, right, up, down) that need to be taken. 
     * The words must be comma-separated, with a space after each comma, and a full stop at the end. 
     * Left-up-right-down movement preference applies.
     */
    public String getShortestPathString(Coordinates start, Coordinates end) {
        // TODO: Your code here...
    	String str = "";  
    	if ((start == null) || (end == null)) {
			return str;
		}
    	this.getVertex(start.row, start.col).distance = 0;
    	
    	while (check()) {
			for (int i = 0; i < this.verticesList.length; i++) {
				for (int j = 0; j < this.verticesList[i].adjacenciesList.length; j++) {
					double v = this.verticesList[i].distance;
					double u = this.verticesList[i].adjacenciesList[j].distance;
					if (this.verticesList[i].adjacenciesList[j].tile == 'X') {
						if ((u) > (v + 2)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+2;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					} else {
						if ((u) > (v + 1)) {
							//System.out.println("u:" + u + " > v+1:" + (v+1));
							this.verticesList[i].adjacenciesList[j].distance = v+1;
							//set predecessor
							this.verticesList[i].adjacenciesList[j].predecessor = this.verticesList[i]; 
							//System.out.println("pred: " + this.verticesList[i].coords.getEntCoords() + ", cur: " + this.verticesList[i].adjacenciesList[j].coords.getEntCoords());
						}
					}
				}
			}
		}
    	
    	
    	if (this.getVertex(end.row, end.col).distance != Double.POSITIVE_INFINITY) {
    		str = findString(this.getVertex(start.row, start.col),this.getVertex(end.row, end.col));
    		this.reset();
    		if (str.length() == 0) {
				return str;
			} else {
				return str+".";
			}
			
		}
    	
        return str; // Stub line, you can safely remove when required
    }

	/**
     * This method has the same functionality as getShortestPathString(Coordinates start, Coordinates end), 
     * but should return the text representation of the shortest path from entrance 
     * to key to treasure and back to the entrance.
     */
    public String getShortestPathString() {
        // TODO: Your code here...
    	String str = "";
    	String strEK = "";
    	
    	if ((this.getDoor() != null) && (this.getKey() != null)) {
    		strEK = this.getShortestPathString(this.getDoor().coords, this.getKey().coords);
		}
    	//System.out.println("strEK: " + strEK);
    	if (strEK == "") {
			return "";
		} else {
			strEK = strEK.substring(0, strEK.length() - 1);
		}
    	
    	//K to T
    	String strKT = "";
    	if ((this.getTreasure() != null) && (this.getKey() != null)) {
    		strKT = this.getShortestPathString(this.getKey().coords, this.getTreasure().coords);
		}
    	//System.out.println("strKT: " + strKT);
    	if (strKT == "") {
			return "";
		} else {
	    	strKT = strKT.substring(0, strKT.length() - 1);
		}
    	
    	//T to E
    	String strTE = "";
    	if ((this.getTreasure() != null) && (this.getDoor() != null)) {
    		strTE = this.getShortestPathString(this.getTreasure().coords, this.getDoor().coords);
		}
    	//System.out.println("strTE: " + strTE);
    	if (strTE == "") {
			return "";
		} 
    	
    	//append E-K-T-E
    	//append E-K
    	str += strEK;
    	
    	//append K-T
    	str += ", " + strKT;
    	
    	//append T-E
    	str += ", " + strTE;
    	
        return str; // Stub line, you can safely remove when required
    }
    
    //Helper Functions
    private boolean check() {
    	boolean f = false;
    	int i = 0;
    	while ((!f) && (this.verticesList.length > i)) {
    		//System.out.println("i:" + i);
    		//System.out.println("Vertex coords: " +  this.verticesList[i].coords.getEntCoords());
    		//System.out.println("j: " + this.verticesList[i].adjacenciesList.length);
			for (int j = 0; j < this.verticesList[i].adjacenciesList.length; j++) {
				double v = this.verticesList[i].distance;
				double u = this.verticesList[i].adjacenciesList[j].distance;
				//System.out.println(u + " > " + (v+1));
				if (this.verticesList[i].adjacenciesList[j].tile == 'X') {
					if ((u) > (v + 2)) {
						f = true;
						return f;
					}
				} else {
					if ((u) > (v + 1)) {
						f = true;
						return f;
					}
				}
			}
			i++;
		}
    	//System.out.println("checked");
    	return f;
    }
    
    private String findString(Vertex start, Vertex cur) {
		// TODO Auto-generated method stub
    	if (cur.predecessor == start) {
    		Vertex pred = cur.predecessor;
    		
    		//check if teleport
    		if ((pred.tile == '!') && (cur.tile == '!')) {
    			return findString(start, cur.predecessor) + "teleport";
			}
    		
    		//right
    		if (pred.coords.col == (cur.coords.col-1)) {
				return findString(start, cur.predecessor) + "right";
			} 
    		//left
    		if (pred.coords.col == (cur.coords.col+1)) {
    			return findString(start, cur.predecessor) + "left";
			}
    		//up
    		if (pred.coords.row == (cur.coords.row+1)) {
    			return findString(start, cur.predecessor) + "up";
			}
    		//down
    		if (pred.coords.row == (cur.coords.row-1)) {
    			return findString(start, cur.predecessor) + "down";
			}
    		
    		return "";
		} else if (cur.predecessor != null) {
    		Vertex pred = cur.predecessor;
    		
    		//check if teleport
    		if ((pred.tile == '!') && (cur.tile == '!')) {
    			return findString(start, cur.predecessor) + ", teleport";
			}
    		
    		//right
    		if (pred.coords.col == (cur.coords.col-1)) {
				return findString(start, cur.predecessor) + ", right";
			} 
    		//left
    		if (pred.coords.col == (cur.coords.col+1)) {
    			return findString(start, cur.predecessor) + ", left";
			}
    		//up
    		if (pred.coords.row == (cur.coords.row+1)) {
    			return findString(start, cur.predecessor) + ", up";
			}
    		//down
    		if (pred.coords.row == (cur.coords.row-1)) {
    			return findString(start, cur.predecessor) + ", down";
			}
    		
    		return "";
    	} 
    	
		return "";
	}

    private Integer getLen(Vertex start, Vertex cur) {
		// TODO Auto-generated method stub
    	if (cur.predecessor != null) {
    		return 1 + getLen(start, cur.predecessor);
    	} 
		return 0;
	}
    
    private Vertex[] findList(Vertex start, Vertex cur, Vertex[] list) {
		// TODO Auto-generated method stub
    	if (list.length == 0) {
			//add vertex to list
    		//System.out.println("tile: " + cur.tile);
    		list = addVert_L(list, cur);
    		return findList(start, cur.predecessor, list);
		} else if (start == list[0]) {
			return list;
		} else {
			list = mov_up(list,cur);
			return findList(start, cur.predecessor, list);
		}
	}
    
    private Vertex[] mov_up(Vertex[] list, Vertex cur) {
		// TODO Auto-generated method stub
		list = addVert_L(list, list[list.length-1]);
		for (int i = list.length-1; i > 0; i--) {
			list[i] = list[i-1];
		}
		list[0] = cur;
		
		return list;
	}

    public Vertex[] addVert_L(Vertex [] list, Vertex v) {
    	
    	//create new array
    	Vertex [] Arr;
    	int count;
    	
    	if (list.length == 0) {
			Arr = new Vertex[1];
			count = 0;
		} else {
			Arr = new Vertex[list.length+1];
			count = list.length;
			for (int i = 0; i < count; i++) {
	            Arr[i] = list[i];
	        }
		}
     
  
        // Assigning new array to original array
        // created above
        list = Arr;
        
        if (v.tile != '#') {
        	list[count] = v;
		}
        return list;
        
    }
    

    /** 
     * =============================
     * ===        TASK 3         ===
     * =============================
     */

    /**
     * No additional methods need to be implemented, but you must expand your code to work with traps and teleports.
     */
    //Helper functions
    private void setAdjTel() {
    	Vertex v = null;
    	Vertex v2 = null;
    	
    	for (int i = 0; i < this.verticesList.length; i++) {
			if (this.verticesList[i].tile == '!') {
				if (v == null) {
					v = this.verticesList[i];
					//System.out.println(i + " coords: " + this.verticesList[i].coords.getEntCoords());
				} else {
					v2 = this.verticesList[i]; 
					//System.out.println(i + " coord: " + this.verticesList[i].coords.getEntCoords());
				}
			}
		}
    	
    	//add adjacencies
    	
    	if (v != null) {
    		v.addAdj(v2);
        	v2.addAdj(v);
		}
    	
    }

}
