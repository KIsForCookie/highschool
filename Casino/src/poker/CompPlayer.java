package poker;

import java.util.ArrayList;

public class CompPlayer {
	
	public int playerNum = 0;
	public Hand playerCards = new Hand();
	private int betValue = 0;
	private ArrayList<Chip> chips = new ArrayList<Chip>();
	
	private boolean isEnabled = false;
	boolean isAllIn = false;
	
	private Integer Money;
	
	CompPlayer(){
		
	}
	
	public void setCompareOrder(Hand h){
		playerCards.setCompareOrder(h);
	}
	
	public void enable(){
		isEnabled = true;
	}
	
	public void disable(){
		isEnabled = false;
	}
	
	public void setPlayerNum(int size){
		playerNum = size + 1;
	}
	
	public void addCard(Card c){
		try{
			playerCards.addToHand(c);
		}catch(Exception NullPointerException){
			System.out.println("hello world");
		}
		playerCards.Sort();
	}
	
	public boolean isEnabled(){
		return isEnabled;
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
		playerCards.clear();
		String foldString = ("CPU has folded.");
		return foldString;
	}
	
	public void check(Pot curPot){
		betValue = 0;
		isAllIn = false;
		if(curPot.betsMade != false){
			System.out.println("Check");
		}
	}
		
	public void call(Pot curPot){
		betValue = 0;
		int Bet = curPot.minimumBet;
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
		}
		else{
			isAllIn = true;
			AllIn(curPot);
		}
	}

	public void AllIn(Pot curPot){
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
		setMoney(getMoney() - betValue);
		setChips(temp);
	}

	public void raise(int bet, Pot curPot){

		ArrayList<Chip> temp = new ArrayList<Chip>();
		
		bet += curPot.minimumBet;
		
			//betValue = curPot.minimumBet;
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
			setMoney(getMoney() - betValue);
			setChips(temp);
	}

	public int callSize(Pot curPot){
		betValue = 0;
		int Bet = curPot.minimumBet;
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
			return temp.size();
		}
		isAllIn = true;
		return AllInSize(curPot);
	}

	public int raiseSize(int bet, Pot curPot){
		ArrayList<Chip> temp = new ArrayList<Chip>();
		
		bet += curPot.minimumBet;
		
			betValue = curPot.minimumBet;
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

	public int AllInSize(Pot curPot){
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
	
	//------------------------------------------_Betting_------------------------------------------\\
	
	
}

