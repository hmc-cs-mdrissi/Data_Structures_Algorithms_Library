/**
 * @(#)Test3.java
 *
 *
 * @author
 * @version 1.00 2013/9/5
 */

public class Test3 {
    public static void main(String[] args) {
    	myLinkedList check = new myLinkedList();

		for(int x = 0; x <= 2; x++){
    		for(int i = 0; i <= 10; i++){
    			check.add(i);
    		}
    	}

    	for(int i = 0; i <= 10; i+=2){
    		if(check.contains(2*i)){
    			System.out.println("The element " + 2*i + " is in the myLinkedList check.");
    			System.out.println("The first index of " + 2*i + " is " + check.indexOf(2*i) + ".");
    			System.out.println("The last index of " + 2*i + " is " + check.lastIndexOf(2*i) + "." + "\n");
    		} else {
    			System.out.println("The element " + 2*i + " is not in myLinkedList check.\n");
    		}
    	}

    	System.out.println("The toString method works like getting each element and adding a few symbols. The next two things printed look identical but were done in different ways.");

    	System.out.println(check);
    	System.out.print("[");
    	for(int i = 0; i < check.size()-1; i++){
    		System.out.print(check.get(i) + ", ");
    	}
    	System.out.print(check.get(check.size()-1) + "]");

    	for(int i = 0; i <= 4; i++){
    		check.set(2*i,i);
    	}

    	System.out.println("\n\nHere you can see the effect of setting the first 5 elements with even index to half of their value.");
    	System.out.println(check);

    	check.remove(0);
    	check.remove(10);
    	check.remove(check.size()-1);

    	System.out.println("\nHere you can see the effect of removing three elements from different indices.");
    	System.out.println(check);

    	System.out.println("\nNow you'll see the effect of calling getFirst, getLast, removeFirst, and removeLast directly a couple times.\n");

    	for(int i = 0; i < 5; i++){
    		System.out.println("The current first element is " + check.getFirst());
    		System.out.println("The current last element is " + check.getLast());
    		check.removeFirst();
    		check.removeLast();
    	}

    	System.out.println("\nNow you'll see the effect of calling getFirst, getLast, addFirst, and addLast directly a couple times.\n");

    	for(int i = 0; i < 5; i++){
    		check.addFirst(i*3);
    		check.addLast(i*5);
    		System.out.println("The current first element is " + check.getFirst());
    		System.out.println("The current last element is " + check.getLast());
    	}

    	System.out.println("\nThe final myLinkedList check looks like: " + check);
    }
}
