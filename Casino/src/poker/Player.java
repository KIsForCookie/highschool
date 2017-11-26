package poker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import poker.Chip.Value;
import poker.Hand;
import poker.Brain.PlayStyle;

public class Player{
	
	protected String firstName;
	protected String lastName;
	protected String name;
	public int playerNum = 0;
	public Hand playerCards = new Hand();
	private int betValue = 0;
	private ArrayList<Chip> chips = new ArrayList<Chip>();
	boolean isCompPlayer = false;
	boolean folded = false;
	private int discards = 0;

	//private Brain playerBrain;
	Pot tempPot;
	Table tAccess = new Table();
	boolean stay = true;
	private String[] firsName = {"John", "Jane", "Daniel", "Alex", "Andrew", "Anne", "Mary", "Michael"};
	private String[] lasName = {"Smith", "Brown", "Olsen", "Jones", "Patel", "Kim", "Lee", "Kruschev"};
	private Integer[] indices = new Integer[3];
	
	private CompEval comparator = new CompEval();
	
	private boolean isEnabled = false;
	boolean isAllIn = false;
	
	private Integer Money;
	
	public Player(){}
	int clubs = 0, spades = 0, hearts = 0, diamonds = 0;
	
	public int getBetTotal(ArrayList<Chip> chips){
		int Return = 0;
		for(int i = 0; i < chips.size(); i++){
			Return += chips.get(i).getValue();
		}
		return Return;
	}
	
	public void generateName(){
		int f = (int) (Math.random() * firsName.length);
		int l = (int) (Math.random() * lasName.length);
		firstName = firsName[f];
		lastName = lasName[l];
	}
	
	/*
	public void createBrain(){
		int n = (int)(Math.random() * 95);
		if(isCompPlayer == true){
			if(n < 30)		//0 - 29
				playerBrain = new Brain(this, Brain.PlayStyle.CONSERVATIVE, tempPot, Money, PokerPanel.table); 
			else if(n < 70)	//30 - 69
				playerBrain = new Brain(this, Brain.PlayStyle.MODERATE, tempPot, Money, PokerPanel.table);		
			else if(n < 95)	//70 - 94
				playerBrain = new Brain(this, Brain.PlayStyle.AGGRESSIVE, tempPot, Money, PokerPanel.table);	
		}
	}
	
	void setBrain(Brain brain){
		playerBrain = brain;
	}
	*/
	
	public void setChips(int raiseVal){	
		int j = 25 * ((int)(raiseVal / 25));
		while(j%25 != 0){
			j--;
		}
		while(j != 0){
			
			for(Chip.Value lValue: Chip.Value.values()){
				while(j >= lValue.getChipVal()){
					chips.add(new Chip(lValue));
					
					j -= lValue.getChipVal();
					System.out.println("chip value is" + lValue.getChipVal());
				}
			}
		}
	}
	
	public Player(String fn, String ln, int cash, int currentPlayers){
		firstName = fn;
		lastName = ln;
		name = firstName + " " + lastName;
		setMoney(cash);
		setPlayerNum(currentPlayers + 1);
	}
	
	public Player(int currentPlayers){
		generateName();
		name = firstName + " " + lastName;
		setMoney((int)(Math.random() * 25000 + 3000));
		setPlayerNum(currentPlayers);
	}
	
	public void setCompPlayer(){
		isCompPlayer = true;
	}
	
	public void notCompPlayer(){
		isCompPlayer = false;
	}
	
	public boolean isCompPlayer(){
		return isCompPlayer;
	}

	public void setCompareOrder(Hand h){
		playerCards.setCompareOrder(h);
	}
	
	public void Discard(int i, Card c){
		if(discards<3){
			playerCards.remove(playerCards.getCard(i - 1));
			playerCards.addToHand(c);
			playerCards.setCompareOrder(comparator.comp(playerCards).returnHand());
			discards++;
		}
	}
	
	public void resetDiscards(){
		discards = 0;
	}
	
