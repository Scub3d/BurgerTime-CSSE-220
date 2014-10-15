/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * TODO Put here a description of what this class does.
 *
 * @author havensid.
 *         Created Feb 5, 2014.
 */
public class Hero extends BurgTimeChar {
	 private static double velocityX = 0;
	 private static double velocityY = 0;
	 private static double centerX;
	 private static double centerY;
	 private Point2D centerPoint;
	 Color color = Color.GREEN;
	private static int coorX ;
	private static int coorY ;
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param world
	 */
	public Hero(Environment world,double centerX, double centerY) {
		super(world);
		Hero.centerX = centerX;
		Hero.centerY = centerY;
		setCenterPoint(this.centerPoint = new Point2D.Double(Hero.centerX,Hero.centerY));
	}
	
	/* (non-Javadoc)
	 * @see burgertime.Drawab le#getColor()
	 */
	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}
	
	/* (non-Javadoc)
	 * @see burgertime.BurgTimeChar#updatePosition()
	 */
	
	@Override
	public void updatePosition() {
		int cornerCount = 1;
		for(Guard c: CopyOfGameWorldEnvironment.guards)
		{
			//System.out.println("looping");
			if(!c.getIsPaused())
			{
				if(Hero.centerX + this.width / 2 >= c.getCenterPoint().getX() - this.width / 2 && Hero.centerX - this.width / 2 <= c.getCenterPoint().getX() + this.width / 2) 
				{
					if(Hero.centerY + this.height / 2 >= c.getCenterPoint().getY() - this.height / 2 && Hero.centerY  - this.height / 2 <= c.getCenterPoint().getY() + this.height / 2 ) 
					{
					
						System.out.println("You died");
						
						Hero.centerX = CopyOfGameWorldEnvironment.heroSpawnX;
						Hero.centerY = CopyOfGameWorldEnvironment.heroSpawnY;
						setCenterPoint(this.centerPoint = new Point2D.Double(Hero.centerX,Hero.centerY));
						StatusPanel.setLives(1);
						int lifeCount = StatusPanel.getLives();			
					
						if(lifeCount == 0)
						{
							System.out.println("Game Over");
							CopyOfGameWorldEnvironment.setPaused(true);
							StatusPanel.gameReset();
							try {
								GameWorld.gameOver();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else{
							GameWorld.deadOver();
							try {
								Thread.sleep(2000);
							}	 catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						}
					
						for(int i=0; i<4; i++)
						{
							CopyOfGameWorldEnvironment.guards.get(i).setCenterPoint(CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser).getCorners(i+1));
						}
					}
				}
			}
		}

		Hero.coorX = (int) (Hero.centerX/24); 
		Hero.coorY = (int) (Hero.centerY/24);
		setCenterPoint(this.centerPoint = new Point2D.Double(Hero.centerX,Hero.centerY));
		Hero.onBurger();
	}
	
	
	public static void onBurger(){
		ArrayList<Burger> pieces = CopyOfGameWorldEnvironment.level.getBurger();
		for(Burger bp: pieces){
			if(Hero.centerX== bp.getX()&&Hero.centerY==bp.getY()){
				System.out.println("PPPPPPPIIIIIIIIEEEEEEEEECCCCCCCCCCEEEEEEEEEE DDDDDDDDDEEEEEEEEPPPPPPPRRRRREEEEESSSSSSSSSSSSSSSSEEEEEEDDDDD");
				bp=bp.depress(bp);
			}
		}
		
	}
	
	public static double getCenterY() {
		return centerY;
	}
	public static double getCenterX() {
		return centerX;
	}
	public static void setVelocityY(double velocity) {
		Hero.velocityY = velocity;
	}
	public static void setVelocityX(double velocity) {
		Hero.velocityX = velocity;
	}
	public Point2D getCenterPoint() {
		return this.centerPoint;
	}
	public static double getWidth() {
		return width;
	}
	public static double getHeight() {
		return height;
	}
	public static void setX(double x)
	{
		centerX = x;
	}public static void setY(double y)
	{
		centerY = y;
	}
	public static double getCoorY() {
		return coorY;
	}
	public static double getCoorX() {
		return coorX;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getX()
	 */
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return (int) Hero.centerX;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getY()
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) Hero.centerY;
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
