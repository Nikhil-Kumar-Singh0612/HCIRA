/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// Custom class to store the result in desired format
public class Result {
    // stores name , recognition score and time taken for recognition
    String Name;
    double Score;
    long time;
    // Constructor to initialize Result
    public Result(String name, double score, long ms){
        Name=name;
        Score=score;
        time=ms;
    }
    // Necessary getter methods
    public String getName(){
        return Name;
    }
    public double getScore(){
        return Score;
    }
    public long getTime(){
        return time;
    }
}
