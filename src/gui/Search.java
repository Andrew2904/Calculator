package gui;

import javax.swing.*;

import util.Count;
import util.XMLParser;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Search extends JPanel implements KeyListener {
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
        countList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        countList.setLayoutOrientation(JList.VERTICAL);

        getData("");

        JScrollPane countScroller = new JScrollPane(countList);
        this.add(searchField, BorderLayout.NORTH);
        this.add(countScroller, BorderLayout.CENTER);
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
}
