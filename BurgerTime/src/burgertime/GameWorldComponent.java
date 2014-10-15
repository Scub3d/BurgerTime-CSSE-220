/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author havensid. Created Feb 6, 2014.
 */
public class GameWorldComponent extends JComponent implements Temporal {
	private static final int FRAMES_PER_SECOND = 30;
	private static final long INTERVAL_MS = 1000 / FRAMES_PER_SECOND;
	private static boolean[][] boMap;
	private CopyOfGameWorldEnvironment map;
	public Hero hero;
	private ArrayList<Rectangle2D> position;
	private boolean hasShownNullErrorMessage = false;
	private ImageObserver imageObserver;
	
	

	// private Level level;

	public GameWorldComponent(CopyOfGameWorldEnvironment map) {
		this.map = map;
		// this.level = new Level();
		// this.level.loadLevel();

		// this.hero = new Hero();
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.drawBurger(g2, this.map.level.getBurger());
		if (CopyOfGameWorldEnvironment.levelCleared()) {
			clearLevel(g2);
			this.drawMap(g2, CopyOfGameWorldEnvironment.levels
					.get(CopyOfGameWorldEnvironment.levelChooser));
			this.drawMap(g2, CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser+3));

		} else {
			this.drawMap(g2, CopyOfGameWorldEnvironment.level);
			this.drawMap(g2, CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser+3));
		}
		

		List<Drawable> drawableGuards = this.map.getDrawableGuards();
		for (Drawable d : drawableGuards) {
			drawDrawable(g2, d);
		}
		
		
		drawHero(g2, this.map.getHero());
		
		for(int z=0;z<topBun.size();z++)
		{
			topBun.get(z).topBunUpdatePosition(z);

			if(topBunBo.get(z))
			{
				System.out.println("Burger top falling");
				//topBun.get(z).setY(topBun.get(z).getY()+24);
				
				topBun.get(z).topBunFalls(z);
			}
			drawTop(g2, topBun.get(z), topBunX.get(z),topBunY.get(z));
		}
		for(int y = 0;y<lettuce.size();y++)
		{
			lettuce.get(y).lettuceUpdatePosition(y);
			if(lettuceBo.get(y))
			{
				System.out.println("Lettuce falling");
				lettuce.get(y).lettuceFalls(y);
			}
			drawLettuce(g2, lettuce.get(y),lettuceX.get(y),lettuceY.get(y));
		}
		for(int m = 0;m<meat.size();m++)
		{
			meat.get(m).meatUpdatePosition(m);
			if(meatBo.get(m))
			{
				System.out.println("Lettuce falling");
				meat.get(m).meatFalls(m);
			}
			drawMeat(g2, meat.get(m),meatX.get(m),meatY.get(m));
		}
		
	}


	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param g2
	 * @param burger
	 * @param integer
	 * @param integer2
	 */
	private void drawMeat(Graphics2D g2, Burger meat, Integer x,
			Integer y) {
		// TODO Auto-generated method stub
		g2.setColor(meat.getColor());
		g2.fillRect(x,y,24,14);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param g2
	 * @param burger
	 * @param integer
	 * @param integer2
	 */
	private void drawLettuce(Graphics2D g2, Burger lettuce, Integer x,
			Integer y) {
		// TODO Auto-generated method stub
		g2.setColor(lettuce.getColor());
		g2.fillRect(x,y,24,14);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param g2
	 * @param top
	 */
	private void drawTop(Graphics2D g2, Burger top, int x, int y) {
		// TODO Auto-generated method stub
		g2.setColor(top.getColor());
		
		g2.fillRect(x, y,24,14);
		
	}

	private void drawHero(Graphics2D g2, Hero hero) {
		g2.drawImage(readImage("res/flapper.png"), (int) Hero.getCenterX(), (int) Hero.getCenterY(), new Color(0,0,0,0), this.imageObserver);
		//g2.setColor(hero.getColor());
		//Shape shape = hero.getShape();
		//g2.fill(shape);
	}

	/**
	 * This helper method draws the given drawable object on the given graphics
	 * area.
	 * 
	 * @param g2
	 *            the graphics area on which to draw
	 * @param d
	 *            the drawable object
	 */
	private void drawDrawable(Graphics2D g2, Drawable d) {
		if(!d.isPaused()) {
		g2.drawImage(readImage("res/flapperKiller.png"), d.getX(), d.getY(), new Color(0,0,0,0), this.imageObserver);
		} else {
			g2.drawImage(readImage("res/cloud.png"), d.getX(), d.getY(), new Color(0,0,0,0), this.imageObserver);
		}
//		g2.setColor(Guard.getGuardColor());
//		Shape shape = d.getShape();
//		g2.fill(shape);
	}

	/**
	 * Displays an error message explaining that the Ball's color/shape was null
	 * so it could not be drawn. Show the message only once per component.
	 */
	private void showNullPointerMessage(String nullAttribute, Drawable d) {
		if (!this.hasShownNullErrorMessage) {
			this.hasShownNullErrorMessage = true;
			String message = "I could not draw this Drawable object of type "
					+ d.getClass().getName() + " because its " + nullAttribute
					+ " is null.\n";
			JOptionPane.showMessageDialog(null, message,
					"Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void drawPathway(Graphics2D g2, Rectangle2D pathSegment) {
		g2.setColor(this.map.getColor());
		g2.fill(pathSegment);
	}

	private static char[] objs = { '-', 'H', '_', '~', '=', 't', 'l', 'm', 'b','p' };
	private Color[] colors = { Color.BLACK, Color.RED, Color.CYAN,
			Color.BLUE, Color.PINK, Color.WHITE};
	 static ArrayList<Burger> topBun = new ArrayList<Burger>();
	static ArrayList<Boolean> topBunBo = new ArrayList<Boolean>();
	 static ArrayList<Integer> topBunX = new ArrayList<Integer>();
	 static ArrayList<Integer> topBunY = new ArrayList<Integer>();
	 
	static ArrayList<Burger> lettuce = new ArrayList<Burger>();
	static ArrayList<Boolean>lettuceBo = new ArrayList<Boolean>();
	 static ArrayList<Integer> lettuceX = new ArrayList<Integer>();
	 static ArrayList<Integer> lettuceY = new ArrayList<Integer>();
	
	static ArrayList<Burger> meat = new ArrayList<Burger>();
	static ArrayList<Boolean>meatBo = new ArrayList<Boolean>();
	 static ArrayList<Integer> meatX = new ArrayList<Integer>();
	 static ArrayList<Integer> meatY = new ArrayList<Integer>();
	 
	 
	private static ArrayList<Burger>botBun = new ArrayList<Burger>();
	private static int topIterator = 0;
	private static int letIterator = 0;
	private static int meatIterator = 0;
	private static int botIterator = 0;
	
	public static void fillBurger(){
		System.out.println("burger filled");
		char[][] map = CopyOfGameWorldEnvironment.levels.get(3).readFile();
		Point[][] coords = CopyOfGameWorldEnvironment.level.setCoors();
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				//for(int k = 0; k < GameWorldComponent.objs.length; k++) {
					if(map[i][j]=='t'){
						
						topBun.add(topIterator,new Burger(24*j,24*i,'t'));
						topBunX.add(topIterator,24*j);
						topBunY.add(topIterator,24*i);
						topBunBo.add(topIterator,false);
						topIterator ++;
						System.out.println(coords[i][j].x + " " +  (int) (coords[i][j].y));
						System.out.println(topIterator);
					}else if(map[i][j]=='l'){
						lettuce.add(letIterator,new Burger(24*j, 24*j,'l'));
						lettuceX.add(letIterator,24*j);
						lettuceY.add(letIterator,24*i);
						lettuceBo.add(letIterator,false);
						letIterator ++;
						//System.out.println(letIterator);
					}else if(map[i][j]=='m'){
						meat.add(meatIterator, new Burger(coords[i][j].x, (int) (coords[i][j].y),'m'));
						meatX.add(meatIterator,24*j);
						meatY.add(meatIterator,24*i);
						meatBo.add(meatIterator,false);
						meatIterator ++;
					}else if(map[i][j]=='b'){
						botBun.add(botIterator, new Burger(coords[i][j].x, (int) (coords[i][j].y),'b'));
						botBun.get(botIterator).setX(coords[i][j].x);
						botBun.get(botIterator).setY((int) (coords[i][j].y));
						botIterator ++;
					}
				//}
			}
		}
		System.out.println(topBun + " " + lettuce  + " "+ meat + " " + botBun );
	}
	

	private void drawMap(Graphics2D g2, Level level) {
		char[][] map = level.readFile();
		GameWorldComponent.boMap = new boolean[map.length][map[0].length];
		//g2.setBackground(Color.BLACK);
		Point[][] coords = level.setCoors();
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				//for(int k = 0; k < this.objs.length; k++) {
					//if(map[i][j] == this.objs[k]) {
						if(map[i][j] == '_') {
							g2.setColor(this.colors[0]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24, 14);
							g2.setColor(this.colors[2]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y+14), 24, 10);
						} else if(map[i][j] == '~') {
							g2.setColor(this.colors[1]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24, 14);
							g2.setColor(this.colors[2]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y+14), 24, 10);
						} else if(map[i][j] == '=') {
							g2.setColor(this.colors[0]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24, 14);
							g2.setColor(this.colors[4]);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y+14), 24, 10);
						} else if(map[i][j]=='H'){
							g2.setColor(Color.RED);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24, 24);
						}else if(map[i][j]=='t'){
							//nothing
						}else if(map[i][j]=='l'){
							//g2.setColor(Color.GREEN);
							//g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24,14);
						}else if(map[i][j]=='m'){
//							g2.setColor(Color.PINK);
//							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24,14);
						}else if(map[i][j]=='b'){
							g2.setColor(Color.ORANGE);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24,14);
						}else if(map[i][j]=='p'){
							PowerUp pow = new PowerUp(coords[i][j].x, (int) coords[i][j].y, Color.WHITE);
							pow.updatePosition();
							if(!pow.getTaken()) {
								g2.setColor(this.colors[5]);
								Ellipse2D iceCream = new Ellipse2D.Double(coords[i][j].x, (int) (coords[i][j].y ), 10, 10);
								g2.fill(iceCream);
							}
							//g2.drawOval(coords[i][j].x, (int) (coords[i][j].y ), 5, 5);
						}
						else if(map[i][j]=='-')
						{
							g2.setColor(Color.BLACK);
							g2.fillRect(coords[i][j].x, (int) (coords[i][j].y), 24, 24);
						}
						if(map[i][j] == '-' || map[i][j] == '=') {
							GameWorldComponent.boMap[i][j] = false;
							//g2.setColor(Color.MAGENTA);
							Ellipse2D coorPoint2 = new Ellipse2D.Double(j*24,i*24,24,24);
							//g2.fill(coorPoint2);
						} else {
							GameWorldComponent.boMap[i][j] = true;
							//g2.setColor(Color.WHITE);
							Ellipse2D coorPoint = new Ellipse2D.Double(j*24,i*24,24,24);
							//g2.fill(coorPoint);
						}
					}
				//}
			//}
		}
	}

	public void clearLevel(Graphics2D g2) {
		g2.clearRect(0, 0, getWidth(), getHeight());
		//g2.setBackground(Color.BLACK);
	}

	 private void drawBurger(Graphics2D g2, ArrayList<Burger> burgers) {
		 ArrayList<Burger> burgersToDraw = burgers;
		 for(int k = 0; k<burgersToDraw.size(); k++){
			 this.drawDrawable(g2, burgersToDraw.get(k));
		 }
	 }

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.Temporal#timePassed()
	 */
	// @Override
	// public void timePassed() {
	// if (!this.hero.pepperDeployed) {
	// if (this.hero.contactWithGuard(this.hero.getHeroCorners(0, 0))) {
	// this.hero.die();
	// }
	// }
	// if (this.hero.isDead()) {
	// this.resetCharacters();
	// this.hero.heroState = false;
	// }
	//
	// }
	//
	// public void resetCharacters() {
	// this.hero.resetPosition();
	// for (int i = 0; i < guard.length; i++) {
	// ((Guard) this.guard[i]).resetPosition();
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.Temporal#die()
	 */
	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.Temporal#setIsPaused(boolean)
	 */
	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.Temporal#getIsPaused()
	 */
	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.Temporal#timePassed()
	 */
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub

	}
	
	public Image readImage(String url) {
		File file = new File(url);
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
