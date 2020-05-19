
public class TreeNode<T> {
			private T data;
			private TreeNode<T> leftChild;
			private TreeNode<T> rightChild;
			private TreeNode<T> next; // node link to the next node
			//constructor for node with a linked node
			public TreeNode (T data, TreeNode<T> newLeft, TreeNode<T> newRight) {
				this.data=data;
				leftChild=newLeft;
				rightChild=newRight;
			}//constructor for book without a linked node
			public TreeNode (T data) { 
				this(data,null,null);
			}// end constructor
		//-------getters/setters/has
			public T getData() {
				return data;
			} //end getData
			public void setData(T newData) {
				data=newData;;
			} //end setData
			public TreeNode<T> getLeftChild () {
				return leftChild;
			} // end getLeftChild
			public void setLeftChild(TreeNode<T> newLeft) {
				leftChild=newLeft;
			} // end setLeftChild
			public TreeNode<T> getRightChild() {
				return rightChild;
			} // end getRightChild
			public void setRightChild(TreeNode<T> newRight) {
				rightChild=newRight;
			} // end setRightChild
			public TreeNode<T> getNext() {
				return next;
			} //end getNext
			public void setNext(TreeNode<T> next) {
				this.next=next;
			} //end setNext
		//------methods/functions
			public boolean hasLeftChild() {
				return leftChild!=null;
			} // end hasLeftChild
			public boolean hasRightChild() {
				return rightChild!=null;
			} // end hasRightChild		
			public boolean isLeaf() {
				return (leftChild==null && rightChild==null);
			} // check to see if this tree node is a leaf
			public TreeNode<T> copy() {
				TreeNode<T> newRoot = new TreeNode<T>(data);
				if (leftChild!=null)
					newRoot.setLeftChild(leftChild.copy());
				if (rightChild!=null)
					newRoot.setRightChild(rightChild.copy());
				return newRoot;
			} // copys a subtree at root
					
		} // end tree node class
