package lk.ijse.moonstonejewerllary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.moonstonejewerllary.model.Employee;
import lk.ijse.moonstonejewerllary.model.tm.EmployeeTm;
import lk.ijse.moonstonejewerllary.repository.EmployeeRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.moonstonejewerllary.controller.CustomerFormController.usr;

public class EmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmployeeAddress;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colEmployeeTel;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeTel;

    @FXML
    private TextField txtEmplyeeAddress;

    @FXML
    private TextField txtEmplyeeName;

    @FXML
    private TextField txtSearchEmplyee;

    private List<Employee>employeeList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtEmployeeId.setText(EmployeeRepo.generateNextEmployeeId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        this.employeeList=  getAllEmployee();
        setCellValueFactory();
        loadAllEmployee();
    }

    private List<Employee> getAllEmployee() {
        List<Employee> employeeList = null;

        try {
            employeeList = EmployeeRepo.getEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colEmployeeTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    @FXML
    void btnEmployeeClearOnAction(ActionEvent event) {
        txtEmplyeeName.clear();
        txtEmplyeeAddress.clear();
        txtEmplyeeAddress.clear();
        txtEmployeeTel.clear();
    }

    @FXML
    void btnEmployeeDeleteOnAction(ActionEvent event) throws SQLException{
        String id = txtEmployeeId.getText();

        try {
            boolean isEmployeeDeleted = EmployeeRepo.deleteEmployee(id);
            if(isEmployeeDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted").show();
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnEmployeeSaveOnAction(ActionEvent event) {
         String id = txtEmployeeId.getText();
         String name = txtEmplyeeName.getText();
         String address = txtEmplyeeAddress.getText();
         String tel = txtEmployeeTel.getText();
         String uId = usr.getUserId();

         Employee employee = new Employee(id, name, address, tel, uId);

        try {
            boolean isEmployeeSaved =  EmployeeRepo.saveEmployee(employee);
            if(isEmployeeSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved").show();
                btnEmployeeClearOnAction(event);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnEmployeeUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtEmplyeeName.getText();
        String address = txtEmplyeeAddress.getText();
        String tel = txtEmployeeTel.getText();
        String uId = usr.getUserId();

        Employee employee = new Employee(id, name, address, tel, uId);
        try {
            boolean isEmployeeUpdated = EmployeeRepo.updateEmployee(employee);
            if(isEmployeeUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadAllEmployee() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getTel()
            );
            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
        tblEmployee.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnSearchEmployeeOnAction(ActionEvent event) throws SQLException{

    }

    @FXML
    void txtEmployeeIdSearchOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            Employee employee = EmployeeRepo.searchByEmployeeId(id);
            if (employee != null) {
                txtEmployeeId.setText(employee.getId());
                txtEmplyeeName.setText(employee.getName());
                txtEmplyeeAddress.setText(employee.getAddress());
                txtEmployeeTel.setText(employee.getTel());
            }
        } catch ( SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}

