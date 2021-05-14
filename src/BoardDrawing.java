
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
 * @author alba
 */
public class BoardDrawing extends JPanel {

    /**
     *
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
     * Constructor de la clase BoardDrawing
     * @param row fila
     * @param col columna
     * @param bs boardscreen
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
     * Pinta los componentes del tablero.
     * @param g Componente gráfico.
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

        int xOffset = calculateOffset(width, col, cellWidth);
        int yOffset = calculateOffset(height, row, cellHeight);

        addCellsIfEmpty(xOffset, yOffset, cellWidth, cellHeight);

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
        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering 
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            //g2d.setColor(Color.red);

            //draw player position
            for (int pl = 0; pl < bs.maxPlayers; pl++) {
                if (bs.players.get(pl).getPosition() == cellnos[i]) {                         //only one player considered here
                   drawPlayer(cell, g2d, pl, cellWidth, cellHeight);
                }
            }

            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.maxPlayers; pl++) {
                    if (bs.players.get(pl).getPosition() >= cellnos[i]) {                         //only one player considered here
                        drawPlayer(cell, g2d, pl, cellWidth, cellHeight);
                    }
                }
            }
            i++;
        }

        //Drawing snakes and ladders
        for (Portal port : bs.portals) {
            drawPort(port, g2d);
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
     * Genera un mensaje dependiendo de la posición del personaje.
     * @param pnos posición del personaje
     * @return mensaje generado
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
     * Incrementa la posición del personaje en a.
     * @param a incremento.
     * @param pnos posición actual.
     */
    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }
    
    /**
     * Calcula el offset de una celda
     * @param sizeTotal tamaño total dimensión
     * @param dimension dimensión
     * @param cellSize tamaño de celda
     * @return offset resultante
     */
    private int calculateOffset(int sizeTotal, int dimension, int cellSize){
        return (sizeTotal - (dimension * cellSize)) / 2;
    }
    
    /**
     * Añade las celdas al tablero si está vacío.
     * @param xOffset offset x
     * @param yOffset offset y
     * @param cellWidth ancho celda
     * @param cellHeight alto celda
     */
    private void addCellsIfEmpty(int xOffset, int yOffset, int cellWidth, int cellHeight){
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
    
    /**
     * Dibuja el personaje en el tablero
     * @param cell celda a dibujar el personaje
     * @param g2d componente gráfico
     * @param pl posición
     * @param cellWidth ancho de celda
     * @param cellHeight alto de celda
     */
    private void drawPlayer(Rectangle cell, Graphics2D g2d, int pl, int cellWidth, int cellHeight){
        g2d.setColor(bs.players.get(pl).getPlayerColor());        //change to player color
        g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
        g2d.setColor(Color.blue);
    }
    
    /**
     * Dibuja el portal en el tablero
     * @param port portal correspondiente
     * @param g2d componente gráfico
     */
    private void drawPort(Portal port, Graphics2D g2d){
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
