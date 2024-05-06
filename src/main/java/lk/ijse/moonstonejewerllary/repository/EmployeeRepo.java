package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static String generateNextEmployeeId() throws SQLException {
        String sql = "SELECT eId FROM Employee ORDER BY eId DESC LIMIT 1";
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentEmployeeId = null;
        if(resultSet.next()){
            currentEmployeeId = resultSet.getString(1);
            return generateNextEmployeeId(currentEmployeeId);
        }
        return generateNextEmployeeId(currentEmployeeId);

    }

    private static String generateNextEmployeeId(String currentEmployeeId) {
        String next = null;
        if(currentEmployeeId == null){
            next = "E001";
        }else{
            String data = currentEmployeeId.replace("E", "");
            int id = Integer.parseInt(data);
            id++;

            if (id >= 1 && id <= 10) {
                next = "E00" + id;
            }else if (id >= 11 && id <= 100) {
                next = "E0" + id;
            }else if (id >= 101 && id <= 1000) {
                next = "E" + id;
            }
        }
        return next;
    }

    public static boolean saveEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1,employee.getId());
        preparedStatement.setObject(2,employee.getName());
        preparedStatement.setObject(3,employee.getAddress());
        preparedStatement.setObject(4,employee.getTel());
        preparedStatement.setObject(5,employee.getUserId());

        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean updateEmployee(Employee employee) throws  SQLException {
        String sql = "UPDATE Employee SET eName = ?, eAddress = ?, eTel = ?, uId = ? WHERE eId = ?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1,employee.getId());
        preparedStatement.setString(2,employee.getName());
        preparedStatement.setString(3,employee.getAddress());
        preparedStatement.setString(4,employee.getTel());
        preparedStatement.setString(5,employee.getUserId());

        return preparedStatement.executeUpdate() > 0;

    }

    public static boolean deleteEmployee(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE eId = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,id);

        return preparedStatement.executeUpdate() > 0;
    }

    public static Employee searchByEmployeeId(String EmployeeId) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE eId = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,EmployeeId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String userId = resultSet.getString(5);

            employee =new Employee(id, name, address, tel, userId);
        }
        return employee;
    }

    public static List<Employee> getEmployee() throws SQLException {
        String sql = "SELECT * FROM Employee";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> allEmployee = new ArrayList<>();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Employee employee = new Employee(id,name,address,tel,userId);
            allEmployee.add(employee);
        }
        return allEmployee;
    }
}
