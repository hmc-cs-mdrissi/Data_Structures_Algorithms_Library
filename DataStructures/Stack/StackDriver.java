/**
 * @(#)Test4.java
 * @author
 * @version 1.00 2013/9/25
 */

public class Test4{
	public static void main(String[] args){
	myStack<Integer> check = new myStack<Integer>(10);

	if(check.isEmpty()){
		System.out.println("The stack is empty.");
	}

	System.out.println("The empty stack looks like " + check);

	for(int i = 0; i < 10; i++){
		check.push(i);
	}

	if(check.isFull()){
		System.out.println("The stack is now full.");
	}

	System.out.println("The full stack looks like " + check);
	System.out.println("The last element of the stack is " + check.peek());

	for(int i = 1;  i <= 3; i++){
		check.pop();
		System.out.println("The last element of the stack after removing from the stack " + i + " times is " + 			check.peek());
	}
	}

}

