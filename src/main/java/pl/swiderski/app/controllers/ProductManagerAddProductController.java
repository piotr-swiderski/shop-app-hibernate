package pl.swiderski.app.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.app.util.AlertMaking;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.CategoryEnum;
import pl.swiderski.model.Product;

import java.util.List;

public class ProductManagerAddProductController {


    private ProductDao productDao = new ProductDao();
    private AlertMaking alertMaking = new AlertMaking();

    @FXML
    private JFXTextField addProductName;

    @FXML
    private JFXTextField addProductSerial;

    @FXML
    private JFXComboBox<String> addProductCategory;

    @FXML
    private JFXTextField addProductQuantity;

    @FXML
    private JFXTextField addProdcutPrice;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSerial;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colPrice;


    @FXML
    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(productDao.findAll());
        addProductCategory.getItems().setAll(CategoryEnum.getCategoryList());
    }


    @FXML
    void addProductButton() {
        getProductFromGUI();
        productDao.createNew(getProductFromGUI());
        setProductItemsToTable(productDao.findAll());
        clearProductPanel();
    }


    @FXML
    void clearProductButton() {
        clearProductPanel();
    }


    private void clearProductPanel() {
        addProductName.clear();
        addProductSerial.clear();
        addProductCategory.getSelectionModel().clearSelection();
        addProductQuantity.clear();
        addProdcutPrice.clear();
    }

    private Product getProductFromGUI() {
        String name = addProductName.getText();
        String serial = addProductSerial.getText();
        String category = addProductCategory.getSelectionModel().getSelectedItem();
        int quantity = getQuantityFromGui();
        double price = getPriceFromGui();

        return new Product(name, serial, category, quantity, price);
    }


    private void setProductItemsToTable(List<Product> products) {
        tableView.getItems().clear();
        tableView.getItems().setAll(products);
    }


    private double getPriceFromGui() {
        double price = Double.NaN;
        try {
            price = Double.parseDouble(addProdcutPrice.getText());
        } catch (Exception e) {
            alertMaking.showErrorAlert("Zla wartosc ceny!!!","ERROR");
        }
        return price;
    }


    private int getQuantityFromGui() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(addProductQuantity.getText());
        } catch (Exception e) {
            alertMaking.showErrorAlert("Pdoales zla ilosc!!!","ERROR");
        }
        return quantity;
    }


    private void setColumnProperties() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}
