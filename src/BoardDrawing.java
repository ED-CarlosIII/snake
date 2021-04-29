import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Define las variables para crear un tablero
 * @author alumno
 */
public class BoardDrawing extends JPanel {

    int b = 0;
    int row = 8;
    int col = 8;
    ArrayList<Rectangle> cells;
    int[] cellnos;

    BoardScreen bs;

    /**
     * 
     * Constructor del tablero
     * @param row
     * @param col
     */
    public BoardDrawing(int row, int col, BoardScreen bs) {
        this.bs = bs;
        this.row = row;
        this.col = col;

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
     * Constructor del gráfico 2d de los jugadores y posición
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / col;
        int cellHeight = height / row;

        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        createCells(xOffset, cellWidth, yOffset, cellHeight);

        g2d.setColor(Color.white);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }

        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        drawCells(g2d, cellWidth, cellHeight);

        drawSnakes(g2d);

    }

    /**
     * Constructor de las serpiente y escaleras en el tablero
     *
     * @param g2d
     */
    public void drawSnakes(Graphics2D g2d) {

        for (Portal port : bs.portals) {
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

    /**
     * Constructor de las celdas en el tablero por donde van a ir los jugadores
     *
     * @param xOffset
     * @param cellWidth
     * @param yOffset
     * @param cellHeight
     */
    public void createCells(int xOffset, int cellWidth, int yOffset, int cellHeight) {
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
     * Constructor del diseño de las celdas para el tablero
     *
     * @param g2d
     * @param cellWidth
     * @param cellHeight
     */
    public void drawCells(Graphics2D g2d, int cellWidth, int cellHeight) {
        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());

            for (int pl = 0; pl < bs.maxPlayers; pl++) {
                if (bs.players.get(pl).getPosition() == cellnos[i]) {                         //only one player considered here

                    g2d.setColor(bs.players.get(pl).getPlayerColor());        //change to player color
                    g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
                    g2d.setColor(Color.blue);
                }
            }

            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.maxPlayers; pl++) {
                    if (bs.players.get(pl).getPosition() >= cellnos[i]) {                         //only one player considered here

                        g2d.setColor(bs.players.get(pl).getPlayerColor());        //change to player color
                        g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
                        g2d.setColor(Color.blue);
                    }
                }
            }
            i++;
        }
    }

    /**
     * Constructor de Posicion del jugador en el tablero
     *
     * @param pnos
     * @return posicion del jugador
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

    /**
     *
     * @param a
     * @param pnos
     */
    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }

}
