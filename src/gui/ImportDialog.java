package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ImportDialog extends JDialog {
    String file;

    public void showDialog(JFrame parent, String caption){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Fisiere XML (*.xml)", "xml");
        fc.setFileFilter(xmlfilter);
        fc.setCurrentDirectory(new File("."));

        fc.showDialog(parent, caption);

        System.out.println(fc.getSelectedFile());
    }

    public void showDialog(String caption){
        showDialog(null, caption);
    }
}
