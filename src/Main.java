// importing the necessary libraries and packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serial;
import java.util.*;
import OneDollarRecognizer.*;
import Result.Result;


// Main class
public class Main {
    public static void main(String[] args) {
        // create an instance of JFrame for creating the frame which acts as a container
        JFrame canvas_frame = new JFrame("Canvas");
        Container content_pane = canvas_frame.getContentPane();
        content_pane.setLayout(new BorderLayout());
        // create an instance of DrawCanvas and add it to the content
        DrawCanvas canvas = new DrawCanvas();
        content_pane.add(canvas, BorderLayout.CENTER);
        // we used JPanel for the controls such as minimize, close and maximize
        JPanel add_controls = new JPanel();
        content_pane.add(add_controls, BorderLayout.NORTH);
        // created clearButton to reset the canvas using JButton
        JButton clear = new JButton("Clear");
        clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clear.setPreferredSize(new Dimension(40, 40));
        clear.addActionListener(e -> canvas.clear());
        Label label = canvas.recognizeGesture();
//
//        //controls.add(clearButton);
//        // add the clearButton to the bottom of the frame
        canvas_frame.add(label);
        canvas_frame.add(clear,"South");
        canvas_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting the frame size
        canvas_frame.setSize(500, 500);
        // setting visibility to true
        canvas_frame.setVisible(true);
    }
}

class DrawCanvas extends JComponent {
    @Serial private static final long serialVersionUID = 1L;

    public ArrayList<Point> points = new ArrayList<>();
    // set statingPoint to NULL

     Point startingPoint = null;

    public DrawCanvas() {
        //Add Mouse Event Listener when mouse is dragged
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // getting the ending Point
                Point endingPoint = e.getPoint();
                System.out.println(endingPoint);
                points.add(endingPoint);
                System.out.println(points.size());
                if (startingPoint != null) {
                    // get graphics to draw the stroke on the canvas
                    Graphics graphics = getGraphics();
                    Graphics2D graphics2 = (Graphics2D) graphics;
                    graphics2.setStroke(new BasicStroke(5));
                    graphics2.setColor(Color.BLACK);
                    // draw the line from starting point to the ending point
                    graphics2.drawLine(startingPoint.x, startingPoint.y, endingPoint.x, endingPoint.y);
                }
                // make the ending point as the starting point for the next line
                startingPoint = endingPoint;

            }
        });
    }
    // clearing the canvas and making startPoint null
    public void clear() {
        startingPoint = null;
        repaint();
    }

    public Label recognizeGesture(){
        OneDollarRecognizer odr = new OneDollarRecognizer();
        Result r = odr.Recognize(points);
        String name = r.getName();
        double score= r.getScore();
        long t = r.getTime();
        String s = "Result: "+ name + " " + "(" + score + ")" + "in " + t + "ms.";
        System.out.println(s);
        return new Label(s);
    }
}





