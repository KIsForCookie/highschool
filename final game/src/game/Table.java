package game;

import java.util.ArrayList;

import javax.swing.Timer;

public class Table {
	static ArrayList<Player> table = new ArrayList<Player>();
	private static int numberPlayers = 0;
	static boolean roundInProgress = false;
	static int dealerPos;
	public static Pot pot;
	static int round;
	static PokerDeck deck = new PokerDeck();
	static CompEval x = new CompEval();
	static PlayerTurn playerturn;
	Timer timer;
	static int turnsNoRaise = 0;

	public Table(){
		
	}
	
	public static ArrayList<ArrayList<Chip>> a = new ArrayList<ArrayList<Chip>>();
	
	public void setAIChipsFresh(int players){
		a.clear();
		for(int i = 0; i < table.size(); i++){
			while(a.size() < players){
				a.add(new ArrayList<Chip>());
			}			
			for(int j = 0; j < table.get(i).getChips().size(); j++){
				a.get(i).add(table.get(i).getChips().get(j));
			}
		}
	}
	
	public void setAIChipsAdditive(int players){
		for(int i = 0; i < table.size(); i++){
			while(a.size() < players){
				a.add(new ArrayList<Chip>());
			}
			for(int j = 0; j < table.get(i).getChips().size(); j++){
				a.get(i).add(table.get(i).getChips().get(j));
			}
		}
	}
	
	public void called(){
		turnsNoRaise++;
	}
	
	public void raised(){
		turnsNoRaise = 0;
	}
	
	public void newRound(){
		if(turnsNoRaise - 1 == numberPlayers){
			round++;
		}
	}
	
	public void setAction(int playerIndex, int action, int raiseAmt){
		if(playerIndex < table.size()){
			table.get(playerIndex).setAction(action);
			if(action == 2){
				raiseAmt = 25 * ((int)(raiseAmt / 25));
				table.get(playerIndex).setAction(action, raiseAmt);
			}
		}
	}
	
