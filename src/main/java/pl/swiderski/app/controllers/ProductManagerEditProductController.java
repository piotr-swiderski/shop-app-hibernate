package pl.swiderski.app.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.CategoryEnum;
import pl.swiderski.model.Product;

import java.util.List;

public class ProductManagerEditProductController {

    private ProductDao productDao = new ProductDao();
    private Product selectedProduct = new Product();

    @FXML
    private JFXTextField editProductID;

    @FXML
    private JFXTextField editProductName;

    @FXML
    private JFXTextField editProductSerial;

    @FXML
    private JFXComboBox<String> editProductCategory;

    @FXML
    private JFXTextField editProductQuantity;

    @FXML
    private JFXTextField editProductPrice;

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


    public void initialize() {
        setColumnProperties();
        setAllProductsToTable();
        editProductCategory.getItems().setAll(CategoryEnum.getCategoryList());
        makeSelectedEvent();
    }

    @FXML
    void cancelEditButton() {
        setValueToProductInGUI(new Product());
    }


    @FXML
    void saveEditButton() {
        productDao.edit(getEditProduct());
        setProductItemsToTable(productDao.findAll());
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


    private void setValueToProductInGUI(Product product) {
        editProductID.setText(String.valueOf(product.getID()));
        editProductName.setText(product.getName());
        editProductSerial.setText(product.getSerial());
        editProductCategory.getSelectionModel().select(CategoryEnum.getIndexOfCategory(product.getCategory()));
        editProductQuantity.setText(String.valueOf(product.getQuantity()));
        editProductPrice.setText(String.valueOf(product.getPrice()));
    }


    private Product getEditProduct() {
        return new Product(
                Integer.parseInt(editProductID.getText()),
                editProductName.getText(),
                editProductSerial.getText(),
                editProductCategory.getSelectionModel().getSelectedItem(),
                Integer.parseInt(editProductQuantity.getText()),
                Double.parseDouble(editProductPrice.getText()));

    }


    private void makeSelectedEvent() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                setValueToProductInGUI(selectedProduct);
            }
        });
    }


    private void setAllProductsToTable() {
        setProductItemsToTable(productDao.findAll());
    }

}
