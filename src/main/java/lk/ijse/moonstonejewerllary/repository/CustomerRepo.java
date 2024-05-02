/*
package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean update(Customer customer) {
        return false;
    }

    public static boolean delete(String id) {
    }

    public static boolean save(Customer customer) {
    }

    public static Customer searchById(String id) {
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            String Id = resultSet.getString(1);
            String Name = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Tel = resultSet.getString(4);
            String Nic = resultSet.getString(5);

            Customer customer = new Customer(Id, Name, Address, Tel, Nic);
            customerList.add(customer);
        }
        return customerList;
    }
}
*/
