package tree;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E>{
	protected int size = 0;

    public int getSize(){
    	return size;
    }

    public boolean isEmpty(){
    	return size == 0;
    }
}
