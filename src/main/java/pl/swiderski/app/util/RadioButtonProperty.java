package pl.swiderski.app.util;

import javafx.scene.control.RadioButton;
import javafx.scene.effect.Blend;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioButtonProperty {


    private RadioButton radioButton;


    public RadioButtonProperty(String text) {
        radioButton = new RadioButton(text);
    }


    public RadioButton getButton() {
        radioButton.setTextFill(Color.WHITE);
        radioButton.setFont(Font.font(16));
        radioButton.setEffect(new Blend());
        return radioButton;
    }

}
