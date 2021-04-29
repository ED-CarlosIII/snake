
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
 * @author AmmourAmine
 */
public class BoardDrawing extends JPanel {

    /**
     *
     */
    private int b = 0;
    private int row = 8;
    private int col = 8;
    private ArrayList<Rectangle> cells;
    //int player;
    private int[] cellnos;

    private BoardScreen bs;
    //ArrayList<Portal> portals;
    //ArrayList<Player> players;

    /**
     *
     * @param row atributo
     * @param col atributo
     * @param bs atributo
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

        int cellWidth = width / getCol();
        int cellHeight = height / getRow();

        int xOffset = (width - (getCol() * cellWidth)) / 2;
        int yOffset = (height - (getRow() * cellHeight)) / 2;

        if (getCells().isEmpty()) {
            for (int i = 0; i < getRow(); i++) {
                for (int j = 0; j < getCol(); j++) {
                    Rectangle latest = new Rectangle(
                            xOffset + (j * cellWidth),
                            yOffset + (i * cellHeight),
                            cellWidth,
                            cellHeight);
                    getCells().add(latest);
                }
            }
        }

        g2d.setColor(Color.white);
        for (Rectangle cell : getCells()) {
            g2d.fill(cell);
        }

        g2d.setColor(Color.BLUE);
        for (Rectangle cell : getCells()) {
            g2d.draw(cell);
        }

        //Draw cells and numbers
        //may have to modify program based on number of players
        g2d.setColor(Color.BLUE);
        int i = 0;                                // i is our visible numbering 
        for (Rectangle cell : getCells()) {

            String message = "" + getCellnos()[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            //g2d.setColor(Color.red);

            //draw player position
            for (int pl = 0; pl < getBs().maxPlayers; pl++) {
                if (getBs().players.get(pl).getPosition() == getCellnos()[i]) {
                    chagePlayerColor(g2d, pl, cell, cellWidth, cellHeight);
                }
            }

            if (getCellnos()[i] == getRow() * getCol() - 1) {
                for (int pl = 0; pl < getBs().maxPlayers; pl++) {
                    if (getBs().players.get(pl).getPosition() >= getCellnos()[i]) {                         //only one player considered here

                        chagePlayerColor(g2d, pl, cell, cellWidth, cellHeight);
                        //change to player position
                        //change to player color
                    }
                }
            }
            i++;
        }

        //Drawing snakes and ladders
        for (Portal port : getBs().portals) {
            if (port.returnNature() == -1) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.green);
            }

            int ind;
            int s = port.returnStart();
            for (ind = 0; ind < getRow() * getCol(); ind++) {
                if (getCellnos()[ind] == s) {
                    break;
                }
            }

            int j;
            int e = port.returnEnd();
            for (j = 0; j < getRow() * getCol(); j++) {
                if (getCellnos()[j] == e) {
                    break;
                }
            }

            g2d.drawLine((int) getCells().get(ind).getCenterX(), (int) getCells().get(ind).getCenterY(), (int) getCells().get(j).getCenterX(), (int) getCells().get(j).getCenterY());

        }

    }

    /**
     *
     * @param g2d atributo
     * @param pl atributo
     * @param cell atributo
     * @param cellWidth atributo
     * @param cellHeight atributo
     */
    public void chagePlayerColor(Graphics2D g2d, int pl, Rectangle cell, int cellWidth, int cellHeight) {
        //only one player considered here

        g2d.setColor(getBs().players.get(pl).getPlayerColor());        //change to player color
        g2d.fillRect(cell.getLocation().x + pl * cellWidth / 4, cell.getLocation().y, cellWidth / 4, cellHeight / 4);//change to player position
        g2d.setColor(Color.blue);
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
     * @param pnos atributo
     * @return String
     */

    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : getBs().portals) {
            if (getBs().players.get(pnos).getPosition() == port.returnStart()) {
                getBs().players.get(pnos).setPosition(port.returnEnd());
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
     * @param a atributo
     * @param pnos atributo
     */

    public void setPlayer(int a, int pnos) {
        getBs().players.get(pnos).incPosition(a);
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return the cells
     */
    public ArrayList<Rectangle> getCells() {
        return cells;
    }

    /**
     * @param cells the cells to set
     */
    public void setCells(ArrayList<Rectangle> cells) {
        this.cells = cells;
    }

    /**
     * @return the cellnos
     */
    public int[] getCellnos() {
        return cellnos;
    }

    /**
     * @param cellnos the cellnos to set
     */
    public void setCellnos(int[] cellnos) {
        this.cellnos = cellnos;
    }

    /**
     * @return the bs
     */
    public BoardScreen getBs() {
        return bs;
    }

    /**
     * @param bs the bs to set
     */
    public void setBs(BoardScreen bs) {
        this.bs = bs;
    }

}
