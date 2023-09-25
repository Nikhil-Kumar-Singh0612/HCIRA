Readme file

Project part 1: Drawing a canvas with clear button to reset the canvas

Language selected: JAVA

Step 1: Setting up the developer environment.

1. Downloaded the latest version of IntelliJ idea. 
2. While setting it up, we needed JDK so got the latest version of Open JDK which is 19 for the compilation of the java code.
3. Added the environment variables and created a new project in the folder HCIRA named Canvas and the code resides in Main.java file.

Step 2: Instantiating a canvas

1. For this purpose we needed the Java AWT (Abstract Window Toolkit) package to develop the canvas window. It contains classes for various APIs like Text Field, label, buttons, etc.
2. It also has an extension to it called java Swing, which provides much improved functionality and excellent event handling support. They are platform independent in nature.
Lines 2-4 in Main.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

3. For creating the canvas, we created an instance of JFrame and created a container for the canvas. 
4. Created an instance of DrawCanvas named canvas and created menu controls using JPanel and added canvas and controls to the container.

Lines 9-18 in Main class and main() method

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


5. Setting the default dimensions for the frame and set visibility to true.This gives us a plain white canvas to draw on at the center of the container.

Lines 32-36 in Main class and main() method

canvas_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//setting the frame size
canvas_frame.setSize(500, 500);
// setting visibility to true
canvas_frame.setVisible(true);

Step 3: Listening for mouse or touch events.

1. For event handling we use the extension of java AWT which is SWING. It provides an abstract class called MouseMotionAdapter which comes with two methods mouseDragged and mouseMoved. 
2. Here we have used the method mouseDragged with object of MouseEvent to fetch the movement when the user drags the mouse. Initially starting point is null and assigned when user drags the mouse.
3. As the mouse is dragged we got multiple points and we stored the starting point and when the starting point is not null we make the current point as end point. 
4. Now using the Graphics and Graphics2D libraries we draw the line between start and end point and changed the start point to the current end point getting us a smooth line by joining a series of points so close that it forms an uni-stroke. Graphics2D also allows us to edit the width and color of the the strokes being drawn on canvas. 

Lines 43 - 64 in DrawingCanvas class constructor

private Point startingPoint = null;

public DrawCanvas() {
    //Add Mouse Event Listener when mouse is dragged
    this.addMouseMotionListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
            // getting the ending Point
            Point endingPoint = e.getPoint();
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

5. However it is also possible to get multi-stroke pattern but in this case the canvas automatically joins the new point to the last endpoint. Thus, it feels, we are drawing uni-stroke.  

Step 4: Clearing the canvas

1. Now there could a possibility of human error while drawing on the canvas hence it makes it necessary to have a clear button for resetting the canvas. 
2. For this we have used a simple java button and named it clear and when the user hovers over the button the cusrsor changes to pointer (hand cursor) and set the dimensions.

Lines 20-22 in Main class in main() method 

JButton clear = new JButton("Clear");
clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
clear.setPreferredSize(new Dimension(40, 40));

3. To feed the task of this button we needed action listener so added the class object of ActionListener() and used its method actionPerformed(ActionEvent object). 

Lines 23-27 in Main class in main() method

clear.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
            canvas.clear();
        }
    });

4. This method provides a functionality called repaint which resets the canvas and also initiated the starting point as NULL again. The user needs to click the button to reset the canvas. 

Lines 65-69 in DrawCanvas Class 
// clearing the canvas and making startPoint null
public void clear() {
    startingPoint = null;
    repaint();
}

5. Lastly aligned the clear button to the bottom of the canvas. 

Lines 29-30 in Main class in main() method

// add the clearButton to the bottom of the frame
canvas_frame.add(clear,"South");

Note: The source code is included in the src folder in Main.java file and we also included output files with classes generated for reference.






  


