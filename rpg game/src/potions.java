public class potions{
	
	private int value;
	private int heal;
	private int weight;
	private String type;
	private int stack = 0;

public void setPotion(String Type,int hp, int wght, int val){ //sets the potions stats
	type = Type;
	heal = hp;
	weight = wght;
	value = val;
	}
	
public void addPotion(){ //for picking up a potion
	stack = stack + 1;
}
	
public int use(){ //using a potion
	if (stack > 0){
		stack = stack - 1;
		return heal;
	}else{
		return 0;
	}
}

public int getValue(){ //value for shops
	return value;
}

public int getWeight(){ //carry weight
	return (weight * stack);
}
	
public String getType(){ //gets the name of the potion
	return type.toLowerCase();
}

}