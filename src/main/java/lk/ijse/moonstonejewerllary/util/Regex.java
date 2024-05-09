package lk.ijse.moonstonejewerllary.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.Parent;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.moonstonejewerllary.util.TextField.*;

public class Regex {
    /*public static boolean isTextFiledValid(JFXTextField textField, String text) {
        String field = "";

        switch (textField) {
            case ID:
                field = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                field = "^[A-z|\\s]{3,}$";
                break;
            case TEL:
                field = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case ADDRESS:
                field = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;

        }

        Pattern pattern = Pattern.compile(field);


        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean setTextColor(JFXTextField location, JFXTextField field){
        if (Regex.isTextFiledValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("Green"));
            field.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("Red"));
            field.setUnFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }*/
}
