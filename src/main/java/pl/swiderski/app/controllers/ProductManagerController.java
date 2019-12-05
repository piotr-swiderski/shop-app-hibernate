package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

public class ProductManagerController {

    @FXML
    AnchorPane anchorPaneProductManager;


    public void initialize() {
        AnchorPane anchorPane = getAnchorPane(getClass().getResource("/fxml/addProduct.fxml"));
        anchorPaneProductManager.getChildren().clear();
        anchorPaneProductManager.getChildren().setAll(anchorPane);
    }

    @FXML
    void addProduct() {
        AnchorPane anchorPane = getAnchorPane(getClass().getResource("/fxml/addProduct.fxml"));
        anchorPaneProductManager.getChildren().clear();
        anchorPaneProductManager.getChildren().setAll(anchorPane);
    }

    @FXML
    void deleteProduct() {
        AnchorPane anchorPane = getAnchorPane(getClass().getResource("/fxml/deleteProduct.fxml"));
        anchorPaneProductManager.getChildren().clear();
        anchorPaneProductManager.getChildren().setAll(anchorPane);
    }

    @FXML
    void editProduct() {
        AnchorPane anchorPane = getAnchorPane(getClass().getResource("/fxml/editProduct.fxml"));
        anchorPaneProductManager.getChildren().clear();
        anchorPaneProductManager.getChildren().setAll(anchorPane);
    }



    private AnchorPane getAnchorPane(URL url) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anchorPane;
    }
}
