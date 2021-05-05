import java.awt.Point;
import java.util.Random;

/**
 * Clase encaargada del portal
 * @author 34602
 */
public class Portal {
    /**
     * Atributos de la clase
     */
    private int nature; //-1 for snake , +1 for ladder
    private int start;
    private int end;
    /**
     * Método que crea los portales del juego
     * @param maxCells Entero que establece el número de portales aleatorios a crear
     */
    public Portal(int maxCells){   //creates random portals
        Random luck = new Random();
        start = luck.nextInt(maxCells);
        end = luck.nextInt(maxCells);
        if(start < end)
           nature = 1;
        else nature = -1;
    }
    /**
     * Método que devuelve que tipo de portal es
     * @return Entero que dice que tipo de portal es
     */
    public int returnNature(){
    	return nature;
    }
    /**
     * Método que devuelve donde empieza el portal
     * @return Entero que dice donde empieza el portal
     */
    public int returnStart(){
    	return start;
    }
    /**
     * Método que devuelve donde acaba el portal
     * @return Entero que dice donde acaba el portal
     */
    public int returnEnd(){
    	return end;
    }
    
}
