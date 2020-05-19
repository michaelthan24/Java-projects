import java.util.Scanner;
import java.util.Random;
class test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Number:");
		int n = input.nextInt();
		int[] num = new int[n];
		Random r = new Random();
		int Low = 1;
		int High = 100;
		for (int i = 0; i < n; i++) {
		num[i] = r.nextInt(High - Low) + Low;
		}
		long start = System.nanoTime();
		sort(num,0,n-1);
		long end = System.nanoTime();
		long totaltime = end - start;
		System.out.println("run time" + totaltime + "ms");
		sort(num,0,n-1);
		printSortedArray(num);
		input.close();
		}
		public static void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;
		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];
		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
		L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
		R[j] = arr[m + 1 + j];
		/* Merge the temp arrays */
		// Initial indexes of first and second subarrays
		int i = 0, j = 0;
		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
		if (L[i] <= R[j]) {
		arr[k] = L[i];
		i++;
		} else {
		arr[k] = R[j];
		j++;
		}
		k++;
		}
		/* Copy remaining elements of L[] if any */
		while (i < n1) {
		arr[k] = L[i];
		i++;
		k++;
		}
		/* Copy remaining elements of R[] if any */
		while (j < n2) {
		arr[k] = R[j];
		j++;
		k++;
		}
		}
		// Main function that sorts arr[l..r] using merge()
		public static void sort(int arr[], int l, int r) {
		if (l < r) {
		// Find the middle point
		int m = (l + r) / 2;
		// Sort first and second halves
		sort(arr, l, m);
		sort(arr, m + 1, r);
		// Merge the sorted halves
		merge(arr, l, m, r);
		}
		}
		public static void printSortedArray(int[] num) {
		System.out.println("Sorted Array for "+num.length+" Elements");
		for (int i = 0; i < num.length; i++) {
		System.out.print(num[i] + " ");
		}
		}
}

