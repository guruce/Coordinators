package Utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pirinthapan
 * Date: 11/27/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class XmlLoader {

    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    SAXParser saxParser;

    public XmlLoader() {

        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        DefaultHandler handler = new DefaultHandler(){

            boolean isActivationAddress = false;
            boolean isRegistrationAddress = false;
            boolean isCompletionAddress = false;

            public void startElement(String uri, String localName,String qName,
                                     Attributes attributes){

                if (qName.equalsIgnoreCase("activationaddress"))
                    isActivationAddress = true;

                if (qName.equalsIgnoreCase("registrationaddress"))
                    isRegistrationAddress = true;

                if (qName.equalsIgnoreCase("completionaddress"))
                    isCompletionAddress = true;

            }


            public void endElement(String uri, String localName,
                                   String qName){

            }


            public void characters(char ch[], int start, int length){

                if(isActivationAddress) {
                    Constants.activationServiceAddress = new String(ch, start, length);
                    System.out.println("Activation address : " + Constants.activationServiceAddress);
                    isActivationAddress = false;
                }

                if(isRegistrationAddress) {
                    Constants.registrationServiceAddress = new String(ch, start, length);
                    System.out.println("Registration address : " + Constants.registrationServiceAddress);
                    isRegistrationAddress = false;
                }

                if(isCompletionAddress) {
                    Constants.completionServiceAddress = new String(ch, start, length);
                    System.out.println("Completion address : " + Constants.completionServiceAddress);
                    isCompletionAddress = false;
                }

            }

        };

        try {

            saxParser.parse(System.getProperty("user.dir") + "/Constants", handler);

        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
