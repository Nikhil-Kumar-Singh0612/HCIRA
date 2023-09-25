Authors: Nikhil Kumar Singh (4050 0443) and Amisha Singhal(1722 3950)
Readme file:

Part A: Write Gesture Files

We created the dataset by taking the input gestures and storing it in XML files. We made use of  many XML libraries for achieving this purpose. 
Start by importing the necessary libraries for writing data into XML file. Then, we started by created a folder associated with the user and then 
in that folder, we stored the 16*10 gestures in 160 xml files. 
we have made a class named "XMLEntry.java". 

In XMLEntry class:

Lines 1-10 : Importing the libraries.

Lines 12-51 : The class consists of only function called writeXML with below signature.

public void writeXML(String template, int count, ArrayList<Point> canvasPoints, String pathname, int userno) 

It takes the template/gesture name, instance count, points for the gesture and folder path where it is stored as input.
The code is enclosed in try-catch block. We start by created new Document Builder and eventually creating a document. 
lines 25-30: Then creating an element name gesture using createElement method of document.Setting the attributes such as Name, Subject and Number and then appending the child. 
lines: 32-38: Then storing the points in the canvasPoints into the file under Point tag with its X and Y coordinates as X and Y attributes and appending the child to the document.
For writing into the xml file we used TransformerFactory. We created an instance of it and created transformer object. The source is of type DOMSource. 
lines 45-55: Then create the XML file if it does not exist already with the folder path. 
line 57-...: And, all the data is stored into the file by StreamResult insatnce named consoleResult which is the new StreamResult for the newly created file and finally we use transform function with source and consoleResult to achieve our purpose of storing the data into XML file.

Part B: Prompt for Specific Samples

We modified our previous canvas code and created a button named save which is used for storing the points and calling the writeXML method when it is clicked.
Also, created two labels namely gestureName for showing what gesture to draw next and sampleNumber for indicating the number of instance for the gesture being drawn. 
And we already have clear button for clearing the canvas in case user needs to redraw the gesture.

In Main Class 

Lines 1-11 : Importing the libraries

Lines 36-63: Initializing the necessary variables and created new JLabels and save button. We have savecount, clickcount, foldercount and gesturecount variables for tracking the users and gestures.

Lines 65-71: savefile method taking shapename and count as input and calling writeXML method with necessary input.

Lines 96-128: created save button and added Action listener to it. First, make start and end null call savefile method and track all the counter variables. if savecount> 10 then increment gesturecount
and if gesturecount>=16 reinitialize gesturecount=0 and increment savecount. If clickcount reaches 160, it indicates that the user successfully registered all the gestures and we move to next user and if clickcount==960 we are done with collecting data.

Lines 142-156: Adding the labels and save button to the container.

Lines 200-214: Creating seperate folders for the users using mkdirs method.

Part C: Recruit 6 People

We asked 6 people for drawing gestures and collected the data along with the signed consent form which will be attached for reference.
We used user1, user2, ... user 6 to represent first user and so on and maintain annonymity of the users.

Part D: Submit Full Dataset

All the data will be stored in folders associated with the user. Thus, we have six folders for six users and each folder have 160 gesture data( 10 instances for each gesture)
in xml files which will be used for next segment of project. 

