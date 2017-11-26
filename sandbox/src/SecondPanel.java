import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondPanel extends JPanel implements ActionListener{
	JButton button = new JButton("change");
	
	public SecondPanel(){
		setLayout(null);
		button.setBounds(25,25,150,75);
		add(button);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			
		}
		
	}

}
