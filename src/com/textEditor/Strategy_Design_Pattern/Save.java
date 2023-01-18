package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;


// Arayuzden implement edildi.
// Execute metodu gerekli islem sekline gore kodlandi.
public class Save implements FileMethodsInterface{

    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;


    @Override
    public void execute(textEditorGUI gui) {
        // Kaydedilmis bir dosya yoksa sadece saveAs metodu cagrildi.
        filename = gui.window.getTitle();
        fileAddress = gui.getFileAddress();

        if(filename == "Notepad"){
            SaveAs a = new SaveAs();
            a.execute(gui);
        }
        // Aksi kosulda yeni bir kay±t olusturur gibi saveAs metotundaki gibi FileWriter yapisi kullanildi.
        else {
            try {
                FileWriter fw = new FileWriter(fileAddress + filename);
                fw.write(gui.textArea.getText());
                gui.setTitle(filename);
                fw.close();
                textString = gui.textArea.getText();
                JOptionPane.showMessageDialog(gui, "The changes saved.");
            } catch (IOException e) {
                System.out.println("ERROR");
            }
        }

    }
}
