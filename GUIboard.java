package CSAFinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.EventQueue;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GUIBoard implements ActionListener{

    private JFrame frame;
    private JTextField 	status;
    private JTextField score1;
    private JTextField score2;

    private int scoreOfOne;
    private int scoreOfTwo ;

    private Board b = new Board();
    private boolean gameMode;
    private String p2Text;
    private JLabel[][] displayBoard = new JLabel[6][7];
    private JButton restart, placePiece;
    private JToggleButton playerToggle = new JToggleButton();//if it is selected, then it is Player 1
    private JLabel 	header,
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
    public GUIBoard() {

        //Initializing and giving traits to the display features, with the default colors.
        p1 = new Player(Color.red,1);
        p2 = new Player(Color.yellow,2);
        createDisplay();

    }
    /**
     * Constructs the GUIBoard with specified players and game mode.
     * @param p1 the first player
     * @param p2 the second player
     * @param isAi true if the second player is an AI, false otherwise
     */
    public GUIBoard(Player p1, Player p2, boolean isAi) {
        this.p1 = p1;
        this.p2 = p2;
        this.gameMode = isAi;
        this.p2Text = isAi?"Computer":"Player 2";
        createDisplay();
        scoreOfOne = 0;
        scoreOfTwo = 0;
    }
    /**
     * Creates the display components for the game.
     */
    public void createDisplay() {

        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
        frame.setBounds(800, 800, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        status = new JTextField();
        score1 = new JTextField();
        score2 = new JTextField();

        status.setFont(new Font("Tahoma", Font.BOLD, 12));
        status.setBounds(249, 634, 298, 91);
        frame.getContentPane().add(status);
        status.setColumns(10);

        score1.setFont(new Font("Tahoma",Font.BOLD,12));
        score1.setBounds(600,140,135,45);
        frame.getContentPane().add(score1);
        score1.setColumns(5);

        score2.setFont(new Font("Tahoma",Font.BOLD,12));
        score2.setBounds(600,180,135,45);
        frame.getContentPane().add(score2);
        score2.setColumns(5);


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
                for(int i = 0; i < buttons.length; i++) {
                    buttons[i].setVisible(true);
                }
                placePiece.setVisible(false);
                restart.setVisible(false);

                if (!playerToggle.isSelected()&&gameMode) {
                    b.dropPiece(2, p2.chooseColumn());
                    updateBoard(p1,p2);
                    winCheck();
                    updatePlayerToggle(!playerToggle.isSelected());
                }

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
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(false);
        }

        updatePlayerToggle(coinFlip());
        initialize();
        frame.setVisible(true); //displays the board

        score1.setText("Player 1 Score: " + scoreOfOne);
        score2.setText(p2Text + " Score: " + scoreOfTwo);


    }
    /**
     * Starts the game with the computer if the computer is Player 2.
     */
    public void startsWithComputer() {
        if (playerToggle.getText().equals("Computer")) {
            dropPieceStuff(p2.getID(),p2.chooseColumn());
            updateBoard(p1,p2);
        }
    }
    /**
     * Simulates a coin flip to determine which player starts first.
     * @return true if Player 1 starts, false otherwise
     */
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
        playerToggle.setText(init?"Player 1":p2Text);
    }

    private void winCheck() {
        if(b.winCheckAll() > 0) {
            //color set:
            status.setBackground(Color.GREEN);
            status.setText((playerToggle.isSelected()?"Player 1":p2Text)+ " scored a WIN!");
            if (playerToggle.isSelected()) {
                scoreOfOne++;
                score1.setText("Player 1 Score: " + scoreOfOne);
            }
            else {
                scoreOfTwo++;
                score2.setText(p2Text + " Score: " + scoreOfTwo);
            }
            placePiece.setVisible(false);
            restart.setVisible(true);
            for(int i = 0; i < buttons.length; i++) {
                buttons[i].setVisible(false);
            }
        }
    }

    public void AIMode(int i) {
        Timer t = new Timer();
        dropPieceStuff(p1.getID(),i);
        for(int j = 0; j < buttons.length; j++) {
            buttons[j].setEnabled(false);
        }
        if (b.winCheckAll()<0) {
            t.schedule(new TimerTask() {
                public void run() {
                    dropPieceStuff(p2.getID(),p2.chooseColumn());
                    for(int x = 0; x < buttons.length; x++) {
                        if (b.fullColumn(x)==-1) {
                            buttons[x].setEnabled(true);
                        }
                        else {
                            buttons[x].setEnabled(false);
                        }
                    }
                }
            }, 500);
        }
    }
    /**
     * Drops a piece into the specified column and updates the game state.
     * @param id the player ID
     * @param col the column index
     */
    public void dropPieceStuff(int id, int col) {
        b.dropPiece(id,col);
        updateBoard(p1,p2);
        winCheck();
        updatePlayerToggle(!playerToggle.isSelected());
    }
    /**
     * Handles button click events for dropping pieces into columns.
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<buttons.length;i++) {
            if(e.getSource()==buttons[i]) {
                if (!gameMode) {
                    dropPieceStuff(playerToggle.isSelected()?1:2,i);
                }

                else {
                    AIMode(i);
                }

            }
        }
    }



    private void initialize() {
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                status.setText("");
                status.setBackground(Color.WHITE);

                updatePlayerToggle(coinFlip());
                restart.setVisible(false);
                for(int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(true);
                    buttons[i].setVisible(true);
                }
                b.clearBoard();
                startsWithComputer();
                updateBoard(p1,p2);


            }});
        playerToggle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                updatePlayerToggle(playerToggle.isSelected());
            }
        });

    }//End Method
}
