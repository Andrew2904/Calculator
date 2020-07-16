package gui.panel;

import entity.Felony;
import util.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CircumstancePanel extends JPanel {
    List<Felony> data;
    JList cList;
    JTextField searchField;
    XMLParser parser;

    public CircumstancePanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        searchField = new JTextField();
    }

    public void readData(String source){

    }
}
