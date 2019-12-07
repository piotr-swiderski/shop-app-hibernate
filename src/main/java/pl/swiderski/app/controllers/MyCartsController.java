package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import pl.swiderski.app.util.LoginUser;
import pl.swiderski.dao.CartDao;
import pl.swiderski.model.Cart;
import pl.swiderski.model.Product;
import pl.swiderski.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCartsController {

    private CartDao cartDao = new CartDao();
    private static int selectedID;

    @FXML
    TableView<Cart> tableViewCart;
    @FXML
    TableColumn<Cart, Integer> colCartID;
    @FXML
    TableColumn<Cart, String> colCreatedDate;

    @FXML
    public void initialize() {
        setColumnProperties();
        setProductItemsToTableCart(cartDao.findAllByUserID(LoginUser.getUser().getID()));

    }

    @FXML
    public void openSelectedCart() {
        setSelectedCartID(getSelectedCart().getID());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/cart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        openNewDialog(root);
    }


    private void setProductItemsToTableCart(List<Cart> carts) {
        tableViewCart.getItems().clear();
        tableViewCart.getItems().setAll(carts);
    }

    private void setColumnProperties() {
        colCartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createDateTime"));
    }


    private void setSelectedCartID(int id) {
        selectedID = id;
    }


    private void openNewDialog(Parent root) {
        Dialog<Object> objectDialog = new Dialog<>();
        objectDialog.getDialogPane().setContent(root);
        objectDialog.initStyle(StageStyle.UTILITY);
        objectDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = objectDialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        objectDialog.showAndWait();
    }

    public static int getSelectedID() {
        return selectedID;
    }

    private Cart getSelectedCart() {
        return tableViewCart.getSelectionModel().getSelectedItem();
    }
}
