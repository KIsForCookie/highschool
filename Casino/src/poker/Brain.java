package poker;

import java.util.ArrayList;

import poker.Card.Rank;

public class Brain{
	enum PlayStyle{	CONSERVATIVE,	//mostly call, won't open/raise unless hand is very good
					MODERATE, 		//raises on decent hands, tends to open on pair and above
					AGGRESSIVE}	//will open and bets heavily	
	PlayStyle playstyle;
	private int Handvalue, raiseAmt, betValue;
	Integer[] action = new Integer[2];
	CompEval x = new CompEval();
	ArrayList<Chip> temp = new ArrayList<Chip>();
	int playerMoney;
	boolean raised = false;
	
	Brain(Player Russel, PlayStyle playStyle, Pot tempPot, int money, Table table){
		playerMoney = money;
		setHandValue(Russel.getHand());
		
		switch(playstyle){
		case CONSERVATIVE:
			conservative(Russel, tempPot, table);
			break;
		case MODERATE:
			moderate(Russel, tempPot, table);
			break;
		case AGGRESSIVE:
			aggressive(Russel,tempPot, table);
			break;
		}
	}
	
	private void setHandValue(Hand hand){		//hand category ordinal value
		Handvalue = hand.getCategory().ordinal();
		//ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, 
		//FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR, HIGH
	}
	
	public Integer[] getAction(){
		/*action[0] = action, action[1] = bet amount
		 * 0 = check
		 * 1 = call
		 * 2 = raise
		 * 3 = all in
		 * 4 = fold
		 * 5 = discard
		 */
		return action;
	}
	
	public ArrayList<Chip> getChips(){
		return temp;
	}
	
