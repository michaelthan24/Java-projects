import java.io.*;
import java.util.*;

	
public class TravelingSalesmanBFS {
	static ArrayList<Integer> vertices = new ArrayList<Integer>();
	static int [][] edges;
	static int numVertices;
	static int minLength;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Integer> optimalPath;
	
	public static void main(String [] args) {
		numVertices = 5;			// can be replaced with different number for different adjacency matrix  
		edges = new int[numVertices][numVertices];
		
		try {
			File file = new File("C:\\Users\\micha\\eclipse-workspace\\CS 3310 finalproj\\src\\inputTSP.txt");
			Scanner sc = new Scanner(file);
			for(int i=0;i<numVertices;i++)
				for(int j=0;j<numVertices;j++)
					if(sc.hasNextInt())
						edges[i][j]=sc.nextInt();
			sc.close();
		} 
		catch (Exception e) {
			System.out.println("File not found");
		}
		int result = travelingSalesBFS();
		System.out.print("The length of the optimal tour is " + result + "\nOptimal tour is: ");
		for(int i=0;i<numVertices;i++) {
			System.out.print(optimalPath.get(i));
			System.out.print("->");
		}
		System.out.print(optimalPath.get(numVertices));
		System.out.println();
	}
	/*
	 * desc: this returns the minimum length of the tour
	 */
	static int travelingSalesBFS () {
		ArrayList<node> qList = new ArrayList<node>();						// creates the priority queue
		node startNode = new node();									
		ArrayList<Integer> startNodeList = new ArrayList<Integer>();		// creates a starting node along with a start path
		int startV = 0;
		startNodeList.add(startV);
		startNode.path = startNodeList;
		startNode.level = 0;
		startNode.bound = bound(startNode);
		qList.add(startNode);												// add the startnode to the queue
		minLength = INF;
		
		node preNode = new node();
		while(!qList.isEmpty()) {
			preNode = qList.get(0);
			qList.remove(0);
			if(preNode.bound < minLength) {					//if the node from the queue is less than the minimum length then it is promising
				for(int i=1;i<numVertices;i++) {			//if the path from the node is 
					if(preNode.path.contains(i)) {
						continue;
					}
					node nextNode = new node();				//create a new node for the child nodes and add all the paths from the parent
					ArrayList<Integer> nextNodeList = new ArrayList<Integer>();
					nextNode.level = preNode.level + 1;
					nextNodeList.addAll(preNode.path);
					nextNodeList.add(i);
					nextNode.path = nextNodeList;
					if(nextNode.level == numVertices-2) {	// if the leaves have been reached
						for(int j=0;j<numVertices;j++) {
							if(!nextNode.path.contains(j)) {
								nextNode.path.add(j);
								break;
							}
						}
						nextNode.path.add(startV);
						int nextNodeLen = length(nextNode);
						if (nextNodeLen < minLength) {		//if the node has a length smaller than the minimum length then replace
							minLength = nextNodeLen;
							optimalPath = nextNode.path;	//update the optimal path with the path of the node with the minimum length
							
						}
					}
					else {
						nextNode.bound = bound(nextNode);	// add the next node into the queue if leaves are not reached
						if(nextNode.bound < minLength) {
							boolean mark = false;
							for (int n=0;n<qList.size();n++) {
								if(qList.get(n).bound >= nextNode.bound) {
									mark = true;
									qList.add(n, nextNode);
									break;
								}
							}
							if(!mark) {
								qList.add(nextNode);
							}
						}
					}
				}
			}
		}
		return minLength;
	}
	/*
	 * desc: calculates the bound of the node
	 */
	static int bound (node node) {
		ArrayList<Integer> selectedV = node.path;
		int bound = 0;
		int i;
		int j;
		
		if(selectedV.size()==1) {				// if the size of the path is only one then the bound is just the sum of all the min edges
			for(i = 0;i<numVertices;i++) {
				bound = bound + minEdge(edges[i]);
			}
		}
		else {									// calculate the bound with taking the already taken vertices into consideration
			for (i=0;i<numVertices;i++) {
				if(selectedV.contains(i) && i != selectedV.get(selectedV.size()-1)) {
					bound = bound + edges[i][selectedV.get(getPosInSelected(i,selectedV)+1)];
				}
				else if(i == selectedV.get(selectedV.size()-1)) {
					ArrayList<Integer> ilist = new ArrayList<Integer>();
					for (j=0;j<numVertices;j++) {
						if(!selectedV.contains(j)) {
							ilist.add(edges[i][j]);
						}
					}
					bound = bound + minEdge(ilist);
				}
				else {
					ArrayList<Integer> ilist = new ArrayList<Integer>();
					for(j=0;j<numVertices;j++) {
						if(j == selectedV.get(0)) {
							ilist.add(edges[i][j]);
						}
						if(!selectedV.contains(j)) {
							ilist.add(edges[i][j]);
						}
					}
					bound = bound + minEdge(ilist);
				}
			}
		}
		return bound;
	}
	static int length (node node) {					// finds the length at the node
		int length = 0;
		for (int i=0;i<numVertices;i++) {
			length = length + edges[node.path.get(i)][node.path.get(i+1)];
		}
		return length;
	}
	static int getPosInSelected(int i, ArrayList<Integer> selectedV) {	// gets the position in the selected vertices list
		for (int j=0; j<selectedV.size();j++) {
			if (i == selectedV.get(j)) {
				return j;
			}
		}
		return INF;
	}
	static int minEdge(ArrayList<Integer> v) {			// finds the minimum edge of the Arraylist
		int min = INF;
		for(int i=0;i<v.size();i++) {
			if(v.get(i) < min && v.get(i) != 0) {
				min = v.get(i);
			}
		}
		return min;
	}
	static int minEdge(int [] v) {						// finds minimum edge of the array
		int min = INF;
		for(int i=0;i<v.length;i++) {
			if(v[i] < min && v[i] != 0) {
				min = v[i];
			}
		}
		return min;
	}
	static class node {									//node utility class
		ArrayList<Integer> path;
		int bound;
		int level;
	}
}
