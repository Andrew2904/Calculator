package gui.panel;

import entity.Circumstance;
import entity.Felony;
import util.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CircumstancePanel extends JPanel {
    List<Circumstance> data;
    JList cList;
    JTextField searchField;
    XMLParser parser;
    private static Felony base;

    public CircumstancePanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        searchField = new JTextField();
    }

    public void readData(String source){

    }

    public static void updateBase(Felony baseFelony){
        base = baseFelony;

        System.out.println(baseFelony.getDesc());
    }
}
