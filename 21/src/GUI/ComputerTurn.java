package GUI;

public class ComputerTurn {
	private int value;
	private int[][] hand;
	private Value val;
	private Card card;
	int cardNumber;
	int compcardnumber;
	private int aceCount;
	private int pass;
	
	public ComputerTurn(){
		val = new Value();
		hand = new int[1][5];
		value = 0;
		card = new Card();
		cardNumber = 0;
		aceCount = 0;
		pass = 0;
		compcardnumber = 0;
	}
	
	public void setHand(int one,int two,int three,int four,int five){
		cardNumber = 0;
		compcardnumber = 0;
		aceCount = 0;
		hand[0][0] = one;
		hand[0][1] = two;
		hand[0][2] = three;
		hand[0][3] = four;
		hand[0][4] = five;
	}
	
	public int getCardNumber(){
		return compcardnumber;
	}
	
	public int playHand(){
		compcardnumber += 2;
		cardNumber ++;
		value = val.getValue(hand[0][0]) + val.getValue(hand[0][1]);
		do{
			for(int j = 0; j < 2; j ++){
				if ((hand[0][j] == 0)||
						(hand[0][j] ==13)||
						(hand[0][j] == 26)||
						(hand[0][j] == 39)){
						aceCount++;
					}
					
				}
			
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
			
			if(value < 17){
				do{
					if(cardNumber == 4){
						return value;
					}else{
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
