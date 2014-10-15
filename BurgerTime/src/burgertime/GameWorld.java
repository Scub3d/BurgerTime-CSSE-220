/**
 * 
 */
package burgertime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * Game World that contains the map (w/ characters and burgers) and update statuses.
 *
 * @author havensid.
 *         Created Feb 5, 2014.
 */
public class GameWorld implements Temporal, Environment {
	private static final long INTERVAL_MS = 10;
	private static int width;
	private static int height;
//	private final Color backgroundColor;
//	private final Shape background;
	private CopyOfGameWorldEnvironment map;
	private GameWorldComponent draw;
	static final long UPDATE_INTERVAL_MS = 10;
	private static boolean isPaused = false;
	//final Temporal temporalObj = isPaused;
	protected static JFrame gameFrame;
	
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param width
	 * @param height
	 * @param color 
	 */
	public GameWorld(int width, int height, Color color) {
		// TODO Auto-generated constructor stub
		this.width=683;
		this.height=650;
		
		this.map = new CopyOfGameWorldEnvironment(this.width, this.height);
		this.draw = new GameWorldComponent(this.map);
		
		
		
		Runnable passingTime = new Runnable(){
			@Override
			public void run(){
				try{
					while(true){
						Thread.sleep(INTERVAL_MS);
						timePassed();
//						StatusPanel.updatePanel();
					}
				}catch (InterruptedException exception){
					// Stop
				}
			}
		};
		new Thread(passingTime).start();
	}


	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void gameFrame() {
		JPanel actionPanel = new JPanel();
		//this.keyHandler = new KeyboardHandler(actionPanel, this.draw.hero);
		gameFrame = new JFrame();
		
		gameFrame.add(actionPanel);
		gameFrame.setTitle("BurgerTime");
		gameFrame.setSize(this.width, this.height);
		GameWorldComponent.fillBurger();
		
		KeyHandler keyhandler = new KeyHandler();
		gameFrame.add(keyhandler);
		gameFrame.getContentPane().setBackground(Color.black);
		gameFrame.getContentPane().add(this.draw);
		
		StatusPanel stats = new StatusPanel(width, height);
		gameFrame.getContentPane().add(stats, BorderLayout.SOUTH);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
	@Override
	public boolean isInsideWorldX(Point2D point) {
		return point.getX() >= 0 && point.getX() <= this.width;
	}

	@Override
	public boolean isInsideWorldY(Point2D point) {
		return point.getY() >= 0 && point.getY() <= this.height;
	}

	@Override
	public boolean isInsideWorld(Point2D point) {
		return isInsideWorldX(point) && isInsideWorldY(point);
	}


	/* (non-Javadoc)
	 * @see burgertime.Temporal#timePassed()
	 */
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub
		//ignored
	}


	/* (non-Javadoc)
	 * @see burgertime.Temporal#die()
	 */
	@Override
	public void die() {
		//ignored
	}


	/* (non-Javadoc)
	 * @see burgertime.Temporal#setIsPaused(boolean)
	 */
	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub
		this.isPaused = isPaused;
	}
	public static void setPaused(boolean paused) {
		// TODO Auto-generated method stub
		isPaused = paused;
	}

	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return isPaused;
	}
	public static boolean isItPaused(){
		return isPaused;
	}


	/* (non-Javadoc)
	 * @see burgertime.Environment#getDrawableParts()
	 */
	@Override
	public List<Drawable> getDrawableGuards() {
		// TODO Auto-generated method stub
			//ignored
		return null;
	}

	/* (non-Javadoc)
	 * @see burgertime.Environment#addGuard(burgertime.Guard)
	 */
	@Override
	public void addGuard(Guard guard) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see burgertime.Environment#removeChar(burgertime.Guard)
	 */
	@Override
	public void removeChar(Guard guard) {
		// TODO Auto-generated method stub
		
	}
	public static double getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	public static double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}


	/**
	 * TODO Put here a description of what this method does.
	 * @throws IOException 
	 *
	 */
	public static void gameOver() throws IOException{
		// TODO Auto-generated method stub
		
		gameFrame.setVisible(false);
		Main.gameOver();
	}
	public static void deadOver(){
		// TODO Auto-generated method stub
		
		Main.deadOver();
	}
	

}
