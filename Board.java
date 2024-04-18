package CSAFinalProject;

public class Board {
	private int[][] board;
	public Board(int row, int col) {
		board = new int[row][col];
	}
	
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	public void print() {
		for(int[] i:board) {
			System.out.println();
			for(int x: i) {
				System.out.print(x+" ");
			}
		}
	}
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	
	/**
	 * @param player - The player that is placing the piece
	 * @param column - the column the piece is being placed
	 * @return - True if a piece was successfully dropped, false otherwise
	 */
	public boolean dropPiece(int player, int column) {
		for(int i = board.length - 1; i>=0;i--) {
			if(board[i][column]==0) {
				board[i][column]=player;
				return true;
			}
		}
		return false;
	}
}
