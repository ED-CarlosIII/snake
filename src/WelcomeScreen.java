
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

    MainWindow mw;

    public void setTitle(String t) {
        title.setText(t);
    }

    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    public void goButtonActionListener() {
        mw.showCard("Two");
    }

    public WelcomeScreen(MainWindow mw) {
        this.mw = mw;

        final BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

        setLayout(boxLayout);
        title = new JLabel();
        add(title);

        //add formatting here
        go = new JButton("New Game");
        quit = new JButton("Quit");

        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                goButtonActionListener();
            }
        };

        go.addActionListener(actionListener);

        final ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                quitButtonActionListener();
            }
        };

        quit.addActionListener(actionListener1);

        add(go);
        add(quit);
    }

}
