/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Put here a description of what this class does.
 *
 * @author havensid.
 *         Created Feb 5, 2014.
 */
public class CopyOfGameWorldEnvironment implements Drawable, Temporal, Environment{
	private static final long UPDATE_INTERVAL_MS=10;
	private Shape background;
	private static boolean isPaused = false;
	public ArrayList<Rectangle2D> path;
	private ArrayList<Drawable> movingParts;
	final static List<Guard> guards = new ArrayList<Guard>();
	private final List<BurgTimeChar> characters = new ArrayList<BurgTimeChar>();
	public static double heroSpawnX = 15*24;
	public static double heroSpawnY = 12*24;
	private Hero hero = new Hero(this.map, heroSpawnX, heroSpawnY);
	private Environment map;
	private Color color;
	public static Level level;
	static List <Level> levels = new ArrayList<Level>();
	public static int levelChooser = 0;
	public static ArrayList<boolean[][]> boMapList = new ArrayList<boolean[][]>();
	
	public ArrayList<Burger> burgers;
	private final List<Point2D>corners = new ArrayList<Point2D>();
	private int frameWidth;
	private int frameHeight;
	public static int topCounter = 0;
	public static int letCounter = 0;
	public static int meatCounter = 0;
	
	public CopyOfGameWorldEnvironment(int width, int height){
		this.frameWidth = width;
		this.frameHeight = height;
		this.color = color;
		this.path = new ArrayList<Rectangle2D>();
		this.addPathway();
//		this.addBurgers();
		
		corners.add(0,new Point2D.Double(0, 0));
		corners.add(1,new Point2D.Double(Main.getWidth(), 0));
		corners.add(2,new Point2D.Double(0, Main.getHeight()));
		corners.add(3,new Point2D.Double(Main.getWidth(), Main.getHeight()));
		
		for(int  i=0; i<4; i++)
		{
			this.guards.add(i,new Guard(this.map,0,0));
			this.guards.get(i).setCenterPoint(this.level.getCorners(i+1));
			System.out.println("Guard centerpoint is" + this.guards.get(i).getCenterPoint());
			this.characters.add(i,this.guards.get(i));
		}
//		this.guards.add(0,new Guard(this.map,0,0));
//		this.guards.get(0).setCenterPoint(this.level.getCorners(1));
//		this.characters.addAll(this.guards);
		this.characters.add(this.hero);

		// Creates a separate "thread of execution" to inform this world of the
				// passage of time.
				Runnable tickTock = new Runnable() {
					@Override
					public void run() {
						try {
							while (true) {
								Thread.sleep(UPDATE_INTERVAL_MS);
								timePassed();
							}
						} catch (InterruptedException exception) {
							// Stop when interrupted
						}
					}
				};
				new Thread(tickTock).start();
				
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	private void addPathway() {
		levels.add(0, new Level("res/BurgerTimeLevel1.txt", 20, 28));
		levels.add(1, new Level("res/BurgerTimeLevel2.txt", 20, 28));
		levels.add(2, new Level("res/BurgerTimeLevel3.txt", 20, 28));
		levels.add(3, new Level("res/BurgerTimeLevel1pieces.txt", 20,28));
		levels.add(4, new Level("res/BurgerTimeLevel2Pieces.txt", 20,28));
		levels.add(5, new Level("res/BurgerTimeLevel3Pieces.txt", 20,28));
		boMapList.add(levels.get(0).getTerrainMap());
		boMapList.add(levels.get(1).getTerrainMap());
		boMapList.add(levels.get(2).getTerrainMap());
		boMapList.add(levels.get(3).getTerrainMap());
		boMapList.add(levels.get(4).getTerrainMap());
		boMapList.add(levels.get(5).getTerrainMap());
		this.level = levels.get(levelChooser);
	}
	

	
	private void addInteractives(){
		this.burgers = this.level.getBurger();
	}
	/* (non-Javadoc)
	 * @see burgertime.Temporal#timePassed()
	 */
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub
		if (!this.isPaused) {
			for (Temporal t : this.characters) {
				t.timePassed();
			}		
		}
	}
	
	/* (non-Javadoc)
	 * @see burgertime.Temporal#die()
	 */
	@Override
	public void die() {
		// ignored
	}
	/* (non-Javadoc)
	 * @see burgertime.Temporal#setIsPaused(boolean)
	 */
	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub
		this.isPaused = isPaused;
	}
	/* (non-Javadoc)
	 * @see burgertime.Temporal#getIsPaused()
	 */
	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return isPaused;
	}
	public static void setPaused(boolean Paused) {
		// TODO Auto-generated method stub
		CopyOfGameWorldEnvironment.isPaused = Paused;
	}
	/* (non-Javadoc)
	 * @see burgertime.Temporal#getIsPaused()
	 */
	public static boolean getPaused() {
		// TODO Auto-generated method stub
		return CopyOfGameWorldEnvironment.isPaused;
	}
	/* (non-Javadoc)
	 * @see burgertime.Drawable#getColor()
	 */
	@Override
	public Color getColor() {
		//this changes the color of all objects in environment to same color
		return this.color;
	}
	/* (non-Javadoc)
	 * @see burgertime.Drawable#getShape()
	 */
	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return this.path.get(0);
	}
	/* (non-Javadoc)
	 * @see burgertime.Environment#addGuard(burgertime.BurgTimeChar)
	 */
	@Override
	public void addGuard(Guard guard) {
		// TODO Auto-generated method stub
		//this.guardsToAdd.add(guard);
	}
	/* (non-Javadoc)
	 * @see burgertime.Environment#removeChar(burgertime.BurgTimeChar)
	 */
	@Override
	public void removeChar(Guard guard) {
		// TODO Auto-generated method stub
		//this.guardsToRemove.remove(guard);
	}
	
	/* (non-Javadoc)
	 * @see burgertime.Environment#isInsideWorldX(java.awt.geom.Point2D)
	 */
	@Override
	public boolean isInsideWorldX(Point2D point) {
		// TODO Auto-generated method stub
		return this.map.isInsideWorld(point);
	}
	/* (non-Javadoc)
	 * @see burgertime.Environment#isInsideWorldY(java.awt.geom.Point2D)
	 */
	@Override
	public boolean isInsideWorldY(Point2D point) {
		// TODO Auto-generated method stub
		return this.map.isInsideWorldY(point);
	}
	/* (non-Javadoc)
	 * @see burgertime.Environment#isInsideWorld(java.awt.geom.Point2D)
	 */
	@Override
	public boolean isInsideWorld(Point2D point) {
		// TODO Auto-generated method stub
		return this.map.isInsideWorld(point);
	}
	/* (non-Javadoc)
	 * @see burgertime.Environment#getDrawableParts()
	 */
	@Override
	public List<Drawable> getDrawableGuards() {
		// TODO Auto-generated method stub
		return new ArrayList<Drawable>(this.guards);
	}
	
	public Hero getHero()
	{
		return this.hero;
	}

	public static boolean levelCleared() {
		if(level != levels.get(levelChooser)) {
			return true;
		}
		return false;
	}
	
	public static int getLevelChooser() {
		return CopyOfGameWorldEnvironment.levelChooser;
	}
	
	public static void setLevel(int levelChooser)
	{
		CopyOfGameWorldEnvironment.level = CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser);
		
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getX()
	 */
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getY()
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#isPaused()
	 */
	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}
	

	

}
