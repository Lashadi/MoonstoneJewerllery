package lk.ijse.moonstonejewerllary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.moonstonejewerllary.repository.OrderRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbItemCode;

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
    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderPlaceOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {

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

    }

}
