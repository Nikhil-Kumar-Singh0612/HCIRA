/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;

// Result class for storing user, name, instance and score for gestures
public class Result {
    String User;
    String Name;

    int Instance;
    double Score;
    //long time;

    //necessary constructors
    public Result(String name, double score,  String user){
        Name=name;
        Score=score;
        User =user;
    }

    public Result(String name, double score,  String user, int ins){
        Name=name;
        Score=score;
        User =user;
        Instance = ins;
    }
    //necessary getters
    public String getName(){
        return Name;
    }
    public double getScore(){
        return Score;
    }

    public int getInstance() {
        return Instance;
    }

    public String getUser() {
        return User;
    }
}
