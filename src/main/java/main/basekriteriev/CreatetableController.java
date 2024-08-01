package main.basekriteriev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CreatetableController implements Initializable {

    @FXML
    public TextField Name1;
    @FXML
    public TextField Name2;
    @FXML
    public TextField Name3;
    @FXML
    public TextField Name4;
    @FXML
    public TextField Name5;
    @FXML
    public TextField Name6;
    @FXML
    public TextField Name7;
    @FXML
    public TextField Name8;
    @FXML
    public TextField Name9;
    @FXML
    public TextField Name10;
    @FXML
    public TextField Name11;
    @FXML
    public TextField Name12;

    @FXML
    public TextField data1;
    @FXML
    public TextField data2;
    @FXML
    public TextField data3;
    @FXML
    public TextField data4;
    @FXML
    public TextField data5;
    @FXML
    public TextField data6;
    @FXML
    public TextField data7;
    @FXML
    public TextField data8;
    @FXML
    public TextField data9;
    @FXML
    public TextField data10;
    @FXML
    public TextField data11;
    @FXML
    public TextField data12;

    @FXML
    private TextField Type1;
    @FXML
    private TextField Type2;
    @FXML
    private TextField Type3;
    @FXML
    private TextField Type4;
    @FXML
    private TextField Type5;
    @FXML
    private TextField Type6;
    @FXML
    private TextField Type7;
    @FXML
    private TextField Type8;
    @FXML
    private TextField Type9;
    @FXML
    private TextField Type10;
    @FXML
    private TextField Type11;
    @FXML
    private TextField Type12;

    @FXML
    public TextField tableNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createTable(ActionEvent actionEvent) {
        String tableName = tableNameField.getText().trim();
        String url = "jdbc:mysql://localhost:3306/base_kriteriev";
        String user = "root";
        String password = "sql1";

        if (tableName.isEmpty()) {
            showAlert(AlertType.ERROR, "Ошибка", "Введите название таблицы");
            return;
        }

        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        createTableQuery.append(tableName).append(" (");
        createTableQuery.append("id INT AUTO_INCREMENT PRIMARY KEY, ");


        for (int i = 1; i <= 12; i++) {
            createTableQuery.append("B").append(i).append(" VARCHAR(25), ");
        }

        createTableQuery.delete(createTableQuery.length() - 2, createTableQuery.length());
        createTableQuery.append(")");

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery.toString());
            showAlert(AlertType.INFORMATION, "Успех", "Таблица успешно создана");
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Ошибка", "Не удалось создать таблицу: " + e.getMessage());
        }
    }

    private void insertData(Connection connection, String tableName, String[] data) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO ").append(tableName).append(" (");

        TextField[] nameFields = {Name1, Name2, Name3, Name4, Name5, Name6, Name7, Name8, Name9, Name10, Name11, Name12};

        for (int i = 0; i < nameFields.length; i++) {
            String columnName = nameFields[i].getText().trim();
            if (!columnName.isEmpty() && !data[i].isEmpty()) {
                insertQuery.append(columnName).append(", ");
            }
        }

        if (insertQuery.length() > 0) {
            insertQuery.setLength(insertQuery.length() - 2);
        }
        insertQuery.append(") VALUES (");

        boolean hasValues = false;
        for (String value : data) {
            if (!value.isEmpty()) {
                insertQuery.append("'").append(value).append("', ");
                hasValues = true;
            }
        }

        if (!hasValues) {
            showAlert(AlertType.WARNING, "Внимание", "Нет данных для вставки");
            return;
        }

        if (insertQuery.length() > 0) {
            insertQuery.setLength(insertQuery.length() - 2);
        }
        insertQuery.append(")");

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery.toString());
            showAlert(AlertType.INFORMATION, "Успех", "Данные успешно добавлены");
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Ошибка", "Не удалось добавить данные: " + e.getMessage());
        }
    }

    public void clearFields() {
        TextField[] nameFields = {Name1, Name2, Name3, Name4, Name5, Name6, Name7, Name8, Name9, Name10, Name11, Name12};
        TextField[] typeFields = {Type1, Type2, Type3, Type4, Type5, Type6, Type7, Type8, Type9, Type10, Type11, Type12};

        for (TextField nameField : nameFields) {
            nameField.clear();
        }

        for (TextField typeField : typeFields) {
            typeField.clear();
        }
    }

    public void a1(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(1);
    }

    public void a2(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(2);
    }

    public void a3(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(3);
    }

    public void a4(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(4);
    }

    public void a5(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(5);
    }

    public void a6(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(6);
    }

    public void a7(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(7);
    }

    public void a8(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(8);
    }

    public void a9(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(9);
    }

    public void a10(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(10);
    }

    public void a11(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(11);
    }

    public void a12(ActionEvent actionEvent) {
        clearFields();
        setNameAndTypeFields(12);
    }

    public void All(ActionEvent actionEvent) {
        setNameAndTypeFields(12);
    }

    private void setNameAndTypeFields(int count) {
        TextField[] nameFields = {Name1, Name2, Name3, Name4, Name5, Name6, Name7, Name8, Name9, Name10, Name11, Name12};
        TextField[] typeFields = {Type1, Type2, Type3, Type4, Type5, Type6, Type7, Type8, Type9, Type10, Type11, Type12};
        for (int i = 0; i < count; i++) {
            nameFields[i].setText("B" + (i + 1));
            typeFields[i].setText("VARCHAR(25)");
        }
    }

    public void addTable(ActionEvent actionEvent) {
        String tableName = tableNameField.getText().trim();
        String url = "jdbc:mysql://localhost:3306/base_kriteriev";
        String user = "root";
        String password = "sql1";

        if (tableName.isEmpty()) {
            showAlert(AlertType.ERROR, "Ошибка", "Введите название таблицы");
            return;
        }

        String[] data = {
                data1.getText().trim(), data2.getText().trim(), data3.getText().trim(), data4.getText().trim(),
                data5.getText().trim(), data6.getText().trim(), data7.getText().trim(), data8.getText().trim(),
                data9.getText().trim(), data10.getText().trim(), data11.getText().trim(), data12.getText().trim()
        };

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            insertData(connection, tableName, data);
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Ошибка", "Не удалось подключиться к базе данных: " + e.getMessage());
        }
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}