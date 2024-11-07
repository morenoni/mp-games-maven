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
  private String player1;
  private String player2;
  private int ROWS;
  private int COLS;
  private boolean hasWinner = false;;

  /**
   * Constructor that initializes the game board with 6x7 dimensions.
   *
   * @param pen The PrintWriter used for printing.
   */
  public FourInRowV0(PrintWriter pen, int rows, int cols) {
    ROWS = rows;
    COLS = cols;
    board = new MatrixV0<>(cols, rows, "/");
    this.pen = pen;
  } // constructor FourInRowV0

  @Override
  public void insertToken1(int col) {
    insertToken(col, PLAYER1_TOKEN, player1);
  } // method insertToken1

  @Override
  public void insertToken2(int col) {
    insertToken(col, PLAYER2_TOKEN, player2);
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
      this.hasWinner = true;
      pen.println(playerName + " wins!");
    } else if (GameUtils.isDraw(board)) {
      pen.println("The game is a draw.");
    } // else if
  } // method insertToken

  /**
   * Draw the current board
   */
  public void displayBoard() {
    pen.println("Current board:");
    Matrix.print(pen, board);
  } // method displayBoard
  
  /**
   * Return the ROWS of the board
   */
  public int row(){
    return this.ROWS;
  }//method row
  
  /**
   * Return the COLS of the board
   */
  public int col(){
    return this.COLS;
  }//method col

  /**
   * return the board
  */
  public Matrix<String> board(){
    return this.board;
  }//method board

  /**
   * return the boolean hasWinner status
   */
  public boolean hasWinner(){
    return this.hasWinner;
  }//method hasWinner

  /**
   * Set player1's name
   */
  public void setP1(String p1){
    this.player1 = p1;
  }//method setP1

  /**
   * Set player2's name
   */
  public void setP2(String p2){
    this.player2 = p2;
  }//method setP2

} // class FourInRowV0