	public void setChips(){
		int playerpos = 0;
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == false){
				playerpos = i;
				break;
			}
		}
		for(int i = 0; i < table.size(); i++){
			if(i == playerpos){
				continue;
			}
			
			if(table.get(i).Act()[0] == 2 || pot.minimumBet > 0){
				if(table.get(i).Act()[1] == null)
					table.get(i).Act()[1] = pot.minimumBet;
				
				table.get(i).toChips(table.get(i).Act()[1]);
			}
		}
	}
	
	public PokerCard DealCard(){
		return deck.Deal();
	}
	
	public void addPlayer(Player p){
		if(roundInProgress == false){
			setNumberPlayers(getNumberPlayers() + 1);
			table.add(p);
			pot = null;
			pot = new Pot();
		}
	}
	
	public void Deal(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < table.size(); j++){
				table.get(j).addCard((deck.Deal()));
			}
		}
	}
	
	public int size(){
		return table.size();
	}
	
	public Player get(int index){
		return table.get(index);
	}
	
	public void reset(){
		setNumberPlayers(0);
		roundInProgress = false;
		pot = null;
	}
	
	public Player leaveTable(int playerNum){
		return table.get(playerNum);		
	}
	
	void removePlayer(int playerNum){
		table.remove(playerNum);
	}
	/*
	public void GO(){
		for(int i = 0; i < table.size(); i++){
			pot.Action(table.get(i), table.get(i).Act());
		}
	}
	*/
	public void setAction(){
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == true){
				table.get(i).AIPlay();
				//System.out.println(table.get(i).Act().toString());
				setAIChipsFresh(table.size());
			}
			/*
			else{
				playerturn = new PlayerTurn(table.get(i).isCompPlayer());
			}*/
		}
	}
	
	public void AIGo(){
		int playerpos = 0;
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == false){
				playerpos = i;
				break;
			}
		}
		for(int i = 0; i < table.size(); i++){
			if(i==playerpos)
				continue;
			newRound();
			pot.Action(table.get(i), table.get(i).Act());
		}
	}
	
	public void RoundStart(){
	//	round = 1;
		//Deal();
		
		pot = new Pot();
		pot.ante();
		pot.betCount=0;
		Deal();
		
	//	System.out.println("it's ALIVE");
		
		for(int i = 0; i < table.size(); i++){
			playerturn = new PlayerTurn(table.get(i).isCompPlayer());
			playerturn.Run(table.get(i), pot);
			//playerturn	player		pot
		}	
		
		//discardTime();
		
		for(int i = 0; i < table.size(); i++){
			playerturn = new PlayerTurn(table.get(i).isCompPlayer());
			playerturn.Run(table.get(i), pot);
			//playerturn	player		pot
		}	
			/* for each playerturn, the system identifies
			 * if the 'Player' object is CPU controlled or
			 * player controlled. if player controlled
			 * player inputs decision, if AI, computer 
			 * decides based on probability
			 */
		
		compareTime();		
	}
	
	public void AI(){
		
	}
	public void ante(){
		pot.ante();
	}
	
	void discardTime(Integer[] index){
		int playerpos = 0;
		Integer[] indices = new Integer[3];
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == false){
				playerpos = i;
				break;
			}
		}
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == true){
				for(int j = 0; j < indices.length; j++){
					indices[j] = table.get(i).discard()[j];
				}
				for(int j = 0; j < table.get(i).getHand().size(); j++){
					if(indices[j] != null)
						table.get(i).getHand().remove(table.get(i).getHand().getCard(indices[4 - i]));	//back to front, doesn't change indices
				}
				while(table.get(i).getHand().size() < 5){
					table.get(i).addCard(deck.Deal());
				}
			}
			else{
				indices = index;
				for(int j = 0; j < table.get(playerpos).getHand().size(); j++){
					table.get(playerpos).getHand().remove(table.get(playerpos).getHand().getCard(indices[4 - i]));	//back to front, doesn't change indices
				}
				while(table.get(playerpos).getHand().size() < 5){
					table.get(playerpos).addCard(deck.Deal());
				}
			}
		}
	}
	
	public void compareTime(){
		int winnerIndex = 0;
		System.out.println(table.size());
		for(int i = 0; i < table.size(); i++){
			table.get(i).setHandCat(x.comp(table.get(i).getHand()).getCategory());
			table.get(i).setCompareOrder(x.comp(table.get(i).getHand()).getHandOrder());
			
			//System.out.println(table.get(i).getHand().category);
			//System.out.println(table.get(i).getHand().compareOrder.toString());
		}
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isOn == true){
				winnerIndex = i;
			}
		}
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isOn == false)	//if the player at i has folded, skip them
				continue;
			if(table.get(i).folded == true){
				continue;
			}
			if(table.get(winnerIndex).getHand().category.ordinal() > table.get(i).getHand().category.ordinal()){
				winnerIndex = i;
			}else if(table.get(winnerIndex).getHand().category == table.get(i).getHand().category){
				for(int j = 0; j < table.get(i).getHand().compareOrder.size(); j++){
					if(winnerIndex == i)
						continue;
					
					System.out.println(table.get(winnerIndex).getHand().compareOrder.get(j));
					System.out.println(table.get(i).getHand().compareOrder.get(j));
					if(table.get(i).getHand().compareOrder.get(j).compareTo(table.get(i).getHand().compareOrder.get(j)) < 0){
						winnerIndex = i;
						System.out.println(winnerIndex);
					}
				}
			}

		}
		System.out.println(table.get(winnerIndex).getName() + " wins " + pot.getPotValue());
	}

	public int getNumberPlayers() {
		return numberPlayers;
	}

	public Pot getPot(){
		return pot;
	}
	
	public int getTableSize(){
		return table.size();
	}
	
	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}
	
}
