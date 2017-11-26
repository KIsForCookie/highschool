import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class Tileset extends JPanel{
	
	private int px;
	private int py;
	private int width;
	private int height;
	
	private final int tilesize = 32;
	
	private String line;
	private String[] row;
	
	private char pinit;
	private char value;
	private char[][] map;
	private char[][] mapscroll;
	private char[][] pmap;
	private char[][] pmapscroll;
	private Image grass,player,wall;
	
	private BufferedImage tileset;
	
	private BufferedReader buffer;

	public Tileset(File TilesetFile){
		try{
		tileset = new BufferedImage(1280,960,BufferedImage.TYPE_INT_RGB);
		tileset = ImageIO.read(TilesetFile);
		
		player = tileset.getSubimage(28*tilesize, 6*tilesize, tilesize, tilesize);
		grass = tileset.getSubimage(5*tilesize, 23*tilesize, tilesize, tilesize);
		wall = tileset.getSubimage(0,22*tilesize,tilesize,tilesize);
		
		}catch(Exception error){
			System.err.println("Error loading tileset");
			System.out.println(error.getCause());
		}
	}
	
	public void setTown(File Map){
		try{
			buffer = new BufferedReader(new FileReader(Map));
			width = Integer.parseInt(buffer.readLine());
			height = Integer.parseInt(buffer.readLine());
			map = new char[height][width];
			mapscroll = new char[5][5];
			pmap = new char[height][width];
			pmapscroll = new char[5][5];
			
			for(int y = 0; y < height; y++){
				line = buffer.readLine();
				row = line.split(" ");
				for(int x = 0; x < width; x++){
					map[y][x] = row[x].charAt(0);
					if (map[y][x]=='3'){//wall id
						pmap[y][x] = map[y][x];
					}else{
						pmap[y][x] = '0';
					}
				}
				
				
			}
			
			
		}catch(Exception error){
			System.err.println("Error loading map");
			System.out.println(error.getCause());
		}
	}
	
	public void draw(){
		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try{
		for(int y = 0; y < 5; y++){
			for (int x = 0; x < 5; x++){
				value = mapscroll[y][x];
				if(value =='8'){
					g.drawImage(grass, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}else if(value == '3'){
					g.drawImage(wall, x*tilesize, y*tilesize, tilesize, tilesize, null);
				}
			}
			
			
		}
		
		g.drawImage(player, px*tilesize, py*tilesize, tilesize, tilesize, null);
		
		}catch(Exception error){//if any errors, shows message and shows cause of the error.
			System.err.println("failed to draw image");
			System.out.println(error.getCause());
		}
		
	}
	
	public void placePlayer(int X, int Y){
		
		pinit = pmap[Y][X];
		px = X;
		py = Y;
		pmap[py][px] ='9';//player id		
		pmapscroll[2][2] = '9';
		for (int y1 = 0; y1 < 5; y1++){
			for(int x1 = 0;x1 < 5; x1++){
				mapscroll[y1][x1] = map[py - 2 + y1][px - 2 + x1];
				if(mapscroll[y1][x1] == '3'){
				pmapscroll[y1][x1] = mapscroll[y1][x1];
			}else{
				pmapscroll[y1][x1] = 0;
			}
			}
		}
	
	}
	
	public void moveLeft(){
		
		if(pmapscroll[py][px-1] == '3'){
			
		}else{
			pmapscroll[py][px] = pinit;
			pinit = mapscroll[py][px-1];
			pmapscroll[py][px-1] = '9';
			px = px -1;
			repaint();
			
		}
		
		}
	
	
	public void moveRight(){
		
			if(pmapscroll[py][px+1] == '3'){
			
			}else{
				pmapscroll[py][px] = pinit;
				pinit = mapscroll[py][px+1];
				pmapscroll[py][px+1] = '9';
				px = px + 1;
				repaint();
			}
	}
	
	
	public void moveUp(){
		if(pmapscroll[py-1][px] == '3'){
			
		}else{
		
			pmap[py][px] = pinit;
			pinit = mapscroll[py-1][px];
			pmap[py-1][px] = '9';
			py = py - 1;
			repaint();
		}
	}
	
	public void moveDown(){ 
		if(pmapscroll[py+1][px] == '3'){
			
		}else{
			pmapscroll[py][px] = pinit;
			pinit = mapscroll[py+1][px];
			pmapscroll[py+1][px] = '9';
			py = py + 1;
			repaint();
		
		}
	}
	
	
}
