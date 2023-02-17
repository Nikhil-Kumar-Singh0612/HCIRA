import OneDollarRecognizer.OneDollarRecognizer;
import OneDollarRecognizer.PointClass;
import OneDollarRecognizer.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private PointClass start;
    private PointClass stop;
    public static ArrayList<PointClass> points = new ArrayList<>();
    public static ArrayList<PointClass> prevpoints;

    public static JPanel canvas;
    public static JLabel l = new JLabel();
    private Shape shape;
    public Main() {
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
        JButton clearButton = new JButton("Clear");
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> {
            start=null;
            stop=null;
            prevpoints.clear();
            canvas.repaint();
            l.setText("");
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(l,BorderLayout.BEFORE_FIRST_LINE);
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(clearButton, BorderLayout.SOUTH);
        setVisible(true);
    }


    class PathListener extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            start = new PointClass(event.getPoint().x, event.getPoint().y);
            // Add start point
            points.add(start);
            shape = new Path2D.Double();
        }

        public void mouseDragged(MouseEvent event) {
            stop = new PointClass(event.getPoint().x, event.getPoint().y);
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

            if (!points.isEmpty()) {
                prevpoints = new ArrayList<>(Arrays.asList(new PointClass[points.size()]));
                Collections.copy(prevpoints, points);
                if(prevpoints.size()<10){
                    l.setText("Too few points made. Please try again.");
                }
                else {
                    OneDollarRecognizer odr = new OneDollarRecognizer();
                    odr.loadTemplates();
                    Result r = odr.Recognize(prevpoints, true);
                    String shapeName = r.getName();
                    Double score = r.getScore();
                    DecimalFormat df = new DecimalFormat("0.00");
                    long time = r.getTime();
                    l.setText("Result: " + shapeName + " (" + df.format(score) + ")" + " " + time + " ms");
                }
            }

            // Clear from previous run
            points.clear();
        }

    }

    public static void main(String[] args) {
        new Main();
    }

}
