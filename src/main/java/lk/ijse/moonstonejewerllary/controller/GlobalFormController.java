package lk.ijse.moonstonejewerllary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import lk.ijse.moonstonejewerllary.util.Navigation;

import java.io.IOException;

public class GlobalFormController {

    @FXML
    private Pane paginPane;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"customer_form.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"employee_form.fxml");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"home_form.fxml");
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
    Navigation.switchPaging(paginPane,"item_form.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"login_form.fxml");
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"order_form.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"payment_form.fxml");
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"report_form.fxml");
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException{
    Navigation.switchPaging(paginPane,"salary_from.fxml");
    }

}
