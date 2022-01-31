package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
	
	JFrame frame;
	JTextField text;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[10];
	JButton addButton, subtractButton, multiButton, divButton;
	JButton clearButton, deleteButton, equalButton, decimalButton;
	JButton posNegButton, percentButton;
	JPanel panel;
	
	Calculator(){
		
		//Frame init
		frame = new JFrame("Calculator");
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		//Setting up text area
		text = new JTextField();
		text.setBounds(40, 30, 300, 50);
		text.setEditable(false);
		
		//Setting up buttons
		addButton = new JButton("+");
		addButton.setBackground(Color.ORANGE);
		subtractButton = new JButton("-");
		subtractButton.setBackground(Color.ORANGE);
		multiButton = new JButton("*");
		multiButton.setBackground(Color.ORANGE);
		divButton = new JButton("/");
		divButton.setBackground(Color.ORANGE);
		decimalButton = new JButton(".");
		decimalButton.setBackground(Color.GRAY);
		equalButton = new JButton("=");
		equalButton.setBackground(Color.ORANGE);
		deleteButton = new JButton("Del");
		clearButton = new JButton("C");
		posNegButton = new JButton("+/-");
		percentButton = new JButton("%");
		
		
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
		
		for (int i = 0; i <8; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setBackground(Color.GRAY);
			
		}
		deleteButton.setBounds(50,430, 50,50);
		clearButton.setBounds(300,430,50,50);
		
		panel = new JPanel();
		panel.setBounds(40,100,300,350);
		panel.setLayout(new GridLayout(5,4,10,10));
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
		// TODO Auto-generated method stub
		
	}

}
