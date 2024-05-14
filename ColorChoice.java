package csaFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChoice {
	private JFrame frame;
	
	private JLabel p1Color,p2Color;
	private JSlider p1RSlider,
			p1GSlider,
			p1BSlider,
			p2RSlider,
			p2GSlider,
			p2BSlider;
	private JButton submitButton;
	/**
 	*Main method that creates and initializes the ColorChoice object
  	*/
	public static void main(String[] args) {
		ColorChoice f = new ColorChoice();
		f.initialize();
	}
	
	/**
	* @wbp.parser.entryPoint
  	* Initializes the GUI for choosing player colors
	*/
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(183, 66, 117, 29);
		frame.getContentPane().add(submitButton);

    //This right here, when the submit button is pressed, the GUIBoard(otherwise the game) is started up
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Player	p1 = new Player(getP1Color(),1),
						p2 = new Player(getP2Color(),2);
				GUIBoard2 g = new GUIBoard2(p1,p2);
			}
		});
		
		p1Sliders();
		p2Sliders();
		
		frame.setBounds(400,400,450,600);
		frame.setVisible(true);
	}
	/**
        * Gets the color chosen by Player 1 from slider values
        * @return The color chosen by Player 1
        */
	public Color getP1Color() {
		return new Color(p1RSlider.getValue(),p1GSlider.getValue(),p1BSlider.getValue());
	}
	/**
        * Gets the color chosen by Player 2 from slider values
        * @return The color chosen by Player 2
        */
	public Color getP2Color() {
		return new Color(p2RSlider.getValue(),p2GSlider.getValue(),p2BSlider.getValue());
	}
	/**
	* Creates sliders for Red, Green, Blue components of Player 1 to choose their colors
 	*Sets up listeners to update color preview
	*/
	private void p1Sliders() {
		p1RSlider = new JSlider();
		p1GSlider = new JSlider();
		p1BSlider = new JSlider();
		ChangeListener sliderUpdate=new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				p1Color.setBackground(getP1Color());
			}
		};
		
		p1RSlider.setForeground(Color.RED);
		p1RSlider.setMinorTickSpacing(1);
		p1RSlider.setPaintTicks(true);
		p1RSlider.setMaximum(255);
		p1RSlider.setValue(255);
		p1RSlider.setBackground(Color.RED);
		p1RSlider.setBounds(42, 145, 190, 29);
		frame.getContentPane().add(p1RSlider);
		p1RSlider.addChangeListener(sliderUpdate);
		
		
		p1GSlider.setValue(0);
		p1GSlider.setMinorTickSpacing(1);
		p1GSlider.setMaximum(255);
		p1GSlider.setPaintTicks(true);
		p1GSlider.setBackground(Color.GREEN);
		p1GSlider.setBounds(42, 214, 190, 29);
		frame.getContentPane().add(p1GSlider);
		p1GSlider.addChangeListener(sliderUpdate);
		
		p1BSlider.setValue(0);
		p1BSlider.setMinorTickSpacing(1);
		p1BSlider.setMaximum(255);
		p1BSlider.setPaintTicks(true);
		p1BSlider.setBackground(Color.BLUE);
		p1BSlider.setBounds(42, 297, 190, 29);
		frame.getContentPane().add(p1BSlider);
		p1BSlider.addChangeListener(sliderUpdate);
		
		p1Color = new JLabel("");
		p1Color.setBackground(getP1Color());
		p1Color.setOpaque(true);
		p1Color.setBounds(42, 362, 174, 162);
		frame.getContentPane().add(p1Color);
		
		
	}
	/**
	* Creates sliders for Red, Green, Blue components of Player 2 to choose their colors
 	*Sets up listeners to update color preview
	*/
	private void p2Sliders() {
		p2RSlider = new JSlider();
		p2GSlider = new JSlider();
		p2BSlider = new JSlider();
		
		ChangeListener sliderUpdate=new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				p2Color.setBackground(getP2Color());
			}
		};
		
		p2RSlider.setForeground(Color.RED);
		p2RSlider.setMinorTickSpacing(1);
		p2RSlider.setPaintTicks(true);
		p2RSlider.setMaximum(255);
		p2RSlider.setValue(255);
		p2RSlider.setBackground(Color.RED);
		p2RSlider.setBounds(242, 145, 190, 29);
		frame.getContentPane().add(p2RSlider);
		p2RSlider.addChangeListener(sliderUpdate);
		
		
		p2GSlider.setMinorTickSpacing(1);
		p2GSlider.setMaximum(255);
		p2GSlider.setValue(255);
		p2GSlider.setPaintTicks(true);
		p2GSlider.setBackground(Color.GREEN);
		p2GSlider.setBounds(242, 214, 190, 29);
		frame.getContentPane().add(p2GSlider);
		p2GSlider.addChangeListener(sliderUpdate);
		
		p2BSlider.setValue(0);
		p2BSlider.setMinorTickSpacing(1);
		p2BSlider.setMaximum(255);
		p2BSlider.setPaintTicks(true);
		p2BSlider.setBackground(Color.BLUE);
		p2BSlider.setBounds(242, 297, 190, 29);
		frame.getContentPane().add(p2BSlider);
		p2BSlider.addChangeListener(sliderUpdate);
		
		p2Color = new JLabel("");
		p2Color.setBackground(getP2Color());
		p2Color.setOpaque(true);
		p2Color.setBounds(242, 362, 174, 162);
		frame.getContentPane().add(p2Color);
	}
}
