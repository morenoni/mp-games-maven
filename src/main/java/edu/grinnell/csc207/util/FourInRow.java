package edu.grinnell.csc207.util;

/**
 * Game "4 in a row".
 *
 * @author Nicole Moreno Gonzalez
 * @author Tiffany
 *
 */
public interface FourInRow {

  /**
   * Inserts a token for player1 in the desired column.
   *
   * @param col
   *   The number of the col to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   *
   * @throws IllegalArgumentException
   *   If the column is full.
   */
  public void insertToken1(int col);

  /**
   * Inserts a token for player2 in the desired column.
   *
   * @param col
   *   The number of the col to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   *
   * @throws IllegalArgumentException
   *   If the column is full.
   */
  public void insertToken2(int col);

} // class FourInRow
