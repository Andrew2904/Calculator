package gui.demo;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CircumstanceRenderer extends JPanel implements ListCellRenderer{
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

        add(new JLabel(value.toString()));



        return this;
    }
}
