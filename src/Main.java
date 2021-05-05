import javax.swing.SwingUtilities;

/**
 * Clase principal del programa
 * @author 34602
 */
public class Main {

	/**
         * Método principal del programa y que lanza el juego
         * @param args Se encarga de lanzar el programa
         */
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable(){
	    /**
             * Método encargado de lanzar el juego
             */
            public void run(){
		MainWindow mw = new MainWindow();
		mw.setSize(600,400);
		mw.setTitle("Snake and Ladder");
	        mw.setVisible(true);
		}
		});
    }

}
