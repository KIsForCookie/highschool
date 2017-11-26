import java.util.Scanner;


public class russianroulette {
	
	
public static void main(String[] args) {
Die dice = new Die();
Player player1 = new Player();
Player player2 = new Player();
Scanner reader = new Scanner(System.in);
int chance = 0;
int player1turn = 0;
int player2turn = 0;
int turn = 0;
@SuppressWarnings("unused")
String pause;
int chamber;

System.out.println("What is the name of the first player?: ");
player1.setName(reader.nextLine());
System.out.println("What is the name of the second player?: ");
player2.setName(reader.nextLine());
System.out.println("Time to play russian roulette!");
chamber = dice.roll(6);
chance = dice.roll(6);
System.out.println(player1.getName() + " spins the chamber. It lands on chamber " + chance);
turn = dice.roll(2);


if (turn == 1){
	player1turn = 1;
	player2turn = 0;
	System.out.println(player1.getName() + " starts!");
}else if(turn == 2){
	player1turn = 0;
	player2turn = 1;
	System.out.println(player2.getName() + " starts!");
}
do{
if (player1turn == 1){
	System.out.print(player1.getName() + " press enter to pull the trigger. It is chamber " + chance);
	pause = reader.nextLine();
	if (chance == chamber){
		System.out.println("*BANG!*");
		System.out.println(player1.getName() + " died!");
		System.out.println(player2.getName() + " survived!");
		break;
	}else{
		System.out.println("*click*");
		chance = chance + 1;
		player2turn = 1;
		player1turn = 0;
		if(chance == 7){
			chance = 1;
		}
	}
}else if(player2turn == 1){
	System.out.print(player2.getName() + " press enter to pull the trigger. It is chamber " + chance);
	pause = reader.nextLine();
	if (chance == chamber){
		System.out.println("*BANG!*");
		System.out.println(player2.getName() + " died!");
		System.out.println(player1.getName() + " survived!");
		break;
	}else{
		System.out.println("*click*");
		chance = chance + 1;
		player1turn = 1;
		player2turn = 0;
		if(chance == 7){
			chance = 1;
		}
	}
}

}while(true);
	}
}
