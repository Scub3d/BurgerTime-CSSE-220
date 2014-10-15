/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * TODO Put here a description of what this class does.
 *
 * @author solyss.
 *         Created Feb 19, 2014.
 */
public class PowerUp implements Drawable{
	private  Color color;
	private static  double y;
	private static  double x;
	private  double width = 5;
	private  double height = 5;
	private static final int thirtySeconds = 30000;
	private static boolean taken = false;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param x
	 * @param y
	 * @param color
	 */
	public PowerUp(double x,double y,Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public static void updatePosition()
	{

		if(x <= Hero.getCenterX() + Hero.getWidth() - 10 && x >= Hero.getCenterX() - Hero.getWidth() + 10
				&& y <= Hero.getCenterY() + Hero.getHeight() - 10 && y >= Hero.getCenterY() - Hero.getHeight() + 10
				&& !taken)
		{
			final Timer timer = new Timer(thirtySeconds, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					
					setTaken(false);
				}
			});
			
			StatusPanel.setScore(100);
			StatusPanel.setPepper(1);
			setTaken(true);
			
			timer.start();
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param b
	 */
	private boolean setVisible(boolean b) {
		return b;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getColor()
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	/* (non-Javadoc)
	 * @see burgertime.Drawable#getShape()
	 */
	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void setTaken(boolean t){
		taken = t;
	}
	public static boolean getTaken(){
		return taken;
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

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	
}
