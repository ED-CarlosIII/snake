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
	private JButton go;
	private JButton quit;
	private MainWindow mw;
	
	private JRadioButton opt1;
	private JRadioButton opt2;
	private JRadioButton opt3;
	
	public void quitButtonActionListener(){
		getMw().showCard("One");
	}
	
	public void goButtonActionListener(){
		playerOptions();
		getMw().s4.setUpPlayers();
		getMw().showCard("Three");
	}
	
	public void playerOptions(){
		int m = PlayerOption();
	    getMw().s4.setMaxPlayers(m);
	}

    public int PlayerOption() {
        int m = 5;
        if(getOpt1().isSelected() == true)
            m = 1;
        else if(getOpt2().isSelected() == true)
            m = 2;
        else if(getOpt3().isSelected() == true)
            m = 3;
        return m;
    }
	
	public PlayerSelectScreen(MainWindow mw){
		this.mw = mw;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel mess = new JLabel("Select Players: ");
		add(mess);
		
		JLabel uc = new JLabel("Default: 1Player");
		add(uc);
		
		//set up radio buttons
		
		opt1 = new JRadioButton("1 Player(Default)");
		opt1.setSelected(true);
		
		opt2 = new JRadioButton("2 Players");
		opt3 = new JRadioButton("3 Players");
		
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
		
		go = new JButton("Customize Board");
		quit = new JButton("Back");	
		
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

    /**
     * @return the go
     */
    public JButton getGo() {
        return go;
    }

    /**
     * @param go the go to set
     */
    public void setGo(JButton go) {
        this.go = go;
    }

    /**
     * @return the quit
     */
    public JButton getQuit() {
        return quit;
    }

    /**
     * @param quit the quit to set
     */
    public void setQuit(JButton quit) {
        this.quit = quit;
    }

    /**
     * @return the mw
     */
    public MainWindow getMw() {
        return mw;
    }

    /**
     * @param mw the mw to set
     */
    public void setMw(MainWindow mw) {
        this.mw = mw;
    }

    /**
     * @return the opt1
     */
    public JRadioButton getOpt1() {
        return opt1;
    }

    /**
     * @param opt1 the opt1 to set
     */
    public void setOpt1(JRadioButton opt1) {
        this.opt1 = opt1;
    }

    /**
     * @return the opt2
     */
    public JRadioButton getOpt2() {
        return opt2;
    }

    /**
     * @param opt2 the opt2 to set
     */
    public void setOpt2(JRadioButton opt2) {
        this.opt2 = opt2;
    }

    /**
     * @return the opt3
     */
    public JRadioButton getOpt3() {
        return opt3;
    }

    /**
     * @param opt3 the opt3 to set
     */
    public void setOpt3(JRadioButton opt3) {
        this.opt3 = opt3;
    }
	

}
