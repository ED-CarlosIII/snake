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
 * Clase encargada de la pantalla de selección de jugador
 * @author 34602
 */
public class PlayerSelectScreen extends JPanel {
    /**
     * Atributos de la clase
     */
    JButton go;
    JButton quit;
    MainWindow mw;
	
    JRadioButton opt1;
    JRadioButton opt2;
    JRadioButton opt3;
	/**
         * Método encargado del botón de salir
         */
	public void quitButtonActionListener(){
	    mw.showCard("One");
	}
	/**
         * Método encargado del botón de ir
         */
	public void goButtonActionListener(){
	    playerOptions();
	    mw.s4.setUpPlayers();
	    mw.showCard("Three");
	}
	/**
         * Método encargado de las opciones de jugador
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
         * Método encargado de la pantalla de selección del jugador
         * @param mw Pantalla princial sobre la que se selecciona al jugador
         */
	public PlayerSelectScreen(MainWindow mw){
	    this.mw = mw;
		
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            String select_Players_ = "Select Players: ";
		
	    JLabel mess = new JLabel(select_Players_);
	    add(mess);
            String default_1Player = "Default: 1Player";
		
	    JLabel uc = new JLabel(default_1Player);
	    add(uc);
            String _PlayerDefault = "1 Player(Default)";
		
		//set up radio buttons
		
	    opt1 = new JRadioButton(_PlayerDefault);
	    opt1.setSelected(true);
            String _Players = "2 Players";
		
	    opt2 = new JRadioButton(_Players);
            String _Players1 = "3 Players";
	    opt3 = new JRadioButton(_Players1);
		
	    ButtonGroup grp = new ButtonGroup();
	    grp.add(opt1);
	    grp.add(opt2);
	    grp.add(opt3);
		
	    add(opt1);
	    add(opt2);
	    add(opt3);

	    opt1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    playerOptions();
		}
		});
	    opt2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    playerOptions();
		}
		});
		
	    opt3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    playerOptions();
		}
		});
            String customize_Board = "Customize Board";
		
	    go = new JButton(customize_Board);
            String back = "Back";	
	    quit = new JButton(back);	
		
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
