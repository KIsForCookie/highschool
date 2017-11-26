

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class SlotPanel extends JPanel {

 Timer timer;
 int random1;
 int random2;
 int random3;
 int coinCount;
 int betamount;
 int doubles;
 int triples;
 int numberOfPulls;
 
 BufferedImage Background;
 
 JButton pull;
 JButton bet1;
 JButton bet5;
 JButton bet10;
 JButton leave;

 JLabel instruction;
 JLabel Nomoney;
 JLabel number1;
 JLabel number2;
 JLabel number3;
 JLabel numberOfDoubles;
 JLabel numberOfTriples;
 JLabel numberOfPullsLabel;
 JLabel coin;
 ImageIcon img1;

public SlotPanel () {

	setLayout(null);
Nomoney = new JLabel("Sorry, you don't have enough to bet");
coin = new JLabel ("You have " + coinCount + " Dollars left to play with ");
coin.setForeground(Color.YELLOW);
numberOfDoubles = new JLabel ("Number of doubles:" + doubles);
numberOfDoubles.setForeground(Color.GREEN);
numberOfTriples = new JLabel ("Number of triples:" + triples);
numberOfTriples.setForeground(Color.ORANGE);
numberOfPullsLabel = new JLabel ("You have pulled the lever " + numberOfPulls + " times.");
numberOfPullsLabel.setForeground(Color.WHITE);
//this line of code adds color and changes the font size 

leave = new JButton("Leave");
timer = new Timer(5,new ActionListener(){
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		coin.setText ("You have " +coinCount + " Dollars left to play with");
		numberOfPullsLabel.setText("You have pulled the lever " + numberOfPulls + " times.");
		numberOfTriples.setText("Number of triples:" + triples);
		numberOfDoubles.setText("Number of doubles:" + doubles);
	}
});

timer.start();
coinCount = 0;
betamount = 1;
random1 = 0;
random2 = 0;
random3 = 0;
doubles = 0;
triples = 0;

try {
	Background = ImageIO.read(new File("res/slotbackground.png"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

bet1 = new JButton("Bet 1");
bet5= new JButton("Bet 5");
bet10= new JButton("Bet 10");

instruction = new JLabel ("Bet first and then press pull lever");
instruction.setForeground(Color.BLUE);

Nomoney = new JLabel ("Sorry, you don't have enough to bet");
Nomoney.setFont (new Font ("Comic Sans", Font.BOLD, 14));
Nomoney.setForeground(Color.RED);
pull = new JButton ("Pull lever");
pull.setEnabled(false);
pull.setForeground(Color.RED);
pull.addActionListener (new ButtonListener());

number1 = new JLabel ("" + random1);
number1.setFont (new Font ("Comic Sans", Font.BOLD, 40));
number1.setForeground(Color.RED);
number2 = new JLabel ("" + random2);
number2.setFont (new Font ("Comic Sans", Font.BOLD, 40));
number2.setForeground(Color.BLUE);
number3 = new JLabel ("" + random3);
number3.setFont (new Font ("Comic Sans", Font.BOLD, 40));
number3.setForeground(Color.GREEN);


add (instruction);
add (coin);
add (pull);
add (bet1);
add (bet5);
add (bet10);
add (Nomoney);
add (number1);
add (number2);
add (number3);
add (numberOfDoubles);
add (numberOfTriples);
add (numberOfPullsLabel);
add(leave);
//this line of code adds the variables 

bet1.addActionListener (new ButtonListener());
bet1.setFont(getFont().deriveFont((float)15));
bet1.setBounds(75,468,200,50);

leave.setFont(getFont().deriveFont((float)15));
leave.setBounds(800,600,200,50);

bet5.addActionListener (new ButtonListener());
bet5.setFont(getFont().deriveFont((float)15));
bet5.setBounds(75,520,200,50);

bet10.addActionListener (new ButtonListener());
bet10.setFont(getFont().deriveFont((float)15));
bet10.setBounds(75,572,200,50);

instruction.setFont(getFont().deriveFont((float) 28));
instruction.setBounds(288,214,500,50);

coin.setFont(getFont().deriveFont((float) 20));
coin.setBounds(4,154,400,40);

pull.setFont(getFont().deriveFont((float)30));
pull.setBounds(394,465,200,50);

Nomoney.setFont(getFont().deriveFont((float) 22));
Nomoney.setBounds(324,544,500,50);
Nomoney.setVisible(false);

number1.setFont(getFont().deriveFont((float) 125));
number1.setBounds(334,298,125,125);

number2.setFont(getFont().deriveFont((float) 125));
number2.setBounds(458,298,125,125);

number3.setFont(getFont().deriveFont((float) 125));
number3.setBounds(576,298,125,125);

numberOfDoubles.setFont(getFont().deriveFont((float) 20));
numberOfDoubles.setBounds(750,125,400,50);

numberOfTriples.setFont(getFont().deriveFont((float) 20));
numberOfTriples.setBounds(750,150,400,50);

numberOfPullsLabel.setFont(getFont().deriveFont((float) 22));
numberOfPullsLabel.setBounds(345,145,400,50);
//this line of code sets the bounds of all the labels and buttons in the JFrame 
	}

public void paintComponent(Graphics g){
	Graphics2D g2d = (Graphics2D)g.create();
	g2d.drawImage(Background,0,0,1024,768,null);
//this line of code sets the size of the background in the JFrame
}

public void reset(){
	betamount = 1;
	random1 = 0;
	random2 = 0;
	random3 = 0;
	doubles = 0;
	triples = 0;
	pull.setEnabled(false);
	bet1.setEnabled(true);
	bet5.setEnabled(true);
	bet10.setEnabled(true);
	doubles = 0;
	triples = 0;
	numberOfPulls = 0;
}

public class ButtonListener implements ActionListener {
public void actionPerformed (ActionEvent event){
	
	if(event.getSource() == bet1){
		betamount = 1;
		pull.setEnabled(true);
	}else if(event.getSource() == bet5){
		betamount = 5;
		pull.setEnabled(true);
	}else if(event.getSource() == bet10){
		betamount = 10;
		pull.setEnabled(true);
//this line of code sets the action to the buttons 
	}
	
	if(event.getSource() == pull){
		if(betamount > coinCount){
			Nomoney.setVisible(true);
			
		}else{
			Nomoney.setVisible(false);
			Random generator = new Random();
			pull.setEnabled(false);

			random1 = generator.nextInt(9) +1;
			random2 = generator.nextInt(9) +1;
			random3 = generator.nextInt(9) +1;
//this line of code is the math for the numbers being generated 

			number1.setText ("" + random1);
			number2.setText ("" + random2);
			number3.setText ("" + random3);

			if (coinCount >=betamount){
				coinCount -= betamount;
			if (random1==random2 || random2==random3){
				doubles++;
				coinCount += betamount * 3;
			if (random1==random3 || random3==random1){
				triples++;
				coinCount += betamount * 95;
//this line of code is the math for betting the money 
			}
			}
			numberOfPulls++;
			} else {

			number1.setText ("");
			number2.setText ("");
			number3.setText ("");
			}
			
			numberOfDoubles.setText ("Number of doubles:" + doubles);
			numberOfTriples.setText ("Number of triples:" + triples);
			numberOfPullsLabel.setText ("You have pulled the lever " + numberOfPulls + " times.");
//this line of code is to show how much money you have used, how many doubles and triples the user got and how many times the lever is pulled 
		}
	}
}
}
}
