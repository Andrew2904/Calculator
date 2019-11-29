package gui.list;

import util.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class CountRenderer extends JPanel implements ListCellRenderer<Count> {
    int w = 25, h = 25;

    @Override
    public Component getListCellRendererComponent(JList<? extends Count> list, Count value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();
        if(isSelected)
            setBorder(BorderFactory.createLineBorder(Color.red, 5));
        else
            setBorder(BorderFactory.createLineBorder(Color.black, 5));

        //setText(value.toString());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel descLabel, jailLabel, fineLabel, lastLabel;
        descLabel = new JLabel();
        jailLabel = new JLabel();
        fineLabel = new JLabel();
        lastLabel = new JLabel();

        if(value.desc.length()>20)
            descLabel.setText(value.desc.substring(0, 20)+"...");
        else
            descLabel.setText(value.desc);

        String fineText, jailText;
        fineText = "";
        jailText = "";

        try {
            ImageIcon ii = new ImageIcon(ResourceManager.getImageFromResources("jail.png", w, h));
            jailLabel.setIcon(ii);

            ii = new ImageIcon(ResourceManager.getImageFromResources("fine.png", w, h));
            fineLabel.setIcon(ii);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            jailLabel.setIcon(null);
            fineLabel.setIcon(null);

            jailText = "Inchisoare: ";
            fineText = "Amenda: ";
        }

        jailLabel.setText(jailText+value.jail);
        fineLabel.setText(fineText+value.fine);
        lastLabel.setText(value.date.toString());

        add(descLabel);
        add(jailLabel);
        add(fineLabel);
        add(lastLabel);

        return this;
    }
}
