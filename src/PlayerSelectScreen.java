
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//needs massive aesthetic rewrites

/**
 * Pantalla de interacción con el usuario donde elige modo de juego. 
 * @author Jose Luis Sáez Sánchez
 */
public class PlayerSelectScreen extends JPanel {

    JButton go;
    JButton quit;
    MainWindow mw;

    JRadioButton opt1;
    JRadioButton opt2;
    JRadioButton opt3;

    /**
     *
     */
    public void quitButtonActionListener() {
        mw.showCard("One");
    }

    /**
     *
     */
    public void goButtonActionListener() {
        playerOptions();
        mw.s4.setUpPlayers();
        mw.showCard("Three");
    }

    /**
     *
     */
    public void playerOptions() {
        int m = 5;
        if (opt1.isSelected() == true) {
            m = 1;
        } else if (opt2.isSelected() == true) {
            m = 2;
        } else if (opt3.isSelected() == true) {
            m = 3;
        }
        mw.s4.setMaxPlayers(m);
    }

    /**
     * Clase que configura la pantalla de opciones al jugador
     * @param MainWindow
     */
    public PlayerSelectScreen(MainWindow mw) {
        final String radioButtonOption1Text = "1 Player(Default)";
        final String radioButtonOption2Text = "2 Players";
        final String radioButtonOption3Text = "3 Players";
        final String buttonGoText = "Customize Board";
        final String buttonQuitText = "Back";
        final String labelMessText = "Select Players: ";
        final String labelUcText = "Default: 1Player";

        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel mess = new JLabel(labelMessText);
        add(mess);

        JLabel uc = new JLabel(labelUcText);
        add(uc);

        //set up radio buttons
        opt1 = new JRadioButton(radioButtonOption1Text);
        opt1.setSelected(true);
        opt2 = new JRadioButton(radioButtonOption2Text);
        opt3 = new JRadioButton(radioButtonOption3Text);
        ButtonGroup grp = new ButtonGroup();
        grp.add(opt1);
        grp.add(opt2);
        grp.add(opt3);

        add(opt1);
        add(opt2);
        add(opt3);

        opt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });
        opt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });

        opt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });

        go = new JButton(buttonGoText);

        quit = new JButton(buttonQuitText);

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
