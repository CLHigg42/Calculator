package application;

import java.io.*;
import java.net.*;

public class Server {

	static DataOutputStream output = null;
	static DataInputStream input = null;
	static ServerSocket ss = null;

	public static void main(String[] args) throws IOException {

		startServer();

	}

	public static void startServer() throws IOException {

		while (true) {

			if (ss == null) {
				ss = new ServerSocket(4999);
				Socket connect = ss.accept();
				input = new DataInputStream(connect.getInputStream());
				output = new DataOutputStream(connect.getOutputStream());

			}
			System.out.println("Recieving");

			
			double num1, num2;
			char operand;

			num1 = input.readDouble();
			System.out.println("num1:" + num1);
			num2 = input.readDouble();
			System.out.println("num2:" + num2);
			operand = input.readChar();
			System.out.println("char:" + operand);

			getResult(num1, num2, operand);

		}

	}

	public static void getResult(double num1, double num2, char operand) throws IOException {

		double result = 0;

		switch (operand) {

		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num1 / num2;
			break;
		}
		System.out.println(result);
		num1 = result;
		output.writeDouble(num1);
		output.flush();

	}

}
/*
 * TO-DO:
 * 
 *  add number formatting to make look pretty
 * 
 * give calculator ability to resize in window or make window non modifiable
 * 
 * re-implement keyboard functionality after UI functionality is functional
 * 
 * Delete all console outputs that are not needed 
 * 
 * COMMENT CODE!!!!
 * 
 * finished for now :)
 * 
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
