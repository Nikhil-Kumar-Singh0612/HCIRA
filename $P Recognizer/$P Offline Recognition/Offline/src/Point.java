/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
//Importing the necessary libraries
public class Point {
    public double x;
    public double y;

    public int id;
    // constructors for create new Point from x and y coordinate
    public Point(int newx, int newy, int Id){
        x= newx;
        y= newy;
        id=Id;
    }

    public Point(double new_x, double new_y, int Id) {
        x = new_x;
        y = new_y;
        id=Id;
    }

    public Point(double new_x, double new_y) {
        x = new_x;
        y = new_y;
        id=0;
    }

    // getting x coordinate
    public double getX() {
        return x;
    }
    // getting y coordinate
    public double getY() {
        return y;
    }
    //getting ID
    public int getId() {
        return id;
    }
}
