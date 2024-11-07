# Abstract games

A mini-project for Grinnell's CSC-207.

Authors:

* Tiffany Tang
* Nicky Moreno
* Samuel A. Rebelsky (Starter code)

Instructions:

* Game Summary:

    Welcome to the game "Four in Row".

    In this game your goal is to align four tokens in a row, whether diagonal, vertical or horizontal,
    before the other player. Otherwise, you lose.

    Your game board is a grid of blanks that could be filled with either token
    'X' (for player 1) or token 'O' (for player 2).

    The only move you can make as a player is to specify in which column of the board
    you want to insert your token. It will automatically insert a token at the last
    empty spot at the specified column.

    In-game commands:

    * INSERT1: prompts to write a column to insert a token for player 1
    * INSERT2: prompts to write a column to insert a token for player 2

    **Note** Valid ranges of column are between 0 and setted up columns number -1.

* Command-line arguments:

      * -r rows - set up the number of rows of the board
      * -c cols  - set up the number of columns of the board
      * -p1 player1 - set up the name of the player one
      * -p2 player2 - set up the name of the player two
  
* How to run the program:
  
  In the command line add:

    mvn clean install
    java -jar target/games-1.0.jar -r rows -c cols -p1 player1 -p2 player2


Acknowledgements:
Game.java is written in a similar structure of the Game1P wrote by Sam.

Source:
This code may be found at <https://github.com/morenoni/mp-games-maven.git>. It is based on code found at <https://github.com/Grinnell-CSC207/mp-games-maven>.
