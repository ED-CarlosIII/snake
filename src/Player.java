
import java.awt.Color;

/**
 *
 * @author mac
 */
public class Player {

    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     *
     * @param no parametro
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     *
     * @return Integer
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     *
     * @return Integer
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param posn parametro
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     *
     * @param posn parametro
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     *
     * @param c parametro
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     *
     * @return Color
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @param a parametro
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     *
     * @return Integer
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
