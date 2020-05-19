/*
 * CS2400-01
 * Michael Than
 *  Bronco ID: 012593802
 * This interface includes the methods to implemented by
 * the array pileOfBooks and the linked pileOfBooks
 */
public interface PileOfBooksBag<T> {
	
	public int getCurrentSize();
	// gets the number of books in pile
	// returns the number of books
	public boolean isEmpty();
	//checks if pile is empty
	//returns true if bag is empty
	public boolean add(T newEntry);
	//adds a new book
	//returns true if book is added successfully
	public T remove();
	//removes a book
	//returns the removed book
	//does not have a remove method for specified books because you can only remove and 
	//add to the top of the pile
	public void clear();
	//clears the pile
	public int getFrequencyOf(T anEntry);
	// counts the number of times a certain book appears in the pile
	//returns the number of times that books appears
	public boolean contains (T anEntry);
	//checks if a book is in pile
	//returns true if it is
	public T[] toArray();
	//turns the pile into an array
	//then returns that array
}
