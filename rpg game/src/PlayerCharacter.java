import java.util.HashMap;


public class PlayerCharacter{
	
	private int maxhealth = 10;
	private int level = 1;
	private int LvlUpXP = (int)(Math.pow(level/3, 2.5)+25);
	private int STR = 5;
	private int CON = 5;
	private int INT = 5;
	private int AGL = 5;
	private int DEF;
	private int ATK = (int)(1+(3/2)*STR);
	private int LUK = 5;
	
	HashMap Equipment = new HashMap();
	
	private int age;
	private String race;
	private String alignment;
	
	private String Gender;
	private String name;
	private String Class;	//affects stats
	private String Faction;	//certain creatures won't attack you
	private int Hands = 2;	//1-h weapons require 1-h, 2-h weapons require 2 hands
	private double Carryweight = 35.0;	//WeightCarried cannot exceed this value
	private double WeightCarried = 0;	//total weight of items carried 
	private double blows;	//potential strikes per attack
	
	public String Chest;
	public String Footwear;
	public String Gloves;
	public String Helmet;
	
	public int health = 10;
	public int exp = 0;
	public int money = 0;
	private int overencumbered = 0;
	private int poisoned = 0;
	private int blind = 0;
	private int hunger = 0;

	public PlayerCharacter(){
		
	}	//constructor
	
	public int editHealth(int b){
		health -= b;
		return health;
	}
	
	public void setAge(int b){	//set age
		age = b;
	}
	public void setRace(String b){	//set race
		race = b;
	}
	public String getRace(){
		return race;
	}

	
	public void setRole(String b){	//class; affects stats
		Class = b;
	}
	public String getRole(){
		return Class;
	}
	public void editHand(int b){	//affects what weapons PC can use
		Hands += b ;
	}
	public int getHand(){
		return Hands;
	}
	public void Alignment(int b){	//affects ATK and DEF
		int al;
		al = b;
		if (al<-2)
			alignment = ("chaotic");
		else if (al>=-2&&al<=2)
			alignment = ("neutral");
		else
			alignment = ("lawful");
	}
	public String getAlignment(){
		return alignment;
	}
	public void editCarryweight(){	//affects carrying capacity; if  decreases combat ability
		Carryweight += (3*STR);
	}
	public double getCarryweight(){
		return Carryweight;
	}
	public void setName(String b){
		name = b;
	}
	
	public String getName(){
		return name;
	}
	
	public void setGender(String b){	//setGender; affects stats
		Gender = b;
	}
	public String getGender(){
		return Gender;
	}
	public void editWeight(int a){
		WeightCarried += a;
		if (WeightCarried>Carryweight)
			overencumbered = 1;
	}
	public int getWeight(){
		return overencumbered;
	}
	public void healthRegen(){
		int temphealth = health;
		
	}
	public void equipItem(){
		
	}
	public void drinkPotion(){
		
	}
}
