/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/

//Importing the necessary libraries
import java.util.ArrayList;
import java.util.Arrays;

public class Helper {
    public ArrayList<Point> Resample(ArrayList<Point> points, int n)   //resample function for the points
    {

        double I = PathLength(points) / (n - 1); // interval length
        double D = 0.0;
        ArrayList<Point> np = new ArrayList<>();
        np.add(points.get(0));
        for (int i = 1; i < points.size(); i++) //
        {
            if (points.get(i).getId() == points.get(i-1).getId())
            {    // calculate the distance
                double d = Distance(points.get(i-1), points.get(i));
                if ((D + d) >= I)
                {   //
                    double qx = points.get(i-1).x + ((I - D) / d) * (points.get(i).x - points.get(i-1).x);
                    double qy = points.get(i-1).y + ((I - D) / d) * (points.get(i).y - points.get(i-1).y);
                    Point q = new Point(qx, qy, points.get(i).id);
                    np.add(q);
                    points.add(i,q);
                    D = 0.0;
                }
                else D += d;
            }
        }
        if (np.size() == n - 1) // sometimes we fall a rounding-error short of adding the last point, so add it if so
            np.add(points.get(points.size()-1));
        return np;
    }
    public ArrayList<Point> Scale(ArrayList<Point> points) //scaling the points here
    {   //
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE, minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        for (Point pt : points) {
            minX = Math.min(minX, pt.getX());
            minY = Math.min(minY, pt.getY());
            maxX = Math.max(maxX, pt.getX());
            maxY = Math.max(maxY, pt.getY());
        }
        double size = Math.max(maxX - minX, maxY - minY);
        ArrayList<Point> np = new ArrayList<>();
        for (Point point : points) {
            double qx = (point.x - minX) / size;
            double qy = (point.y - minY) / size;
            np.add(new Point(qx, qy, point.id));   //adding the scaled points to the list
        }
        return np;
    }
    public ArrayList<Point> TranslateTo(ArrayList<Point> points, Point pt) // translates points' centroid to pt
    {
        Point c = Centroid(points);
        ArrayList<Point> np = new ArrayList<>();
        for (Point point : points) {
            double qx = point.x + pt.x - c.x;
            double qy = point.y + pt.y - c.y;
            np.add(new Point(qx, qy, point.id)); //add the translated points to the list.
        }
        return np;
    }
    public Point Centroid(ArrayList<Point> points) //get the new centroid of the points
    {
        double x = 0.0, y = 0.0;
        for (Point point : points) {
            x += point.x; // get the sum of all x coordinates
            y += point.y;  //get the sum of all y coordinates
        }
        x /= points.size();  // divide the sum by size to get the centroid point
        y /= points.size();
        return new Point(x, y, 0);   //returns the centroid of the points list.
    }
    public double PathLength(ArrayList<Point> points) // length traversed by a point path
    {
        double d = 0.0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).id == points.get(i-1).id)
                d += Distance(points.get(i-1), points.get(i));
        }
        return d; // returns the path length
    }
    public double Distance(Point p1, Point p2) // Euclidean distance between two points
    {
        var dx = p2.x - p1.x;
        var dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}