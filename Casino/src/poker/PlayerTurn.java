package poker;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerTurn{
	private Pot pot;
	private Player player;
	boolean cpuTurn = false;
	private Integer[] input = new Integer[2];
	private boolean inputIsGood = true;
	Scanner reader = new Scanner(System.in);
	volatile boolean advance = false;
	Integer temp;
	
	public PlayerTurn(boolean isCPU){
		cpuTurn = isCPU;
	}
	
	public void Run(Player Russel, Pot curPot){
		if(cpuTurn)
			RunForCPU(Russel, curPot);
		else
			RunForPlayer(Russel, curPot);
	}
	
	public void advance(){
		advance = true;
	}
	
	public void RunForPlayer(Player Russel, Pot curPot){
		player = Russel;
		pot = curPot;
		CompEval x = new CompEval();
				
		System.out.println(player.getName() + " has " + player.playerCards.toString() + ", \n" + x.comp(player.playerCards));
		
		if(Russel.getMoney() < curPot.minimumBet){
			Russel.fold();
		}
		else{
			/*
			System.out.print("Pick one; \n"
					+ "1) Check\n"
					+ "2) Call\n"
					+ "3) Raise\n"
					+ "4) All In\n"
					+ "5) Fold\n");
			*/				
			Decisions(Russel, curPot);
		}
	}
	
	public void RunForCPU(Player Jimmies, Pot curPot){
		System.out.println("Hello world");
		player = Jimmies;
		if(player.getMoney() < curPot.minimumBet){
			player.fold();
		}
		else
			Decisions(player, curPot);
	}
	
	public Pot getPot(){
		return pot;
	}
	
	public void setInput(Integer[] is){
		this.input = is;
	}
	
	public void Decisions(Player player, Pot curPot){
		while(inputIsGood){
			/*
			if(cpuTurn == false){
				inputIsGood = false;
				input[0] = (int)reader.nextDouble();
			}
			else{
				setInput(player.Act());	//AI decides input
			}
			*/
			while(true){
				if(player.Act() == null){
					if(temp == null)
						continue;
					player.setAction(temp);
				}
				else{
					setInput(player.Act());
					break;
				}
			}
			
			switch(input[0]){
			case 1: 
				if(curPot.betsMade == false){
					pot.Action(player, input);
				}
				else{
					inputIsGood = true;
					System.out.println("bets made, can't check");
				}
				break;
			case 2:
				if(player.getMoney() >= curPot.minimumBet){
					System.out.println("Call");
					pot.Action(player, input);
				}
				else{
					inputIsGood = true;
				}
				break;
			case 3:
				int raiseAmt = 0;
				System.out.println("how much?");
				//if(input[1] == null){	//if input[1] has a value, it's used, otherwise, it takes user-input for input[1] (for comp)
					raiseAmt = (int)reader.nextDouble();
					//raiseAmt = ((int)(raiseAmt + 25/2) / 25) * 25;
					
					input[1] = raiseAmt;
			//	}
				
				int n = 0;
				input[1] = ((int)(input[1] / 25)) * 25;
				while( input[1] - n > 0){
					for(Chip.Value lValue : Chip.Value.values()){
						while(input[1] - n >= lValue.getChipVal()){
							player.betChip(new Chip(lValue));
							n += (new Chip(lValue).getValue());
						}
					}
				}
				
				if(player.getMoney() >= curPot.minimumBet + input[1]){
					System.out.println("raise by " + input[1]);
					pot.Action(player, input);
				}
				else{
					inputIsGood = true;
				}
				break;
			case 4:
				System.out.println("All In");
				pot.Action(player, input);
				break;
			case 5:
				System.out.println("Fold");
				pot.Action(player, input);
				break;
			default:
				System.out.println("Invalid entry");
				break;
			}
		}
	}
		
	public ArrayList<Chip> getChips(){
		return player.getChips();
	}
	
	
	
}

