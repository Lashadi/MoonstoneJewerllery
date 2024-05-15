package lk.ijse.moonstonejewerllary.repository;

import javafx.scene.chart.PieChart;
import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashBoardRepo {
    public static ArrayList<PieChart.Data> getPieChartData() throws SQLException {
        String sql = "SELECT iCode,SUM(qty) AS orderCount FROM Order_Detail GROUP BY iCode ORDER BY orderCount desc limit 5";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ArrayList<PieChart.Data> data = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Item item = ItemRepo.searchById(resultSet.getString(1));
            data.add(
                    new PieChart.Data(item.getItemName(), resultSet.getInt(2))
            );
        }
        return data;
    }

}
