import java.util.EmptyStackException;
import java.util.Vector;

/*
 * CS2400-01
 * Michael Than
 * ID: 012593802
 * This class implements the StacksBooksInterface to show the use of a vector
 */
public class StackBooksVector <T> implements StackBooksInterface<T> {
	private Vector<T>stack; // last element is top of stack
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 1000;
	//default constructor for vector stack
	public StackBooksVector() {
		this(DEFAULT_CAPACITY);
	} // end default constructor
	// constructor for vector stack
	public StackBooksVector(int initialCapacity) {
		integrityOK = false;
		checkCapacity(initialCapacity);
		stack = new Vector<>(initialCapacity);
		integrityOK = true;
	}// end constructor
	// pushes a book on top of the stack
	public void push (T newBook) {
		checkIntegrity();
		stack.add(newBook);
	} // end push
	// removes book from the top of the stack
	public T pop() {
		checkIntegrity();
		if (isEmpty()) {
			throw new EmptyStackException();
		} // end else
		else {
			return stack.remove(stack.size() - 1);
		} // end else
	} // end pop
	// returns book at the top of the stack
	public T peek() {
		checkIntegrity();
		if (isEmpty()) {
			throw new EmptyStackException();
		} // end if
		else {
			return stack.lastElement();
		} // end else
	} // end peek
	// returns true if stack is empty, returns false otherwise
	public boolean isEmpty() {
		checkIntegrity();
		return stack.isEmpty();
	} // end isEmpty
	// removes all books in the stack
	public void clear () {
		checkIntegrity();
		stack.clear();
	} // end clear
// additional methods added to run this class---------------------
	/*
	 * makes sure the capacity of the stack does not go over the maximum amount
	 */
	private void checkCapacity(int capacity) {
		if (capacity>MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create stack with capacity exceeding the max");
	} //end checkCapacity
	/*
	 * checks to see if the object is initialized, if it is not an exception is thrown
	 */
	private void checkIntegrity () {
		if(!integrityOK) {
			throw new SecurityException("Stack is corrupt");
		}
	} //end checkIntegrity method
	
	
	
	
	
	
	
	
	
	
	
}
