package CSAFinalProject;

import java.awt.Color;
import java.util.Random;

public class Opponent extends Player {

    public Opponent(Color c, int id) {
        super(c, id);
    }

    public int chooseColumn() {
        return (int) (Math.random()*7);
    }
}
