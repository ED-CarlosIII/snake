
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
     *
     * @param no ¿?
     * 
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     *
     * @return devuelve
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     *
     * @return devuelve
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param posn posición
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     *
     * @param posn posición
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     *
     * @param c ccoloor
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     *
     * @return algo
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @param a score del jugador
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     *
     * @return devuelve datos
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
