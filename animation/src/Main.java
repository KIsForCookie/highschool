import javax.swing.JFrame;
import java.awt.*;

public class Main{

	public Main(){
		JFrame window = new JFrame();
		
		Resolution resolution = new Resolution();
		window.add(new Panel());
		
		DisplayMode displayMode = new DisplayMode(640,480, 16, 75);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640,480);
        window.setLocationRelativeTo(null);
        window.setTitle("Donut");
        window.setUndecorated(true);
        window.setVisible(true);
        resolution.setFullScreen(displayMode, window);
	}
}
