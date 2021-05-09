
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

//note: board does not change dynamically 
//note: board shape and window aesthetics to be set
//note: unification of colors not done

/**
 *
 * @author Úrsula Almarcha López
 */
public class BoardDrawing extends JPanel {

    /**
     * Variables para definir las dimensiones del tablero
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
     * @param row
     * @param col
     * @param bs
     * Los parametros de la clase definiran las caracteristicas
     * estéticas de los tableros que el usuario verá
     * Filas y columnas que conformarán las celdas del juego
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

        cells = new ArrayList<Rectangle>();

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
        bs.portals = new ArrayList<Portal>(noPorts);
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row * col);
            bs.portals.add(temp);
        }

    }
/**
 * Método que permite que el usuario conozca la evolución de tablero
 * @param g 
 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;//.create();
        //Create cells
        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / col;
        int cellHeight = height / row;

        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        emptyCell(xOffset, cellWidth, yOffset, cellHeight);

        g2d.setColor(Color.white);
        setColorFill(g2d);

        g2d.setColor(Color.BLUE);
        setColorDraw(g2d);

        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering 
        iterateCell(i, g2d, cellWidth, cellHeight);

        drawSnakes(g2d);

    }
/**
 * Método que hace aparecer la serpiente en pantalla
 * @param g2d 
 */
    private void drawSnakes(Graphics2D g2d) {
        //Drawing snakes and ladders
        for (Portal port : bs.portals) {
            if (port.returnNature() == -1) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.green);
            }

            int ind = startPortCells(port);

            int j = endPortCells(port);

            g2d.drawLine((int) cells.get(ind).getCenterX(), (int) cells.get(ind).getCenterY(), (int) cells.get(j).getCenterX(), (int) cells.get(j).getCenterY());

        }
    }
    /**
     * 
     * @param port
     * @return posición (int) del array cellnos 
     * cuyo valor sea igual a port.returnStart()
     */

    private int startPortCells(Portal port) {
        int ind;
        int s = port.returnStart();
        for (ind = 0; ind < row * col; ind++) {
            if (cellnos[ind] == s) {
                break;
            }
        }
        return ind;
    }
    
/**
 * 
 * @param port
 * @return posición (int) del array cellnos 
 * cuyo valor sea igual a port.returnEnd()
 */
    private int endPortCells(Portal port) {
        int j;
        int e = port.returnEnd();
        for (j = 0; j < row * col; j++) {
            if (cellnos[j] == e) {
                break;
            }
        }
        return j;
    }
/**
 * 
 * @param i
 * @param g2d
 * @param cellWidth
 * @param cellHeight 
 * Método que recorre todas las celdas del tablero 
 * mientras dibuja o respresenta la posición de los jugadores
 */
    private void iterateCell(int i, Graphics2D g2d, int cellWidth, int cellHeight) {
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            //g2d.setColor(Color.red);
            drawPlayerPosition(i, g2d, cell, cellWidth, cellHeight);

            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.maxPlayers; pl++) {
                    setPlayerData(pl, i, g2d, cell, cellWidth, cellHeight);
                }
            }
            i++;
        }
    }
    /**
     * 
     * @param pl
     * @param i
     * @param g2d
     * @param cell
     * @param cellWidth
     * @param cellHeight 
     * Cambia de jugador en el tablero
     */

    private void setPlayerData(int pl, int i, Graphics2D g2d, Rectangle cell, int cellWidth, int cellHeight) {
        if (bs.players.get(pl).getPosition() >= cellnos[i]) {                         //only one player considered here

            g2d.setColor(bs.players.get(pl).getPlayerColor());        //change to player color
            g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
            g2d.setColor(Color.blue);
        }
    }
/**
 * 
 * @param i
 * @param g2d
 * @param cell
 * @param cellWidth
 * @param cellHeight 
 * Cambia la posición del jugador y el color del objeto g2d de la clase
 * Graphics2D g2d
 */
    private void drawPlayerPosition(int i, Graphics2D g2d, Rectangle cell, int cellWidth, int cellHeight) {
        for (int pl = 0; pl < bs.maxPlayers; pl++) {
            if (bs.players.get(pl).getPosition() == cellnos[i]) {                         //only one player considered here

                g2d.setColor(bs.players.get(pl).getPlayerColor());        //change to player color
                g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
                g2d.setColor(Color.blue);
            }
        }
    }
    /**
     * 
     * @param g2d 
     * Dibuja celdas y números
     */

    private void setColorDraw(Graphics2D g2d) {
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        //Draw cells and numbers
        //may have to modify program based on number of players
    }
/**
 * 
 * @param g2d 
 * Rellena una celda
 */
    private void setColorFill(Graphics2D g2d) {
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }
    }
/**
 * 
 * @param xOffset
 * @param cellWidth
 * @param yOffset
 * @param cellHeight 
 * Si cells.isEmpty() == true crea un nuevo objeto Rectangle
 * para después añadirlo al array cells
 */
    private void emptyCell(int xOffset, int cellWidth, int yOffset, int cellHeight) {
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
     * @param pnos
     * @return String informativo para el usuario
     * Informa de su posición en el tablero o si la serpiente 
     * le ha alcanzado
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
     *
     * @param a
     * @param pnos
     * Cambia de usuario
     */

    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }

}
