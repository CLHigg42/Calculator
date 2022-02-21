package application;

import javax.swing.*;

import org.junit.platform.commons.util.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Calculator implements ActionListener {

	JFrame frame;
	JTextField text;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[12];
	JButton addButton, subtractButton, multiButton, divButton;
	JButton clearButton, deleteButton, equalButton, decimalButton;
	JButton posNegButton, percentButton, rightPButton, leftPButton;
	JPanel panel;
	Font myFont = new Font("", Font.BOLD, 30);

	
	

	Calculator() {

		// Frame init
		frame = new JFrame("Calculator");
		frame.setSize(400, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// Setting up text area
		text = new JTextField();
		text.setBounds(40, 30, 300, 50);
		text.setFont(myFont);
		text.setEditable(false);

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
		rightPButton = new JButton("(");
		leftPButton = new JButton(")");

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
		functionButtons[10] = rightPButton;
		functionButtons[11] = leftPButton;

		for (int i = 0; i < 12; i++) {
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

	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				text.setText(text.getText().concat(String.valueOf(i)));
			}
		}
		if (e.getSource() == clearButton) {
			String expression = text.getText();
			expression = expression.substring(0,expression.length()-1);
			text.setText(expression);
			
		}

		if (e.getSource() == divButton) {

			if (operatorCheck() == true) {
				System.out.println("here");
				text.setText(text.getText().concat(""));

			} else {
				text.setText(text.getText().concat("/"));
			}
		}
		if (e.getSource() == multiButton) {
			if (operatorCheck() == true) {
				System.out.println("here");
				text.setText(text.getText().concat(""));

			} else {
			text.setText(text.getText().concat("*"));
			}

		}
		if (e.getSource() == subtractButton) {
			if (operatorCheck() == true) {
				System.out.println("here");
				text.setText(text.getText().concat(""));

			} else {
			text.setText(text.getText().concat("-"));
			}

		}
		if (e.getSource() == addButton) {
			if (operatorCheck() == true) {
				System.out.println("here");
				text.setText(text.getText().concat(""));

			} else {
			text.setText(text.getText().concat("+"));
			}
		}
		if (e.getSource() == equalButton) {
			if(text.getText().length() == 0) {
				text.setText("0");
			}
			text.setText(String.valueOf(Consume_String.evaluate(text.getText())));

		}
		if (e.getSource() == percentButton) {
			double num = Double.parseDouble(text.getText());
			num = num / 100;
			text.setText("");
			text.setText(text.getText().concat(String.valueOf(num)));
		}
		if (e.getSource()==posNegButton){
			String num;
			String expression = text.getText();
			num = expression.substring(expression.length()-1);
			
			
			
			
		}
		
			
			
			
	}

	

	public String removeLastChar(String s) {
		return (s == null) ? null : s.replaceAll(".$", "");
	}

	public boolean operatorCheck() {
		String expression = text.getText();
		System.out.println(expression.substring(expression.length() - 1));
		String op = expression.substring(expression.length() - 1);

		try {
			Double.parseDouble(op);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;

	}

}
