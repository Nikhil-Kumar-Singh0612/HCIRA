/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

//point class from previous parts to store the coordinates.
class Point
{
    double x,y;
    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }
    public Point(int x, int y){
        this.x = (double) x;
        this.y = (double) y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

public class Main extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private Point start;
    private Point stop;
    public static ArrayList<Point> points = new ArrayList<>(); // arraylist of point class type to store the points drawn by the user.
    public static ArrayList<Point> prevpoints;

    public static JPanel canvas; // drawing the canvas


    public static  JLabel gestureName = new JLabel("gesture type : triangle"); // the default label on the canvas



    public static JLabel sampleNumber = new JLabel("sample #: 1");  // the default label on the canvas


    // array to store the names of all the gestures.
    String[] shapes = {"triangle", "x", "rectangle", "circle", "check", "caret", "zig-zag", "arrow", "left-square-bracket", "right-square-bracket", "v", "delete", "left-curly-brace" , "right-curly-brace", "star", "pigtail"};

    private Shape shape;

    int savecount= 1; //to store the number of clicks on save button for each gesture (10)
    int clickcount=0; //to store the total number of clicks of save button for each user (16*10 )
    int foldercount=1; //to keep a count of different users
    int gesturecount=0; // to keep a count of gestures (16 per user)

    // After the user clicks the save button it fetches the file path and control goes to the XMLEntry class.
    public void savefile(String shapename, int count) {

        XMLEntry xml = new XMLEntry(); //object of the XMLEntry class
        String folderPath = "user" + foldercount + "/"; // storing the folder path for each user.

        //calling the writeXML function with parameters: gesture name, instance number, the points, path of the user folder, and user number(1-6)
        xml.writeXML(shapename, count, prevpoints, folderPath, foldercount);

        prevpoints.clear();
        canvas.repaint(); //repainting the canvas for the next drawing.
    }

    public Main() {
        //setting up the canvas(prev code refractored)
        setTitle("Canvas");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PathListener listener = new PathListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                super.paintComponent(g2);
                if (start != null && stop != null) {
                    BasicStroke stroke = new BasicStroke(5);
                    Shape strokedShape = stroke.createStrokedShape(shape);
                    g2.draw(strokedShape);
                    g2.fill(strokedShape);
                }
            }
        };
        canvas.setBackground(Color.WHITE);
        // added a save button to store the gesture.
        JButton save = new JButton("Save");
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //functionality to be done after the user clicks save button
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               start = null;
                stop = null;
                savefile(shapes[gesturecount], savecount); //calling savefile function to create a new file and save the points fetched
                savecount++; //increasing the click count
                clickcount++; //increasing the click count
                if(savecount>10) { //if the savecount surpases 10 then change the gesture type
                    gesturecount++;  //increasing the gesture count.

                    if(gesturecount<16) {
                        gestureName.setText("Gesture type: "+ shapes[gesturecount]); //changing the canvas label with the gesture.
                    }else{
                        gesturecount=0;//if the gesture count surpasses 16 then reset it.
                        gestureName.setText("gesture type : triangle");
                    }
                    savecount = 1; //resetting the save counter.
                }
               if(clickcount%160==0){ // if clickcount surpasses 16*10 that mean one user has completed his segment we can go to the next user.
                    foldercount++; //updating the user count.
                    gesturecount=0;
                    savecount=1;
                }
                if(clickcount== 960) //if all the gestures have been drawn, successfully terminate the code.
                    System.exit(0);


                sampleNumber.setText("Sample number: "+ String.valueOf(savecount)); // label to show the instance number for every gesture.
            }

        });


    //clear button to reset the canvas
        JButton clearButton = new JButton("Clear");
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> {
            start=null;
            stop=null;
            prevpoints.clear();
            canvas.repaint();

        });

        //aligning everything on the canvas.
        gestureName.setHorizontalAlignment(SwingConstants.CENTER);
        gestureName.setVerticalAlignment(SwingConstants.CENTER);
        sampleNumber.setHorizontalAlignment(SwingConstants.CENTER);
        sampleNumber.setVerticalAlignment(SwingConstants.CENTER);
        getContentPane().setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new BorderLayout());
        //adding the labels to the canvas.
        labelPanel.add(gestureName, BorderLayout.PAGE_START );
        labelPanel.add(sampleNumber, BorderLayout.PAGE_END);
        add(labelPanel, "North");
        getContentPane().add(canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(save);
        buttonPanel.add(clearButton);
        getContentPane().add(BorderLayout.SOUTH, (buttonPanel));
        setVisible(true); //setting the visibilty to true

    }

    class PathListener extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            start = new Point(event.getPoint().x, event.getPoint().y);
            // Add start point
            points.add(start);
            shape = new Path2D.Double();
        }

        public void mouseDragged(MouseEvent event) {
            stop = new Point(event.getPoint().x, event.getPoint().y);
            // Add end point
            points.add(stop);
            Path2D path = (Path2D) shape;
            path.moveTo(start.x, start.y);
            path.lineTo(stop.x, stop.y);
            shape = path;
            start = stop;
            repaint();
        }

        public void mouseReleased(MouseEvent event) {
            Path2D path = (Path2D) shape;
            try {
                path.closePath();
            } catch (Exception exp) {
                throw new RuntimeException(exp);
            }
            shape = path;
            repaint();

            if (!points.isEmpty()) { //if the points are not empty than store them in the arraylist.
                prevpoints = new ArrayList<>(Arrays.asList(new Point[points.size()]));
                Collections.copy(prevpoints, points);
            }
            // Clear from previous run
            points.clear();
        }

    }

    public static void main(String[] args) {
        //array to store the folders name.

        String[] Users = {"user1", "user2", "user3", "user4", "user5", "user6"};

        for(int i=0; i<6; i++) {
            String cwd = System.getProperty("user.dir");
            cwd += "/" + Users[i]; //getting the path where we need to make the folder
            File folder = new File(cwd);
            folder.mkdirs(); //creating a new folder
        }

        new Main();

    }

}

