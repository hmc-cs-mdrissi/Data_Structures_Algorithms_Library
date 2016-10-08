/**
 * @(#)myArrayList.java
 * @author: Mehdi Drissi
 * @date: 2013/8/27
 */


public class myArrayList<E> extends myAbstractList<E> {
	public static final int INITIAL_CAPACITY = 10;
	private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public myArrayList() {
    }

    public myArrayList(E[] objects){
    	for(int i = 0; i < objects.length; i++){
    		add(objects[i]);
    	}
    }

    public void add(int index, E e){
    	size++;

    	if(index > size){
    		index = size;
    	}

    	ensureCapacity();
    	E curr = data[index];
    	data[index] = e;

    	for(int i = index+1; i < size; i++){
    		E next = data[i];
    		data[i] = curr;
    		curr = next;
    	}
    }

    private void ensureCapacity(){
    	if(size <= data.length){
    			return;
    	}

    	E[] temp = (E[]) new Object[(size-1)*2];
    	for(int i = 0; i < data.length; i++){
    		temp[i] = data[i];
    	}

    	data = temp;
    }

    public void clear(){
    	data = (E[]) new Object[INITIAL_CAPACITY];
    	size = 0;
    }

    public boolean contains(E e){
    	return indexOf(e) != -1;
    }

    public E get(int index){
    	return data[index];
    }

    public int indexOf(E e){
    	for(int i = 0; i < size; i++){
    		if(data[i].equals(e)){
    			return i;
    		}
    	}
    	return -1;
    }

    public int lastIndexOf(E e){
    	for(int i = size-1; i >= 0; i--){
    		if(data[i].equals(e)){
    			return i;
    		}
    	}
    	return -1;
    }

    public E remove(int index){
    	E curr = data[size-1];

    	data[--size] = null;

    	E next = data[index];

    	for(int i = size; i > index; i--){
    		next = data[i-1];
    		data[i-1] = curr;
    		curr = next;
    	}

    	return next;
    }

    public E set(int index, E e){
    	E past = data[index];
    	data[index] = e;
    	return past;
    }

    public String toString(){
    	String result = "[";

    	for(int i = 0; i < size-1; i++){
    		result += data[i] + ", ";
    	}

    	result += data[size-1] + "]";

    	return result;
	}

}