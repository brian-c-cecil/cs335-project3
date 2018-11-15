import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class panelHandler extends JPanel {
    public gridPanel panel1;
    public gridPanel panel2;

    private int xSize;
    private int ySize;

    private int x;
    private int y;

    public panelHandler(int x, int y){

        xSize = x;
        ySize = y;

        makeGrids();

        add(panel1);
        add(panel2);

        setListeners();
    }

    public void makeGrids(){
        panel1 = new gridPanel(xSize, ySize);
        panel2 = new gridPanel(xSize, ySize);
    }

    private void setListeners(){
        controlPoint[][] p1 = panel1.getPoints();
        controlPoint[][] p2 = panel2.getPoints();

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

                        p1[tempX][tempY].setLocation(e.getX() + p1[tempX][tempY].getxPos() - 5, e.getY() + p1[tempX][tempY].getyPos() - 5);
                        p1[tempX][tempY].relocate();
                        panel1.drawStuff();
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
                        int tempX = clickedPoint.getxPos();
                        int tempY = clickedPoint.getyPos();

                        p1[tempX][tempY].setInactive();
                        p2[tempX][tempY].setInactive();
                        panel1.drawStuff();
                        panel2.drawStuff();
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

                        p2[tempX][tempY].setLocation(e.getX() + p2[tempX][tempY].getxPos() - 5, e.getY() + p2[tempX][tempY].getyPos() - 5);
                        p2[tempX][tempY].relocate();
                        panel2.drawStuff();
                    }
                });
                p2[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        controlPoint clickedPoint = (controlPoint)e.getSource();
                        int tempX = clickedPoint.getxPos();
                        int tempY = clickedPoint.getyPos();

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
                    }
                });
            }
        }
    }
}
