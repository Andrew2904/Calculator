package util;

import gui.list.Count;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class XMLParser {
    public static List<Count> read(String file){
        List<Count> data = null;

        /*
        Count entry, base;
        String desc;

        base = new Count("", 100, 0);

        for(int i=0;i<30;i++){
            if(i%4==0)
                base.setDesc("Fapta "+i);
            else
            if(i%4==1)
                base.setDesc("Furt "+i);
            else
            if(i%4==2)
                base.setDesc("Santaj "+i);
            else
            if(i%4==3)
                base.setDesc("Mita "+i);

            entry = new Count(base);
            data.add(entry);
        }
        */
        Parser parser = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();

            //System.out.println(new File("demo.xml").getAbsolutePath());
            saxParser.parse(new File("demo.xml").getAbsolutePath(), handler);

            data = handler.getList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return data;
    }
}
