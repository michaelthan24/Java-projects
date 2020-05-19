import java.util.Scanner;
import java.util.Random;

public class mergeSort {
	// Merge Sort ------------
	public static void merge (int A[], int lt, int mid, int rt) {
		int x = mid - lt + 1;		// finds sizes of the two subarrays
		int y = rt - mid;
		int a1[] = new int[x];		//create the two temp subarrays
		int a2[] = new int[y];
		for (int i = 0; i < x; ++i)	// copy subarrays from primary array
			a1[i] = A[lt + i];
		for (int j = 0; j < y; ++j)
			a2[j] = A[mid + 1 + j];
		int i = 0, j = 0;			// initial indices
		int k = lt;					//initial index of merged array
		while (i < x && j < y) {
			if (a1[i] <= a2[j]) {
				A[k] = a1[i];
				i++;
			} 
			else {
				A[k] = a2[j];
				j++;
			}
			k++;
		}
		while (i < x) {			// copy over any left over elements
			A[k] = a1[i];
			i++;
			k++;
		}
		while (j < y) {
			A[k] = a2[j];
			j++;
			k++;
		}
	}
	public static void mSort (int A[], int lt, int rt) {
		if (lt < rt) {
			int mid = (lt+rt)/2;	// find mid
			mSort (A, lt, mid);
			mSort (A, mid+1, rt);
			merge (A, lt, mid, rt);	// merge two sorted arrays
		}		
	}
	public static void main(String[] args) {
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
		long start = System.nanoTime();			//calculates how long the merge sort algorithm is ran
		mSort(arr, 0, n-1);
		long end = System.nanoTime();
		long totaltime = end - start;
		totaltime = totaltime/1000000;			//converts to millisecs
		System.out.println("run time " + totaltime + " ms");
		input.close();
		
	}

}
