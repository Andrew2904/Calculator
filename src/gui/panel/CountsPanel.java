package gui.panel;

import javax.swing.*;

import gui.Calculator;
import gui.dialog.CountDialog;
import entity.Count;
import gui.list.CountRenderer;
import util.XMLParser;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

//TO DO: Adaugat mesaje de eroare vizibile pentru user
//TO DO: Check events
public class CountsPanel extends JPanel implements ActionListener, KeyListener, MouseListener{
    List<Count> data;
    JList countList;
    JTextField searchField;
    XMLParser parser;

    public CountsPanel(){
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
        countList.addMouseListener(this);

        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        listPanel.setLayout( new BorderLayout() );
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 4));

        JButton addButton = new JButton();
        addButton.setText("Adaugă fapta");
        addButton.addActionListener(this);

        JButton changeButton = new JButton();
        changeButton.setText("Modifică fapta");
        changeButton.addActionListener(this);

        JButton removeButton = new JButton();
        removeButton.setText("Șterge fapta");
        removeButton.addActionListener(this);

        JScrollPane countScroller = new JScrollPane(countList);
        listPanel.add(searchField, BorderLayout.NORTH);
        listPanel.add(countScroller, BorderLayout.CENTER);
        this.add(listPanel);
        buttonPanel.add(addButton, BorderLayout.SOUTH);
        buttonPanel.add(changeButton, BorderLayout.SOUTH);
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
        CountDialog ad;
        int i;
        Count added;

        switch (source.getText()){
            case "Adaugă fapta":
                ad = new CountDialog();
                ad.showDialog();
                added = ad.getAdded();
                if(added != null)
                    data.add(added);

                break;
            case "Șterge fapta":
                i = countList.getSelectedIndex();
                if(i>=0)
                    data.remove(countList.getSelectedIndex());

                break;
            case "Modifică fapta":
                i = countList.getSelectedIndex();
                if(i<0)
                    break;

                ad = new CountDialog(data.get(i));
                ad.showDialog();

                added = ad.getAdded();
                if(added != null)
                    data.set(i, added);

                break;
            default:
                return;
        }

        getData();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent source = (JComponent) e.getSource();
        System.out.println("Click");
        do{
            //System.out.println("Searching... "+source.getClass());
            source = (JComponent) source.getParent();
        }while(source!=null && !(source.getParent() instanceof Calculator));

        Calculator parent = (Calculator) source.getParent();
        parent.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getClickCount()!=1)
            return;

        JComponent source = (JComponent) e.getSource();
        System.out.println("Hooooooooooooooooold");
        do{
            //System.out.println("Searching... "+source.getClass());
            source = (JComponent) source.getParent();
        }while(source!=null && !(source.getParent() instanceof Calculator));

        Calculator parent = (Calculator) source.getParent();
        parent.mouseClicked(e);
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
