/**
 CrapsGame.java
 By: Areg Vanesyan
 Program Description:This program allows users to play craps.
 */

package GUI;
import java.awt.Color;
import java.awt.DisplayMode;
import javax.swing.JFrame;
import GUI.Resolution;

public class CrapsGame {

	public CrapsGame(){
		CrapsPanel a = new CrapsPanel();
		/*a.btnRollDie.setSize(130, 37);
		a.lblMessage.setSize(199, 50);
		a.lblDiceOne.setSize(68, 64);
		a.lblDiceTwo.setSize(69, 64);
		a.btnLeave.setLocation(850, 711);
		a.lblBackground.setSize(1024, 873);
		a.btnFiveDollar.setLocation(150, 481);
		a.btnOneDollar.setLocation(89, 481);
		a.lblMessage.setLocation(474, 718);
		a.lblDiceTwo.setLocation(165, 203);
		a.lblDiceOne.setLocation(78, 203);
		a.lblPointNumber.setLocation(86, 278);
		a.btnRollDie.setLocation(85, 534);
		a.lblWins.setLocation(52, 717);
		a.lblLoses.setLocation(52, 738);
		a.btnOneHundredDollar.setLocation(90, 414);
		a.btnFiftyDollar.setLocation(150, 448);
		a.btnTwentyFiveDollar.setLocation(89, 448);
		a.lblBet.setLocation(167, 717);
		a.lblBackground.setLocation(0, -105);
		a.lblBank.setLocation(167, 738);*/
		Resolution resolution = new Resolution();
		DisplayMode mode = new DisplayMode(1024,768,32,60);
		
		JFrame window = new JFrame("Craps");
 		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1366,768);
		window.setResizable(false);
		window.getContentPane().add(a);
		window.setUndecorated(true);
		window.setVisible(true);
		window.setBackground(Color.GREEN);
		try
		{
			resolution.setFullScreen(mode, window);
		}catch(Exception error)
		{
			System.out.println("Error");
		}
		
	}
	
	public static void main(String[] args) {
		new CrapsGame();
	}

}



