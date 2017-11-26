package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Chip {
	public enum Value{
		TEN_GRAND(10000), FIVE_GRAND(5000), TWO_HALF_GRAND(2500), 
		THOUSAND(1000), FIVE_HUNDRED(500), TWO_FIFTY(250), 
		HUNDRED(100), FIFTY(50), TWENTY_FIVE(25);
				
		private int chipValue;
		Value(int chipValue){
			this.chipValue = chipValue;
		}
		
		int getChipVal(){
			return chipValue;
		}
	
	}
	private ImageIcon img;
	public Value value;
	private BufferedImage bimg;
	
	public Chip(Value val){
		value = val;
		setImage();
		setBImg();
	}
	
	public String toString(){
		String Return = "";
		
		Return += (value.getChipVal());
		
		return Return;
	}
	
	public String vtoString(){
		String Return = "";
		
		Return = value.name().substring(0, 1).toUpperCase() + 
				value.name().substring(1).toLowerCase();
		
		return Return;
	}
	
	public int getValue(){
		return value.getChipVal();
	}
	
	public ImageIcon getImage(){
		return img;
	}
	
	public BufferedImage getBImg(){
		return bimg;
	}
	
	public void setBImg(){
		switch(value){
		case TWENTY_FIVE:
			try{
				bimg = ImageIO.read(new File("images/Chips/twentyfive.png"));
			}catch(Exception NullPointerException){}
			break;
		case FIFTY:
			try{
				bimg = ImageIO.read(new File("images/Chips/fifty.png"));
			}catch(Exception NullPointerException){}
			break;
		case HUNDRED:
			try{
				bimg = ImageIO.read(new File("images/Chips/hundred.png"));
			}catch(Exception NullPointerException){}
			break;
		case TWO_FIFTY:
			try{
				bimg = ImageIO.read(new File("images/Chips/twofifty.png"));
			}catch(Exception NullPointerException){}
			break;
		case FIVE_HUNDRED:
			try{
				bimg = ImageIO.read(new File("images/Chips/fivehundred.png"));
			}catch(Exception NullPointerException){}
			break;
		case THOUSAND:
			try{
				bimg = ImageIO.read(new File("images/Chips/thousand.png"));
			}catch(Exception NullPointerException){}
			break;
		case TWO_HALF_GRAND:
			try{
				bimg = ImageIO.read(new File("images/Chips/twentyfivehundred.png"));
			}catch(Exception NullPointerException){}
			break;	
		case FIVE_GRAND:
			try{
				bimg = ImageIO.read(new File("images/Chips/fivegrand.png"));
			}catch(Exception NullPointerException){}
			break;
		case TEN_GRAND:
			try{
				bimg = ImageIO.read(new File("images/Chips/tengrand.png"));
			}catch(Exception NullPointerException){}
			break;		
		}
	}
	
	public void setImage(){
		switch(value){
		case TWENTY_FIVE:
			try{
				img = new ImageIcon("images/Chips/twentyfive.png");
			}catch(Exception NullPointerException){}
			break;
		case FIFTY:
			try{
				img = new ImageIcon("images/Chips/fifty.png");
			}catch(Exception NullPointerException){}
			break;
		case HUNDRED:
			try{
				img = new ImageIcon("images/Chips/hundred.png");
			}catch(Exception NullPointerException){}
			break;
		case TWO_FIFTY:
			try{
				img = new ImageIcon("images/Chips/twofifty.png");
			}catch(Exception NullPointerException){}
			break;
		case FIVE_HUNDRED:
			try{
				img = new ImageIcon("images/Chips/fivehundred.png");
			}catch(Exception NullPointerException){}
			break;
		case THOUSAND:
			try{
				img = new ImageIcon("images/Chips/thousand.png");
			}catch(Exception NullPointerException){}
			break;
		case TWO_HALF_GRAND:
			try{
				img = new ImageIcon("images/Chips/twentyfivehundred.png");
			}catch(Exception NullPointerException){}
			break;	
		case FIVE_GRAND:
			try{
				img = new ImageIcon("images/Chips/fivegrand.png");
			}catch(Exception NullPointerException){}
			break;
		case TEN_GRAND:
			try{
				img = new ImageIcon("images/Chips/tengrand.png");
			}catch(Exception NullPointerException){}
			break;		
		}
	}
	
	
	
	
	
	
	
	
}
