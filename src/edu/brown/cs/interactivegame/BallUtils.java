/**
 * BallUtils.java
 * Utility functions for List of ball objects
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

import java.util.List;

/**
 * BallUtils - Utility class for balls lists.
 *
 * @author Stephanie Olaiya Date: 10/21/24
 */
public final class BallUtils {

  /**
   * Constructor
   */
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
