package com.textEditor.Command_Design_Pattern;

// Undo islemini uygulamak icin tasarlanan sinif.
// Komutlari cagiran, cagirici sinif olarak düsünülebilir.

import com.textEditor.Command_Design_Pattern.Command;

public class UndoImplement {

    private final Command cmd;

    public UndoImplement(Command cmd) {
        this.cmd = cmd;
    }

    public void push(){
        cmd.executePush();
    }

    public void pop(){
        cmd.executePop();
    }
}