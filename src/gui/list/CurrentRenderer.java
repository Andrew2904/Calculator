package gui.list;

import entity.Felony;
import util.ResourceManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CurrentRenderer extends JPanel implements ListCellRenderer<Felony> {
    int w = 25, h = 25, t = 5;
    int width = 40;

    @Override
    public Component getListCellRendererComponent(JList<? extends Felony> list, Felony value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();
        Border mainBorder, paddingBorder;
        if(isSelected)
            mainBorder = BorderFactory.createLineBorder(Color.red, t);
        else
            mainBorder = BorderFactory.createLineBorder(Color.black, t);

        paddingBorder = BorderFactory.createCompoundBorder(mainBorder, new EmptyBorder(t, t, t, t));
        setBorder(paddingBorder);

        //setText(value.toString());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel descLabel, jailLabel, fineLabel, lastLabel;
        descLabel = new JLabel();
        jailLabel = new JLabel();
        fineLabel = new JLabel();
        lastLabel = new JLabel();

        String descText = "<html>";
        int i;
        for(i=0;i<value.getDesc().length()/width-1;i++){
            descText += value.getDesc().substring(width*i, width*(i+1));
            descText += "<br>";
        }
        descText += value.getDesc().substring(width*i, value.getDesc().length());
        descText += "<br>";
        descText += "</html>";
        descLabel.setText(descText);

        String fineText, jailText;
        fineText = "";
        jailText = "";

        try {
            ImageIcon ii = new ImageIcon(ResourceManager.getImageFromResources("jail.png", w, h));
            jailLabel.setIcon(ii);

            ii = new ImageIcon(ResourceManager.getImageFromResources("fine.png", w, h));
            fineLabel.setIcon(ii);

            ii = new ImageIcon(ResourceManager.getImageFromResources("date.png", w, h));
            lastLabel.setIcon(ii);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            jailLabel.setIcon(null);
            fineLabel.setIcon(null);

            jailText = "Inchisoare: ";
            fineText = "Amenda: ";
        }

        jailLabel.setText(jailText+value.getMinJail()+" - "+value.getMaxJail());
        fineLabel.setText(fineText+value.getMinFine()+" - "+value.getMaxFine());
        lastLabel.setText(value.getDate());

        add(descLabel);
        add(jailLabel);
        add(fineLabel);
        add(lastLabel);

        return this;
    }
}
