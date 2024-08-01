package main.basekriteriev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Delete {

    @FXML
    private TextField NameTable;


    private static final String DB_URL = "jdbc:mysql://localhost:3306/base_kriteriev";
    private static final String USER = "root";
    private static final String PASS = "sql1";


    @FXML
    private void Delete(ActionEvent event) {
        String dataTableName = NameTable.getText();
        if (dataTableName == null || dataTableName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Имя таблицы не введено");
        } else {
            try {
                boolean wasDeleted = deleteTableByName(dataTableName);
                if (wasDeleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Успеx", "Таблица '" + dataTableName + "' успешно удалена");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Ошибка", "Нет '" + dataTableName + "' в базе данных");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось удалить таблицу: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean deleteTableByName(String dataTableName) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + dataTableName + "'");
            if (rs.next()) {
                String sql = "DROP TABLE IF EXISTS `" + dataTableName + "`";
                System.out.println("Executing SQL: " + sql);
                stmt.executeUpdate(sql);
                System.out.println("Таблица " + dataTableName + " успешно удалена");
                return true;
            } else {
                return false;
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}