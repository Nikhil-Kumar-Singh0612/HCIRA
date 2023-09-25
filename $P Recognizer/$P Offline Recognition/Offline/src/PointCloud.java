/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// importing necessary libraries
import java.util.ArrayList;

public class PointCloud extends Helper{
    public int Numpoints=32;
    public Point Origin= new Point(0,0,0);   //to form a point
    public String Name;
    public ArrayList<Point> points;   // arraylist of point type.

    public int InstanceNumber;

    public PointCloud(String name, ArrayList<Point> p){   ///parameterised constructor
        Name=name;
        this.points = Resample(p, Numpoints);   //call resample on the stored points.
        this.points = Scale(this.points);       //scale the points
        this.points = TranslateTo(this.points, Origin); //translate
    }

    public PointCloud(){}

    public PointCloud(String name, ArrayList<Point> p, int ins){
        Name=name;
        points = p;
        InstanceNumber = ins;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public String getName() {
        return Name;
    }

    public int getInstanceNumber() {
        return InstanceNumber;
    } // return the instance number  of the gesture
}
