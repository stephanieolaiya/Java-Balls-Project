package edu.brown.cs.interactivegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;

/**
 * Ball A ball object that moves around the screen.
 *
 * @author Stephanie Olaiya Date: 9/20/24
 */
public class Ball {


  /**
   * Launcher object that extend JPanel (where ball id drawn).
   */
  private final Launcher animation;
  /**
   * x-position of ball.
   */
  private int x;
  /**
   * y-position of ball.
   */
  private int y;
  /**
   * speed of ball in x direction.
   */
  private int speedX = 2;
  /**
   * speed of ball in y direction.
   */
  private int speedY = 2;
  /**
   * boolean describing if ball has collided with another ball within animation duration.
   */
  private boolean collided;
  /**
   * Diameter of the ball.
   */
  private final int diameter;
  /**
   * Color of ball. Default it black.
   */
  private Color ballColor = Color.BLACK;

  /**
   * Constructs a new ball object with x position, y position and Launcher object where ball moves.
   * in.
   *
   * @param x         - horizontal position
   * @param y         - vertical position
   * @param animation - Launcher object where ball moves in
   */
  public Ball(int x, int y, double cpuUsage, Launcher animation) {
    this.x = x;
    this.y = y;
    this.diameter = (int) (15 + cpuUsage * 3); // cpuUsage used to compute diameter
    this.animation = animation;
    this.collided = false;
  }

  /**
   * Dictates how ball object moves, on collision with borders and with other ball objects.
   */
  public void move() {
//    this.frameCollision("ellipse");
    this.frameCollision("rectangle");

    for (Ball ball2 : this.animation.getBalls()) {
      if (!(this.equals(ball2))) {
        if (this.getBounds().intersects(ball2.getBounds())) {
          this.speedX *= -1;
          this.speedY *= -1;
          ball2.speedX *= -1;
          ball2.speedY *= -1;
          this.collided = true;
          ball2.collided = true;
        }
      }
    }
    this.x += this.speedX;
    this.y += this.speedY;
  }

  /**
   * Method that describes how ball is painted.
   *
   * @param g2d - Graphics2D object
   */
  public void paint(Graphics2D g2d) {
    g2d.fillOval(this.x, this.y, this.diameter, this.diameter); // adding a single ball
    if (this.collided) {
      if (this.ballColor.equals(Color.BLACK)) {
        Random r = new Random();
        this.ballColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        g2d.setColor(this.ballColor);
      } else {
        g2d.setColor(this.ballColor);
      }
    } else {
      g2d.setColor(this.ballColor);
    }
    g2d.fillOval(this.x, this.y, this.diameter, this.diameter); // adding a single ball
  }

  /**
   * Returns the rectangle surrounding a given ball object.
   *
   * @return Rectangle defining the bounds of the ball object
   */
  private Rectangle getBounds() {
    return new Rectangle(this.x, this.y, this.diameter, this.diameter);
  }

  private void frameCollision(String type) {
    if (type.equals("ellipse")) {
      // formula for ellipse bounds: (x-h)²/a² + (y-k)²/b² = 1
      // Ellipse center
      double h = this.animation.getWidth() / 2.0;
      double k = this.animation.getHeight() / 2.0;

      // Semi-major and semi-minor axes
      double a = this.animation.getWidth() / 2.0;
      double b = this.animation.getHeight() / 2.0;

      // Calculate the distance from the ball's center to the ellipse center
      double normalizedX = (this.x - h) / a;
      double normalizedY = (this.y - k) / b;

      // Check if the ball's edge is within the ellipse boundary
      double distance = normalizedX * normalizedX + normalizedY * normalizedY;
      boolean collided = distance >= 1 + Math.pow((double) (this.diameter / 2) / a, 2) + Math.pow(
          (double) (this.diameter / 2) / b, 2);

      if (collided) {
        this.speedX *= -1;
        this.speedY *= -1;
      }
    } else {
      if (this.x < 0) {
        this.speedX *= -1;
      }
      if (this.x > this.animation.getWidth() - this.diameter) {
        this.speedX *= -1;
      }
      if (this.y < 0) {
        this.speedY *= -1;
      }
      if (this.y > this.animation.getHeight() - this.diameter) {
        this.speedY *= -1;
      }
    }
  }
}
