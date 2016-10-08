/* Driver.java
 * @author: Mehdi Drissi
 * @date: 2013/11/6
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException{
        //In a practical problem what the hashTable stores and what the key is should be different types with the key having all the variables that determine what is stored hashCode.
        openHashTable<String,String> test = new openHashTable<String,String>(50);
		Scanner readProg1 = new Scanner(new FileReader("openHashTable.java"));
		Scanner readProg2 = new Scanner(new FileReader("openHashTable.java"));
		Scanner readProg3 = new Scanner(new FileReader("openHashTable.java"));

        while(test.size() < 50){
        	String curr = readProg1.next();
        	System.out.println("The word " + curr + " is the next word to add to the table");
        	test.insert(curr);
        }

        System.out.println();

		for(int i = 0; i < test.size(); i++){
			String curr = readProg2.next();
			System.out.println("The location in the table of " + curr + " is " + test.indexOf(curr));
		}

		System.out.println();

		while(test.size() < 100){
        	String curr = readProg1.next();
        	System.out.println("The word " + curr + " is the next word to add to the table");
        	test.insert(curr);
        }

        System.out.println();

        double averageProbes = 0;

        for(int i = 0; i < test.size(); i++){
			String curr = readProg3.next();
			System.out.println("The location of " + curr + " in the table is now " + test.indexOf(curr));
			System.out.println("The number of probes for " + curr + " is " + test.numberOfProbes(curr));
			System.out.println();
			averageProbes += test.numberOfProbes(curr);
		}

		System.out.println("The average number of probes is " + averageProbes/test.size());
    }
}
