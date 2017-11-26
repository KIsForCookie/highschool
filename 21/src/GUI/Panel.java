package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.Random;


public class Panel extends JPanel implements ActionListener{
	int money;
	int wager;
	Timer timer;
	Timer chipsTimer;
	JButton bet;
	JButton hit;
	JTextArea amount;
	JButton stay;
	JButton leave;
	JLabel background;
	JLabel chips;
	
	int hand[][] = new int[2][5];
	int handsplit[][] = new int[2][5];
	int aceCount[] = new int[2];
	int cardVal[] = new int[2];
	int deck[] = new int[52];
	int recycleCard = 0;
	int cardNumber = 0;
	int compcardnumber = 0;
	final int multiplier = 2;
	int num = 0;
	final int deckNumber = 8;
	int pass = 0;
	int lose = 0;
	int chipsMoveVelocity = 5;
	int chipsTargetY = 0;
	int chipsLowY = 500;
	int chipsUpY = 20;
	int chipsMiddleY = 300;
	
	BufferedImage table;
	
	Card card = new Card();
	Random gen = new Random();
	Value value = new Value();
	ComputerTurn computer = new ComputerTurn();
	
	public Panel(){
		
		/*try{
			table = ImageIO.read(new File("res/table.jpg"));
		}catch(Exception error){
			
		}*/
		money = 500;
		setLayout(null);
		bet = new JButton("Bet");
		hit = new JButton("Hit");
		amount = new JTextArea();
		leave = new JButton("Leave table");
		stay = new JButton("Stay");
		timer = new Timer(50,this);
		chipsTimer = new Timer(10, this);
		background = new JLabel(new ImageIcon("res/table.jpg"));
		chips = new JLabel(new ImageIcon("res/red.png"));
		bet.setBounds(25,675,100,50);
		amount.setBounds(25,650,100,20);
		hit.setBounds(150,675,100,50);
		leave.setBounds(850,675,150,50);
		stay.setBounds(275,675,100,50);
		background.setBounds(0,0,1024,768);
		chips.setBounds(512,584,120,120);
		timer.start();
		add(bet);
		add(amount);
		add(stay);
		add(hit);
		add(leave);
		add(chips);
		add(background);
		hit.setEnabled(false);
		stay.setEnabled(false);
		bet.addActionListener(this);
		stay.addActionListener(this);
		hit.addActionListener(this);
		leave.addActionListener(this);
		
		for(int i = 0; i < 52; i++){
			deck[i] = deckNumber;
		}
		
		for(int i = 0; i < 2;i ++){
			for(int j = 0 ; j < 5; j ++){
				hand[i][j] = 0;
				handsplit[i][j] = 0;
				aceCount[i] = 0;
				cardVal[i] = 0;
				cardNumber = 0;
				recycleCard = 0;
			}
		}
		
		if(recycleCard < 20){
			for(int i = 0; i < 52; i++){
				deck[i] = deckNumber;
			}
		}
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 5; j++){
				do{
				num = gen.nextInt(52);
				deck[num] = deck[num] - 1; 
				if(deck[num] < 0){
					deck[num] = deck[num] + 1;
					pass = 0;
				}else{
					hand[i][j] = num;
					pass = 1;
				}
				}while(pass == 0);
				
			}
		}
		
	}
	
	public void reset(){
		for(int i = 0; i < 2;i ++){
			for(int j = 0 ; j < 5; j ++){
				aceCount[i] = 0;
				cardVal[i] = 0;
				recycleCard = 0;
			}
		}
		if(recycleCard < 20){
			for(int i = 0; i < 52; i++){
				deck[i] = deckNumber;
			}
		}

		wager = 0;
		hit.setEnabled(false);
		stay.setEnabled(false);
		bet.setEnabled(true);
		amount.setEnabled(true);
		amount.setText("");
	}
	
	public int checkValue(){
		int check = 0;
		if((cardVal[0] > cardVal[1])||(cardVal[1] > 21)){
			check = 2;
			wager = wager * 2;
			money += wager;
		}else if(cardVal[0] == cardVal[1]){
			check = 3;
			money += wager;
		}else if(cardVal[0] < cardVal[1]){
			check = 1;
		}
		return check;
	}




	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){
			repaint();
		}
		
		if(e.getSource() == chipsTimer){
			Rectangle rect = chips.getBounds();
			if((chipsMoveVelocity < 0 && rect.y < chipsTargetY) 
					|| (chipsMoveVelocity > 0 && rect.y > chipsTargetY)) {
				chipsTimer.stop();
			} else {
				rect.y += chipsMoveVelocity;
				chips.setBounds(rect);
			}
		}
		
		if(e.getSource() == leave){
			chipsTimer.start();
			//System.exit(1);
		}else if(e.getSource() == hit){
			chipsTimer.stop();
			if(cardNumber == 2){
				if ((hand[0][2] == 0)||
						(hand[0][2] ==13)||
						(hand[0][2] == 26)||
						(hand[0][2] == 39)){
						aceCount[0]++;
					}
				cardVal[0] += value.getValue(hand[0][2]);
				cardNumber++;
				
				if(cardVal[0] > 21){
					if(aceCount[0] == 0){
						lose = 1;
						reset();
				}else if(aceCount[0] > 0){
					aceCount[0]--;
					cardVal[0] -= 10;
					}
				}
				
			}else if(cardNumber == 3){
				if ((hand[0][3] == 0)||
						(hand[0][3] ==13)||
						(hand[0][3] == 26)||
						(hand[0][3] == 39)){
						aceCount[0]++;
					}
				cardVal[0] += value.getValue(hand[0][3]);
				cardNumber++;
				
				if(cardVal[0] > 21){
					if(aceCount[0] == 0){
						lose = 1;
						reset();
				}else if(aceCount[0] > 0){
					aceCount[0]--;
					cardVal[0] -= 10;
					}
				}
				
			}else if(cardNumber == 4){
				if ((hand[0][4] == 0)||
						(hand[0][4] ==13)||
						(hand[0][4] == 26)||
						(hand[0][4] == 39)){
						aceCount[0]++;
					}
				cardVal[0] += value.getValue(hand[0][4]);
				cardNumber++;
				if(cardVal[0] > 21){
					if(aceCount[0] == 0){
						lose = 1;
						reset();
				}else if(aceCount[0] > 0){
					aceCount[0]--;
					cardVal[0] -= 10;
					bet.setEnabled(false);
					computer.setHand(hand[1][0],hand[1][1],hand[1][2],hand[1][3],hand[1][4]);
					cardVal[1] = computer.playHand();
					compcardnumber = computer.getCardNumber();
					lose = checkValue();
					reset();
					}
				}else{
					bet.setEnabled(false);
					computer.setHand(hand[1][0],hand[1][1],hand[1][2],hand[1][3],hand[1][4]);
					cardVal[1] = computer.playHand();
					compcardnumber = computer.getCardNumber();
					lose = checkValue();
					reset();
				}
				
			}
			
		}else if(e.getSource() == stay){
			computer.setHand(hand[1][0],hand[1][1],hand[1][2],hand[1][3],hand[1][4]);
			cardVal[1] = computer.playHand();
			compcardnumber = computer.getCardNumber();
			lose = checkValue();
			if(lose == 1) {
				moveChips(chipsUpY, true);
			} else {
				moveChips(chipsLowY, false);
			}
			reset();
		}else if(e.getSource() == bet){
			try{
				wager = Integer.parseInt(amount.getText());
			}catch(Exception error){
				amount.setText("Not a valid bet");
			}
			
			if(((money - wager)< 0)||(wager <= 0)){
				amount.setText("Not a valid bet");
			}else{
				lose = 0;
				cardNumber = 0;
				compcardnumber = 0;
				money -= wager;
				bet.setEnabled(false);
				amount.setEnabled(false);
				hit.setEnabled(true);
				stay.setEnabled(true);
				// reset chips position
				Rectangle rect = chips.getBounds();
				rect.y = chipsLowY;
				chips.setBounds(rect);
				moveChips(chipsMiddleY, true);
				
				for(int i = 0; i < 2; i++){
					for(int j = 0; j < 5; j++){
						do{
						num = gen.nextInt(52);
						deck[num] = deck[num] - 1; 
						if(deck[num] < 0){
							deck[num] = deck[num] + 1;
							pass = 0;
						}else{
							hand[i][j] = num;
							pass = 1;
						}
						}while(pass == 0);
						
					}
				}
				
				for(int j = 0; j < 2; j ++){
					if ((hand[0][j] == 0)||
							(hand[0][j] ==13)||
							(hand[0][j] == 26)||
							(hand[0][j] == 39)){
							aceCount[0]++;
						}
						
					}
				
				cardVal[0] = value.getValue(hand[0][0]) + value.getValue(hand[0][1]);
				cardNumber = 2;
				
				if(cardVal[0] > 21){
					if(aceCount[0] == 0){
						lose = 1;
						reset();
				}else if(aceCount[0] > 0){
					aceCount[0]--;
					cardVal[0] -= 10;
					}
				}
				
			}
		}
		
	}
	
	private void moveChips(int targetY, boolean isUp) {
		if(isUp) {
			chipsMoveVelocity = (-1) * Math.abs(chipsMoveVelocity);
		} else {
			chipsMoveVelocity = Math.abs(chipsMoveVelocity);
		}
		chipsTargetY = targetY;
		chipsTimer.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D scale = (Graphics2D) g2d.create();
		g2d.setFont(new Font(null, Font.PLAIN, 25)); 
		g2d.setColor(Color.WHITE);
		g2d.drawString("Money: " + money, 25, 50);
		scale.scale(1.25, 1.25);
		if(lose == 1){
			g2d.setFont(new Font(null, Font.PLAIN, 60)); 
			g2d.setColor(Color.WHITE);
			g2d.drawString("YOU LOSE",350,425);
			g.clearRect(25, 25, 300, 300);
		}else if(lose == 2){
			g2d.setFont(new Font(null, Font.PLAIN, 60)); 
			g2d.setColor(Color.WHITE);
			g2d.drawString("YOU WIN",350,425);
			
		}else if(lose == 3){
			g2d.setFont(new Font(null, Font.PLAIN, 60)); 
			g2d.setColor(Color.WHITE);
			g2d.drawString("TIE",350,425);
		}
		
		
		if(cardNumber == 2){
		scale.drawImage(card.getCard(hand[0][0]), 50,400,null);
		scale.drawImage(card.getCard(hand[0][1]), 125,400,null);
		}else if(cardNumber == 3){
		scale.drawImage(card.getCard(hand[0][0]), 50,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][1]), 125,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][2]), 200,400,71,96,null);
		}else if(cardNumber == 4){			
		scale.drawImage(card.getCard(hand[0][0]), 50,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][1]), 125,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][2]), 200,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][3]), 275,400,71,96,null);
		}else if(cardNumber == 5){
		scale.drawImage(card.getCard(hand[0][0]), 50,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][1]), 125,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][2]), 200,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][3]), 275,400,71,96,null);
		scale.drawImage(card.getCard(hand[0][4]), 350,400,71,96,null);
		}
		
		if(compcardnumber == 2){
			scale.drawImage(card.getCard(hand[1][0]), 50,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][1]), 125,150,71,96,null);
			}else if(compcardnumber == 3){
			scale.drawImage(card.getCard(hand[1][0]), 50,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][1]), 125,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][2]), 200,150,71,96,null);
			}else if(compcardnumber == 4){
			scale.drawImage(card.getCard(hand[1][0]), 50,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][1]), 125,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][2]), 200,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][3]), 275,150,71,96,null);
			}else if(compcardnumber == 5){
			scale.drawImage(card.getCard(hand[1][0]), 50,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][1]), 125,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][2]), 200,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][3]), 275,150,71,96,null);
			scale.drawImage(card.getCard(hand[1][4]), 350,150,71,96,null);
			}
	}

}
