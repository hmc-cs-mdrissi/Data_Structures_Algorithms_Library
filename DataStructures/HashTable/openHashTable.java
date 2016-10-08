/* openHashTable.java
 * @author: Mehdi Drissi
 * @date: 2013/11/5
 */
 import java.util.LinkedList;
 import java.util.ArrayList;

public class openHashTable<E,T> {
	private ArrayList<LinkedList<E>> table;
	private int size = 0;
	private int capacity;

    public openHashTable(int capacity) {
    	this.capacity = nextPrime(capacity);
    	table = new ArrayList<LinkedList<E>>();

		for(int i = 0; i < this.capacity; i++){
			table.add(new LinkedList<E>());
		}
    }

    public openHashTable() {
    	table = new ArrayList<LinkedList<E>>();
		capacity = 11;

		for(int i = 0; i < 11; i++){
			table.add(new LinkedList<E>());
		}
    }

    public void insert(E e){
    	int loc = hash(e);

    	if(!table.get(loc).contains(e)){
    		size++;
    		ensureCapacity();
    		table.get(loc).addFirst(e);
    	}
    }

    public int size(){
    	return size;
    }

    public int indexOf(E e){
    	int loc = hash(e);

    	for(int i = 0; i < table.get(loc).size(); i++){
    		if(table.get(loc).get(i).equals(e)){
    			return loc;
    		}
    	}

    	return -1;
    }

    public int numberOfProbes(E e){
    	int loc = hash(e);

    	for(int i = 0; i < table.get(loc).size(); i++){
    		if(table.get(loc).get(i).equals(e)){
    			return i+1;
    		}
    	}

    	return -1;
    }

    private void ensureCapacity(){
    	if(size/table.size() >= 1){
    		capacity = table.size()*2;
    		ArrayList<LinkedList<E>> largerTable = new ArrayList<LinkedList<E>>();

    		for(int i = 0; i < capacity; i++){
				largerTable.add(new LinkedList<E>());
			}

    		for(int i = 0; i < table.size(); i++){
    			for(int j = 0; j < table.get(i).size(); j++){
    				E curr = table.get(i).get(j);
    				int loc = hash(curr);
    				largerTable.get(loc).addFirst(curr);
    			}
    		}

    		table = largerTable;
    	}
    }

    public boolean contains(E e){
    	int loc = hash(e);

    	for(int i = 0; i < table.get(loc).size(); i++){
    		if(table.get(loc).get(i).equals(e)){
    			return true;
    		}
    	}

    	return false;
    }

    public boolean remove(E e){
    	int loc = hash(e);

    	if(table.get(loc).remove(e)){
    		size--;
    		return true;
    	}

    	return false;
    }

    public E get(T t){
    	int loc = hash(t);

    	for(int i = 0; i < table.get(loc).size(); i++){
    		if(table.get(loc).get(i).hashCode() == t.hashCode()){
    			return table.get(loc).get(i);
    		}
    	}

    	return null;
    }

    private int nextPrime(int start){
    	int result = start+1;

    	while(!isPrime(result)){
    		result++;
    	}

    	return result;
    }

    private boolean isPrime(int c){
    	if(c == 1 || c == 0){
    		return false;
    	} else if(c == 2){
    		return true;
    	} else {
    		for(int i = 2; i <= Math.sqrt(c); i++){
    			if(c % i == 0){
    				return false;
    			}
    		}
    	}

    	return true;
    }

    private <U> int hash(U u){
    	int hashVal = u.hashCode() % capacity;
    	return hashVal > 0 ? hashVal : hashVal * -1;
    }
}