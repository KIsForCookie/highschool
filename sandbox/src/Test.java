
public class Test extends Abstract{
	
	protected String taco;
	
	public Test(){
		super();
		taco = "";
	}
	
	public Test(String hello, String bye, String food){
		super.hello = hello;
		super.bye = bye;
		taco = food;
	}

	public String taco(){
		return taco;
	}

}
