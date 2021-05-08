
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase Pantalla de bienvenida
 * @author Juan José Férez Cerezo
 */
public class WelcomeScreen extends JPanel {
    
    /**
     * Variable estatica boton borrar
     */
    private static final JButton J_BUTTON_QUIT = new JButton("Quit");
    /**
     * Variable estatica boton inicio
     */
    private static final JButton J_BUTTON_GO = new JButton("New Game");
    
    JLabel title;
    JButton go;
    JButton quit;

    MainWindow mw;

    /**
     * Constructor
     * @param mw pantalla inicial
     */
    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = J_BUTTON_GO;
        quit = J_BUTTON_QUIT;

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

        add(go);
        add(quit);
    }
    
    /**
     * Metodo establer titulo
     * @param t titulo
     */
    public void setTitle(String t) {
        title.setText(t);
    }

    /**
     * ActionListener boton borrar
     */
    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * ActionListener boton ir hacia delante
     */
    public void goButtonActionListener() {
        mw.showCard("Two");
    }

    
    
}
