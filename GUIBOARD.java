package CSAFinalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIBoard extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	//Arrays
	private int[][] intBoard = new int[6][7];
	private JLabel[][] guiBoard = new JLabel[6][7];
	private JButton[] buttons = new JButton[7];
	
	JLabel background = new JLabel();
	
	
	Player p1, p2;
	boolean isPlayer1=true;
	
	public GUIBoard() {
		setSize(700,600);
		background.setBounds(0,0,900,900);
		background.setBackground(Color.blue);
		background.setOpaque(true);
		
		//Adding Display for Board
		for(int i=0;i<guiBoard.length;i++) {
			for(int j=0;j<guiBoard[0].length;j++) {
				guiBoard[i][j]=new JLabel();
				guiBoard[i][j].setBounds(j*100+5,i*100+205,90,90);
				guiBoard[i][j].setBackground(Color.white);
				guiBoard[i][j].setOpaque(true);				
				add(guiBoard[i][j]);
			}
		}
		
		//Adding Buttons
		for(int i=0;i<buttons.length;i++) {
			buttons[i]=new JButton();
			
			buttons[i].setBounds(5+(i*100),105,90,90);
			
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		add(background);
		
		setLayout(null);
		setVisible(true);
		
		//Adding Players
		p1=new Player(Color.red,1);
		p2=new Player(Color.yellow,2);
	}
	
	public void clearBoard() {
		for(int[] i:intBoard) {
			for(int x: i) {
				x=0;
			}
		}
	}
	/**
	 * @param player - The player that is placing the piece
	 * @param column - the column the piece is being placed
	 * @return - True if a piece was successfully dropped, false otherwise
	 */
	public boolean dropPiece(Player p, int column) {
		for(int i=5;i>=0;i--) {
			if(intBoard[i][column]==0) {
				intBoard[i][column]=p.getID();
				return true;
			}
		}
		return false;
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
				int value=intBoard[i][j];
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
		}else if(intBoard[row][column]==value){
			//Based on the same ID, each win requires to check a different direction
			int rowAdd=(id==1)?0:-1,
				columnAdd=(id==1||id==3)?1:(id==2)?0:-1;
			return recWin((row+rowAdd),(column+columnAdd),value,count+1,id);
		}else {
			return false;
		}
	}
	
	public void updateBoard(Player p1, Player p2) {
		for(int row=0;row<intBoard.length;row++) {
			for(int column=0;column<intBoard[0].length;column++) {
				int point = intBoard[row][column];
				guiBoard[row][column].setBackground(
						(point==p1.getID())?p1.getColor():
						(point==p2.getID()?p2.getColor():Color.white));
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<buttons.length;i++) {
			if(e.getSource()==buttons[i]) {
				dropPiece((isPlayer1)?p1:p2,i);
				isPlayer1=!isPlayer1;
				updateBoard(p1,p2);
				System.out.println(winCheckAll());
			}
		}
	}
