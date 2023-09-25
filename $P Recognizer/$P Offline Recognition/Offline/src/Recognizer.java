/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// importing necessary libraries
import java.text.DecimalFormat;
import java.util.*;

public class Recognizer {

    public Result bestResult;

    // recognize function that also creates the nbest list.
    public List<Result> RecognizeWithNBestList(ArrayList<Point> points, ArrayList<PointCloud> puts, String user){
        List<Result> bestList=new ArrayList<>();    //to store the n-best list
        int u = -1;
        double b = Double.POSITIVE_INFINITY;
        double d;
        DecimalFormat df = new DecimalFormat("#.##"); //to format the score to 2 decimal places

        for (int i = 0; i < puts.size(); i++){
            d = GreedyCloudMatch(points, puts.get(i));
            double score= 0.0;
            if(d>1.0){
                score = 1.0/d;
            }
            else{
                score =1.0;
            }
            double sd = Double.parseDouble(df.format(score));
            int insnumber=puts.get(i).InstanceNumber;
            bestList.add(new Result(puts.get(i).Name, sd, user, insnumber)); //adding the point to the n best list
            if (d < b) {
                b = d; // best (least) distance
                u = i; // point-cloud index
            }
        }
        Collections.sort(bestList, new Comparator<Result>() {
            public int compare(Result r1, Result r2) {
                return Double.compare(r2.getScore(), r1.getScore());
            } // compare the scores to get the best scores instances
        });
        int minimum = Math.min(bestList.size(),50);
        List<Result> nbestlist = bestList.subList(0,minimum); //get the best 50 instances for the n best list.
        bestResult =nbestlist.get(0);
        return nbestlist;
    }


   //function to return the minimum distance between the point cloud and the current point.
    public double GreedyCloudMatch(ArrayList<Point> points1, PointCloud P)
    {
        double e = 0.50;
        double step = Math.floor(Math.pow(points1.size(), 1.0 - e));
        var min = Double.MAX_VALUE;
        for (int i = 0; i < points1.size(); i += step) {
            double d1 = CloudDistance(points1, P.points, i);
            double d2 = CloudDistance(P.points, points1, i);
            min = Math.min(min, Math.min(d1, d2)); // min3
        }
        return min;
    }

    public double CloudDistance(ArrayList<Point> pts1,ArrayList<Point> pts2, int start)
    {
        // pts1.length == pts2.length
        boolean[] matched = new boolean[pts1.size()];
        for (int k = 0; k < pts1.size(); k++)
            matched[k] = false;
        double sum = 0;
        int i = start;
        do
        {
            int index = -1;
            double min = Double.MAX_VALUE;
            for (int j = 0; j < matched.length; j++)
            {
                if (!matched[j]) {
                    double d = Distance(pts1.get(i), pts2.get(j));
                    if (d < min) {
                        min = d;
                        index = j;
                    }
                }
            }
            matched[index] = true;
            double weight = 1 - ((((i - start) + pts1.size()) % pts1.size()) / pts1.size());
            sum += weight * min;
            i = (i + 1) % pts1.size();
        } while (i != start);
        return sum;
    }

    public double Distance(Point p1, Point p2) // Euclidean distance between two points
    {
        var dx = p2.x - p1.x;
        var dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
