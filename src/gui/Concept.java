package gui;

import gui.demo.FelonyPanel;

import javax.swing.*;
import java.awt.event.*;

public class Concept extends JFrame implements ActionListener, MouseListener {
    public Concept(){

    }

    public static void main(String[] args){
        Concept frame = new Concept();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator Demo");
        frame.setSize(800,800);
        frame.setLocation(200,200);

        JPanel content = (JPanel) frame.getContentPane();
        FelonyPanel counts = new FelonyPanel();

        content.add(counts);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
