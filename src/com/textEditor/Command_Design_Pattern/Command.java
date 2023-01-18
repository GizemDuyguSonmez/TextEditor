package com.textEditor.Command_Design_Pattern;

public interface Command {

    //Push ve Pop işlemlerini gerçekleştiren metotlar arayüzde oluşturuldu.
    public void executePush();
    public void executePop();

}