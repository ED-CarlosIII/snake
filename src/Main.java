
import javax.swing.SwingUtilities;

/**
 * Metodo main del programama. llama y configura la pantalla inicial
 * @author Jose Luis Sáez Sánchez
 */
public class Main {

    /**Metodo principal que carga a MAinWindow que llama al resto de metodos que hay detras para arrancar la aplicación.
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
