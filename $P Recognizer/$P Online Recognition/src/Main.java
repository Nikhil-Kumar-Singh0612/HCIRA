/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// importing all the necessary libraries
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.text.DecimalFormat;

// Main class for live recognition
public class Main extends JFrame implements MouseListener, MouseMotionListener {

    // Storing all the strokes in the arraylist
    private final ArrayList<ArrayList<Point>> strokes;
    // Storing the points of the current stroke being drawn
    private ArrayList<Point> currentStroke;
    // Temporary arraylist used for swapping
    public ArrayList<Point> prevlines=new ArrayList<>();
    // clear button for clearing the canvas
    private final JButton clearButton;
    // to track the line count
    public static int currentlinenum=1;
    private final JPanel canvas;
    // for printing the label on canvas
    public static JLabel l = new JLabel("$P Recognizer");
    //MAin Constructor
    public Main() {
        setTitle("Canvas");
        strokes = new ArrayList<>();
        currentStroke = new ArrayList<>();
        clearButton = new JButton("Clear");
        // Action Listener for clear button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                strokes.clear();
                currentStroke.clear();
                prevlines.clear();
                l.setText("Draw again...");
                canvas.repaint();
            }
        });
        // Displaying the gesture on the canvas
        canvas = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.BLACK);

                for (ArrayList<Point> line : strokes) {
                    for (int i = 1; i < line.size(); i++) {
                        g2d.drawLine((int) line.get(i - 1).x, (int) line.get(i - 1).y, (int) line.get(i).x, (int) line.get(i).y);}
                }

                // Draw the current line
                if (!currentStroke.isEmpty()) {
                    for (int i = 1; i < currentStroke.size(); i++) {
                        g2d.drawLine((int) currentStroke.get(i - 1).x, (int) currentStroke.get(i - 1).y, (int) currentStroke.get(i).x, (int) currentStroke.get(i).y);
                    }
                }
            }
        };
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(clearButton, BorderLayout.SOUTH);
        add(l,BorderLayout.BEFORE_FIRST_LINE);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MouseListener methods

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            currentStroke.add(new Point(e.getPoint().x, e.getPoint().y, currentlinenum));
        }
    }
    // Mouse release method
    public void mouseReleased(MouseEvent e) {
        // if left click is performed
        if (e.getButton() == MouseEvent.BUTTON1) {
            strokes.add(currentStroke);
            currentStroke = new ArrayList<>();
            currentlinenum++;
            l.setText("Stroke #: " + strokes.size());
            canvas.repaint();
        }
        // if right click is pressed
        else if (e.getButton() == MouseEvent.BUTTON3) {
            // storing the points in prevlines
            if(strokes.size()>0){
                for(ArrayList<Point> p : strokes){
                    prevlines.addAll(p);
                }
                // Fewer points on canvas
                if(prevlines.size()<10){
                    l.setText("Too few points made. Please try again.");
                }
                else{
                    Recognizer odr = new Recognizer();
                    odr.loadTemplates();
                    Point[] prev= new Point[prevlines.size()];
                    int k=0;
                    for(Point p: prevlines){
                        prev[k++]= new Point(p.x, p.y);
                    }
                    // Call recognize method with user points
                    Result r = odr.Recognize(prev);
                    String shapeName = r.getName();
                    Double score = r.getScore();
                    DecimalFormat df = new DecimalFormat("0.00");
                    long time = r.getTime();
                    // Printing the output on canvas
                    l.setText("Result: " + shapeName + " (" + df.format(score) + ")" + " " + time + " ms");
                }
            }
        }
    }
    // Other Mouse Functions not related to the present code
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // MouseMotionListener methods
    // store the points when mouse is dragged
    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            currentStroke.add(new Point(e.getPoint().x, e.getPoint().y, currentlinenum));
            canvas.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {}
    // main method
    public static void main(String[] args) {
        new Main();
    }
}
