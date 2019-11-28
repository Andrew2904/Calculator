package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TO DO: deschis fisier recent la initializare
//TO DO: creat fisier la inchiderea programului
//TO DO: completat fereastra pentru user
//TO DO: instantiat lista de fapte+import
public class Calculator extends JFrame implements ActionListener {
    public Calculator(){
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS) );

        JPanel setupPanel, searchPanel, jailPanel, finePanel;
        setupPanel = new JPanel();
        setupPanel.setLayout( new BoxLayout(setupPanel, BoxLayout.X_AXIS) );
        searchPanel = new Search();

        setupPanel.add(searchPanel);
        content.add(setupPanel);

        JPanel sentencePanel, mainPanel, pastPanel, finalPanel;
        sentencePanel = new JPanel();
        sentencePanel.setLayout( new BoxLayout(sentencePanel, BoxLayout.Y_AXIS) );

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.RED);

        sentencePanel.add(mainPanel);
        content.add(sentencePanel);
    }

    public static void main(String args[]){
        Calculator frame = new Calculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setSize(400,400);
        frame.setLocation(200,200);

        frame.setJMenuBar(frame.getMenu());

        frame.setVisible(true);
    }

    public JMenuBar getMenu(){
        JMenuBar mb = new JMenuBar();
        JMenu fileItem, dataItem;
        JMenuItem importItem, exportItem, quitItem, helpItem;
        fileItem = new JMenu("Fisier");
        quitItem = new JMenuItem("Iesire");
        quitItem.addActionListener(this);
        fileItem.add(quitItem);
        mb.add(fileItem);
        dataItem = new JMenu("Lista");
        mb.add(dataItem);
        importItem = new JMenuItem("Import...");
        importItem.addActionListener(this);
        dataItem.add(importItem);
        exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(this);
        dataItem.add(exportItem);
        helpItem = new JMenuItem("Ajutor");
        helpItem.addActionListener(this);
        mb.add(helpItem);

        return mb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JMenuItem source = (JMenuItem) e.getSource();
            String text = source.getText();
            if(text.equals("Ajutor"))
                JOptionPane.showMessageDialog(this, "Placeholder pentru ajutor", "Ajutor", JOptionPane.INFORMATION_MESSAGE);
            else
            if(text.equals("Iesire"))
                System.exit(0);
            else
            if(text.equals("Import..."))
                ;//ImportDialog.showDialog(this, "Alegere fisier");
            else
            if(text.equals("Export..."))
                ExportDialog.showDialog();
            else
                return;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
