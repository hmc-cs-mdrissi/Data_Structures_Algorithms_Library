/**
 * @(#)myLinkedList.java
 * @author: Mehdi Drissi
 * @Date: 2013/9/4
 */


public class myLinkedList<E> extends myAbstractList<E>{
	private Node<E> head;
	private Node<E> tail;

    public myLinkedList() {
    }

    public myLinkedList(E[] objects){
    	for(int i = 0; i < objects.length; i++){
    		add(objects[i]);
    	}
    }

    public void add(int index, E e){
    	if(index > size){
    		System.out.println("You can not add to this list with an index greater than size.");
    		return;
    	}

    	if(index == 0 || size == 0){
    		addFirst(e);
    		return;
    	}

    	if(index == size){
    		addLast(e);
    		return;
    	}

    	size++;

    	int i = 1;
    	Node<E> curr = head;
    	Node<E> toAdd = new Node<E>(e);

    	while(curr != null){
    		if(i == index){
    			curr.next = toAdd;
    			return;
    		}

    		curr = curr.next;
    		i++;
    	}
    }

    public void addFirst(E e){
    	size++;

    	if(head == null){
    		head = tail = new Node<E>(e);
    		return;
    	}

    	Node<E> newNode = new Node<E>(e);
    	newNode.next = head;
    	head = newNode;
    }

    public void addLast(E e){
    	size++;

    	if(head == null){
    		head = tail = new Node<E>(e);
    		return;
    	}

    	Node<E> newNode = new Node<E>(e);
    	tail.next = newNode;
    	tail = newNode;

    }

    public void clear(){
    	head = null;
    	tail = null;
    	size = 0;
    }

    public boolean contains(E e){
    	return (indexOf(e) >= 0);
    }

	public E get(int index){
		if(index == 0){
			return getFirst();
		}

		if(index == size-1){
			return getLast();
		}

		Node<E> curr = head;

    	for(int i = 0; i != index; i++){
    		curr = curr.next;
    	}

    	return curr.data;
	}

	public int indexOf(E e){
		Node<E> curr = head;
		int i = 0;

    	while(curr != null){
    		if(curr.data == e){
    			return i;
    		}
    		curr = curr.next;
    		i++;
    	}
    	return -1;
	}

	public int lastIndexOf(E e){
		Node<E> curr = head;
		int[] locs = new int[size];
		int i = 0;

    	while(curr != null){
    		if(curr.data == e){
    			locs[i] = 1;
    		}
    		curr = curr.next;
    		i++;
    	}

    	for(i = locs.length-1; i >= 0; i--){
    		if(locs[i] == 1){
    			return i;
    		}
    	}

    	return -1;
	}

    public E set(int index, E e){
    	if(index >= size){
			System.out.println("You can not set an element from this list to" +
				" an index greater than or equal to size.");
			return null;
		}

    	Node<E> curr = head;

    	for(int i = 0; i != index; i++){
    		curr = curr.next;
    	}

		E ret = curr.data;
    	curr.data = e;

    	return ret;
    }

	public E remove(int index){
		if(index >= size){
			System.out.println("You can not remove from this list with an index greater than or equal to size.");
			return null;
		}

		size--;

		if(index == 0){
			return removeFirst();
		}

		if(index == size-1){
			return removeLast();
		}

		E temp;

		Node<E> curr = head;

		for(int i = 0; i != index-1; i++){
    		curr = curr.next;
		}

		temp = (E) curr.next.data;
		curr.next = curr.next.next;
    	return temp;
	}

	public E removeFirst(){
			E temp = head.data;
			head = head.next;
			return temp;
	}

	public E removeLast(){
		Node<E> curr = head;
		Node<E> prev = null;

		while(curr.next != null){
			prev = curr;
			curr = curr.next;
		}

		prev.next = null;
		tail = prev;

		return curr.data;
	}

	public E getLast(){
		if(size != 0){
			return tail.data;
		}
		return null;
	}

	public E getFirst(){
		if(size != 0){
			return head.data;
		}
		return null;
	}

	public String toString(){
		if(size == 0){
			return "";
		}

		String result = "[";
		Node<E> curr = head;

		while(curr.next != null){
			result += curr.data + ", ";
			curr = curr.next;
		}

		return result + curr.data + "]";
	}

    static class Node<E>{
    	private E data;
    	private Node next;

    	private Node(E e){
    		data = e;
    	}
    }
}