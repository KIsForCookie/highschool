/**
 * @(#)twitter.java
 *
 * twitter application
 *
 * @author
 * @version 1.00 2013/2/28
 */
import java.util.Scanner;

public class twitter {

    public static void main(String[] args) {
    	String message;
		int character;
		int choice;

do{

    	System.out.print("Type tweet here: ");
    	Scanner reader = new Scanner(System.in);
		message = reader.nextLine();
		character = message.length();
		if (character > 140){
			System.out.println("The tweet cannot be sent because you are " + (character - 140) + " characters over the limit! Try agian.");
			choice = 0;
		}
		else{
			System.out.print("Your message has been sent as a tweet! It contained " + character + " characters.");
				choice = 1;
		}

}while(choice == 0);
    }
}
