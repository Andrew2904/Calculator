package util;

import gui.list.Count;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Date;
import java.util.List;

public class XMLParser {
    List<Count> data;
    String last;

    public XMLParser(){
        data = null;
        last = Count.format(new Date());
    }

    public void read(String file){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();

            saxParser.parse(new File(file).getAbsolutePath(), handler);

            data = handler.getList();
            last = handler.getLast();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Count> getData() {
        return data;
    }

    public String getLast(){
        return last;
    }
}