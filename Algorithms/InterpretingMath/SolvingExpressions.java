/**
 * @(#)SolvingPostfix.java
 * @author
 * @version 1.00 2013/9/25
 */
import java.util.Scanner;

public class SolvingExpressions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("What math problem do you want calculated. Division will be done as integer division. Put a space between each operator or operand.");
        System.out.println("An example would be the solution to ( 5 + 2 ) * 8 / 6 - 3 + 4 * ( 4 % 3 ) would be " + solvePostfix(convertToPostfix("( 5 + 2 ) * 8 / 6 - 3 + 4 * ( 4 % 3 )")));

		int solution = solvePostfix(convertToPostfix(input.nextLine()));

        System.out.println("The solution to your problem is " + solution + ".");
	}

    public static boolean isOperator(String check){
    	return check.matches("[+-/%*()]{1}");
    }

    //The case where b is ) is dealt with in convertToPostFix
    public static boolean shouldPushOperator(String a, String b){
    	return a.equals("(") || b.equals("(") || (b.matches("[*/]") && a.matches("[+-]"));
    }

    public static int solvePostfix(String mathProb){
    	String next;
        int later,earlier;

    	myStack<Integer> operands = new myStack<Integer>((mathProb.length()+3)/4);
    	Scanner prob = new Scanner(mathProb.trim());

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
     				operands.push(earlier % later);
     			}

     			else if(next.equals("/")){
     				later = operands.pop();
     				earlier = operands.pop();
     				operands.push(earlier / later);
     			}

     			else if(next.equals("*")){
     				operands.push(operands.pop() * operands.pop());
     			}
        	}
        }

    	return operands.pop();
    }

	public static String convertToPostfix(String infix){
		String postFix = "";
		myStack<String> operators = new myStack<String>(infix.length()/2);

		Scanner tokens = new Scanner(infix);

		while(tokens.hasNext()){
			String curr = tokens.next();

			if(isOperator(curr)){
				if(operators.isEmpty()){
					operators.push(curr);
				} else {
					if(curr.equals(")")){
						while(!operators.peek().equals("(")){
							postFix += operators.pop() + " ";
						}
						operators.pop();
					}
					else if(shouldPushOperator(operators.peek(),curr)){
						operators.push(curr);
					} else {
						while(!operators.isEmpty() && !shouldPushOperator(operators.peek(),curr)){
							postFix += operators.pop() + " ";
						}
						operators.push(curr);
					}
				}
			} else {
				postFix += curr + " ";
			}
		}

		while(!operators.isEmpty()){
			postFix += operators.pop() + " ";
		}

		return postFix;
	}
}