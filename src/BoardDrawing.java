
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/*
* Esta clase define el dibujo de nuestra pizarra
* @author: José Antonio Garrido García
* @version: 13/04/2021
 */
//note: board does not change dynamically 
//note: board shape and window aesthetics to be set
//note: unification of colors not done
public class BoardDrawing extends JPanel {

    /**
     * Declaración de atributos
     */
    int b = 0;
    int row = 8;
    int col = 8;
    ArrayList<Rectangle> cells;
    //int player;
    int[] cellnos;

    BoardScreen bs;
    //ArrayList<Portal> portals;
    //ArrayList<Player> players;

    /**
     *
     * Constructor para el dibujo de la pizarra
     *
     * @param row El parámetro row define el numero de fila
     * @param col El parámetro col define el numero de columna
     *
     */
    public BoardDrawing(int row, int col, BoardScreen bs) {
        this.bs = bs;
        this.row = row;
        this.col = col;
        //player = 0;
        //bs.players = new ArrayList<Player>();
        //for(int i = 1;i <= bs.returnMaxPlayers();i++)
        //    bs.players.add(new Player(i));
        //get and add player(s) names

        crearPizarra(row, col, bs);

    }

    /**
     *
     * Metodo el cual dibuja los componentes de nuestra pizarra
     *
     * @param g El parámetro g define los graficos
     *
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;//.create();

        /*
		int sw = getSize().width;
		int sh = getSize().height;
		int a = (int) (0.75*((sw > sh)?sh:sw));
		
		//Point start = new Point(0,0);
		//Point end = new Point(100,100);
		
		g.drawLine(0,0,sw, sh);
         */
        //Create cells
        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / col;
        int cellHeight = height / row;

        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    Rectangle latest = new Rectangle(
                            xOffset + (j * cellWidth),
                            yOffset + (i * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(latest);
                }
            }
        }

        g2d.setColor(Color.white);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }

        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        //Draw cells and numbers
        //may have to modify program based on number of players
        dibujarCeldNum(g2d, cellWidth, cellHeight);

        dibujarSnakesLadder(g2d);

    }

    /**
     *
     * Metodo el cual crea una pizarra
     *
     * @param row1 El parámetro row1 hace referencia a la primera fila
     * @param col1 El parámetro col1 hace referencia a la primera columna
     * @param bs1 El parámetro bs1 hace referencia a la primera pantalla de
     * pizarra
     *
     */
    private void crearPizarra(int row1, int col1, BoardScreen bs1) {

        cells = new ArrayList<Rectangle>();
        cellnos = new int[row1 * col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (i % 2 == 0) {
                    cellnos[i * col1 + j] = i * col1 + j;
                } else {
                    cellnos[i * col1 + j] = i * col1 + (row1 - 1 - j);
                }
            }
        }
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                cellnos[i * col1 + j] = row1 * col1 - 1 - cellnos[i * col1 + j];
            }
        }
        int noPorts = 8;
        bs1.portals = new ArrayList<Portal>(noPorts);
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row1 * col1);
            bs1.getPortals().add(temp);
        }
    }

    /**
     *
     * Metodo el cual dibuja las celdas y los numeros de nuestra pizarra
     *
     * @param g2d El parámetro g2d hace referencia a la segunda dimension
     * @param cellWidth El parámetro cellWidth hace referencia al ancho de la
     * celda
     * @param celHeight El parámetro celHeight hace referencia a la altura de la
     * celda pizarra
     *
     */
    private void dibujarCeldNum(Graphics2D g2d, int cellWidth, int cellHeight) {
        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            //g2d.setColor(Color.red);

            //draw player position
            for (int pl = 0; pl < bs.getMaxPlayers(); pl++) {
                if (bs.getPlayers().get(pl).devolverPosicion() == cellnos[i]) {                         //only one player considered here

                    g2d.setColor(bs.getPlayers().get(pl).devolverColorJugador());        //change to player color
                    g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
                    g2d.setColor(Color.blue);
                }
            }

            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.getMaxPlayers(); pl++) {
                    if (bs.getPlayers().get(pl).devolverPosicion() >= cellnos[i]) {                         //only one player considered here

                        g2d.setColor(bs.getPlayers().get(pl).devolverColorJugador());        //change to player color
                        g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
                        g2d.setColor(Color.blue);
                    }
                }
            }
            i++;
        }
    }

    /**
     *
     * Metodo el cual dibuja las serpientes y las escaleras
     *
     * @param g2d El parámetro g2d hace referencia a la segunda dimension
     *
     *
     */
    private void dibujarSnakesLadder(Graphics2D g2d) {
        //Drawing snakes and ladders
        for (Portal port : bs.getPortals()) {
            if (port.returnNature() == -1) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.green);
            }

            int ind;
            int s = port.returnStart();
            for (ind = 0; ind < row * col; ind++) {
                if (cellnos[ind] == s) {
                    break;
                }
            }

            int j;
            int e = port.returnEnd();
            for (j = 0; j < row * col; j++) {
                if (cellnos[j] == e) {
                    break;
                }
            }

            g2d.drawLine((int) cells.get(ind).getCenterX(), (int) cells.get(ind).getCenterY(), (int) cells.get(j).getCenterX(), (int) cells.get(j).getCenterY());

        }
    }

    /*
	public void ensurePlayerPosition(){
		for(Portal port :portals){
			if(player == port.returnStart())
				player = port.returnEnd();
		}
	}
     */
    /**
     *
     * Metodo el cual garantiza la posición del jugador
     *
     * @param pnos El parámetro pnos hace referencia a la posición del jugador
     *
     */
    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : bs.getPortals()) {
            if (bs.getPlayers().get(pnos).devolverPosicion() == port.returnStart()) {
                bs.getPlayers().get(pnos).añadirPosicion(port.returnEnd());
                if (port.returnNature() == 1) {
                    message += "You are up through ladder at position " + port.returnStart();
                } else if (port.returnNature() == -1) {
                    message += "Snake at " + port.returnStart() + " got you.";
                }
            }
        }
        return message;
    }

    /*
	public void setPlayer(int a){
		player = a;
	}
     */
    /**
     *
     * Metodo el cual establece un jugador
     *
     * @param a El parámetro a hace referencia al otro punto
     * @param pnos El parámetro pnos hace referencia a la posicion del jugador
     *
     */
    public void setPlayer(int a, int pnos) {
        bs.getPlayers().get(pnos).aumentarPosicion(a);
    }

}
