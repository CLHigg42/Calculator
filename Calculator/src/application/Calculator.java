package application;

import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

	JFrame frame;
	JTextField text;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[12];
	JButton addButton, subtractButton, multiButton, divButton;
	JButton clearButton, deleteButton, equalButton, decimalButton;
	JButton posNegButton, percentButton;
	JPanel panel;
	Font myFont = new Font("", Font.BOLD, 30);
	double num1 = 0;
	double num2;
	double result = 0;
	char operand;
	Socket client = null;

	Calculator() {

		// Frame init
		frame = new JFrame("Calculator");
		frame.setSize(400, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		// Setting up text area
		text = new JTextField();
		text.setBounds(40, 30, 300, 50);
		text.setFont(myFont);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setBorder(new LineBorder(Color.BLACK));
		text.setHorizontalAlignment(SwingConstants.RIGHT);
		text.setText("0");

		// Setting up buttons
		addButton = new JButton("+");
		addButton.setBackground(Color.ORANGE.darker());
		addButton.setForeground(Color.WHITE);
		subtractButton = new JButton("-");
		subtractButton.setBackground(Color.ORANGE.darker());
		subtractButton.setForeground(Color.WHITE);
		multiButton = new JButton("*");
		multiButton.setBackground(Color.ORANGE.darker());
		multiButton.setForeground(Color.WHITE);
		divButton = new JButton("/");
		divButton.setBackground(Color.ORANGE.darker());
		divButton.setForeground(Color.WHITE);
		decimalButton = new JButton(".");
		decimalButton.setBackground(Color.DARK_GRAY);
		decimalButton.setForeground(Color.WHITE);
		equalButton = new JButton("=");
		equalButton.setBackground(Color.ORANGE.darker());
		equalButton.setForeground(Color.WHITE);
		deleteButton = new JButton("Del");
		clearButton = new JButton("C");
		clearButton.setBackground(Color.LIGHT_GRAY);
		posNegButton = new JButton("+/-");
		posNegButton.setBackground(Color.LIGHT_GRAY);
		percentButton = new JButton("%");
		percentButton.setBackground(Color.LIGHT_GRAY);

		functionButtons[0] = addButton;
		functionButtons[1] = subtractButton;
		functionButtons[2] = multiButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decimalButton;
		functionButtons[5] = equalButton;
		functionButtons[6] = deleteButton;
		functionButtons[7] = clearButton;
		functionButtons[8] = posNegButton;
		functionButtons[9] = percentButton;

		for (int i = 0; i < 10; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFocusable(false);
			functionButtons[i].setFont(myFont);
		}

		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setBackground(Color.DARK_GRAY);
			numberButtons[i].setForeground(Color.WHITE);
			numberButtons[i].setFont(myFont);

		}
		// Adding all elements to frame
		panel = new JPanel();
		panel.setBounds(40, 100, 300, 375);
		panel.setLayout(new GridLayout(5, 4, 5, 5));
		panel.setBackground(Color.BLACK);

		panel.add(clearButton);
		panel.add(posNegButton);
		panel.add(percentButton);
		panel.add(divButton);

		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(multiButton);

		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subtractButton);

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);

		panel.add(numberButtons[0]);
		panel.add(decimalButton);
		panel.add(equalButton);

		frame.add(panel);
		frame.add(text);
		frame.getContentPane().setBackground(Color.BLACK);

		frame.setVisible(true);
		
		
		//function that handles all keyboard inputs
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {

				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					num2 = Double.parseDouble(text.getText());
					try {
						getResult();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

				if (ke.getKeyCode() == KeyEvent.VK_MINUS) {

					if (text.getText().charAt(0) == '0') {
						text.setText(" ");
					} else if (text.getText().contains("-") == true && num1 <= 0) {
						num1 = Double.parseDouble(text.getText());
						System.out.println(num1);
						operand = '-';
						text.setText(" ");
					} else if (text.getText().contains("-") == false && num1 <= 0) {
						num1 = Double.parseDouble(text.getText());
						System.out.println(num1);
						operand = '-';
						text.setText(" ");

					} else {
						num1 = Double.parseDouble(text.getText());
						System.out.println(num1);
						operand = '-';
						text.setText("-");
					}

				}

				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
					if (text.getText().contains("+") || text.getText().contains("*") || text.getText().contains("/"))
						text.setText(" ");
					if (text.getText().charAt(0) == '0')
						text.setText(" ");

					text.setEditable(true);

					if (text.getText().contains("-") == true && (num1 > 0 || num1 < 0)) {
						text.setText(" ");
					}

				} else if (ke.getKeyChar() == '+' || ke.getKeyChar() == '-' || ke.getKeyChar() == '*'
						|| ke.getKeyChar() == '/' || ke.getKeyChar() == '.') {

					switch (ke.getKeyChar()) {

					case '+':
						num1 = Double.parseDouble(text.getText());
						operand = '+';
						text.setText("+");
						break;

					/*
					 * case '-': num1 = Double.parseDouble(text.getText()); operand = '-';
					 * text.setText(""); break;
					 */

					case '*':
						num1 = Double.parseDouble(text.getText());
						operand = '*';
						text.setText("*");
						break;
					case '/':
						num1 = Double.parseDouble(text.getText());
						operand = '/';
						text.setText("/");
						break;
					case '.':

						if (text.getText().contains(".") == true) {

							text.setEditable(false);
						} else
							text.setEditable(true);

						break;

					}

				} else
					text.setEditable(false);

			}

		});

	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		try {
			Server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// function that handles all UI input
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 10; i++) {

			if (text.getText().contains("+") == true || text.getText().contains("*") == true
					|| text.getText().contains("/") == true) {
				text.setText(" ");

			}

			if (e.getSource() == numberButtons[i]) {

				if (text.getText().contains("-") == false && text.getText().charAt(0) == '0') {
					if (text.getText().contains(".") == true)
						text.setText(text.getText().concat(String.valueOf(i)));
					else {
						text.setText(" ");
						text.setText(text.getText().concat(String.valueOf(i)));
					}
				}

				else if (num1 != 0 && e.getSource() == numberButtons[i] && text.getText().contains("-") == true) {
					text.setText(" ");
					text.setText(text.getText().concat(String.valueOf(i)));

				} else if (text.getText().charAt(0) == '0' && num1 >= 0) {
					text.setText(" ");
					text.setText(text.getText().concat(String.valueOf(i)));

				} else if (text.getText().contains("-") == true && num1 == 0) {
					text.setText(text.getText().concat(String.valueOf(i)));
				} else {
					text.setText(text.getText().concat(String.valueOf(i)));
				}

			}

		}

		if (e.getSource() == numberButtons[0] && Double.parseDouble(String.valueOf(text.getText())) == 0) {
			text.setText("0");
		} else if (e.getSource() == clearButton) {
			text.setText("0");
			num1 = 0;
		}

		else if (e.getSource() == divButton) {
			num1 = Double.parseDouble(text.getText());
			operand = '/';
			text.setText("/");
		} else if (e.getSource() == multiButton) {
			num1 = Double.parseDouble(text.getText());
			operand = '*';
			text.setText("*");

		}

		else if (e.getSource() == subtractButton) {

			if (text.getText() == "0") {
				text.setText("-");
				System.out.println(text.getText().charAt(0));
			} else if (text.getText().contains("-") == true && num1 <= 0) {
				num1 = Double.parseDouble(text.getText());
				System.out.println(num1);
				operand = '-';
				text.setText("-");
			} else if (text.getText().contains("-") == false && num1 <= 0) {
				num1 = Double.parseDouble(text.getText());
				System.out.println(num1);
				operand = '-';
				text.setText("-");

			} else {
				num1 = Double.parseDouble(text.getText());
				System.out.println(num1);
				operand = '-';
				text.setText("-");
			}
		}

		else if (e.getSource() == addButton) {
			num1 = Double.parseDouble(text.getText());
			operand = '+';
			text.setText("+");
		} else if (e.getSource() == decimalButton) {
			text.setText(text.getText().concat("."));
		} else if (e.getSource() == equalButton) {
			num2 = Double.parseDouble(text.getText());
			try {
				getResult();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == percentButton) {
			double num = Double.parseDouble(text.getText());
			num = num / 100;
			text.setText("");
			text.setText(text.getText().concat(String.valueOf(num)));
		} else if (e.getSource() == posNegButton) {

			double num = Double.parseDouble(String.valueOf(text.getText()));
			num = num * (-1);
			text.setText(String.valueOf(num));
		}

	}
	
	//Function that gives input to server, gets server output, and displays output for the user.
	public void getResult() throws IOException {

		if (client == null) {
			System.out.println("create client socket");
			client = new Socket("localHost", 4999);
		}

		DataInputStream input = new DataInputStream(client.getInputStream());
		DataOutputStream output = new DataOutputStream(client.getOutputStream());
		System.out.println("Init variables");

		output.writeDouble(num1);
		output.flush();

		output.writeDouble(num2);
		output.flush();

		output.writeChar(operand);
		output.flush();

		System.out.println("Flushed all variables");
		num1 = input.readDouble();
		text.setText(String.valueOf(num1));

	}

}
