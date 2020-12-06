package gui;

import gui.demo.FelonyPanel;

import javax.swing.*;
import java.awt.*;

public class Concept {
    public static void main(String[] args){
        FelonyPanel frame = new FelonyPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator Demo");
        frame.setSize(500,550);
        frame.setLocation(200,200);
        frame.setMinimumSize( new Dimension(500, 550) );

        frame.setVisible(true);
    }
}
