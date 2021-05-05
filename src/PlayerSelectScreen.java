import java.awt.Component;
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

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel mess = new JLabel("Select Players: ");
        Component add = add(mess);

        JLabel uc = new JLabel("Default: 1Player");
        Component add1 = add(uc);
        String _PlayerDefault = "1 Player(Default)";

        //set up radio buttons
        opt1 = new JRadioButton(_PlayerDefault);
        opt1.setSelected(true);
        String _Players2 = "2 Players";

        opt2 = new JRadioButton(_Players2);
        String _Players3 = "3 Players";
        opt3 = new JRadioButton(_Players3);

        ButtonGroup grp = new ButtonGroup();
        grp.add(opt1);
        grp.add(opt2);
        grp.add(opt3);

        Component add2 = add(opt1);
        Component add3 = add(opt2);
        Component add4 = add(opt3);

        ActionListener actionListener = (ActionEvent event) -> {
            playerOptions();
        };

        opt1.addActionListener(actionListener);
        ActionListener actionListener1 = (ActionEvent event) -> {
            playerOptions();
        };
        opt2.addActionListener(actionListener1);
        ActionListener actionListener2 = (ActionEvent event) -> {
            playerOptions();
        };

        opt3.addActionListener(actionListener2);

        go = new JButton("Customize Board");
        quit = new JButton("Back");

        go.addActionListener((ActionEvent event) -> {
            goButtonActionListener();
        });
        ActionListener actionListener3 = (ActionEvent event) -> {
            quitButtonActionListener();
        };

        quit.addActionListener(actionListener3);

        Component add5 = add(go);
        Component add6 = add(quit);

    }


}
