package pl.swiderski.app.controllers;

import javafx.scene.control.RadioButton;
import javafx.scene.effect.Blend;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioButtonProperty {


    RadioButton radioButton = new RadioButton();

    public RadioButtonProperty(String text) {
        radioButton = new RadioButton(text);
    }

    public RadioButton getButton(){
        radioButton.setTextFill(Color.WHITE);
        radioButton.setFont(Font.font(14));
        radioButton.setEffect(new Blend());
        return radioButton;
    }

}
