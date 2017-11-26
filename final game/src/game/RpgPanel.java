

package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

public class RpgPanel extends JPanel implements ActionListener, KeyListener{
	//declarations
	Dimension size;
	Character character;
	BufferedImage background,string;
	FontMetrics fontsize;
	Clip clip;
	AudioInputStream ais;
	FloatControl volume;
	Timer timer;
	int backgroundX;
	int scroll;
	File sound;
	int width;
	int frameWidth,frameHeight;
	Double factor;
	int choice,select; //1 = blackjack, 2 = mainmenu, 3 = poker, 4 = slots, 5 = craps, 6 = atm
	int px;
	int looponce;
	int money;
	int buffer;
	public RpgPanel(){
		//instatniations
		money = 0;
		setFocusable(true);
		addKeyListener(this);
		string = new BufferedImage(1,1,1);
		fontsize = string.getGraphics().getFontMetrics();
		character = new Character();
		try{
		background = ImageIO.read(new File("res/rpg.png"));
		width = background.getWidth();
		}catch(Exception error){
			System.out.println("Image didn't load");
		}
		
		backgroundX = 0;
		choice = 0;
		scroll = 0;
		buffer = 250;
		looponce = 1;
		select = 0;
		timer = new Timer(25, this);
		timer.start();
	}
	
	//gets the choice of panel that the user wants to go into
	public int getPanel(){
		return choice;
	}
	
	public void playMusic(File music){//plays music
		try{
			ais = AudioSystem.getAudioInputStream(music);//gets the audio input stream from the music file
			clip = AudioSystem.getClip();//turns the music into a preloaded clip
			clip.open(ais);//aqquires system resources needed to play the music
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); //create the volume controls for the clip
			volume.setValue((float) -10.0); //lowers the volume by 10 decibels
			clip.start(); //starts playing the music
			clip.loop(Clip.LOOP_CONTINUOUSLY); //keeps looping the music
		}catch(Exception error){
			System.err.println("Music didn't play");
			System.out.println(error.getCause());
		}
	}
	
	public void stopMusic(){
		clip.stop(); //stops the music
	}
	
	public void paint(Graphics g){ //paint method
		super.paint(g);//uses the super class methods
		Graphics2D g2d = (Graphics2D) g; //declares a graphics2d object, linked to g object.
		Graphics2D scale = (Graphics2D) g2d.create();//creates a seperate graphics2d object that is not linked to g2d object
		
		if(looponce == 1){ //loops once
			factor = (double)frameHeight / (double)background.getHeight(); //gets the factor for scaling
			width = (int) (background.getWidth() * factor);//gets the width of the background
			looponce = 0;
			//this makes the game able to become any resolution that I want the game to be and the map will still fit
		}
		
		px = (int) (character.getX() - backgroundX*factor); //gets the player x with factoring taken into account
		
		if(px <= 0){ //making sure the character cannot walk off the map
			character.x = 0;
			backgroundX = 0;
		}else if((character.getX() <= buffer)&&(backgroundX*factor < 0)&&(character.dx < 0)){ //scrolling the map left to see other parts of the map
			character.x = buffer;
			backgroundX -= (int)(2*character.dx/factor);
		}
		
		if(px + character.width>= width){//making sure the character cannot walk off the map
			character.x = frameWidth - character.width;
			backgroundX = (int) ((frameWidth - width)/factor);//factor is taken into account
		}else if((character.getX() + character.width >= frameWidth-buffer)&&((frameWidth - backgroundX*factor) <= width)&&(character.dx > 0)&&(backgroundX !=(int)((frameWidth - width)/factor))){// scrolling the map right to see other parts of the map
			character.x = frameWidth - buffer - character.width;
			backgroundX -=(int)(2*character.dx/factor);//factor is taken into account
		}
		
		if(frameWidth - backgroundX*factor >= width){
			backgroundX = (int) ((frameWidth - width)/factor);//gets the background x position with factor taken into account
		}
		
		scale.scale(factor,factor); //scales the scale object so the desired factors
		scale.drawImage(background,backgroundX,0,null);//draws the background at the background x position
		g2d.drawImage(character.getCharacter(),character.getX(),character.getY(),null);//draws the character at the character x position
		
		//g2d.setColor(Color.GREEN);
		//g2d.drawString("distance to end from left side: " + (width + backgroundX*factor),0,90);
		//g2d.drawString("BackgroundX: " + backgroundX*factor, 0, 30);
		//g2d.drawString("distance to end from right side"+(frameWidth - backgroundX*factor),0,120);
		//g2d.drawString("characterX on screen: " + character.getX(), 0, 150);
		
		g2d.setColor(Color.WHITE);//sets the colour of the g2d is white
		
		//g2d.drawString("CharacterPosition: " + px, 0, 60);
		
		g2d.setFont(getFont().deriveFont((float)20));//changes the font of g2d
		
		
		g2d.drawString("Your money: $" + money, character.getX() - 75, character.getY() - 50);//draws the string to tell user money
		g2d.drawString(""+character.name,character.getX() + character.width/2 - fontsize.stringWidth(""+character.name), character.getY() + character.height + 65); //draws the character name under the character
		
		//gives prompts to enter various parts of the casino dependant of the character's x position
		if((px < 450)&&(px >300)){
			g2d.drawString("Press Enter to play BlackJack", character.getX()-75, character.getY() - 25);
			choice = 1;
		}else if(px<150){
			g2d.drawString("Press Enter to exit the Casino", character.getX()- 75, character.getY() - 25);
			choice = 2;
		}else if((px < 1100)&&(px > 975)){
			g2d.drawString("Press Enter to play Poker", character.getX()- 75, character.getY() - 25);
			choice = 3;
		}else if((px > 1500)&&(px <1700)){
			g2d.drawString("Press Enter to play Slots", character.getX()- 75, character.getY() - 25);
			choice = 4;
		}else if((px > 2300)&&(px < 2400)){
			g2d.drawString("Press Enter to play Craps", character.getX()- 75, character.getY() - 25);
			choice = 5;
		}else if(px>2800){
			g2d.drawString("Press Enter visit the ATM", character.getX()- 75, character.getY() - 25);
			choice = 6;
		}else{
			choice = 0;
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){//timer to continuously redraw screen
			character.move();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_LEFT){ //listens for which key is pressed and responds accordingly
			character.moveLeft();//makes the character move left
		}else if(key == KeyEvent.VK_RIGHT){
			character.moveRight();//makes the character move right
		}else if(key == KeyEvent.VK_UP){
			character.jump();//not implemented
		}else if(key == KeyEvent.VK_DOWN){
			//also not implemented
		}else if(key == KeyEvent.VK_ENTER){
			select = choice;//allows the character to enter the casino section of their choice
		}else{
			
		}
			
	}

	@Override
	public void keyReleased(KeyEvent k) {//stops moving the player when direction keys are released
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			character.stop();//makes the character stop
		}else if(key == KeyEvent.VK_RIGHT){
			character.stop();//makes the character stop
		}else if(key == KeyEvent.VK_UP){
		
		}else if(key == KeyEvent.VK_DOWN){
			
		}else{
			
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {}

}
