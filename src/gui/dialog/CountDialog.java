package gui.dialog;

import entity.Count;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

//TO DO: Tratat valori gresite vizibil pentru user
public class CountDialog extends JDialog implements ActionListener{
    private Count added;
    private boolean accessible;
    private JTextField descField, minJailField, maxJailField, minFineField, maxFineField, dateField;
    private JButton addButton;

    public CountDialog(Count current){
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
        descField.setText( current.getDesc() );
        descPanel.add(descField);
        this.add(descPanel);

        jailPanel = new JPanel();
        jailPanel.setLayout(new BoxLayout(jailPanel, BoxLayout.X_AXIS));
        jailPanel.add(new JLabel("Pedeapsa cu inchisoare(zile):"));
        minJailField = new JTextField();
        minJailField.setText(String.valueOf( current.getMinJail() ));
        jailPanel.add(minJailField);
        jailPanel.add(new JLabel("-"));
        maxJailField = new JTextField();
        maxJailField.setText(String.valueOf( current.getMaxJail() ));
        jailPanel.add(maxJailField);
        this.add(jailPanel);

        finePanel = new JPanel();
        finePanel.setLayout(new BoxLayout(finePanel, BoxLayout.X_AXIS));
        finePanel.add(new JLabel("Pedeapsa cu amenda:"));
        minFineField = new JTextField();
        minFineField.setText(String.valueOf( current.getMinFine() ));
        finePanel.add(minFineField);
        finePanel.add(new JLabel("-"));
        maxFineField = new JTextField();
        maxFineField.setText(String.valueOf( current.getMaxFine() ));
        finePanel.add(maxFineField);
        this.add(finePanel);

        datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.add(new JLabel("Data de aplicare:"));
        dateField = new JTextField();
        dateField.setText(String.valueOf( current.getDate() ));
        datePanel.add(dateField);
        this.add(datePanel);

        submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        JButton cancelButton;
        addButton = new JButton("Modifică");
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

    public CountDialog(){
        this(new Count());

        addButton.setText("Adaugă");
    }

    public void showDialog(){
        this.setVisible(true);
    }

    private boolean validateInput(){
        accessible = true;

        //System.out.println( "Description: "+ descField.getText() );
        added.setDesc( descField.getText() );
        try{
            int jail = Integer.parseInt(minJailField.getText());
            //System.out.println( "Jail(days): "+jail );
            added.setMinJail( jail );
            jail = Integer.parseInt(maxJailField.getText());
            added.setMaxJail( jail );
        }catch (Exception ex){
            accessible = false;
            System.err.println( "Jail(days): "+"WRONG" );
        }

        try{
            int fine = Integer.parseInt(minFineField.getText());
            //System.out.println( "Fine(RON): "+fine );
            added.setMinFine( fine );
            fine = Integer.parseInt(maxFineField.getText());
            added.setMaxFine( fine );
        }catch (Exception ex){
            accessible = false;
            System.err.println( "Fine(RON): "+"WRONG" );
        }

        try{
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse( dateField.getText() );
            added.setDate(date);
        }catch (Exception ex){
            accessible = false;
            System.err.println( "Data: "+"WRONG" );
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
