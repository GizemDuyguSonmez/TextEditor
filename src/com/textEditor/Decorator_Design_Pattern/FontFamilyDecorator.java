package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;

public class FontFamilyDecorator extends StyleDecorator {

    public FontFamilyDecorator(TextEditorStyle style) {
        super(style);
    }

    @Override
    public textEditorGUI setStyle(String name) {
        textEditorGUI gui = super.setStyle(name);
        textEditorGUI newGui = StyleUtil.setFontFamily(gui, name);
        return newGui;
    }

}
