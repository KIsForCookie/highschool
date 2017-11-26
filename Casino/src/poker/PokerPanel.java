package poker;

import javax.swing.*;

import poker.Card.Rank;
import poker.Card.Suit;
import poker.Chip;
import poker.Chip.Value;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.ArrayList;

public class PokerPanel extends JPanel implements ActionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	private JButton twentyFive, fifty, hundred, twoFifty, fiveHundred, thousand, 
		twentyFiveHundred, fiveGrand, tenGrand;
	private ImageIcon twentyFiveimg, fiftyimg, hundredimg, twoFiftyimg, fiveHundredimg, 
		thousandimg, twentyFiveHundredimg, fiveGrandimg, tenGrandimg;
	private JLabel background;
	private JButton call, check, raise, allIn, fold, go, leave, confirm, back, discard;
	public static Table table = new Table();	
	public Deck deck;
	//public Pot pot;
	double ninety = Math.toRadians(90);
	boolean showCPUCards = true;
	boolean	cardsFaceUp = false;
	double cardwidth = new Card(Rank.ACE, Suit.SPADES).getBack().getWidth();
	double cardheight = new Card(Rank.ACE, Suit.SPADES).getBack().getHeight();
	int played = 0;
	int i = 0;	
	
	CTimer nextTurn = new CTimer();
	
	JOptionPane msgBox = new JOptionPane();
	CompEval u = new CompEval();
	ArrayList<JButton> laziness1 = new ArrayList<JButton>();	//for using methods over and over and over and over and...
	ArrayList<Chip> PlayerChips = new ArrayList<Chip>();	//dealer is at position 0
	ArrayList<JLabel> ChipImages = new ArrayList<JLabel>();
	ArrayList<Card> discards = new ArrayList<Card>();
	ArrayList<Integer> x = new ArrayList<Integer>(), y = new ArrayList<Integer>();
	
	Player PC;
	
	JLabel money;
	Timer timer, update;
	boolean[] selected = new boolean[5];
	int cardsSel = 0;
	int PlayerPos;
	
	
	
	private class CTimer implements ActionListener{
		Timer timer = new Timer(100, this);
		@Override
		public void actionPerformed(ActionEvent arg0) {
				if(played == 1){
					System.out.println("played = 0");
					played = 0;
					for(int i = 0; i < table.size(); i++){
						if(i==PlayerPos){
							System.out.println("Continues");
							continue;
						}
						System.out.println("doesnt continue");
						table.get(i).AIPlay();
						System.out.println(table.get(i).Act()[0] + ", " + table.get(i).Act()[1]);
						table.setAction(i, table.get(i).Act()[0], table.get(i).Act()[1]);
						
						
						System.out.println(table.get(i).Act()[0] + ", " + table.get(i).Act()[1]);
					}
				}
		}
		
		public void start(){
			timer.start();
		}
		
		public void stop(){
			timer.stop();
		}
			
	}
	
	private void setSelected(int index){
		if(cardsSel < 3 && selected[index] != true){
			selected[index] = true;
		}
		else if(selected[index] == true){
			selected[index] = false;
		}
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D scale = (Graphics2D) g2d.create();
        g2d.setFont(new Font(null, Font.PLAIN, 18)); 
		g2d.setColor(Color.WHITE);
		g2d.drawString("Current Bet: " + getBetValue(), 850, 530);
		g2d.drawString("Money: " + table.get(PlayerPos).getMoney(), 850, 550);
		scale.scale(1,1);		
		
		if(table.getPot().getPotValue() > 0){
			g2d.setColor(Color.YELLOW);
			g2d.drawString("$" + table.getPot().getPotValue(), 450, 350);
		}
		
		if(table.get(PlayerPos).folded == false){
			for(int i = 0; i < table.get(PlayerPos).getHand().size(); i++){
				scale.drawImage(table.get(PlayerPos).getHand().getCard(i).getImage(), 350 + i * 75, 575, null);
			}
		
		}else if(table.get(PlayerPos).folded){
			for(int i = 0; i < table.get(PlayerPos).getHand().size(); i++){
				scale.drawImage(table.get(PlayerPos).getHand().getCard(i).getBack(), 350 + i * 75, 575, null);
			}
		}
		//playerchips		
		for(int i = 0; i < PlayerChips.size(); i++){
			scale.drawImage(PlayerChips.get(i).getBImg(), x.get(i), y.get(i), null);
		}
			
		//Comp chips
		for(int i = 0; i < table.size(); i++){
			if(i == PlayerPos)
				continue;
			for(int j = 0; j < table.get(i).getChips().size(); j++){
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
				scale.drawImage(table.get(i).getChips().get(j).getBImg(), x.get((int)(Math.random() * x.size())),
						y.get((int)(Math.random() * y.size())), null);
			}
		}
		
		//cards
		if(showCPUCards == true){
			if(cardsFaceUp == true){
				ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
				if(table.get(1) != null && PlayerPos != 1){
					for(int j = 0; j < table.get(1).getHand().size(); j++){
						temp.add(rotate(table.get(1).getHand().getCard(j).getImage(), 1));
					}
					for(int i = 0; i < temp.size(); i++){
						scale.drawImage(temp.get(i), 50, 150 + i*75, null);
						
					}
				}
				temp.clear();
				if(table.size() > 2){
					if(table.get(2) != null && PlayerPos != 2){
						
						for(int j = 0; j < table.get(2).getHand().size(); j++){
							//temp.add(rotate(table.get(2).getHand().getCard(j).getImage(), 2));
							temp.add(rotate(rotate(table.get(2).getHand().getCard(j).getImage(), 1), 1));
						}
						for(int i = 0; i < temp.size(); i++){
							scale.drawImage(temp.get(i), 350 + i *75, 50, null);
						}
					}
					temp.clear();
				}
				if(table.size() > 3){
					if(table.get(3) != null && PlayerPos != 3){
						
						for(int j = 0; j < table.get(3).getHand().size(); j++){
							temp.add(rotate(table.get(3).getHand().getCard(j).getImage(), 3));
						}
						for(int i = 0; i < temp.size(); i++){
							scale.drawImage(temp.get(i), 875, 125 + i*75, null);
						}
					}
					temp.clear();
				}
			}
			if(cardsFaceUp == false){
				ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
				if(table.get(1) != null && PlayerPos != 1){
					for(int j = 0; j < table.get(1).getHand().size(); j++){
						temp.add(rotate(Card.back, 1));
					}
					for(int i = 0; i < temp.size(); i++){
						scale.drawImage(temp.get(i), 50, 150 + i*75, null);
						
					}
				}
				temp.clear();
				if(table.size() > 2){
					if(table.get(2) != null && PlayerPos != 2){
						
						for(int j = 0; j < table.get(2).getHand().size(); j++){
							//temp.add(rotate(table.get(2).getHand().getCard(j).getImage(), 2));
							temp.add(rotate(Card.back, 2));
						}
						for(int i = 0; i < temp.size(); i++){
							scale.drawImage(temp.get(i), 350 + i *75, 50, null);
						}
					}
					temp.clear();
				}
				if(table.size() > 3){
					if(table.get(3) != null && PlayerPos != 3){
						
						for(int j = 0; j < table.get(3).getHand().size(); j++){
							temp.add(rotate(Card.back, 3));
						}
						for(int i = 0; i < temp.size(); i++){
							scale.drawImage(temp.get(i), 875, 125 + i*75, null);
						}
					}
					temp.clear();
				}
			}
		}
    }
	
	private int getBetValue(){
		int value = 0;
		for(int i = 0; i < PlayerChips.size(); i++){
			value += PlayerChips.get(i).getValue();
		}
		return value;
	}
	
	private void showCards(){
		cardsFaceUp = true;
	}
	
	private void reset(){
		Player temp = table.get(PlayerPos);
		table.table.clear();
		table.addPlayer(temp);
		table.pot = null;
		table.deck.reset();
		table.deck.shuffle();
		
	}
	
	private BufferedImage rotate(BufferedImage image, int quadrants){
		int w0 = image.getWidth();
	    int h0 = image.getHeight();
	    int w1 = w0;
	    int h1 = h0;
	    int centerX = w0 / 2;
	    int centerY = h0 / 2;

	    if (quadrants % 2 == 1) {
	        w1 = h0;
	        h1 = w0;
	    }

	    if (quadrants % 4 == 1) {
	        centerX = h0 / 2;
	        centerY = h0 / 2;
	    } else if (quadrants % 4 == 3) {
	        centerX = w0 / 2;
	        centerY = w0 / 2;
	    }

	    AffineTransform affineTransform = new AffineTransform();
	    affineTransform.setToQuadrantRotation(quadrants, centerX, centerY);
	    AffineTransformOp opRotated = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
	    
	    BufferedImage transformedImage = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_ARGB);
	    transformedImage = opRotated.filter(image, transformedImage);

	    return transformedImage;
	}
	
	public void showError(int error){
		switch(error){
		case 0:	//discard list full
			JOptionPane.showMessageDialog(null, "You may not discard more than three cards.");
			break;
		case 1:	//raise is 0
			JOptionPane.showMessageDialog(null, "");
			break;
		case 2: //not enough money for action
			JOptionPane.showMessageDialog(null, "");
			break;
		default:	//for copying
			JOptionPane.showMessageDialog(null, "");	
			break;
		}
	}
	
	public PokerPanel(){
				
		check = new JButton("Check");
		call = new JButton("Call");
		raise = new JButton("Raise");
		allIn = new JButton("All In");
		fold = new JButton("Fold");
		confirm = new JButton("Confirm");
		back = new JButton("Back");
		discard = new JButton("Discard");
		
		check.setBounds(915, 570, 75, 25);
		call.setBounds(915, 600, 75, 25);
		raise.setBounds(915, 630, 75, 25);
		allIn.setBounds(915, 660, 75, 25);
		fold.setBounds(915, 690, 75, 25);
		confirm.setBounds(900, 630, 90, 25);
		back.setBounds(900, 600, 90, 25);
		discard.setBounds(915, 600, 75, 25);
	
		check.setVisible(false);
		call.setVisible(false);
		raise.setVisible(false);
		allIn.setVisible(false);
		fold.setVisible(false);
		confirm.setVisible(false);
		back.setVisible(false);
		discard.setVisible(false);
		
		check.addActionListener(this);
		call.addActionListener(this);
		raise.addActionListener(this);
		allIn.addActionListener(this);
		fold.addActionListener(this);
		confirm.addActionListener(this);
		back.addActionListener(this);
		discard.addActionListener(this);
		
		setSize(1024,768);
		setLayout(null);
		
		twentyFiveimg = 		new Chip(Value.TWENTY_FIVE).getImage();
		fiftyimg = 				new Chip(Value.FIFTY).getImage();
		hundredimg = 			new Chip(Value.HUNDRED).getImage();
		twoFiftyimg = 			new Chip(Value.TWO_FIFTY).getImage();
		fiveHundredimg = 		new Chip(Value.FIVE_HUNDRED).getImage();
		thousandimg = 			new Chip(Value.THOUSAND).getImage();
		twentyFiveHundredimg = 	new Chip(Value.TWO_HALF_GRAND).getImage();
		fiveGrandimg = 			new Chip(Value.FIVE_GRAND).getImage();
		tenGrandimg = 			new Chip(Value.TEN_GRAND).getImage();
		
		twentyFive = 			new JButton((twentyFiveimg));
		fifty = 				new JButton((fiftyimg));
		hundred =				new JButton((hundredimg));
		twoFifty = 				new JButton((twoFiftyimg));
		fiveHundred = 			new JButton((fiveHundredimg));
		thousand = 				new JButton((thousandimg));
		twentyFiveHundred = 	new JButton((twentyFiveHundredimg));
		fiveGrand = 			new JButton((fiveGrandimg));
		tenGrand = 				new JButton((tenGrandimg));
		
		leave = new JButton("Leave Table");
		leave.setBounds(875, 5, 125, 25);
		add(leave);
		leave.setVisible(true);
		leave.addActionListener(this);
		
		go = new JButton("Start Round");
		go.setBounds(30, 550, 150, 30);
		add(go);
		go.setVisible(true);
		go.addActionListener(this);
		
		laziness1.add(twentyFive);
		laziness1.add(fifty);
		laziness1.add(hundred);
		laziness1.add(twoFifty);
		laziness1.add(fiveHundred);
		laziness1.add(thousand);
		laziness1.add(twentyFiveHundred);
		laziness1.add(fiveGrand);
		laziness1.add(tenGrand);

		for(int i = 0; i < laziness1.size(); i++){
			laziness1.get(i).setBorderPainted(false); 
			laziness1.get(i).setContentAreaFilled(false); 
			laziness1.get(i).setFocusPainted(false); 
			laziness1.get(i).setOpaque(false);
			laziness1.get(i).setSize(96,96);
			laziness1.get(i).setVisible(false);
			laziness1.get(i).setSize(96, 96);
			laziness1.get(i).setBounds(i*100, 5, 96, 96);
			laziness1.get(i).addActionListener(this);
			add(laziness1.get(i));
		}
		
		background = new JLabel(new ImageIcon("images/table.jpg"));
		background.setBounds(0,0,1024,768);

		add(check);
		add(call);
		add(raise);
		add(allIn);
		add(fold);
		add(confirm);
		add(back);
		add(discard);

		add(background);
		timer = new Timer(25, this);
		
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).isCompPlayer() != true){
				PlayerPos = i;
				break;
			}
		}
		//System.out.println(PlayerPos);
		PC = new Player("James", "Rustles", 19425, table.size());
		table.addPlayer(PC);
		
		int k = (int)(Math.random() * 3 + 1);	//0 + 1, 1 + 1, 2 + 1
		for(int i = 1; i < k + 1; i++){
			table.addPlayer(new Player(table.size()));
			table.get(i).generateName();
			table.get(i).setCompPlayer();
			//player is position 0, and minimum of 1 additional player to max of 4 'players' (including PC)
		}
		
	}
	
	public void gameStart(){
		//pot = new Pot();
		//deck = new Deck();
		nextTurn.start();
		Table.deck.shuffle();
		table.Deal();
		
		//for(int j = 0; j < 5; j++){
			for(int i = 0; i < table.size(); i++){
				System.out.println(table.get(i).getHand().toString());
			}
		//}
		
		
		//System.out.println(table.get(PlayerPos).getHand().toString() + u.comp(table.get(PlayerPos).getHand()).getCategory());
		if(table.get(PlayerPos).getMoney() < table.getPot().minimumBet){
			check.setVisible(false);
			call.setVisible(false);
			raise.setVisible(false);
			allIn.setVisible(false);
			leave.setVisible(true);
			go.setVisible(true);
			for(int i = 0; i < laziness1.size(); i++){
				laziness1.get(i).setVisible(false);
			}
			
			for(int i = 0; i < table.size(); i++){
				if(i==PlayerPos)
					continue;
				table.get(i).AIPlay();
			}
			
			
			
		}
		

		
		/*
		pCard1.setIcon(table.get(PlayerPos).getHand().getCard(0).getImage());
		pCard2.setIcon(table.get(PlayerPos).getHand().getCard(1).getImage());
		pCard3.setIcon(table.get(PlayerPos).getHand().getCard(2).getImage());
		pCard4.setIcon(table.get(PlayerPos).getHand().getCard(3).getImage());
		pCard5.setIcon(table.get(PlayerPos).getHand().getCard(4).getImage());
		*/
		/*
		for(int i = 0; i < pCards.size(); i++){
			pCards.get(i).setIcon(table.get(PlayerPos).getHand().getCard(i).getImage());
			pCards.get(i).setVisible(true);
			repaint();
		}*/
		//complicated game stuff here
		
		
	}
	
	private void AISGo(){
		table.setAction();
		//table.AIGo();
	}
	
	public void actionPerformed(ActionEvent e){
		repaint();
		if(e.getSource() == timer){
			repaint();
		}
		if(e.getSource() == leave){
			
		}
		if(e.getSource() == go){
			check.setVisible(true);
			if(table.getPot().minimumBet > 0)
				call.setVisible(true);
			else
				call.setVisible(false);
			raise.setVisible(true);
			fold.setVisible(true);
			allIn.setVisible(true);
			go.setVisible(false);
			leave.setVisible(false);
			
			gameStart();
			
		}
		if(e.getSource() == twentyFive){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.TWENTY_FIVE).getValue()){
				PlayerChips.add(new Chip(Value.TWENTY_FIVE));
				//table.get(PlayerPos).betChip(new Chip(Value.TWENTY_FIVE));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == fifty){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.FIFTY).getValue()){
				PlayerChips.add(new Chip(Value.FIFTY));
				//table.get(PlayerPos).betChip(new Chip(Value.FIFTY));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == hundred){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.HUNDRED).getValue()){
				PlayerChips.add(new Chip(Value.HUNDRED));
				//table.get(PlayerPos).betChip(new Chip(Value.HUNDRED));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == twoFifty){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.TWO_FIFTY).getValue()){
				PlayerChips.add(new Chip(Value.TWO_FIFTY));
				//table.get(PlayerPos).betChip(new Chip(Value.TWO_FIFTY));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
			
		}
		if(e.getSource() == fiveHundred){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.FIVE_HUNDRED).getValue()){
				PlayerChips.add(new Chip(Value.FIVE_HUNDRED));
				//table.get(PlayerPos).betChip(new Chip(Value.FIVE_HUNDRED));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == thousand){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.THOUSAND).getValue()){
				PlayerChips.add(new Chip(Value.THOUSAND));
				//table.get(PlayerPos).betChip(new Chip(Value.THOUSAND));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == twentyFiveHundred){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.TWO_HALF_GRAND).getValue()){
				PlayerChips.add(new Chip(Value.TWO_HALF_GRAND));
				//table.get(PlayerPos).betChip(new Chip(Value.TWO_HALF_GRAND));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == fiveGrand){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.FIVE_GRAND).getValue()){
				PlayerChips.add(new Chip(Value.FIVE_GRAND));
				//table.get(PlayerPos).betChip(new Chip(Value.FIVE_GRAND));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == tenGrand){
			if(table.get(PlayerPos).getMoney() >= new Chip(Value.TEN_GRAND).getValue()){
				PlayerChips.add(new Chip(Value.TEN_GRAND));
				//table.get(PlayerPos).betChip(new Chip(Value.TEN_GRAND));
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
		}
		if(e.getSource() == check){
			played = 1;
			table.get(PlayerPos).check();
			table.get(PlayerPos).setAction(0, 0);
			table.getPot().Action(table.get(PlayerPos), table.get(PlayerPos).Act());
			AISGo();
			
		}
		else if(e.getSource() == call){
			table.get(PlayerPos).call();
			check.setVisible(false);
			table.get(PlayerPos).setAction(1, 0);
			table.getPot().Action(table.get(PlayerPos), table.get(PlayerPos).Act());
			x.add(new Integer((int)(Math.random() * 256 + 352)));
			y.add(new Integer((int)(Math.random() * 128 + 256)));
			played = 1;
		}
		else if(e.getSource() == fold){
			table.get(PlayerPos).setAction(0, 0);
			table.getPot().Action(table.get(PlayerPos), table.get(PlayerPos).Act());
			table.get(PlayerPos).folded = true;
			AISGo();
		}
		else if(e.getSource() == allIn){
			check.setVisible(false);
			table.get(PlayerPos).setAction(3, 0);
			table.getPot().Action(table.get(PlayerPos), table.get(PlayerPos).Act());
			for(int i = 0; i < table.get(PlayerPos).getChips().size(); i++){
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
				played = 1;
			}
		}
		else if(e.getSource() == raise){
			confirm.setVisible(true);
			back.setVisible(true);
			fold.setVisible(false);
			check.setVisible(false);
			raise.setVisible(false);
			call.setVisible(false);
			allIn.setVisible(false);
			showCPUCards = false;
			for(int i = 0; i < laziness1.size(); i++){
				laziness1.get(i).setVisible(true);	
			}
			table.get(PlayerPos).getChips().clear();
			
			//table.get(index).raise(table.get(index).getChips(), table.getPot());
			check.setVisible(false);
			//table.get(index).setAction(2);
		}
		if(e.getSource() == back){
			if(table.getPot().betsMade == true)
				check.setVisible(false);
			else
				check.setVisible(true);
			if(table.getPot().minimumBet > 0)
				call.setVisible(true);
			else
				call.setVisible(false);
			raise.setVisible(true);
			fold.setVisible(true);
			allIn.setVisible(true);
			go.setVisible(false);
			leave.setVisible(false);
			back.setVisible(false);
			confirm.setVisible(false);
			showCPUCards = true;
			for(int i = 0; i < laziness1.size(); i++){
				laziness1.get(i).setVisible(false);	
			}
			while(table.get(PlayerPos).getChips().size() > 0){
				table.get(PlayerPos).setMoney(table.get(PlayerPos).getMoney() + table.get(PlayerPos).getChips().get(0).getValue());
				table.get(PlayerPos).getChips().remove(0);
			}
		}
		if(e.getSource() == confirm){
			check.setVisible(false);
			call.setVisible(true);
			raise.setVisible(true);
			fold.setVisible(true);
			allIn.setVisible(true);
			go.setVisible(false);
			leave.setVisible(false);
			back.setVisible(false);
			confirm.setVisible(false);
			
			table.get(PlayerPos).setAction(2);
			table.getPot().Action(table.get(PlayerPos), table.get(PlayerPos).Act());
			showCPUCards = true;
			
			for(int i = 0; i < table.get(PlayerPos).getChips().size(); i++){
				x.add(new Integer((int)(Math.random() * 256 + 352)));
				y.add(new Integer((int)(Math.random() * 128 + 256)));
			}
			for(int i = 0; i < laziness1.size(); i++){
				laziness1.get(i).setVisible(false);
			}
			for(int i = 0; i < PlayerChips.size(); i++){
				table.get(PlayerPos).betChip(PlayerChips.get(i));
			}
			played = 1;
		}
	}
		
	@SuppressWarnings({ "serial", "hiding" })
	class CompCards<BufferedImage> extends ArrayList<ArrayList<BufferedImage>>{
		//CompCards = table.	get(i).	getHand().	get(j).	getImage();
		//CompCards = table, 	player, hand, 		Card, 	image
		@SuppressWarnings("unchecked")
		public void addNewHand(int indexIn, BufferedImage img){
			int index = indexIn - 1;
			while(this.size() < index){
				this.add(new ArrayList<BufferedImage>());
				//			npc, 		cards
			}
			
			for(int j = 0; j < table.get(indexIn).getHand().size(); j++){
				this.get(index).add(img);
				this.get(index).set(j, (BufferedImage) table.get(indexIn).getHand().getCard(j).getImage());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int mouseX = (int) b.getX();
		int mouseY = (int) b.getY();
		
		if(mouseY > 575 && mouseY < 666){
			if(mouseX > 350 && mouseX < 421){
				setSelected(0);
			}
			if(mouseX > 425 && mouseX < 496){
				setSelected(1);			
			}
			if(mouseX > 500 && mouseX < 571){
				setSelected(2);
			}
			if(mouseX > 575 && mouseX < 646){
				setSelected(3);
			}
			if(mouseX > 650 && mouseX < 721){
				setSelected(4);
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	
}

