import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class controlPoint extends JButton {

    private int xPos;
    private int yPos;

    private int xID;
    private int yID;

    private controlPoint east;
    private controlPoint south;
    private controlPoint southeast;

    private boolean isActive = false;

    public controlPoint(int x, int y, int xid, int yid){
        xPos = x;
        yPos = y;
        xID = xid;
        yID = yid;

        setBounds(xPos, yPos, 10, 10);

        //set it red
        if (isActive){
            setBackground(Color.red);
        }
        else{
            setBackground(Color.black);
        }
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public int getxID(){
        return xID;
    }

    public int getyID(){
        return yID;
    }

    public void setEastNeighbor(controlPoint en){
        east = en;
    }

    public void setSouthNeighor(controlPoint sn){
        south = sn;
    }

    public void setSouthEastNeighor(controlPoint sen){
        southeast = sen;
    }

    public controlPoint getEastNeighbor(){
        return east;
    }

    public controlPoint getSouthNeighbor(){
        return south;
    }

    public controlPoint getSouthEastNeighbor(){
        return southeast;
    }

    public void setLocation(int x, int y){
        xPos = x;
        yPos = y;
    }

    public void setActive(){
        isActive = true;
    }

    public void setInactive(){
        isActive = false;
    }
}
