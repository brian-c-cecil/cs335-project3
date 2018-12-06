import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class panelHandler extends JPanel {
    public gridPanel panel1;
    public gridPanel panel2;

    private int xSize;
    private int ySize;

    private int x;
    private int y;

    private boolean edgeflag1;
    private boolean edgeflag2;

    //Constructor
    public panelHandler(int x, int y){

        xSize = x;
        ySize = y;

        edgeflag1 = false;
        edgeflag2 = false;

        makeGrids();

        add(panel1);
        add(panel2);

        setListeners();
    }

    /*
    -Creates 2 new gridPanel objects
    -Is its own function so I didn't have to copy the same code twice
     in the constructor and in changeResolution
    */
    public void makeGrids(){
        panel1 = new gridPanel(xSize, ySize);
        panel2 = new gridPanel(xSize, ySize);
    }

    /*
    -The listeners are put on out here to make it easier for the 2 gridPanels
     to communicate with each other
    */
    private void setListeners(){
        controlPoint[][] p1 = panel1.getPoints();
        controlPoint[][] p2 = panel2.getPoints();

        //Sets the mouse drag listeners to redraw the lines and the point
        for (int i = 1; i < xSize+1; i++){
            for (int j = 1; j < ySize+1; j++){
                x = i;
                y = j;
                p1[x][y].addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        //Get distance to n, get dist to ne
                        //check to see if dist n + dist ne  = dist n-> ne

                        double NtoE = Dist(p1[tempX][tempY].north, p1[tempX][tempY].east);
                        double EtoSE = Dist(p1[tempX][tempY].east, p1[tempX][tempY].southeast);
                        double SEtoS = Dist(p1[tempX][tempY].southeast, p1[tempX][tempY].south);
                        double StoW = Dist(p1[tempX][tempY].south, p1[tempX][tempY].west);
                        double WtoNW = Dist(p1[tempX][tempY].west, p1[tempX][tempY].northwest);
                        double NWtoN = Dist(p1[tempX][tempY].northwest, p1[tempX][tempY].north);

                        double toN = Dist(p1[tempX][tempY], p1[tempX][tempY].north);
                        double toE = Dist(p1[tempX][tempY], p1[tempX][tempY].east);
                        double toSE = Dist(p1[tempX][tempY], p1[tempX][tempY].southeast);
                        double toS = Dist(p1[tempX][tempY], p1[tempX][tempY].south);
                        double toW = Dist(p1[tempX][tempY], p1[tempX][tempY].west);
                        double toNW = Dist(p1[tempX][tempY], p1[tempX][tempY].northwest);

                        if(toN + toE == NtoE || toE + toSE == EtoSE || toSE + toS == SEtoS || toS + toW == StoW || toW + toNW == WtoNW || toNW + toN == NWtoN){
                            edgeflag1 = true;
                        }

                        if (!edgeflag1) {
                            p1[tempX][tempY].setLocation(e.getX() + p1[tempX][tempY].getxPos() - 5, e.getY() + p1[tempX][tempY].getyPos() - 5);
                            p1[tempX][tempY].relocate();
                            panel1.drawStuff();
                        }
                    }
                });
                p1[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        p1[tempX][tempY].setActive();
                        p2[tempX][tempY].setActive();
                        panel1.drawStuff();
                        panel2.drawStuff();
                    }
                });
                p1[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        p1[tempX][tempY].setInactive();
                        p2[tempX][tempY].setInactive();
                        panel1.drawStuff();
                        panel2.drawStuff();
                        edgeflag1 = false;
                    }
                });
            }
        }

        for (int i = 1; i < xSize+1; i++){
            for (int j = 1; j < ySize+1; j++){
                x = i;
                y = j;
                p2[x][y].addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        double NtoE = Dist(p2[tempX][tempY].north, p2[tempX][tempY].east);
                        double EtoSE = Dist(p2[tempX][tempY].east, p2[tempX][tempY].southeast);
                        double SEtoS = Dist(p2[tempX][tempY].southeast, p2[tempX][tempY].south);
                        double StoW = Dist(p2[tempX][tempY].south, p2[tempX][tempY].west);
                        double WtoNW = Dist(p2[tempX][tempY].west, p2[tempX][tempY].northwest);
                        double NWtoN = Dist(p2[tempX][tempY].northwest, p2[tempX][tempY].north);

                        double toN = Dist(p2[tempX][tempY], p2[tempX][tempY].north);
                        double toE = Dist(p2[tempX][tempY], p2[tempX][tempY].east);
                        double toSE = Dist(p2[tempX][tempY], p2[tempX][tempY].southeast);
                        double toS = Dist(p2[tempX][tempY], p2[tempX][tempY].south);
                        double toW = Dist(p2[tempX][tempY], p2[tempX][tempY].west);
                        double toNW = Dist(p2[tempX][tempY], p2[tempX][tempY].northwest);

                        if(toN + toE == NtoE || toE + toSE == EtoSE || toSE + toS == SEtoS || toS + toW == StoW || toW + toNW == WtoNW || toNW + toN == NWtoN){
                            edgeflag2 = true;
                        }
                        if (!edgeflag2) {
                            p2[tempX][tempY].setLocation(e.getX() + p2[tempX][tempY].getxPos() - 5, e.getY() + p2[tempX][tempY].getyPos() - 5);
                            p2[tempX][tempY].relocate();
                            panel2.drawStuff();
                        }
                    }
                });
                p2[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        p2[tempX][tempY].setActive();
                        p1[tempX][tempY].setActive();
                        panel1.drawStuff();
                        panel2.drawStuff();
                    }
                });
                p2[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getRowID();
                        int tempY = clickedPoint.getColID();

                        p2[tempX][tempY].setInactive();
                        p1[tempX][tempY].setInactive();
                        panel1.drawStuff();
                        panel2.drawStuff();
                        edgeflag2 = false;
                    }
                });
            }
        }
    }

    //Finds the distance between 2 points
    private double Dist(controlPoint a, controlPoint b){
        return Math.sqrt(Math.pow(a.getxPos() - b.getxPos(), 2) + Math.pow(a.getyPos() - b.getyPos(), 2));
    }

    //Called when the user changes the resolution of the grid
    public void changeResolution(int x, int y){
        panel1.setVisible(false);
        panel2.setVisible(false);
        remove(panel1);
        remove(panel2);

        xSize = x;
        ySize = y;

        makeGrids();

        add(panel1);
        add(panel2);

        setListeners();
    }

    //These 2 send the pictures down one more layer
    public void setPrePic(BufferedImage pic){
        panel1.setPic(pic);
    }
    public void setPostPic(BufferedImage pic){
        panel2.setPic(pic);
    }
}