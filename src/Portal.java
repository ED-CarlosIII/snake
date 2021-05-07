
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author alumno
 */
public class Portal {

    private int nature; //-1 for snake , +1 for ladder
    private int start;
    private int end;

    /**
     * Método constructor de un objeto que representa un portal
     * @param maxCells número máximo de celdas
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
     * Método que devuelve el tipo de portal
     * @return tipo de portal
     */
    public int returnNature() {
        return nature;
    }

    /**
     * Método que devuelve el principio del portal
     * @return principio del portal
     */
    public int returnStart() {
        return start;
    }

    /**
     * Método que devuelve el final del portal
     * @return final del portal
     */
    public int returnEnd() {
        return end;
    }

}
