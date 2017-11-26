import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel implements ActionListener{
	BufferedImage wheel, ball;
	Timer timer;
	int spin;
	int rotation;
	public Panel(){
		timer = new Timer(5,this);
		spin = 1;
		rotation = 0;
		try {
			wheel = ImageIO.read(new File("res/wheel.jpg"));
		} catch (IOException error) {
			error.printStackTrace();
		}
		
		timer.start();
		
	}
	
	public void paint(Graphics g){
		//AffineTransform at = new AffineTransform();
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D scale = (Graphics2D) g2d.create();
		scale.scale(0.5, 0.5);
		if(spin == 1){
			rotation++;
			if(rotation > 360){
				rotation = 0;
			}
		scale.translate(500, 500);
		scale.rotate(Math.toRadians(rotation), wheel.getWidth() / 2, wheel.getHeight() / 2);
		g.clearRect(0,0,1024,768);
		scale.drawImage(wheel,0,0,null);
		}
		/*scale.scale(0.5, 0.5);
		scale.drawImage(wheel,0,0,null);
		if(spin == 1){
			scale.rotate(Math.PI/6);
			repaint();
		}*/
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){
			repaint();
		}
		
		
	}

}
