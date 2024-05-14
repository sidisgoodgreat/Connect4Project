package CSAFinalProject;

import java.awt.Color;

public class Player {
	//Player class should have a number/id set to them
	//A color associated with them
	//Points based on how many times they've won
	
	private int wins=0;
	private Color playerCol;
	private int id;

	/** Constructor initializes id and the color
	*
	*/
	public Player(Color c, int id) {
		this.id=id;
		this.playerCol=c;
	}
	/**
	*Increments wins by 1 if player wins
	*/
	public void incWin() {
		wins++;
	}
	/** 
	*Returns the number of times the player won
	*/
	public int getWins() {
		return wins;
	}
	/**
	*Returns the color of the player
	*/
	public Color getColor() {
		return playerCol;
	}
	/**
	*Returns the id
	*/
	public int getID() {
		return id;
	}
	
}
