// imports statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class gui  { //the public class gui that has properties of JFrame and can use ActionListener
	
	
	
// variable declarations	
private Random gen = new Random();
private int dice1 = 0;
private int dice2 = 0;
private int total = 0;
private double bank = 500;
private double bet = 0;
private int pointNumber = 0;
private int win = 0;
private int lose = 0;
private int bankrupt = 0;
private final double multiplier = 2;
private int firstroll = 0;
private int choice;	
private int pass = 0;
	
//more variable declarations	
private JButton btndollar;
private JButton btn5dollar;
private JButton btn10dollar;
private JButton btn20dollar;
private JButton btnroll;

//even more variable declarations
private JLabel lblbank;
private JLabel lblbet;
private JLabel lbldie1;
private JLabel lbldie2;
private JLabel lblbetS;
private JLabel lblbankS;
	
	public gui(){ //core code
	setTitle("the craps table");//sets title
	setSize(500,400); //sets size
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //causes program to close when x button is clicked
	setResizable(false); //cannot resize window
	setLayout(null); //disabled default layout
	
	
	btndollar = new JButton("Bet $1"); //creates a JButton called btndollar
	btn5dollar = new JButton("Bet $5"); //creates a JButton called btn5dollar
	btn10dollar = new JButton("Bet $10"); //creates a JButton called btn10dollar
	btn20dollar = new JButton("Bet $20"); //creates a JButton called btn20dollar
	btnroll = new JButton("Roll"); //creates a JButton called btnroll
	lblbank = new JLabel(Double.toString(bank)); //creates a JLabel called lblbank
	lblbet = new JLabel(Double.toString(bet));// creates a Jabel called lblbet
	lbldie1 = new JLabel(Integer.toString(dice1)); //creates a JLabel called lbldie1
	lbldie2 = new JLabel(Integer.toString(dice2)); //creates a JLabel called lbldie2
	lblbetS = new JLabel("How much would you like to bet?: $");//creates a JLabel called lblbetS
	lblbankS = new JLabel("Your bank: $");//creates a JLabel called lblbankS
	Dimension betsize = lblbet.getPreferredSize(); //gives betsize to the best dimensions for lblbet
	Dimension banksize = lblbank.getPreferredSize();//gives banksize to the best dimensions for lblbank
	
	lbldie1.setFont(lbldie1.getFont().deriveFont((float)50)); //gets the font of lbldie1 and changes the font size to 50
	lbldie2.setFont(lbldie2.getFont().deriveFont((float)50)); //sets the font of lbldie2 and changes the font size to 50
	btndollar.addActionListener(this); //adds an action listener to btndollar
	btn5dollar.addActionListener(this); //adds an actionlistener to btn5dollar
	btn10dollar.addActionListener(this); //adds an actionlistener to btn10dollar
	btn20dollar.addActionListener(this);//adds an actionlistener to btn20dollar
	btnroll.addActionListener(this);//adds an actionlistener to btnroll
	
	add(btndollar); //shows btndollar onto the window
	add(btn5dollar);//shows btn5dollar onto the window
	add(btn10dollar);//shows btn10dollar onto the window
	add(btn20dollar);//shows btn20dollar onto the window
	add(btnroll);//shows btnroll onto the window
	add(lblbank);//shows lblbank onto the window
	add(lblbet);//shows lblbet onto the window
	add(lbldie1);//shows lbldie1 onto the window
	add(lbldie2);//shows lbldie2 onto the window
	add(lblbetS);//shows lblbetS onto the window
	add(lblbankS);//shows lblbankS onto the window
	
	
	lblbetS.setBounds(15,15,200,25); //places lblbetS 15 pixels in, 15 pixels,down, with a size of 200X25 
	lblbet.setBounds(215,19,betsize.width,betsize.height); //places lblbetS 215 pixels in, 19 pixels down, with the best size
	btndollar.setBounds(15,40,80,20);//places btndollar
	btn5dollar.setBounds(15,70,80,20);//places btn5dollar
	btn10dollar.setBounds(15,100,80,20);//placesbtn10dollar
	btn20dollar.setBounds(15,130,80,20);//places btn20dollar
	lblbankS.setBounds(15,150,75,25);//places lblbankS
	lblbank.setBounds(89,154,banksize.width,banksize.height);//places lblbank
	lbldie1.setBounds(150,150,200,200);//places lbldie1
	lbldie2.setBounds(250,150,200,200);//places lbldie2
	btnroll.setBounds(140,300,150,20);//places btnroll
	btnroll.setEnabled(false);//btnroll is not enabled

	}


	public void actionPerformed(ActionEvent e) {//action performed class
		
		if(e.getSource() == btndollar){//if action comes from btndollar
			if(bank>= 1){ // if bank >= 1
			bank = bank - 1; //bank = bank - 1
			lblbank.setText(Double.toString(bank)); //sets the text of lblbank to the string version of the double bank
			Dimension banksize = lblbank.getPreferredSize(); //gets the best size for lblbank
			lblbank.setBounds(89,154,banksize.width,banksize.height);//sets the size for lblbank
			bet = bet + 1; //bet = bet + 1
			lblbet.setText(Double.toString(bet)); //sets the text of lblbet to the string version of the double bet
			Dimension betsize = lblbet.getPreferredSize();//gets the best size for lblbet
			lblbet.setBounds(215, 19,betsize.width,betsize.height);//sets the size for lblbet
			btnroll.setEnabled(true);//makes btnroll enabled
			}
		}else if(e.getSource() == btn5dollar){//same if statement, except for 5 dollars and different source
			if(bank>= 5){
				bank = bank - 5;
				lblbank.setText(Double.toString(bank)); 
				Dimension banksize = lblbank.getPreferredSize();
				lblbank.setBounds(89,154,banksize.width,banksize.height);
				bet = bet + 5;
				lblbet.setText(Double.toString(bet));
				Dimension betsize = lblbet.getPreferredSize();
				lblbet.setBounds(215, 19,betsize.width,betsize.height);
				btnroll.setEnabled(true);
				}
		}else if(e.getSource() == btn10dollar){//same if statement, except for 10 dollars and different source
			if(bank>= 10){
				bank = bank - 10;
				lblbank.setText(Double.toString(bank));
				Dimension banksize = lblbank.getPreferredSize();
				lblbank.setBounds(89,154,banksize.width,banksize.height);
				bet = bet + 10;
				lblbet.setText(Double.toString(bet));
				Dimension betsize = lblbet.getPreferredSize();
				lblbet.setBounds(215, 19,betsize.width,betsize.height);
				btnroll.setEnabled(true);
				}
		}else if(e.getSource() == btn20dollar){//same if statement, except for 20 dollars and different source
			if(bank>= 20){
				bank = bank - 20;
				lblbank.setText(Double.toString(bank));
				Dimension banksize = lblbank.getPreferredSize();
				lblbank.setBounds(89,154,banksize.width,banksize.height);
				bet = bet + 20;
				lblbet.setText(Double.toString(bet));
				Dimension betsize = lblbet.getPreferredSize();
				lblbet.setBounds(215, 19,betsize.width,betsize.height);
				btnroll.setEnabled(true);
				}
		}
		
		if(e.getSource() == btnroll){//if action comes from btnroll
			btndollar.setEnabled(false);//btndollar is not enabled
			btn5dollar.setEnabled(false);//btn5dollar is not enabled
			btn10dollar.setEnabled(false);//btn10dollar is not enabled
			btn20dollar.setEnabled(false);//btn20dollar is not enabled
			dice1 = gen.nextInt(6)+1;//dice1 is random number from 1-6
			lbldie1.setText(Integer.toString(dice1));//sets the text of lbldie1 to dice1
			dice2 = gen.nextInt(6)+1;//dice2 is random number from 1-6
			lbldie2.setText(Integer.toString(dice2));//sets the text of lbldie2 to dice2
			total = dice1 + dice2;//total = dice1+ dice2
			
			if(firstroll == 0){//if firstroll = 0
			if ((total == 7)||(total == 11)){// if total = 7 or 11
				win = win + 1;//win = win + 1
				bet = bet * multiplier;//multiplies bet by 2
				bank = bank + bet;//bank = bank + bet
				JOptionPane.showMessageDialog(null, "You win! $" + bet + "!");//messagebox saying that you win
				lblbank.setText(Double.toString(bank));//sets the text of lblbank
				Dimension banksize = lblbank.getPreferredSize();//gets the best size for lblbank
				lblbank.setBounds(89,154,banksize.width,banksize.height);//sets the size for lblbank
				bet = 0;//bet = 0
				lblbet.setText(Double.toString(bet));//sets the text of lblbet
				btnroll.setEnabled(false);//btnroll is unenabled
				firstroll = 0;//firstroll = 0
				btndollar.setEnabled(true);//btndollar is enabled
				btn5dollar.setEnabled(true);//btn5dollar is enabled
				btn10dollar.setEnabled(true);//btn10dollar is enabled
				btn20dollar.setEnabled(true);//btn20dollar is enabled
			}else if((total == 2)||(total == 3)||(total == 12)){//else if total = 2,3, or 12
				lose = lose + 1;//list = lose + 1
				JOptionPane.showMessageDialog(null, "You lose!");//messagebox saying you lose
				bet = 0;//bet = 0
				lblbet.setText(Double.toString(bet));//sets text of lblbet
				btnroll.setEnabled(false);//btn roll is not enabled
				firstroll = 0;//firstroll is not enabled
				btndollar.setEnabled(true);//btndollar is enabled
				btn5dollar.setEnabled(true);//btn5dollar is enabled
				btn10dollar.setEnabled(true);//btn10dollar is enabled
				btn20dollar.setEnabled(true);//btn20dollar is enabled
			}else{//else
				pointNumber = total;//pointnumber = total
				firstroll = 1;//firstroll = 1
			}
			}else{//else
				if (total == 7){//if total = 7
					JOptionPane.showMessageDialog(null, "You lose!");//messagebox saying you lose
					lose = lose + 1;//lose = lose + 1;
					bet = 0; //bet = 0
					lblbet.setText(Double.toString(bet));//sets text of lblbet
					btnroll.setEnabled(false);//btnroll is not enabled
					firstroll = 0;//firstroll = 0
					btndollar.setEnabled(true);//btndollar is enabled
					btn5dollar.setEnabled(true);//btn5dollar is enabled
					btn10dollar.setEnabled(true);//btn10dollar is enabled
					btn20dollar.setEnabled(true);//btn20dollar is enabled
			}else if(total == pointNumber){//if total = point number
				win = win + 1;//win = win + 1
				bet = bet * multiplier;//bet = bet * 2
				bank = bank + bet;//bank = bank + bet
				JOptionPane.showMessageDialog(null, "You win! $" + bet + "!");//message box saying you win
				lblbank.setText(Double.toString(bank));//sets the text of lblbank
				Dimension banksize = lblbank.getPreferredSize();//gets the best size for lblbank
				lblbank.setBounds(89,154,banksize.width,banksize.height);//sets the size of lblbank
				bet = 0;//bet = 0
				lblbet.setText(Double.toString(bet));//sets the text of lblbet
				btnroll.setEnabled(false);//btnroll is not enabled
				firstroll = 0;//firstrroll = 0
				btndollar.setEnabled(true);//btndollar is enabled
				btn5dollar.setEnabled(true);//btn5dollar is enabled
				btn10dollar.setEnabled(true);//btn10dollar is enabled
				btn20dollar.setEnabled(true);//btn20dollar is enabled
		}
		
			}
	}
		
	if((bank == 0)&&(bet==0)){//if bank = 0 and bet = 0
		do{//loop
		bankrupt = bankrupt + 1;//bankrupt = bankrupt + 1
		choice = JOptionPane.showConfirmDialog(null,"You are bankrupt! Would you like to go to the bank to withdraw $500?","Bankruptcy!!!", JOptionPane.YES_NO_OPTION);//message box with a yes/no option
		if(choice == 1){//if choose no
			JOptionPane.showMessageDialog(null,"Thank you, come agian");//message box says thank you
			pass = 1;//allowed to exit loop
			try{//try/catch block
			PrintWriter printer = new PrintWriter(new FileWriter("score.txt"));//instantiates a new printwriter object called printer
			printer.println("In this session of craps, you played " + (win + lose)+" games, won " + win +" times, lost "+lose+" times, and went bankrupt "+ bankrupt+" times."); //prints text to the output file score.txt
			printer.close();//ends the printer.
		}catch(IOException error){}//catches ioexception errors
			System.exit(0);//closes program
		}else if(choice == 0){//if chooses yes
			bank = 500; //bank = 500
			lblbank.setText(Double.toString(bank));//sets text of lblbank
			Dimension banksize = lblbank.getPreferredSize();//gets the best size for lblbank
			lblbank.setBounds(89,154,banksize.width,banksize.height);//sets the size of lblbank
			pass = 1;//allowed to exit loop
		}else{//else
			pass = 0;//not allowed to exit loop
		}
		}while(pass == 0);//loops while pass = 0
	}
}
	}
	

