package com.textEditor.Factory_Design_Pattern;
import com.textEditor.Factory_Design_Pattern.Format;

public class Factory {

       /* Factory sinifinda yapilacak olan islem secildi. Ve secilen isleme uygun
        islemler yapildi.*/

        public Format getFormat(String shapeType){
            if(shapeType == null){
                return null;
            }
            if(shapeType.equalsIgnoreCase("find")){
                return new Find();
            } else if (shapeType.equalsIgnoreCase("replace")){
                return new Replace();
            } else if(shapeType.equals("find all")){
                return new FindAll();
            } else if (shapeType.equals("replace all")){
                return new ReplaceAll();
            }
            return null;
        }
}
