// Name: Onthatile
// Student number: u20589507
import java.util.ArrayList;
import java.util.List;
 
public class Graph {
 
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.POSITIVE_INFINITY);
		}
	}

	////// Implement the methods below this line //////

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {
		
		// Your code here
		//Bellman-Ford Algorithm
		sourceVertex.setDistance(0);
		while (check()) {
			for (int j = 0; j < this.verticesList.size(); j++) {
				
				for (int j2 = 0; j2 < getEdgeList(j).size(); j2++) {
					double v = getEdgeStartDist(j,j2);
					double u = getEdgeEndDist(j,j2);
					if ((u) > (v + getEdgeWeight(j, j2))) {
						setEdgeEndDist(j,j2,v + getEdgeWeight(j, j2));
						getEdgeEnd(j, j2).setPredecessor(getEdgeStart(j, j2));
					}
				}
			}
		}
		List<Vertex> list = new ArrayList<>();
		if (targetVertex.getDistance() != Double.POSITIVE_INFINITY) {
			return find_List(sourceVertex,targetVertex,list);
		} else {
			return list;
		}
		
	}

	

	

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
 
		// Your code here
		//Bellman-Ford Algorithm
		sourceVertex.setDistance(0);
		while (check()) {
			for (int j = 0; j < this.verticesList.size(); j++) {
				for (int j2 = 0; j2 < getEdgeList(j).size(); j2++) {
					double v = getEdgeStartDist(j,j2);
					double u = getEdgeEndDist(j,j2);
					if ((u) > (v + getEdgeWeight(j, j2))) {
						setEdgeEndDist(j,j2,v + getEdgeWeight(j, j2));
						getEdgeEnd(j, j2).setPredecessor(getEdgeStart(j, j2));
					}
				}
			}
		}
		return targetVertex.getDistance();
	}
	
	//helper functions
	private boolean check() {
		boolean f = false;
		int i = 0;
		while ((!f) && (this.verticesList.size() > i)) {
			for (int j = 0; j < getEdgeList(i).size(); j++) {
				double v = getEdgeStartDist(i,j);
				double u = getEdgeEndDist(i,j);
				if ((u) > (v + getEdgeWeight(i, j))) {
					f = true;
					return f;
				}
			}
			i++;
		}
		return f;
	}

	private List<Edge> getEdgeList(int i) {
		return this.verticesList.get(i).getAdjacenciesList();
	} 
	private Vertex getEdgeStart(int i, int j) {
		return this.verticesList.get(i).getAdjacenciesList().get(j).getStartVertex();
	} 
	private Vertex getEdgeEnd(int i, int j) {
		return this.verticesList.get(i).getAdjacenciesList().get(j).getEndVertex();
	}
	private double getEdgeStartDist(int i, int j) {
		return this.verticesList.get(i).getAdjacenciesList().get(j).getStartVertex().getDistance();
	} 
	private double getEdgeEndDist(int i, int j) {
		return this.verticesList.get(i).getAdjacenciesList().get(j).getEndVertex().getDistance();
	} 
	private double getEdgeWeight(int i, int j) {
		return this.verticesList.get(i).getAdjacenciesList().get(j).getWeight();
	} 
	
	private void setEdgeEndDist(int i, int j, double d) {
		this.verticesList.get(i).getAdjacenciesList().get(j).getEndVertex().setDistance(d);;
	} 
	
	private List<Vertex> find_List(Vertex sourceVertex, Vertex curr, List<Vertex> list) {
		
		if (list.isEmpty()) {
			list.add(curr);
			return find_List(sourceVertex, curr.getPredecessor(), list);
		} else if (sourceVertex == list.get(0)) {
			return list;
		} else {
			list = mov_up(list,curr);
			return find_List(sourceVertex, curr.getPredecessor(), list);
		}
	}

	private List<Vertex> mov_up(List<Vertex> list, Vertex curr) {
		list.add(list.get(list.size()-1));
		for (int i = list.size()-1; i > 0; i--) {
			list.set(i, list.get(i-1));
		}
		list.set(0, curr);
		return list;
	}
}