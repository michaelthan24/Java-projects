import java.util.ArrayList;

public class GraphTest {
	public static void main(String[] args) {
		//the graph has 9 vertices/nodes
		Graph<String> newGraph = new Graph<String>(9);
		newGraph.setLabel(0, "A"); // adding the vertices to the graph, 
		newGraph.setLabel(1, "B"); // assigns each vertex to an index in the vertices array of the newGraph
		newGraph.setLabel(2, "C");
		newGraph.setLabel(3, "D");
		newGraph.setLabel(4, "E");
		newGraph.setLabel(5, "F");
		newGraph.setLabel(6, "G");
		newGraph.setLabel(7, "H");
		newGraph.setLabel(8, "I");
		newGraph.addEdge(0, 1); // adds edge to graph based on index numbers corresponding to the vertex
		newGraph.addEdge(0, 3); // adds edges based on the graph given on assignment paper
		newGraph.addEdge(0, 4);
		newGraph.addEdge(1, 4);
		newGraph.addEdge(2, 1);
		newGraph.addEdge(3, 6);
		newGraph.addEdge(4, 7);
		newGraph.addEdge(4, 5);
		newGraph.addEdge(5, 7);
		newGraph.addEdge(5, 2);
		newGraph.addEdge(6, 7);
		newGraph.addEdge(7, 8);
		newGraph.addEdge(8, 5);
		ArrayList<String> DFTList =  newGraph.getDFT("A"); // gets the depth first search in form of ArrayList
		for (int i=0;i<DFTList.size();i++) { // prints out Depth first traversal of the graph
			System.out.println("Depth first traversal of graph");
			System.out.print(DFTList.get(i) + " ");
		}
	} // end main method
} // end graph test class
