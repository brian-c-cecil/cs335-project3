import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class panelHandler extends JPanel {
    public gridPanel panel1;
    public gridPanel panel2;

    private int xSize;
    private int ySize;

    private controlPoint curr;
    private controlPoint match;

    public panelHandler(int x, int y){

        xSize = x;
        ySize = y;

        panel1 = new gridPanel(x, y);
        panel2 = new gridPanel(x, y);

        add(panel1);
        add(panel2);

        setListeners();
    }

    public void makeGrids(int x, int y){
        panel1 = new gridPanel(x, y);
        panel2 = new gridPanel(x, y);
    }

    private void setListeners(){
        //add the action listeners for all the buttons

        controlPoint[][] p1 = panel1.getPoints();
        controlPoint[][] p2 = panel2.getPoints();

        //These work but repaint doesn't for some reason
        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                curr = p1[i][j];
                match = p2[i][j];
                curr.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        curr.setLocation(e.getX()+curr.getxPos() - 5, e.getY()+curr.getyPos()-5);
                        repaint();
                    }
                });
                curr.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        curr.setActive();
                        match.setActive();
                        repaint();
                    }
                });
                curr.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        curr.setInactive();
                        match.setInactive();
                        repaint();
                    }
                });
            }
        }

        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                curr = p2[i][j];
                match = p1[i][j];
                curr.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        curr.setLocation(e.getX()+curr.getxPos() - 5, e.getY()+curr.getyPos()-5);
                        repaint();
                    }
                });
                curr.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        curr.setActive();
                        match.setActive();
                        repaint();
                    }
                });
                curr.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        curr.setInactive();
                        match.setInactive();
                        repaint();
                    }
                });
            }
        }
    }


}
