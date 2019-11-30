package gui.dialog;

import gui.list.Count;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.Date;
import java.util.List;

//TO DO: Verifica daca fisierul ales exista
//TO DO: Verifica daca fisierul e in formatul dorit
public class ExportDialog {
    String file;

    public void showDialog(JFrame parent, String caption){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Fisiere XML (*.xml)", "xml");
        fc.setFileFilter(xmlfilter);
        fc.setCurrentDirectory(new File("."));

        fc.showSaveDialog(parent);

        System.out.println(fc.getSelectedFile());
        file = fc.getSelectedFile().getAbsolutePath();
    }

    public void showDialog(String caption){
        showDialog(null, caption);
    }

    public String getFile(){
        return file;
    }

    public void export(List<Count> data){
        file = new File("save.xml").getAbsolutePath();

        try {
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(osw);

            out.writeStartDocument("UTF-8", "1.0");
            out.writeCharacters(System.getProperty("line.separator"));

            out.writeStartElement("Fapte");
            out.writeAttribute("data", Count.format(new Date()));

            //TO DO: Actualizeaza progress bar aici
            for(int i=0;i<data.size();i++){
                out.writeStartElement("Fapta");
                out.writeCharacters(System.getProperty("line.separator"));

                out.writeStartElement("Denumire");
                out.writeCharacters(data.get(i).getDesc());
                out.writeEndElement();
                out.writeCharacters(System.getProperty("line.separator"));

                out.writeStartElement("Inchisoare");
                out.writeCharacters(String.valueOf(data.get(i).getJail()));
                out.writeEndElement();
                out.writeCharacters(System.getProperty("line.separator"));


                out.writeStartElement("Amenda");
                out.writeCharacters(String.valueOf(data.get(i).getFine()));
                out.writeEndElement();
                out.writeCharacters(System.getProperty("line.separator"));

                out.writeStartElement("Editare");
                out.writeCharacters(data.get(i).getDate());
                out.writeEndElement();
                out.writeCharacters(System.getProperty("line.separator"));

                out.writeEndElement();
                out.writeCharacters(System.getProperty("line.separator"));
            }

            out.writeEndElement();

            out.writeEndDocument();
            out.close();
        } catch (IOException e) {
            System.err.println("Fisier gresit");
        } catch (Exception e) {
            System.err.println("Eroare la scriere");
        }
    }
}
