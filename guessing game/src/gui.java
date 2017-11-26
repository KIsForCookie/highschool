import javax.swing.*;
import java.lang.NumberFormatException;
import java.awt.*;
import java.awt.event.*;

public class gui extends JFrame implements ActionListener{
	
	
	//declares private primitives
	private int player1turn;
	private int player2turn;
	private int number;
	private int pass;
	private int player1win;
	private int player2win;
	private int player1choice;
	private int player2choice;
	//declares private objects
	private Die die;
	
	private Player player1;
	private Player player2;
	
	private JTextField txtplayer1name;
	private JTextField txtplayer2name;
	private JTextField txtplayer1guess;
	private JTextField txtplayer2guess;
	
	private JLabel lblplayer1name;
	private JLabel lblplayer2name;
	private JLabel lblplayer1guess;
	private JLabel lblplayer2guess;
	
	private JButton btndone;
	private JButton btnguess1;
	private JButton btnguess2;
	
	
	public gui(){//core code
		
		//initializes objects
		player1 = new Player();
		player2 = new Player();
		die = new Die();
		
		setTitle("Guessing game");//sets title
		setSize(400,300); //sets size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //causes program to close when x button is clicked
		setResizable(false); //cannot resize window
		setLayout(null); //disabled default layout
		number = die.roll(100); // number is a random number from 1-100
		
		//initializes JTextField objects
		txtplayer1guess = new JTextField("guess!");
		txtplayer2guess = new JTextField("guess!");
		txtplayer1name = new JTextField("Enter name");
		txtplayer2name = new JTextField("Enter name");
		
		//initalizes JButton objects
		btndone = new JButton("Done!");
		btnguess1 = new JButton("Guess!");
		btnguess2 = new JButton("Guess!");
		
		//initalizes JLabel objects
		lblplayer1name = new JLabel("Player 1");
		lblplayer2name = new JLabel("Player 2");
		lblplayer1guess = new JLabel("0");
		lblplayer2guess = new JLabel("0");
		
		
		//sets the location of all the gui objects on the window
		lblplayer1name.setBounds(100,50,50,20);
		lblplayer2name.setBounds(100,80,50,20);
		lblplayer1guess.setBounds(75,100,100,20);
		lblplayer2guess.setBounds(75,100,100,20);
		txtplayer1name.setBounds(150,50,150,20);
		txtplayer2name.setBounds(150,80,150,20);
		txtplayer1guess.setBounds(75,80,250,20);
		txtplayer2guess.setBounds(75,80,250,20);
		btndone.setBounds(150,120,75,40);
		btnguess1.setBounds(150,120,75,40);
		btnguess2.setBounds(150,120,75,40);
		
		
		//makes the following objects invisible
		txtplayer1guess.setVisible(false);
		txtplayer2guess.setVisible(false);
		lblplayer1guess.setVisible(false);
		lblplayer2guess.setVisible(false);
		btnguess1.setVisible(false);
		btnguess2.setVisible(false);
		
		
		//adds action listener to the buttons
		btndone.addActionListener(this);
		btnguess1.addActionListener(this);
		btnguess2.addActionListener(this);
		
		//adds all the gui objects to the window
		add(txtplayer1guess);
		add(txtplayer2guess);
		add(lblplayer2name);
		add(txtplayer2name);
		add(lblplayer1guess);
		add(lblplayer2guess);
		add(txtplayer1name);
		add(lblplayer1name);
		add(btndone);
		add(btnguess1);
		add(btnguess2);
		
	}
	
	
	public void actionPerformed(ActionEvent e){ // waits for actions performed
		if (e.getSource() == btndone){ // if source is from btndone
			player1.setName(txtplayer1name.getText()); //sets player1 name
			player2.setName(txtplayer2name.getText());//sets player2 name
			//makes the following objects invisible
			btndone.setVisible(false);
			txtplayer1name.setVisible(false);
			txtplayer2name.setVisible(false);
			lblplayer2name.setVisible(false);
			//makes the following objects visible
			txtplayer1guess.setVisible(true);
			btnguess1.setVisible(true);
			
			lblplayer1name.setText(player1.getName() + ", guess a number between 1 and 100");//changes the text of lblplayer1name
			lblplayer2name.setText(player2.getName() + ", guess a number between 1 and 100");//changes the text of player2name
			Dimension player1size = lblplayer1name.getPreferredSize();//finds best size for lblplayer1name
			Dimension player2size = lblplayer2name.getPreferredSize();//finds best size for lblplayer2name
			lblplayer1name.setBounds(75,50,player1size.width,player2size.height);//places lblplayer1name with optimal sizes
			lblplayer2name.setBounds(75,50,player2size.width,player2size.height);//places lblplayer2name with optimal sizes
			
		}
		
		if(e.getSource() == btnguess1){ //if source is from btnguess1
			try{//try catch block for numberformatexception error
			player1choice = Integer.parseInt(txtplayer1guess.getText());//makes player1choice their guess from the textbox
			}catch(NumberFormatException Error){
				txtplayer1guess.setText("Enter a number!");//if numberformatexception error, this line excecutes 
			}
			if((player1choice > 100)||(player1choice < 0)){//checks to see if player's guess is allowed
				txtplayer1guess.setText("You cannot guess this number! Try again."); //if not allowed
			}else{//if allowed
				lblplayer1guess.setVisible(true);//makes lblplayer1guess visible
				if(player1choice > number){//if player's guess is higher then random number
					lblplayer1guess.setText("Too high!");//tells player his guess is too high
					player1.setScore(player1.getScore() + 1);//adds plus 1 score to player1
				}else if(player1choice < number){//if player's choice is less then random number
					lblplayer1guess.setText("Too low!");//tells player his guess is too low
					player1.setScore(player1.getScore() + 1);//increases score by 1
				}else if (player1choice == number){//if player's choice is the same as random number
					player1.setScore(player1.getScore() + 1);//increases score by 1
					JOptionPane.showMessageDialog(null, "Correct!");//msgbox telling player that he is correct
					number = die.roll(100);//finds a new random number
					//makes the following objects invisible
					txtplayer1guess.setVisible(false);
					lblplayer1guess.setVisible(false);
					btnguess1.setVisible(false);
					lblplayer1name.setVisible(false);
					lblplayer2name.setVisible(true);
					//makes the following objects visible
					btnguess2.setVisible(true);
					txtplayer2guess.setVisible(true);
					
				}
			}
				
			}
		
		
		
		if(e.getSource() == btnguess2){//if source comes from btnguess2
			try{//try catch block to catch numberformatexception error
				player2choice = Integer.parseInt(txtplayer2guess.getText());//makes player2choice their guess from the textbox
				}catch(NumberFormatException Error){
					txtplayer2guess.setText("Enter a number!");
				}
			if((player2choice > 100)||(player2choice < 0)){//checks to see if player's guess is out of bounds
				txtplayer2guess.setText("You cannot guess this number! Try again.");
			}else{
				lblplayer2guess.setVisible(true);//sets lblplayer2guess visible
				if(player2choice > number){//if player's guess is higher then random number
					lblplayer2guess.setText("Too high!");
					player2.setScore(player2.getScore() + 1);
				}else if(player2choice < number){//if player's guess is lower then random number
					lblplayer2guess.setText("Too low!");
					player2.setScore(player2.getScore() + 1);
				}else if (player2choice == number){//if player's guess is same as random number
					JOptionPane.showMessageDialog(null, "Correct!");
					if(player1.getScore() < player2.getScore()){//if player1's score is less then player2's score
						JOptionPane.showMessageDialog(null,player1.getName() + " wins the round!");//msgbox saying player1 wins round
						player1.plusWin();//adds a win to player1
					}else if (player2.getScore() < player1.getScore()){//if player2's score is less then player1's score
						JOptionPane.showMessageDialog(null,player2.getName() + " wins the round!");//msgbox saying player2 wins round
						player2.plusWin();//adds a win to player2
					}
					
					if(player1.getWin() == 5){//if player1's wins are 5
						JOptionPane.showMessageDialog(null,player1.getName() + " wins the game!");//msgbox saying player1 wings the game
						System.exit(0);//exits program
					}else if(player2.getWin() == 5){//if player2's wins are 5
						JOptionPane.showMessageDialog(null,player2.getName() + " wins the game!");//msgbox saying player2 wins the game
						System.exit(0);//exits program
					}else{
						number = die.roll(100);//random number from 1-100
						//sets following objects visible
						txtplayer1guess.setVisible(true);
						lblplayer1guess.setVisible(true);
						btnguess1.setVisible(true);
						lblplayer1name.setVisible(true);
						//makes following objects invisible
						lblplayer2name.setVisible(false);
						btnguess2.setVisible(false);
						lblplayer2guess.setVisible(false);
						txtplayer2guess.setVisible(false);
					}
				}
			}
		}
		}
		
	
	
	
	}
	
	
