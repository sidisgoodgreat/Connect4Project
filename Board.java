public class Board {
	private int[][] board = new int[6][7];
	public Board() {
		for(int[] i:board) {
			for(int x: i) {
				x=0;
			}
		}
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
		for(int i=5;i>=0;i--) {
			if(board[i][column]==0) {
				board[i][column]=player;
				return true;
			}
		}
		return false;
	}
	
}
