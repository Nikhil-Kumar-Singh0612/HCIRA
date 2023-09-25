/*Authors: Amisha Singhal(1722 3950) and Nikhil Kumar Singh(4050 0443)*/
package OneDollarRecognizer;
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
    // function to make the GesStructure from the file input
    public ArrayList<GesStructure> makeArraylist(String currentDirectory, String user, String[] gesture,
                                                 String[] gesturesTypes) throws ParserConfigurationException, SAXException, IOException {
        ArrayList<GesStructure> tempTemplate = new ArrayList<>();
        //currentDirectory += "/xml_logs/" + user + "/medium/";
        currentDirectory+="/data_set/" + user +"/";
        String temp = currentDirectory;
        for (String g : gesture) {
            for (String gt : gesturesTypes) {
                temp += g + gt + ".xml";
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
        //String[] users = { "s02", "s03", "s04", "s05", "s06", "s07", "s08", "s09", "s10", "s11" };
        String[] users = {"user1", "user2", "user3", "user4", "user5", "user6"};
        String[] gesture = { "arrow", "caret", "check", "circle", "delete", "left-curly-brace", "left-square-bracket",
                "pigtail", "rectangle", "right-curly-brace", "right-square-bracket","star", "triangle", "v", "x", "zig-zag" };
        String[] gesturesTypes = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };

        ArrayList<GesStructure> gesForUser;
        ArrayList<GestureStructure> g = new ArrayList<>();
        for (String u : users) {
            user = u;
            gesForUser = makeArraylist(currentDirectory, user, gesture, gesturesTypes);
            g.add(new GestureStructure(user, gesForUser));
        }
        for (GestureStructure ges : g) {
            System.out.println("User: " + ges.user + " Template Size: " + ges.Template.size());
        }
        return g;
    }
    // loading the templates and storing in form GesStructure
    public GesStructure loadTemplates(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        Document doc = xmlparser(filename);
        Element root = doc.getDocumentElement();
        String l = root.getAttribute("Name");
        GesStructure g = new GesStructure();
        g.fullName = l;
        String lab = l.substring(0, l.length() - 2);
        g.label = lab;

        String ins = l.substring(lab.length());
        int insno = Integer.parseInt(ins);
        // Get a list of all the elements with the tag name "log"
        NodeList nodeList = doc.getElementsByTagName("Point");
        ArrayList<PointClass> p = new ArrayList<>();
        // Loop through the list and print the content of each element
        for (int temp = 0; temp < nodeList.getLength(); temp++) {

            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String s1 = element.getAttribute("X");
                String s2 = element.getAttribute("Y");
                //p.add(new PointClass(Integer.parseInt(s1),Integer.parseInt(s2)));
                p.add(new PointClass(Double.parseDouble(s1), Double.parseDouble(s2)));
            }
        }
        g.points=p;
        g.InstanceNumber=insno;
        return g;
    }
    // function which returns the document
    public Document xmlparser(String filepath) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filepath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        // Normalize the document
        doc.getDocumentElement().normalize();
        return doc;
    }


}
