package gui.demo;

import entity.Circumstance;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.List;

//TO DO: CardLayout pentru fixed si/sau aggro
//TO DO: Afisare fractionara pentru !fixed
public class CircumstancePanel extends JPanel implements ActionListener, ListSelectionListener {
    private List<Circumstance> circumstances;
    private JList circumstanceList;
    private DefaultListModel circumstanceModel;
    private String addCirc, saveCirc, removeCirc;
    private int cindex;

    private JPanel focusPanel;
    private JTextField descField, minJailField, maxJailField, minFineField, maxFineField;
    private JCheckBox fixedBox, aggroBox;

    FocusListener fl;

    public CircumstancePanel(List<Circumstance> current){
        setLayout(new BorderLayout());

        addCirc = "Adaugă circumstanță";
        saveCirc = "Salvează circumstanță";
        removeCirc = "Șterge circumstanță";

        JPanel allPanel;
        allPanel = new JPanel(new BorderLayout());

        circumstanceModel = new DefaultListModel();
        circumstanceList = new JList(circumstanceModel);
        circumstanceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        circumstanceList.setCellRenderer(new CircumstanceRenderer());
        circumstanceList.addListSelectionListener(this);
        JScrollPane scroller = new JScrollPane(circumstanceList);
        allPanel.add(scroller, BorderLayout.CENTER);

        JButton addButton = new JButton();
        addButton.setText(addCirc);
        addButton.addActionListener(this);
        allPanel.add(addButton, BorderLayout.SOUTH);

        add(allPanel, BorderLayout.WEST);

        focusPanel = new JPanel();
        focusPanel.setLayout(new BoxLayout(focusPanel, BoxLayout.Y_AXIS));
        JScrollPane focusScroller = new JScrollPane(focusPanel);
        add(focusScroller, BorderLayout.CENTER);

        cindex = 0;
        circumstances = current;

        for(int i=0;i<circumstances.size();i++)
            circumstanceModel.addElement( circumstances.get(i).getDesc() );
    }

    public List<Circumstance> getCircumstances(){
        return circumstances;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(fixedBox) || e.getSource().equals(aggroBox)){
            //save();
        }
        else{
            JButton src = (JButton) e.getSource();

            if(src.getText().equals(addCirc)){
                String data = "Circumstanța "+ ++cindex;

                circumstanceModel.addElement(data);
                Circumstance current = new Circumstance();
                current.setDesc(data);
                circumstances.add(current);

                //save();
            }
            else
            if(src.getText().equals(saveCirc)){
                save();
            }
            else
            if(src.getText().equals(removeCirc)){
                int index = circumstanceList.getSelectedIndex();
                focusPanel.removeAll();
                this.revalidate();
                //circumstanceList.clearSelection();

                if(index == -1)
                    return;

                try {
                    circumstanceModel.removeElement( circumstances.get(index).getDesc() );
                    circumstances.remove(index);
                }catch (Exception ex){
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = circumstanceList.getSelectedIndex();

        if(index == -1)
            return;

        showCircumstance(circumstances.get(index));
    }

    private void showCircumstance(Circumstance current){
        focusPanel.removeAll();

        JPanel dataPanel, updatePanel;

        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.add(new JLabel("Descriere: "), BorderLayout.WEST);
        descField = new JTextField();
        descField.setText(current.getDesc());
        descPanel.add(descField, BorderLayout.CENTER);
        dataPanel.add(descPanel);

        JPanel fixedPanel = new JPanel(new BorderLayout());
        fixedBox = new JCheckBox("Interval fix");
        fixedBox.setSelected( current.isFixed() );
        fixedBox.addActionListener(this);
        fixedPanel.add(fixedBox, BorderLayout.CENTER);
        dataPanel.add(fixedPanel);

        JPanel aggroPanel = new JPanel(new BorderLayout());
        aggroBox = new JCheckBox("Agravant");
        aggroBox.setSelected( current.isAggro() );
        aggroBox.addActionListener(this);
        aggroPanel.add(aggroBox, BorderLayout.CENTER);
        dataPanel.add(aggroPanel);

        JPanel minJailPanel = new JPanel(new BorderLayout());
        minJailPanel.add(new JLabel("Închisoare minimă: "), BorderLayout.WEST);
        minJailField = new JTextField();
        minJailField.setText(current.getMinJail()+"");
        minJailPanel.add(minJailField, BorderLayout.CENTER);
        dataPanel.add(minJailPanel);

        JPanel maxJailPanel = new JPanel(new BorderLayout());
        maxJailPanel.add(new JLabel("Închisoare maximă: "), BorderLayout.WEST);
        maxJailField = new JTextField();
        maxJailField.setText(current.getMaxJail()+"");
        maxJailPanel.add(maxJailField, BorderLayout.CENTER);
        dataPanel.add(maxJailPanel);

        JPanel minFinePanel = new JPanel(new BorderLayout());
        minFinePanel.add(new JLabel("Amendă minimă: "), BorderLayout.WEST);
        minFineField = new JTextField();
        minFineField.setText(current.getMinFine()+"");
        minFinePanel.add(minFineField, BorderLayout.CENTER);
        dataPanel.add(minFinePanel);

        JPanel maxFinePanel = new JPanel(new BorderLayout());
        maxFinePanel.add(new JLabel("Amendă maximă: "), BorderLayout.WEST);
        maxFineField = new JTextField();
        maxFineField.setText(current.getMaxFine()+"");
        maxFinePanel.add(maxFineField, BorderLayout.CENTER);
        dataPanel.add(maxFinePanel);

        focusPanel.add(dataPanel, BorderLayout.CENTER);

        updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.X_AXIS));

        JButton saveButton, removeButton;
        saveButton = new JButton(saveCirc);
        saveButton.addActionListener(this);
        updatePanel.add(saveButton);

        removeButton = new JButton(removeCirc);
        removeButton.addActionListener(this);
        updatePanel.add(removeButton);

        focusPanel.add(updatePanel, BorderLayout.SOUTH);

        descField.addFocusListener(fl);
        fixedBox.addFocusListener(fl);
        fixedBox.addFocusListener(fl);
        maxJailField.addFocusListener(fl);
        minFineField.addFocusListener(fl);
        maxFineField.addFocusListener(fl);

        this.revalidate();
    }

    public void save(){
        int index = circumstanceList.getSelectedIndex();
        //System.out.println(index);

        Circumstance added = new Circumstance();
        added.setDesc( descField.getText() );
        added.setFixed( fixedBox.isSelected() );
        added.setAggro( aggroBox.isSelected() );

        int minj, maxj, minf, maxf;
        minj = (int) Float.parseFloat(minJailField.getText());
        maxj = (int) Float.parseFloat(maxJailField.getText());
        minf = (int) Float.parseFloat(minFineField.getText());
        maxf = (int) Float.parseFloat(maxFineField.getText());

        added.setMinJail(1, minj);
        added.setMaxJail(1, maxj);
        added.setMinFine(1, minf);
        added.setMaxFine(1, maxf);

        circumstanceModel.set( index, added.getDesc());
        circumstances.set( index, added);
    }

    @Override
    public synchronized void addFocusListener(FocusListener l) {
        super.addFocusListener(l);

        fl = l;
    }
}
