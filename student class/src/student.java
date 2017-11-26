public class student{
	
	private String name;
	private int test1;
	private int test2;
	private int test3;
	private int avg;
	private int highScore;
	private String result;
	
	public void setName(String a){
		name = a;
	}
	
	public String getName(){
		return name;
	}
	
	public void setScore(int a, int score){
		if(a == 1){
			test1 = score;
		}else if(a == 2){
			test2 = score;
		}else if(a == 3){
			test3 = score;
		}else{
			test3 = score;
		}
	}
	
	public int getAverage(){
		avg = (test1 + test2 + test3)/3;
		return avg;
	}
	
	public int getHighScore(){
		highScore = test1;
		if(highScore < test2){
			highScore = test2;
		}
		if(highScore < test3){
			highScore = test3;
		}
		return highScore;
	}
	
	public String getResult(){
		result = "Name: " + name + "\nTest1: " + test1 + "\nTest2: " + test2 +
				 "\nTest3: " + test3 + "\nAverage: " + avg;
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}