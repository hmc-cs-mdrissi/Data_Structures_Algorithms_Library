/* @Author: Mehdi Drissi
 * @Date: January 22nd, 2013
 */

 import java.util.Scanner;

public class KMPAlgorithm {
	public static int counter = 0;

    public static void main(String[] args) {
        String pattern = makeACGT(8);
        int[] failureTable = makeFailure(pattern);

        System.out.println("The dna sequence being searched for is " + pattern);
        System.out.println("The dna sequence's failure table is:");


        for(int i = 0; i < failureTable.length; i++){
        	System.out.print(failureTable[i] + " ");
        }

        System.out.println();

        for(int i = 0; i < 10000; i++){
        	String text = makeACGT(1000);
        	KMP(text,pattern,i*1000);
        }

		System.out.println("The pattern was located a total of " + counter + " times.");
    }

    public static String makeACGT(int length){
    	String result = "";

    	for(int i = 0; i < length; i++){
    		int letter = (int)(Math.random()*4);

    		switch(letter){
    			case 0: result += "A"; break;
    			case 1: result += "C"; break;
    			case 2: result += "G"; break;
    			case 3: result += "T";
    		}
    	}

    	return result;
    }

    public static void KMP(String text, String pattern,int offset){
    	int i = 0;
    	int j = 0;
    	int[] failureTable = makeFailure(pattern);


    	while(i < text.length()){
    		if(text.charAt(i) == pattern.charAt(j)){
    			i++;
    			j++;

    			if(j == pattern.length()){
    				counter++;

    				if(counter % 10 == 0){
    					System.out.println();
    				}

    				System.out.print((i-j+offset) + " ");
    				j = 0;
    			}
    		} else if (j != 0){
    			j = failureTable[j-1];
    		} else {
    			i++;
    		}
    	}
    }

	public static int[] makeFailure(String pattern){
		int[] result = new int[pattern.length()];
		result[0] = 0;

		int i = 0;
		int j = 1;

		while(j < pattern.length()){
			if(pattern.charAt(i) == pattern.charAt(j)){
				i++;
				result[j] = i;
				j++;
			} else if(i != 0){
				i = result[i-1];
			} else {
				result[j] = 0;
				j++;
			}
		}

		return result;
	}
}
