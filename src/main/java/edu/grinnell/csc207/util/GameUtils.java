package edu.grinnell.csc207.util;

/**
 * Implementation of additional methods for the game.
 *
 * @author Nicole Moreno Gonzalez
 * @author Tiffany
 *
 */
public class GameUtils {

  /**
   * Finds the last available row in the given column.
   *
   * @param col column to check.
   * @param matrix the game board matrix.
   * @return the index of the last empty row, or -1 if the column is full.
   */
  public static int lastInCol(int col, Matrix<String> matrix) {
    int height = matrix.height();
    for (int row = height - 1; row >= 0; row--) {
      if (matrix.get(row, col) == null || matrix.get(row, col).equals("/")) {
        return row;
      } // if
    } // for
    return -1; 
  } // method lastInCol

  /**
   * Checks if there is a winner on the board.
   *
   * @param matrix the game board matrix.
   * @param token the token to check for a winning sequence.
   * @return true if a player has won, false otherwise.
   */
  public static boolean isWinner(Matrix<String> matrix, String token) {
    int rows = matrix.height();
    int cols = matrix.width();

    // Check horizontal win
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col <= cols - 4; col++) {
        if (matrix.get(row, col).equals(token) &&
            matrix.get(row, col + 1).equals(token) &&
            matrix.get(row, col + 2).equals(token) &&
            matrix.get(row, col + 3).equals(token)) {
          return true;
        } // if
      } // for
    } // for

    // Check vertical win
    for (int col = 0; col < cols; col++) {
      for (int row = 0; row <= rows - 4; row++) {
        if (matrix.get(row, col).equals(token) &&
            matrix.get(row + 1, col).equals(token) &&
            matrix.get(row + 2, col).equals(token) &&
            matrix.get(row + 3, col).equals(token)) {
          return true;
        } // if
      } // for
    } // for

    // Check diagonal (\) win
    for (int row = 0; row <= rows - 4; row++) {
      for (int col = 0; col <= cols - 4; col++) {
        if (matrix.get(row, col).equals(token) &&
            matrix.get(row + 1, col + 1).equals(token) &&
            matrix.get(row + 2, col + 2).equals(token) &&
            matrix.get(row + 3, col + 3).equals(token)) {
          return true;
        } // if
      } // for
    } //for

    // Check diagonal (/) win
    for (int row = 3; row < rows; row++) {
      for (int col = 0; col <= cols - 4; col++) {
        if (matrix.get(row, col).equals(token) &&
            matrix.get(row - 1, col + 1).equals(token) &&
            matrix.get(row - 2, col + 2).equals(token) &&
            matrix.get(row - 3, col + 3).equals(token)) {
          return true;
        } // if
      } // for
    } // for
    return false; 
  } //method isWinner

  /**
   * Checks if the board is full (a draw).
   *
   * @param matrix the game board matrix.
   * @return true if the board is full, false otherwise.
   */
  public static boolean isDraw(Matrix<String> matrix) {
    int cols = matrix.width();
    for (int col = 0; col < cols; col++) {
      if (lastInCol(col, matrix) != -1) {
        return false;
      } // if
    } // for
    return true;
  } // method isDraw

}
