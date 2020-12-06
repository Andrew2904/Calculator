package gui.demo;

import entity.Circumstance;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CIrcumstanceList extends JPanel {
    private JPanel rootPanel;
    private JList circumstanceList;
    private JButton insertButton;
    private JButton removeButton;
    private JButton updateButton;

    public CIrcumstanceList(){
        add(rootPanel);
    }

    public void setCircumstances(List<Circumstance> cList){

    }

    public List<Circumstance> getCircumstances(){
        return new ArrayList<>();
    }
}
