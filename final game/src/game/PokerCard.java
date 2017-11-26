package game;

import game.PokerCard.Rank;
import game.PokerCard.Suit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PokerCard implements Comparable<PokerCard>{
	private final Suit suit;
	private final Rank cardtype; 
	
	private String name;
	private String suitName;
	private String valName;
	BufferedImage img;			
	static BufferedImage back;
	
	int cardRank;
	
	public enum Suit	{SPADES, HEARTS, DIAMONDS, CLUBS};
	public enum Rank	{ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, 
						 SEVEN, SIX, FIVE, FOUR, THREE, TWO};
						 
	public PokerCard(Rank aRank, Suit aSuit){
	
		cardtype = aRank;
		suit = aSuit;
		
		suitName = suit.name().toLowerCase();	//all to lowercase
		suitName = suitName.substring(0, 1).toUpperCase() + suitName.substring(1);	//capitalize first letter
		
		valName = cardtype.name().toLowerCase();
		valName = valName.substring(0, 1).toUpperCase() + valName.substring(1);
		name = valName + " of " + suitName;
		cardRank = 13 * suit.ordinal() + cardtype.ordinal();
		
		setImageSFW();
	}
	


	public Rank getType(){
		return cardtype;
	}
	
	public Suit getSuit(){
		return suit;
	}

	public static PokerCard newCard(String card, Suit suit){
		return new PokerCard(Rank.valueOf(card), suit);
	}
	
	public String toString(){
		return name;
		
	}
	
	public int compareCards(PokerCard c){
	/*	if((getType().ordinal() == c.getType().ordinal())){
			return (getSuit().ordinal() - c.getSuit().ordinal());
		}
		else*/
		
		int n = getType().ordinal() - c.getType().ordinal();
			return n;
		
	}

	@Override
	public int compareTo(PokerCard c) {		//compareTo is compareCards
		if((getType().ordinal() == c.getType().ordinal()))
			return (getSuit().ordinal() - c.getSuit().ordinal());
		else
			return getType().ordinal() - c.getType().ordinal();
	}
	

	public void setImage(){
		try{
			back = ImageIO.read(new File("images/back_1.jpg"));
		}catch(Exception IOException){}	
		img = null;
		if(suit == Suit.CLUBS){
				switch(cardtype){
				case ACE:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_Ace.jpg")); 
						break;
						}catch(Exception IOException){}
				case KING:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_King.jpg")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_Queen.jpg")); 
						break;
						}catch(Exception IOException){}
				case JACK:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_Jack.jpg")); 
						break;
						}catch(Exception IOException){}
				case TEN:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_10.jpg")); 
						break;
						}catch(Exception IOException){}
				case NINE:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_9.jpg")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_8.jpg")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_7.jpg")); 
						break;
						}catch(Exception IOException){}
				case SIX:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_6.jpg")); 
						break;
						}catch(Exception IOException){}
				case FIVE:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_5.jpg")); 
						break;
						}catch(Exception IOException){}
				case FOUR:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_4.jpg")); 
						break;
						}catch(Exception IOException){}
				case THREE:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_3.jpg")); 
						break;
						}catch(Exception IOException){}
				case TWO:	
					try{
						img = ImageIO.read(new File("images/Clubs/Clubs_2.jpg")); 
						break;
					}catch(Exception IOException){}
				}
		}
		if(suit == Suit.HEARTS){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_Ace.jpg")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_King.jpg")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_Queen.jpg")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_Jack.jpg")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_10.jpg")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_9.jpg")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_8.jpg")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_7.jpg")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_6.jpg")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_5.jpg")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_4.jpg")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_3.jpg")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/Hearts/Hearts_2.jpg")); 
						break;
						}catch(Exception IOException){}
				}
		}
		if(suit == Suit.DIAMONDS){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_Ace.jpg")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_King.jpg")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_Queen.jpg")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_Jack.jpg")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_10.jpg")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_9.jpg")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_8.jpg")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_7.jpg")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_6.jpg")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_5.jpg")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_4.jpg")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_3.jpg")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/Diamonds/Diamonds_2.jpg")); 
						break;
						}catch(Exception IOException){}
				}
		}
		if(suit == Suit.SPADES){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_Ace.jpg")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_King.jpg")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_Queen.jpg")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_Jack.jpg")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_10.jpg")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_9.jpg")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_8.jpg")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_7.jpg")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_6.jpg")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_5.jpg")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_4.jpg")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_3.jpg")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/Spades/Spades_2.jpg")); 
						break;
						}catch(Exception IOException){}
				}
		}		
		
		
		

	}
	
	public void setImageSFW(){
		try{
			back = ImageIO.read(new File("images/_SFW Cards/back.png"));
		}catch(Exception e){}	
		img = null;
		if(suit == Suit.CLUBS){
				switch(cardtype){
				case ACE:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c1.png")); 
						break;
						}catch(Exception IOException){}
				case KING:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/ck.png")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/cq.png")); 
						break;
						}catch(Exception IOException){}
				case JACK:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/cj.png")); 
						break;
						}catch(Exception IOException){}
				case TEN:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c10.png")); 
						break;
						}catch(Exception IOException){}
				case NINE:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c9.png")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c8.png")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c7.png")); 
						break;
						}catch(Exception IOException){}
				case SIX:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c6.png")); 
						break;
						}catch(Exception IOException){}
				case FIVE:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c5.png")); 
						break;
						}catch(Exception IOException){}
				case FOUR:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c4.png")); 
						break;
						}catch(Exception IOException){}
				case THREE:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c3.png")); 
						break;
						}catch(Exception IOException){}
				case TWO:	
					try{
						img = ImageIO.read(new File("images/_SFW Cards/c2.png")); 
						break;
					}catch(Exception IOException){}
				}
			}
		if(suit == Suit.HEARTS){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h1.png")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/hk.png")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/hq.png")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/hj.png")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h10.png")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h9.png")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h8.png")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h7.png")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h6.png")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h5.png")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h4.png")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h3.png")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/h2.png")); 
						break;
						}catch(Exception IOException){}
				}
		}
		if(suit == Suit.DIAMONDS){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d1.png")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/dk.png")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/dq.png")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/dj.png")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d10.png")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d9.png")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d8.png")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d7.png")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d6.png")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d5.png")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d4.png")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d3.png")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/d2.png")); 
						break;
						}catch(Exception IOException){}
				}
		}
		if(suit == Suit.SPADES){
				switch(cardtype){
				case ACE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s1.png")); 
						break;
						}catch(Exception IOException){}
				case KING:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/sk.png")); 
						break;
						}catch(Exception IOException){}
				case QUEEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/sq.png")); 
						break;
						}catch(Exception IOException){}
				case JACK:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/sj.png")); 
						break;
						}catch(Exception IOException){}
				case TEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s10.png")); 
						break;
						}catch(Exception IOException){}
				case NINE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s9.png")); 
						break;
						}catch(Exception IOException){}
				case EIGHT:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s8.png")); 
						break;
						}catch(Exception IOException){}
				case SEVEN:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s7.png")); 
						break;
						}catch(Exception IOException){}
				case SIX:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s6.png")); 
						break;
						}catch(Exception IOException){}
				case FIVE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s5.png")); 
						break;
						}catch(Exception IOException){}
				case FOUR:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s4.png")); 
						break;
						}catch(Exception IOException){}
				case THREE:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s3.png")); 
						break;
						}catch(Exception IOException){}
				case TWO:
					try{
						img = ImageIO.read(new File("images/_SFW Cards/s2.png")); 
						break;
						}catch(Exception IOException){}
				}
		}		
		
		
		

	}

	void setImages(){
		
	}
	
	public BufferedImage getImage() {
		return img;
	}

	public BufferedImage getBack() {
		return back;
	}

	
	
	
	

}
