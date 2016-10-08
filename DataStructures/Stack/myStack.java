/**
 * @(#)myStack.java
 * @author
 * @version 1.00 2013/9/25
 */
import java.util.LinkedList;

public class myStack<E>{
	private LinkedList<E> data = new LinkedList<E>();
	private int capacity;

	public myStack(int capacity){
		this.capacity = capacity;
	}

	public boolean push(E e) throws StackException{
		if(!isFull()){
			data.addLast(e);
			return true;
		}
		throw new StackException("You cannot add to a full stack.");
	}

	public boolean isEmpty(){
		return data.size() == 0;
	}

	public boolean isFull(){
		return data.size() == capacity;
	}

	public E pop() throws StackException{
		if(!isEmpty()){
			return data.removeLast();
		}
		throw new StackException("You cannot remove from an empty stack.");
	}

public E peek() throws StackException{
		if(!isEmpty()){
			return data.getLast();
		}
		throw new StackException("You cannot get an item from an empty stack.");
	}

	public String toString(){
		return data.toString();
}
}
