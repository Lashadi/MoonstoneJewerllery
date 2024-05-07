package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.OrderDetails;
import lk.ijse.moonstonejewerllary.model.Payment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsRepo {
    public static boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        for(OrderDetails orderDetails : orderDetailsList){
            if(!saveOrderDetails(orderDetails)){
                return false;
            }
        }
        return true;
    }

    private static boolean saveOrderDetails(OrderDetails orderDetails) throws SQLException {
        String sql = "INSERT INTO Order_Detail VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,orderDetails.getOrderId());
        preparedStatement.setString(2,orderDetails.getItemCode());
        preparedStatement.setDouble(3,orderDetails.getUnitPrice());
        preparedStatement.setInt(4,orderDetails.getQty());
        preparedStatement.setDouble(5,orderDetails.getTotalAmount());

        return preparedStatement.executeUpdate() > 0;
    }
}
