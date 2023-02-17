package OneDollarRecognizer;

import java.util.ArrayList;


class Rectangle {
    double X, Y, Height, Width;

    public Rectangle(double x, double y, double h, double w) {
        X = x;
        Y = y;
        Height = h;
        Width = w;
    }
}

public class Preprocess {
    public  double Distance(PointClass p1, PointClass p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    //function for calculating the path length
    public  double PathLength(ArrayList<PointClass> points) {
        double d = 0.0;
        for (int i = 1; i < points.size(); i++)
            d += Distance(points.get(i - 1), points.get(i));
        return d;
    }

    // function for forming Bounding Boxes
    public  Rectangle BoundingBox(ArrayList<PointClass> points) {
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE, minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        for (PointClass pt : points) {
            minX = Math.min(minX, pt.getX());
            minY = Math.min(minY, pt.getY());
            maxX = Math.max(maxX, pt.getX());
            maxY = Math.max(maxY, pt.getY());
        }
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }
    // function for calculating centroid
    public  PointClass Centroid(ArrayList<PointClass> points) {
        double x = 0.0, y = 0.0;
        double n =  points.size();
        for (PointClass pt : points) {
            x += pt.getX();
            y += pt.getY();
        }
        x /= n;
        y /= n;
        return new PointClass(x, y);
    }
    // function for calculating indicative angle
    public  double IndicativeAngle(ArrayList<PointClass> points) {
        PointClass c = Centroid(points);
        return Math.atan2(c.getY() - points.get(0).getY(), c.getX() - points.get(0).getX());
    }


    // Function of Resampling the points to get fixed number of points
    public  ArrayList<PointClass> resample(ArrayList<PointClass> points) {
        int n = 64;
        double I = PathLength(points) /(double) (n - 1);
        double D = 0.0;
        ArrayList<PointClass> newPoints = new ArrayList<>();
        newPoints.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            double d = Distance(points.get(i - 1), points.get(i));
            if ((D + d) >= I) {
                double qx = points.get(i - 1).getX()
                        + ((I - D) / d) * (points.get(i).getX() - points.get(i - 1).getX());
                double qy = points.get(i - 1).getY()
                        + ((I - D) / d) * (points.get(i).getY() - points.get(i - 1).getY());
                PointClass q = new PointClass(qx, qy);
                newPoints.add(q);
                points.add(i, q);
                D = 0.00;
            } else
                D += d;
        }
        // Avoid rounding-error by checking the below condition and adding the last point if not added
        if (newPoints.size() == (n - 1)) {
            newPoints.add(points.get(points.size()-1));
        }
        return newPoints;
    }
    // Function for Rotating the points with angle
    public  ArrayList<PointClass> RotateBy(ArrayList<PointClass> points, double radians) {
        PointClass c = Centroid(points);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        ArrayList<PointClass> newPoints = new ArrayList<>();
        for (PointClass pt : points) {
            double qx = (pt.getX() - c.getX()) * cos - (pt.getY() - c.getY()) * sin + c.getX();
            double qy = (pt.getX() - c.getX()) * sin - (pt.getY() - c.getY()) * cos + c.getY();
            newPoints.add(new PointClass(qx, qy));
        }
        return newPoints;
    }
    //Function for Scaling the points till given size
    public  ArrayList<PointClass> ScaleTo(ArrayList<PointClass> points) {
        double size = 250.0;
        Rectangle B = BoundingBox(points);
        ArrayList<PointClass> newPoints = new ArrayList<>();
        for (PointClass pt : points) {
            double qx = pt.getX() * (size / B.Width);
            double qy = pt.getY() * (size / B.Height);
            newPoints.add(new PointClass(qx, qy));
        }
        return newPoints;
    }
    // Function for Translating the points to the point p
    public  ArrayList<PointClass> TranslateTo(ArrayList<PointClass> points) {
        PointClass p = new PointClass(0.0, 0.0);
        PointClass c = Centroid(points);
        ArrayList<PointClass> newPoints = new ArrayList<>();
        for (PointClass pt : points) {
            double qx = pt.getX() + p.getX() - c.getX();
            double qy = pt.getY() + p.getY() - c.getY();
            newPoints.add(new PointClass( qx, qy));
        }
        return newPoints;
    }

    public double[] Vectorize(ArrayList<PointClass> points){
        double sum = 0.0;
        double magnitude;
        double[] vector = new double[points.size() * 2];
        int index = 0;
        for (PointClass v : points) {
            vector[index++] = v.x;
            vector[index++] = v.y;

            sum += v.x * v.x + v.y * v.y;
        }

        magnitude = Math.sqrt(sum);
        for (int i = 0; i < vector.length; i++)
            vector[i] /= magnitude;

        return vector;
    }


}
