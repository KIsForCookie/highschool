package Items;

public class Weapon {
	private static int Weight;
	private static int ATK;
	private String Material = null;
	private String Weapontype;
	private String Name;
	private String WeaponType;
	private static int Handreq;
	boolean artifact = (false);
	private int Value = 0;
	private double BlowsMult = 0;
	private boolean equipped = false;
	
	public Weapon(int mat, int type){
		int matATK = 0;
		int matweight = 0;
		double typeATKMult = 0;
		double typeWeightMult = 0;
		double typeBlowsMult = 0;
		double matBlowsMult = 0;
		double matValMod = 0;
		double typeValMod = 0;
		
		if(artifact == false){
			switch(type){
			case 0:	this.Weapontype = "Broadsword"; 
				this.Handreq = 1;
				typeValMod = 50;
				typeATKMult = 1.1;
				typeWeightMult = 1;
				typeBlowsMult = 1.25;
				break;
			case 1: this.Weapontype = "War Axe";
					typeATKMult = 1.15;
					typeWeightMult = 1.1;
					typeBlowsMult = 1;
					this.Handreq = 1;
					typeValMod = 50;
					break;
			case 2: this.Weapontype = "Mace";
					typeATKMult = 1.5;
					typeWeightMult = 1.2;
					typeBlowsMult = 0.8;
					this.Handreq = 1;
					typeValMod = 30;
					break;
			case 3: this.Weapontype = "Spear";
					typeATKMult = 2;
					typeWeightMult = 1.3;
					typeBlowsMult = 0.8;
					this.Handreq = 2;
					typeValMod = 40;
					break;
			case 4: this.Weapontype = "Claymore";
					this.Handreq = 2;
					typeValMod = 75;
					typeATKMult = 1.35;
					typeWeightMult = 1.1;
					typeBlowsMult = 0.9;
				    break;
			
			case 5: this.Weapontype = "Battleaxe";
					typeATKMult = 1.5;
					typeWeightMult = 1.25;
					typeBlowsMult = 0.9;
					this.Handreq = 2;
					typeValMod = 75;
					break;
			case 6: this.Weapontype = "Warhammer";
					typeATKMult = 2.0;
					typeWeightMult = 1.5;
					typeBlowsMult = 0.75;
					this.Handreq = 2;
					typeValMod = 75;
					break;
			case 7: this.Weapontype = "Quarterstaff";
					typeATKMult = 0.75;
					typeWeightMult = 0.75;
					typeBlowsMult = 1.5;
					this.Handreq = 2;
					typeValMod = 25.0;
					break;
			case 8: this.Weapontype = "Dagger";
					typeATKMult = 2 / 3;
					typeWeightMult = 0.25;
					typeBlowsMult = 2.0;
					this.Handreq = 1;
					typeValMod = 20.0;
					break;
			}//switch(type)
			switch(mat){
			case 0: this.Material = "Wooden";
					matweight = 1;
					matATK = 1; 
					matValMod = 0.25;
					matBlowsMult = 1.5;
					break;
			case 1: this.Material = "Bronze";
					matweight = 5;
					matATK = 3;
					matValMod = 0.75;
					matBlowsMult = 0.8;
					break;
			case 2: this.Material = "Iron";
					matweight = 5;
					matATK = 4;
					matValMod = 1;
					matBlowsMult = 0.9;
					break;
			case 3: this.Material = "Steel";
					matweight = 4;
					matATK = 5;
					matValMod = 1.25;
					matBlowsMult = 1.0;
					break;
			case 4: this.Material = "Wootz Steel";
					matweight = 4;
					matATK = 6;
					matValMod = 1.5;
					matBlowsMult = 1.25;
					break;
			case 5: this.Material = "Orichalcum";
					matweight = 5;
					matATK = 8;
					matValMod = 1.75;
					matBlowsMult = 1.0;
					break;
			case 6: this.Material = "Adamantium";
					matweight = 2;
					matATK = 10;
					matValMod = 2.5;
					matBlowsMult = 2.5;
					break;
			case 7: this.Material = "Gold";
					matweight = 15;
					matATK = 1;
					matValMod = 3.25;
					matBlowsMult = 0.25;
					break;
			}//switch(mat)
		}//artifact false
		
		this.Name = Material + " " + Weapontype;
		if(this.Material == "Wooden" && this.Weapontype == "Katana")
			this.Name = "Bokken";
		
		this.ATK = (int)(matATK*typeATKMult);
		if(this.ATK == 0)
			this.ATK = 1;
		
		this.Weight = (int)(matweight*typeWeightMult);
		if(this.Weight == 0)
			this.Weight = 1;
		
		this.Value = (int)(typeValMod * matValMod);
		
		this.BlowsMult = (typeBlowsMult + matBlowsMult)/2;
	}
	
	public int getWeight(){
		return Weight;
	}
	public int getATK(){
		return ATK;
	}
	public void setName(String name){
		this.Name = name;
	}
	public String getName(){
		return Name;
	}
	public void isArtifact(){
		artifact = true;
	}
	public int getVal(){
		return this.Value;
	}
	public double getBlows(){
		return this.BlowsMult;
	}
	public int getHandsReq(){
		return this.Handreq;
	}

	public boolean equippable(){
		return this.equipped;
	}
}
