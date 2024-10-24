package edu.brown.cs.interactivegame;

import static edu.brown.cs.interactivegame.BallUtils.matchingBallWithPid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * ProcessMonitor - gets all system processes and modifies
 * ball list in a separate thread.
 *
 * @author Stephanie Olaiya Date: 10/20/24
 */
public class ProcessMonitor implements Runnable {

  /**
   * current list of balls.
   */
  private final List<Ball> balls;
  /**
   * animation panel.
   */
  private final Launcher animation;
  /**
   * The number of ideal fields to index into.
   */
  private static final int NUM_IDEAL_FIELD = 3;
  /**
   * Upper bound of random integer generation for placing ball.
   */
  private static final int UPPER_BOUND_RAND = 100;
  /**
   * ms to sleep thread before next poll to update processes.
   */
  private static final int THREAD_SLEEP_TIME = 5000;

  /**
   * Constructor for ProcessMonitor object.
   * @param ballList - the list of balls currently in the program
   * @param launcherAnimation - the JPanel object
   */
  public ProcessMonitor(List<Ball> ballList,
      final Launcher launcherAnimation) {
    this.balls = ballList;
    this.animation = launcherAnimation;
  }

  /**
   * When an object implementing interface {@code Runnable}
   * is used to create a thread, starting the
   * thread causes the object's {@code run} method to
   * be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method {@code run} is that
   * it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    while (true) {
      try {
        // Runtime.getRuntime() code gotten from ChatGPT
        Process process = Runtime.getRuntime().exec("ps -eo pid,comm,%cpu");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream()));
        String line;
        // Read the output line by line
        List<String> pidList = new ArrayList<>(); // list of read processes
        while ((line = reader.readLine()) != null) {
          if (line.contains("PID")) {
            continue; // Skip header
          }
          String[] fields = line.trim().split("\\s+");
          // to prevent obtaining wrong field
          if (fields.length > NUM_IDEAL_FIELD) {
            continue;
          }
          String pid = fields[0];
          String command = fields[1];
          double cpuUsage = Double.parseDouble(fields[2]);
            pidList.add(pid); // update pid list with current pid
            // if ball already in program, update cpuUsage
            if (matchingBallWithPid(this.balls, pid) != null) {
              Ball selectedBall = matchingBallWithPid(this.balls, pid);
              if (selectedBall != null) {
                selectedBall.setCpuUsage(cpuUsage);
              }
            } else {
              // else create ball the ball and add it to the program
              if ((this.animation.getWidth() < 1)
                  || (this.animation.getHeight() < 1)) {
                this.balls.add(new Ball(new Random().nextInt(UPPER_BOUND_RAND),
                    new Random().nextInt(UPPER_BOUND_RAND), cpuUsage, pid,
                    this.determineProcessType(command), this.animation));
              } else {
                int x = new Random().nextInt(this.animation.getWidth());
                int y = new Random().nextInt(this.animation.getHeight());
                this.balls.add(new Ball(x, y, cpuUsage, pid,
                    this.determineProcessType(command), this.animation));
              }
          }
        }
        // remove ball associated with pid if process no longer running
        this.balls.removeIf(ball -> !pidList.contains(ball.getPid()));
        // Sleep for 5 seconds before the next poll
        Thread.sleep(THREAD_SLEEP_TIME);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Determine the process type of process command.
   * @param command - command of a process
   * @return - the ProcessType of that process
   */
  private ProcessType determineProcessType(final String command) {
    if (command.contains("Applications")) {
      return ProcessType.APPLICATION;
    } else if (command.contains("usr")) {
      return ProcessType.USR;
    } else if (command.contains("System")) {
      return ProcessType.SYSTEM;
    } else {
      return ProcessType.OTHER;
    }
  }

}
