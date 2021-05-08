
import java.util.Random;

/**
 * Clase Portal
 * @author Juan José Férez Cerezo
 */
public class Portal {

    private int nature; //-1 for snake , +1 for ladder
    private int start;
    private int end;

    /**
     * Constructor
     * @param maxCells Maximo de celdas
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
     * Metodo devuelve nature
     * @return devuelve nature
     */
    public int returnNature() {
        return nature;
    }

    /**
     * Metodo devuelve comienzo
     * @return devuelve comienzo
     */
    public int returnStart() {
        return start;
    }

    /**
     * Metodo devuelve final
     * @return devuelve final
     */
    public int returnEnd() {
        return end;
    }

}
