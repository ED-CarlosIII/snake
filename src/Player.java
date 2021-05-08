
import java.awt.Color;

/**
 *
 * @author alumno
 */
public class Player {

    private final String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     *
     * @param no int
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     *
     * @return int
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param posn
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     *
     * @param posn
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     *
     * @param c
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     *
     * @return Color
     */
    public Color returnPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @param a
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     *
     * @return int
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
