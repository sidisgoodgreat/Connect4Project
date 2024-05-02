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

public class Connect4Game1 {

	private JFrame frame;
	private JTextField Column;
	private JTextField isError;
	private Board b = new Board();
	private JTextField winStatus;
	private boolean isWin = false;
	private JLabel Header;
	private JButton[][] buttons = new JButton[6][7];
	private JRadioButton P1;
	private JRadioButton P2;
	private JButton Restart;
	private boolean p1Turn = false;
	private boolean p2Turn = false;
	private int whoStarts = (int) (Math.random() * 2);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect4Game1 window = new Connect4Game1();
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
	public Connect4Game1() {
		initialize();
		int x = 150;
		int y = 252;
		for(int r = 0; r < buttons.length; r++) {
			x = 150;
			for(int c = 0; c < buttons[0].length; c++) {
				x += 55;
				buttons[r][c] = new JButton();
				buttons[r][c].setBounds(x,y,50,50);
				frame.getContentPane().add(buttons[r][c]);
			}
			y+=65;
		}
		if(whoStarts == 0) {
			P2.setVisible(false);
		}
		else if(whoStarts == 1) {
			P1.setVisible(false);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * PLAYER 1 IS RED!, PLAYER 2 IS ORANGE!
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		frame.setBounds(800, 800, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton placePiece = new JButton("Place Piece");
		placePiece.setFont(new Font("Tahoma", Font.BOLD, 11));
		placePiece.setBackground(new Color(0, 255, 128));
		placePiece.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				Restart.setVisible(false); 
				int rowNum = 0;
				if(!isWin) {
					try {
						whoStarts = -1;
						//ButtonGroup bG = new ButtonGroup();
						int radioButtonToInt = 0;
						String col = Column.getText();
						int colStrToInt = Integer.valueOf(col);
						if((P1.isSelected() || P2.isSelected()) && (!(P1.isSelected() && P2.isSelected()))) {
							if(P1.isSelected()) radioButtonToInt = 1;
							else if(P2.isSelected()) radioButtonToInt = 2;
							if((colStrToInt >= 0 && colStrToInt < b.getCols())) {
								isError.setText("");
								rowNum = b.dropPiece(radioButtonToInt, colStrToInt);
								if(radioButtonToInt == 1) {
									p2Turn = false;
									p1Turn = true;
									buttons[rowNum][colStrToInt].setBackground(Color.RED);
									buttons[rowNum][colStrToInt].setOpaque(true);
								}
								else if(radioButtonToInt == 2){
									p1Turn = false;
									p2Turn = true;
									buttons[rowNum][colStrToInt].setBackground(Color.ORANGE);
									buttons[rowNum][colStrToInt].setOpaque(true);
								}
								
								JOptionPane.showMessageDialog(null,  "Player " + radioButtonToInt + " dropped a piece in column " + col);
								if(b.winCheckAll() > 0) {
									//color set:
									winStatus.setBackground(Color.GREEN);
									if(b.winCheckAll() >= 1 && b.winCheckAll() <= 4) {
										winStatus.setText("Player " + radioButtonToInt + " scored a WIN!");
									}
									Thread.sleep(1000);
									placePiece.setVisible(false);
									Restart.setVisible(true);
									isWin = true;
									P1.setSelected(false);
									P2.setSelected(false);
									return;
								}
							}
							else {
								JOptionPane.showMessageDialog(null,  "Please enter a valid column number, with the max being 6!");
								isError.setText("ERROR!");
							}
							if(p1Turn && colStrToInt >= 0 && colStrToInt <= 6) {
								P1.setVisible(false);
								P2.setVisible(true);
							}
							
							else if(p2Turn && colStrToInt >= 0 && colStrToInt <= 6) {
								P2.setVisible(false);
								P1.setVisible(true);
							}
						}
						
						
						else {
							JOptionPane.showMessageDialog(null,  "Please choose your player number, either 1 or 2!");
							isError.setText("ERROR!");
						}
						b.print();
					}
					catch(Exception a) {
						b.print();
						isError.setText("ERROR!");
					}
				}
				P1.setSelected(false);
				P2.setSelected(false);
				Column.setText("");
				
			}
		});
		placePiece.setBounds(302, 211, 200, 30);
		frame.getContentPane().add(placePiece);
		
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
		
		JLabel Label1 = new JLabel("Choose the player:");
		Label1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Label1.setBounds(91, 61, 126, 38);
		frame.getContentPane().add(Label1);
		
		JLabel label2 = new JLabel("Enter Column Number:");
		label2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label2.setBounds(563, 56, 136, 49);
		frame.getContentPane().add(label2);
		
		Header = new JLabel("Connect 4!");
		Header.setFont(new Font("Tahoma", Font.BOLD, 40));
		Header.setBounds(288, 11, 266, 68);
		frame.getContentPane().add(Header);
		
		P1 = new JRadioButton("Player 1");
		P1.setFont(new Font("Tahoma", Font.BOLD, 11));
		P1.setBounds(91, 98, 109, 23);
		frame.getContentPane().add(P1);
		
		P2 = new JRadioButton("Player 2");
		P2.setFont(new Font("Tahoma", Font.BOLD, 11));
		P2.setBounds(91, 124, 109, 23);
		frame.getContentPane().add(P2);
		
		Restart = new JButton("Restart Game");
		Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int r = 0; r < buttons.length; r++) {
					for(int c = 0; c < buttons[r].length; c++) {
						buttons[r][c].setBackground(null);
						
					}
				}
				Restart.setVisible(false);
				placePiece.setVisible(true);
				P1.setSelected(false);
				P2.setSelected(false);
				Column.setText("");
				winStatus.setText("");
				winStatus.setBackground(Color.WHITE);
				b.clear();
				isWin = false;
				p1Turn = false;
				p2Turn = false;
				P1.setVisible(true);
				P2.setVisible(true);
				whoStarts = (int)(Math.random() * 2) + 1;
				
			}
		});
		Restart.setBackground(Color.RED);
		Restart.setBounds(302, 212, 200, 29);
		frame.getContentPane().add(Restart);
		
	}
}
