package CSAFinal;
import java.awt.Color;
import java.util.Scanner;

public class Game {
	/**
 	*Creates the board and the player instance variables
  	*/
	private GUIBoard board;
	private Player p1,p2;

	/**
 	*asks the user which color and sets each player to desired color
  	*/
	public Game() {
		Scanner s = new Scanner(System.in);
		System.out.println("Choose Color?");
		
		String choice = s.next();
		
		Color p1Color,p2Color;
		
		if(choice.equals("y")) {
			System.out.println("Player 1, Choose RGB Values(press enter after each value");
			
			p1Color = new Color(s.nextInt(),s.nextInt(),s.nextInt());
			
			System.out.println("Player 2, Choose RGB Values(press enter after each value");
			
			p2Color = new Color(s.nextInt(),s.nextInt(),s.nextInt());
		}else {
			p1Color=Color.red;
			p2Color=Color.yellow;
		}
		p1=new Player(p1Color,1);
		p2=new Player(p2Color,2);
		
		board=new GUIBoard(p1,p2);
	}
	
}
