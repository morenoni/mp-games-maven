package edu.grinnell.csc207.util;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;
import edu.grinnell.csc207.util.GameUtils;
/**
 * An implementation of Four in a Row.
 *
 * @author Nicole Moreno Gonzalez
 * @author Tiffany
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */

public class FourInRowV0 implements FourInRow {
  
  Matrix<String> defaultGame = new MatrixV0<String>(6, 5, null );

  /**
   * Inserts a token for player1 in the desired column.
   *
   * @param col
   *   The number of the col to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */


  public void insertToken1(int col) {
    return; // STUB

  } // method insertToken1

  /**
   * Inserts a token for player2 in the desired column.
   *
   * @param col
   *   The number of the col to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertToken2(int col) {
    return; //STUB
  }// method insertToken2
} // class FourInRowV0
