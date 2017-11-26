/**
 * @(#)time_converter.java
 *
 * time_converter application
 *
 * @author
 * @version 1.00 2013/2/25
 */
 import java.util.Scanner;
 import java.text.DecimalFormat;

public class time_converter {

    public static void main(String[] args) {
    	Scanner reader = new Scanner(System.in);
    	DecimalFormat round = new DecimalFormat("#0");
    	double test;
    	double input;
    	double second;
    	double minute;
    	double hour;
    	double day;
do{

    	System.out.print("Enter second: ");
    	input = reader.nextDouble();
    	test = input % 1;
    	if (test > 0){
    		System.out.println("You cannot have decimal seconds! Try agian.");
    	}
    	else if(input < 0){
    		System.out.println("You cannot have negative seconds! Try agian.");
    	}

}while ((test > 0) || (input < 0));

		test = input;
		second = input % 60;
		input = input - second;
		input = input / 60;
		minute = input % 60;
		input = input - minute;
		input = input / 60;
		hour = input % 24;
		input = input - hour;
		day = input / 24;
		System.out.println(round.format(test) + " seconds converts to: " + round.format(day) + " day(s), "
			+ round.format(hour) + " hour(s), " + round.format(minute) + " minute(s), and " +
				 round.format(second) + " second(s)." );

    }
}
