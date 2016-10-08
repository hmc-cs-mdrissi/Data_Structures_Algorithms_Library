/**
 * @(#)SolvingPostfix.java
 * @author
 * @version 1.00 2013/9/25
 */
import java.util.Scanner;

public class SolvingPostfix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("What math problem do you want calculated. Put it in post fix notation with spaces between operands or operations?");
        System.out.println("An example would be the solution to 5 -9 3 - 17 * + 12 3 - % 6 / would be " + solveProblem("5 -9 3 - 17 * + 12 3 - % 6 /"));

		int solution = solveProblem(input.nextLine());

        System.out.println("The solution to your problem is " + solution + ".");
	}

    public static boolean isOperator(String check){
    	return check.matches("[+-/%*]{1}");
    }

    public static int solveProblem(String mathProb){
    	String next;
        int later,earlier;

    	myStack<Integer> operands = new myStack<Integer>((mathProb.length()+3)/4);
    	Scanner prob = new Scanner(mathProb);

    	while(prob.hasNext()){
        	next = prob.next();
        	if(!isOperator(next)){
        		operands.push(Integer.parseInt(next));
        	} else {
     			if(next.equals("+")){
     				operands.push(operands.pop() + operands.pop());
     			}

     			else if(next.equals("-")){
     				operands.push(-operands.pop() + operands.pop());
     			}

     			else if(next.equals("%")){
     				later = operands.pop();
     				earlier = operands.pop();
     				operands.push(later % earlier);
     			}

     			else if(next.equals("/")){
     				later = operands.pop();
     				earlier = operands.pop();
     				operands.push(later / earlier);
     			}

     			else if(next.equals("*")){
     				operands.push(operands.pop() * operands.pop());
     			}
        	}
        }

    	return operands.pop();
    }
}
