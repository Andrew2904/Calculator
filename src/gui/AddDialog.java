package gui;

import gui.list.Count;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDialog extends JDialog implements ActionListener{
    private Count added;
    private boolean accessible;
    private static AddDialog instance = new AddDialog();
    private JTextField descField, jailField, fineField, dateField;

    private AddDialog(){
        super();
        this.setLocationRelativeTo(null);
        this.setTitle("Adaugare fapta");
        this.setModal(true);

        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel descPanel, jailPanel, finePanel, datePanel, submitPanel;

        descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.X_AXIS));
        descPanel.add(new JLabel("Descrierea faptei:"));
        descField = new JTextField();
        descPanel.add(descField);
        this.add(descPanel);

        jailPanel = new JPanel();
        jailPanel.setLayout(new BoxLayout(jailPanel, BoxLayout.X_AXIS));
        jailPanel.add(new JLabel("Pedeapsa cu inchisoare(zile):"));
        jailField = new JTextField();
        jailPanel.add(jailField);
        this.add(jailPanel);

        finePanel = new JPanel();
        finePanel.setLayout(new BoxLayout(finePanel, BoxLayout.X_AXIS));
        finePanel.add(new JLabel("Pedeapsa cu amenda:"));
        fineField = new JTextField();
        finePanel.add(fineField);
        this.add(finePanel);

        datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.add(new JLabel("Data de aplicare(optional):"));
        this.add(datePanel);

        submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        JButton addButton, cancelButton;
        addButton = new JButton("Adauga");
        addButton.setName("add");
        addButton.addActionListener(this);
        cancelButton = new JButton("Anuleaza");
        cancelButton.setName("cancel");
        cancelButton.addActionListener(this);

        submitPanel.add(addButton);
        submitPanel.add(cancelButton);
        this.add(submitPanel);

        accessible = false;
        added = new Count();

        this.pack();
    }

    public static void showDialog(){
        instance.show();
    }

    private boolean validateInput(){
        accessible = true;

        System.out.println( "Description: "+descField.getText() );
        try{
            int jail = Integer.parseInt(jailField.getText());
            System.out.println( "Jail(days): "+jail );
        }catch (Exception ex){
            accessible = false;
            System.out.println( "Jail(days): "+"WRONG" );
        }

        return accessible;
    }

    public Count getAdded() {
        if(accessible)
            return added;
        else
            return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton srcButton = (JButton) e.getSource();
        if( srcButton.getName().equals("add") )
            if( !validateInput() )
                return;
        else
        if( srcButton.getName().equals("cancel") )
            accessible = false;

        this.dispose();
    }
}
