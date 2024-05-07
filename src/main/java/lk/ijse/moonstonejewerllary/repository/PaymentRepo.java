package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepo {
    public static String generatePaymentId() throws SQLException {
        String sql = "SELECT pId FROM Payment ORDER BY pId DESC LIMIT 1";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String currentPaymentId = null;
        if(resultSet.next()){
            currentPaymentId = resultSet.getString(1);
            return nextPaymentId(currentPaymentId);
        }
        return nextPaymentId(null);
    }

    private static String nextPaymentId(String currentPaymentId) {
        String next = null;
        if(currentPaymentId == null){
            next = "P001";
        }else{
            String data = currentPaymentId.replace("P", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 9){
                next = "P00" + id;
            } else if (id >= 10 && id <= 99) {
                next = "P0" + id;
            } else if (id >= 100 && id <= 999) {
                next = "P" + id;
            }
        }
        return next;
    }

    public static boolean savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1,payment.getPaymentId());
        preparedStatement.setString(2,payment.getCustomerId());
        preparedStatement.setString(3,payment.getOrderId());
        preparedStatement.setDouble(4,payment.getPaymentAmount());
        preparedStatement.setDate(5,payment.getDate());

        return preparedStatement.executeUpdate() > 0;
    }
}
