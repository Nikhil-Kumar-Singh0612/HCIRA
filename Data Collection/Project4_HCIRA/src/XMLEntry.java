/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;

public class XMLEntry {

    public void writeXML(String template, int count, ArrayList<Point> canvasPoints, String pathname, int userno) {
        try { //using document builder to create the docs.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            //adding an element gesture to set the attributes: gesture type, subject name, and instance number.
            Element gesture = doc.createElement("Gesture");
            gesture.setAttribute("Name", template+String.valueOf(count));
            gesture.setAttribute("Subject", String.valueOf(userno));
            gesture.setAttribute("number", String.valueOf(count));

            doc.appendChild(gesture);
            // printing the points to the XML file.
            for (Point point : canvasPoints) {
                //creating element point to add the points to the file.
                Element pointElement = doc.createElement("Point");
                pointElement.setAttribute("X", String.valueOf(point.x));
                pointElement.setAttribute("Y", String.valueOf(point.y));
                gesture.appendChild(pointElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            System.out.println(pathname); //printing the pathname just to ensure that the files are created.
            File file = new File(pathname+template+count+".xml"); // creating a new xml file with the gesture type and count name
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName()); // print statements to ensure the file creation
                } else {
                    System.out.println("File already exists."); // if the file is already present.
                }
            } catch (IOException e) {
               // System.out.println("An error occurred.");
                e.printStackTrace();
            }

            StreamResult consoleResult = new StreamResult(file); //storing the data in the new file created.
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
