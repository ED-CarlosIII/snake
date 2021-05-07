
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
 *
 * @author alumno
 */
public class PlayerSelectScreen extends JPanel {

    JButton go;
    JButton quit;
    MainWindow mw;

    JRadioButton opt1;
    JRadioButton opt2;
    JRadioButton opt3;

    /**
     * Método que deja de escuchar la acción del botón 
     */
    public void quitButtonActionListener() {
        mw.showCard("One");
    }

    /**
     * Método que escucha la acción del botón 
     */
    public void goButtonActionListener() {
        playerOptions();
        mw.s4.setUpPlayers();
        mw.showCard("Three");
    }

    /**
     * Métod opara las opciones del jugador
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
     * Método constructor de un objeto que representa la pantalla de selección del jugador
     * @param mw objeto de tipo ventana principal
     */
    public PlayerSelectScreen(MainWindow mw) {
        this.mw = mw;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

        String textMess = "Select Players: ";
        String textUc = "Default: 1Player";
        String textOpt1 = "1 Player(Default)";
        String textOpt2 = "2 Players";
        String textOpt3 = "3 Players";
        String textGo = "Customize Board";
        String textBack = "Back";

        setLayout(boxLayout);

        JLabel mess = new JLabel(textMess);
        add(mess);

        JLabel uc = new JLabel(textUc);
        add(uc);

        //set up radio buttons
        opt1 = new JRadioButton(textOpt1);
        opt1.setSelected(true);

        opt2 = new JRadioButton(textOpt2);

        opt3 = new JRadioButton(textOpt3);

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

        go = new JButton(textGo);

        quit = new JButton(textBack);

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
