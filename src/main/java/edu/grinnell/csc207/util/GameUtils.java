package edu.grinnell.csc207.util;
/**
 * Implementation of additional methods for the game.
 *
 * @author Nicole Moreno Gonzalez
 * @author Tifanny
 *
 */

public class GameUtils {



  public static int lastInCol(int col, Matrix matrix) {
    int start = matrix.height();
    int currentRow = 0;
    int lastNullRow = -1;
    while(matrix.get(col,currentRow)== null || currentRow <= start) {
      currentRow++;
      lastNullRow++;
    }
    return lastNullRow;
  } // method lastInCol

  public boolean isWinner(Matrix matrix) {

    return false;
  } // method iswinner

}
