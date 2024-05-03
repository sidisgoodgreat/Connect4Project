package CSAFinalProject;

import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class GUIBoard2 {

	private JFrame frame;
	private JTextField Column,
						isError;
	private Board b = new Board();
	private JTextField winStatus;
	private boolean isWin = false;
	private JLabel Header;
	private JLabel[][] displayBoard = new JLabel[6][7];
	private JButton Restart, placePiece;
	private JToggleButton playerToggle = new JToggleButton("isPlayer1");
	private JLabel label2 = new JLabel("Enter Column Number:"),
			Label1 = new JLabel("Choose the player:");
	
	//TEMP
	Player p1 = new Player(Color.red,1);
	Player p2 = new Player(Color.yellow,2);
	//TEMP

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBoard2 window = new GUIBoard2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIBoard2() {
		
		//Initializing and giving traits to the display features
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		frame.setBounds(800, 800, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Column = new JTextField();
		Column.setBounds(562, 98, 126, 49);
		frame.getContentPane().add(Column);
		Column.setColumns(10);
		
		isError = new JTextField();
		isError.setBounds(350, 143, 110, 38);
		frame.getContentPane().add(isError);
		isError.setColumns(10);
		
		winStatus = new JTextField();
		winStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		winStatus.setBounds(249, 634, 298, 91);				
		frame.getContentPane().add(winStatus);
		winStatus.setColumns(10);
		
		Label1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Label1.setBounds(91, 61, 126, 38);
		frame.getContentPane().add(Label1);
	
		label2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label2.setBounds(563, 56, 136, 49);
		frame.getContentPane().add(label2);
		
		Header = new JLabel("Connect 4!");
		Header.setFont(new Font("Tahoma", Font.BOLD, 40));
		Header.setBounds(288, 11, 266, 68);
		frame.getContentPane().add(Header);
		
		placePiece = new JButton("Place Piece");
		placePiece.setFont(new Font("Tahoma", Font.BOLD, 11));
		placePiece.setBackground(new Color(0, 255, 128));
		placePiece.setBounds(302, 211, 200, 30);
		frame.getContentPane().add(placePiece);
		
		Restart = new JButton("Restart Game");
		Restart.setBackground(Color.RED);
		Restart.setBounds(302, 212, 200, 29);
		frame.getContentPane().add(Restart);
		
		playerToggle.setSelected(true);
		playerToggle.setBounds(62, 98, 161, 29);
		frame.getContentPane().add(playerToggle);
		
		
		//Creating and setting the board
		int x = 150,
			y = 252;
		for(int r = 0; r < displayBoard.length; r++) {
			x = 150;
			for(int c = 0; c < displayBoard[0].length; c++) {
				x += 55;
				displayBoard[r][c] = new JLabel();
				displayBoard[r][c].setBackground(Color.white);
				displayBoard[r][c].setOpaque(true);
				displayBoard[r][c].setBounds(x,y,50,50);
				frame.getContentPane().add(displayBoard[r][c]);
			}
			y+=65;
		}
		//Flipping a coin to see who goes first
		updatePlayerToggle(coinFlip());
		
		initialize();
		
	}
	
	private boolean coinFlip() {
		return (int)Math.random()+1==1;
	}
	public void updateBoard(Player p1, Player p2) {
		for(int row=0;row<b.getBoard().length;row++) {
			for(int column=0;column<b.getBoard()[0].length;column++) {
				int point = b.getBoard()[row][column];
				displayBoard[row][column].setBackground(
						(point==p1.getID())?p1.getColor():
						(point==p2.getID()?p2.getColor():Color.white));
			}
		}
	}
	public void updatePlayerToggle(boolean init) {
		playerToggle.setSelected(init);
		playerToggle.setText((init?"Player 1":"Player 2"));
	}
	/**
	 * Initialize the contents of the frame.
	 * PLAYER 1 IS RED!, PLAYER 2 IS ORANGE!
	 */
	private void winCheck() {
		if(b.winCheckAll() > 0) {
			//color set:
			winStatus.setBackground(Color.GREEN);
			winStatus.setText("Player " + (playerToggle.isSelected()?1:2) + " scored a WIN!");
			placePiece.setVisible(false);
			Restart.setVisible(true);
			isWin = true;
		}
	}
	private void initialize() {
		placePiece.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				Restart.setVisible(false); 
				if(!isWin) {
					try {
						String col = Column.getText();
						int colStrToInt = Integer.valueOf(col);
						isError.setText("");
						b.dropPiece(playerToggle.isSelected()?1:2, colStrToInt);
								
						updateBoard(p1,p2);
				
						b.print();
						updatePlayerToggle(!playerToggle.isSelected());
						
						winCheck();
						
					}catch(Exception a) {
						isError.setText("ERROR!");
					}
				}
				Column.setText("");
				
			}
		});
		Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restart.setVisible(false);
				placePiece.setVisible(true);
				Column.setText("");
				winStatus.setText("");
				winStatus.setBackground(Color.WHITE);
				
				b.clearBoard();
				updateBoard(p1,p2);
				
				isWin = false;
				updatePlayerToggle(coinFlip());
			}
		});
		playerToggle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				playerToggle.setText((playerToggle.isSelected()?"Player 1":"Player 2"));
			}
		});
	}//End Method
}
