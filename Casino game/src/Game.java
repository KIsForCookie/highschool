import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;


public class Game implements KeyListener{

	private JFrame test;
	private File tilemap;
	private File town;
	private Tileset tile;
	
	public Game(){
	
		
		town = new File("res/map.txt");
		tilemap = new File("res/tileset.png");
		tile = new Tileset(tilemap);
		tile.setTown(town);
		test = new JFrame();
		test.setVisible(true);
		test.setSize(500,500);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(tile);
		test.addKeyListener(this);
		tile.placePlayer(3, 3);
		tile.draw();
		
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_LEFT){ //listens for which key is pressed and responds accordingly
			tile.moveLeft();
		}else if(key == KeyEvent.VK_RIGHT){
			tile.moveRight();
		}else if(key == KeyEvent.VK_UP){
			tile.moveUp();
		}else if(key == KeyEvent.VK_DOWN){
			tile.moveDown();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	

}
