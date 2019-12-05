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

import java.util.List;

public class ProductManagerAddProductController {

    
    ProductDao productDao = new ProductDao();
    
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

    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(productDao.findAll());
        addProductCategory.getItems().addAll(CategoryEnum.CATEGORY1.getString(),
                CategoryEnum.CATEGORY2.getString(),
                CategoryEnum.CATEGORY3.getString(),
                CategoryEnum.CATEGORY4.getString(),
                CategoryEnum.CATEGORY5.getString());
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

        Product product = new Product(name, serial, category, quantity, price);
        return product;
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
            AlertMaking.get("Zla wartosc ceny!!!");
        }
        return price;
    }

    private int getQuantityFromGui() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(addProductQuantity.getText());
        } catch (Exception e) {
            AlertMaking.get("Pdoales zla ilosc!!!");
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
