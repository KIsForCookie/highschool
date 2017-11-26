package test;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FirstPanel extends JPanel implements ActionListener{
	JButton button = new JButton("change");
	File song;
	Clip clip;
	AudioInputStream ais;
	public FirstPanel(){
		setBackground(Color.BLUE);
		button.addActionListener(this);
		song = new File("res/test.mp3");
		 try{
		     ais =AudioSystem.getAudioInputStream(this.getClass().getResource("res/test.mp3"));
		     clip = AudioSystem.getClip();
		     clip.open(ais);
		     clip.start( );
		    }
		   catch(Exception ex)
		   {  }
		
	}
	

	public void actionPerformed(ActionEvent e) {
	}
	

}
