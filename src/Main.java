
import static javax.swing.SwingUtilities.invokeLater;

/**
 *
 * @author Ana Belen Madid Garcia
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        invokeLater(() -> {
            MainWindow mw = new MainWindow();
            mw.setSize(600, 400);
            mw.setTitle("Snake and Ladder");
            mw.setVisible(true);
        });
    }

}
