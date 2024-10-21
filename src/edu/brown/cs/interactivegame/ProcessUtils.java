package edu.brown.cs.interactivegame;

import java.util.List;

/**
 * ProcessUtils
 *
 * @author Stephanie Olaiya Date: 10/21/24
 */
public class ProcessUtils {
    public static boolean containsProcessWithPid(List<ProcessInfo> processInfoList, String pid) {
      for (ProcessInfo processInfo : processInfoList) {
        if (processInfo.getPid().equals(pid)) {
          return true;
        }
      }
      return false; // No match found
    }

  public static ProcessInfo matchingProcessWithPid(List<ProcessInfo> processInfoList, String pid) {
    for (ProcessInfo processInfo : processInfoList) {
      if (processInfo.getPid().equals(pid)) {
        return processInfo;
      }
    }
    return null;
  }

}
