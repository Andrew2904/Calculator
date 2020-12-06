package gui.demo;

import entity.Sentence;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SentencePanel extends JPanel {
    private JLabel titleLabel, jailLabel, fineLabel;
    private String title;

    public SentencePanel(String title){
        this.title = title;
        int t = 5, fs=20;

        /*
        Border mainBorder, paddingBorder;
        mainBorder = BorderFactory.createLineBorder(Color.black, t);
        paddingBorder = BorderFactory.createCompoundBorder(mainBorder, new EmptyBorder(t, t, t, t));
        setBorder(paddingBorder);
         */

        titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getName(), Font.PLAIN, fs);
        titleLabel.setFont(font);

        jailLabel = new JLabel();
        jailLabel.setFont(font);

        fineLabel = new JLabel();
        fineLabel.setFont(font);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titleLabel);
        add(jailLabel);
        add(fineLabel);
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

        jailSentence = String.format("%4d Ani, %2d Luni, %2d Zile", years, months, days);

        return jailSentence;
    }
}
