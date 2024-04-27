package lk.ijse.moonstonejewerllary.repository;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.moonstonejewerllary.controller.LoginFormController;
import lk.ijse.moonstonejewerllary.db.DbConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    public static void checkCredential(String userId, String password, AnchorPane ancLogin) throws SQLException, IOException {
        String sql = "SELECT uId,uPassword FROM User WHERE uId = ?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String upw = resultSet.getString(2);
            if(upw.equals(password)){
                ancLogin.getScene().getWindow().hide();
                new LoginFormController().gotoDashBoard();
            }else{
                new Alert(Alert.AlertType.ERROR,"Password is incorrect");
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"User Id Not Found!");
        }
    }
}
