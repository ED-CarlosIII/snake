
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

    MainWindow mw;

    private static final String TXT_NEW_GAME = "New Game";
    private static final String TXT_QUIT = "Quit";
    private static final String TXT_CONFIRMATION = "Are you sure?";
    private static final String TXT_TWO = "Two";

    public void setTitle(String t) {
        title.setText(t);
    }

    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, TXT_CONFIRMATION) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    public void goButtonActionListener() {
        mw.showCard(TXT_TWO);
    }

    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton(TXT_NEW_GAME);
        quit = new JButton(TXT_QUIT);

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
