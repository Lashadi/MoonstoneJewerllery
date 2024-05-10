package lk.ijse.moonstonejewerllary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.moonstonejewerllary.model.Supplier;
import lk.ijse.moonstonejewerllary.model.tm.SupplierTm;
import lk.ijse.moonstonejewerllary.repository.SupplierRepo;
import lk.ijse.moonstonejewerllary.util.DataValidateController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.moonstonejewerllary.controller.CustomerFormController.usr;

public class SupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableColumn<?, ?> colSupplierTel;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtSearchSupplier;

    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierItemName;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtSupplierTel;

    @FXML
    private Label supplierItemNameValidate;

    @FXML
    private Label supplierNameValidate;

    @FXML
    private Label supplierTelValidate;


    private List<Supplier> supplierList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtSupplierId.setText(SupplierRepo.generateSupplierId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.supplierList = getAllSupplier();
        setCellValueFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();

        for (Supplier supplier : supplierList) {
            SupplierTm supplierTm = new SupplierTm(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getItemName(),
                    supplier.getTel()
            );
            tmList.add(supplierTm);

        }
        tblSupplier.setItems(tmList);
        tblSupplier.getSelectionModel().getSelectedItem();
    }

    private List<Supplier> getAllSupplier() {
        List<Supplier>supplierList = null;

        try {
            supplierList = SupplierRepo.getSupplier();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    private void setCellValueFactory() {
       colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colSupplierTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));


    }


    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
        String tel = txtSearchSupplier.getText();

        try {
            Supplier supplier = SupplierRepo.searchByContactNo(tel);
            if(supplier != null) {
                txtSupplierId.setText(supplier.getId());
                txtSupplierName.setText(supplier.getName());
                txtSupplierItemName.setText(supplier.getItemName());
                txtSupplierTel.setText(supplier.getTel());
                btnSearchSupplierOnAction(event);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSupplierClearOnAction(ActionEvent event) {
        txtSupplierName.clear();
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierTel.clear();
        txtSupplierItemName.clear();
    }

    @FXML
    void btnSupplierDeleteOnAction(ActionEvent event) throws SQLException{
        String id = txtSupplierId.getText();

        try {
            boolean isSupplierDeleted = SupplierRepo.deleteSupplier(id);
            if(isSupplierDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted").show();
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSupplierSaveOnAction(ActionEvent event) throws SQLException {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String ItemName = txtSupplierItemName.getText();
        String tel = txtSupplierTel.getText();
        String uId = usr.getUserId();

        Supplier supplier = new Supplier(id, name, ItemName, tel, uId);

        if(DataValidateController.validateSupplierName(txtSupplierName.getText())) {
            supplierNameValidate.setText("");
            if (DataValidateController.validateSupplierTel(txtSupplierTel.getText())) {
                supplierTelValidate.setText("");
                try {
                    boolean isSupplierSaved = SupplierRepo.saveSupplier(supplier);
                    if (isSupplierSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
                        btnSupplierClearOnAction(event);
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else {
                supplierTelValidate.setText("Invalid Telephone Number");
            }
        }else{
           supplierNameValidate.setText("Invalid Supplier Name");
        }
    }

    @FXML
    void btnSupplierUpdateOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String itemName = txtSupplierItemName.getText();
        String tel = txtSupplierTel.getText();
        String uId = usr.getUserId();

        Supplier supplier = new Supplier(id, name, itemName, tel, uId);

        try {
            boolean isSupplier = SupplierRepo.updateSupplier(supplier);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSupplierIdSearchOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        Supplier supplier = null;
        try {
            supplier = SupplierRepo.searchBySupplier(id);
            if (supplier != null) {
                txtSupplierId.setText(supplier.getId());
                txtSupplierName.setText(supplier.getName());
                txtSupplierItemName.setText(supplier.getItemName());
                txtSupplierTel.setText(supplier.getTel());
            }
        } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
