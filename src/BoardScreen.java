
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
 *
 * @author Antonio José Martínez González
 */
public class BoardScreen extends JPanel {

    /**
     *  atributos
     */
    private int player = 0;
    private final BoardDrawing bd;
    private final JPanel stats;
    private final JLabel dieResults;
    private final JLabel whichPlayer;
    private final JLabel extraInfo;
    private int maxPlayers = 1;
    private int currPlayer = 0;
    protected ArrayList<Portal> portals;
    private ArrayList<Player> players;
    private int x;
    private int y;
    private JLabel success;
    private JButton roll;
    private MainWindow mw;

    private JButton go;
    private JButton quit;

    /**
     *
     * @param mw parametro que establece ventana principal
     */
    public BoardScreen(MainWindow mw) {
        this.mw = mw;

        currPlayer = 0;

        go = new JButton("New Game");
        quit = new JButton("Quit");

        go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                goButtonActionListener();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                quitButtonActionListener();
            }
        });

        players = new ArrayList<Player>();
        players.add(new Player(getCurrPlayer()));
        //for(int i = 0;i < returnMaxPlayers();i++)
        //    players.add(new Player(i));
        //get and add player(s) names

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        x = y = 8;

        bd = new BoardDrawing(getX(), getY(), this);
        bd.setVisible(true);
        //bd.setSize(getSize());

        int sw = getSize().width;
        int sh = getSize().height;
        int a = (int) (0.75 * ((sw > sh) ? sh : sw));

        bd.setSize(a, a);

        add(bd);

        stats = new JPanel();
        stats.setLayout(new BoxLayout(getStats(), BoxLayout.X_AXIS));
        add(stats);

        stats.add(go);
        stats.add(quit);

        //String playername = "Player 1";
        //currPlayer = 0;
        whichPlayer = new JLabel();
        whichPlayer.setText(players.get(currPlayer).getName());
        stats.add(whichPlayer);

        extraInfo = new JLabel();

        success = new JLabel("");
        
        //modify action listener to move between the n players 
        //outside needs to know some amount of player data which may be got be asking and passing to inside
        //no need to create separate stores outside
        //may need more functions inside to communicate for this reason
        roll = new JButton("Roll the die!");
        roll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random die = new Random();
                int a = die.nextInt(6) + 1;
                getDieResults().setText("You rolled a " + a);
                player += a;
                //bd.setPlayer(player);
                getBd().setPlayer(a, getCurrPlayer());
                //bd.ensurePlayerPosition();
                getExtraInfo().setText(getBd().ensurePlayerPosition(getCurrPlayer()));
                getBd().repaint();

                getPlayers().get(getCurrPlayer()).incPlayerScore(1);

                for (Player p : getPlayers()) {
                    if (p.getPosition() >= getX() * getY() - 1) {
                        getSuccess().setText("And the winner is: " + p.getName() + "\nYour score: " + p.getPlayerScore());
                        getRoll().setVisible(false);
                    }
                }

                if (getCurrPlayer() == getMaxPlayers() - 1) {
                    currPlayer = 0;
                } else {
                    currPlayer += 1;
                }

                //currPlayer = players.size() - 1;
                getWhichPlayer().setText(getPlayers().get(getCurrPlayer()).getName());

            }
        });
        roll.setVisible(true);

        stats.add(roll);

        dieResults = new JLabel();
        stats.add(dieResults);

        stats.add(extraInfo);
        stats.add(success);
        
    }

    /**
     *
     */
    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     *
     */
    public void goButtonActionListener() {
        getMw().showCard("Two");
        //mw.setBoard();
        getMw().resetAll();
    }

    /**
     *
     * @param m
     */
    public void setMaxPlayers(int m) {
        maxPlayers = m;
    }

    /**
     *
     * @return
     */
    public int returnMaxPlayers() {
        return getMaxPlayers();
    }

    /**
     *
     */
    public void setUpPlayers() {
        players = new ArrayList<Player>();
        for (int i = 0; i < returnMaxPlayers(); i++) {
            getPlayers().add(new Player(i));
        }
        //get and add player(s) names

        //manual color entry - automate later
       if (0 < returnMaxPlayers()) {
            getPlayers().get(0).setPlayerColor(Color.green);
        }
        if (1 < returnMaxPlayers()) {
            getPlayers().get(1).setPlayerColor(Color.blue);
        }
        if (2 < returnMaxPlayers()) {
            getPlayers().get(2).setPlayerColor(Color.red);
        }

    }


    /**
     * @return the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @return the bd
     */
    public BoardDrawing getBd() {
        return bd;
    }

    /**
     * @return the stats
     */
    public JPanel getStats() {
        return stats;
    }

    /**
     * @return the dieResults
     */
    public JLabel getDieResults() {
        return dieResults;
    }

    /**
     * @return the whichPlayer
     */
    public JLabel getWhichPlayer() {
        return whichPlayer;
    }

    /**
     * @return the extraInfo
     */
    public JLabel getExtraInfo() {
        return extraInfo;
    }

    /**
     * @return the maxPlayers
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * @return the currPlayer
     */
    public int getCurrPlayer() {
        return currPlayer;
    }

    /**
     * @return the portals
     */
    public ArrayList<Portal> getPortals() {
        return portals;
    }

    /**
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the success
     */
    public JLabel getSuccess() {
        return success;
    }

    /**
     * @return the roll
     */
    public JButton getRoll() {
        return roll;
    }

    /**
     * @return the mw
     */
    public MainWindow getMw() {
        return mw;
    }

    /**
     * @return the go
     */
    public JButton getGo() {
        return go;
    }

    /**
     * @return the quit
     */
    public JButton getQuit() {
        return quit;
    }

}
