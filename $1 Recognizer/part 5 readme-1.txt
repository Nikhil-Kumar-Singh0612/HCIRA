Authors: Nikhil Kumar Singh(4050-0443) and Amisha Singhal(1722-3950)

Readme:-


a. Run an Offline Recognition test: 

1. The data collected from 6 users in previous segment of project is being used here  as our input dataset. Each user folder consists of 10 samples of all the 16 gestures considered for this project.
2. Then, we performed offline recognizer functionalities on the dataset similar to section 3 of this project.
3. We modified the source code of part 3 to include all the functionalities required for successful implementation of this part.
4. The file structure was changes from previous part as now we simply use a folder named data_set containing 6 users folder which contains 16 X 10 gesture templates. 


b. Output the Result: 
 
1. We then run the code which generates a log file with the data mentioned in the project handout and also included the total average accuracy across all recognition calls at the end of your file.
2. We generated it for 10 iterations.
3. There are no much modifications in the structure of the log file.

c. Analyze the dataset using GHOST: 
1. The data visualization is performed by using GHOST toolkit.
2. Imported the whole dataset which had 60 samples of each gesture.
3. First, we selected the parameters such as align points chronologically and shape error for generating heatmaps.
4. Then, we computed heatmap in .bmp format and feature dataset in .csv format. The images are attached for reference. the dataset was visualized using the ghost toolkit as per the guidelines.
   The image and the feature dataset has been exported and attached.


d. User articulation Insights:

1. After successful implementation of previous segment in the project, from the generated heatmap and feature dataset we can make many insights about the data.
2. By visualisation of the dataset created in part 4. Most of the users were inconsistente in creating the "star" and "zig-zag" as visibly they have orange and reddish color which represents higher magnitudes of inconsistency when gesture was being drawn,
   while cold colors such as blue and greens are seen in gestures "lest-square-bracket" and "right-square_bracket", which represent lesser magnitude or we can infer that these were easy to draw and users did these gestures with more consistency. 
3. Gestures such as left and right curly braces were difficult to draw and users took realtively more time for inputing these gestures.
4. Circle was the most consistent gesture among all. Arrow, star and zig-zag were the ones which have combination of more than two colors.  
5. The accuracy for this created dataset is expected to be realtively less due to the varying users and their way of drawing each gesture. 

