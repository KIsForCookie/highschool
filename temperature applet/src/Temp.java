
public class Temp{
	
	private double celcius = 0;
	
	public Temp(){}
	
	public void setCelcius(double degrees){
		celcius = degrees;
	}
	
	public void setFahrenheit(double degrees){
		celcius = (degrees - 32)*5/9;
	}
	
	public double getCelcius(){
		return celcius;
	}
	
	public double getFahrenheit(){
		celcius = celcius*9/5 + 32;
		return celcius;
	}
}