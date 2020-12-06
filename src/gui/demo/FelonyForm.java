package gui.demo;

import entity.Count;
import entity.Sentence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FelonyForm extends JPanel implements FocusListener {
    private JTextField descField;
    private JTextField minJailYearField, minJailMonthField, minJailDayField;
    private JTextField maxJailYearField, maxJailMonthField, maxJailDayField;
    private JTextField minFineField, maxFineField;
    private JPanel circumstancePanel;
    private JPanel sentencePanel;
    private JPanel rootPanel;

    private Count currentCount;

    public FelonyForm(){
        add(rootPanel);
        setVisible(false);

        sentencePanel = new SentencePanel("Sentința curentă");

        descField.addFocusListener(this);

        minJailYearField.addFocusListener(this);
        minJailMonthField.addFocusListener(this);
        minJailDayField.addFocusListener(this);

        maxJailYearField.addFocusListener(this);
        maxJailMonthField.addFocusListener(this);
        maxJailDayField.addFocusListener(this);

        minFineField.addFocusListener(this);
        maxFineField.addFocusListener(this);
    }

    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }

    public void showCount(Count f){
        currentCount = f;

        descField.setText( currentCount.getDesc() );

        minJailYearField.setText( Sentence.getYear( currentCount.getBase().getMinJail() )+"" );
        minJailMonthField.setText( Sentence.getMonth( currentCount.getBase().getMinJail() )+"" );
        minJailDayField.setText( Sentence.getDay( currentCount.getBase().getMinJail() )+"" );

        maxJailYearField.setText( Sentence.getYear( currentCount.getBase().getMaxJail() )+"" );
        maxJailMonthField.setText( Sentence.getMonth( currentCount.getBase().getMaxJail() )+"" );
        maxJailDayField.setText( Sentence.getDay( currentCount.getBase().getMaxJail() )+"" );

        minFineField.setText( currentCount.getBase().getMinFine()+"" );
        maxFineField.setText( currentCount.getBase().getMaxFine()+"" );
    }

    public Count read(){
        Count added = new Count();
        added.setDesc( descField.getText() );
        Sentence read = new Sentence();
        int minJail = Sentence.getDays( Integer.parseInt( minJailYearField.getText() ), Integer.parseInt( minJailMonthField.getText() ), Integer.parseInt( minJailDayField.getText() ));
        int maxJail = Sentence.getDays( Integer.parseInt( maxJailYearField.getText() ), Integer.parseInt( maxJailMonthField.getText() ), Integer.parseInt( maxJailDayField.getText() ));
        read.setMinJail( minJail );
        read.setMaxJail( maxJail );
        read.setMinFine( Integer.parseInt( minFineField.getText() ));
        read.setMaxFine( Integer.parseInt( maxFineField.getText() ));
        added.setBase(read);

        //circumstances

        showCount(added);

        return added;
    }

    private void updateSentence(){
        //int index = felonyList.getSelectedIndex();

        Count added = read();
        added.update();

        ( (SentencePanel)sentencePanel ).showSentence( added.getCompound() );
        /*
        added.setCircumstances( cp.getCircumstances() );

        updateFelony(added);

        felonyModel.set( index, added.getDesc());
        felonyRegister.set( index, added);
         */
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        read();

        sentencePanel.setVisible(true);
        sentencePanel.revalidate();

        revalidate();
    }
}
