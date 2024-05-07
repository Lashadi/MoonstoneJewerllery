package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public static List<String> getCustomerIds() throws SQLException {
        List<String> customerIds = new ArrayList<>();

        String sql = "SELECT cId FROM Customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            customerIds.add(resultSet.getString(1));
        }
        return customerIds;
    }

    public static boolean saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO Orders VALUES (?,?,?)";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,order.getOrderId());
        preparedStatement.setDate(2,order.getDate());
        preparedStatement.setString(3,order.getCustomerId());

        return preparedStatement.executeUpdate() > 0;
    }
}
