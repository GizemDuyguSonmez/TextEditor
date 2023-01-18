package com.textEditor.Factory_Design_Pattern;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import com.textEditor.textEditorGUI;


public class FindAll implements Format {

    int currentPosition = 0;

    @Override
    public void execute(textEditorGUI gui) {
        currentPosition = 0; // Metot cagrildigi zaman guncel pozisyon 0
        String findFromText = gui.textArea.getText(); // Texte yazilan veri alindi
        String toFindText = gui.FindTextField.getText(); // Find kismina yazilan kelime alindi.
        Highlighter h = gui.textArea.getHighlighter(); // Highlighter sinifindan nesne olusturma
        h.removeAllHighlights();

        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            while(findFromText.indexOf(toFindText, currentPosition) != -1){ // Texte yazilan veri icinde aranan kelime bulundugu surece
                int indexOf = findFromText.indexOf(toFindText,currentPosition); // Kelimenin var oldugu yerleri bulma
                int length = toFindText.length(); // Kelimenin uzunlugunu bulma islemi
                try {
                    // bulunan butun kelimeler sari renge boyandi.
                    h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
                } catch (BadLocationException ex) {
                    System.out.println("Word Not Found.");
                }
                currentPosition = indexOf+length ; // guncel pozisyon guncellendi
            }

            // Guncel pozisyon sifirlama islemleri
            if(currentPosition >= findFromText.length()){ // Eger guncel pozisyon aranan kelimenin uzunlugundan buyuk ya da esitse
                currentPosition = 0;
            }
            if(findFromText.indexOf(toFindText, currentPosition) == -1){ // Aranan kelime text icinde bulunamiyorsa
                currentPosition = 0 ;
            }
        }
    }
}
