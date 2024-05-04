package lk.ijse.moonstonejewerllary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.moonstonejewerllary.model.Item;
import lk.ijse.moonstonejewerllary.model.tm.ItemTm;
import lk.ijse.moonstonejewerllary.repository.ItemRepo;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemCategory;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchItem;

    @FXML
    private TextField txtUnitPrice;

    ItemRepo itemRepo = new ItemRepo();

    private List<Item>itemList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtItemCode.setText(itemRepo.generateNextItemId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.itemList=getAllItem();
        loadAllItem();
        setCellValueFactory();

    }

    private List<Item> getAllItem() {
        List<Item>itemList = null;
        try {
            itemList = itemRepo.getAllItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void loadAllItem() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        for(Item item : itemList) {
            ItemTm itemTm = new ItemTm(
                    item.getCode(),
                    item.getItemName(),
                    item.getCategory(),
                    item.getQty(),
                    item.getPrice()
            );
            tmList.add(itemTm);
        }
        tblItem.setItems(tmList);
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnItemClearOnAction(ActionEvent event) {
        txtItemCategory.clear();
        txtItemName.clear();
        txtQty.clear();
        txtUnitPrice.clear();
    }

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemSaveOnAction(ActionEvent event) {
        String id = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = txtItemCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtUnitPrice.getText());
        Date date = Date.valueOf(dpDate.getValue());

        Item item = new Item(id, itemName, category, qty, price, date);

        try {
            boolean isItemSaved = itemRepo.saveItem(item);
            if(isItemSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved").show();
                loadAllItem();
                btnItemClearOnAction(event);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

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
