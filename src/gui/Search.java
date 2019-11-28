package gui;

import javax.swing.*;

import gui.AddDialog;
import gui.list.Count;
import gui.list.CountRenderer;
import util.XMLParser;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Search extends JPanel implements KeyListener, ActionListener, MouseListener {
    List<Count> data;
    JList countList;
    JTextField searchField;

    public Search(){
        this.setLayout( new BorderLayout() );
        this.setBackground(Color.BLACK);
        searchField = new JTextField();
        searchField.addKeyListener(this);

        data = XMLParser.read("");
        countList = new JList();
        countList.setCellRenderer(new CountRenderer());
        countList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        countList.setLayoutOrientation(JList.VERTICAL);

        getData("");

        JButton addButton = new JButton();
        addButton.setText("Adauga fapta");
        addButton.addActionListener(this);

        JScrollPane countScroller = new JScrollPane(countList);
        this.add(searchField, BorderLayout.NORTH);
        this.add(countScroller, BorderLayout.CENTER);
        this.add(addButton, BorderLayout.SOUTH);
    }

    private void getData(String term){
        DefaultListModel listData = new DefaultListModel();
        for(Count entry:data)
            if(entry.contains(term))
                listData.addElement(entry);
        countList.setModel(listData);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        getData(searchField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddDialog.showDialog();
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
