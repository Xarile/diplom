package main.basekriteriev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Kf {

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    private static final String URL = "jdbc:mysql://localhost:3306/base_kriteriev";
    private static final String USER = "root";
    private static final String PASSWORD = "sql1";

    @FXML
    private void handleSubmitButtonAction() {
        String input = inputField.getText();
        String[] parts = input.split(";");
        if (parts.length != 2) {
            outputLabel.setText("Неверный формат ввода. Используйте формат 'Bn;B1 B2 B3 и тд'");
            return;
        }else {
            outputLabel.setText("Выбока начинается от 2 критериев");
        }
        String tableId = parts[0];
        String[] data = parts[1].split(" ");
        if (data.length == 0) {
            outputLabel.setText("Неверный формат ввода. Введите значения после ';'.");
            return;
        }

        String tableName1 = getTableName1(tableId);
        String tableName2 = getTableName2(tableId);

        if (!tableName1.isEmpty() && !tableName2.isEmpty()) {
            processTable(tableName1, tableName2, data);
        }
        // Проверка первого числа
        int expectedSum;
        try {
            expectedSum = Integer.parseInt(tableId);
        } catch (NumberFormatException e) {
            outputLabel.setText("Не верно задан формат ");
            return;
        }

        // Проверка суммы оставшихся чисел
        int sum = 0;
        for (String str : data) {
            try {
                int num = Integer.parseInt(str);
                sum += num;
            } catch (NumberFormatException e) {
                outputLabel.setText("Не верно задана формула Bn;B1 B2 B3: " + str);
                return;
            }
        }

        if (sum != expectedSum) {
            outputLabel.setText("Ошибка: сумма чисел в строке не равна числу критериев n");
            return;
        }
    }

    private String getTableName1(String tableName) {
        switch (tableName) {
            case "2": return "two";
            case "3": return "three";
            case "4": return "forr";
            case "5": return "five";
            case "6": return "six";
            case "7": return "seven";
            case "8": return "eight";
            case "9": return "nine";
            case "10": return "ten";
            case "11": return "eleven";
            case "12": return "twelve";
            default: return "";
        }
    }

    private String getTableName2(String tableName) {
        switch (tableName) {
            case "2": return "twox";
            case "3": return "threex";
            case "4": return "forrx";
            case "5": return "fivex";
            case "6": return "sixx";
            case "7": return "sevenx";
            case "8": return "eightx";
            case "9": return "ninex";
            case "10": return "tenx";
            case "11": return "elevenx";
            case "12": return "twelvex";
            default: return "";
        }
    }

    private void processTable(String firstTableName, String secondTableName, String[] data) {
        int id = getIdFromFirstTable(firstTableName, data);

        if (id != -1) {
            String secondTableData = getDataFromSecondTable(secondTableName, id);
            outputLabel.setText(secondTableData);
        } else {
            outputLabel.setText("Данные не найдены в таблице (критериев)");
        }
    }

    private int getIdFromFirstTable(String tableName, String[] data) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            StringBuilder queryBuilder = new StringBuilder("SELECT id FROM ").append(tableName).append(" WHERE ");
            for (int i = 0; i < data.length; i++) {
                if (i > 0) {
                    queryBuilder.append(" AND ");
                }
                queryBuilder.append("B").append(i + 1).append(" = ?");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
                for (int i = 0; i < data.length; i++) {
                    preparedStatement.setString(i + 1, data[i]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    outputLabel.setText("Данные не найдены в первой таблице для значения " + Arrays.toString(data) + ".");
                }
            }
        } catch (SQLException e) {
            outputLabel.setText("Ошибка: " + e.getMessage());
        }
        return -1;
    }

    private String getDataFromSecondTable(String tableName, int id) {
        StringBuilder result = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    for (int i = 1; i <= 12; i++) {
                        String columnName = "B" + i;
                        String columnValue = resultSet.getString(columnName);
                        if (columnValue != null) {
                            result.append(columnName).append(": ").append(columnValue).append("\n");
                        }
                    }
                } else {
                    result.append("Данные не найдены во второй таблице для id ").append(id).append(".");
                }
            }
        } catch (SQLException e) {
            result.append("Ошибка: ").append(e.getMessage());
        }
        return result.toString();
    }

    public void infoUser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("infoUser.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 387, 623);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("Справка");
        stage.setScene(scene);
        stage.show();
    }
}