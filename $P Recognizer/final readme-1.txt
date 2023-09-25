Readme: Project 2 ($P Algorithm) 
Group 15: Amisha Singhal and Nikhil Kumar Singh


Part 2: Online Recognizer


In the $1 online recognizer, it recognized the gesture drawn by the user as soon as the mouse was released since all the gestures were unistrokes, but in the $P online recognizer, we need to record the number of strokes and recognize only when the user right clicks on the canvas, so there have been changes described as follows:


Inside Main Class

Main.java:


Lines 16-26: We made two new ArrayLists to store different strokes, and we also added a stroke counting feature. We modified the mouse release event to ensure the user can verify their drawing before calling the recognizer. If the user is unsure, they can use the clear button and draw the gesture again.


Lines 31-54: The $P algorithm is designed to work with multi-stroke gestures, which means the user needs to draw multiple strokes for each gesture. We added lines of code that allow the user to draw as many strokes as they want until they right-click on the screen, indicating that they have completed drawing the gesture.


Lines 74-106: Upon releasing the mouse, the points of the stroke are immediately stored in the ArrayList 'prevlines'. When the user right-clicks, the size of the ArrayList is checked to determine the number of points that were fetched. If the number of points is less than 10, a message is displayed indicating that too few points were drawn. Otherwise, the 'recognize' function is called on the fetched points, and the results are stored.


Inside PointCloud Class


PointCloud.java:


This class utilizes a helper class that takes in the points and applies various preprocessing techniques such as resampling, scaling, and translation to form a point cloud.
By representing gestures as point-clouds, $P can handle both unistrokes and multistrokes efficiently and without the combinatorial overhead of $N. The stroke number, order, and direction are all ignored, enabling a more flexible recognition approach. When comparing two point-clouds, $P solves the assignment problem between two bipartite graphs using an approximation of the Hungarian algorithm.
This class is used to create the pointclouds of various gestures. 




Inside Helper Class


Helper.java: 


This class includes functions for various preprocessing techniques such as resampling, scaling, and translating the points to form a point cloud. Additionally, it includes auxiliary functions required for calculations, such as 'PathLength' for determining the length traversed by the point path, 'Centroid' for obtaining the centroid after adding new points to the point cloud, and 'Distance' for calculating the distance between two points.


Inside Result Class


Result.java: 


A custom class was created to retrieve the scores and name of the recognized gesture. This class likely includes variables to store the name of the recognized gesture and the corresponding scores, as well as getter functions to retrieve these values. The scores could potentially be represented as a list or arrayList of values, with each value indicating the confidence level of the recognizer for that particular gesture. 


Inside Recognizer Class


Recognize.java:


Lines 11-46: In this section of the code, we loaded templates for all 16 gestures and created a point cloud for each of them.


Lines 47-62: The 'recognize' function is the main functionality of the $P algorithm, and it returns a result type. This function utilizes the 'GreedyCloudMatch' function, which is called on multiple point clouds and the points of the fetched gesture. The 'GreedyCloudMatch' function likely compares the gesture points to each of the loaded point clouds and returns the closest match based on a similarity measure. The 'recognize' function then returns the name of the recognized gesture along with its corresponding scores, as determined by the 'GreedyCloudMatch' function.


Lines 74-85: This is the GreedyCloudMatch function that matches the fetched data points and compares them with all the Pointclouds by calculating the Euclidean distance between the point clouds and storing the results.


Part 3: Offline recognizer


Inside Main Class


Main.java: 


Lines 17-36: The code uses the OpenCSV library to create a log file. The header of the log file is set to include the column names for the data that will be logged.


Lines 50 - 91 :This is the main loop of the program. The first loop runs from E 1 to 9. The second loop is for the number of iterations, up to 100, and within each iteration, the training and testing sets are randomly selected. A random number is generated, and the respective template is selected for training or testing purposes.


Lines 95- 147: This segment of the code trains and tests the templates and stores the results. The program also keeps track of the best results to form the N-best list. The N-best list is a ranked list of the top N gesture recognitions based on their scores. The program likely calculates the scores for each recognition and compares them to the current top N recognitions, updating the list if a new recognition exceeds the score of one of the existing top N recognitions. The results are also likely to be logged to the output file for future reference and analysis.


Inside PointCloud Class


PointCloud.java:


This class utilizes a helper class that takes in the points and applies various preprocessing techniques such as resampling, scaling, and translation to form a point cloud.


Inside XMLParsingData Class


XMLParsingData.java:


This class makes use of the SAX parser to parse through the dataset.


Lines 38- 56: In this updated version, there are 20 users, and the gestures are different. An ArrayList of 'PointCloud' type is created to store the gesture templates. 


Lines 58-96: The load templates function takes a filename as input and fetches the data from it. The function then stores the x and y coordinates in an ArrayList. Afterward, the 'PointCloud' function is called to form the point cloud of those points. This process is likely to be repeated for each of the gesture templates in the file.


Note : The other classes are the same as the online recognizer.


Part 5: Exploring the Dataset


1. The GHOST toolkit was used to generate the heatmap of the MMG dataset provided in the $P paper. 
2. The data visualization is performed using the GHOST toolkit.
3. Import the whole dataset, which had 200 samples of each gesture.
4. First, we selected the parameters, such as perform point-cloud alignment (for multistroke gestures) and shape error, for generating heatmaps.
5. Then, we computed a heatmap in .bmp format and a feature dataset in .csv format. The images are attached for reference. The dataset was visualized using the ghost toolkit as per the guidelines.


Insights from the heatmap:


1. Out of all the gestures, drawing a line was the simplest and required the least effort.
2. The Half note, D, and T gestures were relatively consistent, although there were some minor discrepancies. 
3. However, the most inconsistent gestures were the Five point star, I, Null, and arrowhead. 
4. It appeared that the number of strokes used to draw the gestures had an impact on the inconsistencies observed.

Citations:


1. Gestures as point clouds: a $P recognizer for user interface prototypes (washington.edu)
2. What is SAX in XML? - GeeksforGeeks
3. opencsv – (sourceforge.net)
4. https://depts.washington.edu/acelab/proj/dollar/pdollar.js