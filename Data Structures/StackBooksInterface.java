/*
 * CS 2400-01
 * Michael Than
 * ID: 012593802
 * Stack interface includes all the methods to be implemented by the array and 
 * linked nodes of the stack
 */
public interface StackBooksInterface <T> {
	// adds new book to top of the stack, parameter takes in new book
	public void push (T book);
	
	//removes a book from the top of the stack, returns the object at the top of the stack
	public T pop();
	
	//retrieves the book at the top of the stack, returns the book at the top
	public T peek();
	
	//checks to see if stack is empty, returns true if stack is empty
	public boolean isEmpty();
	
	//removes all books from the stack
	public void clear();
	
}
