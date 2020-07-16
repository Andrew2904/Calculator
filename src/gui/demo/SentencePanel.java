package gui.demo;

import entity.Sentence;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SentencePanel extends JPanel {
    private JLabel titleLabel, fineLabel;
    private JLabel minJailYearLabel, minJailMonthLabel, minJailDayLabel;
    private JLabel maxJailYearLabel, maxJailMonthLabel, maxJailDayLabel;
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

        JLabel tempLabel;

        JPanel jailPanel = new JPanel();
        jailPanel.setLayout(new BoxLayout(jailPanel, BoxLayout.X_AXIS));
        tempLabel = new JLabel("Pedeapsa cu închisoare: ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);
        minJailYearLabel = new JLabel();
        minJailYearLabel.setFont(font);
        jailPanel.add(minJailYearLabel);
        tempLabel = new JLabel(" Ani ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);
        minJailMonthLabel = new JLabel();
        minJailMonthLabel.setFont(font);
        jailPanel.add(minJailMonthLabel);
        tempLabel = new JLabel(" Luni ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);
        minJailDayLabel = new JLabel();
        minJailDayLabel.setFont(font);
        jailPanel.add(minJailDayLabel);
        tempLabel = new JLabel(" Zile ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);

        tempLabel = new JLabel(" - ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);

        maxJailYearLabel = new JLabel();
        maxJailYearLabel.setFont(font);
        jailPanel.add(maxJailYearLabel);
        tempLabel = new JLabel(" Ani ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);
        maxJailMonthLabel = new JLabel();
        maxJailMonthLabel.setFont(font);
        jailPanel.add(maxJailMonthLabel);
        tempLabel = new JLabel(" Luni ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);
        maxJailDayLabel = new JLabel();
        maxJailDayLabel.setFont(font);
        jailPanel.add(maxJailDayLabel);
        tempLabel = new JLabel(" Zile ");
        tempLabel.setFont(font);
        jailPanel.add(tempLabel);

        fineLabel = new JLabel();
        fineLabel.setFont(font);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titleLabel);
        add(jailPanel);
        add(fineLabel);
    }

    public void showSentence(Sentence sentence){
        titleLabel.setText(title);
        minJailYearLabel.setText( Sentence.getYear( sentence.getMinJail() )+"" );
        minJailMonthLabel.setText( Sentence.getMonth( sentence.getMinJail() )+"" );
        minJailDayLabel.setText( Sentence.getDay( sentence.getMinJail() )+"" );

        maxJailYearLabel.setText( Sentence.getYear( sentence.getMaxJail() )+"" );
        maxJailMonthLabel.setText( Sentence.getMonth( sentence.getMaxJail() )+"" );
        maxJailDayLabel.setText( Sentence.getDay( sentence.getMaxJail() )+"" );

        fineLabel.setText( "Pedeapsa cu amendă: "+sentence.getMinFine()+" - "+sentence.getMaxFine()+" zile amendă" );
    }

    public void setTitle(String title){
        this.title = title;
    }
}
