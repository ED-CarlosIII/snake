
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
 * Draws the board.
 * @author Borja Ortiz Hernández
 * 
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
     * Draws the board.
     * @param row. Rows of the board.
     * @param col. Columns of the board.
     * @param bs. Boardscreen.
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
     * Paints the components of the board.
     * @param g. Graphics of the board.
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

        addBoard(xOffset, cellWidth, yOffset, cellHeight);

        colorCells(g2d);

        colorMargins(g2d);

        drawPositions(g2d, cellWidth, cellHeight);

        drawSnakesLadders(g2d);

    }
    
    /**
     * Draws the positions of the board.
     * @param g2d. Graphics.
     * @param cellWidth. Width of the cell.
     * @param cellHeight. Height of the cell.
     * 
     */
    private void drawPositions(Graphics2D g2d, int cellWidth, int cellHeight) {
        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering
        for (Rectangle cell : cells) {

            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            //g2d.setColor(Color.red);

            //draw player position
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
     * Draws snakes and ladders.
     * @param g2d. Graphics
     * 
     */
    private void drawSnakesLadders(Graphics2D g2d) {
        //Drawing snakes and ladders
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
     * Colors the margins of the board.
     * @param g2d. Graphics.
     * 
     */
    private void colorMargins(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

    }

    /**
     * Colors the background of the cell. 
     * @param g2d. Graphics.
     * 
     */
    private void colorCells(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }
    }

    /**
     * Adds the board. 
     * @param xOffset. Width of the board.
     * @param cellWidth. Width of the cell.
     * @param yOffset. Height of the board.
     * @param cellHeight. Height of the cell.
     * 
     */
    private void addBoard(int xOffset, int cellWidth, int yOffset, int cellHeight) {
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
     * Changes position of the player depending on whether it falls into a snake or a ladder. 
     * @param pnos. Position of the player
     * @return information message about player position. 
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
     * Changes position of the player given a current position and the number of positions to increase. 
     * @param a. Number of positions to increase. 
     * @param pnos. Current position. 
     */

    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }

}
