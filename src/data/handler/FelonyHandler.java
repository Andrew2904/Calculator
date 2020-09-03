package data.handler;

import data.entity.Felony;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class FelonyHandler extends DefaultHandler {
    boolean bDesc, bMinJail, bMaxJail, bMinFine, bMaxFine, bLast;
    List<Felony> list;
    Felony current;
    String last;

    public FelonyHandler(){
        bDesc = false;
        bMinJail = false;
        bMaxJail = false;
        bMinFine = false;
        bMaxFine = false;
        bLast = false;
        list = new ArrayList<>();
    }

    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()){
            case "fapte":
                System.out.println("Fisier de la data de: "+attributes.getValue("data"));
                last = attributes.getValue("data");
                break;
            case "fapta":
                current = new Felony();
                break;
            case "denumire":
                bDesc = true;
                break;
            case "mininchisoare":
                bMinJail = true;
                break;
            case "maxinchisoare":
                bMaxJail = true;
                break;
            case "minamenda":
                bMinFine = true;
                break;
            case "maxamenda":
                bMaxFine = true;
                break;
            case "editare":
                bLast = true;
                break;
            default:
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()){
            case "fapta":
                list.add(current);
                break;
            case "denumire":
                bDesc = false;
                break;
            case "mininchisoare":
                bMinJail = false;
                break;
            case "maxinchisoare":
                bMaxJail = false;
                break;
            case "minamenda":
                bMinFine = false;
                break;
            case "maxamenda":
                bMaxFine = false;
                break;
            case "editare":
                bLast = false;
                break;
            default:
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        if(bDesc)
            current.setDesc(value);
        if(bMinJail)
            current.setMinJail( parseInt(value) );
        if(bMaxJail)
            current.setMaxJail( parseInt(value) );
        if(bMinFine)
            current.setMinFine( parseInt(value) );
        if(bMaxFine)
            current.setMaxFine( parseInt(value) );
        if(bLast)
            current.setDate(value);
    }

    public List<Felony> getList(){
        return list;
    }

    private int parseInt(String src){
        int res = 0;
        try{
            res = Integer.parseInt(src);
        }catch (Exception ex){
            System.err.println(src+" nu este numar");
        }finally {
            return res;
        }
    }

    private float parseFloat(String src){
        float res = 0;
        try{
            res = Float.parseFloat(src);
        }catch (Exception ex){
            System.err.println(src+" nu este numar");
        }finally {
            return res;
        }
    }

    public String getLast(){
        return last;
    }
}
