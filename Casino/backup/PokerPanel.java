package poker_GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import poker.Card;
import poker.Table;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class PokerPanel extends JPanel implements ActionListener{
	
	private BufferedImage twentyFive, fifty, hundred, twoFifty, fiveHundred, thousand, twentyFiveGrand, fiveGrand, tenGrand;
	private JButton call, check, raise, discard, allIn, fold;
	private BufferedImage pCard1, pCard2, pCard3, pCard4, pCard5, deck, background;
	Table table = new Table();	
	/* table {deck, cards, pot, chips, comparator}
	 * player {money, hand, }
	 */
	JLabel money;
	Timer timer;
	
	public PokerPanel(){
		setLayout(null);
		twentyFive = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		fifty = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		hundred = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		twoFifty = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		fiveHundred = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		thousand = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		twentyFiveGrand = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		fiveGrand = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		tenGrand = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		timer = new Timer(50, this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == timer){
			repaint();
		}
		
		if(e.getSource() == check){
			
		}
		else if(e.getSource() == call){
			
		}
		else if(e.getSource() == fold){
					
		}
		else if(e.getSource() == allIn){
			
		}
		else if(e.getSource() == raise){
			
		}
		else if(e.getSource() == discard){
			
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
	
	
	
	
}

