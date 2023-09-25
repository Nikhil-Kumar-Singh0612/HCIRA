/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;

//import necessary package for using ArrayList
import java.util.ArrayList;

public class Template  extends Preprocess{
    //Template name and points arraylist
    public String Name;
    public ArrayList<PointClass> Points;

    public String fullName;

    public double[] Vector;

    // Template constructor for creating templates and performing preprocessing on the points
    public Template(String Name, ArrayList<PointClass> Points) {
        this.Name = Name;
        this.Points = Points;
        this.Points = resample(this.Points);
        double radians = IndicativeAngle(this.Points);
        this.Points = RotateBy(this.Points, -radians);
        this.Points = ScaleTo(this.Points);
        this.Points = TranslateTo(this.Points);
        this.Vector = Vectorize(this.Points);
    }
}
