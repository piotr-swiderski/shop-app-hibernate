package pl.swiderski.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class ScreenChanger {

    public void change(URL resource, AnchorPane root) throws IOException {
        AnchorPane regPane = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().setAll(regPane);
        Window window = root.getScene().getWindow();
        double paddingH = 25;
        double paddingW = 20;
        window.setHeight(regPane.getPrefHeight() + paddingH);
        window.setWidth(regPane.getPrefWidth() + paddingW);

    }

}
