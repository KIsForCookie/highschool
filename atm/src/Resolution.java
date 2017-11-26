
import java.awt.*;
import javax.swing.*;
public class Resolution {
	private GraphicsDevice device;
	private GraphicsEnvironment graphics;
	public Resolution(){
		graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = graphics.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode displaymode, JFrame jframe){
		jframe.setResizable(false);
		//jframe.setUndecorated(true);
		try{
		device.setFullScreenWindow(jframe);
		device.setDisplayMode(displaymode);
		}catch(Exception error){
			System.err.println("error");
			System.out.println(error.getCause());
		}
	}
	
	public void restoreScreen(){
		device.setFullScreenWindow(null);
	}

}
