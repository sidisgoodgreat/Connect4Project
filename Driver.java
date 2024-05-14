package CSAFinalProject;

public class Driver {
/**
* The main method to create and test the Connect Four game.
*/
	public static void main(String[] args) {
		// Creates a new board with dimensions of (10 rows, 10 columns)
		Board b = new Board(10, 10);
		b.print();
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.print();
	}

}
