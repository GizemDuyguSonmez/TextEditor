package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;


// Arayuzden implement edildi.
// Execute metodu gerekli islem sekline gore kodlandi.
public class SaveAs implements FileMethodsInterface{

    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;


    @Override
    public void execute(textEditorGUI gui) {
        // Kaydetme islemi icin FileDialog yapisi kullanildi.
        FileDialog fd = new FileDialog(gui,"SaveAs",FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null){
            filename = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(filename);
            gui.setFileAddress(fileAddress);

        }

        try {
            // Kaydetme islemi icin FileWriter yapisi kullanildi.
            FileWriter fw = new FileWriter(fileAddress + filename);
            fw.write(gui.textArea.getText());
            fw.close();
            textString = gui.textArea.getText();
            gui.setOpenText(textString);

        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
