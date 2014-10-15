package burgertime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * @author Your team number and names here and in all your code files
 */
public class Main {
	private static final int WIDTH = 683;
	private static final int HEIGHT = 650;
	private static final int twoSeconds = 2000;
	// private static final Color BACKGROUND_COLOR = Color.black;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// TODO Add code here. Create any other needed methods in this class,
		// and any other classes that you need.

		final JFrame mainFrame = new JFrame();
		mainFrame.setSize(WIDTH, HEIGHT);
		mainFrame.setTitle("BurgerTime");
		mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		final BufferedImage titleImage = ImageIO.read(new File("res/top_title.jpg"));
		
		JPanel titlePanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(titleImage,0,0,null);
			}
		};
		titlePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT/2));
		
		
		
		JButton newGameButton = new JButton("Start New Game");
		newGameButton.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 12));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(newGameButton, BorderLayout.CENTER);
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		mainFrame.add(buttonPanel, BorderLayout.CENTER);
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				GameWorld game = new GameWorld(WIDTH, HEIGHT, Color.BLACK);
				game.gameFrame();
			}
		});

		JPanel instructPanel = new JPanel();
		instructPanel.setLayout(new GridLayout(6, 1));
		JLabel label1 = new JLabel("Instructions:");
		label1.setFont(new Font("Arial", Font.BOLD, 30));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label2 = new JLabel("Move hero with arrow keys");
		label2.setFont(new Font("Arial", Font.BOLD, 15));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label3 = new JLabel("<SPACEBAR> to use pepper");
		label3.setFont(new Font("Arial", Font.BOLD, 15));
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label4 = new JLabel("<U> to go up a level");
		label4.setFont(new Font("Arial", Font.BOLD, 15));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label5 = new JLabel("<D> to go down a level");
		label5.setFont(new Font("Arial", Font.BOLD, 15));
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label6 = new JLabel("<P> to Pause/Resume game");
		label6.setFont(new Font("Arial", Font.BOLD, 15));
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		instructPanel.add(label1);
		instructPanel.add(label2);
		instructPanel.add(label3);
		instructPanel.add(label4);
		instructPanel.add(label5);
		instructPanel.add(label6);
		mainFrame.add(instructPanel, BorderLayout.SOUTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public static double getWidth() {
		return WIDTH;
	}

	public static double getHeight() {
		return HEIGHT;
	}

	public static void gameOver() throws IOException {
		BufferedImage img = ImageIO.read(new File("res/gameOver.jpg"));
		final JFrame overFrame = new JFrame();
		overFrame.setSize(img.getWidth() + 100, img.getHeight() + 100);
		overFrame.add(new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				((Graphics2D) g).setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.ORANGE);
				g.drawString("Game Over", getWidth() / 2 - getWidth() / 5,
						getHeight() / 2 - getHeight() / 7);

			}
		});

		ActionListener newGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GameWorld.

				overFrame.setVisible(false);
				GameWorld.gameFrame.setVisible(true);
				CopyOfGameWorldEnvironment.setPaused(false);

			}
		};

		JButton newButton = new JButton();
		try {
			newButton.add(new JComponent() {
				BufferedImage img = ImageIO.read(new File("res/gameOver.jpg"));

				private ImageObserver obs;

				@Override
				protected void paintComponent(Graphics g) {
					g.drawImage(this.img, 0, 0,
							new Color(0, 0, 0, 0), this.obs);

				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		overFrame.add(newButton);

		newButton.addActionListener(newGame);
		overFrame.setVisible(true);
	}
	public static void deadOver() {
		final JFrame deadFrame = new JFrame();
		deadFrame.setSize(WIDTH, HEIGHT);
		deadFrame.add(new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.ORANGE);
				g.drawString("GG", getWidth() / 2, getHeight() - 2);
				
			}
		});
		
		ActionListener newGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GameWorld.
				
				deadFrame.setVisible(false);
				GameWorld.gameFrame.setVisible(true);
				CopyOfGameWorldEnvironment.setPaused(false);
				
			}
		};
		
		JButton newButton = new JButton();
		try {
			newButton.add(new JComponent() {
				BufferedImage img = ImageIO.read(new File("res/dead.jpg"));
				
				private ImageObserver obs;


				@Override
				protected void paintComponent(Graphics g) {
					g.drawImage(this.img, getWidth() / 4 , getHeight() / 4 , new Color(0,0,0,0), this.obs);
					
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		deadFrame.add(newButton);

		newButton.addActionListener(newGame);
		
		final Timer timer = new Timer(Main.twoSeconds, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deadFrame.setVisible(false);
			}
		});
		deadFrame.setVisible(true);
		timer.start();
	}

}
