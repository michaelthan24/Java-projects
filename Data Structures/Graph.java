import java.util.ArrayList;

public class Graph <E> implements GraphInterface<E> {
	private boolean [][] edges;
	private E[]labels; // labels index corresponds with vertices index
	Vertex[] vertices; // stores the vertices as vertex objects
	@SuppressWarnings("unchecked")
	public Graph (int n) {
		edges = new boolean[n][n];
		labels = (E[]) new Object[n];
		vertices = new Vertex[n];
		for (int i=0; i<vertices.length;i++) { // initializes the array of vertices
			vertices[i] = new Vertex();
		} // end for
	} // constructor
	public E getVertexData (Vertex x) {
		int index = 0;
		for (int i=0;i<labels.length;i++) {
			if (vertices[i]==x) {
				index = i;
				break;
			} // end if
		} // end for
		return labels[index];
	} // end getVertexData
	public int getVertexIndex (Vertex x) {
		int index = 0;
		for (int i=0;i<labels.length;i++) {
			if (vertices[i]==x) {
				index = i;
				break;
			} // end if
		} // end for
		return index;
	}
	public E getLabel (int vertex) {
		return labels[vertex];
	} // end getLabel
	public boolean isEdge (int vertex, int target) {
		return edges[vertex][target];
	} // end isEdge
	public void addEdge (int vertex, int target) {
		edges [vertex][target] = true;
	} // end addEdge
	public Vertex[] neighbors (int vertex) { // returns the array of vertex neighbors of that vertex based on the index given in parameter
		int i;
		int count = 0;
		Vertex[] answer;
		for (i=0;i<labels.length;i++) {
			if (edges[vertex][i])
				count ++;
		} // end for
		answer = new Vertex[count];
		count = 0;
		for (i=0;i<labels.length;i++) {
			if (edges[vertex][i])
				answer[count++] = vertices[i];
		} // end for
		return answer;
	} // end neighbors
	public Vertex getUnvisitedNeighbor(Vertex target) { // searches for the nodes that has not been visited, returns null if all have been visited
		Vertex[] listOfNeighbors = neighbors(getVertexIndex(target));
		for(int i=0; i<listOfNeighbors.length;i++) {
			if(listOfNeighbors[i].visit==false)
				return listOfNeighbors[i];
		} // end for
		return null;
	} // end getUnVisistedNeighbor
	public void removeEdge (int vertex, int target) {
		edges[vertex][target]=false;
	} // end removeEdge
	public void setLabel (int vertex, E newLabel) {
		labels[vertex]=newLabel;
	} // end setLabel
	public int size() {
		return labels.length;
	} // end size
	public ArrayList<E> getDFT (E origin) {
		ArrayList<E> result = new ArrayList<>();
		StackInterface<Vertex> vertexStack = new LinkedStack<>();
		
		Vertex originVertex = new Vertex();
		for(int i=0;i<labels.length;i++) {
			if(labels[i]==origin)  { // searches for the vertex to set as the origin
				originVertex=vertices[i];
				break;
			} // end if
		} // end for
		originVertex.visited(); // marks the vertex as visited
		result.add(getVertexData(originVertex));
		vertexStack.push(originVertex);
		while(!vertexStack.isEmpty()) {
			Vertex topVertex = vertexStack.peek();
			Vertex nextNeighbor = getUnvisitedNeighbor(topVertex);
			if(nextNeighbor!=null) { // adds all neighbors 
				nextNeighbor.visited();
				result.add(getVertexData(nextNeighbor));
				vertexStack.push(nextNeighbor);
			} // end if
			else { // all neighbors are visited
				vertexStack.pop();
			} // end else
		} // end while
		return result;
	} // end getDFT
	
	
} // end Graph
