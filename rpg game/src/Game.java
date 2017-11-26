import java.awt.*;
import javax.swing.*;

import Items.Armour3;
import Items.Weapon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


public class Game implements ActionListener, KeyListener{
	
	
	private PlayerCharacter player;
	
	private Object item;
	private Object[] items;
	
	private JFrame intro;
	private JFrame game;
	private JFrame store;
	
	private int py;
	private int px;
	private int level = 0;
	private int levelchange = 0;
	private int choice;
	private int steps = 0;
	
	private int storechoice;
	private int storesteps = 0;
	private GraphicsDevice device;
	
	private Armour3 bronzehelmet;
	private Armour3 bronzechestplate;
	private Armour3 bronzegauntlets;
	private Armour3 bronzeboots;
	private Armour3 bronzeshield;
	private Armour3 ironhelmet;
	private Armour3 ironchestplate;
	private Armour3 irongauntlets;
	private Armour3 ironboots;
	private Armour3 ironshield;
	private Armour3 steelhelmet;
	private Armour3 steelchestplate;
	private Armour3 steelgauntlets;
	private Armour3 steelboots;
	private Armour3 steelshield;
	
	private Weapon woodenbroadsword;
	private Weapon woodenwaraxe;
	private Weapon woodenmace;
	private Weapon woodenspear;
	private Weapon woodenclaymore;
	private Weapon woodenbattleaxe;
	private Weapon woodenwarhammer;
	private Weapon woodenquarterstaff;
	private Weapon woodendagger;
	private Weapon bronzebroadsword;
	private Weapon bronzewaraxe;
	private Weapon bronzemace;
	private Weapon bronzespear;
	private Weapon bronzeclaymore;
	private Weapon bronzebattleaxe;
	private Weapon bronzewarhammer;
	private Weapon bronzequarterstaff;
	private Weapon bronzedagger;
	private Weapon ironbroadsword;
	private Weapon ironwaraxe;
	private Weapon ironmace;
	private Weapon ironspear;
	private Weapon ironclaymore;
	private Weapon ironbattleaxe;
	private Weapon ironwarhammer;
	private Weapon ironquarterstaff;
	private Weapon irondagger;
	
	private JTextArea text;
	private JTextField input;
	private JButton btninput;
	
	
	private JButton btnnext;
	private JButton btnplay;
	private JButton btninstructions;
	private JButton btncredits;
	private JButton btnlore;
	private JButton btnback;
	
	private JLabel lbltitle;
	private JLabel picbackground;
	private JLabel lblinfo;
	
	private JTextField txtinfo;
	
	private JTextArea txtoptions;
	private JTextArea txtinstructions;
	private JTextArea txtcredits;
	private JTextArea txtlore;
	
	private ImageIcon pic;
	
	private Tileset tile;
	
	private File[] levels;
	
	private File tiles;
	private File town;
	private File level1;
	
