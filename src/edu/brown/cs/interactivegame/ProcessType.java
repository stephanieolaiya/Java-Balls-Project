/**
 * ProcessType.enum
 * Contains ProcessType enum.
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
