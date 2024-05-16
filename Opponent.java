package CSAFinalProject;

import java.awt.Color;
import java.util.Random;

/**
 * This class represents an opponent in the game.
 * The opponent can choose moves randomly.
 */
public class Opponent extends Player {

    /**
     * Constructs an Opponent with a specified color and ID.
     * @param c the color of the opponent
     * @param id the ID of the opponent
     */
    public Opponent(Color c, int id) {
        super(c, id);
    }

    /**
     * Chooses a column for the opponent's move randomly.
     * @return the column number chosen randomly (0 to 6)
     */
    @Override
    public int chooseColumn() {
        return (int) (Math.random() * 7);
    }
}
