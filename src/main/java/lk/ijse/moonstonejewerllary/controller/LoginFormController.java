package lk.ijse.moonstonejewerllary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.moonstonejewerllary.model.User;
import lk.ijse.moonstonejewerllary.repository.UserRepo;
import lk.ijse.moonstonejewerllary.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnSignInOnAction(ActionEvent event) throws SQLException {
        String userId = txtUserId.getText();
        String password = txtPassword.getText();

            try{
                UserRepo.checkCredential(userId,password,ancLogin);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"User Id or Password doesn't match");
            }catch (IOException e){
                throw new RuntimeException(e);
        }

    }


    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }


    @FXML
    void txtPasswordOnAction(ActionEvent event) throws SQLException {
        btnSignInOnAction(event);
    }

    @FXML
    void txtUseNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void gotoDashBoard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/global_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("DashBoard Form");
        stage.show();


    }
}
