/**
 * @(#)Test2.java
 * @author: Mehdi Drissi
 * @date: 2013/8/28
 */

public class Test2 {
    public static void main(String[] args) {
    	myArrayList check = new myArrayList();

		for(int x = 0; x <= 2; x++){
    		for(int i = 0; i <= 10; i++){
    			check.add(i);
    		}
    	}

    	for(int i = 0; i <= 10; i+=2){
    		if(check.contains(i)){
    			System.out.println("The element " + i + " is in the myArrayList check.");
    			System.out.println("The first index of " + i + " is " + check.indexOf(2*i) + ".");
    			System.out.println("The last index of " + i + " is " + check.lastIndexOf(2*i) + "." + "\n");
    		} else {
    			System.out.println("The element " + i + " is not in the myArrayList check.");
    		}
    	}

    	System.out.println("The toString method works like getting each elemenet and adding a few symbols. The next two things printed look identical but were done in different ways.");

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

    	check.remove(5);
    	check.remove(10);
    	check.remove(15);

    	System.out.println("\nLastly here you can see the effect of removing three elements from different indices.");
    	System.out.println(check);
    }
}
