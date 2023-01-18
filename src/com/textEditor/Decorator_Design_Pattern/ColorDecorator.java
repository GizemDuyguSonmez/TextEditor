package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;

public class ColorDecorator extends StyleDecorator{

    private textEditorGUI gui;

    public ColorDecorator(TextEditorStyle style) {
        super(style);

    }

    @Override
    public textEditorGUI setStyle(String name) {
        textEditorGUI gui = super.setStyle(name);
        textEditorGUI newGui = StyleUtil.setColor(gui, name);
        return newGui;
    }

    public textEditorGUI getGui() {
        return gui;
    }

    public void setGui(textEditorGUI gui) {
        this.gui = gui;
    }
}
