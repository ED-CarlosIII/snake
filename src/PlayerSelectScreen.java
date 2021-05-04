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
 * @author alumno
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
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            String eligeJugador = "Select Players: ";
		
		JLabel mess = new JLabel(eligeJugador);
		add(mess);
            String jugadorPorDefecto = "Default: 1Player";
		
		JLabel uc = new JLabel(jugadorPorDefecto);
		add(uc);
            String un_Jugador = "1 Player(Default)";
		
		//set up radio buttons
		
		opt1 = new JRadioButton(un_Jugador);
		opt1.setSelected(true);
            String dos_Jugadores = "2 Players";
		
		opt2 = new JRadioButton(dos_Jugadores);
            String tres_Jugadores = "3 Players";
		opt3 = new JRadioButton(tres_Jugadores);
		
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
            String personaliza_Pantalla = "Customize Board";
		
		go = new JButton(personaliza_Pantalla);
            String atras = "Back";	
		quit = new JButton(atras);	
		
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
