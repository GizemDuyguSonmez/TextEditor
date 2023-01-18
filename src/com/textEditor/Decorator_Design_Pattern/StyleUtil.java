package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;
import java.awt.Font;
import java.awt.*;

public class StyleUtil {

    public static textEditorGUI setColor(textEditorGUI gui, String name){
        switch (name){
            case "Black":
                gui.textArea.setForeground(Color.BLACK);
                gui.textArea.setBackground(Color.WHITE);
                break;
            case "White":
                gui.textArea.setForeground(Color.WHITE);
                gui.textArea.setBackground(Color.BLACK);
                break;
            case "Green":
                gui.textArea.setForeground(Color.GREEN);
                gui.textArea.setBackground(Color.WHITE);
                break;
            case "Blue":
                gui.textArea.setForeground(Color.BLUE);
                gui.textArea.setBackground(Color.WHITE);
                break;
            case "Red":
                gui.textArea.setForeground(Color.RED);
                gui.textArea.setBackground(Color.WHITE);

                break;
        }
        return gui;
    }

    public static textEditorGUI setFontFamily(textEditorGUI gui, String name) {
        Font fontFamilyName = null;
        switch (name){
            case "Arial":
                fontFamilyName = new Font("Arial", Font.PLAIN, gui.textArea.getFont().getSize());
                break;

            case "Comic Sans MS":
                fontFamilyName = new Font("Comic Sans MS", Font.PLAIN, gui.textArea.getFont().getSize());

                break;
            case "Times New Roman":
                fontFamilyName = new Font("Times New Roman", Font.PLAIN, gui.textArea.getFont().getSize());

                break;
        }

        gui.textArea.setFont(fontFamilyName);
        return gui;
    }
    public static textEditorGUI setFontSize(textEditorGUI gui, String name){
        Font font = gui.textArea.getFont();

        switch (name){
            case "8":
                gui.textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 8));
                break;
            case "16":
                gui.textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 16));
                break;
            case "20":
                gui.textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 20));
                break;
            case "24":
                gui.textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 24));
                break;
            case "28":
                gui.textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 28));
                break;
        }


        return gui;
    }
}
