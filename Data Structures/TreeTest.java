
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreeTest {
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list){  
        ArrayList<T> newList = new ArrayList<T>();
        for (int i=0; i<list.size();i++) {
        	if(!newList.contains(list.get(i))) 
        		newList.add((T) list.get(i));
        } // end for
        return newList;
    } // end remove duplicates  
	public static void helpMenu () {
		System.out.println(" I insert a value");
		System.out.println(" D delete a value");
		System.out.println(" P find predecessor");
		System.out.println(" S find successor");
		System.out.println(" E exit the program");
		System.out.println(" H display this message");
	}
	public static void main (String [] args) {
		System.out.println("Please enter the initial sequence of values: ");
		Scanner numbers = new Scanner(System.in);
		String input = numbers.nextLine(); // gets the inputed line of String numbers
		String [] inputArr = input.split(" "); // puts the line of String integers into an array
		int[] inputNums = new int[inputArr.length];
		ArrayList<Integer> finalArr = new ArrayList<Integer>();
		for (int i=0; i<inputArr.length;i++) {
			finalArr.add(Integer.parseInt(inputArr[i]));
		} // end for
		ArrayList<Integer> newList=removeDuplicates(finalArr); // removes duplicates
		// now add to tree
		Tree<Integer> newTree = new Tree <Integer>();
		for(int i=0; i < newList.size();i++) {
			newTree.add(newList.get(i));
		} // end for
		System.out.print("Pre-order: ");
		newTree.preorder();
		System.out.print("In-order: ");
		newTree.inorder();
		System.out.print("Post-order: ");
		newTree.postorder();
		helpMenu();
		boolean inProg = true;
		while (inProg) {
			System.out.println("Command? ");
			Scanner userIn = new Scanner(System.in);
			String inComm = userIn.nextLine();
			String [] userComm = inComm.split(" ");
			switch (userComm[0]) {
			case "I": 
				boolean checkI = newTree.containsNode(newTree.getRoot(), Integer.parseInt(userComm[1]));
				if (checkI==true) {
					System.out.println("That value already exists! ");
					break;
				} // end if
				newTree.add(Integer.parseInt(userComm[1]));
				newTree.inorder();
				break;
			case "D":
				boolean checkD = newTree.containsNode(newTree.getRoot(), Integer.parseInt(userComm[1]));
				if (checkD==false) {
					System.out.println("That value does not exist, cannot remove");
					break;
				} // end if
				newTree.removeTreeNode(Integer.parseInt(userComm[1]));
				newTree.inorder();
				break;
			case "P":
				boolean checkP = newTree.containsNode(newTree.getRoot(), Integer.parseInt(userComm[1]));
				if (checkP==false) {
					System.out.println("That value does not exist, cannot get predecessor");
					break;
				} // end if
				TreeNode<Integer> pre = newTree.findPre(newTree.getRoot(), null, Integer.parseInt(userComm[1]));	
				System.out.println(pre.getData());
				break;
			case "S":
				boolean checkS = newTree.containsNode(newTree.getRoot(), Integer.parseInt(userComm[1]));
				if (checkS==false) {
					System.out.println("That value does not exist, cannot get Successor");
					break;
				} // end if
				TreeNode<Integer> succ = newTree.findSucc(newTree.getRoot(), Integer.parseInt(userComm[1]));
				System.out.println(succ.getData());
				break;
			case "H":
				helpMenu();
				break;
			case "E":
				System.out.println("Thank you for using my program!");
				inProg = false;
				break;
			default:
				System.out.println("Input valid command");
			} // end switch
		} // end while
	} // end main
} // end TreeTest
