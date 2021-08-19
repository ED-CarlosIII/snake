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
 * @author hugaz
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
    public void quitButtonActionListener(){
		mw.showCard("One");
	}
	
    /**
     *
     */
    public void goButtonActionListener(){
		playerOptions();
		mw.s4.setUpPlayers();
		mw.showCard("Three");
	}
	
    /**
     *
     */
    public void playerOptions(){
		int m = 5;
		if(opt1.isSelected() == true)
		    m = 1;
		else if(opt2.isSelected() == true)
			m = 2;
		else if(opt3.isSelected() == true)
			m = 3;
	    mw.s4.setMaxPlayers(m);
	}
	
    /**
     *
     * @param mw
     */
    public PlayerSelectScreen(MainWindow mw){
		this.mw = mw;
                
                String select = "Select players: ";
                String defecto = "Default: 1Player";
                String oneplayer = "1 Player(Default)";
                String twoplayer = "2 Players";
                String treeplayers = "3 Players";
                String customizeBoard = "Customize Board";
                String back = "Back";
                
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel mess = new JLabel(select);
		add(mess);
		
		JLabel uc = new JLabel(defecto);
		add(uc);
		
		//set up radio buttons
		
		opt1 = new JRadioButton(oneplayer);
		opt1.setSelected(true);
		
		opt2 = new JRadioButton(twoplayer);
		opt3 = new JRadioButton(treeplayers);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(opt1);
		grp.add(opt2);
		grp.add(opt3);
		
		add(opt1);
		add(opt2);
		add(opt3);

		opt1.addActionListener((ActionEvent event) -> {
                    playerOptions();
                });
		opt2.addActionListener((ActionEvent event) -> {
                    playerOptions();
                });
		
		opt3.addActionListener((ActionEvent event) -> {
                    playerOptions();
                });
		
		go = new JButton(customizeBoard);
		quit = new JButton(back);	
		
		go.addActionListener((ActionEvent event) -> {
                    goButtonActionListener();
                });
		
		quit.addActionListener((ActionEvent event) -> {
                    quitButtonActionListener();
                });
		
		add(go);
		add(quit);
		
	}
	

}
