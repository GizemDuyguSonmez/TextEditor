package com.textEditor.Factory_Design_Pattern;
import com.textEditor.textEditorGUI;
import javax.swing.*;


/*
    Format arayüzünden implements yardimiyla bir sinif olusturuldu.
    Bu sinifta Format arayüzünde olusturulan execute metodu uygun isleme göre
    kodlandi.
*/
public class Replace implements Format{

    int currentPosition = 0;

    @Override
    public void execute(textEditorGUI gui) {
        String findFromText = gui.textArea.getText(); // Text'e yazilan veri alindi.
        String toFindText = gui.FindTextField.getText(); // Bulunmak istenilen kelime

        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            String withReplaceText = gui.ReplaceTextField.getText(); // Degistirilmek istenilen kelime
            gui.textArea.setText(findFromText.replaceFirst(toFindText, withReplaceText)); // Kelime degistirme islemi
            currentPosition = 0; // Guncel pozisyon sifirlandi
        }
    }
}
