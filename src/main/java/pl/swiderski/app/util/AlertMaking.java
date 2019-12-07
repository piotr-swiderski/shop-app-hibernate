package pl.swiderski.app.util;

import javafx.scene.control.Alert;

public  class AlertMaking {

    public void showErrorAlert(String errorInfo, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorInfo);
        alert.setHeaderText("Błąd !!!");
        alert.setTitle(title);
        alert.showAndWait();
    }

    public void showInfoAlert(String infoText) {
        AlertMaking alertMaking = new AlertMaking();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoText);
        alert.show();
    }


}
