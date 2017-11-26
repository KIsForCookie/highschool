package poker;

import java.util.ArrayList;

import poker.Chip.Value;

public class Pot {
	private final Chip ante = new Chip(Value.TWENTY_FIVE);
	public int minimumBet = 0;
	private playerBets<Chip> bets;
	boolean betsMade = false;
	int turnsNoRaise = 0;
	public int round = 1,numberPlayers;
	private int potValue = 0;
	
	public Pot(){
		bets = new playerBets<Chip>();	
		
	}
	
	public void setPlayernum(){
		for(int i = 0; i < bets.size(); i++){
			numberPlayers = i + 1;
		}
	}
	
	public void setMinBet(int minbet){
		minimumBet = minbet;
	}
	
	public String toString(){
		String Return = "";
		
		for(int i = 0; i < bets.size(); i++){
			Return += i + ", { ";
			for(int j = 0; j < bets.get(i).size(); j++){
				Return += bets.get(i).get(j).getValue();
				if(j < bets.get(i).size() - 1){
					Return += ", ";
				}
			}
			Return += " }\n";
		}
		
		
		return Return;
	}
	
	public void reset(){
		bets.clear();
	}
	
	public int wins(){
		int winnings = 0;
		winnings = bets.getTotal();		
		bets.clear();
		return winnings;
	}

	public void setPot(Pot p){
		bets = p.bets;
	}
	
	
	
	public void bet(ArrayList<Chip> chips, int playerNum, boolean isAllIn){	
			
		int betValue = minimumBet;
		if(chips.size() > 0){
			for(int i = 0; i < chips.size(); i++){
				betValue += chips.get(i).getValue();
			}
		}
		
		if(betValue < minimumBet && (isAllIn == false)){
			System.out.println("Invalid input");
		}
		else{				
			for(int i = 0; i < chips.size(); i++){
				bets.addToInnerArray(playerNum, chips.get(i));
			}
		}
		
		if(betValue > minimumBet)
			minimumBet = betValue;	//raise
		
		if(betsMade == false)
			betsMade = true;
		
		setPlayernum();
		
	}
	
	public int getPotValue(){	//win		
		return bets.getTotal();
	}
	
	public int getNumberPlayers(){
		return bets.size();
	}
	
	public void ante(){
		Table t = new Table();
		for(int i = 0; i < bets.size(); i++){
			t.get(i).setMoney(t.get(i).getMoney() - ante.getValue());
		}
		t.pot.potValue = 25 * t.size();
	}
	
	public void Action(Player Russel, Integer[] action){
		switch(action[0]){
			case 1:
				break;
			case 2: 
				Russel.call();
				bet(Russel.getChips(), Russel.playerNum, Russel.isAllIn());
				break;
			case 3:			
				Russel.raise();
				bet(Russel.getChips(), Russel.playerNum, Russel.isAllIn());
				break;
			case 4:
				Russel.AllIn();
				bet(Russel.getChips(), Russel.playerNum, Russel.isAllIn());
				break;
			case 5:
				Russel.Off();
				break;
			default:
				break;
		}
	}
	
	@SuppressWarnings({ "serial", "hiding" })
	private class playerBets<Chip> extends ArrayList<ArrayList<Chip>> {
	   
		public void addToInnerArray(int index, Chip chip) {
	        while (index >= this.size()) {
	            this.add(new ArrayList<Chip>());
	        }
	        
	        this.get(index).add(chip);
	    }

	    public int getTotal(){
			int total = 0;
	    	
			for(int i = 0; i < this.size(); i++){
					for(int j = 0; j < this.get(i).size(); j++){
						total += ((poker.Chip) this.get(i).get(j)).getValue();
					}
			}
	    	return total;
	    }
	    
	}
	
}
