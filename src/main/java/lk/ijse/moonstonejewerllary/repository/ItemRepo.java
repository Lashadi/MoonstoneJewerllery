package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public String generateNextItemId() throws SQLException, SQLException {
        String sql = "SELECT iCode FROM Item ORDER BY iCode DESC LIMIT 1";
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentItemId = null;
        if (resultSet.next()) {
            currentItemId = resultSet.getString(1);
            return generateNextItemId(currentItemId);
        }
        return generateNextItemId(currentItemId);
    }

    private String generateNextItemId(String currentItemId) {
        String next = null;
        if (currentItemId == null) {
            next = "I001";
        }else {
            String data = currentItemId.replace("I", "");
            int id = Integer.parseInt(data);
            id++;
            if (id >= 1 && id <= 10) {
                next = "I00" + id;
            } else if (id >= 11 && id <= 100) {
                next = "I0" + id;
            } else if (id >= 101 && id <= 1000) {
                next = "I" + id;
            }
        }

        return next;

    }

    public boolean saveItem(Item item) throws SQLException {
        String sql = "INSERT INTO Item VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1,item.getCode());
        preparedStatement.setObject(2,item.getItemName());
        preparedStatement.setObject(3,item.getCategory());
        preparedStatement.setObject(4,item.getQty());
        preparedStatement.setObject(5,item.getPrice());
        preparedStatement.setObject(6,item.getDate());
        return preparedStatement.executeUpdate() > 0;
    }

    public List<Item> getAllItem() throws SQLException {
        String sql = "SELECT * FROM Item";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Item> allItem = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);
            int qty = Integer.parseInt(resultSet.getString(4));
            double price = Double.parseDouble(resultSet.getString(5));
            Date date = Date.valueOf(resultSet.getString(6));

            Item item = new Item(code, name, category, qty, price, date);
            allItem.add(item);
        }
        return allItem;
    }
}
