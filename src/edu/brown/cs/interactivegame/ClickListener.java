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
	 * Animation Launcher object that extends JPanel.
	 */
	private final Launcher animation;

	/**
	 * Constructs ClickListener object with current balls in animation and the Launcher animation
	 * object.
	 * @param animation - Launcher animation object
	 */
	public ClickListener(Launcher animation) {
		this.animation = animation;
	}

	/**
	 * OnClick action listener, adds new ball on onclick if there are less than 16 balls in animation.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
				this.animation.addBall(e.getX(), e.getY());
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

