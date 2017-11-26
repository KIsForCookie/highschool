 package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

public class Panel extends JPanel implements ActionListener, KeyListener{
	
	Character character;
	BufferedImage background;
	Timer timer;
	int backgroundX;
	int scroll;
	int width;
	int frameWidth,frameHeight;
	Double factor;
	int choice;
	int px;
	int looponce;
	int buffer;
	public Panel(){
		setFocusable(true);
		addKeyListener(this);
		character = new Character();
		try{
		background = ImageIO.read(new File("src/Resources/test.png"));
		width = background.getWidth();
		}catch(Exception error){
			
		}
		
		backgroundX = 0;
		choice = 0;
		scroll = 0;
		buffer = 100;
		looponce = 1;
		timer = new Timer(5, this);
		timer.start();
	}
	
	public int getPanel(){
		return choice;
	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D scale = (Graphics2D) g2d.create();
		
		
		
		if(looponce == 1){
			factor = (double)frameHeight / (double)background.getHeight();
			width = (int) (background.getWidth() * factor);
			looponce = 0;
		}
		
		px = (int) (character.getX() - backgroundX*factor);
		
		
		if(px <= 0){
			character.x = 0;
			backgroundX = 0;
		}else if((character.getX() <= buffer)&&(backgroundX*factor < 0)&&(character.dx < 0)){
			character.x = buffer;
			backgroundX -= (int)(2*character.dx/factor);
		}
		
		if(px + character.width>= width){
			character.x = frameWidth - character.width;
			backgroundX = (int) ((frameWidth - width)/factor);
		}else if((character.getX() + character.width >= frameWidth-buffer)&&((frameWidth - backgroundX*factor) <= width)&&(character.dx > 0)&&(backgroundX !=(int)((frameWidth - width)/factor))){
			character.x = frameWidth - buffer - character.width;
			backgroundX -=(int)(2*character.dx/factor);
		}
		
		if(frameWidth - backgroundX*factor >= width){
			backgroundX = (int) ((frameWidth - width)/factor);
		}
		
		scale.scale(factor,factor);
		scale.drawImage(background,backgroundX,0,null);
		g2d.drawImage(character.getCharacter(),character.getX(),character.getY(),null);
		/*g2d.setColor(Color.GREEN);
		g2d.drawString("distance to end from left side: " + (width + backgroundX*factor),0,90);
		g2d.drawString("BackgroundX: " + backgroundX*factor, 0, 30);
		g2d.drawString("CharacterPosition: " + px, 0, 60);
		g2d.drawString("distance to end from right side"+(frameWidth - backgroundX*factor),0,120);
		g2d.drawString("characterX on screen: " + character.getX(), 0, 150);*/
		
		if((px < 600)&&(px >300)){
			choice = 1;
			g2d.drawString("Press Enter to go to BlackJack", px-75, 150);
		}
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){
			character.move();
			repaint();
		}
		
	}


	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_LEFT){ //listens for which key is pressed and responds accordingly
			character.moveLeft();
		}else if(key == KeyEvent.VK_RIGHT){
			character.moveRight();
		}else if(key == KeyEvent.VK_UP){
			character.jump();
		}else if(key == KeyEvent.VK_DOWN){
			
		}else if(key == KeyEvent.VK_ENTER){
		}
			
	}


	@Override
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			character.stop();
		}else if(key == KeyEvent.VK_RIGHT){
			character.stop();
		}else if(key == KeyEvent.VK_UP){
		
		}else if(key == KeyEvent.VK_DOWN){
			
		}
	}


	@Override
	public void keyTyped(KeyEvent k) {}

}
