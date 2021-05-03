
import javax.swing.SwingUtilities;

/**
 * Main class of the game.
 * @author Borja Ortiz Hernández
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mw = new MainWindow();
                mw.setSize(600, 400);
                mw.setTitle("Snake and Ladder");
                mw.setVisible(true);
            }
        });
    }

}
