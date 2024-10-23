package edu.brown.cs.interactivegame;

import java.util.List;

/**
 * BallUtils - Utility class for balls lists.
 *
 * @author Stephanie Olaiya Date: 10/21/24
 */
public final class BallUtils {
  private BallUtils() {
  }
  /**
   * Finds and returns a ball matching the given pid
   * from a given ball list or null.
   * @param balls - the list of balls currently in the program
   * @param pid - the pid to check
   * @return - a ball matching the given pid or null.
   */
  public static Ball matchingBallWithPid(
      final List<Ball> balls, final String pid) {
    for (Ball ball : balls) {
      if (ball.getPid().equals(pid)) {
        return ball;
      }
    }
    return null;
  }

}