	public Game(){
		
		
		intro = new JFrame();
		intro.setTitle("THE ADVENTURES OF M00T THE TERRIBLE");//sets title
		intro.setSize(500,400); //sets size
		intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //causes program to close when x button is clicked
		intro.setResizable(false); //cannot resize window
		intro.setLayout(null); //disabled default layout
		intro.setVisible(true);
		
		store = new JFrame();
		store.setTitle("THE STORE");//sets title
		store.setSize(500,400); //sets size
		store.setResizable(false); //cannot resize window
		store.setLayout(null); //disabled default layout
		store.setVisible(false);
		
		tiles = new File("res/tileset.png");
		town = new File("res/town.txt");
		level1 = new File("res/gen.txt");
		items = new Object[42];
		
		levels = new File[10];
		levels[0] = town;
		levels[1] = level1;
		
		tile = new Tileset(tiles);
		tile.setTileset(levels[0]);
		
		
		try{
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();//gets the local graphics settings and finds the default screen monitor
		}catch(HeadlessException error){};
		
		player = new PlayerCharacter();
		
		/*
		 bronzehelmet = new Armour3(0,0);
		 bronzechestplate = new Armour3(0,1);
		 bronzegauntlets = new Armour3(0,2);
		 bronzeboots = new Armour3(0,3);
		 bronzeshield = new Armour3(0,4);
		 ironhelmet = new Armour3(1,0);
		 ironchestplate = new Armour3(1,1);
		 irongauntlets = new Armour3(1,2);
		 ironboots = new Armour3(1,3);
		 ironshield = new Armour3(1,4);
		 steelhelmet = new Armour3(2,0);
		 steelchestplate = new Armour3(2,1);
		 steelgauntlets = new Armour3(2,2);
		 steelboots = new Armour3(2,3);
		 steelshield = new Armour3(2,4);
		 
		 woodenbroadsword = new Weapon(0,0);
		 woodenwaraxe = new Weapon(0,1);
		 woodenmace = new Weapon(0,2);
		 woodenspear = new Weapon(0,3);
		 woodenclaymore = new Weapon(0,4);
		 woodenbattleaxe = new Weapon(0,5);
		 woodenwarhammer = new Weapon(0,6);
		 woodenquarterstaff = new Weapon(0,7);
		 woodendagger = new Weapon(0,8);
		 bronzebroadsword = new Weapon(1,0);
		 bronzewaraxe = new Weapon(1,1);
		 bronzemace = new Weapon(1,2);
		 bronzespear = new Weapon(1,3);
		 bronzeclaymore = new Weapon(1,4);
		 bronzebattleaxe = new Weapon(1,5);
		 bronzewarhammer = new Weapon(1,6);
		 bronzequarterstaff = new Weapon(1,7);
		 bronzedagger = new Weapon(1,8);
		 ironbroadsword = new Weapon(2,0);
		 ironwaraxe = new Weapon(2,1);
		 ironmace = new Weapon(2,2);
		 ironspear = new Weapon(2,3);
		 ironclaymore = new Weapon(2,4);
		 ironbattleaxe = new Weapon(2,5);
		 ironwarhammer = new Weapon(2,6);
		 ironquarterstaff = new Weapon(2,7);
		 irondagger = new Weapon(2,8);
	
*/
		 

		input = new JTextField();
		btninput = new JButton("Submit");
		text = new JTextArea();
		text.setEditable(false);
		
		lblinfo = new JLabel();
		lbltitle = new JLabel("THE ADVENTURES OF M00T THE TERRIBLE");
		
		txtinfo = new JTextField();
		
		txtoptions = new JTextArea();
		txtinstructions = new JTextArea();
		txtcredits = new JTextArea();
		txtlore = new JTextArea();
		
		btnnext = new JButton("Next");
		btnplay = new JButton("PLAY");
		btninstructions = new JButton("INSTRUCTIONS");
		btncredits = new JButton("CREDITS");
		btnlore = new JButton("LORE");
		btnback = new JButton("BACK");
		
		pic = new ImageIcon("res/background.jpg");
		picbackground = new JLabel(pic);
		
		txtoptions.setVisible(false);
		txtinfo.setVisible(false);
		lblinfo.setVisible(false);
		btnnext.setVisible(false);
		txtcredits.setVisible(false);
		txtinstructions.setVisible(false);
		txtlore.setVisible(false);
		txtcredits.setEditable(false);
		txtinstructions.setEditable(false);
		txtlore.setEditable(false);
		btnback.setVisible(false);
		txtoptions.setEditable(false);
		
		txtcredits.setText("CREDITS\nAlex Kim\nNick Purdye");
		txtinstructions.setText("Use the numpad arrow keys to move.\nMore commands will be given later");
		txtlore.setText("Many years ago, <PlayerName> was living an idyllic lifestyle in \nthe land of Forchu when the Dark Lord MLP " +
				"destroyed his home \nvillage of /b/roville during his reign of terror. The overseer, \nknown only as m00t, cast " +
				"the Dark Lord out of Forchun after a \nlong and epic battle (approx. 12 months). However, in recent \nyears, m00t " +
				"has become reclusive and MLP threatens to return to \nForchun. It is up to an unlikely hero (read: YOU) to stop " +
				"him \nbefore he resumes his reign of terror.");
		intro.add(tile);
		intro.add(txtoptions);
		intro.add(txtinfo);
		intro.add(txtlore);
		intro.add(btnlore);
		intro.add(btnplay);
		intro.add(btninstructions);
		intro.add(btncredits);
		intro.add(txtinstructions);
		intro.add(txtcredits);
		intro.add(lbltitle);
		intro.add(lblinfo);
		intro.add(picbackground);
		intro.add(btnback);
		intro.add(btnnext);
		
		store.add(input);
		store.add(btninput);
		store.add(text);
		
		text.setBounds(0,0,500,300);
		input.setBounds(0,300,300,50);
		btninput.setBounds(350,300,100,50);
		
		btninput.addActionListener(this);
		btnplay.addActionListener(this);
		btninstructions.addActionListener(this);
		btncredits.addActionListener(this);
		btnlore.addActionListener(this);
		btnback.addActionListener(this);
		btnnext.addActionListener(this);
		
		txtlore.setOpaque(false);
		txtcredits.setOpaque(false);
		txtinstructions.setOpaque(false);
		
		txtlore.setForeground(Color.white);
		txtinstructions.setForeground(Color.white);
		txtcredits.setForeground(Color.white);
		
		Font font = new Font("Lucida Calligraphy",Font.BOLD,12);
		
		
		txtcredits.setFont(font);
		txtinstructions.setFont(font);
		txtlore.setFont(font);
		lbltitle.setFont(lbltitle.getFont().deriveFont((float)20));
		lbltitle.setForeground(Color.white);
		Dimension titlesize = lbltitle.getPreferredSize();
		
		
		lbltitle.setBounds(35,20,titlesize.width,titlesize.height);	
		btnplay.setBounds(175,150,120,20);
		btnlore.setBounds(175,235,120,20);
		btninstructions.setBounds(175,180,120,20);
		btncredits.setBounds(175, 210, 120, 20);
		btnback.setBounds(355,335,120,20);
		btnnext.setBounds(150,120,75,20);
		picbackground.setBounds(0,0,500,400);
		lblinfo.setBounds(20,40,150,20);
		txtinfo.setBounds(20,80,150,20);
		txtcredits.setBounds(0,0,500,300);
		txtinstructions.setBounds(0,0,500,300);
		txtlore.setBounds(0,0,500,300);
		}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnplay){
			picbackground.setVisible(false);
			btnplay.setVisible(false);
			btninstructions.setVisible(false);
			btncredits.setVisible(false);
			btnlore.setVisible(false);
			btnback.setVisible(false);
			lbltitle.setVisible(false);
			btnnext.setVisible(true);
			txtinfo.setVisible(true);
			lblinfo.setVisible(true);
			steps = 0;
			lblinfo.setText("What is your character's name?");
			Dimension infosize = lblinfo.getPreferredSize();
			lblinfo.setBounds(20,40,infosize.width,infosize.height);
			
		}
		
		if(e.getSource() == btninstructions){
			btnback.setVisible(true);
			btnplay.setVisible(false);
			btnlore.setVisible(false);
			btninstructions.setVisible(false);
			btncredits.setVisible(false);
			lbltitle.setVisible(false);
			txtinstructions.setVisible(true);
		}
		
		if(e.getSource() == btncredits){
			btnback.setVisible(true);
			btnplay.setVisible(false);
			btnlore.setVisible(false);
			btninstructions.setVisible(false);
			btncredits.setVisible(false);
			lbltitle.setVisible(false);
			txtcredits.setVisible(true);
		}
		
		if(e.getSource() == btnlore){
			btnback.setVisible(true);
			btnplay.setVisible(false);
			btninstructions.setVisible(false);
			btncredits.setVisible(false);
			lbltitle.setVisible(false);
			btnlore.setVisible(false);
			txtlore.setVisible(true);
		}
		
		if(e.getSource() == btnback){
			btnplay.setVisible(true);
			lbltitle.setVisible(true);
			btncredits.setVisible(true);
			btninstructions.setVisible(true);
			btnlore.setVisible(true);
			txtcredits.setVisible(false);
			txtinstructions.setVisible(false);
			txtlore.setVisible(false);
			btnback.setVisible(false);
		}
		
		if(e.getSource() == btnnext){
			/*switch(steps){
			case 0: 
				player.setName(txtinfo.getText());
				if (player.getName().equals("")){
					player.setName("Joe Random");
				}
				steps = steps + 1;
				txtinfo.setText("");
				lblinfo.setText("What role would you like to play as?");
				Dimension infosize = lblinfo.getPreferredSize();
				txtoptions.setVisible(true);
				txtoptions.setText("type 1 for knight \ntype 2 for barbarian \ntype" +
						" 3 for monk \ntype 4 for rogue \ntype 5 for samurai" +
						" \ntype 6 for tourist");
				Dimension optionsize = txtoptions.getPreferredSize();
				txtoptions.setBounds(250,75,optionsize.width,optionsize.height);
				lblinfo.setBounds(20,40,infosize.width,infosize.height);
				break;
			case 1:
				try{
					choice = Integer.parseInt(txtinfo.getText());
					}catch(NumberFormatException error){}
		
				switch(choice){
				case 1:
					player.setRole("knight");
					steps = steps + 1;
					break;
				case 2:
					player.setRole("barbarian");
					steps = steps + 1;
					break;
				case 3:
					player.setRole("monk");
					steps = steps + 1;
					break;
				case 4:
					player.setRole("rogue");
					steps = steps + 1;
					break;
				case 5:
					player.setRole("samurai");
					steps = steps + 1;
					break;
				case 6:
					player.setRole("tourist");
					steps = steps + 1;
					break;
				default:
					txtinfo.setText("Wrong input! Try agian");
					break;
				}
				if(steps == 2){
					lblinfo.setText("What race would you like to be?");
					txtoptions.setText("type 1 for human \ntype 2 for elf \ntype 3 for dwarf \ntype 4 for half-troll \ntype 5 for kolbold");
					txtinfo.setText("");
					choice = 0;
					break;
				}
			case 2:
				try{
					choice = Integer.parseInt(txtinfo.getText());
					}catch(NumberFormatException error){}
				switch(choice){
				case 1:
					player.setRace("human");
					steps = steps + 1;
					break;
				case 2:
					player.setRace("elf");
					steps = steps + 1;
					break;
				case 3:
					player.setRace("dwarf");
					steps = steps + 1;
					break;
				case 4:
					player.setRace("half-troll");
					steps = steps + 1;
					break;
				case 5:
					player.setRace("kolbold");
					steps = steps + 1;
					break;
				default:
					txtinfo.setText("Wrong input! Try agian");
					break;
				}
				if(steps == 3){
					lblinfo.setText("What gender would you like to be?");
					txtoptions.setText("type 1 for male \ntype 2 for female \ntype 3 for neutered");
					txtinfo.setText("");
					choice = 0;
					break;
				}	
			case 3:
				try{
					choice = Integer.parseInt(txtinfo.getText());
					}catch(NumberFormatException error){}
				switch(choice){
				case 1:
					player.setGender("male");
					steps = steps + 1;
					break;
				case 2:
					player.setGender("female");
					steps = steps + 1;
					break;
				case 3:
					player.setGender("neutered");
					steps = steps + 1;
					break;
				default:
					txtinfo.setText("Wrong input! Try agian");
					break;
				}
				if(steps == 4){
					txtinfo.setText("");
					lblinfo.setText("Finally what alignment would you like to be?");
					infosize = lblinfo.getPreferredSize();
					lblinfo.setBounds(20,40,infosize.width,infosize.height);
					txtoptions.setText("type 1 for lawful \ntype 2 for neutral \ntype 3 for chaotic");
					choice = 0;
					break;
				}
			case 4:
				try{
					choice = Integer.parseInt(txtinfo.getText());
					}catch(NumberFormatException error){}
				switch(choice){
				case 1:
					player.Alignment(5);
					steps = steps + 1;
					break;
				case 2:
					player.Alignment(0);
					steps = steps + 1;
					break;
				case 3:
					player.Alignment(-5);
					steps = steps + 1;
					break;
				default:
					txtinfo.setText("Wrong input! Try agian");
					break;
				}
				
				if(steps == 5){*/
					lblinfo.setVisible(false);
					txtinfo.setVisible(false);
					txtoptions.setVisible(false);
					btnnext.setVisible(false);
					
					/*System.out.println(player.getName());
					System.out.println(player.getRole());
					System.out.println(player.getRace());
					System.out.println(player.getGender());
					System.out.println(player.getAlignment());*/
					
					
					game = new JFrame("the game");
					game.setSize(500,400);
					game.setVisible(true);
					game.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					intro.setVisible(false);
					game.add(tile);
					game.addKeyListener(this); //adds a key listener to the game jframe
					tile.draw();
					tile.placePlayer(3,3);
					device.setFullScreenWindow(game);//sets the graphics settings so that the game JFrame is the fullscreen.
					
				}
		//}
			if(e.getSource() == btninput){
				try{
				switch(storesteps){
				case 0:
					storechoice = Integer.parseInt(input.getText());
					if(storechoice == 1){
						storesteps = storesteps + 1;
						text.setText("We have many fine weapons for sale! what kind of weapon would you like?" +
								"\npress 1 for sword" +
								"\npress 2 for waraxe" +
								"\npress 3 for mace" +
								"\npress 4 for spear" +
								"]npress 5 for claymore" +
								"\npress 6 for battle axe" +
								"\npress 7 for quarterstaff" +
								"\npress 8 for dagger");
						storechoice = 0;
						input.setText("");
						break;
					}else if(storechoice == 2){
						storesteps = storesteps + 1;
						text.setText("We have many fine armours for sale! what kind of armour would you like?" +
								"\npress 1 for helmet" +
								"\npress 2 for chestplate" +
								"\npress 3 for gauntlets" +
								"\npress 4 for boots" +
								"\npress 5 for shield");
						storechoice = 0;
						input.setText("");
						break;
					
						
					}
					
				
				
				
				
				}
				
				}catch(Exception error){}
			}
	}
		
		
	//}

	public void keyPressed(KeyEvent k) { //key listener
	int key = k.getKeyCode();
	
	if(key == KeyEvent.VK_LEFT){ //listens for which key is pressed and responds accordingly
		tile.moveLeft();
	}else if(key == KeyEvent.VK_RIGHT){
		tile.moveRight();
	}else if(key == KeyEvent.VK_UP){
		tile.moveUp();
	}else if(key == KeyEvent.VK_DOWN){
		tile.moveDown();
	}else if(key == KeyEvent.VK_ENTER){ //if the player interacts with his environment
		levelchange = tile.interact();
		if(levelchange == 1){
			tile.setTileset(levels[level+1]);
			level = level + 1;
			tile.draw();
			py = tile.getpydown();
			px = tile.getpxdown();
			tile.placePlayer(px,py);
			levelchange = 0;
		}else if(levelchange == 2){
			tile.setTileset(levels[level - 1]);
			level = level -1;
			tile.draw();
			py = tile.getpyup();
			px = tile.getpxup();
			tile.placePlayer(px,py);
			levelchange = 0;
		}else if (levelchange == 3){
			store.setVisible(true);
			text.setText("Welcome to the store! What would you like to buy?" +
					"\npress 1 for weapons" +
					"\npress 2 for armour");
			
		}
		
		
	}
	
		
	}

	
	public void keyReleased(KeyEvent k) {
		
	}

	
	public void keyTyped(KeyEvent k) {
		
	}
	
	
	}
