public class ClosedHashTableLinear<E> extends AbstractClosedHashTable<E>{
	public ClosedHashTableLinear(int capacity){
		HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(capacity*2)];
	}

	public ClosedHashTableLinear(){
		HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(DEFAULT_CAPACITY*2)];
	}

	protected int hash(E e, int increment){
		return ((e.hashCode() + increment)% HashTable.length) ;
	}
}
