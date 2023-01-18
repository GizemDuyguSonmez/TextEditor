package com.textEditor.Command_Design_Pattern;
import java.util.Stack;
import com.textEditor.textEditorGUI;

// Undo icin gerekli olan pop ve push metotlarinin kodlarinin yazildigi sinif
public class UndoFunction {

    textEditorGUI gui; // GUI yapisina Edit_Methods sinifinda kullanabilmek icin yapilan degisken
    Stack<String> undoActions = new Stack<>(); // Undo metodu icin String tipinde stack

    // Constructor
    public UndoFunction(textEditorGUI gui) {
        this.gui = gui;
    }

    // Stack pop islemi yaparak değerleri geri alan metot
    public void PopUndoStack(){

        // Veri kalmadıysa undo işlemi boş döndürür
        if(undoActions.size() < 1){
            return;
        }
        gui.textArea.setText(undoActions.pop());
    }


    // Key Pressed islemine baglanan metot
    public void PushUndoStack(){
        undoActions.push(gui.textArea.getText());
    }


}