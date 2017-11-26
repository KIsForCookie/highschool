package test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondPanel extends JPanel implements ActionListener{
	JButton button = new JButton("change");
	
	public SecondPanel(){
		setBackground(Color.GREEN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			
		}
		
	}

}
