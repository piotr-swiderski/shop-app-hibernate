package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.Product;
import pl.swiderski.model.User;

import java.util.List;

public class ProductListScreenController {


    @FXML
    TableView<Product> tableView;
    @FXML
    TableColumn<User, Integer> colProductId;
    @FXML
    TableColumn<User, String> colProductName;
    @FXML
    TableColumn<User, String> colProductSerial;
    @FXML
    TableColumn<User, String> colProductCategory;
    @FXML
    TableColumn<User, Integer> colProductQuantity;
    @FXML
    TableColumn<User, Double> colProductPrice;


    @FXML
    public void initialize() {
        setColumnProperties();
        loadProductItems();
    }

    private void loadProductItems() {
        List<Product> allProductList = new ProductDao().findAll();
        tableView.getItems().clear();
        tableView.getItems().setAll(allProductList);

    }

    private void setColumnProperties() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    private void clickShowProducts() {

    }


}
