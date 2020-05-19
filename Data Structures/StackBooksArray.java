import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * CS2400-01
 * Michael Than
 * ID:012593802
 * This class implements the StackBooksInterface to show the resizable array of the 
 * stack 
 */
public class StackBooksArray <T> implements StackBooksInterface<T> {
	private T[] stack;
	private static final int DEFAULT_CAPACITY = 25;
	private int topIndex;
	private boolean integrityOK=false;
	private int MAX_CAPACITY = 1000;
	
	// default constructor that creates a stack with 25 capacity
	public StackBooksArray () {
		this(DEFAULT_CAPACITY);
	} // end default constructor
	// constructor that creates a stack of books with a capacity given by the user
	public StackBooksArray (int capacity) {
		if (capacity<=MAX_CAPACITY) { 
			@SuppressWarnings("unchecked") //cast is safe because the new array contains null entries
			T[] tempStack = (T[])new Object[capacity];//unchecked cast
			stack=tempStack;
			topIndex=-1;
			integrityOK = true; 
			}
			else //throws an exception if there are more books that the MAXIMUM_CAPACITY
				throw new IllegalStateException ("Attempt to create a stack with capacity that is too high, stack will reach the ceiling");
		}//end constructor

	//Implemented methods-----------------
	/*Pushes a new book on the top of the stack, which is at the end of the array
	 *parameter takes in the book to be added 
	 */
	public void push(T book) {
		checkIntegrity();
		ensureCapacity();
		stack[topIndex+1] = book;
		topIndex++;
		}//end push
	/*
	 *removes a book from the top of the stack
	 *returns the book that was at the top of the stack
	 */
	public T pop() {
		checkIntegrity();//checks to see if object is initialized
		if (isEmpty())
			throw new EmptyStackException();
			else {
				T newTop = stack[topIndex];
				stack[topIndex] = null;
				topIndex--;
				return newTop;
			} // end else
		} //end pop method
	/*
	 * Returns the book at the top of the stack  
	 */
	public T peek() {
		checkIntegrity();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return stack[topIndex];
	} // end peek
	/* checks to see if stack is empty
	 * returns true if stack is empty, false if not empty
	 */
	public boolean isEmpty() {
		return topIndex < 0;
	}//end isEmpty method
	/*
	 * clears all books in the stack, from top to bottom
	 */
	public void clear() {
		checkIntegrity();
		while (topIndex > -1) {
			stack[topIndex] = null;
			topIndex--;
		} // end while
	}//end clear method
// additional methods added to run this class---------------------
/*
 * makes sure the capacity of the stack does not go over the maximum amount
 */
private void checkCapacity(int capacity) {
	if (capacity>MAX_CAPACITY)
		throw new IllegalStateException("Attemt to create stack with capacity exceeding the max");
} //end checkCapacity
/*
 * checks to see if the object is initialized, if it is not an exception is thrown
 */
private void checkIntegrity () {
	if(!integrityOK) {
		throw new SecurityException("Stack is corrupt");
	}
} //end checkIntegrity method
/*
 * checks to make sure array is still valid, doubles in size if array is full
 */
private void ensureCapacity() { 
	if (topIndex >= stack.length) {
		int newLength = 2 * stack.length;
		checkCapacity(newLength);
		stack = Arrays.copyOf(stack, newLength);
	} // end iff
} // end ensureCapacity






}

