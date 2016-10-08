/* Author: Mehdi Drissi
 * Date: 1/13/2014
 */
 import java.util.Scanner;
 import java.util.HashMap;

public class BoyerMoore {
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	char again = 'y';

		while(again == 'y'){
			System.out.println("What is your text?");
			String text = input.nextLine();

			System.out.println("What is your pattern?");
			String pattern = input.nextLine();

			Boyer(text,pattern);

			System.out.println("Do you want to check another pattern (yes/no)?");
			again = input.nextLine().toLowerCase().charAt(0);
		}
    }

	public static void Boyer(String text, String pattern){
    	HashMap<Character,Integer> skip;

       	text.toLowerCase();
       	pattern.toLowerCase();

       	skip = createSkipTable(pattern);
       	boolean found = false;


       	outer: for(int i = 0; i <= text.length()-pattern.length(); i++){
       		for(int j = pattern.length()-1; j >= 0; j--){
       			if(text.charAt(i+j) != pattern.charAt(j)){
       				if(j == pattern.length()-1){
       					i += skip.get(text.charAt(i+j)) != null ? skip.get(text.charAt(i+j)) : pattern.length()-1;
       				}
       				continue outer;
       			}
       		}

       		System.out.print(i + " ");
       		found = true;
       	}


       	if(!found){
       		System.out.println("not found");
       	} else {
       		System.out.println();
       	}
	}

    public static HashMap<Character,Integer> createSkipTable(String pattern){
    	HashMap<Character,Integer> result = new HashMap<Character,Integer>();

    	for(int i = 0; i < pattern.length(); i++){
    		result.put(pattern.charAt(i),pattern.length()-i-2);
    	}

    	return result;
    }
}
