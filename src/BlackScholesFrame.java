import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class BlackScholesFrame extends JFrame {
	
	BlackScholes option;
	
	private JRadioButton call;
	private JRadioButton put;
	private JRadioButton DEFAULT;
	
	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JButton answer;
	
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	private JTextField text7;
	private JTextField textAnswer;

	public BlackScholesFrame() {
		super("Black Scholes");
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		panel.setLayout(layout);
		//panel.setPreferredSize(maxSize);
		
		title = new JLabel("Black Scholes Pricing Model");
		label1 = new JLabel("Stock ($)");
		label2 = new JLabel("Strike ($)");
		label3 = new JLabel("Rate (%)");
		label4 = new JLabel("Time (Years)");
		label5 = new JLabel("Volatility (%)");
		label6 = new JLabel("Dividend ($)");
		label7 = new JLabel("Yield");
		answer = new JButton("Calculate");
		
		answer.addActionListener(new Calculate());
		
		text1 = new JTextField("", 10);
		text2 = new JTextField("", 10);
		text3 = new JTextField("", 10);
		text4 = new JTextField("", 10);
		text5 = new JTextField("", 10);
		text6 = new JTextField("", 10);
		text7 = new JTextField("", 10);
		textAnswer = new JTextField("", 10);
		
		call = new JRadioButton("Call");
		put = new JRadioButton("Put");
		DEFAULT = new JRadioButton("Default");
		DEFAULT.addActionListener(new DefaultSettings());
		
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(call);
		bGroup.add(put);
		bGroup.add(DEFAULT);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label2).addComponent(label3).
				addComponent(label4).addComponent(label5).addComponent(label6).addComponent(label7).addComponent(answer));
		hGroup.addGroup(layout.createParallelGroup().addComponent(text1).addComponent(text2).addComponent(text3).
				addComponent(text4).addComponent(text5).addComponent(text6).addComponent(text7).addComponent(textAnswer));
		hGroup.addGroup(layout.createSequentialGroup().addComponent(call).addComponent(put).addComponent(DEFAULT));
		layout.setHorizontalGroup(hGroup);
		
		//layout.linkSize(SwingConstants.HORIZONTAL, call, put, DEFAULT);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(call).addComponent(put).addComponent(DEFAULT));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label1).addComponent(text1));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label2).addComponent(text2));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label3).addComponent(text3));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label4).addComponent(text4));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label5).addComponent(text5));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label6).addComponent(text6));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label7).addComponent(text7));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(answer).addComponent(textAnswer));
		
		layout.setVerticalGroup(vGroup);
		
		frame.add(panel);
		//frame.pack();
		frame.setSize(400, 400);
		frame.setVisible(true);
	}	
	
	class Calculate implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean type = false;
			if (call.isSelected())
				type = true;
			if (put.isSelected())
				type = false;
			
			String stock = text1.getText();
			String strike = text2.getText();
			String rate = text3.getText();
			String time = text4.getText();
			String volatility = text5.getText();
			String div = text6.getText();
			String yield = text7.getText();
			
			double STOCK = Double.parseDouble(stock);
			double STRIKE = Double.parseDouble(strike);
			double RATE = Double.parseDouble(rate);
			double TIME = Double.parseDouble(time);
			double VOLATILITY = Double.parseDouble(volatility);
			double DIV = Double.parseDouble(div);
			double YIELD = Double.parseDouble(yield);
			
			RATE /= 100;
			VOLATILITY /= 100;
			
			option = new BlackScholes(type, STOCK, STRIKE, RATE, TIME, VOLATILITY, DIV, YIELD);
			double price = option.calculateOptionPrice();
			textAnswer.setText("" + price);
			System.out.println("Price: " + price);
			option.printGreeks();
		}
	}
	
	class DefaultSettings implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			call.setSelected(true);
			text1.setText("100");
			text2.setText("125");
			text3.setText("5");
			text4.setText("3");
			text5.setText("15");
			text6.setText("3");
			text7.setText("0");
		}
	}
}
