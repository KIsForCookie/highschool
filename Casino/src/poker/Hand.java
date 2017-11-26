package poker;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import poker.Card;
import poker.Comparison.Category;

public class Hand implements Iterable<Card>, Cloneable{

	public ArrayList<Card> hand;
	ArrayList<Card> compareOrder;
	private final int HAND_SIZE = 5;
	
	CompEval x = new CompEval();
	Category category;
	Card compareCard;	
		
	public Hand(){
		hand  = new ArrayList<Card>(HAND_SIZE);
		compareOrder  = new ArrayList<Card>(HAND_SIZE);
	}
	
	void getHand(){
		System.out.println(hand);
	}
	
	public void setCategory(Category c){
		category = c;
	}
	
	private void setCompareOrder(ArrayList<Card> pCards){
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
		n = getCategory().ordinal() - h.getCategory().ordinal();
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
	
	public void remove( Card pCard ){
		hand.remove(pCard);
	}
	
	public void addToHand(Card c){
		try{	
			hand.add(c);
		}catch(Exception NullPointerException){
			System.out.println("couldn't find card");
		}
		//Sort();
	}
	
	void discard(Card c){
		try{
			if(hand.size() != 0) 
				hand.remove(c);
		}catch(Exception NullPointerException){
			System.out.println("Not a card");
		}
		
	}
	
	public Card getCard(int index){
		if(index < hand.size())
			return hand.get(index);
		else
			return null;
	}
	
	public Iterator<Card> iterator(){
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
		
		for( Card c : hand ){
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
	
	public Card getHighCard(){
		Sort();
		return hand.get(0);
	}
	
	public void Sort(){
		ArrayList<Card> temp = new ArrayList<Card>();
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
	
		class Compare implements Comparator<Card>{

			@Override
			public int compare(Card c1, Card c2) {
				//System.out.println(c1.toString());
				//System.out.println(c2.toString());
				if(c1 != null && c2 != null)
					return c1.compareTo(c2);
				
				return 0;
			}
			
		}
	
	
	
	
	
}
