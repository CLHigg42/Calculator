package application;

import java.util.Stack;

public class Consume_String {

	public static double evaluate(String expression) {

		char[] tokens = expression.toCharArray();

		// stack for numbers
		Stack<Double> values = new Stack<Double>();

		// Stack for operands
		Stack<Character> operands = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {

			if (tokens[i] == ' ') {
				continue;
			}

			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();

				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);

				values.push(Double.parseDouble(sbuf.toString()));

				i--;

			}

			else if (tokens[i] == '(')
				operands.push(tokens[i]);
			else if(tokens[i] == ')') {
				while (operands.peek() != '(')
					values.push(applyOps(operands.pop(),values.pop(),values.pop()));
				operands.pop();
			}
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				 while (!operands.empty() &&
	                       hasPrecedence(tokens[i],
	                                    operands.peek()))
	                  values.push(applyOps(operands.pop(),
	                                   values.pop(),
	                                 values.pop()));
	 
	                // Push current token to 'ops'.
	                operands.push(tokens[i]);
			}
				
		}
		
		while(!operands.empty())
			values.push(applyOps(operands.pop(),values.pop(),values.pop()));
		
		
		return values.pop();
	}
	
	public static boolean hasPrecedence(char operand1, char operand2) {
		if(operand2 == '(' || operand2 == ')')
			return false;
		if((operand1 == '*' || operand1 == '/') && (operand2 == '+' || operand2 =='-'))
			return false;
		else
			return true;
		
	}
	
	public static double applyOps(char operand, double num2, double num1) {
		
		switch (operand) {
		
		case '+' : 
			return num1 + num2;
		case '-' : 
			return num1 - num2;
		case '*' : 
			return num1 * num2;
		case '/' : 
			if(num2 == 0)
				throw new UnsupportedOperationException("Infinity");
			return num1 / num2;
		}
		return 0;
	}
}
