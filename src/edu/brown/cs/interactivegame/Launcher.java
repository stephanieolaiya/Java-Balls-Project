/**
 * Launcher.java
 * Creates animation window and JFrame and runs it.
 * v1.0
 * 2024-11-12
 * MIT License
 * Copyright (c) 2024 Brown University -- Stephanie Olaiya
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
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
    JFrame window = new JFrame("Bouncing Balls showing System Processes");
    Launcher animation = new Launcher();
    window.add(animation);
    window.setUndecorated(true);
    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setShape(
        new Ellipse2D.Double(0, 0,
            WINDOW_WIDTH, WINDOW_HEIGHT)); // make frame an ellipse
    window.setLocationRelativeTo(null); // Center the window
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while (true) {
      animation.move(); // make all items in launcher animation move
      animation.repaint(); // repaint after changes
      Thread.sleep(REPAINT_TIME);
    }
  }
  /**
   * List of balls in animation.
   */
  private final List<Ball> balls = new CopyOnWriteArrayList<>();
  /**
   * Width of Jframe window.
   */
  private static final int WINDOW_WIDTH = 1000;
  /**
   * Height of JFrame window.
   */
  private static final int WINDOW_HEIGHT = 800;
  /**
   * ms time to update animation frame .
   */
  private static final int REPAINT_TIME = 10;
  /**
   * Constructor for game launcher.
   */
  public Launcher() {
    new Thread(new ProcessMonitor(this.balls, this)).start();
  }

  /**
   * Does painting on the JPanel.
   * @param g  the <code>Graphics</code> context in which to paint
   */
  @Override
  public void paint(final Graphics g) {
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
