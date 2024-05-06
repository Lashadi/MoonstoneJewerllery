package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderRepo {
    public static String generateNextOrderId() throws SQLException {
        String sql = "SELECT oId FROM Orders ORDER BY oId DESC LIMIT 1";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String currentOrderId = null;
        if(resultSet.next()){
            currentOrderId = resultSet.getString(1);
            return nextOrderId(currentOrderId);
        }
        return nextOrderId(currentOrderId);
    }

    private static String nextOrderId(String currentOrderId) {
        String nextOrderId = null;
        if(currentOrderId == null){
            nextOrderId = "OR001";
        }else {
            String data = currentOrderId.replace("OR", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 9){
                nextOrderId = "OR00" + id;
            } else if (id >= 10 && id <= 99) {
                nextOrderId = "OR0" + id;
            } else if (id >= 100 && id <= 999) {
                nextOrderId = "OR" + id;

            }
        }
        return nextOrderId;
    }
}
