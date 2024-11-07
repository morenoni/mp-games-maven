package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.ArrayUtils;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;
import edu.grinnell.csc207.util.FourInRowV0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * A two-player game with the goal of connecting 4 tokens in a line.
 * 
 * @author Samuel A. Rebelsky (template of Game1P)
 * @author Tiffany Tang
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

                Your game board is a grid of blanks that could be filled with either 
                token 'X' or token 'O'.

                Your goal is to make four tokens in a row, whether diagonal or horizontal,
                before the other player. Otherise, you lose. 

                The only move a player can make is to specify which column of the board
                that you want to insert your token. It will automatically insert a token
                for you at the position at the last empty row at the specified column.
                

                * INSERT1: insert a token for player one
                * INSERT2: insert a token for player two

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
    String player1;
    String player2;

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
            System.err.printf("Invalid height: %s (less than 6)\n", rows);
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
            System.err.printf("Invalid height: %s (less than 7)\n", cols);
            return;
          } // if
          break;

        case "-p1":
          player1 = args[++i];
          break;

        case "-p2":
          player2 = args[++i];
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

    // Set up the board
    FourInRowV0 sampleFIR = new FourInRowV0(pen,rows,cols);
    Matrix<String> board = sampleFIR.board();
    
    // Run the game
    
    String[] commands = new String[] {"INSERT1","INSERT2"};
    while(!sampleFIR.hasWinner())
    {
      sampleFIR.displayBoard();
      String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command.toUpperCase()) {
        case "INSERT1":
        {
          int colToInsert1 =
              IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
          sampleFIR.insertToken1(colToInsert1);
        }
        break;

        case "INSERT2":
        {
          int colToInsert2 =
              IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
          sampleFIR.insertToken1(colToInsert2);
        }
        break;
      }
    }//while
    
    // And we're done
    pen.close();
  } // main(String[])
} // class Game
