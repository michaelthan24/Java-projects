import java.util.Random;
import java.util.Scanner;

public class StrassenMulti {
	public int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		int[][] R = new int[n][n];
		if (n == 1)								// base case if base of matrices is 1
			R[0][0] = A[0][0] * B[0][0];
		else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];
			split(A, A11, 0, 0);				// matrix A split into 4 halves
			split(A, A12, 0, n / 2);
			split(A, A21, n / 2, 0);
			split(A, A22, n / 2, n / 2);
			split(B, B11, 0, 0);				// matrix B split into 4 halves
			split(B, B12, 0, n / 2);
			split(B, B21, n / 2, 0);
			split(B, B22, n / 2, n / 2);
			int[][] p1 = multiply(add(A11, A22), add(B11, B22));
			int[][] p2 = multiply(add(A21, A22), B11);
			int[][] p3 = multiply(A11, sub(B12, B22));
			int[][] p4 = multiply(A22, sub(B21, B11));
			int[][] p5 = multiply(add(A11, A12), B22);
			int[][] p6 = multiply(sub(A21, A11), add(B11, B12));
			int[][] p7 = multiply(sub(A12, A22), add(B21, B22));
			int[][] C11 = add(sub(add(p1, p4), p5), p7);
			int[][] C12 = add(p3, p5);
			int[][] C21 = add(p2, p4);
			int[][] C22 = add(sub(add(p1, p3), p2), p6);
			join(C11, R, 0, 0);					// merge all 4 halves into one matrix
			join(C12, R, 0, n / 2);
			join(C21, R, n / 2, 0);
			join(C22, R, n / 2, n / 2);
			}
	return R;
	}
	public int[][] sub(int[][] A, int[][] B) { // used to subtract two matrices
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
					C[i][j] = A[i][j] - B[i][j];
			return C;
		}
	public int[][] add(int[][] A, int[][] B) {  // used to add two matrices
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
			return C;
		}
	public void split(int[][] P, int[][] C, int iB, int jB) { // used to split the matrices into smaller matrices
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				C[i1][j1] = P[i2][j2];
		}
	public void join(int[][] C, int[][] P, int iB, int jB) { // used to bring back together the split matrices
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
					P[i2][j2] = C[i1][j1];
		}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StrassenMulti strassenMatixMultiplication = new StrassenMulti();
		System.out.println("Enter the base of squared matrices:");
		int n = input.nextInt();
		int[][] firstMatrix = new int[n][n];
		int[][] secondMatrix = new int[n][n];
		int[][] resultMatrix = new int[n][n];
		Random r = new Random();
		int low = 1;
		int high = 10;
		for (int i = 0; i < n; i++) { // creating first matrix
			for (int j = 0; j < n; j++) {
				firstMatrix[i][j] = r.nextInt(high-low) + low;
			}
		}
		for (int i = 0; i < n; i++) { // creating second matrix
			for (int j = 0; j < n; j++) {
				secondMatrix[i][j] = r.nextInt(high-low) + low;
			}
		}
		// result matrix will have the product of the first and second matrix using the strassen algorithm
		long startTime = System.nanoTime();
		resultMatrix = strassenMatixMultiplication.multiply(firstMatrix, secondMatrix);
		long endTime = System.nanoTime();	// calculates runtime of matrix multiplication algorithm
		long totalTime = endTime-startTime;
		totalTime = totalTime/1000000;			//converts to millisecs
		System.out.println("run time " + totalTime + " ms");
		}
}
