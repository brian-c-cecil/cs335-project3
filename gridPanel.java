import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class gridPanel extends JPanel {

    private BufferedImage pic;

    private static int defaultSize = 220;
    private int sizeX = defaultSize;
    private int sizeY = defaultSize;
    private int rows;
    private int cols;

    private controlPoint[][] controlPoints;

    //Default Constructor
    public gridPanel(int x, int y){
        rows = x;
        cols = y;

        controlPoints = new controlPoint[rows][cols];

        //Sets up the points at an even spacing
        //Should work arbitrarily  for any # of rows, cols and panel size
        int xSpacing = sizeX/(rows + 1);
        int ySpacing = sizeY/(cols + 1);

        //Create the control points
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                controlPoints[i][j] = new controlPoint((i+1) * xSpacing, (j+1) * ySpacing, i, j);
            }
        }
    }

    //Set the image
    public void setImage(String filepath){

    }

    //Redraw the pic and then the grid
    protected void paintComponent(Graphics g){

    }



}

























