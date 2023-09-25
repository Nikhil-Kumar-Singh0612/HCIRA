/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// PointCloud class to perform preprocessing of the templates
public class PointCloud extends Helper{
    // Constants used in $P Recognizer
    public int Numpoints=32;
    public Point Origin= new Point(0,0,0);
    // Name of the gesture
    public String Name;

    // Points associated with the gesture
    public Point[] points;

    // Constructor for initializing the PointCloud object with name and points

    public PointCloud(String name, Point[] p){
        Name=name;
        this.points=Resample(p, Numpoints);
        this.points = Scale(this.points);
        this.points = TranslateTo(this.points, Origin);
    }
    // method to get the points
    public Point[] getPoints() {
        return points;
    }
    // method to get the name of the gesture
    public String getName() {
        return Name;
    }
}
