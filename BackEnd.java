// --== CS400 File Header Information ==--
// Name: Shourya Agrawal
// Email: agrawal28@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Professor Florian Heimerl
// Notes to teammates:
import java.util.List;
import java.util.LinkedList;

/**
 * Back End Implementation
 * @author shourya
 *
 */
public class BackEnd extends CS400Graph<String> {

	/*
	  
	// These methods are the ones inherited form CS400Graph
	// They can be called through super or directly 
	 
	public boolean insertVertex(String data) {
		
		return super.insertVertex(data);
	}
	
	
	public boolean removeVertex(String data) {
		
		return super.removeVertex(data);
	} 
	
	
	public boolean insertEdge(String source, String target, int weight) {
		
		return super.insertEdge(source, target, weight);
	}
	
	
	public boolean removeEdge(String source, String target) {
		
		return super.removeEdge(source, target);
	}
	
	
	public boolean containsVertex(String data){
		
		return super.containsVertex(data);
	}
	
	
	public boolean containsEdge(String source, String target) {
		
		return super.containsEdge(source, target);
	} 
	
	
	public int getWeight(String source, String target) {
		return super.getWeight(source, target);
	}
	
	
	public int getEdgeCount() {
		return super.getEdgeCount();
	}
	
	
	 public int getVertexCount() {
		 return super.getVertexCount();
	 }
	 
	 
	 public boolean isEmpty() {
		 return super.isEmpty();
	 }
	 
	 
	 protected Path dijkstrasShortestPath(String start, String end) {
		 return super.dijkstrasShortestPath(start, end);
	 }
	 
	 
	 public List<String> shortestPath(String start, String end){
		 return super.shortestPath(start, end);
	 }
	 
	 
	 public int getPathCost(String start, String end) {
		 return super.getPathCost(start, end);
	 }
	*/ 
	 /**
	  * This method changes the name of location on the map
	  * @param oldName
	  * @param newName
	  * @return
	  */
	 public boolean changeName(String oldName, String newName) {
		 
		
		 if (containsVertex(oldName)) {
			 
			 Vertex tempV =  vertices.get(oldName); // temp node
			 LinkedList<Edge> tempEdgesLea = tempV.edgesLeaving;
			 LinkedList<Edge> edgeTarget = new LinkedList<Edge>();
			 LinkedList<String> sourceV = new LinkedList<String>();
			  for(Vertex v : vertices.values()) { // goes through the whole table
		            for(Edge e : v.edgesLeaving) // goes through all edges
		                if(e.target == tempV) // if target matches to our temp nodes
		                    edgeTarget.add(e);
		            		sourceV.add(v.data);
		        }
			 
			 removeVertex(oldName); // removes all edges and vertex
			 
			 insertVertex(newName); // insert only vertex
			 for (Edge e: tempEdgesLea) { // inserts all edges from given node
				 insertEdge(newName, e.target.data, e.weight);
			 }
			 for (int i = 0 ; i < edgeTarget.size(); i++) {
				 // inserts all edges to given node
				 
				 insertEdge(sourceV.get(i), edgeTarget.get(i).target.data,
						 edgeTarget.get(i).weight);
			 }
			 return true;
			 
			 
			 
			 
		 }
		 else return false;
	 }
	 
	 /**
	  * This method finds the immediate neighbour of a location / node 
	  * 
	  * @param data
	  * @return
	  */
	 public LinkedList<String> findNeighbours(String data) {
		 
		 LinkedList <String> neighbours = new LinkedList<String>();
		 if (containsVertex(data)) {
			 
			for (int i = 0 ; i < vertices.get(data).edgesLeaving.size();i++) {
				/// gets all neighbours through edgesLeaving Linked List
				neighbours.add(vertices.get(data).edgesLeaving.get(i).target.data);
				
			}
			
			return neighbours;
		 }
		 return null;
	 }
	 
	
}
