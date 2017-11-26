import java.util.Random;

public class Die{
	private int dice;
	private Random gen;
	
	
public Die(){
	
gen = new Random();

}
		
	
	public int roll(int a){
	dice = gen.nextInt(a) + 1;
	return dice;
	}
	
	
	
	
	}
	
