package com.textEditor.Decorator_Design_Pattern;
import com.textEditor.textEditorGUI;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class StyleUtilTest {

    @org.junit.jupiter.api.Test
    void setColor() {

        DefaultTheme d = new DefaultTheme(textEditorGUI.getTextEditorGUI());
        ColorDecorator colorDecorator = new ColorDecorator(d);
        String nameExpected = "Red";

        assertEquals(colorDecorator.setStyle(nameExpected).textArea.getForeground(), Color.RED, "Color is not red." );
    }

    @org.junit.jupiter.api.Test
    void setFontFamily() {

        DefaultTheme d = new DefaultTheme(textEditorGUI.getTextEditorGUI());
        FontFamilyDecorator fontFamilyDecorator = new FontFamilyDecorator(d);

        String fontNameExpected = "Arial";

        assertEquals(fontFamilyDecorator.setStyle("Arial").textArea.getFont().getFamily(),fontNameExpected , "Font is not Arial." );
    }

    @org.junit.jupiter.api.Test
    void setFontSize() {

        DefaultTheme d = new DefaultTheme(textEditorGUI.getTextEditorGUI());
        FontSizeDecorator fontSizeDecorator = new FontSizeDecorator(d);

        int fontSizeExpected = 28;

        assertEquals(fontSizeDecorator.setStyle("28").textArea.getFont().getSize() ,fontSizeExpected , "Font size is not 28." );
    }
}