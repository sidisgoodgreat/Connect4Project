//For a demo on how a GUI can be used, refer to the rock paper scissors example I implemented using the slides provided:
//Link to images used: https://drive.google.com/drive/folders/1O1OmwQZD_pi3_Ml0TmOQSe33zl00Nak6?usp=sharing
//Eclipse Java Project name: RockPaperScissors
package RPC;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui1 extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Icon ic1 = new ImageIcon ("rock1.jpg");
  	Icon ic2 = new ImageIcon ("paper1.jpg");
 	Icon ic3 = new ImageIcon ("scissors1.jpg");
  	Icon ic4 = new ImageIcon ("white.jpg");
  	JLabel l1, l2, l3, l4;
  	JButton b1, b2, b3, b4, b5;
  	String r = "rock";
  	String p = "paper";
  	String s = "scissors";
  	//b2 uses ic2, corner at 130, 80
  	//b3 uses ic3, corner at 250, 80
  	//b4 uses ic4, corner at 10, 280
  	//b5  uses ic4, corner at 250, 280
	public Gui1() {
		setSize (600, 500);
	   	setLayout (null);
	   	setVisible (true);
		l1=new JLabel ("Click on your choice");
	    l1.setBounds (10,30,500,40);
	    l1.setFont (new Font("Calibri", Font.PLAIN, 28));
	    l1.setForeground (Color.black);
	    l2 = new JLabel ("You");
	    l2.setBounds (10, 230, 200, 40);
	    l2.setFont(new Font ("Calibri", Font.PLAIN, 28));
	    l2.setForeground (Color.blue);
	    l3 = new JLabel ("Computer");
	    l3.setBounds (250, 230, 200, 40);
	    l3.setFont(new Font ("Calibri", Font.PLAIN, 28));
	    l3.setForeground (Color.green);
	    l4 = new JLabel (" ");
	    l4.setBounds (200, 400, 300, 40);
	    l4.setFont (new Font ("Calibri", Font.PLAIN, 28));
	    l4.setForeground (Color.red);
	    add (l1);
	    add (l2);
	    add (l3);
	    add (l4);
	    l4.setForeground(Color.red);
	  	b1=new JButton (ic1);
	  	b1.setBounds(10, 80, 110, 110);
	  	b2=new JButton (ic2);
	  	b2.setBounds(130, 80, 110, 110);
	  	b3=new JButton (ic3);
	  	b3.setBounds(250, 80, 110, 110);
	  	b4=new JButton (ic4);
	  	b4.setBounds(10, 280, 110, 110);
	  	b5=new JButton (ic4);
	  	b5.setBounds(250, 280, 110, 110);
	  	add (l4);
	  	add (b1);
	  	add (b2);
	  	add (b3);
	  	add (b4); 
	  	add (b5);
	  	//add (b5);
	  	b1.addActionListener (this); 
	  	b2.addActionListener (this); 
	  	b3.addActionListener (this); 

	  	setLayout (null);
	  	//Icon ic4 = new ImageIcon (“white.jpg");
	  	

	  	
	}
	public void actionPerformed (ActionEvent e)
	{
	    String yourChoice = " ";
	    String comChoice = " ";

	    if (e.getSource () == b1)
	    {
	      b4.setIcon (ic1);
	      yourChoice = r;
	    }
	    if(e.getSource () == b2) 
	    {
	      b4.setIcon (ic2);
	      yourChoice = p;
	    }
	    
	    if(e.getSource () == b3)
	    {
	      b4.setIcon (ic3);
	      yourChoice = s;
	    }
	    if(e.getSource () == b3)
	    {
	      b4.setIcon (ic3);
	      yourChoice = s;
	    }  

	    comChoice = computerChoice ();
	    whoWon(yourChoice, comChoice);

	}
	public String computerChoice ()
	  {
	    String com = " ";
	    
	    int rNum = (int) (Math.random () * 3 + 1);
	    if (rNum == 1)
	    {
	      b5.setIcon (ic1);
	      com = r;
	    }
	    else if (rNum == 2)
	    {
	      b5.setIcon (ic2);
	      com = p;
	    }
	    else
	    {
	      b5.setIcon (ic3);
	      com = s;
	    }
	    
	    return com;
	  }
	public void whoWon (String you, String com)
	  {
	    if (you.equals (com))
	      l4.setText ("It's a draw.");
	    else if((you.equals(s) && com.equals(p)) || (you.equals(p) && com.equals(r)) || (you.equals(r) && com.equals(s)))
	    	 l4.setText ("You won.");
	    else if((com.equals(s) && you.equals(p)) || (com.equals(p) && you.equals(r)) || (com.equals(r) && you.equals(s)))
	    	 l4.setText ("The computer won.");

	  }



	public static void main (String [] args) 
	{
	    	new Gui1 ();
	}

}
