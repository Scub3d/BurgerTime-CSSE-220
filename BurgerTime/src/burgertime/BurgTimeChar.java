/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;


/**
 * Base abstract class for characters in world.
 *
 * @author havensid.
 *         Created Feb 5, 2014.
 */
public abstract class BurgTimeChar implements Drawable, Temporal, Relocatable{
	protected static double height;
	protected static double width;
	private  Point2D centerPoint;
	protected Environment world;
	protected Environment map;
	protected boolean isPaused = false;
	private static Color color;
	
	
	public BurgTimeChar(Environment map) {
		this(map, new Point2D.Double(), color);
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public  BurgTimeChar(Environment map, Point2D centerPoint, Color color){
		BurgTimeChar.height  = 24;
		BurgTimeChar.width = 24;
		this.map=map;
		this.centerPoint = centerPoint;
		this.color = color;
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param centerPoint
	 */
	protected  void setCenterPoint(Point2D centerPoint) {
		this.centerPoint = centerPoint;
	}

	protected Environment getWorld() {
		return this.map;
	}
	
	//Temporal Interface
	
	@Override
	public void timePassed() {
		if(!isPaused)
		{
			updatePosition();
		}
	}

	@Override
	public void die() {
		//ignored
	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	public static Color getColour()
	{
		return color;
	}
	public static void setColor(Color newColor)
	{
		color = newColor;
	}
	
	//Drawable Interface
	@Override
	public Shape getShape() {
		double x = getCenterPoint().getX();
		double y = getCenterPoint().getY();
		return new Ellipse2D.Double(x,y, BurgTimeChar.width, BurgTimeChar.height);
	}
	
	// Relocatable interface

	/**
	 * Re-centers this ball at the given point.
	 * 
	 * @param point
	 */
	@Override
	public void moveTo(Point2D point) {
		this.centerPoint = point;
		setCenterPoint(this.centerPoint);
	}

	/**
	 * Returns the center point of this object.
	 * 
	 * @return the center point
	 */
	public Point2D getCenterPoint() {
		return this.centerPoint;
	}
	
	public abstract void updatePosition();
}
