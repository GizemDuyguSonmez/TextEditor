package com.textEditor.Factory_Design_Pattern;
import com.textEditor.textEditorGUI;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


/*
    Format arayüzünden implements yardimiyla bir sinif olusturuldu.
    Bu sinifta Format arayüzünde olusturulan execute metodu uygun isleme göre
    kodlandi.
*/

public class Find implements Format {

    int currentPosition = 0; // Guncel pozisyonu tutmak icin degisken


    @Override
    public void execute(textEditorGUI gui) {
        String findFromText = gui.textArea.getText(); // Texte yazilan veri alindi
        String toFindText = gui.FindTextField.getText(); // Find kismina yazilan kelime alindi.

        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            int indexOf = findFromText.indexOf(toFindText,currentPosition); // Aranan kelimenin textin neresinde oldugu bulundu.
            int length = toFindText.length(); // Aranmak istenen kelime uzunlugu
            Highlighter h = gui.textArea.getHighlighter(); // Highlighter sinifindan nesne olusturma
            h.removeAllHighlights();
            try {
                // Aranan kelime text area icinde bulunursa sari renge boyandi.
                h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            } catch (BadLocationException ex) {
                JOptionPane.showMessageDialog(null, "Word Not Found.Try again.");
            }

            currentPosition = indexOf+length; // guncel pozisyon guncellendi

            // Guncel pozisyon sifirlama islemleri
            if(currentPosition >= findFromText.length())currentPosition = 0; // Eger guncel pozisyon aranan kelimenin uzunlugundan buyuk ya da esitse
            if(findFromText.indexOf(toFindText, currentPosition) == -1) // Aranan kelime text icinde bulunamiyorsa
                currentPosition = 0 ;
        }


    }
}