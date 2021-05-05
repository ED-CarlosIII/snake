
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//needs massive aesthetic rewrites
public class PlayerSelectScreen extends JPanel {

    JButton go;
    JButton quit;
    MainWindow mw;

    JRadioButton opt1;
    JRadioButton opt2;
    JRadioButton opt3;

    public void quitButtonActionListener() {
        mw.showCard("One");
    }

    public void goButtonActionListener() {
        playerOptions();
        mw.s4.setUpPlayers();
        mw.showCard("Three");
    }

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

    public PlayerSelectScreen(MainWindow mw) {
        this.mw = mw;

        final String selectPlayers = "Select Players: ";
        final String defaultPlayer1 = "Default: 1Player";
        final String player1 = "1 Player(Default)";
        final String players2 = "2 Players";
        final String players3 = "3 Players";

        final String customizeBoard = "Customize Board";
        final String backMessage = "Back";

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel mess = new JLabel(selectPlayers);
        add(mess);

        JLabel uc = new JLabel(defaultPlayer1);
        add(uc);

        //set up radio buttons
        opt1 = new JRadioButton(player1);
        opt1.setSelected(true);
        opt2 = new JRadioButton(players2);
        opt3 = new JRadioButton(players3);

        ButtonGroup grp = new ButtonGroup();
        grp.add(opt1);
        grp.add(opt2);
        grp.add(opt3);

        add(opt1);
        add(opt2);
        add(opt3);

        opt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });
        opt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });

        opt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                playerOptions();
            }
        });

        go = new JButton(customizeBoard);

        quit = new JButton(backMessage);

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

        add(go);
        add(quit);

    }

}
