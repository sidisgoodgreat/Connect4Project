package CSAFinalProject;

import java.awt.Color;
import java.util.Random;

public class Opponent extends Player {
    /**
 	*Creates the board instance variable
  	*/
    private Board board;

    /**
 	*Constructor initializing the color, id, and the board
  	*/
    public Opponent(Color c, int id, Board board) {
        super(c, id);
        this.board = board;
    }

    /**
 	*chooses the columnn based on the randomMove method
  	*/
    public int chooseColumn() {
        return (int) (Math.random()*7);
    }

    /**
 	*The piece will get dropped in a random column in the connect four grid.
  	*/
    private int randomMove() {
        Random rand = new Random();
        int col;
        do {
            col = rand.nextInt(7);
        } while (!board.dropPiece(getID(), col));  
        return col;
    }

    /**
 	* finds a strategic move for the player to add a piece to the grid.
    * traverse through the 2d array to find the specific value equivalent to 0
    * return the correct value if applicable otherwise return -1
  	*/
    private int findStrategicMove() {
        int maxSequence = 3;  
        for (int col = 0; col < board.getBoard()[0].length; col++) {
            for (int row = board.getBoard().length - 1; row >= 0; row--) {
                if (board.getBoard()[row][col] == 0) {  
                    board.getBoard()[row][col] = getID();
                    boolean strategicMove = false;
                    for (int dir = 1; dir <= 4; dir++) {
                        if (countSequence(row, col, getID(), maxSequence, dir) >= 2) {
                            strategicMove = true;
                            break;
                        }
                    }
                    board.getBoard()[row][col] = 0;

                    if (strategicMove) {
                        return col;
                    }
                    break;
                }
            }
        }
        return -1; 
    }

    /**
 	*this method countSequence is used to count the number of consecutive occurences of a specific playerID in a particular direction from (row, col)
  	*/
    private int countSequence(int row, int col, int playerID, int targetCount, int directionID) {
        int count = 1;  
        int rowAdd = (directionID == 1) ? 0 : (directionID == 2) ? 1 : (directionID == 3) ? 1 : 1;
        int colAdd = (directionID == 1) ? 1 : (directionID == 2) ? 0 : (directionID == 3) ? 1 : -1;
        int i = row + rowAdd, j = col + colAdd;
        while (isValidPosition(i, j) && board.getBoard()[i][j] == playerID && count < targetCount) {
            count++;
            i += rowAdd;
            j += colAdd;
        }
        i = row - rowAdd;
        j = col - colAdd;
        while (isValidPosition(i, j) && board.getBoard()[i][j] == playerID && count < targetCount) {
            count++;
            i -= rowAdd;
            j -= colAdd;
        }

        return count - 1;  
    }

    /**
 	*this method isValidPosition checks if the position is a valid position in the game.
  	*/
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.getBoard().length && col >= 0 && col < board.getBoard()[0].length;
    }  
}
