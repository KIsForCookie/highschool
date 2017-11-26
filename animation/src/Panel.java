import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel{
	Graphics2D g;
	Dimension size;
	Double x;
	Double y;
	public Panel(){
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		@SuppressWarnings("unused")
		Graphics2D g2d = (Graphics2D) g;
		size = getSize();
		x = size.getWidth();
		y = size.getHeight();
		@SuppressWarnings("unused")
		Rectangle a = new Rectangle(15,15,25,25);
		g.drawLine(0, 0, (int)Math.round(x), (int)Math.round(y));
		g.drawLine(0,(int)Math.round(y),(int)Math.round(x),0);
		g.drawString("ASDSADSADSADSADSADSADSAD", 0, 250);
		g.drawRect(250, 250, 100, 100);
	}

}
