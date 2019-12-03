package pl.swiderski.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ScreenChanger {

    public void change(URL resource, AnchorPane root) throws IOException {
            AnchorPane regPane = FXMLLoader.load(resource);
            root.getChildren().setAll(regPane);

    }

}
