package game;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import game.PokerCard;
import game.Comparison.Category;

public class Hand implements Iterable<PokerCard>, Cloneable{

	public ArrayList<PokerCard> hand;
	public ArrayList<PokerCard> compareOrder;
	private final int HAND_SIZE = 5;
	
	CompEval x = new CompEval();
	
	Comparison.Category category;
	PokerCard compareCard;	
		
	public Hand(){
		hand  = new ArrayList<PokerCard>(HAND_SIZE);
		compareOrder  = new ArrayList<PokerCard>(HAND_SIZE);
	}
	
	void getHand(){
		System.out.println(hand);
	}
	
	public void setCategory(Category c){
		category = c;
	}
	
	private void setCompareOrder(ArrayList<PokerCard> pCards){
		compareOrder.clear();
		for(int i = 0; i < pCards.size(); i++){
			if(pCards.get(i) != null){
				compareOrder.add(pCards.get(i));
			}
		}
	}
	
	public void setCompareOrder(Hand h){
		compareOrder.clear();
		for(int i = 0; i < h.size(); i++){
			if(h.getCard(i) != null)
				compareOrder.add(h.getCard(i));
		}
	}
	
	public int CompareHand(Hand h){
		int n = 0;
		int i = 0;
		n = getCategory().ordinal() - h.getCategory().ordinal();	//lower wins
		System.out.println("hand compare: " + n);
		while(n == 0){
			n = compareOrder.get(i).getType().ordinal() - (h.compareOrder.get(i).getType().ordinal());
			i++;
		}
		
		System.out.println(h.compareOrder.get(i));
		System.out.println(compareOrder.get(i));
		return n;
	}
	
	public Category getCategory(){
		return category;
	}
	
	public void remove( PokerCard pCard ){
		hand.remove(pCard);
	}
	
	public void addToHand(PokerCard c){
		try{	
			hand.add(c);
		}catch(Exception NullPointerException){
			System.out.println("couldn't find card");
		}
		//Sort();
	}
	
	void discard(PokerCard c){
		try{
			if(hand.size() != 0) 
				hand.remove(c);
		}catch(Exception NullPointerException){
			System.out.println("Not a card");
		}
		
	}
	
	public PokerCard getCard(int index){
		if(index < hand.size())
			return hand.get(index);
		else
			return null;
	}
	
	public Iterator<PokerCard> iterator(){
		return hand.iterator();
	}
	
	public boolean isFull(){
		return hand.size() == HAND_SIZE;
	}
	
	public void clear(){
		hand.clear();
	}
	
	public String toString(){
		String lReturn = "{";
		int j = 0;
		Sort();
		
		for( PokerCard c : hand ){
			j++;
			
			if(c == null)
				continue;
			
			lReturn += c.toString();
			
			if(j < hand.size())
				lReturn += ", ";
		}
		lReturn += "}";
		return lReturn;
	
	}
	
	public int size(){
		return hand.size();
	}
	
	public PokerCard getHighCard(){
		Sort();
		return hand.get(0);
	}
	
	public void Sort(){
		ArrayList<PokerCard> temp = new ArrayList<PokerCard>();
		for(int i = 0; i < hand.size(); i++){
			temp.add(null);
		}
		
		Compare com = new Compare();
		int k = 0;
		
		for(int i = 0; i < hand.size(); i++){
			k = 0;
			
			for(int j = 0; j < hand.size(); j++){
				if(j == i)
					continue;
				
				int m = com.compare(hand.get(i), hand.get(j));
				
				if(m > 0){
					k++;
				}
				//System.out.print(k + ", ");
				
			}
			temp.set(k, hand.get(i));
			//System.out.println(temp.toString());
		}
		hand.clear();
		hand.addAll(temp);
		//System.out.println("hello world");
		
		
		//Collections.sort(hand, new Compare());
		
		//Collections.sort(hand);
	}
	
		class Compare implements Comparator<PokerCard>{

			@Override
			public int compare(PokerCard c1, PokerCard c2) {
				//System.out.println(c1.toString());
				//System.out.println(c2.toString());
				if(c1 != null && c2 != null)
					return c1.compareTo(c2);
				
				return 0;
			}
			
		}
	
	
	
	
	
}
