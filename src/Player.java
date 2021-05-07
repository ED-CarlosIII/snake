
import java.awt.Color;

/**
 * Clase Player. Es el modelo de objeto que usaremos en el programa.
 * @author Jose Luis Sáez Sánchez
 */
public class Player {

    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     * Constructor que define el numero del jugador.
     * @param no
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     *
     * @return la posición del jugador
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     *
     * @return el nombre del jugador
     */
    public String getName() {
        return name;
    }

    /**
     * define la posición del jugador
     * @param posn 
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     * inicializa posición.
     * @param posn 
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     * Guarda el color seleccionado por el jugador.
     * @param c 
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     *
     * @return el color seleccionado por el jugador.
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * inicializa el marcador del jugador
     * @param a 
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     *
     * @return el marcador del jugador
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
