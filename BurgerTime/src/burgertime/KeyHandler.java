/**
 * 
 */
package burgertime;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author maas.
 *         Created Feb 16, 2014.
 */
public class KeyHandler extends JPanel{
	private CopyOfGameWorldEnvironment map;
	private final Set<Integer> pressed = new HashSet<Integer>();
	
	public KeyHandler(){
		KeyListener listener = new KeyListener(){
			private CopyOfGameWorldEnvironment map;
			double block = 24;

			@Override
			public void keyPressed(KeyEvent e) {
				
				int tempX = (int) (Hero.getCenterY() / block);
				int tempY = (int) (Hero.getCenterX() / block);
				//System.out.println(tempX + ", " + tempY + ", " + CopyOfGameWorldEnvironment.level.checkIsValidTile(tempX, tempY));
				int key = e.getKeyCode();
				pressed.add(e.getKeyCode());
				
				char[][] map = this.map.level.readFile();
				boolean[][] boMap = new boolean[map.length][map[0].length];
				
				for(int i = 0; i < map.length; i++) 
				{
					for(int j = 0; j < map[0].length; j++) 
					{
							if(map[i][j] == '-' || map[i][j] == '=') 
							{
									boMap[i][j] = false;
							} 
							else 
							{
									boMap[i][j] = true;
							}
					}
				}
				double x = Hero.getCenterX();
				double y = Hero.getCenterY();
				
				
				if(pressed.size() > 1)
		    	{
		    		Hero.setVelocityX(0);
		    		Hero.setVelocityY(0);
		    	}
				else if(e.getKeyChar() == 'u' || e.getKeyChar() == 'U')
		    	{
		    		//Next level
					
					if(CopyOfGameWorldEnvironment.levelChooser == 2)
					{
						System.out.println("You are at the last level.");
					}
					else
					{
						System.out.println("Next Level");
						int levelChooser = CopyOfGameWorldEnvironment.getLevelChooser();
						levelChooser += 1;
						CopyOfGameWorldEnvironment.setLevel(levelChooser);
						CopyOfGameWorldEnvironment.levelChooser++;

						System.out.println(levelChooser);
						for(int i=0; i<4; i++)
						{
							CopyOfGameWorldEnvironment.guards.get(i).setCenterPoint(CopyOfGameWorldEnvironment.levels.get(levelChooser).getCorners(i+1));
						}
						Hero.setX(CopyOfGameWorldEnvironment.heroSpawnX);
						Hero.setY(CopyOfGameWorldEnvironment.heroSpawnY);
					}
		    	}
		    	else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
		    	{
		    		//Previous level
		    		
		    		if(CopyOfGameWorldEnvironment.levelChooser == 0)
					{
						System.out.println("You are at the first level.");
					}
					else
					{
						System.out.println("Previous Level");
						int levelChooser = CopyOfGameWorldEnvironment.getLevelChooser();
						levelChooser -= 1;
						CopyOfGameWorldEnvironment.setLevel(levelChooser);
						CopyOfGameWorldEnvironment.levelChooser--;
						

						
						for(int i=0; i<4; i++)
						{
							CopyOfGameWorldEnvironment.guards.get(i).setCenterPoint(CopyOfGameWorldEnvironment.levels.get(levelChooser).getCorners(i+1));
						}
						Hero.setX(CopyOfGameWorldEnvironment.heroSpawnX);
						Hero.setY(CopyOfGameWorldEnvironment.heroSpawnY);
					}
		    	}
		    	else if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P')
		    	{
		    		//Pause the Game
		    		boolean isPaused = CopyOfGameWorldEnvironment.getPaused();
		    		isPaused = !isPaused;
		    		CopyOfGameWorldEnvironment.setPaused(isPaused);
		    	}
				if(key == KeyEvent.VK_UP) 
				{
					//Move up
					if(CopyOfGameWorldEnvironment.level.canGoUp(tempX, tempY, CopyOfGameWorldEnvironment.levelChooser)) {
						Hero.setY(y - block);
					}
					else
					{
						Hero.setVelocityY(0);
					}
				}
				else if (key == KeyEvent.VK_LEFT ) 
		    	{
					//Move left
					if(CopyOfGameWorldEnvironment.level.canGoLeft(tempX, tempY, CopyOfGameWorldEnvironment.levelChooser)) {
						Hero.setX(x - block);
					}
					else
					{
						Hero.setVelocityX(0);
					}
		    	}
		    	else if (key== KeyEvent.VK_RIGHT ) 
		    	{
		    		//Move right
		    		System.out.println("Pressed Right");
		    		if(CopyOfGameWorldEnvironment.level.canGoRight(tempX, tempY, CopyOfGameWorldEnvironment.levelChooser)) {
		    			Hero.setX(x + block);
		    		}
		    		else
		    		{
		    			System.out.println("stopped");
		    			Hero.setVelocityX(0);
		    		}
		    	}
		    	else if (key == KeyEvent.VK_DOWN ) 
		    	{
		    		//Move down
		    		if(CopyOfGameWorldEnvironment.level.canGoDown(tempX, tempY, CopyOfGameWorldEnvironment.levelChooser))
		    		{
		    			Hero.setY(y + block);
		    		}
		    		else
		    		{
		    			Hero.setVelocityY(0);
		    		}
		    		
		    	}
		    	else if(pressed.size() > 1)
		    	{
		    		Hero.setVelocityX(0);
		    		Hero.setVelocityY(0);
		    	}
		    	else if (key == KeyEvent.VK_SPACE) 
		    	{
		    		//spray the pepper spray
		    		int pepperUsed = -1;
		    		int pepperLeft = StatusPanel.getPepper();
		    		if(pepperLeft == 0)
		    		{
		    			System.out.println("Out of pepperspray");
		    		}
		    		else
		    		{
		    			//Stun the guard that was sprayed and change his color.
		    			StatusPanel.setPepper(pepperUsed);
		    			for(Guard c: CopyOfGameWorldEnvironment.guards)
		    			{
		    				if(Hero.getCenterX() + Hero.getWidth()  / 2  + 2*24 >= c.getCenterPoint().getX() - Hero.getWidth() / 2 && Hero.getCenterX() - Hero.getWidth() / 2 - 2*24 <= c.getCenterPoint().getX() + Hero.getWidth() / 2) 
		    				{
		    					if(Hero.getCenterY() + Hero.getHeight() / 2 + 2*24 >= c.getCenterPoint().getY() - Hero.getHeight() / 2 && Hero.getCenterY()  - Hero.getHeight() / 2 - 2*24 <= c.getCenterPoint().getY() + Hero.getHeight() / 2 ) 
		    					{
		    						try {
										c.isSprayed();
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
		    						
		    					}
		    				}
		    			}
		    		}
	    		}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressed.remove(e.getKeyCode());
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP)
				{
					System.out.println("UP");
					Hero.setVelocityY(0);
					//System.out.println(Hero.getVelocityY());
				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		    	{
		    		//Stop left
		    		Hero.setVelocityX(0);
		    	}
		    	else if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		    	{
		    		//Stop right
		    		Hero.setVelocityX(0);
		    	}
		    	else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		    	{
		    		//Stop down
		    		Hero.setVelocityY(0);
		    	}
		    	else if (e.getKeyCode() == KeyEvent.VK_SPACE) 
		    	{
		    		//ignored
	    		}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//Ignored
			}
		};
		addKeyListener(listener);
		setFocusable(true);
		
	}

}
