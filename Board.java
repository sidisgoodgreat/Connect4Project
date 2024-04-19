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
	public int cardinalWin() {
		//First checking horizontal wins(4 in a horizontal line)
		for(int i=0;i<=5;i++) {//Going Row by Row
			for(int j=0;j<=3;j++) {//first 3 columns only
				if(cardinalCheck(i,j,true)) {
					return board[i][j];
				}
			}
		}
		//If no Horizontals are found, searching for vertical wins
		for(int i=5;i>=3;i--) {//Row
			for(int j=6;j>=0;j--) {//Column
				if(cardinalCheck(i,j,false)) {
					return board[i][j];
				}
			}
		}
		return -1;//In the event no win is found, returns -1
	}
	private boolean cardinalCheck(int row, int column,boolean isHoz) {
		int value=board[row][column];
		return(value!=0&&recCardinal(row,column,value,0,isHoz));
	}
	private boolean recCardinal(int row, int column, int value,int count,boolean isHoz) {
		if(count==4) {
			return true;
		}else if(board[row][column]==value) {
			boolean val = isHoz?recCardinal(row,column+1,value,count+1,isHoz):
									recCardinal(row-1,column,value,count+1,isHoz);
			return val;
		}else {
			return false;
		}
	}
}
