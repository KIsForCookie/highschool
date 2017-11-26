//By: Areg Vanesyan

package game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.Random;


public class CrapsPanel extends JPanel implements ActionListener{
	

	// The following lines declare the labels for the form.
	public JLabel lblWins;
	public  JLabel lblLoses;
	public  JLabel lblDiceOne;
	public  JLabel lblDiceTwo;
	public  JLabel lblBank;
	public  JLabel lblBet;
	public  JLabel lblPointNumber;
	public  JLabel lblBackground;
	public  JLabel lblMessage;
	// The following lines declare the buttons for the form.
	public  JButton btnOneDollar;
	public  JButton btnFiveDollar;
	public  JButton btnTwentyFiveDollar;
	public  JButton btnFiftyDollar;
	public  JButton btnOneHundredDollar;
	public  JButton btnRollDie;
	public  JButton btnLeave; 
	public  Timer timer,updateTimer;
	
    Random GeneratorOne = new Random (); // Makes a new randomizer.
	Random GeneratorTwo = new Random ();// Makes a new randomizer.
	
	// The following lines declare the variables of the program.
	int intDieRollCount = 0;  
	int intNumberOfTimesWon = 0;
	int intNumberOfTimesLost = 0 ;
	int intBet = 0;
	int intPointNumber1 = 0;
	int intRollDieCounter = 0;
	int intBank = 0;
	int intNumberOfTimesPlayed =0;
	String strBet = "";
	ImageIcon[] diceImageArray = new ImageIcon[6];
	
