import javax.swing.*;
import java.awt.*;

public class Atm {
	JFrame window;
	AtmPanel panel;
	Resolution res;
	public Atm(){
		res = new Resolution();
		DisplayMode mode = new DisplayMode(1024,768,16,60);
		panel = new AtmPanel();
		window = new JFrame();
		window.setResizable(false);
		window.setUndecorated(true);
		window.setSize(1024,768);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		
		try{
			res.setFullScreen(mode,window);
		}catch(Exception error){
			System.out.println("error");
		}
	}
	
	public static void main(String[] args) {
		new Atm();
	}

}
