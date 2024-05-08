package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Employee;
import lk.ijse.moonstonejewerllary.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean saveSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setString(3, supplier.getItemName());
        preparedStatement.setString(4, supplier.getTel());
        preparedStatement.setString(5, supplier.getUId());

        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE sId = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Supplier supplier = null;

        if (resultSet.next()) {
            String sId = resultSet.getString(1);
            String sName = resultSet.getString(2);
            String itemName = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String uId = resultSet.getString(5);

            supplier = new Supplier(sId, sName, itemName, tel, uId);
        }
        return supplier != null;
    }

    public static boolean updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET sName = ?, itemName = ?, tel = ?, uId = ? WHERE sId = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getItemName());
        preparedStatement.setString(3, supplier.getTel());
        preparedStatement.setString(4, supplier.getUId());

        return preparedStatement.executeUpdate() > 0;

    }

    public static String generateSupplierId() throws SQLException {
        String sql = "SELECT supId FROM Supplier ORDER BY supId DESC LIMIT 1";
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentSupplierId = null;
        if (resultSet.next()) {
            currentSupplierId = resultSet.getString(1);
            return generateSupplierId(currentSupplierId);
        }
        return generateSupplierId(currentSupplierId);

    }

    private static String generateSupplierId(String currentSupplierId) {
        String next = null;
        if (currentSupplierId == null) {
            next = "S001";
        } else {
            String data = currentSupplierId.replace("S", "");
            int id = Integer.parseInt(data);
            id++;

            if (id >= 1 && id <= 10) {
                next = "S00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "S0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "S" + id;
            }
        }
        return next;
    }

    public static List<Supplier> getSupplier() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Supplier> allSupplier = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String itemName = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Supplier supplier = new Supplier(id,name,itemName,tel,userId);
            allSupplier.add(supplier);
        }
        return allSupplier;
    }

    public static Supplier searchBySupplier(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supId = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Supplier supplier = null;

        if(resultSet.next()){
            String supId = resultSet.getString(1);
            String sName = resultSet.getString(2);
            String itemName = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String uId = resultSet.getString(5);

            supplier = new Supplier(supId,sName,itemName,tel,uId);
        }
        return supplier;
    }

    public static Supplier searchByContactNo(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supTelephone = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Supplier supplier = null;

        if(resultSet.next()){
            String supId = resultSet.getString(1);
            String sName = resultSet.getString(2);
            String itemName = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String uId = resultSet.getString(5);

            supplier = new Supplier(supId,sName,itemName,tel,uId);
        }
        return supplier;
    }
}





