package tree;

public class Driver {
	public static void main(String[] args) {
		BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();
		
		test.insert(5);
		test.insert(3);
		test.insert(7);
		test.insert(1);
		test.insert(4);
		test.insert(11);
		
		System.out.println(test.getSize());
		System.out.println(test.inOrder());
		System.out.println(test.preOrder());
		System.out.println(test.postOrder());
	}

}
