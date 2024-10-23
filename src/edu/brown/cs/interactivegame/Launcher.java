package edu.brown.cs.interactivegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Launcher Code adapted from:
 * <a href="http://www.edu4java.com/en/game/game1.html">Java game
 * tutorial</a>. Creates the JFrame and adds animation movements
 * to JPanel in window.
 *
 * @author Stephanie Olaiya Date: 9/20/24
 */
public class Launcher extends JPanel {


  /**
   * Main program.
   * @param args - arguments
   * @throws InterruptedException - InterruptException for using threads
   */
  public static void main(final String[] args) throws InterruptedException {
    int windowWidth = 1000; // window width
    int windowHeight = 800; // window height
    JFrame window = new JFrame("Bouncing Balls showing System Processes");
    Launcher animation = new Launcher();
    window.add(animation);
    window.setUndecorated(true);
    window.setSize(windowWidth, windowHeight);
    window.setShape(
        new Ellipse2D.Double(0, 0, windowWidth, windowHeight)); // make frame an ellipse
    window.setLocationRelativeTo(null); // Center the window
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    while (true) {
      animation.move(); // make all items in launcher animation move
      animation.repaint(); // repaint after changes
      Thread.sleep(10);
    }
  }

  /**
   * List of balls in animation.
   */
  private final List<Ball> balls = new CopyOnWriteArrayList<>();

  /**
   * Constructor for game launcher.
   */
  public Launcher() {
    new Thread(new ProcessMonitor(this.balls, this)).start();
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
   * Adds random ball at a given location. Used to manually add balls to program
   * @param x -
   * @param y
   */
  public void addBall(int x, int y) {
    Ball ball = new Ball(x, y, 4.0, "", this);
    this.balls.add(ball);
  }

  /**
   * Does painting on the JPanel
   * @param g  the <code>Graphics</code> context in which to paint
   */
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


  /**
   * Calls move function for all balls currently in the launcher.
   */
  private void move() {
    for (Ball ball : this.balls) {
      ball.move();
    }
  }

}
