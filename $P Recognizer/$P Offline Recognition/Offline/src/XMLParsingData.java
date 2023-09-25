/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/

// importing necessary libraries
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


// class for parsing XML files
public class XMLParsingData {
    // function to make the PointCloud from the file input
    public ArrayList<PointCloud> makeArraylist(String currentDirectory, String user, String[] gesture,
                                                 String[] gesturesTypes) throws ParserConfigurationException, SAXException, IOException {
        ArrayList<PointCloud> tempTemplate = new ArrayList<>();
        currentDirectory+="/data/" + user +"/";
        String temp = currentDirectory;

        for (String g : gesture) {
            for (String gt : gesturesTypes) {
                temp += g + "-" +gt + ".xml";
                tempTemplate.add(loadTemplates(temp));
                temp = currentDirectory;
            }
        }

        return tempTemplate;
    }
    // function for fetching all the gesture data
    public ArrayList<GestureStructure> fullGestureList() throws ParserConfigurationException, IOException, SAXException {
        String currentDirectory = System.getProperty("user.dir");
        String user;
        // user names
        String[] users = {"user01", "user02", "user03", "user04", "user05", "user06", "user07", "user08", "user09", "user10", "user11", "user12", "user13", "user14", "user15", "user16", "user17", "user18", "user19", "user20"};
        // gesture names
        String[] gesture = { "arrowhead", "asterisk", "D", "exclamation_point", "five_point_star", "H", "half_note", "I", "line", "N", "null","P", "pitchfork", "six_point_star", "T", "X" };
        // this is the instance number for each gesture.
        String[] gesturesTypes = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };

        ArrayList<PointCloud> gesForUser;
        ArrayList<GestureStructure> g = new ArrayList<>();
        for (String u : users) {
            user = u;
            gesForUser = makeArraylist(currentDirectory, user, gesture, gesturesTypes);
            g.add(new GestureStructure(user, gesForUser));
        }
        return g;
    }
    // loading the templates and storing in form Pointcloud
    public PointCloud loadTemplates(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        Document doc = xmlparser(filename);
        Element root = doc.getDocumentElement();
        String l = root.getAttribute("Name");
//        GesStructure g = new GesStructure();
//        g.fullName = l;
        //PointCloud g = new PointCloud();
        String lab = l.substring(0, l.length() - 3);
        //g.label = lab;
//        g.Name = lab;

        String ins = l.substring(lab.length() + 1);
        int insno = Integer.parseInt(ins);
        // Get a list of all the elements with the tag name "log"
        NodeList nodeList = doc.getElementsByTagName("Point");
        ArrayList<Point> p = new ArrayList<>();
        // Loop through the list and print the content of each element
        for (int temp = 0; temp < nodeList.getLength(); temp++) {

            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String s1 = element.getAttribute("X"); // fetch the x coordinate
                String s2 = element.getAttribute("Y");  // fetch the y coordinate
                int nos = element.getAttribute("index").length();
                p.add(new Point(Double.parseDouble(s1), Double.parseDouble(s2), nos)); // add the point to the list p.
                //System.out.println("X: " + Double.parseDouble(s1) + "-- Y: " + Double.parseDouble(s2) );
            }
        }
//        g.points=p;

        PointCloud g = new PointCloud(lab,p);
        g.InstanceNumber=insno;
//        System.out.println(" Num of points : " + g.points.size());
        return g;
    }
    // function which returns the document
    public Document xmlparser(String filepath) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filepath);
        // making the instance of xml parser to create the document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        // Normalize the document
        doc.getDocumentElement().normalize();
        return doc;
    }

}
