
import static java.awt.Color.BLUE;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

//note: board does not change dynamically 
//note: board shape and window aesthetics to be set
//note: unification of colors not done

/**
 * Clase que nos imprimirá un tablero de juego.
 * 
 * @author Ana Belen Madrid Garcia
 */
public class BoardDrawing extends JPanel {
    /**
     * @see b 0
     */
    int b = 0;
    /**
     * @see row 8
     */
    int row = 8;
    /**
     * @see col 8
     */
    int col = 8;
    /**
     * @see ArrayList<Rectangle> cells
     */
    ArrayList<Rectangle> cells;
    //int player;
    /**
     * @see int[] cellnos
     */
    int[] cellnos;
    /**
     * @see bs
     */
    BoardScreen bs;
    //ArrayList<Portal> portals;
    //ArrayList<Player> players;

    /**
     * Constructor de la clase DibujarTablero que recibe varios parametros
     * 
     * @param row Integer
     * @param col Integer
     * @param bs  BoardScreen 
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

        cells = new ArrayList<>();

        cellnos = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i % 2 == 0) {
                    cellnos[i * col + j] = i * col + j;
                } else {
                    cellnos[i * col + j] = i * col + (row - 1 - j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cellnos[i * col + j] = row * col - 1 - cellnos[i * col + j];
            }
        }

        int noPorts = 8;
        bs.portals = new ArrayList<>(noPorts);
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row * col);
            bs.portals.add(temp);
        }

    }

    /**
     * Metodo que será llamado para pintar los distintos componentes en el tablero.
     * 
     * @param g Graphics
     *  
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        Graphics2D g2d = (Graphics2D) g;

             //.create();

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

        g2d.setColor(white);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }

        g2d.setColor(BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        //Draw cells and numbers
        //may have to modify program based on number of players
        g2d.setColor(BLUE);
        int i = 0;                                // i is our visible numbering 
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            
             //g2d.setColor(Color.red);

            //draw player position
            for (int pl = 0; pl < bs.maxPlayers; pl++) {
                if (bs.players.get(pl).getPosition() == cellnos[i]) {
                    drawPlayerPosition(g2d, pl, cell, cellWidth, cellHeight);
                }
            }

            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.maxPlayers; pl++) {
                    if (bs.players.get(pl).getPosition() >= cellnos[i]) {                         //only one player considered here

                        drawPlayerPosition(g2d, pl, cell, cellWidth, cellHeight);
                        //change to player position
                        //change to player color
                    }
                }
            }
            i++;
        }

        //Drawing snakes and ladders
        for (Portal port : bs.portals) {
            drawColor(port, g2d);

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

    /**
     * Método que dotará de color a los elementos del tablero.
     * 
     * @param port Portal
     * @param g2d Graphics2D
     */
    private void drawColor(Portal port, Graphics2D g2d) {
        if (port.returnNature() == -1) {
            g2d.setColor(red);
        } else {
            g2d.setColor(green);
        }
    }

    /**
     * Método para posicionar al jugador en el tablero
     * 
     * @param g2d Graphics2D
     * @param pl int
     * @param cell Rectangle
     * @param cellWidth int
     * @param cellHeight int
     */
    private void drawPlayerPosition(Graphics2D g2d, int pl, Rectangle cell, int cellWidth, int cellHeight) {
        //only one player considered here

        g2d.setColor(bs.players.get(pl).returnPlayerColor());        //change to player color
        g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
        g2d.setColor(blue);
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
     * Método para asegurar posicion jugador que devolverá un mensaje segun convenga.
     * 
     * 
     * @param pnos int
     * @return String
     * 
     */

    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : bs.portals) {
            if (bs.players.get(pnos).getPosition() == port.returnStart()) {
                bs.players.get(pnos).setPosition(port.returnEnd());
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
     * Método para establecer a un jugador
     * 
     * 
     * @param a int
     * @param pnos int
     * 
     */

    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }

}
