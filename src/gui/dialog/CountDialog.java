package gui.dialog;

import data.entity.Felony;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDialog extends JDialog implements ActionListener{
    private Felony added;
    private boolean accessible;
    private JTextField descField, minJailField, maxJailField, minFineField, maxFineField, dateField;
    private JButton addButton;
    private JLabel errLabel;

    public CountDialog(Felony current){
        super();
        this.setLocationRelativeTo(null);
        this.setTitle("Modificare faptă");
        this.setModal(true);

        JPanel content = (JPanel) this.getContentPane();
        JPanel dataPanel, submitPanel;
        content.setLayout(new BorderLayout());

        errLabel = new JLabel();
        errLabel.setBackground(Color.red);
        errLabel.setOpaque(true);
        content.add(errLabel, BorderLayout.NORTH);

        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 10;
        gbc.ipadx = 10;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        dataPanel.add(new JLabel("Descrierea faptei:"), gbc);
        descField = new JTextField();
        descField.setText( current.getDesc() );
        gbc.gridx = 2;
        gbc.gridwidth = 7;
        dataPanel.add(descField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        dataPanel.add(new JLabel("Pedeapsa cu inchisoare(zile):"), gbc);
        minJailField = new JTextField();
        minJailField.setText(String.valueOf( current.getMinJail() ));
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 3.0;
        dataPanel.add(minJailField, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.fill = 0;
        dataPanel.add(new JLabel("-"), gbc);
        maxJailField = new JTextField();
        maxJailField.setText(String.valueOf( current.getMaxJail() ));
        gbc.gridx = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 3.0;
        gbc.fill = GridBagConstraints.BOTH;
        dataPanel.add(maxJailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        dataPanel.add(new JLabel("Pedeapsa cu amenda:"), gbc);
        minFineField = new JTextField();
        minFineField.setText(String.valueOf( current.getMinFine() ));
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 3.0;
        dataPanel.add(minFineField, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.fill = 0;
        dataPanel.add(new JLabel("-"), gbc);
        maxFineField = new JTextField();
        maxFineField.setText(String.valueOf( current.getMaxFine() ));
        gbc.gridx = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 3.0;
        gbc.fill = GridBagConstraints.BOTH;
        dataPanel.add(maxFineField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        dataPanel.add(new JLabel("Data de aplicare(zi-luna-an):"), gbc);
        dateField = new JTextField();
        dateField.setText(String.valueOf( current.getDate() ));
        gbc.gridx = 2;
        gbc.gridwidth = 7;
        dataPanel.add(dateField, gbc);

        this.add(dataPanel, BorderLayout.CENTER);

        JButton cancelButton;
        addButton = new JButton("Modifică");
        addButton.setName("add");
        addButton.addActionListener(this);
        cancelButton = new JButton("Anuleaza");
        cancelButton.setName("cancel");
        cancelButton.addActionListener(this);

        submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        submitPanel.add(addButton);
        submitPanel.add(cancelButton);
        this.add(submitPanel, BorderLayout.SOUTH);

        accessible = false;
        added = new Felony();

        this.pack();
        this.setResizable(false);
    }

    public CountDialog(){
        this(new Felony());

        addButton.setText("Adaugă");
        this.setTitle("Adăugare faptă");
    }

    public void showDialog(){
        this.setVisible(true);
    }

    private boolean validateInput(){
        accessible = true;

        added.setDesc( descField.getText() );
        try{
            int jail = Integer.parseInt(minJailField.getText());
            added.setMinJail( jail );
            jail = Integer.parseInt(maxJailField.getText());
            added.setMaxJail( jail );
        }catch (Exception ex){
            accessible = false;
            error( "Pedeapsa cu închisoarea incorectă", ex);
        }

        try{
            int fine = Integer.parseInt(minFineField.getText());
            added.setMinFine( fine );
            fine = Integer.parseInt(maxFineField.getText());
            added.setMaxFine( fine );
        }catch (Exception ex){
            accessible = false;
            error( "Pedeapsa cu amendă incorectă", ex);
        }

        try{
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            Date date = df.parse( dateField.getText() );
            added.setDate(date);
        }catch (Exception ex){
            accessible = false;
            error( "Data incorectă", ex);
        }

        return accessible;
    }

    public Felony getAdded() {
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

    private void error(String message, Exception ex){
        System.err.println("Message: "+message);
        System.err.println("Caused by: "+ex.getMessage());

        errLabel.setText(message);
        this.pack();
    }
}
