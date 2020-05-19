import java.util.Scanner;

public class ZeroOneKnapsack1 {
	public static void main(String args[]) {
	 	int knapsackCap;
		int numObjects;
		int counter;
		System.out.println("Enter the capacity of knapsack :"); // capacity of knapsack will be kept in knapsackCap
		Scanner sc = new Scanner(System.in);
		knapsackCap=sc.nextInt();  		

		System.out.println("Enter number of objects : ");  		//  number of objects will be kept in numObjects
		numObjects=sc.nextInt();

		int[] weights = new int[numObjects];  
		int[] profits = new int[numObjects];
  
		System.out.println("Enter the weights of each item");	// ask weight of each item and store in array
		for(counter=0;counter<numObjects;counter++){  
			weights[counter]=sc.nextInt();   
		}

		System.out.println("Enter the profits of each item :");	// ask for profits of each item
		for(counter=0;counter<numObjects;counter++) {
			System.out.println("Profit of item " + counter + " : " );
			profits[counter]=sc.nextInt();
		}
		printknapSack(weights, profits, knapsackCap, numObjects);
		
	}
	static void printknapSack(int wt[], int prof[], int cap, int numObj) { 
		int i, kcap; 
		int K[][] = new int[numObj + 1][cap + 1]; 
		for (i = 0; i <= numObj; i++) { 						// creating the table for dynamic algorithm
			for (kcap = 0; kcap <= cap; kcap++) { 
				if (i == 0 || kcap == 0) 
					K[i][kcap] = 0; 
				else if (wt[i - 1] <= kcap) 
					K[i][kcap] = Math.max(prof[i - 1] +  K[i - 1][kcap - wt[i - 1]], K[i - 1][kcap]); 
				else
					K[i][kcap] = K[i - 1][kcap]; 
			} 
		} 
		
		int result = K[numObj][cap]; 								// this will store the max profit
		System.out.println("Maximum profit will be: "+result); 

		kcap = cap; 											// use the capacity of the knapsack 
		for (i = numObj; i > 0 && result > 0; i--) { 			// this will handle listing out the ibjects	
			if (result == K[i - 1][kcap]) 						// if the object is not included
				continue; 
			else { 												// the object will be included
				System.out.println("Object included in the knapsack has a weight of " + wt[i - 1] + " and profit of " + prof[i-1]); 					
			result = result - prof[i - 1]; 						// subtract the profit 
			kcap = kcap - wt[i - 1]; 							// subtract the cap
			} 
		} 
	}	
		
		
	
	
}

