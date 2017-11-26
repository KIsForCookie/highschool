
package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card {
	BufferedImage da,d2,d3,d4,d5,d6,d7,d8,d9,d10,dj,dq,dk,ca,c2,c3,c4,c5,c6,c7,c8,c9,c10,cj,cq,ck,ha,h2,h3,h4,h5,h6,h7,h8,h9,h10,hj,hq,hk,sa,s2,s3,s4,s5,s6,s7,s8,s9,s10,sj,sq,sk,cover;
public Card(){
	try {
		da = ImageIO.read(new File("res/d1.png"));
		d2 = ImageIO.read(new File("res/d2.png"));
		d3 = ImageIO.read(new File("res/d3.png"));
		d4 = ImageIO.read(new File("res/d4.png"));
		d5 = ImageIO.read(new File("res/d5.png"));
		d6 = ImageIO.read(new File("res/d6.png"));
		d7 = ImageIO.read(new File("res/d7.png"));
		d8 = ImageIO.read(new File("res/d8.png"));
		d9 = ImageIO.read(new File("res/d9.png"));
		d10 = ImageIO.read(new File("res/d10.png"));
		dj = ImageIO.read(new File("res/dj.png"));
		dq = ImageIO.read(new File("res/dq.png"));
		dk = ImageIO.read(new File("res/dk.png"));
		ca = ImageIO.read(new File("res/c1.png"));
		c2 = ImageIO.read(new File("res/c2.png"));
		c3 = ImageIO.read(new File("res/c3.png"));
		c4 = ImageIO.read(new File("res/c4.png"));
		c5 = ImageIO.read(new File("res/c5.png"));
		c6 = ImageIO.read(new File("res/c6.png"));
		c7 = ImageIO.read(new File("res/c7.png"));
		c8 = ImageIO.read(new File("res/c8.png"));
		c9 = ImageIO.read(new File("res/c9.png"));
		c10 = ImageIO.read(new File("res/c10.png"));
		cj = ImageIO.read(new File("res/cj.png"));
		cq = ImageIO.read(new File("res/cq.png"));
		ck = ImageIO.read(new File("res/ck.png"));
		ha = ImageIO.read(new File("res/h1.png"));
		h2 = ImageIO.read(new File("res/h2.png"));
		h3 = ImageIO.read(new File("res/h3.png"));
		h4 = ImageIO.read(new File("res/h4.png"));
		h5 = ImageIO.read(new File("res/h5.png"));
		h6 = ImageIO.read(new File("res/h6.png"));
		h7 = ImageIO.read(new File("res/h7.png"));
		h8 = ImageIO.read(new File("res/h8.png"));
		h9 = ImageIO.read(new File("res/h9.png"));
		h10 = ImageIO.read(new File("res/h10.png"));
		hj = ImageIO.read(new File("res/hj.png"));
		hq = ImageIO.read(new File("res/hq.png"));
		hk = ImageIO.read(new File("res/hk.png"));
		sa = ImageIO.read(new File("res/s1.png"));
		s2 = ImageIO.read(new File("res/s2.png"));
		s3 = ImageIO.read(new File("res/s3.png"));
		s4 = ImageIO.read(new File("res/s4.png"));
		s5 = ImageIO.read(new File("res/s5.png"));
		s6 = ImageIO.read(new File("res/s6.png"));
		s7 = ImageIO.read(new File("res/s7.png"));
		s8 = ImageIO.read(new File("res/s8.png"));
		s9 = ImageIO.read(new File("res/s9.png"));
		s10 = ImageIO.read(new File("res/s10.png"));
		sj = ImageIO.read(new File("res/sj.png"));
		sq = ImageIO.read(new File("res/sq.png"));
		sk = ImageIO.read(new File("res/sk.png"));
		cover = ImageIO.read(new File("res/cover.png"));
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}
	
}

public Image getCard(int card){
	switch(card){
	case 0: return da;
	case 1: return d2;
	case 2: return d3;
	case 3: return d4;
	case 4: return d5;
	case 5: return d6;
	case 6: return d7;
	case 7: return d8;
	case 8: return d9;
	case 9: return d10;
	case 10: return dj;
	case 11: return dq;
	case 12: return dk;
	case 13: return ca;
	case 14: return c2;
	case 15: return c3;
	case 16: return c4; 
	case 17: return c5;
	case 18: return c6;
	case 19: return c7;
	case 20: return c8;
	case 21: return c9;
	case 22: return c10;
	case 23: return cj;
	case 24: return cq;
	case 25: return ck;
	case 26: return ha;
	case 27: return h2;
	case 28: return h3;
	case 29: return h4;
	case 30: return h5;
	case 31: return h6;
	case 32: return h7;
	case 33: return h8;
	case 34: return h9;
	case 35: return h10;
	case 36: return hj;
	case 37: return hq;
	case 38: return hk;
	case 39: return sa;
	case 40: return s2;
	case 41: return s3;
	case 42: return s4;
	case 43: return s5;
	case 44: return s6;
	case 45: return s7;
	case 46: return s8;
	case 47: return s9;
	case 48: return s10;
	case 49: return sj;
	case 50: return sq;
	case 51: return sk;
	case 100: return cover;
	}
	return null;
}

}
