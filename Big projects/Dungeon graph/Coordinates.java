/**
 * Name: Onthatile
 * Student Number: u20589507
 */

public class Coordinates {

    public Integer row;
    public Integer col;

    public Coordinates(Integer row, Integer col) {
        // TODO: Your code here...
    	this.row = row;
    	this.col = col;
    }
    
    public String getCoords() {
    	String str = "";
    	str = ",(" + row + "," + col + ")";
    	return str;
    }
    
    public String getEntCoords() {
    	String str = "";
    	str = "(" + row + "," + col + ")";
    	return str;
    }

}
