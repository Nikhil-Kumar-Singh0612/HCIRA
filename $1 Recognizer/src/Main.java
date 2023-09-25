/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/

// importing the necessary packages
import OneDollarRecognizer.*;
import OneDollarRecognizer.OneDollarRecognizer;
import com.opencsv.CSVWriter;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

// It is the Main Class
public class Main {
    // Main Constructor for the creation of log files
    public Main() {
        String output = System.getProperty("user.dir");
        output += "/Gesture_log_file.csv";
        DecimalFormat df = new DecimalFormat("#.##");
        File file = new File(output);
        try {
            FileWriter outputfile = new FileWriter(file);
            // for writing log files
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<>();
            data.add(new String[] {"Recognition Log: Nikhil Kumar Singh and Amisha Singhal // $1 algorithm // medium dataset for all users in xml_logs // USER-DEPENDENT RANDOM 10"," ", " "," ", " "," "," "," "," "," "," "," "});
            data.add(new String[] {"User","GestureType","IterationNumber","#TrainingExamples","TrainingSetSize","TrainingSetContents","Candidate","RecoResultGestureType","Correct/Incorrect","RecoResultScore","RecoResultBestMatch","RecoResultNBestSorted"});
            XMLParsingData x = new XMLParsingData();
            ArrayList<GestureStructure> gestureList = x.fullGestureList();
            double correct = 0;
            double total = 0;

            // Users loop
            for (GestureStructure users : gestureList) {
                ArrayList<GesStructure> ut = users.Template;
                ArrayList<List<GesStructure>> gestTemplate = new ArrayList<>();
                for (int index = 0; index < 16; index++) {
                    List<GesStructure> l = ut.subList(index * 10, (index * 10) + 10);
                    gestTemplate.add(l);
                }
                double uc = 0;
                double utotal = 0;
                System.out.println("Size of template for user : " + ut.size());
                Random random = new Random();
                // E loop
                for (int E = 1; E <= 9; E++) {
                    // Iterations loop
                    for (int iter = 1; iter <= 10; iter++) {
                        ArrayList<GesStructure> train = new ArrayList<>();
                        ArrayList<GesStructure> test = new ArrayList<>();
                        String currentuser = users.getUser();

                        for (List<GesStructure> t : gestTemplate) {
                            Set<Integer> selectedNumbers = new HashSet<>();
                            // Create a set to store remaining numbers
                            Set<Integer> remainingNumbers = new HashSet<>();
                            for (int i = 0; i < 10; i++) {
                                remainingNumbers.add(i);
                            }
                            while (selectedNumbers.size() < E) {  // Select E random numbers
                                int randomNum = random.nextInt(10);  // Generate random number between 0 and 9
                                if (!selectedNumbers.contains(randomNum)) {  // Check if number has already been selected
                                    selectedNumbers.add(randomNum);  // Add number to selectedNumbers set
                                    remainingNumbers.remove(randomNum);  // Remove number from remainingNumbers set
                                }
                            }

                            for (Integer selected : selectedNumbers) {
                                int ci = selected;
                                GesStructure gi = t.get(ci);
                                //System.out.println("selected training full name : " + gi.fullName);
                                train.add(gi);

                            }

                            Object[] numbersArray = remainingNumbers.toArray();
                            int randomIndex = random.nextInt(remainingNumbers.size());
                            int randomNum = (int) numbersArray[randomIndex];

                            GesStructure gs = t.get(randomNum);
                            //System.out.println("selected for testing full Name : " + gs.fullName);
                            test.add(gs);
                        }

                        OneDollarRecognizer o = new OneDollarRecognizer();

                        for (GesStructure tes : test) {
                            ArrayList<String> inputString = new ArrayList<>();
                            inputString.add(users.user);
                            inputString.add(tes.label);
                            inputString.add(String.valueOf(iter));
                            inputString.add(String.valueOf(E));
                            inputString.add(String.valueOf(E*16));
                            String trainString="{";
                            for(GesStructure gi: train){
                                trainString += currentuser+"-"+gi.label+"-"+gi.InstanceNumber+"|";
                            }
                            trainString=trainString.substring(0,trainString.length()-1) + "}";
                            inputString.add(trainString);
                            String currentcandidate = users.getUser()+"-"+tes.label+"-"+tes.InstanceNumber;
                            inputString.add(currentcandidate);
                            List<Result> results = o.Recognize(tes.points, train, true, users.user);
                            String NBestList="{";
                            for( Result r: results){
                                //System.out.println("Label : " + r.getName() + " User : " + r.getUser() + " Score : " + r.getScore() + " instance : " + r.getInstance());
                                NBestList+=r.getUser()+"-"+r.getName()+"-"+r.getInstance()+"-"+r.getScore()+"|";
                            }
                            NBestList = NBestList.substring(0,NBestList.length()-1) + "}";
                            Result bestresult = o.bestResult;
                            inputString.add(bestresult.getName());
                            total++;
                            utotal++;
                            //comparing the result of recognize method with the test label
                            if (bestresult.getName().trim().equalsIgnoreCase(tes.label.trim())) {
                                correct++;
                                uc++;
                                inputString.add("1");
                                //System.out.println("Result label : " + bestresult.getName() + "---- Match Label : " + tes.label);
                            } else {
                                inputString.add("0");
                                //System.out.println("Result label : " + bestresult.getName() + "---- Match Label : " + tes.label);
                            }
                            inputString.add(String.valueOf(bestresult.getScore()));
                            String bestRes = bestresult.getUser()+"-"+bestresult.getName()+"-"+bestresult.getInstance();
                            inputString.add(bestRes);
                            inputString.add(NBestList);
                            String[] in= inputString.toArray(new String[0]);
                            //data.add((String[]) inputString.toArray());
                            data.add(in);
                        }

                    }
                }
                // printing per user accuracy
                System.out.println(" Per User Accuracy : " + df.format(uc / utotal * 100));
            }
                // printing average user accuracy
            System.out.println(" Avg User Accuracy : " + df.format(correct/total * 100));

            data.add(new String[]{"TotalAvgAccuracy",String.valueOf(df.format(correct/total))," "," "," "," "," "," "," "," "," "," "});
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
