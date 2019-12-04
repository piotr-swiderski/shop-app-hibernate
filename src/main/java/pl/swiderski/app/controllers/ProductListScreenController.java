package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swiderski.app.AlertMaking;
import pl.swiderski.dao.CartDao;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.Cart;
import pl.swiderski.model.CategoryEnum;
import pl.swiderski.model.Product;
import pl.swiderski.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProductListScreenController {


    @FXML
    TableView<Product> tableView;
    @FXML
    TableColumn<User, Integer> colProductID;
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
    TableView<Product> tableViewCart;
    @FXML
    TableColumn<User, Integer> colCartID;
    @FXML
    TableColumn<User, String> colCartName;
    @FXML
    TableColumn<User, String> colCartSerial;
    @FXML
    TableColumn<User, String> colCartCategory;
    @FXML
    TableColumn<User, Integer> colCartQuantity;
    @FXML
    TableColumn<User, Double> colCartPrice;
    @FXML
    RadioButton filterCat1;
    @FXML
    RadioButton filterCat2;
    @FXML
    RadioButton filterCat3;
    @FXML
    RadioButton filterCat4;
    @FXML
    TextField filterMinPrice;
    @FXML
    TextField filterMaxPrice;
    @FXML
    TextField findByText;
    @FXML
    TextField loginAccount;

    private List<Product> productList = new ArrayList<>();

    @FXML
    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(new ProductDao().findAll());
        loginAccount.setText(LoginUser.getUser().getLogin());
    }

    private void setProductItemsToTable(List<Product> products) {
        tableView.getItems().clear();
        tableView.getItems().setAll(products);

    }

    private void setColumnProperties() {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colCartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCartSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colCartCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCartQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    @FXML
    private void buttonCategoryFilter() {
        List<String> selectedCategory = getSelectedCategory();
        if (selectedCategory.size() > 0) {
            setProductItemsToTable(new ProductDao().findByCategory(selectedCategory));
        } else {
            new AlertMaking().show("Zaznacz którąś z kategorii!!", "Blad");
        }
    }

    @FXML
    private void buttonPriceFilter() {
        double min = getMinValueFilter();
        double max = getMaxValueFilter();
        if (min > max || Double.isNaN(min) || Double.isNaN(max)) {
            new AlertMaking().show("Wartosc min > max!!!", "");
        } else {
            setProductItemsToTable(new ProductDao().findByPriceLimit(min, max));
        }

    }

    @FXML
    public void buttonSearchByName() {
        String text = findByText.getText();
        List<Product> productList = new ProductDao().findByName(text);
        setProductItemsToTable(productList);
    }

    @FXML
    public void buttonAddSelected() {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            productList.add(selectedItem);
            setProductItemsToTableCart(productList);
        }
    }

    @FXML
    public void buttonMakeCart() {
        Cart cart = new Cart(LoginUser.getUser(), productList);
        new CartDao().createNew(cart);
    }

    private void setProductItemsToTableCart(List<Product> products) {
        tableViewCart.getItems().clear();
        tableViewCart.getItems().setAll(products);

    }

    public List<String> getSelectedCategory() {
        List<String> list = new ArrayList<>();
        if (filterCat1.isSelected()) {
            list.add(CategoryEnum.CATEGORY1.getCategory());
        }
        if (filterCat2.isSelected()) {
            list.add(CategoryEnum.CATEGORY2.getCategory());
        }
        if (filterCat3.isSelected()) {
            list.add(CategoryEnum.CATEGORY3.getCategory());
        }
        if (filterCat4.isSelected()) {
            list.add(CategoryEnum.CATEGORY4.getCategory());
        }
        return list;
    }


    private double getMinValueFilter() {
        try {
            return Double.parseDouble(filterMinPrice.getText());
        } catch (Exception e) {
            new AlertMaking().show("Wartosc min musi byc liczba", "Blad");
           // e.printStackTrace();
        }
        return Double.NaN;
    }

    private double getMaxValueFilter() {
        try {
            return Double.parseDouble(filterMaxPrice.getText());
        } catch (Exception e) {
            new AlertMaking().show("Wartosc max musi byc liczba", "Blad");
            //e.printStackTrace();
        }
        return Double.NaN;
    }

}
