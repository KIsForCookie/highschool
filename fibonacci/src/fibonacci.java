import java.util.Scanner;

public class fibonacci {

	public static void main(String[] args) {
		while(true){
		int a = 0;
		int b = 1;
		int c = 0;
		double n;
	Scanner reader = new Scanner(System.in);
	System.out.println("nth term of fibonacci's sequence: ");
	n = reader.nextDouble();
	while(c < n){
		if(c == (n - 1)){
			System.out.println(b);
			c = c+ 1;
		}else{
			b = a + b;
			a = b - a;
			c = c + 1;
		}
	}
		}	
	
	
	
	}
	}
