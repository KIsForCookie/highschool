

package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;


public class Frame{
	//declarations
	JFrame window;
	RpgPanel rpg;
	BlackjackPanel blackjack;
	MainPanel titlescreen;
	JPanel cont;
	Resolution resolution;
	DisplayMode mode;
	CardLayout cl;
	Timer timer;
	int money;
	AtmPanel atm;
	CrapsPanel craps;
	Table t;
	Fade fade;
	SlotPanel slot;
	PokerPanel poker;
	
	public Frame(){
		money = 1000;
		t = new Table();
		timer = new Timer(100,new ActionListener(){
			@Override
			
			//timer set to refresh every 0.1 sec. Preformes actions basesd on select variable in rpg Panel 
			
			public void actionPerformed(ActionEvent e) {
				if(rpg.select == 1){ //goes to blackjack
					cl.show(cont,"blackjack");
					sync();
					rpg.select = 0;
				}else if(rpg.select == 2){//goes to the title screen. If bankrupt, goes to the lose screen
					if((money == 0)&&(atm.stored == 0)&&(atm.maxloan == 0)){
			        	rpg.select = 666;
					}else{
						cl.show(cont,"title");
						rpg.stopMusic();
						rpg.select = 0;
						sync();
						titlescreen.reset();
					}	
				}else if(rpg.select == 6){//goes to the atm screen
					cl.show(cont,"atm");
					//rpg.stopMusic();
					sync();
					rpg.select = 0;
				}else if(rpg.select == 666){//goes to the lose screen
					cl.show(cont,"lose");
					rpg.select = 0;
					rpg.stopMusic();
				}else if(rpg.select == 4){//goes to the slots screen
					cl.show(cont, "slot");
					sync();
					rpg.select = 0;
				}else if(rpg.select == 5){
					cl.show(cont, "craps");
					sync();
					rpg.select = 0;
				}else if(rpg.select == 3){
					cl.show(cont,"poker");
					sync();
				}
				
				if((rpg.character.name != "")&&(rpg.character.gender != 0)){
					titlescreen.start.setText("RESUME");
				}else{
					titlescreen.start.setText("START");
				}
			}
		});
		
		//instantations
		titlescreen = new MainPanel();
		window = new JFrame("game");
		rpg = new RpgPanel();
		atm = new AtmPanel();
		blackjack = new BlackjackPanel();
		cont = new JPanel();
		fade = new Fade();
		craps = new CrapsPanel();
		slot = new SlotPanel();
		poker = new PokerPanel();
		resolution = new Resolution(); //resolution class allows for fullscreen, color bit depth, resolution, and screen refrech rate of my choosing
		mode = new DisplayMode(1024,768,16,60);
		cl = new CardLayout(); //card layout allows for changing JPanels on the same JFrame
		
		
		//adds an action listener to the leave button in the atm screen
		atm.leave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont, "rpg");
				money = atm.money;
				sync();
			}
		});
		
		//adds actionlistener to leave button in blackjack panel
		blackjack.leave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont,"rpg"); //changes cardlayer to the rpg panel
				money = blackjack.money;
				sync();
			}
			
		});
		
		poker.leave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cont,"rpg");
				money = poker.table.get(0).getMoney();
				sync();
			}
			
		});
		
		poker.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				poker.requestFocusInWindow(); //JPanel requests focus so that user can interact with JPanel
				poker.reset();
			}
		});
		
		
		//adds an action listener to the start button on the titlescreen start button
		titlescreen.start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if((rpg.character.name != "")&&(rpg.character.gender != 0)){
					cl.show(cont,"rpg");
					rpg.playMusic(new File("res/music.wav").getAbsoluteFile()); //plays music using absolute file of the music wav file
					rpg.character.x = 10;
					if(rpg.character.gender == 1){
						rpg.character.choice = rpg.character.r;
					}else if(rpg.character.gender == 2){
						rpg.character.choice = rpg.character.fr;
					}
					sync();
				}else{
					titlescreen.titlename.setVisible(false);
					titlescreen.start.setVisible(false);
					titlescreen.nameprompt.setVisible(true);
					titlescreen.input.setVisible(true);
					titlescreen.quit.setVisible(false);
					titlescreen.next.setVisible(true);
					titlescreen.background.setVisible(false);
				}
				
			}
			
		});
		
		//adds an actionlistener to the lose button is the fade panel
		fade.lose.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont,"title");
				rpg.select = 0;
				rpg.character.name = "";
				rpg.character.gender = 0;
				titlescreen.reset();
				money = 1000;
				titlescreen.step = 0;
				rpg.timer.start();
			}
		});
		
		   craps.btnLeave.addActionListener(new ActionListener(){// adds action listener to the btnleave button in the craps panel
			   @Override
			   public void actionPerformed(ActionEvent e){
				   cl.show(cont,"rpg"); //changes cardlayer to the rpg panel
					money = craps.intBank;
					sync();
				}
			});
		
		//adds action listener to the next button in the titlescreen panel
		titlescreen.next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(titlescreen.step == 0){
					
					if((titlescreen.input.getText().equals(""))||(titlescreen.input.getText().length() > 9)){
						titlescreen.input.setText("Invalid name");
					}else{
						titlescreen.step = 1;
						rpg.character.name = titlescreen.input.getText();
						titlescreen.input.setText("");
						titlescreen.genderprompt.setVisible(true);
						titlescreen.nameprompt.setVisible(false);
					}
					
					}else if(titlescreen.step == 1){
					try{
						if(titlescreen.input.getText().equalsIgnoreCase("m")){
							rpg.character.setGender(1);
							titlescreen.step = 2;
							titlescreen.genderprompt.setVisible(false);
							titlescreen.input.setVisible(false);
							titlescreen.story.setVisible(true);
							titlescreen.story1.setVisible(true);
							titlescreen.story2.setVisible(true);
							titlescreen.story3.setVisible(true);
						}else if(titlescreen.input.getText().equalsIgnoreCase("f")){
							rpg.character.setGender(2);
							titlescreen.genderprompt.setVisible(false);
							titlescreen.input.setVisible(false);
							titlescreen.story.setVisible(true);
							titlescreen.story1.setVisible(true);
							titlescreen.story2.setVisible(true);
							titlescreen.story3.setVisible(true);
							titlescreen.step = 2;
						}else{
							titlescreen.input.setText("Invalid gender");
						}
					}catch(Exception error){
						
					}
				}else if(titlescreen.step == 2){
					cl.show(cont,"rpg");
					rpg.playMusic(new File("res/music.wav").getAbsoluteFile()); //plays music using absolute file of the music wav file
					rpg.character.x = 10;
					sync();
				}
				
			}
			
		});
		
		craps.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				craps.requestFocusInWindow(); //JPanel requests focus so that user can interact with JPanel
				craps.reset();
			}
		});
		
		slot.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				slot.requestFocusInWindow(); //JPanel requests focus so that user can interact with JPanel
				slot.reset();
			}
		});
		
		slot.leave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cont, "rpg");
				money = slot.coinCount;
				sync();
			}
			
		});
		
		// adds a component listener to fade JPanel allowing me to give it commands whenever the JPanel is shown
		fade.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				fade.requestFocusInWindow(); //JPanel requests focus so that user can interact with JPanel
				fade.reset();
				fade.timer.start();
				rpg.timer.stop();
			}
		});
		
		//adds a component listener to the atm JPanel allowing me to give it commands whenever the JPanel is shown
		atm.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				atm.requestFocusInWindow();
				atm.reset();
				atm.screennumber = 0;
			}
		});
		
		//adds a componentlistener to the titlescreen panel allowing me to give it commands whenever the JPanel is shown
		titlescreen.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				titlescreen.requestFocusInWindow();//requests focus for the panel whenever it is called by the card layer
			}
		});
		
		//adds a componentlistener to the rpg panel allowing me to give it commands whenever the JPanel is shown
		rpg.addComponentListener(new ComponentListener(){
			@Override
			public void componentShown(ComponentEvent e){
		        rpg.requestFocusInWindow();
			}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
		
		//adds a componentlistener to the blackjack panel allowing me to give it commands whenever the JPanel is shown
		blackjack.addComponentListener(new ComponentListener(){
			@Override
			public void componentHidden(ComponentEvent e){}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				blackjack.requestFocusInWindow();
				blackjack.fullReset();
			}
			
		});
		//setting layout and adding components to cont panel
		cont.setLayout(cl);
		cont.add(blackjack,"blackjack");
		cont.add(rpg,"rpg");
		cont.add(titlescreen,"title");
		cont.add(atm,"atm");
		cont.add(fade,"lose");
		cont.add(slot,"slot");
		cont.add(poker,"poker");
		cont.add(craps,"craps");
		//makes initial panel seen the title panel
		cl.show(cont, "title");
		//adds cont panel to the window frame and sets up the frame for fullscreen optimiation
		window.add(cont);
		window.setSize(1024,768);
		window.setUndecorated(true);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//passes frame width and height from the resolution settings to the rpg panel
		rpg.frameWidth = mode.getWidth();
		rpg.frameHeight = mode.getHeight();
		timer.start();
		
		
		try{
			//makes window frame full screen, while catching any exceptions
			resolution.setFullScreen(mode, window);
		}catch(Exception error){
			System.out.println(error.getCause());
		}
	}
	
	//syncs money between all the classes
	public void sync(){
		blackjack.money = money;
		rpg.money = money;
		atm.money = money;
		slot.coinCount = money;
		craps.intBank = money;
		poker.table.get(0).setMoney(money);
	}

	public static void main(String[] args) {
		//code not written in here to circumvent static restrictions
		new Frame();
		
	}

}