	public void setHandCat(Comparison.Category category){
		playerCards.setCategory(category);
	}
	
	public void addCard(Card c){
		try{
			playerCards.addToHand(c);
		}catch(Exception NullPointerException){
			System.out.println("hello world");
		}
		playerCards.Sort();
	}
	
	public void setPlayerNum(int size){
		playerNum = size + 1;
	}
	
	public void enable(){
		isEnabled = true;
	}
	
	public void disable(){
		isEnabled = false;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
	
	public Hand getHand(){
		return playerCards;
	}
	
	public boolean leaveTable(){
		return stay;
	}
	
	public void leave(){
		stay = false;
	}
	
	public String getName(){
		return name;
	}
	
	public String handToString(){
		return playerCards.toString();
		
	}
	
	void reset(){
		folded = false;
		action[0] = null;
		action[1] = null;
		chips.clear();
		playerCards.clear();
	}
	
	//------------------------------------------_Betting_------------------------------------------\\
	public void clearChips(){
		chips.clear();
	}
	
	private void setChips(ArrayList<Chip> input){
		chips.clear();
		for(int i = 0; i < input.size(); i++){
			chips.add(input.get(i));
		}
	}
	
	public ArrayList<Chip> getChips(){
		return chips;
	}
	
	public String fold(){
		betValue = 0;
		isAllIn = false;
		folded = true;
		//playerCards.clear();
		String foldString = (name + " has folded.");
		return foldString;
	}
	
	public void check(){
		betValue = 0;
		isAllIn = false;
		if(tAccess.getPot().betsMade != false){
			System.out.println("Check");
		}
	}
		
	public void call(){
		betValue = 0;
		int Bet = tAccess.getPot().minimumBet;
		ArrayList<Chip> temp = new ArrayList<Chip>();
		
		if(Bet % 25 != 0){
			Bet = (int)(Bet / 25) * 25;
		}
		
		if(Bet < getMoney()){
			isAllIn = false;
			while(Bet != 0){
				for(Chip.Value lValue : Chip.Value.values()){
					while(Bet >= lValue.getChipVal()){
						Bet -= lValue.getChipVal();
						temp.add(new Chip(lValue));
						betValue += (new Chip(lValue).getValue());
					}
				}
			}
			setMoney(getMoney() - betValue);
			setChips(temp);
			tAccess.called();
		}
		
	}

	public void AllIn(){
		betValue = 0;
		isAllIn = true;
		ArrayList<Chip> temp = new ArrayList<Chip>();
		int money = (int)(getMoney() / 25) * 25;
		
		while(money != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(money >= lValue.getChipVal()){
					money -= lValue.getChipVal();
					temp.add(new Chip(lValue));
					betValue += (new Chip(lValue).getValue());
				}
			}
		}
		setMoney(getMoney() - betValue);
		setChips(temp);
	}
	
