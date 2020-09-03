package util;

import data.entity.Felony;
import data.handler.FelonyHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Date;
import java.util.List;

public class XMLParser {
    List data;
    String last;

    public XMLParser(){
        data = null;
        //last = Felony.format(new Date());
        last = ResourceManager.format( new Date() );
    }

    public void read(String file){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            FelonyHandler felonyHandler = new FelonyHandler();

            saxParser.parse(new File(file).getAbsolutePath(), felonyHandler);

            data = felonyHandler.getList();
            last = felonyHandler.getLast();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Felony> getData() {
        return data;
    }

    public String getLast(){
        return last;
    }
}