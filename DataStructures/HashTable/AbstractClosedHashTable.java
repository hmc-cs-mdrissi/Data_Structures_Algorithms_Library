import java.util.*;

public abstract class AbstractClosedHashTable<E> {
    protected HashEntry<E>[] HashTable;
	protected int size = 0;
	protected int deleted = 0;
	public static final int DEFAULT_CAPACITY = 100;
	protected int failedInsert = 0;
	protected int recentCollisions = 0;

	protected static class HashEntry<E>{
		boolean active = false;
		E element;

		private HashEntry(E e){
			element = e;
		}
	}

	protected int findPos(E e){
		int increment = 0;
		int currPos = hash(e, increment);

		if(HashTable[currPos] == null || HashTable[currPos].element.equals(e)){
			return currPos;
		}

		E firstElement = HashTable[currPos].element;

		do{
			increment++;
			currPos = hash(e, increment);
			recentCollisions++;
		}while(HashTable[currPos] != null && !HashTable[currPos].element.equals(e) && !HashTable[currPos].equals(firstElement));

		if(HashTable[currPos] == null || HashTable[currPos].element.equals(e)){
			return currPos;
		}

		return -1;
	}

	public int size(){
		return size;
	}

	public int getFailedInsert(){
		return failedInsert;
	}

	public int getRecentCollisions(){
		return recentCollisions;
	}

	public void resetCollisions(){
		recentCollisions = 0;
	}

	public void resetFailedInserts(){
		failedInsert = 0;
	}

	protected int numberOfProbes(E e){
		if(indexOf(e) == -1){
			return -1;
		}

		int increment = 0;
		int currPos = hash(e, increment);

		while(!HashTable[currPos].element.equals(e)){
			increment++;
			currPos = hash(e, increment);
		}

		return increment+1;
	}

	public double avgNumberOfProbes(ArrayList<E> areaToCheck, int start, int end){
		double avgProbes = 0;

		for(int i = start; i <= end; i++){
			avgProbes += numberOfProbes(areaToCheck.get(i));
		}

		return avgProbes/(end-start+1);
	}

	protected void insert(E e){
		int pos = findPos(e);

		if(pos == -1 || HashTable[pos] != null && HashTable[pos].element.equals(e) && HashTable[pos].active){
			if(pos == -1){
				failedInsert++;
			}
			return;
		}

		size++;

		if(HashTable[pos] != null && HashTable[pos].element.equals(e)){
			deleted--;
			HashTable[pos].active = true;
			return;
		}

		HashTable[pos] = new HashEntry<E>(e);
		HashTable[pos].active = true;
	}

	protected int indexOf(E e){
		int pos = findPos(e);

		if(pos == -1 || HashTable[pos] == null || !HashTable[pos].active){
			return -1;
		}

		return pos;
	}

	protected boolean delete(E e){
		int pos = findPos(e);

		if(pos == -1 || (HashTable[pos] != null && !HashTable[pos].active)){
			return false;
		}

		HashTable[pos].active = false;
		deleted++;
		size--;
		return true;
	}

	protected double loadingFactor(){
		return size/(double)HashTable.length;
	}

	protected abstract int hash(E e, int increment);

	protected int nextPrime(int start){
    	int result = start+1;

    	while(!isPrime(result)){
    		result++;
    	}

    	return result;
    }

    protected boolean isPrime(int c){
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
}