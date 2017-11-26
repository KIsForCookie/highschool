
package game;

import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Character {
	
	String name;
	int x, dx;
	int y, dy;
	int gender;//1 = male, 2 = female
	BufferedImage choice;
	BufferedImage r,r1,r2,r3,l,l1,l2,l3;
	BufferedImage fr,fr1,fr2,fr3,fl,fl1,fl2,fl3;
	int width,height;
	public Character(){
		try{
		r = ImageIO.read(new File("res/r.png"));
		r1 = ImageIO.read(new File("res/r1.png"));
		r2 = ImageIO.read(new File("res/r2.png"));
		r3 = ImageIO.read(new File("res/r3.png"));
		l = ImageIO.read(new File("res/l.png"));
		l1 = ImageIO.read(new File("res/l1.png"));
		l2 = ImageIO.read(new File("res/l2.png"));
		l3 = ImageIO.read(new File("res/l3.png"));
		fr = ImageIO.read(new File("res/fr.png"));
		fr1 = ImageIO.read(new File("res/fr1.png"));
		fr2 = ImageIO.read(new File("res/fr2.png"));
		fr3 = ImageIO.read(new File("res/fr3.png"));
		fl = ImageIO.read(new File("res/fl.png"));
		fl1 = ImageIO.read(new File("res/fl1.png"));
		fl2 = ImageIO.read(new File("res/fl2.png"));
		fl3 = ImageIO.read(new File("res/fl3.png"));
		}catch(Exception error){
			System.out.println("picture didnt load");
		}
		gender = 0;
		x = 10;
		y = 687;
		name = "";
		if(gender == 1){
			choice = r;
			width = choice.getWidth();
			height = choice.getHeight();
		}else if(gender == 2){
			choice = fr;
			width = choice.getWidth();
			height = choice.getHeight();
		}
		
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
		gender = g;
		if(gender == 1){
			choice = r;
			width = choice.getWidth();
		}else if(gender == 2){
			choice = fr;
			width = choice.getWidth();
		}
	}
	
	public void moveLeft(){
		dx = -3;
		if(gender == 1){
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
		}else if(gender == 2){
			if((choice == fr1)||(choice == fr2)||(choice == fr3)||(choice == fr)){
				choice = fl;
			}
			if(choice == fl){
				choice = fl1;
			}else if(choice == fl1){
				choice = fl2;
			}else if(choice == fl2){
				choice = fl3;
			}else if(choice == fl3){
				choice = fl1;
			}
		}
	}
	
	public void moveRight(){
		dx = 3;
		if(gender == 1){
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
		}else if(gender == 2){
			if((choice == fl1)||(choice == fl2)||(choice == fl3)||(choice == fl)){
				choice = fr;
			}
			
			if(choice == fr){
				choice = fr1;
			}else if(choice == fr1){
				choice = fr2;
			}else if(choice == fr2){
				choice = fr3;
			}else if(choice == fr3){
				choice = fr1;
			}
		}
	}
	
	public void jump(){
		dy = 1;
	}
	
	public void stop(){
		dx = 0;
		if(gender == 1){
			if((choice == r1)||(choice == r2)||(choice == r3)){
				choice = r;
			}else if((choice == l1)||(choice == l2)||(choice == l3)){
				choice = l;
			}
		}else if(gender == 2){
			if((choice == fr1)||(choice == fr2)||(choice == fr3)){
				choice = fr;
			}else if((choice == fl1)||(choice == fl2)||(choice == fl3)){
				choice = fl;
			}
		}
		
	}

}
