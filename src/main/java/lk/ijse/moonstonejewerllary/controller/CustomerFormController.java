package lk.ijse.moonstonejewerllary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.moonstonejewerllary.model.Customer;
import lk.ijse.moonstonejewerllary.model.User;
import lk.ijse.moonstonejewerllary.model.tm.CustomerTm;
import lk.ijse.moonstonejewerllary.repository.CustomerRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    static User usr;

    @FXML
    private ComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    @FXML
    private TextField txtSearchCustomer;

    @FXML
    private TableColumn<?, ?> txtUserId;

    private List<Customer> customerList = new ArrayList<>();

    CustomerRepo customerRepo = new CustomerRepo();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtCustomerId.setText(customerRepo.generateNextCustomerId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.customerList=getAllCustomer();
        setCellValueFactory();
        loadAllCustomers();

    }

    private List<Customer> getAllCustomer() {
        List<Customer> customerList = null;
        try {
            customerList = customerRepo.getAllCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            CustomerTm customerTm = new CustomerTm(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel(),
                    customer.getEmail()
            );

            tmList.add(customerTm);
        }
        tblCustomer.setItems(tmList);
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
    }

    public static void setUser(User usr) {
        CustomerFormController.usr = usr;
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerTel.clear();
        txtCustomerEmail.clear();
    }

    @FXML
    void btnCustomerDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtCustomerId.getText();

        try {
            boolean isCustomerDeleted = customerRepo.deleteCustomer(id);
            if (isCustomerDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted").show();
                loadAllCustomers();
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String tel = txtCustomerTel.getText();
        String email = txtCustomerEmail.getText();
        String uId = usr.getUserId();

        Customer customer = new Customer(id, name, address, tel, email, uId);

        try {
            boolean isCustomerSaved = customerRepo.saveCustomer(customer);
            if(isCustomerSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
                loadAllCustomers();
                btnClearOnAction(event);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnCustomerUpdateOnAction(ActionEvent event) {
         String id = txtCustomerId.getText();
         String name = txtCustomerName.getText();
         String address = txtCustomerAddress.getText();
         String tel = txtCustomerTel.getText();
         String email = txtCustomerEmail.getText();
         String uId = usr.getUserId();

         Customer customer = new Customer(id, name, address, tel, email, uId);

        try {
            boolean isCustomerUpdated=  customerRepo.updateCustomer(customer);
            if (isCustomerUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        String telephone = txtSearchCustomer.getText();

        try {
            Customer customer = customerRepo.searchByTelephone(telephone);
            if(customer != null){
                txtCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerTel.setText(customer.getTel());
                txtCustomerEmail.setText(customer.getEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void tblCustomerOnClick(MouseEvent event) {
        TablePosition tp = tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<CustomerTm,?>>columns = tblCustomer.getColumns();


        txtCustomerId.setText(columns.get(0).getCellData(row).toString());
        txtCustomerName.setText(columns.get(1).getCellData(row).toString());
        txtCustomerAddress.setText(columns.get(2).getCellData(row).toString());
        txtCustomerTel.setText(columns.get(3).getCellData(row).toString());
        txtCustomerEmail.setText(columns.get(4).getCellData(row).toString());

        tblCustomer.setCursor(Cursor.HAND);
    }

    @FXML
    void txtCustomerIdSearchOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try {
            Customer customer = customerRepo.searchByCustomerId(id);
            if(customer != null) {
                txtCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerTel.setText(customer.getTel());
                txtCustomerEmail.setText(customer.getEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
