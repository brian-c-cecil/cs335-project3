import javax.swing.*;
import java.awt.*;

public class controlPoint extends JButton {

    public int xPos;
    public int yPos;
    private int rowID;
    private int colID;

    public controlPoint east;
    public controlPoint south;
    public controlPoint southeast;

    public int eLocx;
    public int sLocx;
    public int seLocx;

    public int eLocy;
    public int sLocy;
    public int seLocy;

    private boolean isActive = false;

    public controlPoint(int x, int y){
        xPos = x;
        yPos = y;

        setBounds(xPos, yPos, 10, 10);

        //set it red
        if (isActive){
            setBackground(Color.red);
        }
        else{
            setBackground(Color.black);
        }
    }

    public int getRowID(){
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public int getColID(){
        return colID;
    }

    public void setColID(int colID){
        this.colID = colID;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public void setEastNeighbor(controlPoint en){
        east = en;
    }

    public void setSouthNeighbor(controlPoint sn){
        south = sn;
    }

    public void setSouthEastNeighbor(controlPoint sen){
        southeast = sen;
    }

    public void setActive(){
        isActive = true;
    }

    public void setInactive(){
        isActive = false;
    }

    public void setLocation(int x, int y){
        xPos = x;
        yPos = y;
    }

    public void relocate(){
        setBounds(xPos, yPos, 10, 10);
    }

    public void setNeighborLocations(){
        eLocx = east.getxPos();
        eLocy = east.getyPos();

        sLocx = south.getxPos();
        sLocy = south.getyPos();

        seLocx = southeast.getxPos();
        seLocy = southeast.getyPos();
    }
}
