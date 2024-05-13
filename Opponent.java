package CSAFinalProject;

import java.awt.Color;
import java.util.Random;

public class Opponent extends Player {
    private Board board;

    public Opponent(Color c, int id, Board board) {
        super(c, id);
        this.board = board;
    }

    public int chooseColumn() {
        int strategicMove = findStrategicMove();
        if (strategicMove != -1) {
            return strategicMove;
        }
        return randomMove();
    }

    private int randomMove() {
        Random rand = new Random();
        int col;
        do {
            col = rand.nextInt(7);
        } while (!board.dropPiece(getID(), col));  
        return col;
    }

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

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.getBoard().length && col >= 0 && col < board.getBoard()[0].length;
    }  
    public int algorithimn(int depth, int alpha, int beta, boolean maxPlayer) {
          //This stuf rlly complicated
    }
}
