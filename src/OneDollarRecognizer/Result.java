package OneDollarRecognizer;

public class Result {
    String Name;
    double Score;
    long time;
    public Result(String name, double score, long ms){
        Name=name;
        Score=score;
        time=ms;
    }
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
