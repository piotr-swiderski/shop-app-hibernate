package pl.swiderski.app;

import javafx.scene.control.Alert;

public class AlertMaking {

    public void show(String errorInfo, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorInfo);
        alert.setHeaderText("Błąd !!!");
        alert.setTitle(title);
        alert.showAndWait();
    }


}
