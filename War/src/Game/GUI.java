package Game;

import javax.swing.*;
import java.awt.*;


public class GUI {
	Timer timer;
	public GUI(){
		JFrame window = new JFrame("The GUI");
		window.setSize(150,225);
		window.add(new Panel());
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
