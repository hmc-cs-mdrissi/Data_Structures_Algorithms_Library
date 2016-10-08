package tree;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {
	private static class TreeNode<E extends Comparable<E>> {
		private E element;
		private TreeNode<E> left;
		private TreeNode<E> right;

		private TreeNode(E e) {
			element = e;
		}

		public String toString() {
			return element.toString();
		}
	}

	protected TreeNode<E> root;

	public BinarySearchTree() {

	}

	public BinarySearchTree(E[] objects) {
		for (E obj : objects) {
			insert(obj);
		}
	}

	// Simply used to help multiple methods to work as desired by creating subtrees needed.
	private BinarySearchTree(TreeNode<E> root) {
		this.root = root;
	}

	public boolean insert(E e) {
		if (isEmpty()) {
			size++;
			root = new TreeNode<E>(e);
			return true;
		}

		TreeNode<E> par = null;
		TreeNode<E> curr = root;

		while (curr != null) {
			if (curr.element.compareTo(e) == 0) {
				return false;
			} else if (curr.element.compareTo(e) > 0) {
				par = curr;
				curr = curr.left;
			} else {
				par = curr;
				curr = curr.right;
			}
		}

		size++;
		if (par.element.compareTo(e) > 0) {
			par.left = new TreeNode<E>(e);
			return true;
		} else {
			par.right = new TreeNode<E>(e);
			return true;
		}
	}

	public boolean delete(E e) {
		TreeNode<E> par = null;
		TreeNode<E> curr = root;
		boolean left = false;
		
		while (curr != null) {
			if (curr.element.compareTo(e) > 0) {
				par = curr;
				curr = curr.left;
				left = true;
			} else if (curr.element.compareTo(e) > 0) {
				par = curr;
				curr = curr.right;
				left = false;
			} else {
				break;
			}
		}
		
		if(curr == null){
			return false;
		}
		
		if(curr.left == null && curr.right == null){
			if (left){
				par.left = null;
			} else {
				par.right = null;
			}
		} else if (curr.left == null || curr.right == null){
			if (left){
				par.left = curr.left != null ? curr.left : curr.right;
			} else {
				par.right = curr.left != null ? curr.left : curr.right;
			}
		} else {
			BinarySearchTree<E> partition = new BinarySearchTree<E>(curr.left);
			E maxArea = partition.findMax();
			curr.element = maxArea;
			partition.delete(maxArea);
		}
		
		return true;
	}

	public boolean search(E e) {
		TreeNode<E> curr = root;

		while (curr != null) {
			if (curr.element.compareTo(e) > 0) {
				curr = curr.left;
			} else if (curr.element.compareTo(e) > 0) {
				curr = curr.right;
			} else {
				return true;
			}
		}

		return false;
	}

	public String inOrder() {
		if (root == null) {
			return "";
		} else {
			return new BinarySearchTree<E>(root.left).inOrder()
					+ root.toString() + " "
					+ new BinarySearchTree<E>(root.right).inOrder();
		}
	}

	public String postOrder() {
		if (root == null) {
			return "";
		} else {
			return new BinarySearchTree<E>(root.left).postOrder()
					+ new BinarySearchTree<E>(root.right).postOrder()
					+ root.toString() + " ";
		}
	}

	public String preOrder() {
		if (root == null) {
			return "";
		} else {
			return root.toString() + " "
					+ new BinarySearchTree<E>(root.left).preOrder()
					+ new BinarySearchTree<E>(root.right).preOrder();
		}
	}

	public E findMax(){
		TreeNode<E> curr = root;
		
		while(curr.right != null){
			curr = curr.right;
		}
		
		return curr.element;
	}
	
	public E findMin(){
		TreeNode<E> curr = root;
		
		while(curr.left != null){
			curr = curr.left;
		}
		
		return curr.element;
	}
	
	//Not yet written which is why it simply returns null at the moment.
	public java.util.Iterator<E> iterator() {
		return null;
	}
}
