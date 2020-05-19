/*
 * CS2400-01
 * Michael Than
 * Bronco ID:012593802 
 * This is the array for pile of book that implements the interface PileOfBooksBag.java
 * 
 */
public class PileOfBooksArray <T> implements PileOfBooksBag <T> {
	private T[] pile;
	private static final int DEFAULT_CAPACITY=25;
	private int numberOfBooks;
	private boolean integrityOK=false;
	private int MAX_CAPACITY = 1000;
	
	
	//default constructor making a pile with capacity of the default number
	public PileOfBooksArray() {
		this(DEFAULT_CAPACITY);
	}//end default constructor
	//creates a pile of books with a specified capacity given by user
	public PileOfBooksArray(int capacity) {
		if (capacity<=MAX_CAPACITY) { 
		@SuppressWarnings("unchecked") //cast is safe because the new array contains null entries
		T[] tempPile = (T[])new Object[capacity];//unchecked cast
		pile=tempPile;
		numberOfBooks=0;
		integrityOK = true; 
		}
		else //throws an exception if there are more books that the MAXIMUM_CAPACITY
			throw new IllegalStateException ("Attempt to create a pile that capacity is too high, pile will reach the ceiling");
	}//end constructor

	//Implemented methods-----------------
	/*Adds a new book to the top of the pile, which is at the end of the array
	 *parameter takes in the book to be added 
	 *returns true if the addition is successful
	 */
	public boolean add (T newBook) {
		boolean result= true;
		if (isFull()) {//checks to see if the pile is full
			doubleCapacity();//if the pile is full, the size of the pile will be doubled
		}
		pile[numberOfBooks]=newBook;//adds new book to the top of the pile/to the end of the array
		numberOfBooks++;//increases the number of books in pile
		return result;
		}//end add method
	/*
	 * (There is no remove method for specific book because you can only add or take away books
	 *  from the top of the pile)
	 *removes a book from the top of the pile
	 * returns the book that is removed
	 */
	public T remove() {
		checkIntegrity();//checks to see if object is initialized
		T result = removeBook();
		return result;
	} //end remove method
	/*checks to see if pile is empty
	 * returns true if pile is empty, false if not empty
	 */
	public boolean isEmpty() {
		return numberOfBooks==0;
	}//end isEmpty method
	/*
	 * gets the current size of the pile
	 * returns the number of books in the pile
	 */
	public int getCurrentSize() {
		return numberOfBooks;
	}//end getCurrentSize method
	/*
	 * gets the amount of a time a certain book appears in the pile
	 * parameter takes in a book
	 * returns how many times that book appears in the pile
	 */
	public int getFrequencyOf(T book) {
		checkIntegrity();
		int counter=0;
		for (int index=0;index<numberOfBooks;index++) {
			if (book.equals(pile[index])) {
				counter++;//counter used to count the amount of times that book appears in the pile
			}
		}
		return counter;
	}//ends getFrequencyOf method
	/*
	 * clears all books in the pile, from top to bottom
	 */
	public void clear() {
		while(!isEmpty())
			remove();
	}//end clear method
	/*
	 * checks to see if a certain book is contained in the pile
	 * parameter takes in the book to be check
	 * returns true if the pile contains the book
	 */
	public boolean contains(T book) {
		checkIntegrity();
		return getIndexOf(book)>=0;
	} //end contains method
	/*
	 * creates a new array with the books in the pile, with capacity of the number of books in the pile
	 * returns the array of current books in the pile
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")//the cast is safe because the array contains null entries
		T[] result = (T[])new Object[numberOfBooks];//unchecked cast
		for (int i=0;i<numberOfBooks;i++) {
			result[i]=pile[i];
		}
		return result;	
	} //end toArray method
	
	
	//additional methods-------------------
	/*
	 * does the actual removing of the book from the pile
	 * returns the book removed
	 */
	private T removeBook() {
		T result = null;
		if (!isEmpty()) {//checks to see if book pile is empty
			result=pile[numberOfBooks-1];//gets the book at the top of the pile
			pile[numberOfBooks-1] = null;//sets the value at the top of the pile to null
			numberOfBooks--;//decreases size of the pile by one
		}
		else { //throws an exception because there are no books to remove
			throw new IllegalStateException("Cannot remove a books because there are no books to remove\n");
		}
		return result;
	} //end removeBook method
	/*
	 * makes sure the capacity of the pile does not go over the maximum amount
	 */
	public void checkCapacity(int capacity) {
		if (capacity>MAX_CAPACITY)
			throw new IllegalStateException("Attemt to create pile with capacity exceeding the max");
	} //end checkCapacity
	/*
	 * doubles the size of the array if there is no more space for a new book to be added
	 */
	private void doubleCapacity() {
		int newSize=2*pile.length; //length of new array
		checkCapacity(newSize);//checks to see if new length is valid
		@SuppressWarnings("unchecked")//cast is safe because the array contains null entries
		T[] newPile = (T[])new Object[newSize];//unchecked cast
		for (int i=0;i<numberOfBooks;i++) { //copies the books from the original pile to the new pile
			newPile[i]=pile[i];
			}
		pile=newPile; // sets the new pile with double capacity to reference of the original pile
	} //end doubleCapacity method
	/*
	 * gets the index of a book in the array
	 * returns the index
	 */
	public int getIndexOf(T book) {
		int location=-1;
		boolean found=false;
		int index=0;
		while (!found && (index<numberOfBooks)) {
			if (book.equals(pile[index])) {
				found=true;
				location=index;
			}
			index++;
		}
		return location;
	} //end getIndexOf method
	/*
	 * checks to see if pile is full
	 */
	public boolean isFull() {
		return numberOfBooks == pile.length;
	} //end isFull method
	/*
	 * checks to see if the object is initialized, if it is not an exception is thrown
	 */
	private void checkIntegrity () {
		if(!integrityOK) {
			throw new SecurityException("Pile is corrupt");
		}
	} //end checkIntegrity method
} //end Class
