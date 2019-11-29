package util;

import gui.list.Count;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    boolean bDesc, bJail, bFine, bLast;
    List<Count> list;
    Count current;

    public Handler(){
        bDesc = false;
        bJail = false;
        bFine = false;
        bLast = false;
        list = new ArrayList<>();
    }

    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()){
            case "fapte":
                System.out.println("Fisier de la data de: "+attributes.getValue("data"));
                break;
            case "fapta":
                current = new Count();
                break;
            case "denumire":
                bDesc = true;
                break;
            case "inchisoare":
                bJail = true;
                break;
            case "amenda":
                bFine = true;
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
            case "inchisoare":
                bJail = false;
                break;
            case "amenda":
                bFine = false;
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
        if(bJail)
            current.setJail(parseInt(value));
        if(bFine)
            current.setFine(parseFloat(value));
        if(bLast)
            current.setDate(value);
    }

    public List<Count> getList(){
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
}
