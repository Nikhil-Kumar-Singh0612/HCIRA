/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// importing all the necessary libraries
import java.util.ArrayList;
import java.util.Arrays;

// Helper class contains all preprocessing methods
public class Helper {
    // Resampling the points to match the count of 32
    public Point[] Resample(Point[] pts, int n)
    {

        double I = PathLength(pts) / (n - 1); // interval length
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(pts));
        double D = 0.0;
        ArrayList<Point> np = new ArrayList<>();
        np.add(pts[0]);
        for (int i = 1; i < points.size(); i++)
        {
            // if both the id matches
            if (points.get(i).getId() == points.get(i-1).getId())
            {
                double d = Distance(points.get(i-1), points.get(i));
                if ((D + d) >= I)
                {
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
        Point[] newpoints = new Point[np.size()];
        int k=0;
        for(Point p: np){
            newpoints[k++]=p;
        }
        // returning resampled points
        return newpoints;
    }

    // Scaling the points to match the size
    public Point[] Scale(Point[] points)
    {
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
            np.add(new Point(qx, qy, point.id));
        }
        Point[] newpoints = new Point[np.size()];
        int k=0;
        for(Point p: np){
            newpoints[k++]=p;
        }
        // returning the scaled points
        return newpoints;
    }

    // Translating the points to Point pt
    public Point[] TranslateTo(Point[] points,Point pt)
    {
        Point c = Centroid(points);
        ArrayList<Point> np = new ArrayList<>();
        for (Point point : points) {
            double qx = point.x + pt.x - c.x;
            double qy = point.y + pt.y - c.y;
            np.add(new Point(qx, qy, point.id));
        }
        Point[] newpoints = new Point[np.size()];
        int k=0;
        for(Point p: np){
            newpoints[k++]=p;
        }
        return newpoints;
    }

    // Method to find the centroid of the points
    public Point Centroid(Point[] points)
    {
        double x = 0.0, y = 0.0;
        for (Point point : points) {
            x += point.x;
            y += point.y;
        }
        x /= points.length;
        y /= points.length;
        return new Point(x, y, 0);
    }

    //method to calculate the path length for given points
    public double PathLength(Point[] points) // length traversed by a point path
    {
        double d = 0.0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].id == points[i-1].id)
                d += Distance(points[i-1], points[i]);
        }
        return d;
    }

    //method to calculate the distance between two points
    public double Distance(Point p1, Point p2) // Euclidean distance between two points
    {
        var dx = p2.x - p1.x;
        var dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
