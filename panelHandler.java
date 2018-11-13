public class panelHandler {
    public gridPanel panel1;
    public gridPanel panel2;

    public panelHandler(int x, int y){
        gridPanel panel1 = new gridPanel(x, y);
        gridPanel panel2 = new gridPanel(x, y);

        setListeners();
    }

    private void setListeners(){
        //add the action listeners for all the buttons
    }


}
