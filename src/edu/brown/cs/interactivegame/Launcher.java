package edu.brown.cs.interactivegame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;

/**
 * Launcher
 * Code adapted from: <a href="http://www.edu4java.com/en/game/game1.html">Java game tutorial</a>.
 * Creates the JFrame and adds animation movements to JPanel in window.
 *
 * @author Stephanie Olaiya
 * Date: 9/20/24
 */
public class Launcher extends JPanel {

	public static void main(String[] args) throws InterruptedException {
		JFrame window = new JFrame("Interactive Bouncing Balls");
		Launcher animation = new Launcher();
		window.add(animation);
		window.setUndecorated(true);
		window.setShape(new Ellipse2D.Double(0, 0, 950, 500));
		window.setSize(1000, 500);

		JButton start = new JButton("Start");
		start.addActionListener(e -> animation.initializeAnimation());
		JButton reset = new JButton("Reset");
		reset.addActionListener(e -> animation.initializeAnimation());

		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPnl.add(start);
		btnPnl.add(reset);

		window.add(btnPnl, BorderLayout.SOUTH);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		while (true) {
//			animation.move(); // make all items in launcher animation move
//			animation.repaint(); // repaint after changes
//			Thread.sleep(10);
//		}
	}

	/**
	 * List of balls in animation.
	 */
	private List<Ball> balls = new ArrayList<>();

	/**
	 * Constructor for game launcher.
	 */
	public Launcher() {
//		this.executorService = Executors.newFixedThreadPool(20); // Create a thread pool
		MouseListener listener = new ClickListener(this.balls, this);
		this.addMouseListener(listener); // add mouse listener to the frame.

	}

	/**
	 * Returns the list of balls that are currently in the animation launcher.
	 *
	 * @return the list of balls that are currently in the animation launcher.
	 */
	public List<Ball> getBalls() {
		return this.balls;
	}

	/**
	 * Reinitialized animation so that only one ball is moving around screen.
	 */
	public void initializeAnimation() {
		long startTime = System.nanoTime(); // Start timing

		this.balls.clear();
		this.initializeBallList(30);
		for (Ball ball : this.balls) {
			new Thread(ball).start(); // Start a new thread for the ball
		}
		this.repaint(); // not sure to remove or not

		long endTime = System.nanoTime(); // End timing
		long duration = endTime - startTime; // Calculate duration in nanoseconds

		System.out.println("Time to initialize animation: " + duration / 1_000_000 + " ms"); // Convert to ms
	}

	public void addBall(int x, int y) {
		Ball ball = new Ball(x, y, this);
		this.balls.add(ball);
		new Thread(ball).start(); // Start a new thread for the ball
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // cleans the screen after repaint
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (Ball ball : this.balls) {
			ball.paint(g2d);
		}
	}

	private void initializeBallList(int x) {
		Random random = new Random();
		for (int i = 0; i < x; i++) {
			// Create a new object with randomized variables
			Ball newBall = new Ball(random.nextInt(100), random.nextInt(100), this);
			this.balls.add(newBall);
		}
	}

//	/**
//	 * Calls move function for all balls currently in the launcher.
//	 */
//	private void move() {
//		for (Ball ball : this.balls) {
//			ball.move();
//		}
//	}


}
