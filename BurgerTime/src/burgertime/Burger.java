/**
 * 
 */
package burgertime;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

import javax.swing.Timer;

/**
 * TODO Put here a description of what this class does.
 *
 * @author havensid.
 *         Created Feb 18, 2014.
 */
public class Burger implements Drawable{
	protected int width=24;
	protected int height=14;
	private Color color;
	private static  int xCoor;
	private static int yCoor;
	private boolean isStepOn = false;
	private boolean isMoving  = false;
	protected Rectangle2D.Double burgerPiece;
	private char part;
	private int counter = 0;
	private int block = 24;
	private final int twoSeconds = 2000;
	int randomCorner;

//	private int xCenter;
//	private int yCenter;

	
	public Burger(int col, int row, char type){
		//this.burgerPiece = new Rectangle2D.Double(col, row, this.width,this.height);
		Burger.xCoor = col;
		Burger.yCoor = row;
		//System.out.println(xCoor + ", " + yCoor);
		//  System.out.println(yCoor);
		//System.out.println(xCoor + " " + yCoor);
		//setCenterPoint(Burger.xCoor,Burger.yCoor);
		
		this.part = type;
		if(this.part =='t'||this.part=='b'){
			this.color = Color.ORANGE;
		}else if(this.part == 'l'){
			this.color = Color.GREEN;
		}else if(this.part=='m'){
			this.color= new Color(160,82,45);
		}
//		setCenters(row, col, this.width, this.height);
	}
	
	
//	private void setCenters(int row, int col, int width2, int height2) {
//		// TODO Auto-generated method stub
//		
//	}

	public void topBunUpdatePosition(int index){
		//CopyOfGameWorldEnvironment.countIt++;
		System.out.println((Hero.getCenterX() + " " +  xCoor + " "  +  Hero.getCenterY() + " " + yCoor));
		if(GameWorldComponent.topBunX.get(index) == (Hero.getCenterX()) &&  GameWorldComponent.topBunY.get(index) == (Hero.getCenterY()))
		{
			//System.out.println("stepped on");
			//setIsSteppedOn(true);
			GameWorldComponent.topBunBo.set(index, true);
		}
		else
		{
			//setIsSteppedOn(false);
		}
	}
	public void lettuceUpdatePosition(int index){
		//CopyOfGameWorldEnvironment.countIt++;
		System.out.println((Hero.getCenterX() + " " +  xCoor + " "  +  Hero.getCenterY() + " " + yCoor));
		if(GameWorldComponent.lettuceX.get(index) == (Hero.getCenterX()) &   GameWorldComponent.lettuceY.get(index) == (Hero.getCenterY()))
		{
			System.out.println("stepped on");
			//setIsSteppedOn(true);
			GameWorldComponent.lettuceBo.set(index, true);
		}
		else
		{
			//setIsSteppedOn(false);
		}
	}
	
