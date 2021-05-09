import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.PAGE_AXIS;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class CustomizeBoardScreen extends JPanel {
	JButton go;
	JButton quit;
	MainWindow mw;

    /**
     *
     * @param mw
     */
    public CustomizeBoardScreen(MainWindow mw) {
        this.mw = mw;
        JLabel mess = new JLabel("Customize Board [Under Construction] ");
        add(mess);
        JLabel uc = new JLabel("Default: 8x8 board with 8 randomly generated snakes/ladders");
        add(uc);
        setLayout(new BoxLayout(this, PAGE_AXIS));
        go = new JButton("Start Game");
        quit = new JButton("Back");
        go.addActionListener((ActionEvent event) -> {
            goButtonActionListener();
        });
        quit.addActionListener((ActionEvent event) -> {
            quitButtonActionListener();
        });
        add(go);
        add(quit);
    }
	
    /**
     *
     */
    public void quitButtonActionListener(){
		mw.showCard("Two");
	}
	
    /**
     *
     */
    public void goButtonActionListener(){
		mw.showCard("Four");
	}
  
}
