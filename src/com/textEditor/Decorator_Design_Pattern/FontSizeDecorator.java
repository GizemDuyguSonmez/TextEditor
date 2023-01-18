package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;

public class FontSizeDecorator extends StyleDecorator{

    public FontSizeDecorator(TextEditorStyle style) {
        super(style);
    }

    @Override
    public textEditorGUI setStyle(String name) {
        textEditorGUI gui = super.setStyle(name);
        textEditorGUI newGui = StyleUtil.setFontSize(gui, name);
        return newGui;
    }
}
