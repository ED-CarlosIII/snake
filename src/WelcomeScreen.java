
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Define el titulo de la pantalla de bienvenida
 * @author Jose Luis Sáez Sánchez
 */
public class WelcomeScreen extends JPanel {

    private static final String quitButtonText = "Quit";
    private static final String goButtonText = "New Game";
    private static final String confirmDialogText = "Are you sure?";
    private static final String showcardTwoText = "Two";

    JLabel title;
    JButton go;
    JButton quit;

    MainWindow mw;

    /**
     *
     * @param t
     * Metodo que define el titulo de la ventana de bienvenida
     */
    public void setTitle(String t) {
        title.setText(t);
    }

    /**
     * Metodo boton salir
     */
    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, confirmDialogText) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Metodo boton go
     */
    public void goButtonActionListener() {
        mw.showCard(showcardTwoText);
    }

    /**
     *
     * @param mw
     * Ventana principal de la pantalla de bienvenida.
     */
    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton(goButtonText);
        quit = new JButton(quitButtonText);

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

}
