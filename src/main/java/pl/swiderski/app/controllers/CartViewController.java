package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.dao.CartDao;
import pl.swiderski.model.Product;

import java.util.List;

public class CartViewController {

    CartDao cartDao = new CartDao();

    @FXML
    private TableView<Product> tableViewCart;

    @FXML
    private TableColumn<Product, Integer> colCartID;

    @FXML
    private TableColumn<Product, String> colCartName;

    @FXML
    private TableColumn<Product, String> colCartSerial;

    @FXML
    private TableColumn<Product, String> colCartCategory;

    @FXML
    private TableColumn<Product, Integer> colCartQuantity;

    @FXML
    private TableColumn<Product, Double> colCartPrice;

    public void initialize(){
        setColumnProperties();
        setProductItemsToTableCart();
    }



    private void setColumnProperties() {
        colCartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCartSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colCartCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCartQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void setProductItemsToTableCart() {
        List<Product> products = cartDao.getListOfProductByIdCart(MyCartsController.getSelectedID());
        tableViewCart.getItems().clear();
        tableViewCart.getItems().setAll(products);
    }

}
