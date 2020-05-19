/*
 * CS2400-01
 * Michael Than
 * Bronco ID: 012593802
 * This class uses PileOfBooksLinked to demonstrate how linked chains work for the pile
 * 
 * 
 */
import java.util.Scanner;
public class PileOfBooksLinkedDemo {

	public static void main(String [] args) {
		PileOfBooksBag<String> pile=new PileOfBooksLinked<>();
		testIsEmpty(pile, true);// checks if the pile is indeed empty
		displayPileOfBooks(pile);//displays that the chain/pile is empty
		System.out.println("Let's start up a pile of books using a linked chain. Enter "
				+ "the amount of books you think will be in this pile\n");
		Scanner inNumBooks = new Scanner(System.in);// takes in the amount of books the user thinks is needed
		int numBooks= inNumBooks.nextInt();
		System.out.println("Now enter the titles of the books you wish to add\n");
		String[] inputBookLinked = new String[numBooks];
		for (int index=0;index<numBooks;index++) {//takes in the titles of the books added to the pile
			Scanner inBook=new Scanner(System.in);
			inputBookLinked[index]=inBook.nextLine();
		} //end for loop
		System.out.println();
		testAdd(pile, inputBookLinked); //adds the books user inputed and puts them in the bag
		displayPileOfBooks(pile);// displays books in bag after add method
		testIsEmpty(pile, false);
		System.out.println("Enter the title of the book you want to check the frequency of: ");
		Scanner freqOf=new Scanner(System.in); //takes in the book that the user wants to find the frequency of
		String bookFreq=freqOf.nextLine();
		testFrequency(pile, bookFreq);//tests getFrequencyOf using inputed book
		System.out.println("Enter the title of the book you want to check if it is contained in the pile of books");
		Scanner inBookContains=new Scanner(System.in);//takes in the book for testing contains method
		String testBookContains=inBookContains.nextLine();
		testContains(pile,testBookContains); //tests contains method using inputed book
		System.out.println("A book will now be removed from the top of the pile");
		testRemove(pile);// tests remove method
		System.out.println("Now enter another book you would like to add to the pile");
		Scanner inBookAdd=new Scanner(System.in);
		String bookAdd=inBookAdd.nextLine();
		testAdd(pile, bookAdd);
		displayPileOfBooks(pile);
		System.out.println("\nNow the pile of books will be cleared");
		pile.clear(); // clears the pile of books
		testIsEmpty(pile, true); //checks to see if pile is indeed empty
		displayPileOfBooks(pile); //displays the empty pile of books
	
		}
	
	
	
	//tests remove method, will only remove from the top of the pile
		private static void testRemove(PileOfBooksBag<String> pile) {
			System.out.println("\nRemoving a string from the pile ");
			String removedBook=pile.remove();
			System.out.println("Remove method removed "+removedBook);
			displayPileOfBooks(pile);
		}
		//tests contains method
		private static void testContains (PileOfBooksBag<String> pile, String titleTested) {
			System.out.println("\nTesting the contains method: ");
			System.out.println("Does this pile contain "+titleTested+"? "+pile.contains(titleTested));
		}//end testContains
		//tests the getFrequencyOf method
		private static void testFrequency (PileOfBooksBag<String> pile, String titleTested) {
			System.out.println("\nTesting getFrequencyOf method");
			System.out.println("In this pile, the count of "+titleTested+" is "+pile.getFrequencyOf(titleTested)+"\n");
		} //end testFrequency
		//tests the isEmpty method, correct results is what isEmpty should return
		private static void testIsEmpty (PileOfBooksBag<String> pile, boolean correctResult) {
			System.out.print("Using isEmpty with "); 
			if(correctResult) {
				System.out.println("an empty pile. ");
			}
			else {
				System.out.println("a pile that is not empty. ");
			}
			System.out.print("isEmpty finds the pile ");
			if (correctResult&&pile.isEmpty()) {
				System.out.print("empty: OK. ");
			}
				else if (correctResult)
					System.out.print("not empty, but it is empty: ERROR. ");
				else if(!correctResult&&pile.isEmpty())
					System.out.print("empty but it is not empty: ERROR. ");
				else
					System.out.print("not empty: OK. ");
			System.out.println();
			} // end testIsEmpty
		//tests add method
		private static void testAdd (PileOfBooksBag<String> pile, String[] books) {
			System.out.println("Adding to the pile: ");
			for (int i=0;i<books.length;i++) {
				pile.add(books[i]);
				System.out.println(books[i]+" ");
			}
			System.out.println();
		} //end testAdd
		// test adding single book
		private static void testAdd(PileOfBooksBag<String> pile, String bookAdd) {
			System.out.println("Adding "+bookAdd+" to the bag ");
			pile.add(bookAdd);
			System.out.println();
		}
		//tests toArray, also displays books in pile
		private static void displayPileOfBooks (PileOfBooksBag<String> pile) { 
			System.out.println("There are "+pile.getCurrentSize()+" books in this pile");
			Object[] pileArray = pile.toArray();
			for (int index=0;index<pileArray.length;index++) {
				System.out.println(pileArray[index]);
			}
			System.out.println();
		} //end displayPileOfBooks
	
	
}
