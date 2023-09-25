//Authors: Nikhil Kumar Singh and Amisha Singhal. This code belongs to group 15 only.
//Importing the necessary libraries

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import com.opencsv.CSVWriter;

//Main Class
public class Main {
    // Main Constructor for the creation of log files
    public Main() {
        String output = System.getProperty("user.dir");
        output += "/Gesture_log_file.csv";  //setting up the name of the gesture log file
        DecimalFormat df = new DecimalFormat("#.####"); // to format the accuracy results in 4 decimals.
        File file = new File(output); //creating a new file
        try { //to catch the exceptions led by the Xml parsers
            FileWriter outputfile = new FileWriter(file);
            // for writing log files
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<>();
            // adding the header of the log file.
            data.add(new String[] {"Recognition Log: Nikhil Kumar Singh and Amisha Singhal // $P algorithm // medium dataset for all users in MMG dataset // USER-DEPENDENT RANDOM 10"," ", " "," ", " "," "," "," "," "," "," "," "});
            data.add(new String[] {"User","GestureType","IterationNumber","#TrainingExamples","TrainingSetSize","TrainingSetContents","Candidate","RecoResultGestureType","Correct/Incorrect","RecoResultScore","RecoResultBestMatch","RecoResultNBestSorted"});
            XMLParsingData x = new XMLParsingData();  // creating object of the xml parser to be used.
            ArrayList<GestureStructure> gestureList = x.fullGestureList();
            double correct = 0;
            double total = 0;

            // looping over all the users.
            for (GestureStructure users : gestureList) {
                ArrayList<PointCloud> ut = users.Template; // creating a template of pointcloud type to store the gesture list
                ArrayList<List<PointCloud>> gestTemplate = new ArrayList<>();
                for (int index = 0; index < 16; index++) { // to loop over all the 16 gestures of every user
                    List<PointCloud> l = ut.subList(index * 10, (index * 10) + 10); // list of list to store the points of every gesture
                    gestTemplate.add(l);
                }
                double uc = 0;
                double utotal = 0;
                //System.out.println("Size of template for user : " + ut.size());

                Random random = new Random();
                // E loop
                for (int E = 1; E <= 9; E++) {
                    double ecount=0;
                    double ecorrect=0;
                    // Iterations loop
                    for (int iter = 1; iter <= 10; iter++) {
                        ArrayList<PointCloud> train = new ArrayList<>();  // to store the training set data
                        ArrayList<PointCloud> test = new ArrayList<>();    //to store the test set data
                        String currentuser = users.getUser(); //fetching the user from class GestureStructure
                        // looping over all the templates
                        for (List<PointCloud> t : gestTemplate) {
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
                                PointCloud gi = t.get(ci);
                                //System.out.println("selected training full name : " + gi.fullName);
                                train.add(gi);

                            }

                            Object[] numbersArray = remainingNumbers.toArray();
                            int randomIndex = random.nextInt(remainingNumbers.size());  //picking up a random index from the remaining numbers.
                            int randomNum = (int) numbersArray[randomIndex];

                            PointCloud gs = t.get(randomNum);
                            //System.out.println("selected for testing full Name : " + gs.Name + " - " + gs.InstanceNumber);
                            test.add(gs);  //adding the test set to test list
                        }

                        //making an object of recognizer  class
                          Recognizer o = new Recognizer();
                            // looping over the formed test set
                        for (PointCloud tes : test) {
                            ArrayList<String> inputString = new ArrayList<>();  // to store the copy of each test list
                            inputString.add(users.user);  // adding the username to the string
                            inputString.add(tes.Name);    // adding th gesture name to the string
                            inputString.add(String.valueOf(iter));
                            inputString.add(String.valueOf(E));
                            inputString.add(String.valueOf(E*16));
                            String trainString="{";
                            // looping over the training set
                            for(PointCloud gi: train){
                                trainString += currentuser+"-"+gi.Name+"-"+gi.InstanceNumber+"|";
                            }
                            trainString=trainString.substring(0,trainString.length()-1) + "}";
                            inputString.add(trainString);
                            // fetching the name and number of the current candidate
                            String currentcandidate = users.getUser()+"-"+tes.Name+"-"+tes.InstanceNumber;
                            inputString.add(currentcandidate);
                            // running the recognizer and fetching  their results score
                            List<Result> results = o.RecognizeWithNBestList(tes.points, train, users.user);
                            String NBestList="{";
                            //creating the nbest list here
                            for( Result r: results){
                                //System.out.println("Label : " + r.getName() + " User : " + r.getUser() + " Score : " + r.getScore() + " instance : " + r.getInstance());
                                NBestList+=r.getUser()+"-"+r.getName()+"-"+r.getInstance()+"-"+r.getScore()+"|";
                            }
                            NBestList = NBestList.substring(0,NBestList.length()-1) + "}";
                            Result bestresult = o.bestResult;  // adding the n best list
                            inputString.add(bestresult.getName());
                            total++;
                            utotal++;
                            //comparing the result of recognize method with the test label
                            if (bestresult.getName().trim().equalsIgnoreCase(tes.Name.trim())) {
                                correct++;
                                uc++;
                                ecount++;
                                ecorrect++;
                                inputString.add("1");
                                //System.out.println("Result label : " + bestresult.getName() + "---- Match Label : " + tes.Name);
                            } else {
                                ecount++;
                                inputString.add("0");
                                //System.out.println("Result label : " + bestresult.getName() + "---- Match Label : " + tes.Name);
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
                    System.out.println("Accuracy for "+ E + " template: " + (ecorrect/ecount) *100);
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
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}

