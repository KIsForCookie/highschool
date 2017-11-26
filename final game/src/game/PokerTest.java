package game;
import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.PokerCard;
import game.PokerCard.Suit;
import game.PokerCard.Rank;
import game.Chip;
import game.CompEval;
import game.PokerDeck;
import game.Hand;
import game.Player;
import game.PlayerTurn;
import game.Pot;
import game.Table;

public class PokerTest {
	
	int playerNum = 0;
	Stack<PokerCard> temp = new Stack<PokerCard>();
	ArrayList<PokerCard> temp2 = new ArrayList<PokerCard>();
	Hand h = new Hand();
	Hand m = new Hand();
	Hand n = new Hand();
	Hand p = new Hand();
	PokerDeck d = new PokerDeck();
	Pot curPot;
	
	Player Russel = new Player("Russel", "Peters", 19425, playerNum);
	
	CompEval x;
	Player Jimmies = new Player("James","Rustles", 19425, playerNum);
	Player Francis = new Player("Francis", "Zakaryan", 19425, playerNum);
	
	
	protected void setUp(){
		x = new CompEval();
		curPot = new Pot();
		
		//addPlayerTable(Jimmies);
		//addPlayerTable(Russel);
		
	}
	
	public PokerTest(){
		setUp();
		//testSF();
		//sortTest();
		//betTest();
		//turnTest();
		//HandCompTest();
		//tableTest();
		//new Cardtest();
		//AIBetTest();
		tbtst();
	}
	
	public static void main(String[] args){
		new PokerTest();
	}
	
	private void AIBetTest(){
		Table t = new Table();
		t.addPlayer(new Player(500));
		t.addPlayer(new Player(500));

		t.get(0).generateName();
		t.get(1).generateName();
		
		t.Deal();
		
		System.out.println(t.get(0).getName());
		System.out.println(t.get(1).getName());
		
		t.setAction(0, 2, (int)(Math.random() * 50000));
		t.setAction(1, 1, (int)(Math.random() * 50000));
		//t.get(0).toChips(t.get(0).Act()[1]);
		//t.get(1).toChips(t.get(1).Act()[1]);
		t.setChips();
		System.out.println(t.get(0).Act()[0]);
		System.out.println(t.get(1).Act()[0]);
		
		t.setAIChipsAdditive(2);
		
		for(int i = 0; i < t.a.get(0).size(); i++){
			System.out.print(Table.a.get(0).get(i).getValue() + ", ");
		}
		System.out.println();
		for(int i = 0; i < t.a.get(1).size(); i++){
			System.out.print(Table.a.get(1).get(i).getValue() + ", ");
		}
	}
	
	private void tbtst(){
		Table t = new Table();
		t.addPlayer(Russel);
		t.addPlayer(Jimmies);
		t.addPlayer(Francis);
		
		t.Deal();
		System.out.println(Russel.getHand().toString() + x.comp(Russel.getHand()).getCategory());
		System.out.println(Jimmies.getHand().toString() + x.comp(Jimmies.getHand()).getCategory());
		System.out.println(Francis.getHand().toString() + x.comp(Francis.getHand()).getCategory());
		
		t.compareTime();
	}
	
	private void tableTest(){
		Table t = new Table();
		
		t.addPlayer(Russel);
		t.addPlayer(Jimmies);
		t.addPlayer(Francis);
		
		t.ante();
		t.RoundStart();
	}
	
	private void HandCompTest(){
		Russel.playerNum = 1;
		Jimmies.playerNum = 0;
		
		for(int i = 0; i < 5; i++){
			Russel.addCard(d.Deal());
			Jimmies.addCard(d.Deal());
		}
		Jimmies.setHandCat(x.comp(Jimmies.getHand()).getCategory());
		System.out.println(x.comp(Jimmies.getHand()).getCategory());
		Russel.setHandCat(x.comp(Russel.getHand()).getCategory());
		System.out.println(x.comp(Russel.getHand()).getCategory());
		
		Jimmies.setCompareOrder(x.comp(Jimmies.getHand()).getHandOrder());
		Russel.setCompareOrder(x.comp(Russel.getHand()).getHandOrder());		
		System.out.println(Jimmies.getName() + ", " + Jimmies.getHand().compareOrder.toString());
		System.out.println(Jimmies.getName() + ", " + Jimmies.getHand().toString());
		System.out.println(Russel.getName() + ", " + Russel.getHand().compareOrder.toString());
		System.out.println(Russel.getName() + ", " + Russel.getHand().toString());

		int win = Russel.getHand().CompareHand(Jimmies.getHand());
		System.out.println("\n"+win);
		if(win < 0){
			System.out.println(Russel.getName() + " wins.");
		}
		else if(win > 0){
			System.out.println(Jimmies.getName() + " wins.");
		}		
	}
	
