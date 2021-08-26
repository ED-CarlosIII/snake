
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WelcomeScreen extends JPanel {

    JLabel title;
    JButton go;
    JButton quit;
    private static final String SALIR = "Quit";
    private static final String NUEVO_JUEGO = "New Game";


    MainWindow mw;

    public void setTitle(String t) {
        title.setText(t);
    }

    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, CONFIRMACION) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
    private static final String CONFIRMACION = "Are you sure?";

    public void goButtonActionListener() {
        mw.showCard("Two");
    }

    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton(NUEVO_JUEGO);
        quit = new JButton(SALIR);

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
