/*
 TempConvert.Java
 By: Alexander Kim
 February 11, 2013
 Period 1
 Description: This program takes a fahrenheit value from the user and converts it to celsius.
 */
import java.util.Scanner; //imports a scanner
import java.text.DecimalFormat; //imports a decimal formatter

public class temp_conversion {

    public static void main(String[] args) {

    	Scanner reader = new Scanner(System.in); //creates a new scanner object
    	Scanner input = new Scanner(System.in); //creates a new scanner object
    	DecimalFormat temperature = new DecimalFormat("#0.00"); //creates a function called temperature that formats decimals to 2 places
    	double fahrenheit; //declares double variable fahrenheit
		double celsius; //declares double vaiable celsius
		double kelvin; //declares double variable kelvin
		String end; //declares string end
		int choice = 0; //declares interger variable choice
		do{ //starts a loop
	do{//starts a loop
			System.out.print("Enter degrees fahrenheit: "); //asks user for input
			fahrenheit = reader.nextDouble(); //makes fahrenheit = user input
			if (fahrenheit < -459) { //if statement to prevent going below absolute zero
				System.out.println("Value is below absolute zero. Try agian."); //tells user to reenter value if value is too low
			}
	}while (fahrenheit < -459); // loops as long as fahrenheit is below -459
			celsius = (fahrenheit - 32.0) * 5.0 / 9.0; //calculates celsius value from fahrenheit
			System.out.print("The equivalent in celsius is: "); //prints "The equivalent in celsius is: "
			System.out.print(temperature.format(celsius));//prints the value of celsius
			System.out.println("°C"); //prints out unit
			kelvin = (celsius + 273.15); //calculates kelvin from celsius
			System.out.print("The equivilant in kelvin is: "); //prints "The equivilant in kelvin is: "
			System.out.print(temperature.format(kelvin)); //prints the value of kelvin
			System.out.println("K"); //prints unit
			System.out.print("Would you like to do another calculation? [y/n]: "); //asks user if they want to do another calculation and waits for a response
			end = input.nextLine(); //end = the next string input the user inputs
		    if (end == "y"){ // if end = "y"
		    choice = 1;	} //choice is 1

		    else if (end == "n"){ //else if end is "n"
		    	choice = 0; //choice is 0
		    }
		}while (choice == 1); //loops so long as choice = 1
 }

    }
