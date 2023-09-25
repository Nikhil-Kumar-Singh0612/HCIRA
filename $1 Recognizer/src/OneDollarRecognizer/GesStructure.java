/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;

// importing the necessary packages
import java.util.ArrayList;


// GesStructure class for storing gestures
public class GesStructure {
    // label for the gesture label name
    public String label;
    // full name of gesture
    public String fullName;
    // instance number of the gesture
    public int InstanceNumber;
    // Points associated with gesture
    public ArrayList<PointClass> points;
    // Constructors for initializing data members
    public GesStructure(String name, ArrayList<PointClass> Points) {
        label = name;
        points = Points;
    }

    public GesStructure(String name, ArrayList<PointClass> Points, int instance) {
        label = name;
        points = Points;
        InstanceNumber = instance;
    }

    public GesStructure() {
    }
    // Necessary getters
    public String getLabel() {
        return label;
    }

    public ArrayList<PointClass> getPoints() {
        return points;
    }

    public int getInstanceNumber() {
        return InstanceNumber;
    }

    public String getFullName() {
        return fullName;
    }
}
