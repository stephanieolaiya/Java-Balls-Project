package edu.brown.cs.interactivegame;

import java.util.List;

/**
 * ProcessInfo
 *
 * @author Stephanie Olaiya Date: 10/20/24
 */
public class ProcessInfo {
  private final String pid;
  private final String command;
  private final double cpuUsage; // Assume this is a percentage

  public ProcessInfo(String pid, String command, double cpuUsage) {
    this.pid = pid;
    this.command = command;
    this.cpuUsage = cpuUsage;
  }

  public String getPid() {
    return pid;
  }

  public String getCommand() {
    return command;
  }

  public double getCpuUsage() {
    return cpuUsage;
  }

  public boolean checkInList(List<ProcessInfo> processes) {
    for (ProcessInfo process : processes) {
      if (this.pid.equals(process.getPid())) {
        return true;
      }
    }
    return false;
  }

}
