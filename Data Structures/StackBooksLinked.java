import java.util.EmptyStackException;

/*
 * CS2400-01
 * Michael Than
 * ID: 012593802
 * This class implements the StackBooksInterface to show linked nodes of a stack
 */
public class StackBooksLinked <T> implements StackBooksInterface<T> {
	private BookNode topNode; //references the first node 
	// default constructor for linked stack
	public StackBooksLinked () {
		topNode = null;
	} // end default constructor
	// pushes a new book onto the stack
	public void push (T newBook) {
		BookNode newNode = new BookNode(newBook, topNode);
		topNode = newNode;
	} // end push
	// removes the top book on the stack, returns the book that was at the top
	public T pop () {
		T topBook = peek();
		if (topNode != null) {
			topNode = topNode.getNextBook();
		} // end if
		return topBook;
	} // end pop
	// returns the book at the top of the stack
	public T peek () {
		if (isEmpty()) {
			throw new EmptyStackException();
		} // end if
		else {
			return topNode.getTitle();
		} // end else
	} // end peek
	// returns true if stack is empty, false otherwise
	public boolean isEmpty() {
		return topNode == null;
	} // end isEmpty
	// removes all nodes in the chain
	public void clear() {
		topNode = null;
	} // end clear;
	//private inner class of nodes
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
	
	
	
	
	
	
	
}
