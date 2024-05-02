package CSAFinalProject;

public class Board {
	private int[][] board;
	public Board(int row, int col) {
		board=new int[row][col];
	}
	public Board(){
		board=new int[6][7];
	}
	
	public int getRows() {
		return board.length;
	}
	public int getCols() {
		return board[0].length;
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
	public void clear() { //clears the board and is only called upon a win
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				board[r][c] = 0;
			}
		}
	}
	public String printStr() {
		String tmp = "";
		for(int[] i:board) {
			for(int x: i) {
				tmp += (x+" ");
			}
			tmp += "\n";
		}
		return tmp;
	}
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	//FOR TESTING PURPOSES ONLY TEMPORARY
	
	/**
	 * @param player - The player that is placing the piece
	 * @param column - the column the piece is being placed
	 * @return - True if a piece was successfully dropped, false otherwise
	 */
	public int dropPiece(int player, int column) {
		for(int i = board.length - 1; i>=0;i--) {
			if(board[i][column]==0) {
				board[i][column]=player;
				return i;
			}
		}
		return -1;
	}
	public int winCheckAll() {
		for(int i=1;i<=4;i++) {
			int winNum=winCheck(i);
			if(winNum!=-1) {
				return winNum;
			}
		}
		return -1;
	}
	public int winCheck(int id) {
		/*
		 * 1: Horizontal
		 * 2: Vertical
		 * 3: BL Diagonal
		 * 4: BR Diagonal
		 */
		//Based on the ID given(seen above), will set the for-loops to the correct parameter
		int outer1=(id==1)?0:3,outer2=5,
			inner1=(id==4)?3:0,inner2=(id==2||id==4)?6:3;
		for(int i=outer1;i<=outer2;i++) {
			for(int j=inner1;j<=inner2;j++) {
				int value=board[i][j];
				if(value!=0&&recWin(i,j,value,0,id)) {
					return value;
				}
			}
		}
		return -1;
	}
	private boolean recWin(int row, int column, int value, int count,int id) {
		if(count==4) {
			return true;
		}else if(board[row][column]==value){
			//Based on the same ID, each win requires to check a different direction
			int rowAdd=(id==1)?0:-1,
				columnAdd=(id==1||id==3)?1:(id==2)?0:-1;
			return recWin((row+rowAdd),(column+columnAdd),value,count+1,id);
		}else {
			return false;
		}
	}
}
