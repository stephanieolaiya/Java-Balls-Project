package edu.brown.cs.interactivegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.Random;

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
   * Diameter of the ball.
   */
  private final int diameter;
  /**
   * Random variable initializer.
   */
  private final Random r = new Random();
  /**
   * Color of the ball. Randomly generated
   */
  private final Color ballColor = new Color(
      this.r.nextInt(256), this.r.nextInt(256), this.r.nextInt(256));
  /**
   * cpuUsage of process assigned to ball.
   */
  private double cpuUsage;
  /**
   * pid of process assigned to ball.
   */
  private final String pid;
  /**
   * Scale of ball.
   */
  private final double scale = 0.3;
  /**
   * Minimum size of ball.
   */
  private final int minSize = 15;

  /**
   * Constructs a new ball object with x position, y position, cpuUsage,
   * pid, and Launcher object where ball moves.
   * in.
   *
   * @param x         - horizontal position
   * @param y         - vertical position
   * @param cpuUsage    - cpuUsage of process assigned to ball
   * @param pid        - pid of process assigned to ball
   * @param animation - Launcher object where ball moves in
   */
  public Ball(int x, int y, double cpuUsage, String pid, Launcher animation) {
    this.x = x;
    this.y = y;
    this.cpuUsage = cpuUsage;
    this.pid = pid;
    this.diameter = (int) (this.minSize + cpuUsage * this.scale);
    this.animation = animation;
  }

  /**
   *  Ball pid getter method.
   * @return the pid of ball
   */
  public String getPid() {
    return this.pid;
  }

  /**
   * Setter method for ball cpuUsage.
   * @param cpuUsage - new cpuUsage
   */
  public void setCpuUsage(double cpuUsage) {
    this.cpuUsage =  cpuUsage;
  }

  /**
   * Dictates how ball object moves.
   */
  public void move() {
    this.frameCollision();
    this.x += this.speedX;
    this.y += this.speedY;
  }

  /**
   * Method that describes how ball is painted.
   *
   * @param g2d - Graphics2D object
   */
  public void paint(Graphics2D g2d) {
    // adding a single ball
    g2d.fillOval(this.x, this.y, this.diameter, this.diameter);
    g2d.setColor(this.ballColor);
  }


  /**
   * Method that overrides equality checker for ball to only matching pid value.
   * @param obj - object to compare against
   * @return boolean describing if balls are equivalent
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Ball other)) {
      return false;
    }
    return this.pid.equals(other.pid);
  }

  /**
   * Hashcode for ball object.
   * @return ball hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.pid);
  }

  /**
   * Returns the rectangle surrounding a given ball object.
   *
   * @return Rectangle defining the bounds of the ball object
   */
  private Rectangle getBounds() {
    return new Rectangle(this.x, this.y, this.diameter, this.diameter);
  }

  /**
   * Reverses direction of movement of ball if it has collided with frame.
   */
  private void frameCollision() {
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
      boolean collided = distance > 1
          + ((double) (this.diameter / 2)
          * (double) (this.diameter / 2) / (a * b));
      // reverse direction of ball has collided
      if (collided) {
        this.speedX *= -1;
        this.speedY *= -1;
      }
  }
}
