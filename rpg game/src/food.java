
public class food{
	private int value;
	private int hunger;
	private int weight;
	private String type;
	private int stack = 0;
	
	public food(){}
	
	public void setFood(String Type,int HungerReplenish, int wght, int val){ //sets the potions stats
		type = Type;
		hunger = HungerReplenish;
		weight = wght;
		value = val;
	}
	
	public void addFood(){ //for picking up food
			stack = stack + 1;
	}
	
	public int eat(){ //eating food
		if (stack > 0){
			stack = stack - 1;
			return hunger;
		}else{
			return 1;
		}
	}
	public int getValue(){ //value for shops
			return value;
		}

	public int getWeight(){ //carry weight
			return (weight * stack);
		}
			
	public String getType(){ //gets the name of the food
			return type.toLowerCase();
		}
	
}