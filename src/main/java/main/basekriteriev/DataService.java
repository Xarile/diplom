package main.basekriteriev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataService {
    private Connection con;

    public DataService(Connection con) {

        this.con = con;
    }

    public void updateRecord(Object selectedItem, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String tableName) {
        try {
            String sql = "UPDATE " + tableName + " SET B1 = ?, B2 = ?, B3 = ?, B4 = ?, B5 = ?, B6 = ?, B7 = ?, B8 = ?, B9 = ?, B10 = ?, B11 = ?, B12 = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, value1);
            statement.setString(2, value2);
            statement.setString(3, value3);
            statement.setString(4, value4);
            statement.setString(5, value5);
            statement.setString(6, value6);
            statement.setString(7, value7);
            statement.setString(8, value8);
            statement.setString(9, value9);
            statement.setString(10, value10);
            statement.setString(11, value11);
            statement.setString(12, value12);


            int id;
            if (selectedItem instanceof tableone) {
                id = ((tableone) selectedItem).getId();
            } else if (selectedItem instanceof tabletwo) {
                id = ((tabletwo) selectedItem).getId();
            } else {
                throw new IllegalArgumentException("Неверный тип выбранной записи");
            }
            statement.setInt(13, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Запись успешно обновлена");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void createRecord(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String tableName) {
        try {
            int maxId = getMaxId(tableName);
            String sql = "INSERT INTO " + tableName + " (id, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, maxId + 1);


            String[] values = {value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12};
            for (int i = 0; i < 12; i++) {
                if (values[i] == null || values[i].isEmpty()) {
                    statement.setNull(i + 2, java.sql.Types.VARCHAR);
                } else {
                    statement.setString(i + 2, values[i]);
                }
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно создана");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteRecord(int id, String tableName) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Запись успешно удалена");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int getMaxId(String tableName) throws SQLException {
        int maxId = 0;
        String sql = "SELECT MAX(id) AS max_id FROM " + tableName;
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            maxId = resultSet.getInt("max_id");
        }
        return maxId;
    }


}




