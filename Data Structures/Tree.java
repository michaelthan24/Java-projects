

public class Tree<T extends Comparable<? super T>> { 
	private TreeNode<T> root;
	public Tree() {
		root = null;
	} // end default constructor
	public Tree(T rootData, Tree<T> leftTree, Tree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	private void privateSetTree(T rootData, Tree<T> leftTree, Tree<T> rightTree) {
		root = new TreeNode<T>(rootData);
		if ((leftTree!=null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root);
		if ((rightTree!=null)&& !rightTree.isEmpty()) {
			if (rightTree!=leftTree)
				root.setRightChild(rightTree.root);
			else 
				root.setRightChild(rightTree.root.copy());
		} // end if
		if ((leftTree!=null) && (leftTree!=this))
			leftTree.clear();
		if ((rightTree!=null)&& (rightTree!=this))
			leftTree.clear();
	} // end privateSetTree
	public TreeNode<T> getRoot() {
		return root;
	}
	public void setRoot(TreeNode<T> entry) {
		root=entry;
	}
	public void preorder() {
		preorder(root);
		System.out.println();
	} // end preorder
	public void inorder () {
		inorder(root);
		System.out.println();
	} // end in order
	public void postorder() {
		postorder(root);
		System.out.println();
	} // end postorder
	private void postorder(TreeNode<T> root) {
		if (root!=null) {
			postorder(root.getLeftChild());
			postorder(root.getRightChild());
			System.out.print(root.getData()+" ");
		} // end if
	} // end private postorder
	private void preorder(TreeNode<T> root) {
		if (root!=null) {
			System.out.print(root.getData()+" ");
			preorder(root.getLeftChild());
			preorder(root.getRightChild());
		} // end if
	} // end private preorder
	private void inorder (TreeNode <T> root) {
		if(root!=null) {
			inorder(root.getLeftChild());
			System.out.print(root.getData()+ " ");
			inorder(root.getRightChild());
		} // end if
	} // end inorder
	public boolean isEmpty() {
		if(root==null)
			return true;
		else return false;
	} // checks if tree node is empty
	public void clear () {
		root = null;
	} // end clear
	public T add (T newTreeNode) {
		T temp = null;
		if (isEmpty()) // adds to empty tree
			setRoot(new TreeNode<T>(newTreeNode));
		else
			temp = addTreeNode(getRoot(), newTreeNode);
		return temp;
	} // end add
	private T addTreeNode (TreeNode<T> rootNode, T newTreeNode) {
		assert rootNode != null; // assumes root node is not null
		T temp = null;
		int compare = newTreeNode.compareTo(rootNode.getData()); 
		if (compare == 0) { // if the new node is the same as the root node
			temp = rootNode.getData();
			rootNode.setData(newTreeNode);
		} // end if
		else if (compare < 0) { // if the new node is less than the root
			if (rootNode.hasLeftChild())
				temp = addTreeNode(rootNode.getLeftChild(), newTreeNode);
			else 
				rootNode.setLeftChild(new TreeNode<T>(newTreeNode));
		} // end else if
		else { // if the new node is greater than the root
			assert compare > 0;
			if (rootNode.hasRightChild())
				temp = addTreeNode(rootNode.getRightChild(), newTreeNode);
			else 
				rootNode.setRightChild(new TreeNode<T>(newTreeNode));
		} // end else
		return temp;
	} // end addTreeNode
	public boolean containsNode (TreeNode<T> root, T entry) {
		if (root == null)
			return false;
		if (root.getData() == entry)
			return true;
		boolean checkLeft = containsNode(root.getLeftChild(), entry);
		boolean checkRight = containsNode(root.getRightChild(), entry);
		return checkLeft || checkRight;
	}
	private T findEntry(TreeNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode!=null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry)) 
			result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		} // end if
		return result;
	} // end find entry
	public void removeTreeNode (T entry) {
		this.root = removeNode(this.root, entry);
	} // end removeTreeNode
	private TreeNode<T> removeNode(TreeNode<T> root, T entry) {
		if (root==null)
			return root;
		int compare = entry.compareTo(root.getData());
		if (compare < 0) {
			root.setLeftChild(removeNode(root.getLeftChild(), entry));
		} // end if
		else if (compare > 0) {
			root.setRightChild(removeNode(root.getRightChild(), entry));
		} // end else if
		else { 
			if (root.getLeftChild()==null)
				return root.getRightChild();
			else if (root.getRightChild()==null)
				return root.getLeftChild();
			root.setData(lowerNode(root.getRightChild()));
			root.setRightChild(removeNode(root.getRightChild(), root.getData()));
		} // end else
		return root;
	} // end removeNode
	private T lowerNode(TreeNode<T> root) {
		T min = root.getData();
		while (root.getLeftChild()!=null) {
			min = root.getLeftChild().getData();
			root = root.getLeftChild();
		} // end while
		return min;
	} // end lowerNode
	
	public TreeNode<T> findPre (TreeNode<T> root, TreeNode<T> prec, T node) {
		int compare = node.compareTo(root.getData());
		if (root==null)
			return prec;
		if (root.getData()==node) {
			if(root.getLeftChild()!=null) {
				return findMax(root.getLeftChild());
			} // end if
		} // end if
		else if(compare < 0) {
			return findPre(root.getLeftChild(), prec, node);
		} // end else if
		else {
			prec=root;
			return findPre(root.getRightChild(), prec, node);
		} // else
		return prec;
	} // end findPre
	public TreeNode<T> findMax (TreeNode<T> root) {
		while(root.getRightChild()!=null) {
			root = root.getRightChild();
		} // end while
		return root;
	} // end findMax
	public TreeNode<T> findSucc (TreeNode <T> root, T node) {
		if (root==null)
			return null;
		TreeNode<T> next = null;
		TreeNode<T> temp = root;
		while (temp!=null&&temp.getData()!=node) {
			int compare = temp.getData().compareTo(node);
			if (compare > 0) {
				next = temp;
				temp=temp.getLeftChild();
			} // end if
			else {
				temp=temp.getRightChild();
			} // end else
		} // end while
		if (temp==null) 
			return null;
		if (temp.getRightChild()==null)
			return next;
		temp=temp.getRightChild();
		while(temp.getLeftChild()!=null)
			temp=temp.getLeftChild();
		return temp;
	} // end findSucc
} // end tree class
	














