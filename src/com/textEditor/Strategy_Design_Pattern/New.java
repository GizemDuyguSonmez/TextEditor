package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;
import javax.swing.*;

public class New implements FileMethodsInterface{


    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;


    @Override
    public void execute(textEditorGUI gui) {
        filename = gui.window.getTitle();
        textString = gui.getOpenText();

        if(filename == "Notepad")
            textString = null;

        if(textString != null && gui.textArea.getText() != null){
            if (!(textString.equals(gui.textArea.getText()))) {
                int answer = showWarningMessage(gui);
                switch (answer) {
                    case JOptionPane.YES_OPTION:
                        if(filename == null){
                            JOptionPane.showInputDialog(gui,"Enter your file name that you want to save.");
                            SaveAs a = new SaveAs();
                            a.execute(gui);
                            gui.setTitle(filename);
                        }
                        filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
                        gui.textArea.setText("");
                        gui.window.setTitle(filename);

                        SaveAs b = new SaveAs();
                        b.execute(gui);
                        break;
                    case JOptionPane.NO_OPTION:
                        filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
                        gui.textArea.setText("");
                        gui.setTitle(filename);
                        break;

                    case JOptionPane.CANCEL_OPTION:;
                        break;
                }
            }
            else {
                filename = null;
                filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
                gui.window.setTitle(filename);
                gui.textArea.setText("");


            }
        }

    }
    // cikis islemlerinde kullanilacak olan uyari kutusu
    private int showWarningMessage(textEditorGUI gui) {
        String[] buttonLabels = new String[] {"Yes", "No", "Cancel"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;

        return JOptionPane.showOptionDialog(gui,
                "There's still something unsaved.\n" +
                        "Do you want to save before exiting?",
                "Warning",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);
    }
}
