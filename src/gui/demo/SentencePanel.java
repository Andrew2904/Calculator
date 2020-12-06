package gui.demo;

import entity.Sentence;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SentencePanel extends JPanel {
    private JLabel titleLabel, jailLabel, fineLabel;
    private String title;
    private JPanel rootPanel;

    public SentencePanel(String title){
        this.title = title;
        int t = 5, fs=20;

        add(rootPanel);
        setVisible(false);

        /*
        Border mainBorder, paddingBorder;
        mainBorder = BorderFactory.createLineBorder(Color.black, t);
        paddingBorder = BorderFactory.createCompoundBorder(mainBorder, new EmptyBorder(t, t, t, t));
        setBorder(paddingBorder);
         */

        Font font = new Font(titleLabel.getName(), Font.PLAIN, fs);
        titleLabel.setFont(font);
        jailLabel.setFont(font);
        fineLabel.setFont(font);
    }

    public void showSentence(Sentence sentence){
        titleLabel.setText(title);
        jailLabel.setText("Pedeapsa cu închisoare: "+ buildJailSentence( Sentence.getYear( sentence.getMinJail() ), Sentence.getMonth( sentence.getMinJail() ), Sentence.getDay( sentence.getMinJail() ))+" - "+buildJailSentence( Sentence.getYear( sentence.getMaxJail() ), Sentence.getMonth( sentence.getMaxJail() ), Sentence.getDay( sentence.getMaxJail() )) );
/*
        maxJailYearLabel.setText( Sentence.getYear( sentence.getMaxJail() )+"" );
        maxJailMonthLabel.setText( Sentence.getMonth( sentence.getMaxJail() )+"" );
        maxJailDayLabel.setText( Sentence.getDay( sentence.getMaxJail() )+"" );
*/
        fineLabel.setText( "Pedeapsa cu amendă: "+sentence.getMinFine()+" - "+sentence.getMaxFine()+" zile amendă" );
    }

    public void setTitle(String title){
        this.title = title;
    }

    private String buildJailSentence(int years, int months, int days){
        String jailSentence = "";

        jailSentence = String.format("%2d Ani, %2d Luni, %2d Zile", years, months, days);

        return jailSentence;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        rootPanel.setVisible(aFlag);
    }
}
