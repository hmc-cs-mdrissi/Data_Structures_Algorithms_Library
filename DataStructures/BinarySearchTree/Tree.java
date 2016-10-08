package tree;

/* @Tree.java
 * @author: Mehdi Drissi
 * @date: 2013/10/17
 */


public interface Tree<E extends Comparable<E>>{
    public boolean search(E e);

    public boolean insert(E e);

    public boolean delete(E e);

    public String inOrder();

    public String postOrder();

    public String preOrder();

    public int getSize();

    public boolean isEmpty();

    public java.util.Iterator<E> iterator();
}
