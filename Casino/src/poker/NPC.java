package poker;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NPC {
	protected String firstName;
	protected String lastName;
	protected String name = firstName + " " + lastName;
	protected static ArrayList<String> Cards = new ArrayList<String>();
	private int playerNum;
	private boolean isEnabled = false;
	int currentBet;
	double money;
	DecimalFormat moneyform = new DecimalFormat("#.00");
	
	public NPC(int pn){
		playerNum = pn;
		money = Math.random() * 4000.00 + 1000.00;
	}
	
	public String getMoney(){
		String output = moneyform.format(money);
		return output;
	}
	
	protected void addToHand(Card c){
		String name = c.toString();
		Cards.add(name);
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
	
	protected ArrayList<String> getHand(){
		return Cards;
	}
	protected String getName(){
		return name;
	}
	//------------------------------------------_Betting_------------------------------------------\\
	protected void fold(){
		
	}
	
	protected void check(){
		
	}
	
	protected void call(){
		
	}
	
	protected void open(){
		
	}
	
	protected int raise(int bet){
		return bet;
	}
	
	
	//------------------------------------------_Betting_------------------------------------------\\
	
	
	
	
	
	
}
