import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class Tileset extends JPanel{
	
	private shop shop = new shop();
	
	private int store = 1;
	private int level = 0;
	private int levelchange;
	private int px = 0; //player's x and y position
	private int py = 0;
	private char initval;//tile the player is standing on
	
	
	private char[][]itemmap;
	private char[][] map; //actual tilemap
	private char[][] mapscale;
	private final int tilesize; //tilesize
	private int width; //width of the map
	private int height; //height of the map
	
	private BufferedImage tileset;
	
	
	
	private String line;
	private String[] row;
	private BufferedReader buffer;
	
	
	private char value;
	
	private Image nothing,dirtwall,dirtfloor,stonewall,stonefloor,
	door,upstairs,downstairs,grass,player,test;
	
	
	public Tileset(File TileSetFile){
		tilesize = 32;
		try{
		tileset = new BufferedImage(1280,960,BufferedImage.TYPE_INT_RGB);
		tileset = ImageIO.read(TileSetFile);
		
		dirtwall = tileset.getSubimage(14*tilesize, 25*tilesize, tilesize, tilesize);
		dirtfloor = tileset.getSubimage(13*tilesize, 25*tilesize, tilesize, tilesize);
		stonewall = tileset.getSubimage(0,22*tilesize,tilesize,tilesize);
		stonefloor = tileset.getSubimage(1*tilesize,21*tilesize, tilesize, tilesize);
		nothing = tileset.getSubimage(29*tilesize, 20*tilesize, tilesize, tilesize);
		door = tileset.getSubimage(4*tilesize, 21*tilesize, tilesize, tilesize);
		upstairs = tileset.getSubimage(11*tilesize, 21*tilesize, tilesize, tilesize);
		downstairs = tileset.getSubimage(12*tilesize, 21*tilesize, tilesize, tilesize);
		player = tileset.getSubimage(28*tilesize, 6*tilesize, tilesize, tilesize);
		grass = tileset.getSubimage(5*tilesize, 23*tilesize, tilesize, tilesize);
		
		tileset = ImageIO.read(new File("H:/a.png"));
		test = tileset.getSubimage(0,0, tilesize, tilesize);
		
		}catch(Exception error){ //if any error, shows error and shows cause of the error.
			System.err.println("Cannot load tileset");
			System.out.println(error.getCause());
		}
	}
	
	public void setTileset(File TileMapFile){
		try{
		buffer = new BufferedReader(new FileReader(TileMapFile)); //reads from the file FileName and buffers it to make it more efficient
		width = Integer.parseInt(buffer.readLine()); //gets width from the file
		height = Integer.parseInt(buffer.readLine()); //gets height from the file
		mapscale = new char[8][8];
		map = new char[height][width];
		itemmap = new char[height][width];

		for(int y = 0; y < height; y++){ //reads every line from the tilemap
			line = buffer.readLine();
			row = line.split(" "); //rets rid of all the spaces inbetween the values and stores the values into an array

			for(int x = 0; x < width; x++){
				map[y][x] = row[x].charAt(0);
				if(map[y][x] == '7'){
					itemmap[y][x] = '7';
				}else if(map[y][x] == '6'){
					itemmap[y][x] = '6';
				}else if(map[y][x] == '5'){
					itemmap[y][x] = '5';
				}
			}
		}
		
		}catch(Exception error){//catches exceptions, displays error and shows cause of the error
			System.err.println("failed to load tilemap");
			System.out.println(error.getCause());
		};
	}
	
	public void draw(){//activates the paintcomponent() method
		repaint();
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); //allows me to use the paint component from the superclass.
		try{ //catches any expetions when drawing the map
		for(int y = 0; y < height; y++){ //checks every row and every collum in each row
			for (int x = 0; x < width; x++){
				value = map[y][x]; //places the value of the array at the spot into a value
				if(value == '0'){ //depending on value, draws a tile at that specific spot.
					g.drawImage(nothing, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '1'){
					g.drawImage(dirtwall, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '2'){
					g.drawImage(dirtfloor, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '3'){
					g.drawImage(stonewall, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '4'){
					g.drawImage(stonefloor, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '5'){
					g.drawImage(door, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '6'){
					g.drawImage(upstairs, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '7'){
					g.drawImage(downstairs, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '8'){
					g.drawImage(grass, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '9'){
					g.drawImage(test, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}
				
				
			}
			
		}
		
		}catch(Exception error){//if any errors, shows message and shows cause of the error.
			System.err.println("failed to draw image");
			System.out.println(error.getCause());
		}
		
	}
	
	public void placePlayer(int X, int Y){ //places the player at specified point on the map
		initval = map[Y][X]; //saves the tile value that the player is occupying
		px = X; //player x value
		py = Y; //player y value
		map[py][px] ='9'; //sets the tile value at that array = 9
		repaint();//redraws the map
	}
	
	public void moveLeft(){ //moves left
		if((map[py][px-1] == '1')||(map[py][px-1] == '3')){
			
		}else {
			map[py][px] = initval;
			initval = map[py][px-1];
			map[py][px-1] = '9';
			px = px -1;
			repaint();
		}
		
		}
	
	
	public void moveRight(){//move right
		if((map[py][px+1] == '1')||(map[py][px+1] =='3')){
			
		}else {
			map[py][px] = initval;
			initval = map[py][px+1];
			map[py][px+1] = '9';
			px = px + 1;
			repaint();
		}
	}
	
	
	public void moveUp(){//move up
		if((map[py-1][px] == '1')||(map[py-1][px] == '3')){
			
		}else{
			map[py][px] = initval;
			initval = map[py-1][px];
			map[py-1][px] = '9';
			py = py - 1;
			repaint();
		}
	}
	
	public void moveDown(){ //move down
		if((map[py+1][px] == '1')||(map[py+1][px]== '3')){
			
		}else{
			map[py][px] = initval;
			initval = map[py+1][px];
			map[py+1][px] = '9';
			py = py + 1;
			repaint();
		}
	
	}
	
	public int interact(){ //interacting with environment
		if(itemmap[py][px] == '7'){//downstairs
			level = level + 1;
			levelchange = 1;
			return levelchange;
			
		}else if(itemmap[py][px] == '6'){//upstairs
			level = level - 1;
			levelchange = 2;
			return levelchange;
		}else if((itemmap[py][px] == '5')&&(level == 0)){ //doors on ground level
			if(level == 0){
				store = 3;
				map[13][11] = '1';
				repaint();
				return store;

			}else{
				store = 4;
				return store;
			}
		}else{
			levelchange = 0;
			return levelchange;	
	}
	}
	public int getpyup(){ //getting the stair location 1 floor up
		for(int y = 0; y < height; y++){ 
			for (int x = 0; x < width; x++){
				value = map[y][x]; 
				if(value == '7'){
					py = y;
				}
			}
		}
		return py;
	}
	
	public int getpxup(){ //getting the stair location 1 floor up
		for(int y = 0; y < height; y++){ 
			for (int x = 0; x < width; x++){
				value = map[y][x]; 
				if(value == '7'){
					px = x;
				}
			}
		}
		return px;
	}
	
	public int getpydown(){ //getting the stair location 1 floor down
		for(int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				value = map[y][x];
				if(value == '6'){
					py = y;
				}
			}
		}
	return py;
	}
	
	public int getpxdown(){ //getting the stair location 1 floor down
		for(int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				value = map[y][x]; 
				if(value == '6'){
					px = x;
				}
			}
		}
		return px;
	}
	
	
	
	}
	
	