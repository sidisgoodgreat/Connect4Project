package csaFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIBoard2 {

	private JFrame frame;
	private JTextField 	column,
						status;
	private Board b = new Board();
	private boolean isWin = false;
	private JLabel[][] displayBoard = new JLabel[6][7];
	private JButton restart, placePiece;
	private JToggleButton playerToggle = new JToggleButton();
	private JLabel 	header,
					columnLabel = new JLabel("Enter column Number:"),
					playerLabel = new JLabel("Choose the player:");
	
	//TEMP
	Player p1 = new Player(Color.red,1);
	Player p2 = new Player(Color.yellow,2);
	//TEMP

	/**
	 * Create the application.
	 */
	public GUIBoard2(Player p1, Player p2) {
		//Creating and setting the board
		setup();
		int x,
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
		
		this.p1=p1;
		this.p2=p2;
		
		frame.setVisible(true);
		
	}
	
	private void setup() {
		//Initializing and giving traits to the display features
				frame = new JFrame();
				frame.getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
				frame.setBounds(800, 800, 800, 800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				column = new JTextField();
				column.setBounds(562, 98, 126, 49);
				frame.getContentPane().add(column);
				column.setColumns(10);
				
				status = new JTextField();
				status.setFont(new Font("Tahoma", Font.BOLD, 12));
				status.setBounds(249, 634, 298, 91);
				frame.getContentPane().add(status);
				status.setColumns(10);
				
				columnLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				columnLabel.setBounds(91, 61, 126, 38);
				frame.getContentPane().add(columnLabel);
			
				playerLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
				playerLabel.setBounds(563, 56, 136, 49);
				frame.getContentPane().add(playerLabel);
				
				header = new JLabel("Connect 4!");
				header.setFont(new Font("Tahoma", Font.BOLD, 40));
				header.setBounds(288, 11, 266, 68);
				frame.getContentPane().add(header);
				
				placePiece = new JButton("Place Piece");
				placePiece.setFont(new Font("Tahoma", Font.BOLD, 11));
				placePiece.setBackground(new Color(0, 255, 128));
				placePiece.setBounds(302, 211, 200, 30);
				frame.getContentPane().add(placePiece);
				
				restart = new JButton("Restart Game");
				restart.setFont(new Font("Tahoma", Font.BOLD, 11));
				restart.setBackground(Color.RED);
				restart.setBounds(302, 212, 200, 29);
				frame.getContentPane().add(restart);
				
				playerToggle.setSelected(true);
				playerToggle.setBounds(62, 98, 161, 29);
				frame.getContentPane().add(playerToggle);
	}
	
	private boolean coinFlip() {
		return (int)Math.random()*2==1;
	}
	
	private void updateBoard(Player p1, Player p2) {
		for(int row=0;row<b.getBoard().length;row++) {
			for(int column=0;column<b.getBoard()[0].length;column++) {
				int point = b.getBoard()[row][column];
				displayBoard[row][column].setBackground(
						(point==p1.getID())?p1.getColor():
						(point==p2.getID()?p2.getColor():Color.white));
			}
		}
	}
	private void updatePlayerToggle(boolean init) {
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
			status.setBackground(Color.GREEN);
			status.setText("Player " + (playerToggle.isSelected()?1:2) + " scored a WIN!");
			placePiece.setVisible(false);
			restart.setVisible(true);
			isWin = true;
		}
	}
	private void initialize() {
		placePiece.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				restart.setVisible(false); 
				if(!isWin) {
					try {
						status.setText("");
						status.setBackground(Color.white);
						b.dropPiece(playerToggle.isSelected()?1:2, 
								Integer.valueOf(column.getText()));
								
						updateBoard(p1,p2);
				
						b.print();
						updatePlayerToggle(!playerToggle.isSelected());
						
						winCheck();
						
					}catch(Exception a) {
						status.setText("ERROR!");
						status.setBackground(Color.red);
					}
				}
				column.setText("");
			}
		});
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart.setVisible(false);
				placePiece.setVisible(true);
				status.setText("");
				status.setBackground(Color.WHITE);
				
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