	public void meatUpdatePosition(int index){
		//CopyOfGameWorldEnvironment.countIt++;
		System.out.println((Hero.getCenterX() + " " +  xCoor + " "  +  Hero.getCenterY() + " " + yCoor));
		if(GameWorldComponent.meatX.get(index) == (Hero.getCenterX()) &   GameWorldComponent.meatY.get(index) == (Hero.getCenterY()))
		{
			System.out.println("stepped on");
			//setIsSteppedOn(true);
			GameWorldComponent.meatBo.set(index, true);
		}
		else
		{
			//setIsSteppedOn(false);
		}
	}
	public void topBunFalls(int index){
		//this.counter+=1;
		System.out.println(this.counter);
		CopyOfGameWorldEnvironment.topCounter++;
		if(CopyOfGameWorldEnvironment.topCounter % 50 == 0 && GameWorldComponent.topBunBo.get(index))
		{
			System.out.println("falling");
			System.out.println(Burger.yCoor);
			GameWorldComponent.topBunY.set(index,GameWorldComponent.topBunY.get(index) + 24);
			System.out.println(Burger.yCoor);
			for(final Guard g:CopyOfGameWorldEnvironment.guards)
			{
				if(GameWorldComponent.topBunX.get(index) == g.getCenterPoint().getX() && GameWorldComponent.topBunY.get(index) == g.getCenterPoint().getY())
				{
					System.out.println("ded");
					double random = Math.random();
					
					if(random >= 0 && random <=.25)
					{
						this.randomCorner = 1;
					}
					else if(random > .25 && random<=.50)
					{
						this.randomCorner = 2;
					}
					else if(random > .50 && random<=.75)
					{
						this.randomCorner = 3;
					}
					else if(random > .75 && random<=1.00)
					{
						this.randomCorner = 4;
					}
					
					final Timer timer = new Timer(this.twoSeconds, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							g.setCenterPoint(CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser).getCorners(randomCorner));
							g.setIsPaused(false);
						}
					});
					g.setIsPaused(true);
					StatusPanel.setScore(50);
					timer.start();
				}
			}
			for(int xIndex = 0;xIndex<GameWorldComponent.lettuceX.size();xIndex++)
			{
				System.out.println("lettuce number: " + xIndex + "  " + GameWorldComponent.lettuceX.get(xIndex) + " " + GameWorldComponent.topBunX.get(index));
				if(GameWorldComponent.lettuceX.get(xIndex).equals(GameWorldComponent.topBunX.get(index)))
				{
					System.out.println(GameWorldComponent.lettuceY.get(xIndex) + " " + GameWorldComponent.topBunY.get(index));
					if(GameWorldComponent.topBunY.get(index).equals(GameWorldComponent.lettuceY.get(xIndex)))
					{
						System.out.println("STOP THERE'S LETTUCE" + index);
						GameWorldComponent.topBunBo.set(index,false);
						GameWorldComponent.lettuceBo.set(xIndex,true);
						GameWorldComponent.lettuce.get(xIndex).lettuceFalls(xIndex);
					}
				}
			}
			if(GameWorldComponent.lettuceY.get(index) == 17*block)
			{
				GameWorldComponent.topBunBo.set(index,false);
			}
		}
	}
	
	public void lettuceFalls(int index){
		System.out.println("lettuce fall");
		CopyOfGameWorldEnvironment.letCounter++;
		if(CopyOfGameWorldEnvironment.letCounter % 40 == 0 && GameWorldComponent.lettuceBo.get(index))
		{

			GameWorldComponent.lettuceY.set(index,GameWorldComponent.lettuceY.get(index) + block);
			for(final Guard g:CopyOfGameWorldEnvironment.guards)
			{
				if(GameWorldComponent.lettuceX.get(index) == g.getCenterPoint().getX() && GameWorldComponent.lettuceY.get(index) == g.getCenterPoint().getY())
				{
					System.out.println("ded2");
					double random = Math.random();
					
					if(random >= 0 && random <=.25)
					{
						this.randomCorner = 1;
					}
					else if(random > .25 && random<=.50)
					{
						this.randomCorner = 2;
					}
					else if(random > .50 && random<=.75)
					{
						this.randomCorner = 3;
					}
					else if(random > .75 && random<=1.00)
					{
						this.randomCorner = 4;
					}
					
					final Timer timer = new Timer(this.twoSeconds, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							g.setCenterPoint(CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser).getCorners(randomCorner));
							g.setIsPaused(false);
						}
					});
					g.setIsPaused(true);
					StatusPanel.setScore(50);
					timer.start();
				}
			}
			if(GameWorldComponent.lettuceY.get(index) == 18*block){
				System.out.println("STOP FALLING");
				GameWorldComponent.lettuceBo.set(index,false);
			}
			for(int xIndex = 0;xIndex<GameWorldComponent.meatX.size();xIndex++)
			{
				System.out.println("lettuce number: " + xIndex + "  " + GameWorldComponent.lettuceX.get(xIndex) + " " + GameWorldComponent.topBunX.get(index));
				if(GameWorldComponent.lettuceX.get(index).equals(GameWorldComponent.meatX.get(xIndex)))
				{
					System.out.println(GameWorldComponent.lettuceY.get(xIndex) + " " + GameWorldComponent.topBunY.get(index));
					if(GameWorldComponent.lettuceY.get(index).equals(GameWorldComponent.meatY.get(xIndex)))
					{
						System.out.println("STOP THERE'S MEAT" + index);
						GameWorldComponent.lettuceBo.set(index,false);
						GameWorldComponent.meatBo.set(xIndex,true);
						GameWorldComponent.meat.get(xIndex).lettuceFalls(xIndex);
					}
				}
			}
		}
	}
	
	public void meatFalls(int index){
		System.out.println("lettuce fall");
		CopyOfGameWorldEnvironment.meatCounter++;
		if(CopyOfGameWorldEnvironment.meatCounter % 30 == 0 && GameWorldComponent.meatBo.get(index))
		{

			GameWorldComponent.meatY.set(index,GameWorldComponent.meatY.get(index) + block);
			for(final Guard g:CopyOfGameWorldEnvironment.guards)
			{
				if(GameWorldComponent.meatX.get(index) == g.getCenterPoint().getX() && GameWorldComponent.meatY.get(index) ==  g.getCenterPoint().getY())
				{
					System.out.println("ded3");
					double random = Math.random();
					
					if(random >= 0 && random <=.25)
					{
						this.randomCorner = 1;
					}
					else if(random > .25 && random<=.50)
					{
						this.randomCorner = 2;
					}
					else if(random > .50 && random<=.75)
					{
						this.randomCorner = 3;
					}
					else if(random > .75 && random<=1.00)
					{
						this.randomCorner = 4;
					}
					
					final Timer timer = new Timer(this.twoSeconds, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							g.setCenterPoint(CopyOfGameWorldEnvironment.levels.get(CopyOfGameWorldEnvironment.levelChooser).getCorners(randomCorner));
							g.setIsPaused(false);
						}
					});
					g.setIsPaused(true);
					StatusPanel.setScore(50);
					timer.start();
				}
			}
			if(GameWorldComponent.meatY.get(index) == 19*block){
				System.out.println("STOP FALLING");
				GameWorldComponent.meatBo.set(index,false);
			}
			for(int xIndex = 0;xIndex<GameWorldComponent.topBunX.size();xIndex++)
			{
				System.out.println("lettuce number: " + xIndex + "  " + GameWorldComponent.lettuceX.get(xIndex) + " " + GameWorldComponent.topBunX.get(index));
				if(GameWorldComponent.lettuceX.get(index).equals(GameWorldComponent.topBunX.get(xIndex)))
				{
					System.out.println(GameWorldComponent.lettuceY.get(xIndex) + " " + GameWorldComponent.topBunY.get(index));
					if(GameWorldComponent.topBunY.get(index) == GameWorldComponent.lettuceY.get(xIndex) )
					{
						System.out.println("STOP THERE'S BUN" + index);
						GameWorldComponent.topBunBo.set(index,true);
					}
				}
			}
			if(GameWorldComponent.lettuceY.get(index) == 19*block){
				System.out.println("STOP FALLING");
				GameWorldComponent.lettuceBo.set(index,false);
			}
		}
	}
	
	public int getX(){
		return Burger.xCoor;
	}
	
	public int getY(){
		return Burger.yCoor;
	}
	
	public char getType(){
		return this.part;
	}
	
	public void setIsSteppedOn(boolean steppedOn){
		this.isStepOn = steppedOn;
	}
	public boolean getIsSteppedOn(){
		return this.isStepOn;
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
		return this.burgerPiece;
	}


	/**
	 * TODO Put here a description of what this method does.
	 * @param bp 
	 * @return 
	 *
	 */
	public Burger depress(Burger bp) {
		//Burger depressedBurg = new Burger(bp.getX(),bp.getY()+10,bp.getType());
		System.out.println("newRectangle");
		return null;
		
	}


	/* (non-Javadoc)
	 * @see burgertime.Drawable#isPaused()
	 */
	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setX(int x){
		Burger.xCoor = x;
	}
	public void setY(int y){
		Burger.yCoor = y;
	}
}
