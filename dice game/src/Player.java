public class Player{
	private String player;
	private int score;
	private int life;
	private int win;
	public Player(){
		life = 3;
		win = 0;
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
	
	public void plusWin(){
		win = win + 1;
	}
	
	public void resetWin(){
		win = 0;
	}
	
	public int getWin(){
		return win;
	}
	
}