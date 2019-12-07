package pl.swiderski.app.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import pl.swiderski.app.util.AlertMaking;
import pl.swiderski.app.util.LoginUser;
import pl.swiderski.app.util.RadioButtonProperty;
import pl.swiderski.dao.CartDao;
import pl.swiderski.dao.ProductDao;
import pl.swiderski.model.Cart;
import pl.swiderski.model.CategoryEnum;
import pl.swiderski.model.Product;
import pl.swiderski.model.User;
import pl.swiderski.app.util.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductListScreenController {


    private ProductDao productDao = new ProductDao();
    private List<RadioButton> radioButtons;
    private List<Product> productList = new ArrayList<>();
    AlertMaking alertMaking = new AlertMaking();


    @FXML
    AnchorPane root;
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
    TextField filterMinPrice;
    @FXML
    TextField filterMaxPrice;
    @FXML
    TextField findByText;
    @FXML
    TextField loginAccount;
    @FXML
    TextField productQuantityToCart;
    @FXML
    VBox vboxWithCateogry;
    @FXML
    ImageView userLogoutButton;
    @FXML
    ImageView userAccountSettingsButton;


    @FXML
    public void initialize() {
        setColumnProperties();
        setProductItemsToTable(productDao.findAll());
        loginAccount.setText(LoginUser.getUser().getLogin());
        vboxWithCateogry.getChildren().addAll(initializeCategoriesFilters());
        setEventLogout();
        setEventAccountSettings();

    }


    @FXML
    private void buttonCategoryFilter() {
        List<String> selectedCategory = getSelectedCategory();
        if (isCategoriesSeleccted(selectedCategory.size(), 0)) {
            setProductItemsToTable(productDao.findByCategory(selectedCategory));
        } else {
            alertMaking.showErrorAlert("Zaznacz którąś z kategorii!!", "Blad");
        }
    }


    @FXML
    private void buttonPriceFilter() {
        double min = getMinValueFilter();
        double max = getMaxValueFilter();

        if (isValidRageValue(min, max)) {
            alertMaking.showErrorAlert("Wartosc min > max!!!", "");
        } else {
            setProductItemsToTable(productDao.findByPriceLimit(min, max));
        }
    }


    @FXML
    public void buttonSearchByName() {
        String text = findByText.getText();
        List<Product> productList = productDao.findByName(text);
        setProductItemsToTable(productList);
    }


    @FXML
    public void buttonAddSelectedToCart() {
        try {
            int quantity = Integer.parseInt(productQuantityToCart.getText());
            Product selectedItem = getSelectedItem();
            if (isEnoughProduct(quantity, selectedItem)) {
                addProductToCartList(quantity, selectedItem);
            } else {
                alertMaking.showErrorAlert("Nie ma wystarczającej ilosci produktu!", "Error");
            }
        } catch (NumberFormatException numberFormat) {
            alertMaking.showInfoAlert("Podaj ilość produktu!!!");
        } catch (NullPointerException nullPointer) {
            alertMaking.showInfoAlert("Zaznacz produkt!!!");
        }
    }


    @FXML
    public void buttonMakeCart() {
        Cart cart = new Cart(LoginUser.getUser(), productList);
        new CartDao().createNew(cart);
        clearTableCart();
    }


    @FXML
    public void buttonProductManager() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ProductManager.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        openNewDialog(root);
    }


    @FXML
    public void buttonAccountSettings() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/accountSettings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        openNewDialog(root);
    }


    @FXML
    public void buttonMyCarts() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/myCarts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        openNewDialog(root);
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


    private void clearTableCart() {
        productList = new ArrayList<>();
        setProductItemsToTableCart(productList);
    }


    private void addProductToCartList(int quantity, Product selectedItem) {
        selectedItem.setQuantity(quantity);
        productList.add(selectedItem);
        productDao.decrementQuantity(selectedItem.getID(), quantity);
        setProductItemsToTableCart(productList);
        setProductItemsToTable(productDao.findAll());
    }


    private void setProductItemsToTableCart(List<Product> products) {
        tableViewCart.getItems().clear();
        tableViewCart.getItems().setAll(products);
    }


    private List<String> getSelectedCategory() {

        List<String> list = new ArrayList<>();
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                list.add(radioButton.getText());
            }
        }
        return list;
    }


    private boolean isCategoriesSeleccted(double size, double i) {
        return size > i;
    }


    private double getMinValueFilter() {
        try {
            return Double.parseDouble(filterMinPrice.getText());
        } catch (Exception e) {
            alertMaking.showErrorAlert("Wartosc min musi byc liczba", "Blad");
            // e.printStackTrace();
        }
        return Double.NaN;
    }


    private double getMaxValueFilter() {
        try {
            return Double.parseDouble(filterMaxPrice.getText());
        } catch (Exception e) {
            alertMaking.showErrorAlert("Wartosc max musi byc liczba", "Blad");
            //e.printStackTrace();
        }
        return Double.NaN;
    }


    private boolean isEnoughProduct(int quantity, Product selectedItem) {
        return selectedItem.getQuantity() - quantity >= 0;
    }


    private boolean isValidRageValue(double min, double max) {
        return isCategoriesSeleccted(min, max) || Double.isNaN(min) || Double.isNaN(max);
    }


    private Product getSelectedItem() {
        return tableView.getSelectionModel().getSelectedItem();

    }


    private List<RadioButton> initializeCategoriesFilters() {

        radioButtons = new ArrayList<>();
        for (String e : CategoryEnum.getCategoryList()) {
            radioButtons.add(new RadioButtonProperty(e).getButton());
        }
        return radioButtons;
    }


    private void setEventAccountSettings() {
        userAccountSettingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonAccountSettings();
            }
        });
    }


    private void setEventLogout() {
        userLogoutButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LoginUser.logoutUser();
                try {
                    new ScreenChanger().change(getClass().getResource("/fxml/loginScreen.fxml"), root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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


    private void setProductItemsToTable(List<Product> products) {
        tableView.getItems().clear();
        tableView.getItems().setAll(products);
    }



}