	//  The following lines convert from string to integer
	String strMessage = "";
	String strBank= String.valueOf(intBank); // Integer is converted to string.
	String strNumberOfTimesWon= String.valueOf(intNumberOfTimesWon); // Integer is converted to string.
	String strNumberOfTimesLost= String.valueOf(intNumberOfTimesLost); // Integer is converted to string.
	String strNumberOfTimesPlayed= String.valueOf(intNumberOfTimesPlayed); // Integer is converted to string.
	public CrapsPanel() // Creates the form.
	{
	   for (int i = 0; i < 6; ++i)
		{
			diceImageArray[i] = new ImageIcon("res/" + (int)(i + 1) + ".png");
		}
	   setLayout(null);
	   lblBackground = new JLabel(new ImageIcon("res/crapsbackground.jpg")); // Names the label and sets the picture of the label.
	   lblBackground.setBounds(0,0,1024,800);// Sets the size and location of the label.
	   lblMessage = new JLabel(""); // Sets the text inside the label.
	   lblMessage.setBounds(220,500,100,50);//Sets the size and location of the label.
	   add(lblMessage); // Adds the label to the form.
	   lblMessage.setFont(new Font("Serif", Font.PLAIN, 20)); // Sets font.
	   lblWins = new JLabel ("Wins:"); // Names the label and sets the text of the label.
	   lblWins.setBounds(225, 148, 150, 30); // Sets the size and location of the label.
	   add(lblWins); // Adds the label to the form.
	   lblLoses = new JLabel ("Losses:"); // Names the label and sets the text of the label.
	   lblLoses.setBounds(225, 189, 282, 30);// Sets the size and location of the label.
	   add(lblLoses); // Adds the label to the form.
	   lblDiceOne = new JLabel (diceImageArray[0]);// Names the label and sets the text of the label.
	   lblDiceOne.setBounds(65, 11, 106, 30);//Sets the size and location of the label.
	   add(lblDiceOne); // Adds the label to the form.
	   lblDiceTwo = new JLabel (diceImageArray[0]);// Names the label and sets the text of the label.
	   lblDiceTwo.setBounds(157, 11, 250, 30);//Sets the size and location of the label.
	   add(lblDiceTwo); // Adds the label to the form.
	   lblBank = new JLabel ("Bank: 500 $"); // Names the label and sets the text of the label.
	   lblBank.setBounds(10, 189, 212, 30);//Sets the size and location of the label.
	   add(lblBank);// Adds the label to the form.
	   lblBet = new JLabel ("Bet:");// Names the label and sets the text of the label.
	   lblBet.setBounds(10, 148, 250, 30);//Sets the size and location of the label.
	   add(lblBet); // Adds the label to the form.
	   lblPointNumber = new JLabel ("Point Number:");// Names the label and sets the text of the label.
	   lblPointNumber.setBounds(92, 38, 250, 30);//Sets the size and location of the label.
	   lblPointNumber.setFont(new Font("Serif", Font.PLAIN, 20)); // Sets font.
	   add(lblPointNumber); // Adds the label to the form.
	   
	   btnLeave = new JButton("Leave table");// Names the button and sets the next.
	   btnLeave.setBounds(850,675,150,50); // Sets the size and location of the button.
	   add(btnLeave);// Adds the button to the form
	   btnOneDollar = new JButton ("1$");// Names the button and sets the next.
	   btnOneDollar.setBounds(92, 189, 59, 30); // Sets the size and location of the button.
	   add(btnOneDollar); // Adds the button to the form
	   btnOneDollar.setBackground(Color.GRAY); // Sets the color of the button.
	   btnOneDollar.setForeground(Color.white); // Sets the color of the letters within the button.
	   btnFiveDollar = new JButton ("5$");// Names the button and sets the next.
	   btnFiveDollar.setBounds(152, 189, 59, 30); // Sets the size and location of the button.
	   btnFiveDollar.setBackground(Color.RED); // Sets the color of the button.
	   btnFiveDollar.setForeground(Color.white);// Sets the color of the letters within the button.
	   add(btnFiveDollar);
	   btnTwentyFiveDollar = new JButton ("25$");// Names the button and sets the next.
	   btnTwentyFiveDollar.setBounds(92, 158, 59, 30); // Sets the size and location of the button.
	   add(btnTwentyFiveDollar); // Adds the button to the form
	   btnTwentyFiveDollar.setBackground(Color.GREEN); // Sets the color of the button.
	   btnTwentyFiveDollar.setForeground(Color.white);// Sets the color of the letters within the button.
	   
	   btnFiftyDollar = new JButton ("50$");// Names the button and sets the next.
	   btnFiftyDollar.setBounds(152, 158, 59, 30); // Sets the size and location of the button.
	   add(btnFiftyDollar); // Adds the button to the form
	   btnFiftyDollar.setBackground(Color.BLUE); // Sets the color of the button.
	   btnFiftyDollar.setForeground(Color.white);// Sets the color of the letters within the button.
	   btnOneHundredDollar = new JButton ("100$");// Names the button and sets the next.
	   btnOneHundredDollar.setBounds(92, 127, 119, 30); // Sets the size and location of the button.
	   add(btnOneHundredDollar); // Adds the button to the form
	   btnOneHundredDollar.setBackground(Color.BLACK); // Sets the color of the button.
	   btnOneHundredDollar.setForeground(Color.white);// Sets the color of the letters within the button.
	   btnRollDie = new JButton ("Roll Dice");// Names the button and sets the next.
	   btnRollDie.setBounds(102, 79, 95, 37); // Sets the size and location of the button.
	   add(btnRollDie); // Adds the button to the form
	   btnRollDie.setEnabled(false); // disables the Roll Die button
	   lblPointNumber.setVisible(false); // makes label not visible
	   lblDiceOne.setVisible(false);// makes label not visible
	   lblDiceTwo.setVisible(false);// makes label not visible
	   btnRollDie.setVisible(false);// makes label not visible
	   add(lblBackground); // adds the label background to the form. 
	   
	   
	   btnRollDie.setSize(130, 37);
		lblMessage.setSize(199, 50);
		lblDiceOne.setSize(68, 64);
		lblDiceTwo.setSize(69, 64);
		btnLeave.setLocation(850, 711);
		lblBackground.setSize(1024, 873);
		btnFiveDollar.setLocation(150, 481);
		btnOneDollar.setLocation(89, 481);
		lblMessage.setLocation(474, 718);
		lblDiceTwo.setLocation(165, 203);
		lblDiceOne.setLocation(78, 203);
		lblPointNumber.setLocation(86, 278);
		btnRollDie.setLocation(85, 534);
		lblWins.setLocation(52, 717);
		lblLoses.setLocation(52, 738);
		btnOneHundredDollar.setLocation(90, 414);
		btnFiftyDollar.setLocation(150, 448);
		btnTwentyFiveDollar.setLocation(89, 448);
		lblBet.setLocation(167, 717);
		lblBackground.setLocation(0, -105);
		lblBank.setLocation(167, 738);
	   
		updateTimer = new Timer(10,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				lblBank.setText("Bank:  " + intBank + " $"); // Sets the text of the label.
				lblBet.setText("Bet:  " + intBet + " $");// Sets the text of the label.
				lblWins.setText("Wins:  " + intNumberOfTimesWon);// Sets the text of the label.
				lblLoses.setText("Losses:  " + intNumberOfTimesLost);// Sets the text of the label.
				updateTimer.stop();
			}
		});
	   
	   btnRollDie.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   
	   
	   {
		   public void actionPerformed(ActionEvent arg0) // This is the function that is being called when the button is pressed.
	       {
			   if (intBet <= intBank) // if int bet is less then or equal to int bet.
			   {
				   timer.start(); // timer starts.
				   lblDiceOne.setVisible(true); //dice become visible
				   lblDiceTwo.setVisible(true);
				   lblMessage.setVisible(false); // message label becomes invisible.
				   btnRollDie.setText("Please Wait.."); // Text changes
				   btnRollDie.setEnabled(false);// Roll die is disabled
			   }
			   else 
			   {
				    timer.stop(); // timer stops
		   			lblDiceOne.setVisible(false); // dice become invisible
		   			lblDiceTwo.setVisible(false);
		   			lblPointNumber.setVisible(false); // label is visible
					strMessage = ("Insufficient Funds"); // "Insufficient Funds is assigned to the variable.
					lblMessage.setText(strMessage); // The message inside the message box.
					lblMessage.setVisible(true); // label is visible
					intBet = 0; // The variable for bet is equal to 0.
					strMessage = ""; // Everything within the variable is erased.
					lblBet.setText("Bet:"); // Sets the text of the label.
					btnRollDie.setEnabled(false);// Disables the button.
					intRollDieCounter = 0; // Resets the counter for the dice.
			   		intPointNumber1 = 0; // The point number is reset.
			   		lblPointNumber.setText("Point Number:");// Sets the text of the label.
					return; // Returns back to the code.
			   }
	       }
		   
	   });
	     
	   
	   btnOneDollar.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   {
		   public void actionPerformed(ActionEvent arg0) // This is the function that is being called when the button is pressed.
		   {
			   intBet +=1; // The bet is increased by 1$.
			   strBet= String.valueOf(intBet);// Integer is converted to string.
			   lblBet.setText("Bet:  " + strBet + " $"); // Sets the text of the label.
			   btnRollDie.setEnabled(true); // Enables the button.
			   btnRollDie.setVisible(true); 
			   lblMessage.setVisible(false); 
		   }
	   });
	   btnFiveDollar.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   {
	      public void actionPerformed(ActionEvent arg0) // This is the function that is being called when the button is pressed.
	      {
	    	  intBet +=5; // The bet is increased by 5$.
	    	  strBet= String.valueOf(intBet);// Integer is converted to string.
	          lblBet.setText("Bet:  " + strBet + " $");// Sets the text of the label.
	          btnRollDie.setEnabled(true);// Enables the button.
	          btnRollDie.setVisible(true);
	          lblMessage.setVisible(false);
	      }
	   });
	   btnTwentyFiveDollar.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   {
	      public void actionPerformed(ActionEvent e) // This is the function that is being called when the button is pressed.
	      {
	    	  intBet +=25; // The bet is increased by 25$.
	    	  strBet= String.valueOf(intBet);// Integer is converted to string.
	          lblBet.setText("Bet:  " + strBet + " $");// Sets the text of the label.
	          btnRollDie.setEnabled(true);// Enables the button.
	          btnRollDie.setVisible(true);
	          lblMessage.setVisible(false);
	      }
	   });
	   btnFiftyDollar.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   {
	      public void actionPerformed(ActionEvent arg0) // This is the function that is being called when the button is pressed.
	      {
	    	  intBet +=50; // The bet is increased by 50$.
	    	  strBet= String.valueOf(intBet);// Integer is converted to string.
	          lblBet.setText("Bet:  " + strBet + " $");// Sets the text of the label.
	          btnRollDie.setEnabled(true);// Enables the button.
	          btnRollDie.setVisible(true);
	          lblMessage.setVisible(false);
	      }
	   });
	   btnOneHundredDollar.addActionListener(new ActionListener()  // Adds the handler method to the button.
	   {
	      public void actionPerformed(ActionEvent arg0) // This is the function that is being called when the button is pressed.
	      {
	    	  intBet +=100; // The bet is increased by 100$.
	    	  btnRollDie.setEnabled(true);// Enables the button.
	    	  strBet= String.valueOf(intBet);// Integer is converted to string.
	          lblBet.setText("Bet:  " + strBet + " $");// Sets the text of the label.
	          btnRollDie.setVisible(true);
	          lblMessage.setVisible(false);
	      }
	   });

	   timer = new Timer(300, this); // timer is declared.
	}
	
	public void reset(){
		lblMessage.setVisible(false);
		lblDiceOne.setVisible(false);
		lblDiceTwo.setVisible(false);
		intDieRollCount = 0;  
		intNumberOfTimesWon = 0;
		intNumberOfTimesLost = 0 ;
		intBet = 0;
		intPointNumber1 = 0;
		intRollDieCounter = 0;
		intNumberOfTimesPlayed =0;
		updateTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) { // if timer is on.
			int dieOne = GeneratorOne.nextInt(6); // the dice variable randomizes
			int dieTwo = GeneratorTwo.nextInt(6);
			lblDiceOne.setIcon(diceImageArray[dieOne]); // dice pictures change.
			lblDiceTwo.setIcon(diceImageArray[dieTwo]);

			if (intDieRollCount < 6) {
				intDieRollCount++; // die integer increases by one.
				
			} else {
				intDieRollCount = 0; // die integer set to 0.
				timer.stop(); // timers stops.
				btnRollDie.setText("Roll Die"); // Sets the text for the button
				btnRollDie.setEnabled(true); // makes button enabled
				lblPointNumber.setVisible(true); // shows label point number.
				diceRolled(dieOne + 1, dieTwo + 1); 
			}

		}
		
	}
	private void diceRolled(int dieOne, int dieTwo) {
		  if (intRollDieCounter ==0) // If the counter is equal to zero then.
		   {
		   		if (intBet <= intBank) // If the players bet is less then or equal to the amount in the bank.
		   		{ 
		   			intBank -= intBet; // The bet is subtracted from the bank.
		   		}
		   		else // Anything else.
		   		{
		   			timer.stop();
		   			lblDiceOne.setVisible(false);
		   			lblDiceTwo.setVisible(false);
		   			lblPointNumber.setVisible(false);
					strMessage = ("Insufficient Funds"); // "Insufficient Funds is assigned to the variable.
					lblMessage.setText(strMessage); // The message inside the message box.
					lblMessage.setVisible(false);
					intBet = 0; // The variable for bet is equal to 0.
					strMessage = ""; // Everything within the variable is erased.
					lblBet.setText("Bet:"); // Sets the text of the label.
					btnRollDie.setEnabled(false);// Disables the button.
					intRollDieCounter = 0; // Resets the counter for the dice.
			   		intPointNumber1 = 0; // The point number is reset.
			   		lblPointNumber.setText("Point Number:");// Sets the text of the label.
					return; // Returns back to the code.
	            }
		   }
			String strBank= String.valueOf(intBank); // Integer is converted to string.
			lblBank.setText("Bank:  " + strBank + " $"); // Sets the text of the label.
			int intPlayerNumber = dieOne + dieTwo; // Adds the values of the two dice.
			intRollDieCounter +=1 ; // Counter of the number of times the dice have been rolled goes up by 1.
			btnOneDollar.setEnabled(false);// Disables the button.
			btnFiveDollar.setEnabled(false);// Disables the button.
			btnTwentyFiveDollar.setEnabled(false);// Disables the button.
			btnFiftyDollar.setEnabled(false);// Disables the button.
			btnOneHundredDollar.setEnabled(false);// Disables the button.
			if (intRollDieCounter == 1) // If the counter is equal to one.
			{
				   if (intPlayerNumber == 7 || intPlayerNumber == 11 ) // If the sum of the two dice is either seven or eleven.
				   {
					   intNumberOfTimesWon +=1; // The counter of number of wins increases by 1.
					   strNumberOfTimesWon= String.valueOf(intNumberOfTimesWon);// Integer is converted to string.
					   lblWins.setText("Wins:  " + strNumberOfTimesWon);// Sets the text of the label.
					   strMessage = ("You Have Won!"); // Assigns the string in quotes to the variable.
					   intBank += 2 * intBet; // The winnings are added on to the bank.
					   strBank= String.valueOf(intBank);// Integer is converted to string.
					   lblBank.setText("Bank:  " + strBank + " $");// Sets the text of the label.
					  lblMessage.setText(strMessage);
					  lblMessage.setVisible(true);
					  lblPointNumber.setVisible(false);
							 
				   }
				   else if (intPlayerNumber == 2 || intPlayerNumber == 3 || intPlayerNumber == 12) // If the sum of the two dice is two, three, or twelve.
				   {
					   intNumberOfTimesLost +=1; // The counter of number of loses increases by 1.
					   strNumberOfTimesLost= String.valueOf(intNumberOfTimesLost);// Integer is converted to string.
					   lblLoses.setText("Losses:  " + strNumberOfTimesLost);// Sets the text of the label.
					   strMessage = ("You Have Lost");// Assigns the string in quotes to the variable.
					  lblMessage.setText(strMessage);
					  lblMessage.setVisible(true);
					  lblPointNumber.setVisible(false);
							   
				   }
				   else // Anything else.
				   {
					   intPointNumber1 = intPlayerNumber; // A point number is established. 
					   String strPointNumber1= String.valueOf(intPointNumber1);// Integer is converted to string.
					   lblPointNumber.setText("Point Number:  " + strPointNumber1);// Sets the text of the label.
				   }
			  }
			  if (intRollDieCounter > 1) // If the dice have been rolled more then once.
			  {
				   if (intPointNumber1 == intPlayerNumber) // If the sum of the two dice is the same as the point number.
				   {
					   strMessage = ("You Have Won!"); // The string in quotes is assigned to the variable.
					  lblMessage.setText(strMessage);
					  lblMessage.setVisible(true);
					  lblPointNumber.setVisible(false);
					   intNumberOfTimesWon +=1; // The counter of number of wins increases by 1. 
					   strNumberOfTimesWon= String.valueOf(intNumberOfTimesWon); // Integer is converted to string.
					   lblWins.setText("Wins:  " + strNumberOfTimesWon);// Sets the text of the label.
					   intBank += 2 * intBet; // Updates the bank with the winnings.
					   strBank= String.valueOf(intBank);// Integer is converted to string.
					   lblBank.setText("Bank:  " + strBank + " $");// Sets the text of the label.
				   }
				   else if (intPlayerNumber == 7) // If the sum of the two numbers is equal to 7.
				   {
					   strMessage = ("You Have Lost");  // The string in quotes is assigned to the variable.
					   lblMessage.setText(strMessage);
					   lblMessage.setVisible(true);
					   lblPointNumber.setVisible(false);
					   intNumberOfTimesLost +=1;// The counter of number of loses increases by 1.
					   strNumberOfTimesLost= String.valueOf(intNumberOfTimesLost);// Integer is converted to string.
					   lblLoses.setText("Losses:  " + strNumberOfTimesLost);// Sets the text of the label.
				   }
			   }
			   		if (strMessage == ("You Have Won!") || strMessage == ("You Have Lost")) // If the variable contains either one of the two statements.
			   		{
			   			intRollDieCounter = 0; // Variable is reset.
			   			intBet = 0; // Variable is reset.
			   			intPointNumber1 = 0;// Variable is reset.
			   			strMessage = "";// Variable is reset.
			   		 lblPointNumber.setVisible(false);
			   			btnOneDollar.setEnabled(true); // Button is enabled.
			   			btnFiveDollar.setEnabled(true);// Button is enabled.
			   			btnTwentyFiveDollar.setEnabled(true);// Button is enabled.
			   			btnFiftyDollar.setEnabled(true);// Button is enabled.
			   			btnOneHundredDollar.setEnabled(true);// Button is enabled.
			   			lblBet.setText("Bet:");// Sets the text of the label.
			   			btnRollDie.setEnabled(false);// Button is disabled.
			   			lblPointNumber.setText("Point Number:");// Sets the text of the label.
			   			intNumberOfTimesPlayed +=1; // The counter for number of times played increases by one.
			   			strNumberOfTimesPlayed= String.valueOf(intNumberOfTimesPlayed); // Integer is converted to string.
			   			if (intBank == 0)
						{
							btnOneDollar.setEnabled(false); // Disables the button.
							btnFiveDollar.setEnabled(false);// Disables the button.
							btnTwentyFiveDollar.setEnabled(false);// Disables the button.
							btnFiftyDollar.setEnabled(false);// Disables the button.
							btnOneHundredDollar.setEnabled(false);// Disables the button.
						}
			   		}		
	}
	
}  
