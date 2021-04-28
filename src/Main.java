
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.invokeLater;

/**
 *
 * @author alumno
 */
public class Main {

    /**
     * @param args los argumentos
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
