public class ClosedHashTableQuadratic<E> extends AbstractClosedHashTable<E>{
	public ClosedHashTableQuadratic(int capacity){
		HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(capacity*2)];
	}

	public ClosedHashTableQuadratic(){
		HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(DEFAULT_CAPACITY*2)];
	}

	protected int hash(E e, int increment){
		return ((e.hashCode() + (int) Math.pow(increment,2))% HashTable.length) ;
	}
}
