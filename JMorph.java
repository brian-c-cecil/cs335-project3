import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JMorph {
    JFrame frame;
    int animationDuration = 1;
    int gridResolution = 10;

    public static void main(String args[]){
        new JMorph();
    }

    public JMorph(){
        frame = new JFrame("JMorph");

        buildMenus();
        buildControls();
        buildGrids();

        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    private void buildMenus () {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu settingsMenu = new JMenu("Settings");
        menuBar.add(settingsMenu);

        JMenuItem openFileMenuItem = new JMenuItem("Open");
        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        //fileMenu.add(openFileMenuItem);

        //fileMenu.addSeparator();
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenuItem animationDurationMenuItem = new JMenuItem("Animation Duration");
        animationDurationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String animationDurationChoice = (String)JOptionPane.showInputDialog(frame,
                        "Specify a time duration (secs).", "Animation Duration", JOptionPane.PLAIN_MESSAGE,
                        null, null, null);

                if ((animationDurationChoice != null) && (animationDurationChoice.length() > 0)) {
                    try{
                        int convertedInput = Integer.parseInt(animationDurationChoice);

                        if(convertedInput < 0){
                            JOptionPane.showMessageDialog(frame, "Value is below zero.");
                        }
                        else{
                            animationDuration = convertedInput;
                        }
                    }

                    //catches if user input is not an integer
                    catch(Exception e){
                        JOptionPane.showMessageDialog(frame, "Value is not an integer.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "No value specified.");
                }
            }
        });
        settingsMenu.add(animationDurationMenuItem);

        JMenuItem gridResolutionMenuItem = new JMenuItem("Grid Resolution");
        gridResolutionMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String gridResolutionChoice = (String)JOptionPane.showInputDialog(frame,
                        "Specify a grid resolution.", "Grid Resolution", JOptionPane.PLAIN_MESSAGE,
                        null, null, null);

                if ((gridResolutionChoice != null) && (gridResolutionChoice.length() > 0)) {
                    try{
                        int convertedInput = Integer.parseInt(gridResolutionChoice);

                        if(convertedInput < 0){
                            JOptionPane.showMessageDialog(frame, "Value is below zero.");
                        }
                        else{
                            gridResolution = convertedInput;
                        }
                    }

                    //catches if user input is not an integer
                    catch(Exception e){
                        JOptionPane.showMessageDialog(frame, "Value is not an integer.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "No value specified.");
                }
            }
        });
        settingsMenu.add(gridResolutionMenuItem);

        frame.setJMenuBar(menuBar);
    }

    private void buildControls(){
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        frame.add(controlPanel);

        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //call function to preview movement of control points
            }
        });
        controlPanel.add(previewButton);

        JButton morphButton = new JButton("Morph");
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //call function to morph the two images
            }
        });
        //controlPanel.add(morphButton);
    }

    private void buildGrids(){
      panelHandler handler = new panelHandler(gridResolution, gridResolution);
      frame.add(handler.panel1);
      frame.add(handler.panel2);
    }
}
