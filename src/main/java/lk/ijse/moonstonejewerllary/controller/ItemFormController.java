package lk.ijse.moonstonejewerllary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemFormController {

    @FXML
    private TableColumn<?, ?> colDeleteItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<?> tblItem;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private TextField txtUSerId;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnItemClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    }

}
