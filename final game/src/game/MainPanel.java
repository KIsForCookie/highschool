

package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ActionListener{
	//declarations
	JLabel titlename;
	JButton start;
	JButton quit;
	JButton next;
	JLabel nameprompt;
	JLabel genderprompt;
	JTextArea input;
	int step;
	JLabel story, story1, story2,story3,background;

	public MainPanel(){
		//instantiations
		setLayout(null);
		setBackground(Color.BLACK);
		story = new JLabel("You managed to rack up 1 million dollars in debt.");
		story1 = new JLabel("You managed to find your last $1000.");
		story2 = new JLabel("It's time to go the Casino.");
		story3 = new JLabel("Go big or lose everything.");
		background = new JLabel(new ImageIcon("res/background.jpg"));
		nameprompt = new JLabel("What is your name? (9 character limit)");
		genderprompt = new JLabel("What is your gender?[m/f]");
		input = new JTextArea();
		next = new JButton("NEXT");
		titlename = new JLabel("Casino game");
		start = new JButton("START");
		quit = new JButton("QUIT");
		step = 0;
		
		//adds compontents to the panel
		add(quit);
		add(titlename);
		add(start);
		add(next);
		add(genderprompt);
		add(nameprompt);
		add(story);
		add(story1);
		add(story2);
		add(story3);
		add(input);
		add(background);
		
		//visibility settings
		story.setVisible(false);
		story1.setVisible(false);
		story2.setVisible(false);
		story3.setVisible(false);
		next.setVisible(false);
		genderprompt.setVisible(false);
		nameprompt.setVisible(false);
		input.setVisible(false);
		//font settings
		story.setFont(getFont().deriveFont((float)25));
		story1.setFont(getFont().deriveFont((float)25));
		story2.setFont(getFont().deriveFont((float)25));
		story3.setFont(getFont().deriveFont((float)25));
		titlename.setFont(getFont().deriveFont((float)150));
		genderprompt.setFont(getFont().deriveFont((float)25));
		nameprompt.setFont(getFont().deriveFont((float)25));
		titlename.setForeground(Color.WHITE); //foreground settings
		genderprompt.setForeground(Color.WHITE);
		nameprompt.setForeground(Color.WHITE);
		story.setForeground(Color.WHITE);
		story1.setForeground(Color.WHITE);
		story2.setForeground(Color.WHITE);
		story3.setForeground(Color.WHITE);
		Dimension size = new Dimension();
		size = titlename.getPreferredSize();
		quit.addActionListener(this);
		story.setBounds(25, 200, 600, 50);//setting the bounds of the compontents
		story1.setBounds(25, 300, 500, 50);
		story2.setBounds(25, 400, 500, 50);
		story3.setBounds(25, 500, 500, 50);
		start.setBounds(300,600,150,50);
		nameprompt.setBounds(150,300,450,75);
		genderprompt.setBounds(250,300,300,75);
		input.setBounds(250,475,150,20);
		quit.setBounds(500,600,150,50);
		next.setBounds(500,500,75,25);
		background.setBounds(0,0,1024,768);
		titlename.setBounds(75,0,(int)size.getWidth(),(int)size.getHeight());
	}
	
	public void reset(){//reset methods for replability purposes
		next.setVisible(false);
		genderprompt.setVisible(false);
		nameprompt.setVisible(false);
		input.setVisible(false);
		titlename.setVisible(true);
		start.setVisible(true);
		quit.setVisible(true);
		input.setText("");
		story.setVisible(false);
		story1.setVisible(false);
		story2.setVisible(false);
		story3.setVisible(false);
		background.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == quit){ //if quit button is pressed, quits the game
			System.exit(0);
		}
		}
		
	}
	

