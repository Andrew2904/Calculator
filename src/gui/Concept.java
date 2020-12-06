package gui;

import gui.demo.FelonyPanel;

import javax.swing.*;
import java.awt.*;

public class Concept {
    public static void main(String[] args){
        FelonyPanel frame = new FelonyPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator Demo");
        frame.setSize(450,480);
        frame.setLocation(200,200);
        frame.setMinimumSize( new Dimension(450, 480) );

        frame.setVisible(true);
    }
}
