package gui.demo;

import entity.Felony;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FelonyRenderer extends JPanel implements ListCellRenderer{
    int t = 5;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();

        Border mainBorder, paddingBorder;
        if(isSelected)
            mainBorder = BorderFactory.createLineBorder(Color.red, t);
        else
            mainBorder = BorderFactory.createLineBorder(Color.black, t);

        paddingBorder = BorderFactory.createCompoundBorder(mainBorder, new EmptyBorder(t, t, t, t));
        setBorder(paddingBorder);

        String strValue = value.toString();
        String getValue;

        if(strValue.length()>20)
            getValue = String.valueOf(strValue.toCharArray(), 0, 20)+"...";
        else
            getValue = strValue;

        add(new JLabel(getValue));



        return this;
    }
}
