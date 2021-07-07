package gui.demo;

import entity.Count;
import entity.Felony;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//TO DO: Count = Felony + Circumstances
public class FelonyPanel extends JFrame implements ActionListener, ListSelectionListener{
    private JPanel rootPanel;
    private JList countList;
    private JPanel focusPanel;
    private JButton insertButton;
    private JButton removeButton;
    private JPanel sentencePanel;
    private JButton updateButton;

    DefaultListModel countModel;
    ArrayList<Count> countRegister;
    int findex;
    FelonyForm form;

    public FelonyPanel(){
        add(rootPanel);
        setVisible(false);

        countModel = new DefaultListModel();
        countList.setModel(countModel);
        countList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        countList.setCellRenderer(new FelonyRenderer());
        countList.addListSelectionListener(this);

        findex = 0;
        countRegister = new ArrayList<>();

        form = new FelonyForm();
        focusPanel.add(form, BorderLayout.CENTER);
        insertButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        rootPanel.setVisible(aFlag);
    }

    public static void changeFont (Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if( src.equals(insertButton) ){
            String data = "Fapta "+ ++findex;

            countModel.addElement(data);
            Count current = new Count();
            current.setDesc(data);
            countRegister.add(current);
        }
        else
        if( src.equals(updateButton) ){
            Count added = form.save();

            int index = countList.getSelectedIndex();
            countModel.set(index, added.getDesc());
            countRegister.set(index, added);

            //getSentence();
            //updateSentence();
            showCount(added);
        }
        else
        if( src.equals(removeButton) ){
            int index = countList.getSelectedIndex();
            form.setVisible(false);

            if(index == -1)
                return;

            try {
                countModel.removeElement(countRegister.get(index).getDesc());
                countRegister.remove(index);
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }

            this.revalidate();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int index = countList.getSelectedIndex();
        //System.out.println( countRegister.get(index) );

        if(index == -1)
            return;

        showCount(countRegister.get(index));
    }

    private void showCount(Count f){
        form.showCount(f);

        Font bigFont = getFont().deriveFont(18.0f);
        form.changeFont(form, bigFont);
        form.setVisible(true);

        this.revalidate();
    }
}
