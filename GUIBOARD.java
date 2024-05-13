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
import javax.swing.JSplitPane;

public class GUIBoard1 implements ActionListener{

	private JFrame frame;
	private JTextField 	status;
	private Board b = new Board();
	private boolean isWin = false;
	private JLabel[][] displayBoard = new JLabel[6][7];
	private JButton restart, placePiece;
	private JToggleButton playerToggle = new JToggleButton();
	private JLabel 	header,
					columnLabel = new JLabel("Enter column Number:"),
					playerLabel = new JLabel("Choose the boxes at the top row to drop a piece!");
	private JButton[] buttons = new JButton[7];
	//TEMP
	Player p1; 
	Player p2;
	//TEMP
	
	/**
	 * Launch the application.
	 */
	/* public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBoard1 window = new GUIBoard1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public GUIBoard1() {
		
		//Initializing and giving traits to the display features, with the default colors.
		p1 = new Player(Color.red,1);
		p2 = new Player(Color.yellow,2);
		createDisplay();
		
	}
	public GUIBoard1(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		createDisplay();
	}
	public void createDisplay() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		frame.setBounds(800, 800, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		status = new JTextField();
		status.setFont(new Font("Tahoma", Font.BOLD, 12));
		status.setBounds(249, 634, 298, 91);
		frame.getContentPane().add(status);
		status.setColumns(10);
		
		columnLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		columnLabel.setBounds(91, 61, 126, 38);
		frame.getContentPane().add(columnLabel);
	
		playerLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		playerLabel.setBounds(513, 32, 298, 96);
		frame.getContentPane().add(playerLabel);
		
		header = new JLabel("Connect 4!");
		header.setFont(new Font("Tahoma", Font.BOLD, 40));
		header.setBounds(288, 11, 266, 68);
		frame.getContentPane().add(header);
		
		placePiece = new JButton("Start Game");
		placePiece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		placePiece.setFont(new Font("Tahoma", Font.BOLD, 11));
		placePiece.setBackground(new Color(0, 255, 128));
		placePiece.setBounds(302, 109, 200, 30);
		frame.getContentPane().add(placePiece);
		
		restart = new JButton("restart Game");
		restart.setBackground(Color.RED);
		restart.setBounds(302, 109, 200, 30);
		frame.getContentPane().add(restart);
		
		playerToggle.setSelected(true);
		playerToggle.setBounds(62, 98, 161, 29);
		frame.getContentPane().add(playerToggle);
		
		
		//Creating and setting the board
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
		//create buttons for user to select their desired column
		int x1 = 150;
		int y1 = 200;
			x1 = 150;
			for(int i = 0; i < buttons.length; i++) {
				x1 += 55;
				buttons[i] = new JButton(i+"");
				buttons[i].setBounds(x1,y1,50,50);
				frame.getContentPane().add(buttons[i]);
				buttons[i].addActionListener(this);
			}
			y1+=65;
		
		//Flipping a coin to see who goes first
		updatePlayerToggle(coinFlip());
		initialize();
		frame.setVisible(true); //displays the board
	}
	
	private boolean coinFlip() {
		return (int)(Math.random() * 2)==1;
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
			status.setText("Player " + (!playerToggle.isSelected()?1:2) + " scored a WIN!");
			//placePiece.setVisible(false);
			restart.setVisible(true);
			isWin = true;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<buttons.length;i++) {
			if(e.getSource()==buttons[i]) {
				b.dropPiece(playerToggle.isSelected()?1:2, i);
				updateBoard(p1,p2);
				updatePlayerToggle(!playerToggle.isSelected());
				winCheck();
			}
		}
	}
	
	private void initialize() {
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart.setVisible(false);
				//placePiece.setVisible(true);
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
