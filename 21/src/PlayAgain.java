import java.util.Scanner;
public class PlayAgain {
	
	private Scanner reader;
	private String choiceS;
	private int pass;
	private int play;
	private char choice;
	
	
	
	public PlayAgain(){
		reader = new Scanner(System.in);
		choiceS = "";
		choice = 'a';
		pass = 0;
		
		
	}
	
	public void play(){
		
		do{
		System.out.println("Would you like to play again?[y/n]");
		choiceS = reader.next();
		choice = choiceS.charAt(0);
		if((choice == 'y')||(choice == 'Y')){
			play = 0;
			pass = 1;

		}else if((choice== 'n')||(choice =='N')){
			System.out.println("Goodbye. Please come again");
			play = 1;
			pass = 1;

		}else{
			System.out.println("Invalid input");
			pass = 0;
		}
		}while(pass == 0);
	}
	
	public int getPlay(){
		return play;
	}

}
