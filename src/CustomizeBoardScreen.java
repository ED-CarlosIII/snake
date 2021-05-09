import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
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

    private static final Logger LOG = getLogger(CustomizeBoardScreen.class.getName());
	JButton go;
	JButton quit;
	MainWindow mw;

    /**
     *
     * @param mw objeto de la clase MainWindow.
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
     * Este método no se que hace
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
