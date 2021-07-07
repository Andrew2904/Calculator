package gui.demo;

import entity.Circumstance;
import entity.Sentence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CircumstanceForm extends JPanel implements ItemListener {
    private JPanel rootPanel;
    private JTextField descField;
    private JCheckBox aggroBox;
    private JCheckBox fixedBox;

    private JTextField minYearJail, minMonthJail, minDayJail;
    private JTextField maxYearJail, maxMonthJail, maxDayJail;
    private JTextField minFine;
    private JTextField maxFine;
    private JPanel cardPanel;

    private JTextField minJailn, minJaild;
    private JTextField maxJailn, maxJaild;
    private JTextField minFinen, minFined;
    private JTextField maxFinen, maxFined;

    Circumstance current;

    public CircumstanceForm(){
        add(rootPanel);
        setVisible(false);

        fixedBox.addItemListener(this);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        rootPanel.setVisible(aFlag);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox src = (JCheckBox) e.getSource();

        if( src.equals(fixedBox) ){
            CardLayout cl = (CardLayout) cardPanel.getLayout();

            if( fixedBox.isSelected() )
                cl.show(cardPanel, "fixed");
            else
                cl.show(cardPanel, "variable");
        }
    }

    public void showCircumstance(Circumstance circumstance){
        current = circumstance;

        descField.setText(current.getDesc());
        aggroBox.setSelected( current.isAggro() );
        fixedBox.setSelected( current.isFixed() );

        if( current.isFixed() ){
            minYearJail.setText( current.minJailn / Sentence.year_day  +"" );
            minMonthJail.setText( current.minJailn % Sentence.year_day / Sentence.month_day +"" );
            minDayJail.setText( current.minJailn % Sentence.month_day +"" );

            maxYearJail.setText( current.maxJailn / Sentence.year_day +"" );
            maxMonthJail.setText( current.maxJailn % Sentence.year_day / Sentence.month_day +"" );
            maxDayJail.setText( current.maxJailn % Sentence.month_day +"" );

            minFine.setText( current.minFinen+"" );
            maxFine.setText( current.maxFinen+"" );
        }
        else{
            minJailn.setText( current.getMinJailn()+"" );
            minJaild.setText( current.getMinJaild()+"" );

            maxJailn.setText( current.getMaxJailn()+"" );
            maxJaild.setText( current.getMaxJaild()+"" );

            minFinen.setText( current.getMinFinen()+"" );
            minFined.setText( current.getMinFined()+"" );

            maxFinen.setText( current.getMaxFinen()+"" );
            maxFined.setText( current.getMaxFined()+"" );
        }
    }

    public Circumstance read(){
        Circumstance added = new Circumstance();

        added.setDesc( descField.getText() );
        added.setAggro( aggroBox.isSelected() );
        added.setFixed( fixedBox.isSelected() );

        if( fixedBox.isSelected() ){
            int year, month, day;
            int fine;

            try {
                year = Integer.parseInt(minYearJail.getText());
                month = Integer.parseInt(minMonthJail.getText());
                day = Integer.parseInt(minDayJail.getText());

                added.setMinJail( year*Sentence.year_day+month*Sentence.month_day+day, 1);
            }catch (Exception ex){
                System.err.println("Inchisoare: Valori minime incorecte");
                return null;
            }

            try {
                year = Integer.parseInt(maxYearJail.getText());
                month = Integer.parseInt(maxMonthJail.getText());
                day = Integer.parseInt(maxDayJail.getText());

                added.setMaxJail( year*Sentence.year_day+month*Sentence.month_day+day, 1);
            }catch (Exception ex){
                System.err.println("Inchisoare: Valori maxime incorecte");
                return null;
            }

            try {
                fine = Integer.parseInt(minFine.getText());

                added.setMinFine( fine, 1);
            }catch (Exception ex){
                System.err.println("Amenda: Valori minime incorecte");
                return null;
            }

            try {
                fine = Integer.parseInt(maxFine.getText());

                added.setMaxFine( fine, 1);
            }catch (Exception ex){
                System.err.println("Amenda: Valori maxime incorecte");
                return null;
            }
        }
        else{
            int num, den;

            try {
                num = Integer.parseInt( minJailn.getText() );
                den = Integer.parseInt( minJaild.getText() );

                added.setMinJail(num, den);
            }catch (Exception ex){
                System.err.println("Inchisoare: Valori minime incorecte");
                return null;
            }

            try {
                num = Integer.parseInt( maxJailn.getText() );
                den = Integer.parseInt( maxJaild.getText() );

                added.setMaxJail(num, den);
            }catch (Exception ex){
                System.err.println("Inchisoare: Valori maxime incorecte");
                return null;
            }

            try {
                num = Integer.parseInt( minFinen.getText() );
                den = Integer.parseInt( minFined.getText() );

                added.setMinFine(num, den);
            }catch (Exception ex){
                System.err.println("Amenda: Valori minime incorecte");
                return null;
            }

            try {
                num = Integer.parseInt( maxFinen.getText() );
                den = Integer.parseInt( maxFined.getText() );

                added.setMaxFine(num, den);
            }catch (Exception ex){
                System.err.println("Amenda: Valori maxime incorecte");
                return null;
            }
        }

        return added;
    }
}
