/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;

// importing the necessary packages
import java.util.ArrayList;

// GestureStructure class for storing user and gesture templates

public class GestureStructure {
    public String user;
    public ArrayList<GesStructure> Template;
    // Constructors for initializing data members

    public GestureStructure(String name, ArrayList<GesStructure> t) {
        user = name;
        Template = t;
    }
    // Necessary getters
    public String getUser() {
        return user;
    }

    public ArrayList<GesStructure> getTemplate() {
        return Template;
    }

}
