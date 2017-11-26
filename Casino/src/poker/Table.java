package poker;

import java.util.ArrayList;

import javax.swing.Timer;

public class Table {
	static ArrayList<Player> table = new ArrayList<Player>();
	private static int numberPlayers = 0;
	static boolean roundInProgress = false;
	static int dealerPos;
	public static Pot pot;
	static int round;
	static Deck deck = new Deck();
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
	
	public Card DealCard(){
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
	
	public void ante(){
		pot.ante();
	}
	
	private void discardTime(int[] indices){
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() == true){
				table.get(i).discard();
			}
			else{
				
			}
		}
	}
	
	private void compareTime(){
		int winnerIndex = 0;
		for(int i = 0; i < table.size(); i++){
			table.get(i).setHandCat(x.comp(table.get(i).getHand()).getCategory());
			table.get(i).setCompareOrder(x.comp(table.get(i).getHand()).getHandOrder());
		}
		
		for(int i = 1; i < table.size(); i++){
			if(table.get(i).isOn == false)
				continue;
			
			int win = table.get(winnerIndex).getHand().CompareHand(table.get(i).getHand());
			if(win > 0){
				winnerIndex = i;
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
