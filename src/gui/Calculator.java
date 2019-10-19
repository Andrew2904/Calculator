package gui;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    public Calculator(){
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS) );

        JPanel setupPanel, searchPanel, jailPanel, finePanel;
        setupPanel = new JPanel();
        setupPanel.setLayout( new BoxLayout(setupPanel, BoxLayout.X_AXIS) );
        searchPanel = new Search();

        setupPanel.add(searchPanel);
        content.add(setupPanel);

        JPanel sentencePanel, mainPanel, pastPanel, finalPanel;
        sentencePanel = new JPanel();
        sentencePanel.setLayout( new BoxLayout(sentencePanel, BoxLayout.Y_AXIS) );

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.RED);

        sentencePanel.add(mainPanel);
        content.add(sentencePanel);
    }

    public static void main(String args[]){
        JFrame frame = new Calculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }
}
