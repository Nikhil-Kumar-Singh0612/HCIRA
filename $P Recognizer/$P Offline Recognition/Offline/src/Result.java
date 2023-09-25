/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/

// Custom class to store the result in desired format
public class Result {
    // stores name , recognition score, user and instance
    String Name;
    double Score;

    String User;

    int Instance;
    //necessary constructors
    public Result(String name, double score,  String user){
        Name=name;
        Score=score;
        User =user;
    }
    //Result Constructor
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


