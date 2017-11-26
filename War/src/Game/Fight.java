package Game;

public class Fight {
	Value value;
	public Fight(){
		value = new Value();
	}
	
	public int battle(int a, int b){
		if(value.getValue(a) == 0){
			System.out.println("Player 1 loses the game!");
			System.out.println("Player 2 wins the game!");
			return 3;
		}else if(value.getValue(b)==0){
			System.out.println("Player 2 loses the game!");
			System.out.println("Player 1 wins the game!");
			return 3;
		}
		
		if((value.getValue(a) == 14)&&(value.getValue(b) == 2)){
			return 2;
		}else if((value.getValue(b) == 14)&&(value.getValue(a) == 2)){
			return 1;
		}
		
		if(value.getValue(a) > value.getValue(b)){
			return 1;
		}else if(value.getValue(a) < value.getValue(b)){
			return 2;
		}else if(value.getValue(a) == value.getValue(b)){
			return 0;
		}
		return (Integer) null;
	}

}
