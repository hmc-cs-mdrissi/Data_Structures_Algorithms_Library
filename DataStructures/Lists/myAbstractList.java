/**
 * @(#)myAbstractList.java
 * @author: Mehdi Drissi
 * @date: 2013/8/27
 */


public abstract class myAbstractList<E> implements myList<E> {
	protected int size = 0;

    protected myAbstractList() {
    }

    protected myAbstractList(E[] objects){
    	for(int i = 0; i < objects.length; i++){
    		add(objects[i]);
    	}
    }

    public void add(E e){
    	add(size,e);
    }

    public boolean isEmpty(){
    	return size == 0;
    }

    public int size(){
    	return size;
    }

    public boolean remove(E e){
    	if(indexOf(e) < 0){
    		return false;
    	} else {
    		remove(indexOf(e));
    		return true;
    	}
    }
}