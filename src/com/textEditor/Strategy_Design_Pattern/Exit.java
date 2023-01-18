package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;
import javax.swing.*;

// Arayuzden implement edildi.
// Execute metodu gerekli islem sekline gore kodlandi.
public class Exit implements FileMethodsInterface {

    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;

    @Override
    public void execute(textEditorGUI gui) {
        filename = gui.window.getTitle();
        if(gui.getOpenText() == null){
            textString = "";
        }else{
            textString = gui.getOpenText();
        }


        if (filename.equals("Notepad") && textString == "") {

            JOptionPane.showMessageDialog(gui, "The file to close was not found.");
             gui.textArea.setText("");
             textString = gui.textArea.getText();
             gui.setOpenText(textString);
            if(gui.getOpenText() == null){
                textString = "";
            }
            gui.window.setTitle("Notepad");
            return;
        }
        if (!(textString.equals(gui.textArea.getText()))) { // değişiklik yapıldıysa
            int answer = showWarningMessage(gui);
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    SaveAs c = new SaveAs();
                    c.execute(gui);

                    break;
                case JOptionPane.NO_OPTION:
                    gui.textArea.setText("");
                    filename = "Notepad";
                    gui.window.setTitle(filename);
                    break;
                case JOptionPane.CANCEL_OPTION:;
                    break;
            }
        }
        else {
            gui.textArea.setText("");
            filename = "Notepad";
            JOptionPane.showMessageDialog(gui, "There is not any changes in the file...");
            gui.window.setTitle(filename);
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