	private void setTemp(){
		while(raiseAmt != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(raiseAmt >= lValue.getChipVal()){
					raiseAmt -= lValue.getChipVal();
					temp.add(new Chip(lValue));
					betValue += temp.get(temp.size() - 1).getValue();
				}
			}
		}
	}
	
	private void setTemp(ArrayList<Chip> chips){
		temp = chips;
	}
	
	private int getCash(Pot curPot){
		return playerMoney - curPot.minimumBet; 
	}

	private int[] discard(Player Russel){
		int[] index = new int[3];
		Integer ind1 = null;
		Integer ind2 = null;
		Integer ind3 = null;
		int value = x.comp(Russel.getHand()).getCategory().ordinal();
		switch(value){
		case 0: //royal flush, do nothing
		case 1:	//straight flush, do nothing
		case 2:	//four of a kind, do nothing
		case 3:	//full house, do nothing
		case 4:	//flush, do nothing
		case 5:	//straight, do nothing
			break;
		case 6:	//three of a kind, drop lower single
			for(int i = 0; i < Russel.getHand().size(); i++){
				if(i==2)
					continue;
				if(Russel.getHand().getCard(2).getType() != Russel.getHand().getCard(i).getType() && ind1 != null){
					ind1 = i;
				}else if(Russel.getHand().getCard(2).getType() != Russel.getHand().getCard(i).getType() && ind1 == null){
					ind2 = i;
				}
			}
			int com = Russel.getHand().getCard(ind1).compareTo(Russel.getHand().getCard(ind2));
			if(com < 0)
				index[0] = ind1;
			else if(com > 0)
				index[0] = ind2;
			break;
		case 7:	//two pairs, drop single
			if(Russel.getHand().getCard(0).compareCards(Russel.getHand().getCard(1)) != 0){
				index[0] = 0;
			}
			else if(Russel.getHand().getCard(1).compareCards(Russel.getHand().getCard(2)) != 0){
				index[0] = 2;
			}
			else if(Russel.getHand().getCard(4).compareCards(Russel.getHand().getCard(3)) != 0){
				index[0] = 4;
			}
			break;
		case 8:	//pair, drop 2 lowest cards
			for(int i = 0; i < Russel.getHand().size() - 1; i++){
				if(Russel.getHand().getCard(i).compareCards(Russel.getHand().getCard(i+1)) == 0){
					i++;
					continue;
				}
				else{
					if(ind1 == null){
						ind1 = i;
						continue;
					}
					else if(ind2 == null){
						ind2 = i;
						continue;
					}
					else if(ind3 == null){
						ind3 = i;
						continue;
					}
				}
			}
		case 9:	//high card, drop 3 lowest
			index[0] = 2;
			index[1] = 3;
			index[2] = 4;
		}
		return index;
	}
	
	private void conservative(Player Russel, Pot tempPot, Table table){	//3ofakind raise
		if(getCash(tempPot) > 0){
			if(table.round == 1){
				switch(x.comp(Russel.getHand()).getCategory()){
				case ROYAL_FLUSH:
				case STRAIGHT_FLUSH:
				case FOUR_OF_A_KIND:
				case FULL_HOUSE:
					raised = true;
					
					break;
				case FLUSH:
				case STRAIGHT:
				case THREE_OF_A_KIND:
				case TWO_PAIR:
				case PAIR:
				case HIGH:
					break;
				}
			}else{
				if(Handvalue <= 6){	//greater or equal 3 of a kind
					betValue = 0;
					if(Handvalue == 6){
						raiseAmt = ((int)(((getCash(tempPot)* 3) / 8) + (int)(Math.random() * playerMoney / 4) / 25)) * 25;
						//minimum 3/8 of total money, max of 5/8
						action[0] = 2;
						action[1] = raiseAmt;
					}
					else if(Handvalue == 6){	//three of a kind
						raiseAmt = ((int)(((getCash(tempPot) * 3) / 5) + (int)(Math.random() * playerMoney / 5) / 25)) * 25;
						//minimum 3/5 of total money, max of 4/5
						action[0] = 2;
						action[1] = raiseAmt;
						
					}
					else if(Handvalue < 5){	//flush and above
						action[0] = 3;
						action[1] = (Integer) null;
					}
				}
				else if(Handvalue > 6){	//two pair and below
					int chance = (int)Math.random() * 50;
					double chanceMult = 1;
					int raisechance;
					int raise = (int)Math.random() * 100;
					int callchance = 0;
					
					if(Handvalue == 7){	//two pair
						chanceMult = 2;
						raiseAmt = ((int)(((getCash(tempPot) * 2) / 8) + (int)((Math.random() * (playerMoney * 1) / 8) / 25))) * 25;
						//1/4 to 3/8
					}
					else if(Handvalue == 8){	//pair
						chanceMult = 1.5;
						raiseAmt = ((int)(((getCash(tempPot) * 2) / 8) + (int)((Math.random() * (playerMoney * 1) / 8) / 25))) * 25;
					}
					else{	//high card
						if(Russel.getHand().getHighCard().getType().ordinal() < 2){	//ace or king
							chanceMult = 1;
							raiseAmt = ((int)(((getCash(tempPot) * 1) / 16) + (int)(Math.random() * (playerMoney * 1) / 8)) / 25) * 25;
							//1/16 to 3/16
						}
					}
					
					raisechance = (int)(chance * chanceMult);
					if(raise <= raisechance){
						
						switch(Russel.getHand().getHighCard().getType()){
						case ACE:
						case KING:
							action[0] = 2;	//raise
							action[1] = raiseAmt;
							break;
							
						case QUEEN:
						case JACK:
						case TEN:
						case NINE:
						case EIGHT:
						case SEVEN:
							action[0] = 1;	//call
							action[1] = null;
							break;
							
						case SIX:
						case FIVE:
						case FOUR:
						case THREE:
						case TWO:
							action[0] = 4;	//fold
							action[1] = null;
							break;
						}
					}
					else if(raise <= raisechance + callchance){
						action[0] = 1;
					}
					else{
						action[0] = 0;
					}
				}
			}
		}else{
			action[0] = 4;
		}
		setTemp();
	}

	private void moderate(Player Russel, Pot tempPot, Table table){		//2pair raise


	}
	
	private void aggressive(Player Russel, Pot tempPot, Table table){		//face pair or Ace high raise
		
		
	}
}
