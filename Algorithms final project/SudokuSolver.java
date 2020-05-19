import java.util.*;
import java.io.*;
public class SudokuSolver{

	public static final int empty=0; // 0 used to mark empty spots in table
	public static final int n=9; 	 // dimension of the table
	
	/*
	 * desc: this checks the row and the value to see if there is already that value in that row
	 */
	public static boolean usedInRow(int table[][],int row,int value) {
		for(int i=0;i<n;i++){
			if(table[row][i] == value)
				return true;
		}
		return false;
	}
	/*
	 * desc: this checks the column and the value to see if there is already that value in the column
	 */
	public static boolean usedInColumn(int table[][],int col,int value) {
		for(int i=0;i<n;i++){
			if(table[i][col] == value)
				return true;
		}
		return false;
	}
	/*
	 * desc: this checks a box to see if that value is already in there
	 */
	public static boolean usedInBox(int table[][],int row,int col,int value) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(table[row+i][col+j] == value)
					return true;
				return false;
	}
	/*
	 * desc: checks to see if this value is safe to put in the table by checking if it is used in the box/column/row
	 */
	public static boolean isSafe(int table[][],int row,int col,int value) {
		if(!usedInBox(table,row-row%3,col-col%3,value)&&!usedInRow(table,row,value)&&!usedInColumn(table,col,value)&&table[row][col] == empty)
			return true;
		return false;
	}
	/*
	 * desc: this checks to see if this spot is empty or not
	 */
	public static boolean findEmptySpot(int table[][],int spot[]) {   
		spot[0] = spot[1] = -1;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(table[i][j] == empty) {
					spot[0] = i;
					spot[1] = j;
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * desc: the is the main functions that solves the sudoku table
	 */
	public static boolean solveSudoku(int table[][]) {
		int row,col;
		int[]spot=new int[2];
		if(!findEmptySpot(table,spot)) {
			return true;
		}
		row=spot[0];
		col=spot[1];
		for(int num=1;num<=9;num++) {
			if (isSafe(table, row, col, num)) {
				table[row][col] = num;
				if (solveSudoku(table))
					return true;
				table[row][col] = empty;
			}
		}
		return false;
	}
  /*
   * desc: prints out the sudoku table solution
   */
	public static void printSudoku(int table[][]) {
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				System.out.print(table[i][j]+" ");
				System.out.print("\n");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		int[][] table= new int[n][n]; // creates the sudoku table
		try{						// replace the File with the location of your input.txt file
			File file = new File("C:\\Users\\micha\\eclipse-workspace\\CS 3310 finalproj\\src\\input.txt");
			Scanner sc = new Scanner(file);
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(sc.hasNextInt())
						table[i][j]=sc.nextInt();
			sc.close();
		}
		catch(Exception e){
			System.out.println("File not found!");
		}
		if(solveSudoku(table)) {	// if there is a possible solution for this table then it will print out solution
			System.out.println("The solution for the sudoku table is :");
			printSudoku(table);
		}
		else
			System.out.println("Not possible");
	}
}