Objective:
To learn how to create Graphs and how to search and traverse them in different ways

List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex)

The function will return the shortest path to the given targetVertex. The returned list will contain
all the vertices from the source vertex to the target vertex in the order that describes the
shortest path. Should the target vertex not be reachable, an empty list should be returned
(not null). The predecessors can be stored in the provided field in the Vertex class. If a
negative cycle is detected, null should be returned. The pre-condition is that the graph is
a weighted digraph.


double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex)
This function should return the shortest total distance to the given targetVertex.
Should the target vertex not be reachable, infinity should be returned. If a negative cycle is detected, negative infinity should be returned. 
The pre-condition is that the graph is aweighted digraph.