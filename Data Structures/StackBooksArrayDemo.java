/*
 * CS 2400-01
 * Michael Than
 * ID: 012593802
 * This class demonstates the use of the StackBooksArray 
 */
import java.util.Scanner;
public class StackBooksArrayDemo {

		public static void main(String [] args) {
			StackBooksInterface<String> stack = new StackBooksArray<>(); //creates the new stack
			testIsEmpty(stack, true);// checks if the stack is indeed empty
			System.out.println("Let's start up a stack of books Using a resizable array. Enter "
					+ "the amount of books you think will be in this stack\n");
			Scanner inNumBooks = new Scanner(System.in);// takes in the amount of books the user thinks is needed
			int numBooks= inNumBooks.nextInt();
			System.out.println("Now enter the titles of the books you wish to add\n");
			String[] inputStackArray = new String[numBooks];
			for (int index=0;index<numBooks;index++) {//takes in the titles of the books added to the stack
				Scanner inBook=new Scanner(System.in);
				inputStackArray[index]=inBook.nextLine();
			} //end for loop
			System.out.println();
			testPush(stack, inputStackArray); //adds the books user inputed and puts them in the stack
			testIsEmpty(stack, false);
			System.out.println("A book will now be removed from the top of the stack");
			testPop(stack);// tests pop method
			System.out.println("\nNow the stack of books will be cleared");
			stack.clear(); // clears the stack of books
			testIsEmpty(stack, true); //checks to see if stack is indeed empty
			}
		
		
		//tests pop method
		private static void testPop(StackBooksInterface<String> stack) {
			System.out.println("\nRemoving a string from the stack ");
			String removedBook=stack.pop();
			System.out.println("Remove method removed "+removedBook);
		} // end testPop
		//tests the isEmpty method, correct results is what isEmpty should return
		private static void testIsEmpty (StackBooksInterface<String> stack, boolean correctResult) {
			System.out.print("Using isEmpty with "); 
			if(correctResult) {
				System.out.println("an empty stack. ");
			}
			else {
				System.out.println("a stack that is not empty. ");
			}
			System.out.print("isEmpty finds the stack ");
			if (correctResult&&stack.isEmpty()) {
				System.out.print("empty: OK. ");
			}
				else if (correctResult)
					System.out.print("not empty, but it is empty: ERROR. ");
				else if(!correctResult&&stack.isEmpty())
					System.out.print("empty but it is not empty: ERROR. ");
				else
					System.out.print("not empty: OK. ");
			System.out.println();
			} // end testIsEmpty
		//tests push method
		private static void testPush (StackBooksInterface<String> stack, String[] books) {
			System.out.println("Adding to the stack: ");
			for (int i=0;i<books.length;i++) {
				stack.push(books[i]);
				System.out.println(books[i]+" ");
			}
			System.out.println();
		} //end testPush

		
}
