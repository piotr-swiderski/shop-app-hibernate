package pl.swiderski.app;

import javafx.scene.control.Alert;

public  class AlertMaking {

    AlertMaking alertMaking;

    public static void get(String errorInfo){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorInfo);
        alert.setHeaderText("Błąd !!!");
        alert.showAndWait();
    }

    public void show(String errorInfo, String title) {
        AlertMaking alertMaking = new AlertMaking();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorInfo);
        alert.setHeaderText("Błąd !!!");
        alert.setTitle(title);
        alert.showAndWait();
    }


}
