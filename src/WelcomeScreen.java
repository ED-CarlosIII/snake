import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class WelcomeScreen extends JPanel{
	JLabel title;
	JButton go;
	JButton quit;
	
	MainWindow mw;
        
        /*Extracciones a una constante que veas necesarias en el método
        WelcomeScreen*/
        private static final String NEW_GAME = "New Game";
        private static final String QUIT = "Quit";
	
	public void setTitle(String t){
		title.setText(t);
	}
	
	public void quitButtonActionListener(){
		if(JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION)
	        System.exit(0);
	}
	
	public void goButtonActionListener(){
		mw.showCard("Two");
	}
 
	public WelcomeScreen(MainWindow mw){
		this.mw = mw;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel();
		add(title);
		
		//add formatting here
		
		go = new JButton(NEW_GAME);
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

}
