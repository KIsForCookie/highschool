package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class sandbox{
	FirstPanel panel1;
	SecondPanel panel2;
	CardLayout cl;
	JButton b1,b2;
	JPanel cont;
	JFrame frame;
	public sandbox(){
		cont = new JPanel();
		panel1 = new FirstPanel();
		panel2 = new SecondPanel();
		cl = new CardLayout();
		b1 = new JButton("switch");
		b2 = new JButton("Switch");
		frame = new JFrame();
		
		frame.add(cont);
		
		panel1.setLayout(null);
		panel2.setLayout(null);
		cont.setLayout(cl);
		
		panel1.add(b1);
		panel2.add(b2);
		
		panel1.setBackground(Color.BLUE);
		panel2.setBackground(Color.GREEN);
		
		b1.setBounds(25,25,100,100);
		b2.setBounds(25,25,100,100);
		
		frame.setSize(500,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cont.add(panel1, "1");
		cont.add(panel2, "2");
		cl.show(cont, "1");
		
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont, "2");
				
			}
			
		});
		
		b2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont, "1");
				
			}
			
		});
	}
	
	public static void main(String[] args) {
		new sandbox();
	}


}
