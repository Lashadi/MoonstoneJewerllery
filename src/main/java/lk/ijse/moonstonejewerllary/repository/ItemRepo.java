package lk.ijse.moonstonejewerllary.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Item;
import lk.ijse.moonstonejewerllary.model.OrderDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static List<String> getItemCodes() throws SQLException {
        List<String> itemCodes = new ArrayList<>();

        String sql = "SELECT iCode FROM Item";
        PreparedStatement preparedStatement =  DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            itemCodes.add(resultSet.getString(1));
        }
        return itemCodes;
    }

    public static boolean updateItemQty(List<OrderDetails> orderDetailsList) throws SQLException {
        for(OrderDetails orderDetails : orderDetailsList){
            if(!updateItemQty(orderDetails)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateItemQty(OrderDetails orderDetails) throws SQLException {
        String sql = "UPDATE Item SET iQty = iQty - ? WHERE iCode = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setInt(1,orderDetails.getQty());
        preparedStatement.setString(2,orderDetails.getItemCode());

        return preparedStatement.executeUpdate() > 0;
    }

    public static ObservableList<XYChart.Series<String, Integer>> getBarChartData() throws SQLException {
        String sql = "SELECT iName,iQty FROM Item";
        Connection connection = DbConnection.getInstance().getConnection();

        ObservableList<XYChart.Series<String,Integer>> dataList = FXCollections.observableArrayList();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        XYChart.Series<String,Integer> series = new XYChart.Series<>();

        while (resultSet.next()){
            String name = resultSet.getString("iName");
            int qty = resultSet.getInt("iQty");
            series.getData().add(new XYChart.Data<>(name,qty));
        }
        dataList.add(series);
        return dataList;
    }

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

    public boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE Item SET iName=?,iCategory=?,iQty=?,iPrice=?,iDate=? WHERE iCode=?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);


     preparedStatement.setObject(1, item.getItemName());
     preparedStatement.setObject(2, item.getCategory());
     preparedStatement.setObject(3, item.getQty());
     preparedStatement.setObject(4, item.getPrice());
     preparedStatement.setObject(5, item.getDate());
     preparedStatement.setObject(6,item.getCode());

     return  preparedStatement.executeUpdate() > 0;
    }


   public static Item searchById(String code) throws SQLException {
        String sql = "SELECT * FROM Item WHERE iCode = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();

        Item item = null;

        if (resultSet.next()) {
            String iCode = resultSet.getString(1);
            String ItemName = resultSet.getString(2);
            String category = resultSet.getString(3);
            int qty = Integer.parseInt(resultSet.getString(4));
            double price = Double.parseDouble(resultSet.getString(5));
            Date date = Date.valueOf(resultSet.getString(6));

            item = new Item(iCode, ItemName, category, qty, price, date);
        }
        return item;
    }


    public boolean deleteItem(String code) throws SQLException {
       String sql = "DELETE FROM Item WHERE iCode = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1, code);

        return preparedStatement.executeUpdate() > 0;
    }
}




