package edu.grinnell.csc207.sample;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import edu.grinnell.csc207.util.FourInRowV0;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;


/**
 * A two-player game with the goal of connecting 4 tokens in a line.
 * @author Tiffany Tang
 * @author Nicole Moreno Gonzalez
 */
public class Game {

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Print the insturctions.
   *
   * @param pen
   *  The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""

                Welcome to the game "Four in Row".

                Command-line arguments:

                * -r rows - set up the number of rows of the board
                * -c cols  - set up the number of columns of the board
                * -p1 player1 - set up the name of the player one
                * -p2 player2 - set up the name of the player two

                ***READ*** If you didn't run the program in the form:

                    mvn clean install
                    java -jar target/games-1.0.jar -r rows -c cols -p1 player1 -p2 player2

                  Please run the game again, otherwise your board won't have dimensions.

                ---------

                In this game your goal is to align four tokens in a row, whether diagonal, vertical
                or horizontal, before the other player. Otherwise, you lose.

                Your game board is a grid of blanks that could be filled with either token
                'X' (for player 1) or token 'O' (for player 2).

                The only move you can make as a player is to specify in which column of the board
                you want to insert your token. It will automatically insert a token at the last
                empty spot at the specified column.

                In-game commands:

                * INSERT1: prompts to write a column to insert a token for player 1
                * INSERT2: prompts to write a column to insert a token for player 2

                **Note** Valid ranges of column are between 0 and setted up columns number -1.

                After each move, a corresponding token for the player will appear in the board.

                     | |
                    -+-+-
                     | |
                    -+-+-
                     |X|

                """);
  } // printInstructions(PrintWriter)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the game.
   *
   * @param args
   *   Command-line arguments.
   */
  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));
    int rows = 0;
    int cols = 0;
    String p1 = "";
    String p2 = "";

    // Process the command line
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-r":
          try {
            rows = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid height: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (rows < 6) {
            System.err.printf("Invalid height: %s (It needs to be 6 or greater)\n", rows);
            return;
          } // if
          break;

        case "-c":
          try {
            cols = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid height: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (cols < 7) {
            System.err.printf("Invalid height: %s (It needs to be 7 or greater)\n", cols);
            return;
          } // if
          break;

        case "-p1":
          p1 = args[++i];
          break;

        case "-p2":
          p2 = args[++i];
          break;

        default:
          System.err.printf("Invalid command-line flag: %s\n", args[i]);
          return;
      } // switch
    } // for

    // Get started
    printInstructions(pen);
    pen.print("Hit return to start the game");
    pen.flush();
    eyes.readLine();

    // Set up the board and player information
    FourInRowV0 sampleFIR = new FourInRowV0(pen, rows, cols);
    Matrix<String> board = sampleFIR.board();
    sampleFIR.setP1(p1);
    sampleFIR.setP2(p2);

    // Run the game
    String[] commands = new String[] {"INSERT1", "INSERT2"};
    while (!sampleFIR.hasWinner()) {
      if (sampleFIR.hasDraw()) {
        sampleFIR.displayBoard();
        break;
      } // if
      sampleFIR.displayBoard();
      String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command.toUpperCase()) {

        case "INSERT1":
          int colToInsert1 = IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
          sampleFIR.insertToken1(colToInsert1);
          break;

        case "INSERT2":
          int colToInsert2 = IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
          sampleFIR.insertToken2(colToInsert2);
          break;

        default:
          System.err.printf("%s Invalid move", command);

      } //switch
    } //while
    pen.close();
  } // main(String[])
} // class Game
