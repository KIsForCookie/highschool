public class circle{
	
	private int velocity;
	private int degree;
	
	public circle(){}
	
	public void setVelocity(int a){
		velocity = a;
	}
	
	public void setDegree(int a){
		degree = a % 360;
	}

	public void turn(int a){
		degree = (degree + a) % 360;
	}
	
	public void move(){
		
	}
}