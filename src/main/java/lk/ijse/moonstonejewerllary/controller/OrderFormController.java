package lk.ijse.moonstonejewerllary.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.moonstonejewerllary.model.Customer;
import lk.ijse.moonstonejewerllary.model.Item;
import lk.ijse.moonstonejewerllary.repository.CustomerRepo;
import lk.ijse.moonstonejewerllary.repository.ItemRepo;
import lk.ijse.moonstonejewerllary.repository.OrderRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDeleteItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private TextField txtUnitPrice;

    OrderRepo orderRepo = new OrderRepo();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtOrderId.setText(orderRepo.generateNextOrderId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCustomerId();
        getItemCode();
    }

    private void getItemCode() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        try {
            List<String>itemList = ItemRepo.getItemCodes();
            for(String code : itemList){
                itemCodeList.add(code);
            }
            cmbItemCode.setItems(itemCodeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> customerIdList = FXCollections.observableArrayList();

        try {
            List<String>idList = OrderRepo.getCustomerIds();
            for(String id : idList){
                customerIdList.add(id);
            }
            cmbCustomerId.setItems(customerIdList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        String itemName = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int Qty = Integer.parseInt(txtQty.getText());
        double totalAmount = unitPrice*Qty;
        JFXButton btnDelete = new JFXButton("Remove");
        btnDelete.setCursor(Cursor.HAND);


    }

    @FXML
    void btnOrderPlaceOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerId = cmbCustomerId.getValue();

        try {
            Customer customer = CustomerRepo.searchByCustomerId(customerId);
            lblCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();

        try {
            Item item = ItemRepo.searchById(itemCode);
            txtItemName.setText(item.getItemName());
            txtUnitPrice.setText(String.valueOf(item.getPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQty()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblNetTotalOnAction(MouseEvent event) {

    }

    @FXML
    void txtItemSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtOrderIdSerachOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

}
