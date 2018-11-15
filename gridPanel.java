import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class gridPanel extends JPanel {

    private BufferedImage pic;
    public boolean drawimage = false;

    private static int defaultSize = 880;
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
                controlPoints[i][j] = new controlPoint((i * xSpacing) - 5, (j * ySpacing) - 5, i, j);
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
                controlPoints[i][j].setSouthNeighor(controlPoints[i][j+1]);
                controlPoints[i][j].setEastNeighbor(controlPoints[i+1][j]);
                controlPoints[i][j].setSouthEastNeighor(controlPoints[i+1][j+1]);
            }
        }
    }

    //Redraw the pic and then the grid
    protected void paintComponent(Graphics g){
        int x1, x2, y1, y2;

        //definitely need some neighbor stuff here
        for (int i = 0; i < rows+2; i++){
            for (int j = 0; j < cols+2; j++){
                x1 = controlPoints[i][j].getxPos() + 5;
                y1 = controlPoints[i][j].getyPos() + 5;
                x2 = x1 + xSpacing;
                y2 = y1 + ySpacing;
                g.drawLine(x1, y1, x2, y2);

                x2 = x1;
                g.drawLine(x1, y1, x2, y2);

                x2 = x1 + xSpacing;
                y2 = y1;
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public controlPoint[][] getPoints(){
        return controlPoints;
    }
}

























