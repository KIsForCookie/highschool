

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fade extends JPanel{
	AudioInputStream ais;
	Clip clip;
	FloatControl volume;
	Timer timer,wait;
	int n;
	int looponce;
	File gunshot, cocking,shell;
	public Fade(){
		n = 0;
		looponce = 0;
		gunshot = new File("res/gunshot.wav").getAbsoluteFile();
		cocking = new File("res/cocking.wav").getAbsoluteFile();
		shell = new File("res/shell.wav").getAbsoluteFile();
		
		wait = new Timer(500,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playMusic(new File("res/shell.wav").getAbsoluteFile());
				wait.stop();
			}
		});
		
		timer = new Timer(10,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();					
				if(n < 350){
					n++;
					if(looponce == 0){
						playMusic(new File("res/cocking.wav").getAbsoluteFile());
						looponce++;
					}
				}else{
					playMusic(new File("res/shot.wav").getAbsoluteFile());
					timer.stop();
					wait.start();
					
				}
			}
			
		});
	}
	
	public void playMusic(File music){
		try{
			ais = AudioSystem.getAudioInputStream(music);//intakes a music file so that it can be played
			clip = AudioSystem.getClip();//loads the music
			clip.open(ais);//aquires system resources needed to play the music
			clip.start(); //starts the music
		}catch(Exception error){
			//exceptions
			System.err.println("Music didn't play");
			System.out.println(error.getCause());
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)n/350));//Blends whatever g2d draws with whatever is already drawn on the screen.
		g2d.fillRect(0,0,1024,768);
		
	}

}
