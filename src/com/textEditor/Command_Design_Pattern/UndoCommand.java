package com.textEditor.Command_Design_Pattern;


public class UndoCommand implements Command {
    /*
    Undo islemini gerceklestirecek olan pop ve push metotlarinin yazili
    oldugu class'a üst sinif olusturuldu.
    */

    // UndoFunction sinifindan bir degisken olusturuldu.
    private final UndoFunction undoFunc;

    public UndoCommand(UndoFunction undoFunc) {
        this.undoFunc = undoFunc;
    }



    // Bir interfaceden alinan pop ve push metotlari override edildi.
    @Override
    public void executePush() {
        undoFunc.PushUndoStack();
    }

    @Override
    public void executePop() {
        undoFunc.PopUndoStack();
    }


}