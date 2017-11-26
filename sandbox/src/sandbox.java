
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;


public class sandbox{

	
	public sandbox(){
		 try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/a.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		
		new sandbox();
		
		/*CardLayout cl = new CardLayout();
		JFrame frame = new JFrame("test");
		JPanel contpanel = new JPanel();
		FirstPanel panel1 = new FirstPanel();
		SecondPanel panel2 = new SecondPanel();
		contpanel.setLayout(cl);
		frame.setSize(500,400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contpanel.add(panel1, "1");
		contpanel.add(panel2,"2");*/
		
		
	}
	
	
}

