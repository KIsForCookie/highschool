package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener{
	Timer timer;
	BufferedImage[] card;
	Random gen;
	
	public Panel(){
		gen = new Random();
		timer = new Timer(1000,this);
		card = new BufferedImage[12];
		for(int i = 0; i < 11; i ++){
			card[i] = new BufferedImage(71, 96,BufferedImage.TYPE_INT_RGB);
		}
		
		try {
			card[0] = ImageIO.read(new File("res/d1.png"));
			card[1] = ImageIO.read(new File("res/c1.png"));
			card[2] = ImageIO.read(new File("res/h1.png"));
			card[3] = ImageIO.read(new File("res/s1.png"));
			card[4] = ImageIO.read(new File("res/d2.png"));
			card[5] = ImageIO.read(new File("res/c2.png"));
			card[6] = ImageIO.read(new File("res/h2.png"));
			card[7] = ImageIO.read(new File("res/s2.png"));
			card[8] = ImageIO.read(new File("res/d3.png"));
			card[9] = ImageIO.read(new File("res/c3.png"));
			card[10] = ImageIO.read(new File("res/h3.png"));
			card[11] = ImageIO.read(new File("res/s3.png"));
		} catch (IOException e) {
			System.err.println(e.getCause());
		}
		
		timer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(card[gen.nextInt(11)], 25,25,null);
	}

}
