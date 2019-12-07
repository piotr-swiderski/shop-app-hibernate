package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.Product;

import java.util.List;

public class ProductManagerDeleteProductController {

    private ProductDao productDao = new ProductDao();

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


    @FXML
    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(productDao.findAll());
    }

    @FXML
    public void deleteSelectedItem() {
        Product selectedItem = getSelectedItem();
        productDao.delete(selectedItem.getID());
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

    private Product getSelectedItem() {
        return tableView.getSelectionModel().getSelectedItem();
    }

}
