import java.util.*;
import java.lang.Exception;

public class Blackjack {
	
	Blackjack(){
		
	}

	
	public static void main(String[] args) {
		int hand[][] = new int[2][5];
		int handsplit[][] = new int[2][5];
		int aceCount[] = new int[2];
		int cardVal[] = new int[2];
		int deck[] = new int[52];
		int bet = 0;
		int recycleCard = 0;
		int cardNumber = 0;
		int money = 0;
		final int multiplier = 2;
		int num = 0;
		final int deckNumber = 8;
		int pass = 0;
		String choices = "";
		char choice = 'a';
		
		
		
		Card card = new Card();
		Random gen = new Random();
		Value value = new Value();
		PlayAgain play = new PlayAgain();
		ComputerTurn computer = new ComputerTurn();
		Scanner reader = new Scanner(System.in);
		money = 500;
		
		for(int i = 0; i < 52; i++){
			deck[i] = deckNumber;
		}
		System.out.print("Press Enter to start new round");
		do{	
			do{
				for(int i = 0; i < 2;i ++){
					for(int j = 0 ; j < 5; j ++){
						hand[i][j] = 0;
						handsplit[i][j] = 0;
						aceCount[i] = 0;
						cardVal[i] = 0;
						cardNumber = 0;
						recycleCard = 0;
					}
				}
				try{
					reader.nextLine();
					System.out.println("Bet. Your funds are " + money);
					bet = reader.nextInt();
					if((bet > money)||(bet < 0)){
						System.out.println("Not a valid bet");
						pass = 0;
					}else if(bet == 0){
						System.out.println("You can't bet nothing");
						pass = 0;
					}else{
						money -= bet;
						pass = 1;
					}
				}catch(Exception error){
					System.out.println("Not even a number");
					pass = 0;
				}
			}while(pass == 0);
			
			
			for (int i = 0; i < 52; i++){
				recycleCard += deck[i];
			}
			
			if(recycleCard > 20){
				System.out.println("Enough cards to play the game");
			}else if(recycleCard < 20){
				System.out.println("Not enough cards. Shuffling the deck");
				for(int i = 0; i < 52; i++){
					deck[i] = deckNumber;
				}
			}
			
			
			
			
			for(int i = 0; i < 2; i++){
				for(int j = 0; j < 5; j++){
					do{
					num = gen.nextInt(52);
					deck[num] = deck[num] - 1; 
					if(deck[num] < 0){
						deck[num] = deck[num] + 1;
						pass = 0;
					}else{
						hand[i][j] = num;
						pass = 1;
					}
					}while(pass == 0);
					
				}
			}
			
			cardVal[0] = value.getValue(hand[0][0]) + value.getValue(hand[0][1]);
			cardNumber = 1;
			System.out.println("Your hand: " + card.getCard(hand[0][0]) + ", " + card.getCard(hand[0][1]) + ". You have played " + (cardNumber + 1) + " cards");
			
				for(int j = 0; j < 2; j ++){
			if ((hand[0][j] == 0)||
					(hand[0][j] ==13)||
					(hand[0][j] == 26)||
					(hand[0][j] == 39)){
					aceCount[0]++;
				}
				
			}
			
			do{
			if(cardVal[0] > 21){
				if(aceCount[0] == 0){
					System.out.println("You lose");
					choice = 'a';
					bet = 0;
					play.play();
					pass = play.getPlay();
					
			}else if(aceCount[0] > 0){
					aceCount[0]--;
					cardVal[0] -= 10;
				}
			}
			
			if((cardVal[0] == 21)&&(cardNumber == 1)){
				System.out.println("you win");
				choice = 'a';
				bet = bet * multiplier;
				money += bet;
				bet = 0;
				play.play();
				pass = play.getPlay();
				break;
			}
			
			if(cardVal[0] <= 21){
				do{
					if(cardNumber == 4){
						computer.setHand(hand[1][0],hand[1][1],hand[1][2],hand[1][3],hand[1][4]);
						cardVal[1] = computer.playHand();
						//System.out.println(cardVal[1]);
						pass = 1;
						choice = 'a';
						if(cardVal[1] > 21){
							System.out.println("You win");
							bet = bet * 2;
							money += bet;
							bet = 0;
							play.play();
							pass = play.getPlay();
							break;
							
						}else if(cardVal[0] > cardVal[1]){
							
							System.out.println("You win");
							bet = bet * 2;
							money += bet;
							bet = 0;
							play.play();
							pass = play.getPlay();
							break;
						}else if(cardVal[0] < cardVal[1]){
						
							System.out.println("You lose");
							bet = 0;
							play.play();
							pass = play.getPlay();
							break;
						}else if(cardVal[0] == cardVal[1]){
							
							System.out.println("Tie");
							money += bet;
							bet = 0;
							play.play();
							pass = play.getPlay();
							break;
						}
					}else{	
					
							System.out.println("Hit or stay?[h/s]");
							choices = reader.next();
							choice = choices.charAt(0);
					
				
				if((choice == 'h')||(choice == 'H')){
					cardNumber++;
					cardVal[0] = cardVal[0] + value.getValue(hand[0][cardNumber]);
					System.out.println("Your card: " + card.getCard(hand[0][cardNumber]) + ". You have played " + (cardNumber + 1) + " cards");
					if ((hand[0][cardNumber] == 0)||
							(hand[0][cardNumber] ==13)||
							(hand[0][cardNumber] == 26)||
							(hand[0][cardNumber] == 39)){
							aceCount[0]++;
						}
					//cardVal[0] = cardVal[0] + value.getValue(hand[0][cardNumber]);
					pass = 1;
				}else if((choice == 's')||(choice == 'S')){
					computer.setHand(hand[1][0],hand[1][1],hand[1][2],hand[1][3],hand[1][4]);
					cardVal[1] = computer.playHand();
					//System.out.println(cardVal[1]);
					pass = 1;
					
					if(cardVal[1] > 21){
						System.out.println("You win");
						bet = bet * 2;
						money += bet;
						bet = 0;
						play.play();
						pass = play.getPlay();
						break;
						
					}else if(cardVal[0] > cardVal[1]){
						
						System.out.println("You win");
						bet = bet * 2;
						money += bet;
						bet = 0;
						play.play();
						pass = play.getPlay();
						break;
					}else if(cardVal[0] < cardVal[1]){
					
						System.out.println("You lose");
						bet = 0;
						play.play();
						pass = play.getPlay();
						break;
					}else if(cardVal[0] == cardVal[1]){
						
						System.out.println("Tie");
						money += bet;
						bet = 0;
						play.play();
						pass = play.getPlay();
						break;
					}
				}else{
					System.out.println("Invalid option");
					pass = 0;
				}
					}
					
				}while(pass == 0);
				
			}
			
			}while((choice == 'h')||(choice == 'H'));
			
			if(money == 0){
				System.out.println("Go home, you're broke.");
				System.out.println("GAME OVER");
				reader.close();
				System.exit(0);
			}
			
			
			
		} while(pass == 0);
		
		reader.close();

	}

}
