package poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import poker.Card;
import poker.Card.Rank;
import poker.Card.Suit;

public class Deck {
	private static final int DECK_SIZE = 52;
	public Stack<Card> deck;
	public static ArrayList<Card> tempDeck = new ArrayList<Card>(DECK_SIZE);
	
	public Deck(){
		deck  = new Stack<Card>();
		shuffle();
	}
	
	public void reset(){
		deck.clear();
		
		for( Suit lSuit : Suit.values()){	//for-each loop
            for( Rank lRank : Rank.values()){	//for-each loop
            	//tempDeck.add(new Card(lRank, lSuit));
            	
            	
            	deck.push(new Card(lRank, lSuit));
            	//System.out.println(lRank + " " + lSuit);
            }
		}
		
	}
	
	public void shuffle(){
		reset();
		Collections.shuffle(deck);
	}
	
	public Card getTopCard(){
		return deck.peek();
	}
	
	public Card Deal(){
		if(deck.size() > 0)
			return deck.pop();
		else 
			shuffle();
		return deck.pop();
	}
	
	public int size(){
		return deck.size();
	}
}
