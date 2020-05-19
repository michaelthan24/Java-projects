import java.util.Random;
import java.util.Scanner;
public class ClassicalMatrixMulti {
	public static void matrixMult (int n, int A[][], int B[][], int C[][]) { // the multiplying algorithm
		int i, j, k;
		for (i=0;i<n;i++) {
			for (j=0;j<n;j++) {
				C[i][j] = 0;
				for (k=0;k<n;k++) {
					C[i][j] = C[i][j] + A[i][k] * B[k][j]; 
				}
			}
		}
	}
	
	public static void main(String [] args) {
		System.out.println("Enter the base of the square matrices ");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int [][] mat1 = new int [n][n];
		int [][] mat2 = new int [n][n];
		int [][] resultMat = new int [n][n];
		Random ran = new Random();
		int low = 1;
		int high = 10;	// lows and highs set so the multiplication does not get too big
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				mat1[i][j] = ran.nextInt(high-low) + low;	// generate first matrix
			}
		}
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				mat2[i][j] = ran.nextInt(high-low) + low;	// generate second matrix
			}
		}
		long startTime = System.nanoTime();
		matrixMult(n, mat1, mat2, resultMat);		// the main operation, matrix multiplication
		long endTime = System.nanoTime();	// calculates runtime of matrix multiplication algorithm
		long totalTime = endTime-startTime;
		totalTime = totalTime/1000000;			//converts to millisecs
		System.out.println("run time " + totalTime + " ms");
		
	
	}
	
	
}
