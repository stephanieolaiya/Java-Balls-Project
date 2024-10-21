package edu.brown.cs.interactivegame;

import java.util.List;

/**
 * BallUtils
 *
 * @author Stephanie Olaiya Date: 10/21/24
 */
public class BallUtils {

  public static Ball matchingBallWithPid(List<Ball> balls, String pid) {
    for (Ball ball : balls) {
      if (ball.getPid().equals(pid)) {
        return ball;
      }
    }
    return null;
  }

}
