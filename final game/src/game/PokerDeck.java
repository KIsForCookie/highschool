package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import game.PokerCard;
import game.PokerCard.Rank;
import game.PokerCard.Suit;

public class PokerDeck {
	private static final int DECK_SIZE = 52;
	public Stack<PokerCard> deck;
	public static ArrayList<PokerCard> tempDeck = new ArrayList<PokerCard>(DECK_SIZE);
	
	public PokerDeck(){
		deck  = new Stack<PokerCard>();
		shuffle();
	}
	
	public void reset(){
		deck.clear();
		
		for( Suit lSuit : Suit.values()){	//for-each loop
            for( Rank lRank : Rank.values()){	//for-each loop
            	//tempDeck.add(new Card(lRank, lSuit));
            	
            	
            	deck.push(new PokerCard(lRank, lSuit));
            	//System.out.println(lRank + " " + lSuit);
            }
		}
		
	}
	
	public void shuffle(){
		reset();
		Collections.shuffle(deck);
	}
	
	public PokerCard getTopCard(){
		return deck.peek();
	}
	
	public PokerCard Deal(){
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
