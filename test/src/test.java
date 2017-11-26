import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


public class test {
	JFrame a;
	Fade b;
	public test(){
		a = new JFrame();
		b = new Fade();
		a.add(b);
		a.setVisible(true);
		a.setSize(1024,768);
	}
		
	
	public static void main(String[] args) {
		new test();

	}

}
