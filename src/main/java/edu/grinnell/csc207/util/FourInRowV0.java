package edu.grinnell.csc207.util;
import java.io.PrintWriter;

/**
 * An implementation of Four in a Row.
 *
 * @author Nicole Moreno Gonzalez
 * @author Tiffany Tang
 *
 */
public class FourInRowV0 implements FourInRow {

  /** variable to help print messages. */
  private PrintWriter pen;
  /** matrix that hold the game information. */
  private Matrix<String> board;
  /** char token for player1. */
  private static final String PLAYER1_TOKEN = "X";
  /** char token for player2. */
  private static final String PLAYER2_TOKEN = "O";
  /** name of player1. */
  private String player1 = "";
  /** name of player2. */
  private String player2 = "";
  /** number of rows of the board matrix. */
  private int rows;
  /** number of columns of the board matrix. */
  private int cols;
  /** a boolean show whether someone has won. */
  private boolean hasWinner = false;
  /** a boolean show whether the board if full. */
  private boolean hasDraw = false;

  /**
   * Constructor that initializes the game board with 6x7 dimensions.
   * @param row the number of rows of the game board
   * @param col the number of columns of the game board
   * @param penwriter The PrintWriter used for printing.
   */
  public FourInRowV0(PrintWriter penwriter, int row, int col) {
    rows = row;
    cols = col;
    board = new MatrixV0<>(cols, rows, " ");
    this.pen = penwriter;
  } // constructor FourInRowV0

  /**
   * Call insertToken function with player1's name and token.
   * @param col the column to insert the token.
   */
  @Override
  public void insertToken1(int col) {
    insertToken(col, PLAYER1_TOKEN, player1);
  } // method insertToken1

  /**
   * Call insertToken function with player2's name and token.
   * @param col the column to insert the token.
   */
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
   */
  private void insertToken(int col, String token, String playerName) {
    if (col < 0 || col >= cols) {
      throw new IndexOutOfBoundsException(
        "Column index out of bounds. Valid range: 0 to " + (cols - 1));
    } // if

    int row = GameUtils.lastInCol(col, board);
    if (row == -1) {
      pen.println("***Column " + col + " is full. Please choose a different column.***");
      return; // Exit early if the column is full
    } // if

    board.set(row, col, token);
    pen.println(playerName + " inserted token at column " + col);

    if (GameUtils.isWinner(board, token)) {
      this.hasWinner = true;
      pen.println(playerName + " wins!");
      this.displayBoard();
    } else if (GameUtils.isDraw(board)) {
      this.hasDraw = true;
      pen.println("The game is a draw.");
    } // else if

  } // method insertToken

  /**
   * Draw the current board.
   */
  public void displayBoard() {
    pen.println("Current board:");
    Matrix.print(pen, board, true);
  } // method displayBoard

  /**
   * Return the rows of the board.
   * @return rows
   */
  public int row() {
    return this.rows;
  } //method row

  /**
   * Return the cols of the board.
   * @return cols
   */
  public int col() {
    return this.cols;
  } //method col

  /**
   * Return the board.
   * @return board
  */
  public Matrix<String> board() {
    return this.board;
  } //method board

  /**
   * Return the boolean hasWinner status.
   * @return boolean hasWinner
   */
  public boolean hasWinner() {
    return this.hasWinner;
  } //method hasWinner

  /**
   * Return the isDraw status.
   * @return boolean hasDraw
   */
  public boolean hasDraw() {
    return this.hasDraw;
  } //method hasDraw

  /**
   * Set player1's name.
   * @param p1 the name of player 1
   * @return String player1
   */
  public String setP1(String p1) {
    this.player1 += p1;
    return player1;
  } //method setP1

  /**
   * Set player2's name.
   * @param p2 the name of player 2
   * @return String player2
   */
  public String setP2(String p2) {
    this.player2 += p2;
    return player2;
  } //method setP2

} // class FourInRowV0
