package pl.swiderski.app.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.app.AlertMaking;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.CategoryEnum;
import pl.swiderski.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ProductManagerEditProductController {

    ProductDao productDao = new ProductDao();

    @FXML
    private JFXTextField addProductName;

    @FXML
    private JFXTextField addProductSerial;

    @FXML
    private JFXComboBox<?> addProductCategory;

    @FXML
    private JFXTextField addProductQuantity;

    @FXML
    private JFXTextField addProdcutPrice;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> colID;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, String> colSerial;

    @FXML
    private TableColumn<Product, String> colCategory;

    @FXML
    private TableColumn<Product, Integer> colQuantity;

    @FXML
    private TableColumn<Product, Double> colPrice;

    Product selectedProduct;


    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(productDao.findAll());
        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            selectedProduct = tableView.getSelectionModel().getSelectedItem();
            setValueToProductInUI(selectedProduct);
        });
    }


    @FXML
    void cancelEditButton() {
        setValueToProductInUI(new Product());
    }

    @FXML
    void saveEditButton() {

    }


    private void setColumnProperties() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void setProductItemsToTable(List<Product> products) {
        tableView.getItems().clear();
        tableView.getItems().setAll(products);
    }

    private void setValueToProductInUI(Product product) {
        addProductName.setText(product.getName());
        addProductSerial.setText(product.getSerial());
        addProductCategory.getSelectionModel().clearSelection();
        addProductQuantity.setText(String.valueOf(product.getQuantity()));
        addProdcutPrice.setText(String.valueOf(product.getPrice()));
    }

}
