public class ClosedHashTableDouble<E> extends AbstractClosedHashTable<E>{
    public ClosedHashTableDouble(int capacity) {
    	HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(capacity*2)];
    }

    public ClosedHashTableDouble() {
    	HashTable = (HashEntry<E>[]) new HashEntry[nextPrime(DEFAULT_CAPACITY*2)];
    }

    protected int hash(E e, int increment){
    	if(increment % 2 == 1){
    		return (e.hashCode() >> 5 - e.hashCode() + (int) Math.pow(increment,2)) % HashTable.length;
    	}

		return ((e.hashCode() + (int) Math.pow(increment,2)) % HashTable.length) ;
	}
}