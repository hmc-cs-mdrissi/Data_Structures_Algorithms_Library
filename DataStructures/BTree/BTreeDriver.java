/* @(#)BTreeDriver.java
 * @author: Mehdi Drissi
 * @version 1.00 2014/4/9
 */
import java.io.*;
import java.util.*;

public class BTreeDriver {
    public static void main(String[] args) throws IOException{
        BTree test = new BTree();
        HashSet<Integer> valuesInserted = new HashSet<Integer>();

        for(int i = 1; i <= 64; i++){
        	int curr = (int)(Math.random()*1000)+1;

        	test.add(curr);
        	valuesInserted.add(curr);
        	
        	if(test.contains(curr)){
        		System.out.println(i + ". The number " + curr + " is in the BTree.");
        	} else {
        		System.out.println(i + ". The number " + curr + " is not in the BTree.");
        	}
        	
        	System.out.println();
        }
        
        System.out.println();
        System.out.println();
        System.out.println();

        for(Iterator<Integer> i = valuesInserted.iterator(); i.hasNext();){
        	int curr = i.next();

        	//Considering the values to check come from values that were added to the BTree this if should always be true.
        	if(test.contains(curr)){
        		System.out.println("The number " + curr + " is in the BTree.");
        	} else {
        		System.out.println("The number " + curr + " is not in the BTree.");
        	}
        }

        System.out.println();
        System.out.println();
        System.out.println();

        for(int i = 1001; i < 1011; i++){
        	//This should always go the else block since the max number that should have been inserted was 1000.
        	if(test.contains(i)){
        		System.out.println("The number " + i + " is in the BTree.");
        	} else {
        		System.out.println("The number " + i + " is not in the BTree.");
        	}
        }
    }
}
