import java.io.*;
import java.util.*;

public class ZeroOneKnapBestFirstBB {
	public static ArrayList<item> itemList;
	public static int maxWeight;
	public static int maxProfit;

	public static void main (String [] args) {
		itemList = new ArrayList<>();	// these are the collection of items to possibly be in the knapsack
		maxWeight= 14;  				// max weight for the test file is 14 but can be replaced with any max weight for the knapsack
		int n = 4;
		int [] p = new int[n];	// arrays to hold values of the profits and weights
		int [] w = new int[n];
		// replace this with the path to your inputKnap.txt file
		File file = new File("C:\\Users\\micha\\eclipse-workspace\\CS 3310 finalproj\\src\\inputKnap.txt");
		try {	// scans in first line to record values for profit and scans second line to record values for weights
			Scanner sc = new Scanner(file);
			String lineProfits = sc.nextLine();
			Scanner profitScanner = new Scanner(lineProfits);
			int i = 0;
			while (profitScanner.hasNext()) {
				p[i] = profitScanner.nextInt();
				i++;
			}
			profitScanner.close();
			String lineWeights = sc.nextLine();
			sc.close();
			Scanner weightScanner = new Scanner(lineWeights);
			i = 0;
			while (weightScanner.hasNext()) {
				w[i] = weightScanner.nextInt();
				i++;
			}
			weightScanner.close();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		// add the weights and profits together to create an item that is a candidate for the knapsack
		for (int i=0;i<n;i++) {			
			itemList.add(new item(p[i],w[i]));
		}
		// sorts the items by price per weight
		for (int i=0;i<n;i++) {
			for (int j=0;j<n-1;j++) {
				if(itemList.get(j).PPW < itemList.get(j+1).PPW) {
					item temp = new item(0,0);
					temp = itemList.get(j);
					itemList.set(j, itemList.get(j+1));
					itemList.set(j+1, temp);
				}
			}
		}
		
		int result = knapsack();
		System.out.println("Maximized profit is: " + result);
	}
	/*
	 *  desc: the function that creates the knapsack based on the items in the file
	 */
	public static int knapsack () {
		ArrayList<node> qlist = new ArrayList<node>();	// creates the priority queue with node items
		node startNode = new node(0,0,0);				// creates dummy node to be the first node
		startNode.bound = bound(startNode);
		qlist.add(startNode);
		maxProfit = 0;
		node preNode = new node(0,0,0);
		while(!qlist.isEmpty()) {
			preNode = qlist.get(0);		// dequeue the first node 
			if(preNode.bound > maxProfit) {				// if the current node is still promising
				System.out.println("Visiting node ("+preNode.level+","+preNode.childNum+") with profit: "+preNode.profit+", weight: "+preNode.weight+", bound: "+preNode.bound+". Max profit is: "+maxProfit);
				qPrint(qlist);
				node nextNode = new node(0,0,0);		// set the next nextNode to the child that includes the next item
				nextNode.level = preNode.level + 1;
				nextNode.weight = preNode.weight + itemList.get(nextNode.level-1).weight;
				nextNode.profit = preNode.profit + itemList.get(nextNode.level-1).profit;
				if(nextNode.weight<=maxWeight && nextNode.profit > maxProfit) {
					maxProfit = nextNode.profit;		// if the node is promising and has greater profit than max profit, then replace
				}
				nextNode.bound = bound(nextNode);
				nextNode.childNum = 1;
				System.out.println("Visiting node ("+nextNode.level+","+nextNode.childNum+") with profit: "+nextNode.profit+", weight: "+nextNode.weight+", bound: "+nextNode.bound+". Max profit is: "+maxProfit);
				qPrint(qlist);
				if(nextNode.bound > maxProfit) {
					qlist.add(nextNode);
					qSort(qlist);
				}
				node newNode = new node(0,0,0);
				newNode.weight = preNode.weight;		// set new node to the child that does not include the next item
				newNode.profit = preNode.profit;
				newNode.level = preNode.level + 1;
				newNode.bound = bound(newNode);
				newNode.childNum = 2;
				System.out.println("Visiting node ("+newNode.level+","+newNode.childNum+") with profit: "+newNode.profit+", weight: "+newNode.weight+", bound: "+newNode.bound+". Max profit is: "+maxProfit);
				if(newNode.bound > maxProfit) {			// add to priority queue
					qlist.add(newNode);
					qSort(qlist);
				}
			}
			qlist.remove(0);
			qPrint(qlist);
		}
		return maxProfit;
	}
	/*
	 *  desc: bound class to find bound of that node, returns the bound
	 */
	static int bound (node node) {
		int totW;
		int bound;
		int j,k;
		if(node.weight >= maxWeight) { 
			return 0;			// returns 0 is node is not promising
		}
		else {
			bound = node.profit;
			j = node.level;
			totW = node.weight;
			while (j<itemList.size() && totW + itemList.get(j).weight <= maxWeight) {
				totW = totW + itemList.get(j).weight;				// grabs as many items as possible
				bound = bound + itemList.get(j).profit;
				j++;
			}
			k=j;
			if(k < itemList.size()) {
				bound = bound + (maxWeight - totW) * itemList.get(j).PPW;		// grabs a fraction of this item
			}
			return bound;
		}
	}
	static void qPrint(ArrayList<node> qlist) {
		System.out.print("   "+"Priority queue contains nodes: ");
		for(int i=0;i<qlist.size();i++) {
			System.out.print("("+qlist.get(i).level+","+qlist.get(i).childNum+")");
		}
		System.out.println();
	}
	static void qSort (ArrayList<node> qlist) {
		for(int i=0;i<qlist.size();i++) {
			for(int j=0;j<qlist.size()-1;j++) {
				if(qlist.get(j).bound<qlist.get(j+1).bound) {
					node temp = new node(0,0,0);
					temp = qlist.get(j);
					qlist.set(j,qlist.get(j+1));
					qlist.set(j+1, temp);
				}
			}
				
		}
	}
	/*
	 * desc: creates an object to be in the knapsack to have the properties of weight and profit
	 */
	static class item {
		int weight;
		int profit;
		int PPW;
		public item (int profit, int weight) {
			this.weight=weight;
			this.profit = profit;
			if (weight == 0) {
				this.PPW = 0;
			} 
			else {
				this.PPW = profit/weight;
			}
		}
	}
	/*
	 * desc: creates the node object with a level, profit, weight, and bound value
	 */
	static class node {
		int level;
		int profit;
		int weight;
		int bound;
		int childNum = 0;
		public node (int level, int profit, int weight) {
			this.level = level;
			this.profit = level;
			this.weight = weight;
		}
	}
}
