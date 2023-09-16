/**
 * Name: Onthatile
 * Student Number: u20589507
 */

public class Main {

    public static void main(String[] args) {
        // TODO: Write tests here...
    	DungeonGraph d = new DungeonGraph();
    	d.createGraph("graph3.txt");
    	
    	//getDoor
    	System.out.println("Door: " + d.getDoor().coords.getEntCoords());
    	//getKey
    	//System.out.println("Key: " + d.getKey().coords.getEntCoords());
    	//GetTreasure
    	System.out.println("Treasure: " + d.getTreasure().coords.getEntCoords());
    	System.out.println("Random: " + d.getVertex(3, 3));
    	
    	System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
    	System.out.println(d.toString()); 
    	
    	//getShortestPath
    	System.out.println();
    	System.out.println("<<<<<<<<<getShortestPath(coords,coords)>>>>>>>>>>");  
    	Vertex[] list = d.getShortestPath(d.getDoor().coords, d.getKey().coords);
    	if (list.length != 0) {
    		System.out.print(list[0].tile);
			for (int i = 1; i < list.length; i++) {
				System.out.print(", " + list[i].tile);
			}
		}
    	System.out.println();
    	
    	System.out.println("<<<<<<<<<getShortestPath()>>>>>>>>>>");    
    	
    	Vertex[] listEKTE = d.getShortestPath();
    	System.out.println("length:" + listEKTE.length);
    	if (listEKTE.length != 0) {
    		System.out.print(listEKTE[0].tile);
			for (int i = 1; i < listEKTE.length; i++) {
				System.out.print(", " + listEKTE[i].tile);
			}
		}
    	System.out.println();
    	
    	System.out.println();
    	System.out.println("<<<<<<<<<getShortestPathInteger>>>>>>>>>>");    
    	
    	
    	try {
    		int i = d.getShortestPathLength(d.getDoor().coords, d.getKey().coords);
    		System.out.println("length: " + i);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println();
		}
    	
    	System.out.println();
    	
    	
    	System.out.println("<<<<<<<<<getShortestPathString(coords,coords)>>>>>>>>>>");    
    	
    	String str = d.getShortestPathString(d.getDoor().coords, d.getTreasure().coords);
    	
    	System.out.println(str);
    	System.out.println();
    	
    	System.out.println("<<<<<<<<<getShortestPathString()>>>>>>>>>>");    
    	
    	String str2 = d.getShortestPathString();
    	
    	System.out.println(str2);
    	System.out.println();
    	
    }
}

