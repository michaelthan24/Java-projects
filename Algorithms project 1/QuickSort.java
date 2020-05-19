import java.util.Random;
import java.util.Scanner;

public class QuickSort {
	// Quick Sort
		static int partition ( int A[], int low, int high) { // low and high are starting and ending indices
			int pivot = A[high];		// find pivot to partition around
			int x = (low-1); 			// index of the smaller index
			for (int i = low;i < high;i++ ) {
				if (A[i] < pivot) {		// if the element is smaller than pivot
					x++;
					int temp = A[x];	// swap the two elements
					A[x] = A[i];
					A[i] = temp;
				}
			}
			int temp = A[x+1];			// swap the pivot with the last index checked
			A[x+1] = A[high];
			A[high] = temp;
			return x+1; 				// returns the pivot index
		}
		static void qSort (int A[], int low, int high) {
			if (low < high) {
				int pivot = partition (A, low, high); // finds the partitioning index
				qSort (A, low, pivot - 1);
				qSort (A, pivot + 1, high);
			}
		}
		public static void main (String [] args) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter N: ");
			int n = input.nextInt();	// request n from user
			int [] arr = new int[n];
			Random ran = new Random();	
			int min = 1;
			int max = 100;				// numbers in array range from 1-99
			for (int i=0; i<n;i++) {
				arr[i]=ran.nextInt(max-min) + min; // generate random number in list of n elements	
			}
			long start = System.nanoTime();			//calculates how long quicksort is ran
			qSort(arr, 0, n-1);
			long end = System.nanoTime();
			long totaltime = end - start;
			totaltime = totaltime/1000000;			//converts to millisecs
			System.out.println("run time " + totaltime + " ms");
			input.close();
		}
}
