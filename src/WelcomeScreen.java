import java.awt.CardLayout;
import java.awt.Component;
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

        setLayout(BOX_LAYOUT);
        title = new JLabel();
        Component add = add(title);

        //add formatting here
        go = new JButton("New Game");
        quit = new JButton("Quit");

        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                goButtonActionListener();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                quitButtonActionListener();
            }
        });

        Component add1 = add(go);
        Component add2 = add(quit);
    }
    private static final BoxLayout BOX_LAYOUT = new BoxLayout(this, BoxLayout.PAGE_AXIS);

}
