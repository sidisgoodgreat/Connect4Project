package CSAFinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Welcome {
    private JLabel welcome;
    private JFrame frame;
    private JButton NonAI;
    private JButton AI;


    public static void main(String[] args) {
        Welcome f = new Welcome();
        f.initialize();
    }

    public void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        frame.setBounds(800, 800, 800, 800);

        welcome = new JLabel("Welcome to Connect 4!",SwingConstants.CENTER);
        welcome.setFont(new Font("Consolas", Font.ITALIC, 30));

        welcome.setBounds(25, 25, 350, 100);
        frame.getContentPane().add(welcome);

        AI = new JButton("Play with AI");
        AI.setBounds(200, 250, 400, 100);
        NonAI = new JButton("Play without AI");
        NonAI.setBounds(200,350,400,100);
        frame.getContentPane().add(AI);
        frame.getContentPane().add(NonAI);




        NonAI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==NonAI) {
                    ColorChoice c = new ColorChoice();
                    c.initialize();

                }
            }
        });

        AI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==AI) {
                    Player p1 = new Player(Color.red,1);
                    Opponent o1 = new Opponent(Color.green,2);
                    GUIBoard g = new GUIBoard(p1,o1,true);
                }
            }
        });


    }
}
