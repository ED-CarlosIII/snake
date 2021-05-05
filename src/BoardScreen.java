import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase que crea la pantalla del tablero para jugar
 * @author 34602
 */
public class BoardScreen extends JPanel{

	/**
	 * Atributos necesarios en la clase BoardScreen
	 */
	int player = 0;
	BoardDrawing bd; 
	JPanel stats;
	JLabel dieResults;
	JLabel whichPlayer;
	JLabel extraInfo;
	int maxPlayers = 1;
	int currPlayer = 0;
	ArrayList<Portal> portals;
	ArrayList<Player> players;
	int x;
	int y;
	JLabel success;
	JButton roll;
	
	MainWindow mw;
	
	JButton go;
	JButton quit;
    /**
     * Método correspondiente al botón salir del juego
     */
    public void quitButtonActionListener(){
	if(JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION)
	System.exit(0);
    }
    /**
     * Método correspondiente al botón de inicio de la pantalla principal
     */	
    public void goButtonActionListener(){
	mw.showCard("Two");
		//mw.setBoard();
	mw.resetAll();
    }
    /**
     * Establece el número máximo de jugadores que van a jugar
     * @param m Número máximo de jugadores que van a jugar
     */	
    public void setMaxPlayers(int m){
	maxPlayers = m;
    }
    /**
     * Método que debuelve el número máximo de jugadores que van a jugar
     * @return Un número entero correspondiente al número máximo de jugadores que juegan
     */	
    public int returnMaxPlayers(){
	return maxPlayers;
    }
    /**
     * Método encargado de configuar los jugadores
     */
    public void setUpPlayers(){
	players = new ArrayList<Player>();
	for(int i = 0;i < returnMaxPlayers();i++)
	    players.add(new Player(i));
		//get and add player(s) names
		
		//manual color entry - automate later
	    if(0 < returnMaxPlayers())players.get(0).setPlayerColor(Color.green);
	    if(1 < returnMaxPlayers())players.get(1).setPlayerColor(Color.blue);
	    if(2 < returnMaxPlayers())players.get(2).setPlayerColor(Color.red);
		
    }
    /**
     * Método que crea la pantalla principal del tablero
     * @param mw Pantalla principal sobre la que se crea el tablero
     */	
    public BoardScreen(MainWindow mw){
	this.mw = mw;
            int name = 0;
		
	currPlayer = name;
            
        String new_Game = "New Game";		
	go = new JButton(new_Game);
            
        String quit1 = "Quit";	
        quit = new JButton(quit1);	
		
	go.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent event){
		goButtonActionListener();
	    }
	    });
		
	quit.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent event){
		quitButtonActionListener();
	    }
	    });
				
	players = new ArrayList<Player>();
	players.add(new Player(currPlayer));
		//for(int i = 0;i < returnMaxPlayers();i++)
		//    players.add(new Player(i));
		//get and add player(s) names
		
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        int name1 = 8;		
	x = y = name1;
		
        dibujarTablero();		 
		
        crearEstadisticas();
	
	asignarEstadisticas();
		
	tirarDados();
	roll.setVisible(true);
          
	stats.add(roll);
				
	dieResults = new JLabel();
	stats.add(dieResults);
		
	stats.add(extraInfo);
	stats.add(success);
	
	}
    /**
     * Método encargado de tirar los dados para seguir jugando
     */
    private void tirarDados() {
        //modify action listener to move between the n players
        //outside needs to know some amount of player data which may be got be asking and passing to inside
        //no need to create separate stores outside
        //may need more functions inside to communicate for this reason
        roll = new JButton("Roll the die!");
        roll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Random die = new Random();
                int a = die.nextInt(6) + 1;
                dieResults.setText("You rolled a " + a);
                player += a;
                //bd.setPlayer(player);
                bd.setPlayer(a, currPlayer);
                //bd.ensurePlayerPosition();
                extraInfo.setText(bd.ensurePlayerPosition(currPlayer));
                bd.repaint();
                
                players.get(currPlayer).incPlayerScore(1);
                
                for(Player p: players){
                    if(p.getPosition() >= x*y-1){
                        success.setText("And the winner is: " + p.getName() + "\nYour score: " + p.getPlayerScore());
                        roll.setVisible(false);
                    }
                }
                
                
                
                if(currPlayer == maxPlayers - 1)
                    currPlayer = 0;
                else
                    currPlayer += 1;
                
                //currPlayer = players.size() - 1;
                whichPlayer.setText(players.get(currPlayer).getName());
                
            }
        });
    }
    /**
     * Método encargado de asignar las estadísticas de los jugadores
     */
    private void asignarEstadisticas() {
        //String playername = "Player 1";
        
        //currPlayer = 0;
        
        whichPlayer = new JLabel();
        whichPlayer.setText(players.get(currPlayer).getName());
        stats.add(whichPlayer);
        
        extraInfo = new JLabel();
        
        success = new JLabel("");
    }
    /**
     * Método encargado de crear las estadísticas
     */
    private void crearEstadisticas() {
        stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.X_AXIS));
        add(stats);
        
        stats.add(go);
        stats.add(quit);
    }
    /**
     * Método encargado de dibujar el tablero
     */
    private void dibujarTablero() {
        bd = new BoardDrawing(x, y,this);
        bd.setVisible(true);
        //bd.setSize(getSize());
        
        int sw = getSize().width;
        int sh = getSize().height;
        int a = (int) (0.75*((sw > sh)?sh:sw));
        
        bd.setSize(a, a);
        
        add(bd);
    }
	
}
