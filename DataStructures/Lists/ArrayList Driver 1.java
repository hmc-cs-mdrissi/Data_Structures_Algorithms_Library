/**
 * @(#)Test.java
 * @author: Mehdi Drissi
 * @date: 2013/8/27
 */

public class Test {
    public static void main(String[] args) {
    	myArrayList check = new myArrayList();

    	for(int i = 0; i <= 10; i++){
    		check.add(i);
    	}

    	System.out.println(check);

    	for(int i = 0; i <= 4; i++){
    		check.add(5,0);
    	}

    	System.out.println(check);
    }
}
