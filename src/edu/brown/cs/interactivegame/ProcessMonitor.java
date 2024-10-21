package edu.brown.cs.interactivegame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

/**
 * ProcessMonitor
 *
 * @author Stephanie Olaiya Date: 10/20/24
 */
public class ProcessMonitor implements Runnable {
  private final List<Ball> balls;
  private final Launcher animation;

  public ProcessMonitor(List<Ball> balls, Launcher animation) {
    this.balls = balls;
    this.animation = animation;
  }

  /**
   * When an object implementing interface {@code Runnable} is used to create a thread, starting the
   * thread causes the object's {@code run} method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method {@code run} is that it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    while (true) {
      try {
        Process process = Runtime.getRuntime().exec("ps -eo pid,comm,%cpu");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        // Clear existing balls
        this.balls.clear();

        // Read the output line by line
        while ((line = reader.readLine()) != null) {
          if (line.contains("PID")) continue; // Skip header
          String[] fields = line.trim().split("\\s+");
          if (fields.length > 3) continue;
          String pid = fields[0];
          String command = fields[1];
          double cpuUsage = Double.parseDouble(fields[2]);
//          if (cpuUsage > 0.0) {
            // Add a new ball for each process that has cpu usage > 0
            if ((this.animation.getWidth() < 1) || (this.animation.getHeight() < 1)) {
              this.balls.add(new Ball(5, 5, cpuUsage, this.animation));
            } else {
              int x = new Random().nextInt(this.animation.getWidth());
              int y = new Random().nextInt(this.animation.getHeight());
              this.balls.add(new Ball(x, y, cpuUsage, this.animation));
            }
//          }
        }
        Thread.sleep(10000); // Sleep for 2 seconds before the next poll
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
