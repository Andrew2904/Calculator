package gui;

import javax.swing.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import gui.dialog.AddDialog;
import gui.list.Count;
import gui.list.CountRenderer;
import org.xml.sax.SAXException;
import util.XMLParser;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TO DO: Adaugat mesaje de eroare vizibile pentru user
public class Search extends JPanel implements KeyListener, ActionListener, MouseListener {
    List<Count> data;
    JList countList;
    JTextField searchField;
    XMLParser parser;

    public Search(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        searchField = new JTextField();
        searchField.addKeyListener(this);

        data = new ArrayList<Count>();

        parser = new XMLParser();

        countList = new JList();
        countList.setCellRenderer(new CountRenderer());
        countList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        countList.setLayoutOrientation(JList.VERTICAL);

        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        listPanel.setLayout( new BorderLayout() );
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 4));

        JButton addButton = new JButton();
        addButton.setText("Adauga fapta");
        addButton.addActionListener(this);

        JButton removeButton = new JButton();
        removeButton.setText("Sterge fapta");
        removeButton.addActionListener(this);

        JScrollPane countScroller = new JScrollPane(countList);
        listPanel.add(searchField, BorderLayout.NORTH);
        listPanel.add(countScroller, BorderLayout.CENTER);
        this.add(listPanel);
        buttonPanel.add(addButton, BorderLayout.SOUTH);
        buttonPanel.add(removeButton, BorderLayout.SOUTH);
        this.add(buttonPanel);
    }

    public void readDate(String source){
        parser.read(source);
        data = parser.getData();
        getData();
    }

    private void getData(){
        if(data == null)
            return;

        String term = searchField.getText();

        DefaultListModel listData = new DefaultListModel();
        for(Count entry:data)
            if(entry.contains(term))
                listData.addElement(entry);
        countList.setModel(listData);
    }

    public List<Count> getList(){
        return data;
    }

    public String getLast(){
        return parser.getLast();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        getData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        switch (source.getText()){
            case "Adauga fapta":
                AddDialog ad = new AddDialog();
                ad.showDialog();
                data.add(ad.getAdded());
                getData();
                break;
            case "Sterge fapta":
                data.remove(countList.getSelectedIndex());
                getData();
                break;
            default:
                return;
        }
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
