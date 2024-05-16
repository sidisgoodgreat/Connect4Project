package CSAFinalProject;

import java.awt.Color;

/**
 * This class represents a player in the game.
 * A player has an ID, a color, and a count of wins.
 */
public class Player {
    private int wins = 0;
    private Color playerCol;
    private int id;

    /**
     * Constructs a Player with a specified color and ID.
     * @param c the color of the player
     * @param id the ID of the player
     */
    public Player(Color c, int id) {
        this.id = id;
        this.playerCol = c;
    }

    /**
     * Increments the win count for the player.
     */
    public void incWin() {
        wins++;
    }

    /**
     * Gets the number of wins for the player.
     * @return the number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Gets the color of the player.
     * @return the color
     */
    public Color getColor() {
        return playerCol;
    }

    /**
     * Gets the ID of the player.
     * @return the ID
     */
    public int getID() {
        return id;
    }

    /**
     * Chooses a column for the player's move.
     * This method can be overridden by subclasses for specific behaviors.
     * @return the column number (default is 1)
     */
    public int chooseColumn() {
        return 1;
    }
}
