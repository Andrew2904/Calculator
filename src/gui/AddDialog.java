package gui;

import util.Count;

import javax.swing.*;

public class AddDialog extends JDialog {
    private Count added;
    private boolean accessible;
    private static AddDialog instance = new AddDialog();

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
        this.add(descPanel);

        jailPanel = new JPanel();
        jailPanel.setLayout(new BoxLayout(jailPanel, BoxLayout.X_AXIS));
        jailPanel.add(new JLabel("Pedeapsa cu inchisoare(zile):"));
        this.add(jailPanel);

        finePanel = new JPanel();
        finePanel.setLayout(new BoxLayout(finePanel, BoxLayout.X_AXIS));
        finePanel.add(new JLabel("Pedeapsa cu amenda:"));
        this.add(finePanel);

        datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.add(new JLabel("Data de aplicare(optional):"));
        this.add(datePanel);

        submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        this.add(submitPanel);

        accessible = false;
        added = new Count();

        this.pack();
    }

    public static void showDialog(){
        instance.show();
    }

    public Count getAdded() {
        if(accessible)
            return added;
        else
            return null;
    }
}
