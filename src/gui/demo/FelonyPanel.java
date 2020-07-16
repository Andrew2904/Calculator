package gui.demo;

import entity.Count;
import entity.Sentence;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class FelonyPanel extends JPanel implements ActionListener, ListSelectionListener, FocusListener {
    DefaultListModel felonyModel;
    JList felonyList;
    int findex;
    ArrayList<Count> felonyRegister;
    JPanel focusPanel;

    String addCount, saveCount, removeCount;
    JTextField descField, minFineField, maxFineField;
    JTextField minJailDayField, minJailMonthField, minJailYearField, maxJailDayField, maxJailMonthField, maxJailYearField;
    CircumstancePanel cp;
    SentencePanel primaryPanel, cumulativePanel, currentSentence;

    public FelonyPanel(){
        setLayout(new BorderLayout());

        JPanel allPanel;
        allPanel = new JPanel();
        allPanel.setLayout(new BorderLayout());

        felonyModel = new DefaultListModel();
        felonyList = new JList(felonyModel);
        felonyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        felonyList.setCellRenderer(new FelonyRenderer());
        felonyList.addListSelectionListener(this);
        JScrollPane scroller = new JScrollPane(felonyList);
        allPanel.add(scroller, BorderLayout.CENTER);

        addCount = "Adaugă faptă";
        saveCount = "Salvează faptă";
        removeCount = "Șterge faptă";

        JButton addButton = new JButton();
        addButton.setText(addCount);
        addButton.addActionListener(this);
        allPanel.add(addButton, BorderLayout.SOUTH);

        add(allPanel, BorderLayout.WEST);

        focusPanel = new JPanel();
        focusPanel.setLayout(new BoxLayout(focusPanel, BoxLayout.Y_AXIS));
        JScrollPane focusScroller = new JScrollPane(focusPanel);
        add(focusScroller, BorderLayout.CENTER);

        findex = 0;
        felonyRegister = new ArrayList<>();

        JPanel sentencePanel = new JPanel();
        sentencePanel.setLayout(new BoxLayout(sentencePanel, BoxLayout.Y_AXIS));
        primaryPanel = new SentencePanel("Sentința principală");
        sentencePanel.add(primaryPanel);
        cumulativePanel = new SentencePanel("Spor de  pedeapsă");
        sentencePanel.add(cumulativePanel);

        currentSentence = new SentencePanel("Sentința curentă");
        add(sentencePanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if(src.getText().equals(addCount)){
            String data = "Fapta "+ ++findex;

            felonyModel.addElement(data);
            Count current = new Count();
            current.setDesc(data);
            felonyRegister.add(current);
        }
        else
        if(src.getText().equals(saveCount)){
            //System.out.println(index);

            try{
                cp.save();
            }catch (Exception ex){

            }

            int index = felonyList.getSelectedIndex();

            getSentence();
            updateSentence();
        }
        else
        if(src.getText().equals(removeCount)){
            int index = felonyList.getSelectedIndex();
            //felonyList.clearSelection();

            if(index == -1)
                return;

            try {
                felonyModel.removeElement(felonyRegister.get(index).getDesc());
                felonyRegister.remove(index);
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = felonyList.getSelectedIndex();
        //System.out.println( felonyRegister.get(index) );

        if(index == -1)
            return;

        showFelony(felonyRegister.get(index));
    }

    private void showFelony(Count f){
        focusPanel.removeAll();

        JPanel dataPanel, circPanel, updatePanel;
        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.add(new JLabel("Descriere: "), BorderLayout.WEST);
        descField = new JTextField();
        descField.setText(f.getDesc());
        descField.addFocusListener(this);
        descPanel.add(descField, BorderLayout.CENTER);
        dataPanel.add(descPanel);

        JPanel minJailPanel = new JPanel(new BorderLayout());
        minJailPanel.add(new JLabel("Închisoare minimă: "), BorderLayout.WEST);
        JPanel minEntryPanel = new JPanel();
        minEntryPanel.setLayout(new BoxLayout(minEntryPanel, BoxLayout.X_AXIS));
        minJailYearField = new JTextField();
        minJailYearField.setText( Sentence.getYear( f.getBase().getMinJail() )+"" );
        minJailYearField.addFocusListener(this);
        minEntryPanel.add(minJailYearField);
        minEntryPanel.add(new JLabel(" Ani "));
        minJailMonthField = new JTextField();
        minJailMonthField.setText( Sentence.getMonth( f.getBase().getMinJail() )+"" );
        minJailMonthField.addFocusListener(this);
        minEntryPanel.add(minJailMonthField);
        minEntryPanel.add(new JLabel(" Luni "));
        minJailDayField = new JTextField();
        minJailDayField.setText( Sentence.getDay( f.getBase().getMinJail() )+"" );
        minJailDayField.addFocusListener(this);
        minEntryPanel.add(minJailDayField);
        minEntryPanel.add(new JLabel(" Zile "));
        minJailPanel.add(minEntryPanel, BorderLayout.CENTER);
        dataPanel.add(minJailPanel);

        JPanel maxJailPanel = new JPanel(new BorderLayout());
        maxJailPanel.add(new JLabel("Închisoare maximă: "), BorderLayout.WEST);
        JPanel maxEntryPanel = new JPanel();
        maxEntryPanel.setLayout(new BoxLayout(maxEntryPanel, BoxLayout.X_AXIS));
        maxJailYearField = new JTextField();
        maxJailYearField.setText( Sentence.getYear( f.getBase().getMaxJail() )+"" );
        maxJailYearField.addFocusListener(this);
        maxEntryPanel.add(maxJailYearField);
        maxEntryPanel.add(new JLabel(" Ani "));
        maxJailMonthField = new JTextField();
        maxJailMonthField.setText( Sentence.getMonth( f.getBase().getMaxJail() )+"" );
        maxJailMonthField.addFocusListener(this);
        maxEntryPanel.add(maxJailMonthField);
        maxEntryPanel.add(new JLabel(" Luni "));
        maxJailDayField = new JTextField();
        maxJailDayField.setText( Sentence.getDay( f.getBase().getMaxJail() )+"" );
        maxJailDayField.addFocusListener(this);
        maxEntryPanel.add(maxJailDayField);
        maxEntryPanel.add(new JLabel(" Zile "));
        maxJailPanel.add(maxEntryPanel, BorderLayout.CENTER);
        dataPanel.add(maxJailPanel);

        JPanel minFinePanel = new JPanel(new BorderLayout());
        minFinePanel.add(new JLabel("Amendă minimă: "), BorderLayout.WEST);
        minFineField = new JTextField();
        minFineField.setText(f.getBase().getMinFine()+"");
        minFineField.addFocusListener(this);
        minFinePanel.add(minFineField, BorderLayout.CENTER);
        dataPanel.add(minFinePanel);

        JPanel maxFinePanel = new JPanel(new BorderLayout());
        maxFinePanel.add(new JLabel("Amendă maximă: "), BorderLayout.WEST);
        maxFineField = new JTextField();
        maxFineField.setText(f.getBase().getMaxFine()+"");
        maxFineField.addFocusListener(this);
        maxFinePanel.add(maxFineField, BorderLayout.CENTER);
        dataPanel.add(maxFinePanel);

        dataPanel.add(currentSentence);

        focusPanel.add(dataPanel, BorderLayout.NORTH);

        circPanel = new JPanel();
        circPanel.add(new JLabel("Circumstanțe: "), BorderLayout.WEST);
        cp = new CircumstancePanel(f.getCircumstances());
        cp.addFocusListener(this);
        circPanel.add(cp, BorderLayout.CENTER);

        focusPanel.add(circPanel, BorderLayout.CENTER);

        updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.X_AXIS));

        JButton saveButton, removeButton;
        saveButton = new JButton(saveCount);
        saveButton.addActionListener(this);
        updatePanel.add(saveButton);

        removeButton = new JButton(removeCount);
        removeButton.addActionListener(this);
        updatePanel.add(removeButton);

        focusPanel.add(updatePanel, BorderLayout.SOUTH);

        this.revalidate();
    }

    private void getSentence(){
        Sentence cumulativeSentence = new Sentence(0, 0, 0, 0);
        Sentence maxSentence = new Sentence(0, 0, 0, 0);

        for(int i=0;i<felonyRegister.size();i++) {
            Sentence current = felonyRegister.get(i).getCompound();
            if (!maxSentence.isGreater( current ))
                maxSentence = current;
            cumulativeSentence.add( current );
        }

        primaryPanel.showSentence( maxSentence );
        cumulativeSentence.sub( maxSentence );

        cumulativeSentence.mul(1/3f);

        cumulativePanel.showSentence( cumulativeSentence );
    }

    private void updateSentence(){
        int index = felonyList.getSelectedIndex();

        Count added = new Count();
        added.setDesc( descField.getText() );
        Sentence read = new Sentence();
        int minJail = Sentence.getDays( Integer.parseInt( minJailYearField.getText() ), Integer.parseInt( minJailMonthField.getText() ), Integer.parseInt( minJailDayField.getText() ));
        int maxJail = Sentence.getDays( Integer.parseInt( maxJailYearField.getText() ), Integer.parseInt( maxJailMonthField.getText() ), Integer.parseInt( maxJailDayField.getText() ));
        read.setMinJail( minJail );
        read.setMaxJail( maxJail );
        read.setMinFine( Integer.parseInt( minFineField.getText() ));
        read.setMaxFine( Integer.parseInt( maxFineField.getText() ));
        added.setBase(read);
        added.setCircumstances( cp.getCircumstances() );

        currentSentence.showSentence( added.getCompound() );

        updateFelony(added);

        felonyModel.set( index, added.getDesc());
        felonyRegister.set( index, added);
    }

    private void updateFelony(Count current){
        current.update();

        minJailYearField.setText( Sentence.getYear(current.getBase().getMinJail())+"" );
        minJailMonthField.setText( Sentence.getMonth(current.getBase().getMinJail())+"" );
        minJailDayField.setText( Sentence.getDay(current.getBase().getMinJail())+"" );
        maxJailYearField.setText( Sentence.getYear(current.getBase().getMaxJail())+"" );
        maxJailMonthField.setText( Sentence.getMonth(current.getBase().getMaxJail())+"" );
        maxJailDayField.setText( Sentence.getDay(current.getBase().getMaxJail())+"" );
        minFineField.setText( current.getBase().getMinFine()+"" );
        maxFineField.setText( current.getBase().getMaxFine()+"" );
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        updateSentence();
    }
}
