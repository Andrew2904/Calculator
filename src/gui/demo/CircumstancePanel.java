package gui.demo;

import entity.Circumstance;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CircumstancePanel extends JPanel implements ActionListener, ListSelectionListener {
    private JPanel rootPanel;
    private JList circumstanceList;
    private JButton insertButton, removeButton;
    public JButton updateButton;
    private JPanel circumstancePanel;

    private List<Circumstance> circumstances;
    private DefaultListModel circumstanceModel;
    private CircumstanceForm form;

    private int cindex;

    public CircumstancePanel(){
        add(rootPanel);

        insertButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);

        circumstanceModel = new DefaultListModel();
        circumstanceList.setModel(circumstanceModel);
        circumstanceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        circumstanceList.setCellRenderer(new CircumstanceRenderer());
        circumstanceList.addListSelectionListener(this);

        form = new CircumstanceForm();
        circumstancePanel.add(form, BorderLayout.CENTER);

        cindex = 0;
        circumstances = new ArrayList<>();
    }

    public void setCircumstances(List<Circumstance> current){
        circumstances = current;

        circumstanceModel.removeAllElements();
        for(int i=0;i<circumstances.size();i++)
            circumstanceModel.addElement( circumstances.get(i).getDesc() );
    }

    public List<Circumstance> getCircumstances(){
        return circumstances;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if( src.equals(insertButton) ){
            String data = "Circumstanța "+ ++cindex;

            circumstanceModel.addElement(data);
            Circumstance current = new Circumstance();
            current.setDesc(data);
            circumstances.add(current);
        }
        else
        if( src.equals(updateButton) ){
            int index = circumstanceList.getSelectedIndex();

            if(index == -1)
                return;

            Circumstance added = form.read();

            circumstanceModel.set(index, added.getDesc());
            circumstances.set(index, added);

        }
        else
        if( src.equals(removeButton) ){
            int index = circumstanceList.getSelectedIndex();

            if(index == -1)
                return;

            try {
                circumstanceModel.removeElement( circumstances.get(index).getDesc() );
                circumstances.remove(index);
                form.setVisible(false);
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }

            revalidate();
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
        form.showCircumstance(current);
        form.setVisible(true);
    }
}
