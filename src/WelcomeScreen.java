import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ruben
 */
public class WelcomeScreen extends JPanel{

    /**
     *
     */
    JLabel title;

    /**
     *
     */
    JButton go;

    /**
     *
     */
    JButton quit;
	
    /**
     *
     */
    MainWindow mw;
	
    /**
     *
     * @param t
     */
    public void setTitle(String t){
		title.setText(t);
	}
	
    /**
     *
     */
    public void quitButtonActionListener(){
		if(JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION)
	        System.exit(0);
	}
	
    /**
     *
     */
    public void goButtonActionListener(){
		mw.showCard("Two");
	}
 
    /**
     *
     * @param mw
     */
    public WelcomeScreen(MainWindow mw){
		this.mw = mw;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel();
		add(title);
		
		//add formatting here
		
		go = new JButton(NEW_GAME);
		quit = new JButton(QUIT1);	
		
		ActionListener go1 = (ActionEvent event) -> {
                    goButtonActionListener();
                };
                go.addActionListener(go1);
		
		ActionListener quit1 = (ActionEvent event) -> {
                    quitButtonActionListener();
                };
                quit.addActionListener(quit1);
		
		add(go);
		add(quit);
	}

    /**
     *
     */
    protected static final String QUIT1 = "Quit";

    /**
     *
     */
    protected static final String NEW_GAME = "New Game";

}
