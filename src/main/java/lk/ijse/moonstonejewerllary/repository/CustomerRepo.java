package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class CustomerRepo {
    public String generateNextCustomerId() throws SQLException {
        String sql = "SELECT cId FROM Customer ORDER BY cId DESC LIMIT 1";
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentCustomerId = null;
        if(resultSet.next()){
            currentCustomerId = resultSet.getString(1);
            return generateNextCustomerId(currentCustomerId);
        }
        return generateNextCustomerId(currentCustomerId);
    }

    private String generateNextCustomerId(String currentCustomerId) {
        String next = null;
        if(currentCustomerId == null){
            next = "C001";
        }else {
            String data = currentCustomerId.replace("C", "");
            int id = Integer.parseInt(data);
            id++;

            if(id >= 1 && id <= 10){
                next = "C00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "C0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "C" + id;
            }
        }
        return next;
    }

    public List<Customer> getAllCustomer() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> allCustomer = new ArrayList<>();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);
            String userId = resultSet.getString(6);

            Customer customer = new Customer(id, name, address, tel, email, userId);
            allCustomer.add(customer);
        }
        return allCustomer;
    }

    public boolean saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1,customer.getId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(3,customer.getAddress());
        preparedStatement.setObject(4,customer.getTel());
        preparedStatement.setObject(5,customer.getEmail());
        preparedStatement.setObject(6,customer.getUserId());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET cName = ?, cAddress = ?, cTel = ?, cEmail = ?, uId = ? WHERE cId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4, customer.getEmail());
        pstm.setObject(5, customer.getUserId());
        pstm.setObject(6, customer.getId());

        return pstm.executeUpdate() > 0;
    }

    public Customer searchByCustomerId(String Customerid) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE cId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,Customerid);
        ResultSet resultSet = pstm.executeQuery();

        Customer customer = null;

        if(resultSet.next()) {
            String cId = resultSet.getString(1);
            String cName = resultSet.getString(2);
            String cAddress = resultSet.getString(3);
            String cEmail = resultSet.getString(4);
            String cTel = resultSet.getString(5);
            String userId = resultSet.getString(6);

            customer = new Customer(cId, cName,cAddress,cEmail,cTel,userId);
        }
        return customer;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE cId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }
}

