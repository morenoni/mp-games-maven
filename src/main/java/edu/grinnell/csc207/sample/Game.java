package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.ArrayUtils;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

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
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default width.
   */
  static final int DEFAULT_WIDTH = 10;

  /**
   * The default number of rows.
   */
  static final int DEFAULT_HEIGHT = 10;

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

                * -w width - set up the width of the board
                * -h height - set up the height of the board
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

    int rrRemaining = 2;
    int rcRemaining = 2;
    int icRemaining = 2;
    int irRemaining = 2;
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    Random rand = new Random();
    int game = rand.nextInt();

    // Process the command line
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-w":
          try {
            width = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid width: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (width < 4) {
            System.err.printf("Invalid width: %s (less than 4)\n", width);
            return;
          } // if
          break;

        case "-h":
          try {
            height = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid height: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (height < 4) {
            System.err.printf("Invalid height: %s (less than 4)\n", height);
            return;
          } // if
          break;

        case "-s":
          try {
            game = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid game number: %s\n", args[i]);
            return;
          } // try/catch
          break;

        default:
          System.err.printf("Invalid command-line flag: %s\n", args[i]);
          return;
      } // switch
    } // for

    // Get started
    printInstructions(pen);
    pen.print("Hit return to continue");
    pen.flush();
    eyes.readLine();

    // Set up the board
    Matrix<String> board = setupBoard(width, height, game);

    // Run the game
    pen.println("Game setup number " + game);
    pen.println();

    String[] commands = new String[] {"RR", "RC", "IR", "IC", "SKIP", "UNDO", "DONE"};
    boolean done = false;
    Matrix<String> prev = board.clone();
    while (!done) {
      Matrix.print(pen, board, true);
      String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command.toUpperCase()) {
        case "RR":
          if (rrRemaining < 0) {
            pen.println("Sorry, you've used up your remove row commands.");
            break;
          } // if
          --rrRemaining;
          if (rrRemaining <= 0) {
            commands = ArrayUtils.removeAll(commands, "RR");
          } // if
          int rowToRemove =
              IOUtils.readInt(pen, eyes, "Row: ", 0, board.height());
          prev = board.clone();
          board.deleteRow(rowToRemove);
          process(board);
          break;

        case "RC":
          if (rcRemaining < 0) {
            pen.println("Sorry, you've used up your remove column commands.");
            break;
          } // if
          --rcRemaining;
          if (rcRemaining <= 0) {
            commands = ArrayUtils.removeAll(commands, "RC");
          } // if
          int colToRemove =
              IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
          prev = board.clone();
          board.deleteCol(colToRemove);
          process(board);
          break;

        case "IR":
          if (irRemaining < 0) {
            pen.println("Sorry, you've used up your insert row commands.");
            break;
          } // if
          --irRemaining;
          if (irRemaining <= 0) {
            commands = ArrayUtils.removeAll(commands, "IR");
          } // if
          int rowToInsert =
              IOUtils.readInt(pen, eyes, "Row: ", 0, board.height() + 1);
          prev = board.clone();
          board.insertRow(rowToInsert);
          process(board);
          break;

        case "IC":
          if (icRemaining < 0) {
            pen.println("Sorry, you've used up your insert row commands.");
            break;
          } // if
          --icRemaining;
          if (icRemaining <= 0) {
            commands = ArrayUtils.removeAll(commands, "IC");
          } // if
          int colToInsert =
              IOUtils.readInt(pen, eyes, "Column: ", 0, board.width() + 1);
          prev = board.clone();
          board.insertCol(colToInsert);
          process(board);
          break;

        case "DONE":
          done = true;
          break;

        case "SKIP":
          prev = board.clone();
          process(board);
          break;

        case "UNDO":
          if (board == prev) {
            pen.println("Sorry: There's only one level of undo.");
          } else {
            board = prev;
          } // if/else
          break;

        default:
          pen.printf("Unexpected command: '%s'. Please try again.\n", command);
          break;
      } // switch
    } // while

    // Print final results
    printResults(pen, board);

    // And we're done
    pen.close();
  } // main(String[])
} // class Game1P
