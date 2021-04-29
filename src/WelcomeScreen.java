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
 * Clase encargada de la página de bienvenida
 * @author 34602
 */
public class WelcomeScreen extends JPanel{
    /**
     * Atributos de la clase
     */
    JLabel title;
    JButton go;
    JButton quit;
	
    MainWindow mw;
	/**
         * Método que se encarga de establecer el título
         * @param t Sting que establece el título 
         */
	public void setTitle(String t){
	    title.setText(t);
	}
	/**
         * Método encargado del botón de salir
         */
	public void quitButtonActionListener(){
	    if(JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION)
	    System.exit(0);
	}
	/**
         * Método encargado del botón de ir
         */
	public void goButtonActionListener(){
	    mw.showCard("Two");
	}
        /**
         * Método encargado de crear la pantalla de bienvenida
         * @param mw Ventana principal sobra la que se crea la pantalla de bienvenida
         */
	public WelcomeScreen(MainWindow mw){
	    this.mw = mw;
		
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    title = new JLabel();
	    add(title);
		
		//add formatting here
		
	    go = new JButton(NEW__GAME);
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
    private static final String QUIT = "Quit";
    private static final String NEW__GAME = "New Game";

}
