package Game;

import java.awt.DisplayMode;
import javax.swing.JFrame;

public class Frame{

	public static void main(String[] args) {
		JFrame window = new JFrame("Game");
		window.setUndecorated(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel a = new Panel();
		window.add(a);
		window.setSize(0,0);
		window.setVisible(true);
		Resolution resolution = new Resolution();
		DisplayMode mode = new DisplayMode(1024,768,32,60);
		a.frameWidth = mode.getWidth();
		a.frameHeight = mode.getHeight();
		
		try{
			resolution.setFullScreen(mode, window);
		}catch(Exception error){
			System.out.println(error.getCause());
		}
		

	}

}
