package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;

public class DefaultTheme implements TextEditorStyle{

    private textEditorGUI gui;

    public DefaultTheme (textEditorGUI gui){
        this.setGui(gui);
    }

    @Override
    public textEditorGUI setStyle(String name) {
        return getGui();
    }

    public textEditorGUI getGui() {
        return gui;
    }

    public void setGui(textEditorGUI gui) {
        this.gui = gui;
    }
}
