package lk.ijse.moonstonejewerllary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeFormController {

    @FXML
    private ComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colNicNo;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerNIC;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    @FXML
    private TextField txtSearchCustomer;

    @FXML
    private TableColumn<?, ?> txtUserId;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {

    }

}
