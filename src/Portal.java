
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author mac
 */
public class Portal {

    private int nature; //-1 for snake , +1 for ladder
    private int start;
    private int end;

    /**
     *
     * @param maxCells parametro
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
     * @return Integer
     */
    public int returnNature() {
        return nature;
    }

    /**
     *
     * @return Integer
     */
    public int returnStart() {
        return start;
    }

    /**
     *
     * @return Integer
     */
    public int returnEnd() {
        return end;
    }

}
