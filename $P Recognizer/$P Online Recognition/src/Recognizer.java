/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
// importing all the necessary libraries
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Recognizer {
    // ArrayList of PointClouds to store the 16 predefined templates
    public PointCloud[] PointClouds = new PointCloud[16];

    // Method to load the pre-defined templates
    public void loadTemplates(){
        PointClouds[0]= new PointCloud("T", new Point[]{new Point(30,7,1),new Point(103,7,1),
                new Point(66,7,2),new Point(66,87,2)});
        PointClouds[1]=new PointCloud("N", new Point[]{new Point(177,92,1),new Point(177,2,1),
                new Point(182,1,2),new Point(246,95,2),
                new Point(247,87,3),new Point(247,1,3)});
        PointClouds[2]=new PointCloud("D",new Point[]{new Point(345,9,1),new Point(345,87,1),
                new Point(351,8,2),new Point(363,8,2),new Point(372,9,2),new Point(380,11,2),new Point(386,14,2),new Point(391,17,2),new Point(394,22,2),new Point(397,28,2),new Point(399,34,2),new Point(400,42,2),new Point(400,50,2),new Point(400,56,2),new Point(399,61,2),new Point(397,66,2),new Point(394,70,2),new Point(391,74,2),new Point(386,78,2),new Point(382,81,2),new Point(377,83,2),new Point(372,85,2),new Point(367,86,2),new Point(360,87,2),new Point(355,87,2),new Point(349,86,2)});
        PointClouds[3]=new PointCloud("P",new Point[]{new Point(507,8,1),new Point(507,87,1),
                new Point(513,7,2),new Point(528,7,2),new Point(537,8,2),new Point(544,10,2),new Point(550,12,2),new Point(555,15,2),new Point(558,18,2),new Point(560,22,2),new Point(561,27,2),new Point(562,33,2),new Point(561,37,2),new Point(559,42,2),new Point(556,45,2),new Point(550,48,2),new Point(544,51,2),new Point(538,53,2),new Point(532,54,2),new Point(525,55,2),new Point(519,55,2),new Point(513,55,2),new Point(510,55,2)});
        PointClouds[4]=new PointCloud("X",new Point[]{new Point(30,146,1),new Point(106,222,1),
                new Point(30,225,2),new Point(106,146,2)});
        PointClouds[5]=new PointCloud("H",new Point[]{new Point(188,137,1),new Point(188,225,1),
                new Point(188,180,2),new Point(241,180,2),
                new Point(241,137,3),new Point(241,225,3)});
        PointClouds[6]=new PointCloud("I",new Point[]{new Point(371,149,1),new Point(371,221,1),
                new Point(341,149,2),new Point(401,149,2),
                new Point(341,221,3),new Point(401,221,3)});
        PointClouds[7]=new PointCloud("Exclamation",new Point[]{new Point(526,142,1),new Point(526,204,1),
                new Point(526,221,2)});
        PointClouds[8]=new PointCloud("line",new Point[]{new Point(12,347,1),new Point(119,347,1)});
        PointClouds[9]=new PointCloud("five-point star",new Point[]{new Point(177,396,1),new Point(223,299,1),new Point(262,396,1),new Point(168,332,1),new Point(278,332,1),new Point(184,397,1)});
        PointClouds[10]=new PointCloud("null",new Point[]{new Point(382,310,1),new Point(377,308,1),new Point(373,307,1),new Point(366,307,1),new Point(360,310,1),new Point(356,313,1),new Point(353,316,1),new Point(349,321,1),new Point(347,326,1),new Point(344,331,1),new Point(342,337,1),new Point(341,343,1),new Point(341,350,1),new Point(341,358,1),new Point(342,362,1),new Point(344,366,1),new Point(347,370,1),new Point(351,374,1),new Point(356,379,1),new Point(361,382,1),new Point(368,385,1),new Point(374,387,1),new Point(381,387,1),new Point(390,387,1),new Point(397,385,1),new Point(404,382,1),new Point(408,378,1),new Point(412,373,1),new Point(416,367,1),new Point(418,361,1),new Point(419,353,1),new Point(418,346,1),new Point(417,341,1),new Point(416,336,1),new Point(413,331,1),new Point(410,326,1),new Point(404,320,1),new Point(400,317,1),new Point(393,313,1),new Point(392,312,1),
                new Point(418,309,2),new Point(337,390,2)});
        PointClouds[11]=new PointCloud("arrowhead",new Point[]{new Point(506,349,1),new Point(574,349,1),
                new Point(525,306,2),new Point(584,349,2),new Point(525,388,2)});
        PointClouds[12]=new PointCloud("pitchfork",new Point[]{new Point(38,470,1),new Point(36,476,1),new Point(36,482,1),new Point(37,489,1),new Point(39,496,1),new Point(42,500,1),new Point(46,503,1),new Point(50,507,1),new Point(56,509,1),new Point(63,509,1),new Point(70,508,1),new Point(75,506,1),new Point(79,503,1),new Point(82,499,1),new Point(85,493,1),new Point(87,487,1),new Point(88,480,1),new Point(88,474,1),new Point(87,468,1),
                new Point(62,464,2),new Point(62,571,2)});
        PointClouds[13]=new PointCloud("six-point star",new Point[]{new Point(177,554,1),new Point(223,476,1),new Point(268,554,1),new Point(183,554,1),
                new Point(177,490,2),new Point(223,568,2),new Point(268,490,2),new Point(183,490,2)});
        PointClouds[14]=new PointCloud("asterisk",new Point[]{new Point(325,499,1),new Point(417,557,1),
                new Point(417,499,2),new Point(325,557,2),
                new Point(371,486,3),new Point(371,571,3)});
        PointClouds[15]=new PointCloud("half-note",new Point[]{new Point(546,465,1),new Point(546,531,1),
                new Point(540,530,2),new Point(536,529,2),new Point(533,528,2),new Point(529,529,2),new Point(524,530,2),new Point(520,532,2),new Point(515,535,2),new Point(511,539,2),new Point(508,545,2),new Point(506,548,2),new Point(506,554,2),new Point(509,558,2),new Point(512,561,2),new Point(517,564,2),new Point(521,564,2),new Point(527,563,2),new Point(531,560,2),new Point(535,557,2),new Point(538,553,2),new Point(542,548,2),new Point(544,544,2),new Point(546,540,2),new Point(546,536,2)});
    }
    // Method to recognize the gesture drawn by user
    public Result Recognize(Point[] points){
        Instant before = Instant.now();
        PointCloud candidate = new PointCloud("",points);
        int u = -1;
        double b = Double.MAX_VALUE;
        for (int i = 0; i < this.PointClouds.length; i++){
            //Perform GreedyCloudMatch to get the calculated distance
            double d = GreedyCloudMatch(candidate.points, this.PointClouds[i]);
            // Find the best match
            if (d < b) {
                b = d; // best (least) distance
                u = i; // point-cloud index
            }
        }
        Instant after = Instant.now();
        long timedif = Duration.between(before, after).toMillis();
        // return the result and if noting matches return No match
        return (u == -1) ? new Result("No match.", 0.0, timedif) : new Result(this.PointClouds[u].Name, b > 1.0 ? 1.0 / b : 1.0, timedif);
    }
    // method to add gesture
    int AddGesture(String name, Point[] points){
        this.PointClouds[this.PointClouds.length] = new PointCloud(name, points);
        int num = 0;
        for (PointCloud pointCloud : this.PointClouds) {
            if (Objects.equals(pointCloud.Name, name))
                num++;
        }
        return num;
    }

    // Finding the minimum distance between the given points and PointCloud points
    public double GreedyCloudMatch(Point[] points, PointCloud P)
    {
        double e = 0.50;
        double step = Math.floor(Math.pow(points.length, 1.0 - e));
        var min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i += step) {
            double d1 = CloudDistance(points, P.points, i);
            double d2 = CloudDistance(P.points, points, i);
            min = Math.min(min, Math.min(d1, d2)); // min3
        }
        return min;
    }
    // Calculate the Cloud Distance between points and given start index
    public double CloudDistance(Point[] pts1,Point[] pts2, int start)
    {
        // pts1.length == pts2.length
        boolean[] matched = new boolean[pts1.length];
        for (int k = 0; k < pts1.length; k++)
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
                    double d = Distance(pts1[i], pts2[j]);
                    if (d < min) {
                        min = d;
                        index = j;
                    }
                }
            }
            matched[index] = true;
            double weight = 1 - ((((i - start) + pts1.length) % pts1.length) / pts1.length);
            sum += weight * min;
            i = (i + 1) % pts1.length;
        } while (i != start);
        return sum;
    }
    //method to calculate the distance between two points
    public double Distance(Point p1, Point p2) // Euclidean distance between two points
    {
        var dx = p2.x - p1.x;
        var dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
