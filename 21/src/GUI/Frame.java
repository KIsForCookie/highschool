package GUI;

import java.awt.DisplayMode;

import javax.swing.JFrame;

public class Frame {

	public static void main(String[] args) {
		Panel a = new Panel();
		Resolution resolution = new Resolution();
		DisplayMode mode = new DisplayMode(1024,768,32,60);

		JFrame window = new JFrame("Blackjack");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1024,768);
		window.setResizable(false);
		window.add(a);
		window.setUndecorated(true);
		window.setVisible(true);
		resolution.setFullScreen(mode, window);
	}

}
