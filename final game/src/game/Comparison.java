package game;

import game.PokerCard;
import game.Hand;

public class Comparison implements Comparable<Comparison>{
	
	public enum Category{ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, 
		FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR, HIGH;}
	private Hand handOrder = new Hand();
	private Category handVal;
	private PokerCard[] topCards;
	
	public Comparison(Category c, Hand hand){
		if(c == null || hand == null)
			throw new NullPointerException();
		
		PokerCard[] myHand = new PokerCard[5];
		
		handVal = c;
		topCards = new PokerCard[5];
		
		for(int i = 0; i < topCards.length; i++){
			topCards[i] = null;
		}
		//hand.Sort();
		//System.out.print(hand.toString() + "\n");
		for(int i = 0; i < hand.size(); i++){	
			if(hand.hand.get(i) != null){
				myHand[i] = hand.hand.get(i);
			}
				//System.out.println(i + " == null");
			
			//System.out.println(myHand[i].toString());
		}
		hand.clear();
		
		for(int i = 0; i < myHand.length; i++){
			hand.addToHand(myHand[i]);
		}
		
		switch(handVal){
		
		case ROYAL_FLUSH:
			setStraightFlush(myHand);
			hand.setCategory(Category.ROYAL_FLUSH);
			break;
		
		case STRAIGHT_FLUSH: 
			setStraightFlush(myHand); 
			hand.setCategory(Category.STRAIGHT_FLUSH);
			break;
			
		case FOUR_OF_A_KIND:
			setFourOfAKind(myHand);
			hand.setCategory(Category.FOUR_OF_A_KIND);
			break;
			
		case FULL_HOUSE:
			setFullHouse(myHand);
			hand.setCategory(Category.FULL_HOUSE);
			break;
			
		case FLUSH:
			setFlush(myHand);
			hand.setCategory(Category.FLUSH);
			break;
			
		case STRAIGHT:
			setStraight(myHand);
			hand.setCategory(Category.STRAIGHT);
			break;
			
		case THREE_OF_A_KIND:
			setThreeOfAKind(myHand);
			hand.setCategory(Category.THREE_OF_A_KIND);
			break;
			
		case TWO_PAIR:
			setTwoPair(myHand);
			hand.setCategory(Category.TWO_PAIR);
			break;
			
		case PAIR:
			setPair(myHand);
			hand.setCategory(Category.PAIR);
			break;
			
		case HIGH:
			setHighCard(myHand);
			hand.setCategory(Category.HIGH);
			break;
		}//switch(handVal)
		hand.setCompareOrder(getHandOrder());
	}
	
	public Hand returnHand(){
		return handOrder;
	}
	
	public Category getCategory(){
		return handVal;
	}
	
	public PokerCard getCard(int i) throws IllegalArgumentException{
		if(i < 0 || i > topCards.length)
			throw new IllegalArgumentException();
		
		return topCards[i];
	}
	
	@Override
	public int compareTo(Comparison comp) throws NullPointerException{
		if(getCategory() == null||comp.getCategory() == null||topCards == null)
			throw new NullPointerException();
		
		int toReturn = getCategory().compareTo(comp.getCategory());
		if(toReturn != 0)
			return toReturn;
		
		for(int i = 0; getCard(i) != null; i++){
			toReturn = getCard(i).compareTo(comp.getCard(i));
			
			if(toReturn != 0)
				return toReturn;
		}		
		
		return 0;
	};
	
	private void setStraightFlush(PokerCard[] myHand){
		for(int i = 0; i < myHand.length; i++){
			topCards[i] = myHand[i];
		}
		setHandOrder();
	}
	
	private void setFourOfAKind(PokerCard[] myHand){
		topCards[0] = myHand[2];
		if(myHand[0].getType() == myHand[2].getType())
			topCards[1] = myHand[4];
		else
			topCards[1] = myHand[0];
		setHandOrder();
	}
	
	private void setFullHouse(PokerCard[] myHand){
		topCards[0] = myHand[2];
		
		if(myHand[0].compareCards(myHand[2]) == 0)
			topCards[1] = myHand[4];
		else
			topCards[1] = myHand[0];
		setHandOrder();
	}
	
	private void setFlush(PokerCard[] myHand){
		for(int i = 0; i < 5; i++)
			topCards[i] = myHand[i];
				setHandOrder();
	}
	
	private void setStraight(PokerCard[] myHand){
		for(int i = 0; i < myHand.length; i++){
			topCards[i] = myHand[i];
		}
		setHandOrder();
	}
	
	private void setThreeOfAKind(PokerCard[] myHand){
		topCards[0] = myHand[2];
		Integer index1 = null;
		Integer index2 = null;
		for(int i = 0; i < myHand.length - 1; i++){
			if(myHand[i].compareCards(myHand[i+1]) != 0 && index1 == null){
				index1 = i;
			}
			else if(myHand[i].compareCards(myHand[i+1]) != 0 && index1 != null){
				index2 = i;
			}
		}
		topCards[1] = myHand[index1];
		topCards[2] = myHand[index2];
		setHandOrder();
	}
	
