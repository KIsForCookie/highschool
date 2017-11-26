import java.util.Scanner;
public class game {
	
	public static void main(String[] args) {
		
		gui gui = new gui();
		gui.setVisible(true);
		
		
		Player player1 = new Player();
		Player player2 = new Player();
		Scanner reader = new Scanner(System.in);
		Die dice = new Die();
		
		int player1win = 0;
		int player2win = 0;
		
		int player1turn = 1;
		int player2turn = 0;
		int number = 0;
		int player1choice = 0;
		int player2choice = 0;
		int pass = 0;

		System.out.println("Welcome to the guessing game!");
		System.out.println("What is the name of the first player?: ");
		player1.setName(reader.nextLine());
		System.out.println("What is the name of the second player?: ");
		player2.setName(reader.nextLine());
		System.out.println("Lets play the game!");
		player1.setLife(5);
		player2.setLife(5);
		number = dice.roll(100);
		player1.setScore(0);
		player2.setScore(0);
		do{
		if(player1turn == 1){
			do{
			do {
		System.out.print(player1.getName() + " guess a number between 1 and 100: ");
		player1choice = reader.nextInt();
		if((player1choice > 100)||(player1choice < 0)){
			System.out.println("You cannot guess this number! Try agian.");
			pass = 0;
		}else{
				pass = 1;
			}
		}while (pass == 0);
		
		if(player1choice > number){
			System.out.println("Your guess is too high! Try agian");
			player1.setScore(player1.getScore() + 1);
		}else if(player1choice < number){
			System.out.println("Your guess is too low! Try agian");
			player1.setScore(player1.getScore() + 1);
		}else if (player1choice == number){
			player1.setScore(player1.getScore() + 1);
			System.out.println("You guessed correctly!");
			System.out.println("Randomizing number choice....");
			number = dice.roll(100);
			System.out.println("New number found!");
			player1win = 1;
			player1turn = 0;
			player2turn = 1;
		}
			}while (player1win == 0);
		
		}
	if (player2turn == 1){
		do{
		do {
			System.out.print(player2.getName() + " guess a number between 1 and 100: ");
			player2choice = reader.nextInt();
			if((player2choice > 100)||(player2choice < 0)){
				System.out.println("You cannot guess this number! Try agian.");
				pass = 0;
			}else{
					pass = 1;
				}
			}while (pass == 0);
		if(player2choice > number){
			System.out.println("Your guess is too high! Try agian");
			player2.setScore(player2.getScore() + 1);
		}else if(player2choice < number){
			System.out.println("Your guess is too low! Try agian");
			player2.setScore(player2.getScore() + 1);
		}else if (player2choice == number){
			player2.setScore(player2.getScore() + 1);
			System.out.println("You guessed correctly!");
			System.out.println("Randomizing number choice....");
			number = dice.roll(100);
			System.out.println("New number found!");
			player2win = 1;
			player2turn = 0;
			player1turn = 1;
		}
		}while(player2win == 0);
	}
	
	if((player1win == 1)&&(player2win == 1)){
		System.out.println(player1.getName() + ", you guessed your number after " + player1.getScore() + " tries!");
		System.out.println(player2.getName() + ", you guessed your number after " + player2.getScore() + " tries!");
		if(player1.getScore() < player2.getScore()){
			System.out.println(player1.getName() + " wins the round!");
			player1.plusWin();
		}else if (player2.getScore() < player1.getScore()){
			System.out.println(player2.getName() + " wins the round!");
			player2.plusWin();
		}
		player1win = 0;
		player2win = 0;
	}
		if(player1.getWin() == 5){
			System.out.println(player1.getName() + " wins the game!");
			System.out.println(player2.getName() + " loses!");
			break;
		}else if (player2.getWin() == 5){
			System.out.println(player2.getName() + " wins the game!");
			System.out.println(player1.getName() + " loses!");
			break;
		}
		}while(true);
	}
}
