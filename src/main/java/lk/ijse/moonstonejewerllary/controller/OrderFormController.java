package lk.ijse.moonstonejewerllary.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.moonstonejewerllary.model.*;
import lk.ijse.moonstonejewerllary.model.tm.AddToCartTm;
import lk.ijse.moonstonejewerllary.repository.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private TableView<AddToCartTm> tblOrderDetails;

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


    private ObservableList<AddToCartTm> itemTmObservableList = FXCollections.observableArrayList();
    double netTotalAmount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtOrderId.setText(OrderRepo.generateNextOrderId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCustomerId();
        getItemCode();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colDeleteItem.setCellValueFactory(new PropertyValueFactory<>("remove"));
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
        int qty = Integer.parseInt(txtQty.getText());
        double totalAmount = unitPrice*qty;
        JFXButton btnDelete = new JFXButton("Remove");
        btnDelete.setCursor(Cursor.HAND);

        btnDelete.setOnAction((e)->{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", yes, no).showAndWait();
            if(type.orElse(no) == yes){
                int index = tblOrderDetails.getSelectionModel().getFocusedIndex();
                itemTmObservableList.remove(index);
                tblOrderDetails.refresh();
                calculateNetAmount();
            }
        });
        for(int i = 0; i<tblOrderDetails.getItems().size(); i++){
            if(itemCode.equals(colItemCode.getCellData(i))){
               qty += itemTmObservableList.get(i).getQty();
               totalAmount += unitPrice*qty;

               itemTmObservableList.get(i).setQty(qty);
               itemTmObservableList.get(i).setTotalAmount(totalAmount);

               tblOrderDetails.refresh();
               calculateNetAmount();
               txtQty.clear();
               cmbItemCode.requestFocus();
               return;
            }
        }
        AddToCartTm addToCartTm = new AddToCartTm(itemCode, itemName, unitPrice, qty, totalAmount, btnDelete);
        itemTmObservableList.add(addToCartTm);
        tblOrderDetails.setItems(itemTmObservableList);
        txtQty.clear();
        calculateNetAmount();

    }

    private void calculateNetAmount() {
        netTotalAmount = 0;
        for(int i=0;i<tblOrderDetails.getItems().size();i++){
            netTotalAmount +=(double)colTotalAmount.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotalAmount));
    }

    @FXML
    void btnOrderPlaceOnAction(ActionEvent event) throws SQLException {
        String orderId = txtOrderId.getText();
        Date date = Date.valueOf(dpOrderDate.getValue());
        String customerId = cmbCustomerId.getValue();

        Order order = new Order(orderId, date, customerId);

        List<OrderDetails>orderList = new ArrayList<>();
        double netAmount = 0;
        for(int i=0; i<tblOrderDetails.getItems().size();i++){
            AddToCartTm addToCartTm = itemTmObservableList.get(i);
            OrderDetails orderDetails = new OrderDetails(
                    orderId,
                    addToCartTm.getItemCode(),
                    addToCartTm.getUnitPrice(),
                    addToCartTm.getQty(),
                    addToCartTm.getTotalAmount()
            );
            orderList.add(orderDetails);
            netAmount += addToCartTm.getTotalAmount();
        }
        String paymentID = PaymentRepo.generatePaymentId();
        Payment payment = new Payment(paymentID, customerId, orderId, netAmount, date);

        PlaceOrder placeOrder = new PlaceOrder(order, orderList, payment);

        try {
            boolean isSaved = PlaceOrderRepo.orderPlace(placeOrder);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong").show();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
