package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// Arayuzden implement edildi.
// Execute metodu gerekli islem sekline gore kodlandi.
public class Open implements FileMethodsInterface{

    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;

    @Override
    public void execute(textEditorGUI gui) {
        //Dosya acma islemi icin FileDialog Load yapisi kullanildi.
        FileDialog fd = new FileDialog(gui,"Open", FileDialog.LOAD);
        fd.setVisible(true);


        // Dosya adi ve adresi elde edildi.
        if(fd.getFile() != null ){
            filename = fd.getFile();
            gui.window.setTitle(filename);
            gui.setFileAddress( fd.getDirectory());
            fileAddress = gui.getFileAddress();

        }
        try {
            // Acma islemi sirasinda dosyayi yüklemek icin dosya adresi ve dosya adi kullanildi.
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + filename));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null){
                sb.append(line + "\n");
            }
            gui.textArea.setText(sb.toString());
            br.close();

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND.");
        }
        textString = gui.textArea.getText();
        gui.setOpenText(textString);
    }
}

