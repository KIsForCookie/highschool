package Game;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Character {
	
	int x, dx;
	int y, dy;
	int gender;//1 = male, 2 = female
	BufferedImage choice;
	BufferedImage character;
	BufferedImage r,r1,r2,r3,l,l1,l2,l3;
	int width;
	public Character(){
		try{
		character = ImageIO.read(new File("src/Resources/r.png"));
		r = ImageIO.read(new File("src/Resources/r.png"));
		r1 = ImageIO.read(new File("src/Resources/r1.png"));
		r2 = ImageIO.read(new File("src/Resources/r2.png"));
		r3 = ImageIO.read(new File("src/Resources/r3.png"));
		l = ImageIO.read(new File("src/Resources/l.png"));
		l1 = ImageIO.read(new File("src/Resources/l1.png"));
		l2 = ImageIO.read(new File("src/Resources/l2.png"));
		l3 = ImageIO.read(new File("src/Resources/l3.png"));
		choice = r;
		}catch(Exception error){
			
		}
		x = 10;
		y = 350;
		gender = 0;
		width = choice.getWidth();
	}
	
	public void move(){
		x = x + dx;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public BufferedImage getCharacter(){
		return choice;
	}
	
	public void setGender(int g){
		if(g == 1){
			gender = g;
		}else if(g==2){
			gender = g;
		}
	}
	
	public void moveLeft(){
		dx = -3;
		if((choice == r1)||(choice == r2)||(choice == r3)||(choice == r)){
			choice = l;
		}
		if(choice == l){
			choice = l1;
		}else if(choice == l1){
			choice = l2;
		}else if(choice == l2){
			choice = l3;
		}else if(choice == l3){
			choice = l1;
		}
	}
	
	public void moveRight(){
		dx = 3;
		if((choice == l1)||(choice == l2)||(choice == l3)||(choice == l)){
			choice = r;
		}
		
		if(choice == r){
			choice = r1;
		}else if(choice == r1){
			choice = r2;
		}else if(choice == r2){
			choice = r3;
		}else if(choice == r3){
			choice = r1;
		}
	}
	
	public void jump(){
		dy = 1;
	}
	
	public void stop(){
		dx = 0;
		
		if((choice == r1)||(choice == r2)||(choice == r3)){
			choice = r;
		}else if((choice == l1)||(choice == l2)||(choice == l3)){
			choice = l;
		}
	}

}
