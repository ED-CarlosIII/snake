import java.awt.Color;

/**
 * Clase que crea los jugadores
 * @author 34602
 */
public class Player {
   /**
    * Atributos de la clase Player
    */
    private String name;
    private int playerPosn;
    private int playerScore;
    private Color playerColor;
	/**
         * Constructor de un objeto de tipo Player
         * @param no Entero que hace referencia al número del jugador a crear
         */
	public Player(int no){
	    name = "Player " + no;
	    playerPosn = 0;
	}
	/**
         * Método que devuelve la posición del jugador
         * @return Entero que devuelve la posición del jugador
         */
	public int getPosition(){
	    return playerPosn;
	}
	/**
         * Método que devuelve el nombre del jugador
         * @return String que devuelve el nombe del jugador
         */
	public String getName(){
	    return name;
	}
	/**
         * Método para establecer la posición del jugador
         * @param posn Entero que establece la posición del jugador
         */
	public void setPosition(int posn){
	    playerPosn = posn;
	}
	/**
         * Método que establece la posición acutal del jugador
         * @param posn Entroo que establece la posición actual del jugador
         */
	public void incPosition(int posn){
	    playerPosn += posn;
	}
	/**
         * Método que establece el color del jugador
         * @param c Color que se establece como el del jugador
         */
	public void setPlayerColor(Color c){
	    playerColor = c;
	}
	/**
         * Método que devuelve el color del jugador
         * @return Devuelve el color del jugador
         */
	public Color getPlayerColor(){
	    return playerColor;
	}
	/**
         * Método que establece la puntuación del jugador
         * @param a Entero que establece la posición del jugador
         */
	public void incPlayerScore(int a){
	    playerScore += a;
	}
	/**
         * Método que devuelve la puntuación del jugador
         * @return Devuelve la puntuación del jugador
         */
	public int getPlayerScore(){
	    return playerScore;
	}

}
