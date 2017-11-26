package poker;

import java.awt.DisplayMode;

import javax.swing.JFrame;

import poker_GUI.Resolution;

public class Frame extends JFrame{
	Resolution resolution;
	DisplayMode mode;
	PokerPanel poker = new PokerPanel();
	Frame(){
		setSize(1024,768);
		add(poker);
		setVisible(true);
		mode = new DisplayMode(1024,768,16,60);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pack();
	}
	
	public static void main(String[] args){
		new Frame();
	}
}
