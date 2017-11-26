//By: Alexander Kim

package game;

public class ComputerTurn { //the computer ai for the blackjack
	int value; //declarations
	int[][] hand;
	Value val;
	int cardNumber;
	int compcardnumber;
	int aceCount;
	int pass;
	int blackjack;
	
	public ComputerTurn(){
		val = new Value();//instanations
		hand = new int[1][5];
		value = 0;
		cardNumber = 0;
		aceCount = 0;
		pass = 0;
		compcardnumber = 0;
	}
	
	public void setHand(int one,int two,int three,int four,int five){ //the 5 cards that the ai will have
		cardNumber = 0;
		compcardnumber = 0;
		aceCount = 0;
		hand[0][0] = one;
		hand[0][1] = two;
		hand[0][2] = three;
		hand[0][3] = four;
		hand[0][4] = five;
	}
	
	public int getCardNumber(){ //returns the card number the ai is on
		return compcardnumber;
	}
	
	
	public int playHand(){ //plays the hand from the 5 cards given.
		compcardnumber += 2;
		cardNumber ++;
		value = val.getValue(hand[0][0]) + val.getValue(hand[0][1]); //value = face value of card1 + face value of card 2
		do{
			for(int j = 0; j < 2; j ++){ //checks for the number of aces ai has
				if ((hand[0][j] == 0)||
						(hand[0][j] ==13)||
						(hand[0][j] == 26)||
						(hand[0][j] == 39)){
						aceCount++;
					}
					
				}
			
			if(value > 21){//if dealer is over 21
				if(aceCount == 0){//if dealer has to aces
					return value;
					
				}else if(aceCount > 0){//if dealer has aces
					aceCount--;
					value -= 10;
					pass = 0;
				}
			}
			
			if((value != 21)&&(blackjack == 1)){//if player has blackjack
				return value;
			}else if(value == 21){//if dealder has blackjack
				return value;
				
			}else if(value >= 17){//if dealer is greater then 17
				return value;
			}
			
			if(value < 17){//if dealer is less then 17, hits to get another card
				do{
					if(cardNumber == 4){//if last card, ai cannot hit anymore
						return value;
					}else{//otherwise calculate number of aces and find the total value of dealer's cards
						cardNumber++;
						compcardnumber++;
						value = value + val.getValue(hand[0][cardNumber]);
						if ((hand[0][cardNumber] == 0)||
								(hand[0][cardNumber] ==13)||
								(hand[0][cardNumber] == 26)||
								(hand[0][cardNumber] == 39)){
								aceCount++;
							}
						pass = 0;
						if(value > 21){
							if(aceCount == 0){
								return value;
								
							}else if(aceCount > 0){
								aceCount--;
								value -= 10;
								pass = 0;
							}
						}
						
						if(value == 21){
							return value;
							
						}else if(value >= 17){
							return value;
						}
					}
				}while(pass == 0);
			}
		}while(pass == 0);
		
		return (Integer) null;
	}

}
