import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
	private Node topNode; //references the first node 
	// default constructor for linked stack
	public LinkedStack () {
		topNode = null;
	} // end default constructor
	// pushes a new node onto the stack
	public void push (T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} // end push
	// removes the top node on the stack, returns the node that was at the top
	public T pop () {
		T top = peek();
		if (topNode != null) {
			topNode = topNode.getNext();
		} // end if
		return top;
	} // end pop
	// returns the node at the top of the stack
	public T peek () {
		if (isEmpty()) {
			throw new EmptyStackException();
		} // end if
		else {
			return topNode.getData();
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
		private class Node {
			private T data; 
			private Node next;
			private Node (T data, Node next) {
				this.data=data;
				this.next=next;
			}
			private Node (T data) { // default constructor
				this(data,null);
			}
			private T getData() {
				return data;
			}
			private void setData(T newData) {
				data=newData;
			} //end setTitle
			private Node getNext() {
				return next;
			}
			private void setNext (Node next) {
				this.next=next;
			}
		} //end BookNode
}
