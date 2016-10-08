import java.util.*;

public class ClosedHashTableDriver {
    public static void main(String[] args) {
    	ClosedHashTableLinear<Integer> test1 = new ClosedHashTableLinear<Integer>(1000);
    	ClosedHashTableQuadratic<Integer> test2 = new ClosedHashTableQuadratic<Integer>(500);
    	ClosedHashTableQuadratic<Integer> test3 = new ClosedHashTableQuadratic<Integer>(1000);
    	ClosedHashTableDouble<Integer> test4 = new ClosedHashTableDouble<Integer>(1000);
    	ClosedHashTableDouble<Integer> test5 = new ClosedHashTableDouble<Integer>(500);
    	ClosedHashTableLinear<Integer> test6 = new ClosedHashTableLinear<Integer>(500);
		ArrayList<Integer> addedElements = new ArrayList<Integer>();

    	for(int i = 1; i <= 10; i++){
    		for(int j = 0; j < 100; j++){
    			int curr = (int)(Math.random()*10000)+1;
				test1.insert(curr);
				test2.insert(curr);
				test3.insert(curr);
				test4.insert(curr);
				test5.insert(curr);
				test6.insert(curr);
				addedElements.add(curr);
    		}

    		System.out.println("The small linear hash table after adding " + i*100 + " integers has a load factor of " + test6.loadingFactor() + ".");
    		System.out.println("The big linear hash table after adding " + i*100 + " integers has a load factor of " + test1.loadingFactor() + ".");
    		System.out.println("The quadratic small hash table after adding " + i*100 + " integers has a load factor of " + test2.loadingFactor() + ".");
    		System.out.println("The quadratic big hash table after adding " + i*100 + " integers has a load factor of " + test3.loadingFactor() + ".");
    		System.out.println("The small double hash table after adding " + i*100 + " integers has a load factor of " + test5.loadingFactor() + ".");
    		System.out.println("The big double hash table after adding " + i*100 + " integers has a load factor of " + test4.loadingFactor() + ".");


    		System.out.println();

    		System.out.println("The average number of probes for the small linear hash table after adding " + i*100 + " integers is " + test6.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");
    		System.out.println("The average number of probes for the big linear hash table after adding " + i*100 + " integers is " + test1.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");
    		System.out.println("The average number of probes for the small quadratic hash table after adding " + i*100 + " integers is " + test2.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");
    		System.out.println("The average number of probes for the big quadratic hash table after adding " + i*100 + " integers is " + test3.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");
    		System.out.println("The average number of probes for the small double hash table after adding " + i*100 + " integers is " + test5.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");
    		System.out.println("The average number of probes for the big double hash table after adding " + i*100 + " integers is " + test4.avgNumberOfProbes(addedElements,0,addedElements.size()-1) + ".");

			System.out.println();
			System.out.println("The average number of probes for the small linear hash table for the last 100 items is " + test6.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");
			System.out.println("The average number of probes for the big linear hash table for the last 100 items is " + test1.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");
			System.out.println("The average number of probes for the small quadratic hash table for the last 100 items is " + test2.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");
    		System.out.println("The average number of probes for the big quadratic hash table for the last 100 items is " + test3.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");
    		System.out.println("The average number of probes for the small double hash table for the last 100 items is " + test5.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");
    		System.out.println("The average number of probes for the big double hash table for the last 100 items is " + test4.avgNumberOfProbes(addedElements,(i-1)*100,(i-1)*100+99) + ".");

    		System.out.println();
			System.out.println("The number of collisions for the small linear hash table for the last 100 items is " + test6.getRecentCollisions()  + ".");
			System.out.println("The number of collisions for the big linear hash table for the last 100 items is " + test1.getRecentCollisions() + ".");
			System.out.println("The number of collisions for the small quadratic hash table for the last 100 items is " + test2.getRecentCollisions() + ".");
    		System.out.println("The number of collisions for the big quadratic hash table for the last 100 items is " + test3.getRecentCollisions() + ".");
    		System.out.println("The number of collisions for the small double hash table for the last 100 items is " + test5.getRecentCollisions() + ".");
    		System.out.println("The number of collisions for the big double hash table for the last 100 items is " + test4.getRecentCollisions() + ".");

 			test1.resetCollisions();
 			test2.resetCollisions();
 			test3.resetCollisions();
 			test4.resetCollisions();
 			test5.resetCollisions();
 			test6.resetCollisions();

 			System.out.println();
 			System.out.println("The number of failed inserts for small quadratic hash table for the last 100 items is " + test2.getFailedInsert() + ".");
 			System.out.println();

 			test2.resetFailedInserts();
    	}

    }
}