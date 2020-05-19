import java.util.Scanner;

class ZeroOneKnapsack {
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

			System.out.println("\nTotal maximum profit : " + maxProfitZeroOne(weights, profits, knapsackCap, numObjects));
	 }
	 	/*
	 	 * @param: takes in weight and profit arrays, knapsack capacity, and number of objects
	 	 * descr: function recursively finds the optimal solution and returns the max profit
	 	 */
	 	static int maxProfitZeroOne(int []wt, int[] prof, int cap, int numObj) { 
	 			int result;
                if (numObj == 0 || cap == 0) {						// base case handles case if number of objects is 0
                	return 0;}
                if (wt[numObj - 1] > cap)							//if weight of the this item is more than knapsack cap then, it cant be included
                	return maxProfitZeroOne(wt, prof,cap, numObj - 1);
                else												//returns the max included in the solution or not included
                	result = max(prof[numObj - 1] + maxProfitZeroOne(wt, prof, cap - wt[numObj - 1], numObj - 1), maxProfitZeroOne(wt, prof,cap, numObj - 1));
                    return result;
        }
        /*
         * descr: function to find maximum between two values
         */
        static int max(int a, int b) {
                return (a > b) ? a : b;
        }
       
}