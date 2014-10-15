/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.Timer;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author havensid. Created Feb 6, 2014.
 */
public class Guard extends BurgTimeChar {
	private double velocityX = 0.4;
	private double velocityY = 0.4;
	private double centerX;
	private double centerY;
	private Point2D centerPoint;
	private static Color color = Color.MAGENTA;
	private Color sprayedColor = Color.GRAY;
	final int twoSeconds = 2000;
	private CopyOfGameWorldEnvironment map;
	private int counter = 0;
	private int block = 24;
	private boolean ispaused;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param world
	 */
	public Guard(Environment world, double centerX, double centerY) {
		super(world);
		this.centerX = centerX;
		this.centerY = centerY ;
		setCenterPoint(this.centerPoint = new Point2D.Double(this.centerX,
				this.centerY));
		//this.tempX = (int) (this.centerY / 24.0);
		//this.tempY = (int) (this.centerX / 24.0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see burgertime.BurgTimeChar#updatePosition()
	 */

	@Override
	public void updatePosition() {
		int tempX = (int) (this.centerY / 24.0);
		int tempY = (int) (this.centerX / 24.0);
		this.counter += 1;
		 //System.out.println(tempY + " ," + tempX + "     " + Hero.getCoorX() + "," + Hero.getCoorY() + "   " + CopyOfGameWorldEnvironment.level.canGoRight(Hero.getCoorY(), Hero.getCoorX()) + "  " + CopyOfGameWorldEnvironment.level.canGoRight(tempX, tempY));
		if(this.counter % 60 == 0) {
			if(distanceX() > distanceY())
			{
				//If the x distance is larger than the y distance
				if(Hero.getCoorX() > tempY)
				{
					//If the hero is to the right of the guard
					if(!CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If the guard cannot go right
						if(Hero.getCoorY() > tempX)
						{
							//If hero is below the guard
							if(CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go down
								System.out.println("Can't go right, going down 1");
								this.centerY = this.centerY + this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go up
								System.out.println("Can't go right, going up 1");
								this.centerY = this.centerY - this.block;
							}
						}
						else if( Hero.getCoorY() < tempX)
						{
							//If hero is above the guard
							if(CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go up
								System.out.println("Can't go right, going up 2");
								this.centerY = this.centerY - this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go down
								System.out.println("Can't go right, going down 2");
								this.centerY = this.centerY + this.block;
							}
						}
					}
					else if (CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If guard can go to the right
						System.out.println("Going right 1");
						this.centerX = this.centerX + this.block;
					}
				}
				else if(Hero.getCoorX() < tempY)
				{
					//If hero is to the left of the guard
					if(!CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If guard cannot move to the left
						if(Hero.getCoorY() > tempX)
						{
							//If hero is below the guard
							if(CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go down
								System.out.println("Can't go left, going down 1");
								this.centerY = this.centerY + this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go up
								System.out.println("Can't go left, going up 1");
								this.centerY = this.centerY - this.block;
							}
						}
						else if( Hero.getCoorY() < tempX)
						{
							//If hero is above the guard
							if(CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go up
								System.out.println("Can't go left, going up 2");
								this.centerY = this.centerY - this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can go down
								System.out.println("Can't go left, going down 2");
								this.centerY = this.centerY + this.block;
							}
						}
					}
					else if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						System.out.println("Can go left");
						this.centerX = this.centerX - this.block;
					}
				}
				else if(Hero.getCoorY() < tempX &&CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser) )
				{
					//Go up
					System.out.println("Going up 2");
					this.centerY = this.centerY - this.block;
				}
				else if(Hero.getCoorX() > tempY &&CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser) )
				{
					//Go down
					System.out.println("Going down 2");
					this.centerY = this.centerY + this.block;
				}
			}
			else if(distanceY() >= distanceX())
			{
				//If the y distance is greater than the x distance
				if(Hero.getCoorY() > tempX)
				{
					//If the hero is below the guard
					if(!CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If guard cannot go down
						if(Hero.getCoorX() > tempY)
						{
							//If hero is to the right of the guard
							if(CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can move right
								System.out.println("Can't go down, going right 1");
								this.centerX = this.centerX + this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can move left
								System.out.println("Can't go down, going left 1");
								this.centerX = this.centerX - this.block;
							}
						}
						else if(Hero.getCoorX() < tempY)
						{
							//If hero is to the left of the guard
							 if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
								{
									//If guard can move left
									System.out.println("Can't go down, going left 2");
									this.centerX = this.centerX - this.block;
								}
							 else if(CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
								{
									//If guard can move right
									System.out.println("Can't go down, going right 2");
									this.centerX = this.centerX + this.block;
								}
							 else{
								 System.out.println("nothing");
							 }
						}
					}
					else if(CopyOfGameWorldEnvironment.level.canGoDown(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If can go down
						System.out.println("Going down 1");
						this.centerY = this.centerY + this.block;
					}
				}
				else if(Hero.getCoorY() < tempX)
				{
					//If hero is above the guard
					if(!CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//If hero cannot go up
						if(Hero.getCoorX() > tempY)
						{
							//If hero is to the right of the guard
							if(CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can move right
								System.out.println("Can't go up, going right 1");
								this.centerX = this.centerX + this.block;
							}
							else if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
							{
								//If guard can move left
								System.out.println("Can't go up, going left 1");
								this.centerX = this.centerX - this.block;
							}
						}
						else if(Hero.getCoorX() < tempY)
						{
							//If hero is to the left of the guard
							 if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
								{
									//If guard can move left
									System.out.println("Can't go up, going left 1");
									this.centerX = this.centerX - this.block;
								}
							 else if(CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
								{
									//If guard can move right
									System.out.println("Can't go up, going right 1");
									this.centerX = this.centerX + this.block;
								}
						}
					}
					else if(CopyOfGameWorldEnvironment.level.canGoUp(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser))
					{
						//Guard can move up
						System.out.println("Moving Up 1");
						this.centerY = this.centerY - this.block;
					}
				}
				else if(Hero.getCoorX() < tempY &&CopyOfGameWorldEnvironment.level.canGoLeft(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser) )
				{
					//Go left
					System.out.println("Going left 2");
					this.centerX = this.centerX - this.block;
				}
				else if(Hero.getCoorX() > tempY &&CopyOfGameWorldEnvironment.level.canGoRight(tempX,tempY, CopyOfGameWorldEnvironment.levelChooser) )
				{
					//Go right
					System.out.println("Going right 2");
					this.centerX = this.centerX + this.block;
				}
			}
	}
		System.out.println(distanceX() + " " + distanceY());
		setCenterPoint(this.centerPoint = new Point2D.Double(this.centerX,this.centerY));
		System.out.println(tempY + " " +  tempX);
	}

	@Override
	public void setCenterPoint(Point2D point) {
		super.setCenterPoint(point);
		this.centerX = point.getX();
		this.centerY = point.getY();
	}

	public void isSprayed() throws InterruptedException {
		final Timer timer = new Timer(this.twoSeconds, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setIsPaused(false);
				setColor(color);
			}
		});
		
		
		//setColor(this.sprayedColor);
		setIsPaused(true);
		this.ispaused = true;
		
		timer.start();

	}

	public double distanceX() {
		int tempY = (int) (this.centerX / 24.0);
		return Math.abs(Hero.getCoorX() - tempY);
	}

	public double distanceY() {
		int tempX = (int) (this.centerY / 24.0);
		return Math.abs(Hero.getCoorX() - tempX);
	}
	
	public static Color getGuardColor()
	{
		return color;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getX()
	 */
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return (int) this.centerX;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getY()
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) this.centerY;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#isPaused()
	 */
	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return this.isPaused;
	}
}