	private void turnTest(){
		//Jimmies.raise(8100, curPot);
		Russel.playerNum = 1;
		Jimmies.playerNum = 0;
		//curPot.bet(Jimmies.getChips(), Jimmies.playerNum, Jimmies.isAllIn());
		
		for(int i = 0; i < 5; i++){
			Russel.addCard(d.Deal());
			Jimmies.addCard(d.Deal());
		}
	
		PlayerTurn jt = new PlayerTurn(false);
		jt.RunForPlayer(Jimmies, curPot);
		curPot = jt.getPot();
		System.out.println(curPot.toString());
		System.out.println(curPot.getPotValue());
		
		PlayerTurn rt = new PlayerTurn(false);
		rt.RunForPlayer(Russel, curPot);
		curPot = rt.getPot();
		System.out.println(curPot.toString());
		System.out.println(curPot.getPotValue());
		System.out.println(Russel.getMoney());
	}
	
	private void betTest(){
		System.out.println(Russel.getName() + " has $" + Russel.getMoney());
		
		ArrayList<Chip> temp = new ArrayList<Chip>();
		//Russel.raise(19400, curPot);
		
		
		for(int i = 0; i < Russel.getChips().size(); i++){
			temp.add(Russel.getChips().get(i));
		}
		

		curPot.bet(temp, Russel.playerNum, Russel.isAllIn());

		
		System.out.println(curPot.toString());
		System.out.println(Russel.getName() + " has $" + Russel.getMoney());
		
	}
	
	private void sortTest(){
		for(int j = 0; j < 7; j++){
			d.shuffle();
			h.clear();
			m.clear();
			n.clear();
			p.clear();
			System.out.print("\n----------------------------------------------------------------------------------------------------\n\n");
			for(int i = 0; i < 5; i++){
				h.addToHand(d.Deal());
				m.addToHand(d.Deal());
				n.addToHand(d.Deal());
				p.addToHand(d.Deal());
			}
			
			System.out.println(x.comp(h).getCategory());
			System.out.println();
			System.out.println(x.comp(m).getCategory());
			System.out.println();
			System.out.println(x.comp(n).getCategory());
			System.out.println();
			System.out.println(x.comp(p).getCategory());
		}
		
		
		/*
		h.addToHand(new Card(Rank.SEVEN, Suit.SPADES));
		h.addToHand(new Card(Rank.SEVEN, Suit.HEARTS));
		h.addToHand(new Card(Rank.NINE, Suit.CLUBS));
		h.addToHand(new Card(Rank.NINE, Suit.DIAMONDS));
		h.addToHand(new Card(Rank.NINE, Suit.SPADES));
		*/
		//System.out.println(h.toString());
		//System.out.println(m.toString());
		//System.out.println(x.comp(h).getCategory());
		//System.out.println();
		//System.out.println(x.comp(m).getCategory());
		/*
		if(h.CompareHand(m) < 0){
			System.out.println("h wins");
		}
		else
			System.out.println("m wins");
			*/
		
		//System.out.println(x.comp(h).toString());
	}
	
	private void testSF(){
		
		h.addToHand(new PokerCard(Rank.ACE, Suit.SPADES));
		h.addToHand(new PokerCard(Rank.TEN, Suit.HEARTS));
		h.addToHand(new PokerCard(Rank.SEVEN, Suit.CLUBS));
		h.addToHand(new PokerCard(Rank.THREE, Suit.CLUBS));
		h.addToHand(new PokerCard(Rank.THREE, Suit.SPADES));
		
		h.Sort();
		System.out.println(x.comp(h));
		/*
		System.out.println();
		h.clear();
		h.addToHand(new Card(Rank.ACE, Suit.SPADES));
		h.addToHand(new Card(Rank.TWO, Suit.SPADES));
		h.addToHand(new Card(Rank.THREE, Suit.SPADES));
		h.addToHand(new Card(Rank.FOUR, Suit.SPADES));
		h.addToHand(new Card(Rank.FIVE, Suit.SPADES));
		
		if(x.comp(h).getCategory() == Category.STRAIGHT_FLUSH){
			System.out.println("is straight flush");
			System.exit(1);
			
		}*/
	}
	
	private class Cardtest extends JFrame{
		
		testPanel panel = new testPanel();
		DisplayMode dm;
		
		 Cardtest(){
			d.reset();
			setVisible(true);
			setSize(1024, 768);
			dm = new DisplayMode(1024, 768, 16, 60);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(panel);
			
		 }
		
	}
	
	private class testPanel extends JPanel implements ActionListener{
		BufferedImage card;
		Timer timer;
		testPanel(){
			timer = new Timer(500, this);
			try{
				card = d.Deal().getImage();
			}catch(Exception e){}
			repaint();
			timer.start();
		}
		
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
	        Graphics2D scale = (Graphics2D) g2d.create();
	        scale.scale(1, 1);
	        
	        scale.drawImage(d.getTopCard().getImage(), 50, 50, null);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == timer){
				d.Deal();
				repaint();
			}
			
		}
		
	}
}


