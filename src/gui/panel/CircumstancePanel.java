package gui.panel;

import data.entity.Circumstance;
import data.entity.Felony;
import gui.Calculator;
import util.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircumstancePanel extends JPanel {
    List<Circumstance> data;
    JList cList;
    JTextField searchField;
    XMLParser parser;
    private static Felony base;

    Calculator parent;

    public CircumstancePanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        searchField = new JTextField();

        parent = Calculator.getInstance();

        //searchField.addKeyListener(this);

        data = new ArrayList<Circumstance>();

    }

    public void readData(String source){
    }

    public static void updateBase(Felony baseFelony){
        base = baseFelony;

        System.out.println(baseFelony.getDesc());
    }
}
