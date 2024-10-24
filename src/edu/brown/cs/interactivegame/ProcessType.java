package edu.brown.cs.interactivegame;

/**
 * ProcessType Enum.
 * A process is either an Applications, usr, system or other process
 * @author Stephanie Olaiya Date: 10/24/24
 */
public enum ProcessType {
  /**
   * Usr process.
   */
    USR,
  /**
   * System process.
   */
    SYSTEM,
  /**
   * Application process.
   */
    APPLICATION,
  /**
   * Other process (not any of the above three).
   */
  OTHER
}
