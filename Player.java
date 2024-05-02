package CSAFinalProject;

import java.awt.Color;

public class Player {
	//Player class should have a number/id set to them
	//A color associated with them
	//Points based on how many times they've won

	//declaring instance variable on wins, the id, and the playerCol from the color class
	private int wins=0;
	private Color playerCol;
	private int id;

	// This is the constructor declaring the id and the player column
	public Player(Color c, int id) {
		this.id=id;
		this.playerCol=c;
	}
	// This method increments win by 1 each time
	public void incWin() {
		wins++;
	}
	//This method returns the wins
	public int getWins() {
		return wins;
	}
	//This method returns the color of the player
	public Color getColor() {
		return playerCol;
	}
	//This method returns the id of the player
	public int getID() {
		return id;
	}
	
}
