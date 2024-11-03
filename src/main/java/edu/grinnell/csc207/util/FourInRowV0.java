package edu.grinnell.csc207.util;
import java.io.PrintWriter;

/**
 * An implementation of Four in a Row.
 *
 * @author Nicole Moreno Gonzalez
 * @author Tiffany
 *
 */
public class FourInRowV0 implements FourInRow {
  
  private PrintWriter pen;
  private Matrix<String> board;
  private static final String PLAYER1_TOKEN = "X";
  private static final String PLAYER2_TOKEN = "O";
  private static final int ROWS = 6;
  private static final int COLS = 7;

  /**
   * Constructor that initializes the game board with 6x7 dimensions.
   *
   * @param pen The PrintWriter used for printing.
   */
  public FourInRowV0(PrintWriter pen) {
    board = new MatrixV0<>(COLS, ROWS, "/");
    this.pen = pen;
  } // constructor FourInRowV0

  @Override
  public void insertToken1(int col) {
    insertToken(col, PLAYER1_TOKEN, "Player 1");
  } // method insertToken1

  @Override
  public void insertToken2(int col) {
    insertToken(col, PLAYER2_TOKEN, "Player 2");
  } // method insertToken2

  /**
   * Inserts a token into the specified column for a player.
   *
   * @param col is the column to insert the token.
   * @param token is the token representing the player.
   * @param playerName name of the player for output.
   * @throws IndexOutOfBoundsException if the column is negative or greater than the width.
   * @throws IllegalArgumentException if the column is full.
   */
  private void insertToken(int col, String token, String playerName) {
    if (col < 0 || col >= COLS) {
      throw new IndexOutOfBoundsException("Column index out of bounds. Valid range: 0 to " + (COLS - 1));
    } // if

    int row = GameUtils.lastInCol(col, board);
    if (row == -1) {
      throw new IllegalArgumentException("Column is full.");
    } // if

    board.set(row, col, token);
    pen.println(playerName + " inserted token at column " + col);
    displayBoard();

    if (GameUtils.isWinner(board, token)) {
      pen.println(playerName + " wins!");
    } else if (GameUtils.isDraw(board)) {
      pen.println("The game is a draw.");
    } // else if
  } // method insertToken

  public void displayBoard() {
    pen.println("Current board:");
    Matrix.print(pen, board);
  } // method displayBoard

} // class FourInRowV0
