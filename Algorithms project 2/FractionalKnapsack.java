import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

public class FractionalKnapsack {

	public static void main (String [] args) {
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
		
		double maxProfit = getMaxProfit(weights, profits, knapsackCap); // sends the weights, profits, and capacity values to getMaxProfit
		System.out.println("Max profit is = " + maxProfit);
		
	}
	/*
	 * @param: the arrays for the weights and profits, and the capacity of the knapsack
	 * desc: Functions to get maximum profit, returns the maximum profit amount
	 */
    private static double getMaxProfit(int[] wt, int[] prof, int capacity) { 
        ItemProf[] Val = new ItemProf[wt.length]; 
        for(int i = 0; i < wt.length; i++) { 
            Val[i] = new ItemProf(wt[i], prof[i], i); 
        } 
        Arrays.sort(Val, new Comparator<ItemProf>()  { 			// sort the objects by value of the objects
        	@Override
            public int compare(ItemProf o1, ItemProf o2)  { 
                return o2.cost.compareTo(o1.cost) ; 
            } 
        }
        ); 
        
        double totalValue = 0; 
        System.out.println("List of objects that maximize the profits: ");
        for(ItemProf i: Val) {
        	int curWt = (int) i.wt; 
            int curProf = (int) i.prof;
            int curInd = (int) i.ind;
            if (capacity - curWt >= 0) { 						// this object can be picked whole 	
                capacity = capacity-curWt; 
                totalValue += curProf; 
                System.out.println("Object " + curInd + " with weight = " + curWt);
            } 
            else { 												// object will be split, can't be picked whole
                double fraction = ((double)capacity/(double)curWt); 
                System.out.println("Object " + curInd + " with weight = " + curWt + ", but only " + fraction + " of this object is taken");
                totalValue += (curProf*fraction); 
                capacity = (int)(capacity - (curWt*fraction)); 
                break; 
            } 
        } 
        return totalValue; 
    } 
  
    /*
     * item profit class to store weights and profits
     */
    static class ItemProf{ 
        Double cost; 
        double wt, prof, ind;   
        public ItemProf(int wt, int prof, int ind) {
            this.wt = wt; 
            this.prof = prof; 
            this.ind = ind; 
            cost = new Double(prof/wt ); 
        } 
    } 
	
}	

