
import java.awt.Point;
import java.util.Random;

/**
 * Generates snakes or ladders
 * @author Borja Ortiz Hernández
 * 
 */
public class Portal {

    private int nature; //-1 for snake , +1 for ladder
    private int start;
    private int end;

    /**
     *
     * @param maxCells. Maximum number of cells within the board. 
     * Creates random portals (snakes or ladders).
     */
    public Portal(int maxCells) {   //creates random portals
        Random luck = new Random();
        start = luck.nextInt(maxCells);
        end = luck.nextInt(maxCells);
        if (start < end) {
            nature = 1;
        } else {
            nature = -1;
        }
    }

    /**
     *
     * @return nature of the portal (snake or ladder)
     */
    public int returnNature() {
        return nature;
    }

    /**
     *
     * @return start of the portal
     */
    public int returnStart() {
        return start;
    }

    /**
     *
     * @return end of the portal
     */
    public int returnEnd() {
        return end;
    }

}
