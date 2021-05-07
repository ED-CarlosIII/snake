
import java.awt.Color;

/**
 *
 * @author alumno
 */
public class Player {

    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     * Método constructor de un objeto que representa un jugador
     * @param no número del jugador
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     * Método que devuelve la posición del jugador
     * @return posición del jugador
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     * Método que devuelve el nombre del jugador
     * @return nombre del jugador
     */
    public String getName() {
        return name;
    }

    /**
     * Método para establecer la posición de jugador
     * @param posn posición del jugador
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     * Método para incrementar la posición de jugador
     * @param posn número de posiciones
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     * Método para establecer el color del jugador
     * @param c objeto de tipo color
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     * Método que devuelve el color del jugador
     * @return objeto de tipo color
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * Método para incrementar la puntuación del jugador
     * @param a valor incremento puntuación 
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     * Método que devuelve la puntuación del juagdor
     * @return puntuación del jugador
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
