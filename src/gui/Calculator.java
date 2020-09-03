package gui;

import data.entity.Felony;
import gui.dialog.ExportDialog;
import gui.dialog.ImportDialog;
import gui.panel.CircumstancePanel;
import gui.panel.CountsPanel;
import gui.panel.CurrentPanel;
import util.ResourceManager;

import javax.swing.*;
import java.awt.event.*;

//TO DO: completat fereastra pentru user
public class Calculator extends JFrame implements ActionListener, MouseListener, WindowListener {
    CountsPanel countPanel;
    CircumstancePanel circumstancePanel;
    CurrentPanel currentPanel;
    JPanel setupPanel;

    private static Calculator instance;

    private Calculator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setSize(400,400);
        setLocation(200,200);
        try {
            setIconImage( ResourceManager.getIconFromResources("icon2.png").getImage() );
        } catch (Exception e) {
            System.err.println("Lipseste icon.ico din resurse");
        }

        setJMenuBar(getMenu());
    }

    private void buildContent(){
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout( new BoxLayout(content, BoxLayout.X_AXIS) );
        this.addWindowListener(this);

        setupPanel = new JPanel();
        setupPanel.setLayout( new BoxLayout(setupPanel, BoxLayout.X_AXIS) );

        countPanel = new CountsPanel();
        countPanel.readData("cdemo.xml");
        setupPanel.add(countPanel);

        currentPanel = new CurrentPanel();
        setupPanel.add(currentPanel);

        circumstancePanel = new CircumstancePanel();
        circumstancePanel.readData("sdemo.xml");
        setupPanel.add(circumstancePanel);

        content.add(setupPanel);
    }

    public static void main(String args[]){
        Calculator frame = Calculator.getInstance();
        frame.buildContent();

        frame.setVisible(true);
    }

    public static Calculator getInstance(){
        if(instance==null)
            instance = new Calculator();

        return instance;
    }

    public JMenuBar getMenu(){
        JMenuBar mb = new JMenuBar();
        JMenu fileItem, dataItem, helpItem;
        JMenuItem importItem, exportItem, quitItem, infoItem;
        fileItem = new JMenu("Fisier");
        quitItem = new JMenuItem("Iesire");
        quitItem.addActionListener(this);
        fileItem.add(quitItem);
        mb.add(fileItem);
        dataItem = new JMenu("Lista");
        mb.add(dataItem);
        importItem = new JMenuItem("Import fapte...");
        importItem.addActionListener(this);
        dataItem.add(importItem);
        exportItem = new JMenuItem("Export fapte...");
        exportItem.addActionListener(this);
        dataItem.add(exportItem);

        dataItem.addSeparator();
        importItem = new JMenuItem("Import circumstanțe...");
        importItem.addActionListener(this);
        dataItem.add(importItem);
        exportItem = new JMenuItem("Export circumstanțe...");
        exportItem.addActionListener(this);
        dataItem.add(exportItem);


        helpItem = new JMenu("Ajutor");
        infoItem = new JMenuItem("Licenta");
        infoItem.addActionListener(this);
        helpItem.add(infoItem);
        mb.add(helpItem);

        return mb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JMenuItem source = (JMenuItem) e.getSource();
            String text = source.getText();
            if(text.equals("Licenta"))
                JOptionPane.showMessageDialog(this, "Placeholder pentru ajutor", "Ajutor", JOptionPane.INFORMATION_MESSAGE);
            else
            if(text.equals("Iesire"))
                System.exit(0);
            else
            if(text.equals("Import fapte...")){
                ImportDialog id = new ImportDialog();
                id.showDialog(this, "Alegere fisier");
                countPanel.readData(id.getFile());
            }
            else
            if(text.equals("Export fapte...")){
                ExportDialog ed = new ExportDialog();
                //ed.showDialog(this, "Export fisier");
                ed.export(countPanel.getList());
            }
            else
                return;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Programul se inchide...");
        //TO DO: export fisier curent
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public void updateCircumstance(Felony base){
        circumstancePanel.updateBase(base);
    }
}
