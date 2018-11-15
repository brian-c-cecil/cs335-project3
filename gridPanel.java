import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class gridPanel extends JPanel {

    private BufferedImage pic;
    public boolean drawimage = false;

    private static int defaultSize = 500;
    private int sizeX = defaultSize;
    private int sizeY = defaultSize;
    private int rows;
    private int cols;

    private int xSpacing;
    private int ySpacing;

    private controlPoint[][] controlPoints;

    //Default Constructor
    public gridPanel(int x, int y){
        rows = x;
        cols = y;

        //Add 2 to draw control points on the edges of the screen
        //these control points will not have listeners, so they cannot be clicked
        controlPoints = new controlPoint[rows+2][cols+2];

        //Sets up the points at an even spacing
        //Should work arbitrarily  for any # of rows, cols and panel size
        xSpacing = sizeX/(rows + 1);
        ySpacing = sizeY/(cols + 1);

        //Create the control points
        for (int i = 0; i < rows+2; i++){
            for (int j = 0; j < cols+2; j++){
                controlPoints[i][j] = new controlPoint((i * xSpacing) - 5, (j * ySpacing) - 5);
                controlPoints[i][j].setRowID(i);
                controlPoints[i][j].setColID(j);
                add(controlPoints[i][j]);
            }
        }

        setNeighbors();

        setLayout(null);
        setPreferredSize(new Dimension(sizeX, sizeY));
        setBorder(BorderFactory.createBevelBorder(1));
    }

    public void setNeighbors(){
        for(int i = 0; i < rows+1; i++){
            for (int j = 0; j < cols+1; j++){
                controlPoints[i][j].setSouthNeighbor(controlPoints[i][j+1]);
                controlPoints[i][j].setEastNeighbor(controlPoints[i+1][j]);
                controlPoints[i][j].setSouthEastNeighbor(controlPoints[i+1][j+1]);

                controlPoints[i][j].setNeighborLocations();
            }
        }
    }

    //Redraw the pic and then the grid
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw the shit
        int x1, x2, y1, y2;
        for (int i = 0; i < rows+1; i++){
            for (int j = 0; j < cols+1; j++){
                x1 = controlPoints[i][j].xPos + 5;
                y1 = controlPoints[i][j].yPos + 5;
                x2 = controlPoints[i][j].east.getxPos() + 5;
                y2 = controlPoints[i][j].east.getyPos() + 5;
                g.drawLine(x1, y1, x2, y2);

                x2 = controlPoints[i][j].south.getxPos() + 5;
                y2 = controlPoints[i][j].south.getyPos() + 5;
                g.drawLine(x1, y1, x2, y2);

                x2 = controlPoints[i][j].southeast.getxPos() + 5;
                y2 = controlPoints[i][j].southeast.getyPos() + 5;
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public controlPoint[][] getPoints(){
        return controlPoints;
    }

    public void drawStuff(){
        repaint();
    }
}

























