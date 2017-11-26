
public abstract class Abstract {
protected String hello;
protected String bye;

public Abstract(){
	hello = "";
	bye = "";
}

public Abstract(String h, String b){
	hello = h;
	bye= b;
}

abstract public String taco();

public final String getHello(){
	return hello;
}

public final String getBye(){
	return bye;
}

public String toString(){
	String str = hello + " " + bye;
	return str;
}
}
