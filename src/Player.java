
import java.awt.Color;

/**
 * Clase Jugador
 * @author Juan José Férez Cerezo
 */
public class Player {

    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    /**
     * Constructor jugador
     * @param no Numero
     */
    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    /**
     * Metodo devolver posicion
     * @return Devuelvo posicion
     */
    public int getPosition() {
        return playerPosn;
    }

    /**
     * Metodo devolver nombre
     * @return Devuelve nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo establecer posicion
     * @param posn posicion
     */
    public void setPosition(int posn) {
        playerPosn = posn;
    }

    /**
     * Metodo incrementar posicion
     * @param posn posicion
     */
    public void incPosition(int posn) {
        playerPosn += posn;
    }

    /**
     * Metodo establecer color jugador
     * @param c color
     */
    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    /**
     * Metodo devolver color jugador
     * @return Devuelve color jugador
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * Metodo incrementar puntuacion jugador
     * @param a puntuacion a incrementar
     */
    public void incPlayerScore(int a) {
        playerScore += a;
    }

    /**
     * Metodo que devuelve puntuacion jugador
     * @return Devuelve puntuacion jugador
     */
    public int getPlayerScore() {
        return playerScore;
    }

}
