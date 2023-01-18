package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;

public abstract class StyleDecorator implements TextEditorStyle {

    private TextEditorStyle style;

    public StyleDecorator(TextEditorStyle style){
        this.style = style;
    }

    @Override
    public textEditorGUI setStyle(String name) {
      return style.setStyle(name);
    }
}
