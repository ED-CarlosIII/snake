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
 * @author hugaz
 */
public class WelcomeScreen extends JPanel{
	JLabel title;
	JButton go;
	JButton quit;
	
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
		if(JOptionPane.showConfirmDialog(this, AREYOUSURE) == JOptionPane.OK_OPTION)
	        System.exit(0);
	}
	
    /**
     *
     */
    public void goButtonActionListener(){
		mw.showCard(NUMBER);
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
		
		go = new JButton(NEWGAME);
		quit = new JButton(QUIT);	
		
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				goButtonActionListener();
			}
		});
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				 quitButtonActionListener();
			}
		});
		
		add(go);
		add(quit);
	}
        
        private static final String AREYOUSURE = "Are you sure?";
        private static final String NUMBER = "2";
        private static final String NEWGAME = "New Game";
        private static final String QUIT = "Quit";
        
        
}


