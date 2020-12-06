package gui.demo;

import entity.Sentence;

import javax.swing.*;
import java.awt.*;

public class SentencePanel extends JPanel {
    private JLabel titleLabel, minJailLabel, minFineLabel;
    private String title;
    private JPanel rootPanel;
    private JLabel maxJailLabel;
    private JLabel maxFineLabel;

    public SentencePanel(String title){
        this.title = title;
        int t = 5, fs=20;

        add(rootPanel);
        setVisible(false);

        Font font = new Font(titleLabel.getName(), Font.PLAIN, fs);
        titleLabel.setFont(font);
        minJailLabel.setFont(font);
        minFineLabel.setFont(font);
    }

    public void showSentence(Sentence sentence){
        titleLabel.setText(title);
        minJailLabel.setText(buildJailSentence( Sentence.getYear( sentence.getMinJail() ), Sentence.getMonth( sentence.getMinJail() ), Sentence.getDay( sentence.getMinJail() )) );
        maxJailLabel.setText(buildJailSentence( Sentence.getYear( sentence.getMaxJail() ), Sentence.getMonth( sentence.getMaxJail() ), Sentence.getDay( sentence.getMaxJail() )) );

        minFineLabel.setText( sentence.getMinFine()+"" );
        maxFineLabel.setText( sentence.getMaxFine()+"" );
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
