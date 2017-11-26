package Items;

public class Armour3 extends item{
	
	private static int Defense;
	private String Material = null;
	private static String Name;	
	private String Armourtype;
	boolean artifact = false;
	public static boolean equipped = false;
	private static Integer BodyLoc;
	
	public Armour3(int Mat, int BL){		
		int matDEF = 0;
		int matWeight = 0;
		double typeDEFmult = 0;
		double typeWeightmult = 0;
		double matValMod = 0;
		double typeValMod = 0;
		BodyLoc = BL;
				
				if(artifact == false){
					if(BodyLoc == 0){
						Armourtype = "Helmet";
						typeDEFmult = 1;
						typeWeightmult = 1.5; 
						typeValMod = 225;
					}
					else if(BodyLoc == 1){
						Armourtype = "Chestplate";
						typeDEFmult = 2.75;
						typeWeightmult = 2.5;
						typeValMod = 500;
					}
					else if(BodyLoc == 2){
						Armourtype = "Gauntlets";
						typeDEFmult = 1;
						typeWeightmult = 1.25;
						typeValMod = 100;
					}
					else if(BodyLoc == 3){
						Armourtype = "Boots";
						typeDEFmult = 1;
						typeWeightmult = 1.25;
						typeValMod = 100;
					}
					else if (BodyLoc == 4){
						Armourtype = "Shield";
						typeDEFmult = 2;
						typeWeightmult = 1.25;
						typeValMod = 150;
					}
				}
				
				switch(Mat){
					case 0: this.Material = "Bronze";
							matWeight = 5;
							matDEF = 3;
							matValMod = 0.75;
							break;
					case 1: this.Material = "Iron";
							matWeight = 5;
							matDEF = 5;
							matValMod = 1.0;
							break;
					case 2: this.Material = "Steel";
							matWeight = 4;
							matDEF = 6;
							matValMod = 1.25;
							break;
					case 3: this.Material = "Wootz Steel";
							matWeight = 4;
							matDEF = 7;
							matValMod = 1.5;
							break;
					case 4: this.Material = "Orichalcum";
							matWeight = 5;
							matDEF = 9;
							matValMod = 1.75;
							break;
					case 5: this.Material = "Adamantium";
							matWeight = 2;
							matDEF = 12;
							matValMod = 2.5;
							break;
					}
		
		this.Defense = (int)(matDEF*typeDEFmult);
		if(this.Defense == 0)
			this.Defense = 1;
		
		this.Weight = (int)(matWeight*typeWeightmult);
		if(this.Weight == 0)
			this.Weight = 1;
		
		this.Name = Material + " " + Armourtype;
		
		this.Value = (int)(typeValMod * matValMod);

	}
	
	public static double getWeight(){
		return Weight;
	}
	public static int getDef(){
		return Defense;
	}
	public static int getBodyLoc(){
		return BodyLoc;
	}
	public void setName(String name){
		Armour3.Name = name;
	}
	public static String getName(){
		return Name;
	}
	public static int getVal(){
		return Value;
	}
	public static void isEquipped(){
		equipped = true;
	}
	public boolean equippable(){
		return equipped;
	}
	
}
