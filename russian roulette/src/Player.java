public class Player{
	private String player;
	private int score;
	private int life;
	public Player(){
		life = 3;
	}
	
	public void setName(String a){
		player = a;
	}
	
	public String getName(){
		return player;
	}

	public void setScore(int a){
		score = a;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setLife(int a){
		life = a;
	}
	
	public int getLife(){
		return life;
	}
	
	
}