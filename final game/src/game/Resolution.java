

package game;

import java.awt.*;
import javax.swing.*;
public class Resolution {
	private GraphicsDevice device;
	private GraphicsEnvironment graphics;
	public Resolution(){
		graphics = GraphicsEnvironment.getLocalGraphicsEnvironment(); //gets the graphics information from the computer's graphics card
		device = graphics.getDefaultScreenDevice(); //gets the default screen device
	}
	
	public void setFullScreen(DisplayMode displaymode, JFrame jframe){ //used to set a jframe fullscreen using the displaymode given
		jframe.setResizable(false);//makes the jframe nonresizable
		try{//try catch statement
		device.setFullScreenWindow(jframe);//makes the jframe fullscreen
		device.setDisplayMode(displaymode);//sets the jframe settings to the displaymode
		}catch(Exception error){
			System.err.println("error");
			System.out.println(error.getCause());
		}
	}
	
	public void restoreScreen(){//makes the screen windowed again
		device.setFullScreenWindow(null);
	}

}
