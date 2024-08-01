package main.basekriteriev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class User {

    @FXML
    public PasswordField password;
    @FXML
    private TextField login;

    private static final String ADMIN_HASHED_PASSWORD = "21232f297a57a5a743894a0e4a801fc3";

    @FXML
    private void JoinAdmin(ActionEvent event) {
        if (checkAdminCredentials(login.getText(), password.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1286, 567);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Администрирование справочной выбора универсальных коэффициентов важности критериев");
            stage.setScene(scene);
            stage.show();
        } else {
            // Обработка неверного логина/пароля
            showAlert("Ошибка","Неверный логин или пароль администратора");
        }
    }

    @FXML
    private void JoinUser(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kf.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 669, 349);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("Справочная выбора универсальных коэффициентов важности критериев");
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkAdminCredentials(String login, String password) {
        // Проверка логина и хэширование введенного пароля для сравнения с сохраненным хэшем
        if ("admin".equals(login)) {
            String hashedPassword = PasswordHash.doHashing(password);
            return ADMIN_HASHED_PASSWORD.equals(hashedPassword);
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}