
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;

import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.PAGE_AXIS;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.JPanel;

/**
 *
 * @author alumno
 */
public class WelcomeScreen extends JPanel {

    JLabel title;
    JButton go;
    JButton quit;

    MainWindow mw;

    /**
     *
     * @param t
     */
    public void setTitle(String t) {
        title.setText(t);
    }

    /**
     *
     */
    public void quitButtonActionListener() {
        if (showConfirmDialog(this, "Are you sure?") == OK_OPTION) {
            exit(0);
        }
    }

    /**
     *
     */
    public void goButtonActionListener() {
        mw.showCard("Two");
    }

    /**
     *
     * @param mw
     */
    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton(NEW__GAME);
        quit = new JButton(QUIT);

        go.addActionListener((ActionEvent event) -> {
            goButtonActionListener();
        });

        quit.addActionListener((ActionEvent event) -> {
            quitButtonActionListener();
        });

        add(go);
        add(quit);
    }
    private static final String QUIT = "Quit";
    private static final String NEW__GAME = "New Game";

}
