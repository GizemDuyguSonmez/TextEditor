package com.textEditor.Strategy_Design_Pattern;
import com.textEditor.textEditorGUI;

public class StrategyFileMethods {

    // Arayüz tipinden bir degisken olusturuldu.
    public FileMethodsInterface method;


    // Degiskenin kullanilabilmesi icin bir constructor olusturuldu.
    public StrategyFileMethods(FileMethodsInterface method){
        this.method = method;
    }


    // Bu tasarim deseni icin islem turunu belirleyen execute metodu
    public void execute(textEditorGUI gui){
        method.execute(gui);
    }
}
