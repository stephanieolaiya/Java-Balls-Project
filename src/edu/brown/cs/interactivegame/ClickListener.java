package edu.brown.cs.interactivegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * ClickListener
 * Implements MouseListener interface to add mouse click functionality in the animation.
 *
 * @author Stephanie Olaiya
 * Date: 9/20/24
 */
public class ClickListener implements MouseListener {

	/**
	 * List of balls in animation.
	 */
	private final List<Ball> balls;
	/**
	 * Animation Launcher object that extends JPanel.
	 */
	private final Launcher animation;

	/**
	 * Constructs ClickListener object with current balls in animation and the Launcher animation
	 * object.
	 * @param balls - current balls in animation
	 * @param animation - Launcher animation object
	 */
	public ClickListener(List<Ball> balls, Launcher animation) {
		this.balls = balls;
		this.animation = animation;
	}

	/**
	 * OnClick action listener, adds new ball on onclick if there are less than 16 balls in animation.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
//		if (this.balls.toArray().length <= 15) { // limit max number of balls to 15
//			Ball newBall = new Ball(e.getX(), e.getY(), this.animation);
//			this.balls.add(newBall);
//			this.executorService.submit(newBall);
				this.animation.addBall(e.getX(), e.getY());
//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class PsParser {
//
//	public static void main(String[] args) {
//		try {
//			// Execute the ps command
//			Process process = Runtime.getRuntime().exec("ps -ef");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//			// Read the output line by line
//			String line;
//			while ((line = reader.readLine()) != null) {
//				// Split the line into fields based on whitespace
//				String[] fields = line.split("\\s+");
//
//				// Extract the desired fields
//				String pid = fields[0]; // Process ID
//				String command = fields[7]; // Command
//
//				// Process the fields as needed
//				System.out.println("PID: " + pid + ", Command: " + command);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
