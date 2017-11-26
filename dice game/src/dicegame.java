/* dice game by Alexander Kim*/

import java.util.Scanner; // import statement

public class dicegame {

	public static void main(String[] args) {
	//creates new objects
		Die dice = new Die();
		Player player1 = new Player();
		Player player2 = new Player();
		Scanner reader = new Scanner(System.in);
		
	//global variables	
		int die1;
		String pause;

		System.out.println("Welcome to the dice game!"); //welcomes user to the game
		System.out.println("What is the name of the first player?: "); //asks for player1's name
		player1.setName(reader.nextLine()); //sets name of player1
		System.out.println("What is the name of the second player?: ");//asks for player2's name
		player2.setName(reader.nextLine());//sets name of player2
		System.out.println("Lets play the game!"); //signals start of game
		do{ //loop
		System.out.print(player1.getName() + ", press enter to roll"); // prompts player 1 to roll
		pause = reader.nextLine(); //pauses until player presses enter
		die1 = dice.roll(6); //random number between 1 and 6
		System.out.println(player1.getName() + ", you have rolled a " + 
		die1 + "."); //tells player1 what his roll was
		player1.setScore(die1); //sets player1's score
		System.out.print(player2.getName() + ", press enter to roll"); //prompts player2 to roll
		pause = reader.nextLine(); //pauses until player presses enter
		die1 = dice.roll(6); //random number between 1 and 6
		System.out.println(player2.getName() + ", you have rolled a " + 
		die1 + "."); //tells player2 what his roll was
		player2.setScore(die1);//sets player2's score
		
		//checks score of player 1 and player2
		if(player1.getScore() > player2.getScore()){ //player1 has higher score
			System.out.println(player1.getName() + " wins!");
			System.out.println("");
			player1.plusWin();
		}else if(player2.getScore() > player1.getScore()){//player2 has higher score
			System.out.println(player2.getName() + " wins!");
			System.out.println("");
			player2.plusWin();
		}else if(player2.getScore() == player1.getScore()){//tie
			System.out.println("Tie!");
			System.out.println("");
		}
		
		//checks if player1 or player2 have necessary 5 wins to win the round
		if (player1.getWin() == 5){//player1 wins the round
			System.out.println(player1.getName() + " wins the round!");
			player1.resetWin();
			player2.resetWin();
			player2.setLife(player2.getLife() - 1);
			System.out.println(player2.getName() + " now has " + 
			player2.getLife() + " lives!");
			System.out.println("");
		}else if (player2.getWin() == 5){//player2 wins the round
			System.out.println(player2.getName() + " wins the round!");
			player1.resetWin();
			player2.resetWin();
			player1.setLife(player1.getLife() - 1);
			System.out.println(player1.getName() + " now has " + 
			player1.getLife() + " lives!");
			System.out.println("");
		}
		
		//checks if player1 or player2 has no more lives
		if (player1.getLife() == 0){//player1 has no more lives
			System.out.println(player1.getName() + " loses the game!");
			System.out.println(player2.getName() + " survived with " + 
			player2.getLife() + " live(s)!");
			break;
		}else if(player2.getLife() == 0){ //player2 has no more lives
			System.out.println(player2.getName() + " loses the game!");
			System.out.println(player1.getName() + " survived with " + 
			player1.getLife() + " live(s)!");
			break;
		}
		}while(true);
	}

}
