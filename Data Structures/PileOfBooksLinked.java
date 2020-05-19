/*
 * CS2400-01
 * Michael Than
 * Bronco ID: 012593802
 * This is the linked nodes class for pile of books that implements the PileOfBooksBag.java
 * 
 */
public final class PileOfBooksLinked<T> implements PileOfBooksBag<T> {
	private BookNode firstBook; //reference to first node
	private int numberOfBooks;
	public PileOfBooksLinked() {
		firstBook=null;
		numberOfBooks=0;
	} //end default constructor
	
	
	//implemented methods-----------
	/*
	 * adds a new book to the top of the pile
	 * parameter takes in the the title of the new book added
	 * returns true if adding is successful
	 */
	public boolean add(T newBook) {
		BookNode newBookNode=new BookNode(newBook); //creating new book node with entered title
		newBookNode.next=firstBook; //connected first book to the rest of the pile
		firstBook= newBookNode;// new book is now at the top of the pile
		numberOfBooks++;
		return true;
	} //end add method
	/*
	 * remove a book from the top of the pile, no remove method for a specific book because you can only take 
	 * from the top of the pile
	 * returns the removed book
	 */
	public T remove () {
		T result=null;
		if(firstBook!=null) {
			result=firstBook.getTitle(); // gets the title of the first book
			firstBook=firstBook.getNextBook();
			numberOfBooks--;
		} 
		return result;
	} //end remove method
	/*
	 * checks to see if pile is empty
	 * returns true if pile is empty
	 */
	public boolean isEmpty() {
		return numberOfBooks==0;
	} //end isEmpty method
	/*
	 * gets the current size of the pile
	 * returns the number of books in the pile
	 */
	public int getCurrentSize() {
		return numberOfBooks;
	} //end getCurrentSize
	/*
	 * clears all books in the pile
	 */
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	} //end clear method
	/*
	 * gets the number of times a certain book appears in the pile
	 * returns number of times the certain book appears
	 */
	public int getFrequencyOf (T aTitle) {
		int frequency=0;//keeps count of how many times that book is in pile
		int counter=0; 
		BookNode currentBook=firstBook;
		while ((counter<numberOfBooks)&&(currentBook!=null)) {
			if(aTitle.equals(currentBook.getTitle())) {
				frequency++;
			}
			counter++;
			currentBook=currentBook.getNextBook();
		}
		return frequency;
	} //end getFrequencyOf
	/*
	 * checks if the book is in the pile
	 * returns true the book is in pile
	 */
	public boolean contains (T aTitle) {
		boolean found=false;
		BookNode currentBook=firstBook;
		while (!found&&(currentBook!=null)) {
			if (aTitle.equals(currentBook.getTitle()))
				found=true;
			else 
				currentBook=currentBook.getNextBook();
		}
		return found;
	} //end contains method
	/*
	 * creates an array out of the linked books
	 * returns an array of books in the pile
	 */
	public T[] toArray () {
		@SuppressWarnings("unchecked") //cast is safe because the array contains null entries
		T[] result=(T[])new Object[numberOfBooks];//unchecked cast
		int index=0;
		BookNode currentBook=firstBook;
		while ((index<numberOfBooks)&&(currentBook!=null)) {
			result[index]=currentBook.getTitle();
			index++;
			currentBook=currentBook.getNextBook();
		}
		return result;
	} //end toArray method
	
	
	//private inner class if nodes
	private class BookNode {
		private T title; // the title of the book stored as data
		private BookNode next; // node link to the next book
		//constructor for book with a linked book
		private BookNode (T title, BookNode nextBook) {
			this.title=title;
			next=nextBook;
		}
		//constructor for book without a linked book
		private BookNode (T title) { 
			this(title,null);
		}// end constructor
		private T getTitle() {
			return title;
		} //end getTitle
		private void setTitle(T newTitle) {
			title=newTitle;
		} //end setTitle
		private BookNode getNextBook() {
			return next;
		} //end getNextBook
		private void setNextBook(BookNode nextBook) {
			next=nextBook;
		} //end setNextBook
		
	} //end BookNode
	
	
} //end PileOfBooksLinked
