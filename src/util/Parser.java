package util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;

public class Parser {
    DocumentBuilder builder;

    public Parser() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    public Document parse(String data){
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream( data.getBytes("UTF-8"));
            Document doc = builder.parse(input);

            return doc;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