	private void setTwoPair(PokerCard[] myHand){		
		
		Integer index1 = null;
		Integer index2 = null;
		for(int i = 0; i < myHand.length - 1; i++){
			
			if(myHand[i].compareCards(myHand[i+1]) == 0 && index1 == null){
				index1 = i;
			}
			else if(myHand[i].compareCards(myHand[i+1]) == 0 && index1 != null){
				index2 = i;
			}
		}
		topCards[0] = myHand[index1];	//larger card in pair
		topCards[1] = myHand[index2];	//smaller card in pair
		int i = 0;
		while(topCards[2] == null){
			if(i == index1 || i == index1 + 1 || i == index2 || i == index2 + 1){
				i++;
				continue;
			}
			else{
				topCards[2] = myHand[i];	//single card
				break;
			}
		}
		setHandOrder();
	}

	private void setPair(PokerCard[] myHand){
		Hand hand = new Hand();
		for(int i = 0; i < myHand.length; i++){
			hand.addToHand(myHand[i]);
		}
		
		hand.Sort();
		for(int i = 0; i < hand.size() - 1; i++){
			if(hand.getCard(i).compareCards(hand.getCard(i + 1)) == 0){
				hand.remove(hand.getCard(i+1));
				topCards[0] = hand.getCard(i);
				hand.remove(hand.getCard(i));
			}
		}
		for(int i = 0; i < hand.size(); i++)
			topCards[i+1] = hand.getCard(i);
		
		setHandOrder();
		/*
		for(int i = 0; i < hand.size(); i++){
			for(int j = 0; j < hand.size(); j++){
				if(i == j)
					continue;
				
				if(hand.getCard(i).getType() == hand.getCard(j).getType()){
					int m = hand.getCard(i).compareTo(hand.getCard(j));
					if(m > 0){
						hand.remove(hand.getCard(i));
					}
					else if(m < 0){
						hand.remove(hand.getCard(j));
					}
				}
			}
		}
		
		for(int i = 0; i < hand.size(); i++){
			topCards[i] = hand.getCard(i);
		}
		*/
		
		//System.out.println("\n" + hand.toString());
		/*
		for(int i = 0; i < myHand.length - 1; i++){
			if(myHand[i] != null)
				System.out.println(myHand[i].toString());
			else 
				System.out.println("index " + i + " == null");
		}*/
		
		/*
		hand.Sort();
		
		for(int i = 0; i < hand.size() - 2; i++){
			if(hand.hand.get(i).compareCards(hand.hand.get(i + 1)) == 0){
				if(hand.hand.get(i).compareTo(hand.hand.get(i + 1)) > 0)	//one suit is "bigger" than the other,
					hand.hand.remove(i + 1);								//so whichever is "smaller" is removed
				else
					hand.hand.remove(i);
				break;
			}
		}
		for(int i = 0; i < hand.size() - 1; i++){
			topCards[i] = hand.hand.get(i);
		}
		
	*/	
	}
	
	private void setHighCard(PokerCard[] myHand){
		for(int i = 0; i < myHand.length; i++){
			topCards[i] = myHand[i];
		}
		setHandOrder();
	}
	
	private void setHandOrder(){
		for(int i = 0; i < topCards.length; i++){
			handOrder.addToHand(topCards[i]);
		}
	}
	
	public Hand getHandOrder(){
		return handOrder;
	}
	
	public String toString(){
		String plural = "s";
		String Return;
		
		switch(getCategory()){
		case HIGH: 
			return topCards[0].getType() + " High, " + topCards[1].getType() + ", " +
				topCards[2].getType() + ", " + topCards[3].getType() + " & " + topCards[4].getType();
		case PAIR:
			if(topCards[0].getType() == PokerCard.Rank.SIX)
				plural = "es";
			
			return "Pair of " + topCards[0].getType() + plural + " & " + topCards[1].getType() +
					", " + topCards[2].getType() + " & " + topCards[3].getType();
		case TWO_PAIR:
			if(topCards[0].getType() == PokerCard.Rank.SIX)
				plural = "es";
			Return = topCards[0].getType() + plural + " over ";
			if(topCards[1].getType() == PokerCard.Rank.SIX)
				plural = "es";
			else
				plural = "s";
			
			Return += topCards[1].getType() + plural + " & a " + topCards[2].getType();
			
			return Return;
			
		case THREE_OF_A_KIND:
			if(topCards[0].getType() == PokerCard.Rank.SIX)
				plural = "es";
			
			return "Three " + topCards[0].getType() + plural;
			
		case STRAIGHT:
			return "Straight to " + topCards[0].getType();
			
		case FLUSH:
			return "Flush";
			
		case FULL_HOUSE:
			if(topCards[0].getType() == PokerCard.Rank.SIX)
				plural = "es";
			
			Return = topCards[0].getType() + plural + " over ";
			
			if(topCards[1].getType() == PokerCard.Rank.SIX)
				plural = "es";
			else 
				plural = "s";
			
			Return += topCards[1].getType() + plural;
			
			return Return;
			
		case FOUR_OF_A_KIND:
			if(topCards[0].getType() == PokerCard.Rank.SIX)
				plural = "es";
			return "Four " + topCards[0].getType() + plural;
			
		case STRAIGHT_FLUSH:
			if(topCards[0].getType() == PokerCard.Rank.ACE && topCards[1].getType() == PokerCard.Rank.KING){
				Return = "Royal Flush";
			}
			else
				Return = "Straight flush to " + topCards[0].getType();
			return Return;
		case ROYAL_FLUSH:
			break;
		default:
			break;
		}
		
		return ""+handVal;
	}
	
}
