
import java.awt.Color;

public class Player {

    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;

    public Player(int no) {
        name = "Player " + no;
        playerPosn = 0;
    }

    public int devolverPosicion() {
        return playerPosn;
    }

    public String devolverNombre() {
        return name;
    }

    public void añadirPosicion(int posn) {
        playerPosn = posn;
    }

    public void aumentarPosicion(int posn) {
        playerPosn += posn;
    }

    public void añadirColorJugador(Color c) {
        playerColor = c;
    }

    public Color devolverColorJugador() {
        return playerColor;
    }

    public void aumentarPuntosJugador(int a) {
        playerScore += a;
    }

    public int devolverPuntuacionJugador() {
        return playerScore;
    }

}
