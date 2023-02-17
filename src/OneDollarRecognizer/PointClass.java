package OneDollarRecognizer;

// PointClass for storing the templates
public class PointClass {
    public double x;
    public double y;
    // constructors for create new Point from x and y coordinate
    public PointClass(int newx, int newy){
        x= newx;
        y= newy;
    }

    public PointClass(double new_x, double new_y) {
        x = new_x;
        y = new_y;
    }
    // getting x coordinate
    public double getX() {
        return x;
    }
    // getting y coordinate
    public double getY() {
        return y;
    }
}
