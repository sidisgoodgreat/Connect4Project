package CSAFinalProject;

public class Driver {

	public static void main(String[] args) {
		Board b = new Board(10, 10);
		b.print();
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.dropPiece(1, 5);
		b.print();
	}

}
