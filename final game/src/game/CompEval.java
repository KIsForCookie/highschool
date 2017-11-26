package game;

import game.PokerCard.Rank;

public class CompEval implements CompInterface{

	@Override
	public Comparison comparison(Hand hand) {
		return null;
	}

	
	public Comparison comp(Hand hand){
		PokerCard[] myHand = new PokerCard[hand.size()];
		
		if(!hand.isFull())
			System.exit(1);
		
		for(int i  = 0; i < myHand.length; i++){
			myHand[i] = hand.getHighCard();			
			hand.hand.remove(0);
		}
		for(int i = 0; i < myHand.length; i++){
			hand.addToHand(myHand[i]);
			hand.toString();
		}
		
		if(isStraightFlush(myHand) && myHand[0].getType() == PokerCard.Rank.ACE &&
				myHand[1].getType() == PokerCard.Rank.KING){
			//System.out.println("Royal Flush");
			return new Comparison(Comparison.Category.ROYAL_FLUSH, hand);
		}
		
		if(isStraightFlush(myHand) && myHand[0].getType() == PokerCard.Rank.ACE 
				&& myHand[1].getType() == PokerCard.Rank.KING)
			return new Comparison(Comparison.Category.ROYAL_FLUSH, hand);
		
		if(isStraightFlush(myHand))
			return new Comparison(Comparison.Category.STRAIGHT_FLUSH, hand);
		
		if(isFourOfAKind(myHand))
			return new Comparison(Comparison.Category.FOUR_OF_A_KIND, hand);
		
		if(isFullHouse(myHand))
			return new Comparison(Comparison.Category.FULL_HOUSE, hand);
		
		if(isFlush(myHand))
			return new Comparison(Comparison.Category.FLUSH, hand);
		
		if(isStraight(myHand))
			return new Comparison(Comparison.Category.STRAIGHT, hand);
		
		if(isThreeOfAKind(myHand))
			return new Comparison(Comparison.Category.THREE_OF_A_KIND, hand);
		
		if(isTwoPair(myHand))
			return new Comparison(Comparison.Category.TWO_PAIR, hand);

		if(isPair(myHand))
			return new Comparison(Comparison.Category.PAIR, hand);
			
		return new Comparison(Comparison.Category.HIGH, hand);
	}
	
	private boolean isStraightFlush(PokerCard[] myHand){	
		for(int i = 0; i < myHand.length - 2; i++)	
		{
			if(myHand[i].getSuit() != myHand[i+1].getSuit())
				return false;
			
			if( myHand[0].getType() == Rank.ACE 	&& 
				myHand[1].getType() == Rank.FIVE 	&&
				myHand[2].getType() == Rank.FOUR 	&&
				myHand[3].getType() == Rank.THREE 	&&
				myHand[4].getType() == Rank.TWO)
				return true;
			
			if(myHand[i].compareCards(myHand[i+1]) != -1 )
				return false;
		}
		
		return true;
	}
	
	private boolean isFourOfAKind(PokerCard[] myHand){
		int c = 0; 
		
		if(myHand[0].compareCards(myHand[1]) == 0)
			c++;
		
		for(int i = 1; i < myHand.length - 1; i++)
			if(myHand[i].compareCards(myHand[i+1]) == 0)
				c++;
			else
				break;
		
		return (c >= 3);
	}
	
	private boolean isFullHouse(PokerCard[] myCards){
		if(myCards[0].compareCards(myCards[1]) != 0)
			return false;
		
		if(myCards[3].compareCards(myCards[4]) != 0)
			return false;
		
		if(		myCards[2].compareCards(myCards[1]) != 0
			&&  myCards[2].compareCards(myCards[3]) != 0 
		)
			return false;
	
		return true;
	}
	
	private boolean isFlush(PokerCard[] myHand){	
		for(int i = 0; i < myHand.length - 1; i++)
		{
			if(myHand[i].getSuit() != myHand[i+1].getSuit())
				return false;
		}
		
		return true;
	}
	
	private boolean isStraight(PokerCard[] myHand){
		for(int i = 0; i < myHand.length - 1; i++)	
			if(myHand[i].compareCards(myHand[i+1]) != -1)
				return false;
		
		return true;
	}
	
	private boolean isThreeOfAKind(PokerCard[] myCards){	
		if(myCards[2].compareCards(myCards[1]) != 0)		//[ ][x][x][ ][ ]
		{
			if(myCards[2].compareCards(myCards[3]) != 0)	//[ ][ ][x][x][ ]
				return false;
			
			if(myCards[2].compareCards(myCards[4]) != 0)	//[ ][ ][x][ ][x]
				return false;
		}
		else
		{
			if(		myCards[2].compareCards(myCards[3]) != 0	//[ ][ ][x][x][ ]
				&&	myCards[2].compareCards(myCards[0]) != 0)	//[x][ ][x][ ][ ]
				return false;		
		}

		return true;
	}
	
	private boolean isTwoPair(PokerCard[] myCards){	
		if(myCards[0].compareCards(myCards[1]) != 0)
		{
			if(		myCards[1].compareCards(myCards[2]) != 0 
				||	myCards[3].compareCards(myCards[4]) != 0)
				return false;
		}
		else
		{
			if(		myCards[2].compareCards(myCards[3])!= 0
				&&	myCards[3].compareCards(myCards[4])!= 0)
				return false;
		}
		
		return true;
	}
	
	private boolean isPair(PokerCard[] myCards)
	{	
		for(int i = 0; i < myCards.length - 1; i++){
			if(myCards[i].compareCards(myCards[i+1]) == 0)	
				return true;
		}
		
		return false;
	}
	
	

}
