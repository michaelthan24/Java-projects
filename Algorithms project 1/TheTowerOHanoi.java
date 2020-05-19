import java.util.Scanner;
public class TheTowerOHanoi {
	static void hanoiTowers(int n, char startPeg, char destPeg, char otherPeg) {
		if (n==1) {				// if there is only one disk
			System.out.println("Move disk 1 from peg " + startPeg + " to peg " + destPeg );
			return;
		}
		hanoiTowers(n-1, startPeg, otherPeg, destPeg);
		System.out.println("Move disk " + n + " from peg " + startPeg + " to peg " + destPeg);
		hanoiTowers(n-1, otherPeg, destPeg, startPeg);
	}
	public static void main(String [] args) {
		System.out.println("Enter number of disks");
		Scanner disk = new Scanner(System.in);
		int n = disk.nextInt();
		long startTime = System.nanoTime();
		hanoiTowers(n, 'A', 'C', 'B');	// three pegs, each named A, B, or C; first moving from A to C
		long endTime = System.nanoTime();	// calculates runtime of tower of hanoi algorithm
		long totalTime = endTime-startTime;
		totalTime = totalTime/1000000;			//converts to millisecs
		System.out.println("run time " + totalTime + " ms");
	}
}