	public ArrayList<Chip> AllInRet(){
		betValue = 0;
		isAllIn = true;
		ArrayList<Chip> temp = new ArrayList<Chip>();
		int money = (int)(getMoney() / 25) * 25;
		
		while(money != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(money >= lValue.getChipVal()){
					money -= lValue.getChipVal();
					temp.add(new Chip(lValue));
					betValue += (new Chip(lValue).getValue());
				}
			}
		}
		setMoney(getMoney() - betValue);
		setChips(temp);
		return temp;
	}

	public void raise(){
		int betRaise = 0;
		for(int i = 0; i < getChips().size(); i++){
			betRaise += getChips().get(i).getValue();
		}
		
		betRaise += tAccess.getPot().minimumBet;
		betRaise = (int)(getMoney() / 25) * 25;
		ArrayList<Chip> temp = new ArrayList<Chip>();
			//betValue = tAccess.getPot().minimumBet;
		
		isAllIn = false;
		while(betRaise != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(betRaise >= lValue.getChipVal()){
					betRaise -= lValue.getChipVal();
					temp.add(new Chip(lValue));
					betValue += (new Chip(lValue).getValue());
				}
			}
		}
		setMoney(getMoney() - betValue);
		setChips(temp);
		tAccess.raised();
	}

	public void betChip(Chip chip){
		if(chip.getValue() <= Money){
			chips.add(chip);
			Money -= chip.getValue();
		}
	}
	
	public int callSize(){
		betValue = 0;
		int Bet = tAccess.getPot().minimumBet;
		ArrayList<Chip> temp = new ArrayList<Chip>();
		
		if(Bet % 25 != 0){
			Bet = (int)(Bet / 25) * 25;
		}
		
		if(Bet < getMoney()){
			isAllIn = false;
			while(Bet != 0){
				for(Chip.Value lValue : Chip.Value.values()){
					while(Bet >= lValue.getChipVal()){
						Bet -= lValue.getChipVal();
						temp.add(new Chip(lValue));
						betValue += (new Chip(lValue).getValue());
					}
				}
			}
			setChips(temp);
		}
		return temp.size();
	}

	public int raiseSize(int bet){
		ArrayList<Chip> temp = new ArrayList<Chip>();
		
		bet += tAccess.getPot().minimumBet;
		
			betValue = tAccess.getPot().minimumBet;
			isAllIn = false;
			while(bet != 0){
				for(Chip.Value lValue : Chip.Value.values()){
					while(bet >= lValue.getChipVal()){
						bet -= lValue.getChipVal();
						temp.add(new Chip(lValue));
						betValue += (new Chip(lValue).getValue());
					}
				}
			}
			setChips(temp);
			return temp.size();
	}

	public int AllInSize(){
		betValue = 0;
		isAllIn = true;
		ArrayList<Chip> temp = new ArrayList<Chip>();
		int money = (int)(getMoney() / 25) * 25;
		setMoney(getMoney() - money);
		
		while(money != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(money >= lValue.getChipVal()){
					money -= lValue.getChipVal();
					temp.add(new Chip(lValue));
					betValue += (new Chip(lValue).getValue());
				}
			}
		}
		setChips(temp);
		return temp.size();
	}

	public Integer getMoney() {
		return Money;
	}

	public void setMoney(Integer money) {
		Money = money;
	}

	public boolean isAllIn(){
		return isAllIn;		
	}
	
	//------------------------------------------_Betting_-------------------------------------------\\
	
	//-------------------------------------------_Brain_---------------------------------------------\\
	
	enum PlayStyle{	CONSERVATIVE,	//mostly call, won't open/raise unless hand is very good
					MODERATE, 		//raises on decent hands, tends to open on pair and above
					AGGRESSIVE}		//will open and bets heavily	
	
	PlayStyle playstyle;
	private int Handvalue, raiseAmt;
	Integer[] action = new Integer[2];
	CompEval x = new CompEval();
	ArrayList<Chip> temp = new ArrayList<Chip>();
	int playerMoney;
	boolean raised = false;
	boolean isOn, round1 = true;
	/*
	void decide(){
		switch(playstyle){
		case CONSERVATIVE:
			conservative(this, tempPot);
		break;
		case MODERATE:
			moderate(this, tempPot);
		break;
		case AGGRESSIVE:
			aggressive(this,tempPot);
		break;
		}
	}
	*/
	public void On(){
		isOn = true;
	}
	
	public void Off(){
		isOn = false;
	}
	
	public void toChips(int betAmt){
		while(betAmt != 0){
			for(Chip.Value lValue : Chip.Value.values()){
				while(betAmt >= lValue.getChipVal()){
					betAmt -= lValue.getChipVal();
					chips.add(new Chip(lValue));
				}
			}
		}
	}
	
	private void setHandValue(){		//hand category ordinal value
	Handvalue = playerCards.getCategory().ordinal();
	//ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, 
	//FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR, HIGH
	}
	
	public void setAction(int a, int b){
		action[0] = a;
		if(a==2){
			action[1] = b;
		}
	}
	
	public void setAction(int a){
		action[0] = a;
	}
	public Integer[] Act(){
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
	
	public void wins(){
		setMoney(getMoney() + tAccess.getPot().wins());
	}
	
	private int getCash(){
		return playerMoney; 
	}
	
	public void setIndice(int index){
		if(indices[0] == null){
			indices[0] = index;
		}else if(indices[1] == null){
			indices[1] = index;
		}else if(indices[2] == null){
			indices[2] = index;
		}else{
			
		}
	}
	
	public int[] discard(){
		int[] index = new int[3];
		Integer ind1 = null;
		Integer ind2 = null;
		Integer ind3 = null;
		int value = x.comp(getHand()).getCategory().ordinal();
		switch(value){
		case 0: //royal flush, do nothing
		case 1:	//straight flush, do nothing
		case 2:	//four of a kind, do nothing
		case 3:	//full house, do nothing
		case 4:	//flush, do nothing
		case 5:	//straight, do nothing
			break;
			
		case 6:	//three of a kind, drop lower single
			for(int i = 0; i < getHand().size(); i++){
				if(i==2)
					continue;
				if(getHand().getCard(2).getType() != getHand().getCard(i).getType() && ind1 != null){
					ind1 = i;
				}else if(getHand().getCard(2).getType() != getHand().getCard(i).getType() && ind1 == null){
					ind2 = i;
				}
			}
			int com = getHand().getCard(ind1).compareTo(getHand().getCard(ind2));
			if(com < 0)
				index[0] = ind1;
			else if(com > 0)
				index[0] = ind2;
			break;
				
		case 7:	//two pairs, drop single
			if(getHand().getCard(0).compareCards(getHand().getCard(1)) != 0){
				index[0] = 0;
			}
			else if(getHand().getCard(1).compareCards(getHand().getCard(2)) != 0){
				index[0] = 2;
			}
			else if(getHand().getCard(4).compareCards(getHand().getCard(3)) != 0){
				index[0] = 4;
			}
			break;
				
		case 8:	//pair, drop 2 lowest cards
			for(int i = 0; i < getHand().size() - 1; i++){
				if(getHand().getCard(i).compareCards(getHand().getCard(i+1)) == 0){
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
			break;
				
		case 9:	//high card, drop 3 lowest
			index[0] = 2;
			index[1] = 3;
			index[2] = 4;
			break;
		}
		
		return index;
	}
		
	public void AIPlay(){
		if(tAccess.getPot().round == 1){
			System.out.println("tAccess.getPot().round == 1");
			
			switch(Handvalue){
			case 0:	//royal flush
			case 1:	//straight flush
			case 2:	//four of a kind
			case 3:	//full house
				System.out.println("case 3");
				if(round1){
					System.out.println("round1");
				action[0] = 2;
				raiseAmt = (int)(Math.random() * 4 + 6);
				switch(raiseAmt){
				case 6:
					System.out.println("case 6");
					raiseAmt =  new Chip(Value.TWO_FIFTY).getValue();
					setChips(raiseAmt);
					break;
				case 7:
					System.out.println("case 7");
					raiseAmt = new Chip(Value.HUNDRED).getValue();
					setChips(raiseAmt);
					break;
				case 8:
					System.out.println("case 8");
					raiseAmt = new Chip(Value.FIFTY).getValue();
					setChips(raiseAmt);
					break;
				case 9:
					System.out.println("case 9");
					raiseAmt = new Chip(Value.TWENTY_FIVE).getValue();
					setChips(raiseAmt);
					break;
				}
				action[1] = raiseAmt;
				break;
				}else{
					System.out.println("Action 0a");
					action[0] = 1;
				}
			case 4:	//flush
			case 5:	//straight
			case 6:	//three of a kind
			case 7:	//two pairs
			case 8:	//pair
			case 9:	//high
				System.out.println("Action 0");
				action[0] = 1;	//call
				break;
			}
		}else if(tAccess.getPot().round == 2){
			System.out.println("tAccess.getPot().round == 2");
			if(Handvalue <= 6){	//greater or equal 3 of a kind
				betValue = 0;
				if(Handvalue == 6){
					raiseAmt = ((int)(((getCash()* 3) / 8) + (int)(Math.random() * playerMoney / 4) / 25)) * 25;
					//minimum 3/8 of total money, max of 5/8
					action[0] = 2;
					action[1] = raiseAmt;
					setChips(raiseAmt);
				}
				else if(Handvalue == 6){	//three of a kind
					raiseAmt = ((int)(((getCash() * 3) / 5) + (int)(Math.random() * playerMoney / 5) / 25)) * 25;
					//minimum 3/5 of total money, max of 4/5
					action[0] = 2;
					action[1] = raiseAmt;
					setChips(raiseAmt);
					
				}
				else if(Handvalue < 5){	//flush and above
					if(tAccess.getPot().round == 1){
						action[0] = 2;
						action[1] = raiseAmt = ((int)(((getCash() * 3) / 5) + (int)(Math.random() * playerMoney / 5) / 25)) * 25;
						setChips(raiseAmt);
					}
					else if(tAccess.getPot().round == 2){
						action[0] = 3;
						action[1] = (Integer) null;
					}
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
					raiseAmt = ((int)(((getCash() * 2) / 8) + (int)((Math.random() * (playerMoney * 1) / 8) / 25))) * 25;
					//1/4 to 3/8
					setChips(raiseAmt);
				}
				else if(Handvalue == 8){	//pair
					chanceMult = 1.5;
					raiseAmt = ((int)(((getCash() * 2) / 8) + (int)((Math.random() * (playerMoney * 1) / 8) / 25))) * 25;
					setChips(raiseAmt);
				}
				else{	//high card
					raiseAmt = ((int)(((getCash() * 1) / 16) + (int)(Math.random() * (playerMoney * 1) / 8)) / 25) * 25;
					//1/16 to 3/16
					if(getHand().getHighCard().getType().ordinal() < 2){	//ace or king
						chanceMult = 1;
					}else{//everything else
						chanceMult = .75;
					}
					raisechance = (int)(chance * chanceMult);
					if(raise <= raisechance){
						
						switch(getHand().getHighCard().getType()){
						case ACE:
						case KING:
							if(tAccess.getPot().round == 1){
							action[0] = 2;	//raise
							action[1] = raiseAmt;
							setChips(raiseAmt);
							}else if(tAccess.getPot().round == 2 && tAccess.getPot().minimumBet < (int)(Money / 4)){
								action[0] = 1;	//call
							}
							break;
							
						case QUEEN:
						case JACK:
						case TEN:
						case NINE:
						case EIGHT:
						case SEVEN:
							if(tAccess.getPot().round == 1){
								action[0] = 1;	//call
								action[1] = null;
							}else if(tAccess.getPot().round == 2){
								action[0] = 4;	//fold
							}
							break;
							
						case SIX:
						case FIVE:
						case FOUR:
						case THREE:
						case TWO:
							if(tAccess.getPot().round == 1)
								if(tAccess.getPot().betsMade)
									action[0] = 1;	//call	
								else
									action[0] = 0;	//check
							if(tAccess.getPot().round == 2)
								action[0] = 4;		//fold
							break;
						}
					}
					else if(raise <= raisechance + callchance){
						action[0] = 1;			//call
					}
					else{
						if(tAccess.getPot().round == 1)
							if(tAccess.getPot().betsMade)
								action[0] = 1;	//call	
							else
								action[0] = 0;	//check
						if(tAccess.getPot().round == 2)
							action[0] = 4;		//fold
					}
				}
				
				
			}
		}
	}
		
	private void conservative(Player Russel, Pot tempPot){
		
	}
	
	private void moderate(Player Russel, Pot tempPot){		
	
	
	}
	
	private void aggressive(Player Russel, Pot tempPot){
	
	
	}
		
		//-------------------------------------------_Brain_---------------------------------------------\\
	
	
	
}
