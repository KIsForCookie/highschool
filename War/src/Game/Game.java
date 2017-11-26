package Game;

import java.util.Random;
import java.util.Scanner;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		GUI gui = new GUI();
		
		String pause = "";
		int choice = 0;
		Fight fight = new Fight();
		Random gen = new Random();
		String player1name = "";
		String player2name = "";
		Scanner reader = new Scanner(System.in);
		int pass = 0;
		Value val = new Value();
		int[][] hand = new int[2][52];
		int[] discardPile = new int[52];
		int[] deck = new int[52];
		int[] test = new int[52];
		int a = 0;
		int b = 0;
		int c = 0;
		int counter = 0;
		
		System.out.println("War Game");
		do{
			System.out.println("What is the name of the first player?: ");
			try{
				player1name = reader.nextLine();
				pass = 1;
			}catch(Exception error){
				System.out.println("Not a valid name.");
				reader.next();
				pass = 0;
			}
		}while(pass == 0);
		
		do{
			System.out.println("What is the name of the second player?: ");
			try{
				player2name = reader.nextLine();
				pass = 1;
			}catch(Exception error){
				System.out.println("Not a valid name.");
				reader.next();
				pass = 0;
			}
		}while(pass == 0);
		
		for(int i = 0; i < 52; i ++){
			test[i] = 1;
		}
		
		for(int i = 0; i < 52; i ++){
			do{
				deck[i] = gen.nextInt(52) + 1;
				test[(deck[i]-1)] -= 1;
				if(test[(deck[i]-1)] < 0){
					test[(deck[i]-1)] += 1;
					pass = 0;
				}else{
					pass = 1;
				}
			}while(pass == 0);
			
		}
		
		
		
		for(int i = 0; i < 52; i++){
			choice = gen.nextInt(2) + 1;
			if(choice == 1){
				if(a < 26){
					hand[0][a] = deck[i];
					a++;
				}else{
					hand[1][b] = deck[i];
					b++;
				}
			}else{
				if(b < 26){
					hand[1][b] = deck[i];
					b++;
				}else{
					hand[0][a] = deck[i];
					a++;
				}
			}
		}
		
		/*for (int i = 0; i < 52; i ++){
			System.out.println(hand[0][i]);
		}
		
	System.out.println("Seperate");
	for (int i = 0; i < 52; i ++){
		System.out.println(hand[1][i]);
	}*/
		do{
			for(int i = 0;i < 52; i ++){
				if(hand[0][i] == 0){
					System.out.println("Player 1 has " + (i) + " cards");
					break;
				}
			}
			
			/*for(int i = 0; i < 52; i ++){
				if(hand[0][i]==0){
					System.out.println("");
					break;
				}else{
					System.out.print(val.getValue(hand[0][i]) + ", ");
				}
			}*/
			
			for(int i = 0;i < 52; i ++){
				if(hand[1][i] == 0){
					System.out.println("Player 2 has " + (i) + " cards");
					break;
				}
			}
			
			/*for(int i = 0; i < 52; i++){
				if(hand[1][i]==0){
					System.out.println("");
					break;
				}else{
					System.out.print(val.getValue(hand[1][i]) + ", ");
				}
			}*/
			
			System.out.println("Press enter to fight");
			pause = reader.nextLine();
			System.out.println("Player 1's " + val.getValue(hand[0][0]) + " battles Player 2's " + val.getValue(hand[1][0]) +"!");
			choice = fight.battle(val.getValue(hand[0][0]), val.getValue(hand[1][0]));
			
			if(choice == 0){
				
				System.out.println("Tie");
				discardPile[c] = hand[0][0];
				c++;
				discardPile[c] = hand[1][0];
				c++;
				
				for(int i = 0; i < 52; i ++){
					if(i == 51){
						hand[0][i] = 0;
						hand[1][i] = 0;
						break;
					}else{
						hand[0][i] = hand[0][i+1];
						hand[1][i] = hand[1][i +1];
					}
				}
				
			}else if(choice == 1){
				
				System.out.println("Player 1 wins");
				discardPile[c] = hand[0][0];
				c++;
				discardPile[c] = hand[1][0];
				c++;
				for(int i = 0; i < 52; i ++){
					if(i == 51){
						hand[0][i] = 0;
						hand[1][i] = 0;
					}else{
						hand[0][i] = hand[0][i+1];
						hand[1][i] = hand[1][i +1];
					}
				}
				c = 0;
				for(int i = 0; i < 52; i ++){
					if(hand[0][i] == 0){
						if(discardPile[c] == 0){
							c = 0;
							break;
						}else{
							hand[0][i] = discardPile[c];
							discardPile[c] = 0;
							c++;
						}
					}
				}
				
				
			}else if(choice == 2){
				
				System.out.println("Player 2 wins");
				discardPile[c] = hand[0][0];
				c++;
				discardPile[c] = hand[1][0];
				c++;
				for(int i = 0; i < 52; i ++){
					if(i == 51){
						hand[0][i] = 0;
						hand[1][i] = 0;
					}else{
						hand[0][i] = hand[0][i+1];
						hand[1][i] = hand[1][i +1];
					}
				}
				c = 0;
				for(int i = 0; i < 52; i ++){
					if(hand[1][i] == 0){
						if(discardPile[c] == 0){
							c = 0;
							break;
						}else{
							hand[1][i] = discardPile[c];
							discardPile[c] = 0;
							c++;
						}
					}
				}
			}else if(choice == 3){
				reader.close();
				System.exit(0);
			}
			
		}while(true);
		
		

	}

}
