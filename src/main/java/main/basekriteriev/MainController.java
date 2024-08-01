package main.basekriteriev;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    public Button create_btn;
    @FXML
    public Button delete_btn;
    @FXML
    public Button info_btn;
    @FXML
    public Button delete_row;
    @FXML
    public Button create_row;
    @FXML
    public Button change_row;
    @FXML
    private Button search_btn;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<tableone> tableView;
    @FXML
    private TableColumn<tableone, String> col_B1;
    @FXML
    private TableColumn<tableone, String> col_B2;
    @FXML
    private TableColumn<tableone, String> col_B3;
    @FXML
    private TableColumn<tableone, String> col_B4;
    @FXML
    private TableColumn<tableone, String> col_B5;
    @FXML
    private TableColumn<tableone, String> col_B6;
    @FXML
    private TableColumn<tableone, String>  col_B7;
    @FXML
    private TableColumn<tableone, String>  col_B8;
    @FXML
    private TableColumn<tableone, String>  col_B9;
    @FXML
    private TableColumn<tableone, String>  col_B10;
    @FXML
    private TableColumn<tableone, String>  col_B11;
    @FXML
    private TableColumn<tableone, String>  col_B12;
    @FXML
    private TableView<tabletwo> tableView1;
    @FXML
    private TableColumn<tabletwo, String> col_B1x;
    @FXML
    private TableColumn<tabletwo, String> col_B2x;
    @FXML
    private TableColumn<tabletwo, String> col_B3x;
    @FXML
    private TableColumn<tabletwo, String> col_B4x;
    @FXML
    private TableColumn<tabletwo, String> col_B5x;
    @FXML
    private TableColumn<tabletwo, String> col_B6x;
    @FXML
    private TableColumn<tabletwo, String> col_B7x;
    @FXML
    private TableColumn<tabletwo, String> col_B8x;
    @FXML
    private TableColumn<tabletwo, String> col_B9x;
    @FXML
    private TableColumn<tabletwo, String>col_B10x;
    @FXML
    private TableColumn<tabletwo, String> col_B11x;
    @FXML
    private TableColumn<tabletwo, String> col_B12x;

    @FXML
    private TextField searchField;
    @FXML
    private Label idLabel;
    @FXML
    private ComboBox<String> tableSelector;


    private ConnectionSql connectDB;
    private Connection con;
    private PreparedStatement pst;
    private DataService dataService;


    public void initialize() {
        connectDB = new ConnectionSql();
        Connect();
        tableSelector.getItems().addAll("2", "3", "4", "5", "6","7", "8", "9","10", "11" , "12");
        tableSelector.setOnAction(event -> {
            String selectedTable = tableSelector.getValue();
            switchTable(selectedTable);
        });

        search_btn.setOnMouseClicked((event) -> {
            switchTable(tableSelector.getValue());
        });

        setupTableRowSelection(tableView);
        setupTableRowSelection(tableView1);
        dataService = new DataService(con);
    }


    private <T> void setupTableRowSelection(TableView<T> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    T rowData = row.getItem();
                    if (rowData instanceof tableone) {
                        idLabel.setText("Строка: " + ((tableone) rowData).getId());
                    } else if (rowData instanceof tabletwo) {
                        idLabel.setText("Строка: " + ((tabletwo) rowData).getId());
                    }
                }
            });
            return row;
        });
    }

    public void Connect() {
        try {
            Connection connection = connectDB.getConnection();
            con = connection;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private <T> void fillTableView(String query, TableView<T> tableView, TableColumn<T, String>[] columns, Class<T> type) {
        ObservableList<T> dataList = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (type == tableone.class) {
                    T data = type.cast(new tableone(rs.getInt("id"), rs.getString("B1"), rs.getString("B2"), rs.getString("B3"), rs.getString("B4"), rs.getString("B5"), rs.getString("B6"),rs.getString("B7"), rs.getString("B8"), rs.getString("B9"), rs.getString("B10"), rs.getString("B11"), rs.getString("B12")));
                    dataList.add(data);
                } else if (type == tabletwo.class) {
                    T data = type.cast(new tabletwo(rs.getInt("id"), rs.getString("B1"), rs.getString("B2"), rs.getString("B3"), rs.getString("B4"), rs.getString("B5"), rs.getString("B6"),rs.getString("B7"), rs.getString("B8"), rs.getString("B9"), rs.getString("B10"), rs.getString("B11"), rs.getString("B12")));
                    dataList.add(data);
                }
            }
            tableView.setItems(dataList);
            for (int i = 0; i < columns.length; i++) {
                columns[i].setCellValueFactory(new PropertyValueFactory<>("B" + (i + 1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchInTables(String searchTerm, String table1, String table2) {
        if (searchTerm.isEmpty()) {
            fillTablesWithAllData(table1, table2);
            return;
        }

        try {
            int id = Integer.parseInt(searchTerm);
            ObservableList<tableone> searchResultsOne = FXCollections.observableArrayList();
            ObservableList<tabletwo> searchResultsTwo = FXCollections.observableArrayList();

            pst = con.prepareStatement("SELECT id, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12 FROM " + table1 + " WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tableone data = new tableone(rs.getInt("id"), rs.getString("B1"), rs.getString("B2"), rs.getString("B3"), rs.getString("B4"), rs.getString("B5"), rs.getString("B6"),rs.getString("B7"), rs.getString("B8"), rs.getString("B9"), rs.getString("B10"), rs.getString("B11"), rs.getString("B12"));
                searchResultsOne.add(data);
            }

            pst = con.prepareStatement("SELECT id, B1, B2, B3, B4, B5, B6 , B7 , B8 , B9, B10 ,B11 , B12 FROM " + table2 + " WHERE id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                tabletwo data = new tabletwo(rs.getInt("id"), rs.getString("B1"), rs.getString("B2"), rs.getString("B3"), rs.getString("B4"), rs.getString("B5"), rs.getString("B6"),rs.getString("B7"), rs.getString("B8"), rs.getString("B9"), rs.getString("B10"), rs.getString("B11"), rs.getString("B12"));
                searchResultsTwo.add(data);
            }

            tableView.setItems(searchResultsOne);
            tableView1.setItems(searchResultsTwo);
        } catch (SQLException | NumberFormatException ex) {
            if (ex instanceof NumberFormatException) {
                showAlert("Ошибка ввода", "Введенное значение должно быть числом.");
            } else {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableView.getItems().clear();
            tableView1.getItems().clear();
        }
    }

    private void fillTablesWithAllData(String table1, String table2) {
        fillTableView("SELECT id, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12 FROM " + table1, tableView, new TableColumn[]{col_B1, col_B2, col_B3, col_B4, col_B5, col_B6, col_B7, col_B8, col_B9, col_B10, col_B11, col_B12 }, tableone.class);
        fillTableView("SELECT id, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12 FROM " + table2, tableView1, new TableColumn[]{col_B1x, col_B2x, col_B3x, col_B4x, col_B5x, col_B6x, col_B7x, col_B8x, col_B9x, col_B10x, col_B11x, col_B12x}, tabletwo.class);
    }

    public void updateAndRefreshTables() {
        fillTablesWithAllData(getTable1(tableSelector.getValue()), getTable2(tableSelector.getValue()));
    }

    private void switchTable(String selectedTable) {
        String table1 = getTable1(selectedTable);
        String table2 = getTable2(selectedTable);
        if (table1 == null || table2 == null) {
            return;
        }
        String searchTerm = searchField.getText();
        searchInTables(searchTerm, table1, table2);
    }

    private boolean tableExists(String tableName) {
        try {
            ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            return false;
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

    private String getTable1(String selectedTable) {
        String tableName = getTableName1(selectedTable);
        if (!tableExists(tableName)) {
            showAlert("Создайте таблицу " + tableName );
            return null;
        }
        return tableName;
    }

    private String getTable2(String selectedTable) {
        String tableName = getTableName2(selectedTable);
        if (!tableExists(tableName)) {
            showAlert("Создайте таблицу " + tableName );
            return null;
        }
        return tableName;
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Таблица не найдена");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void create_table(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("TableEditor.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 555, 608);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("Создать таблицу");
        stage.setScene(scene);
        stage.show();
    }

    public void changeRow(ActionEvent actionEvent) {
        Object selectedItem = null;
        String tableName = null;

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView.getSelectionModel().getSelectedItem();
            tableName = tableSelector.getValue();
        } else if (tableView1.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView1.getSelectionModel().getSelectedItem();
            tableName = tableSelector.getValue();
        }

        if (selectedItem != null && tableName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRow.fxml"));
                Parent root = loader.load();

                EditRowController controller = loader.getController();
                controller.setUpdateTableCallback(this::updateAndRefreshTables);
                String currentTable1 = getTable1(tableName);
                String currentTable2 = getTable2(tableName);
                controller.setSelectedItem(selectedItem, currentTable1, currentTable2);

                DataService dataService = new DataService(con);
                controller.setDataService(dataService);
                Stage stage = new Stage();
                stage.setTitle("Редактирование строки");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите строку для редактирования.");
        }
    }



    public void delete_table(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Delete.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 301, 234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("Удалить таблицу");
        stage.setScene(scene);
        stage.show();
    }


    public void deleteRow(ActionEvent actionEvent) {
        Object selectedItem = null;
        String tableName = null;
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView.getSelectionModel().getSelectedItem();
            tableName = tableSelector.getValue();
        } else if (tableView1.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView1.getSelectionModel().getSelectedItem();
            tableName = tableSelector.getValue();
        }

        // Удаляем запись
        if (selectedItem != null && tableName != null) {
            DataService dataService = new DataService(con); // Создаем экземпляр DataService
            if (selectedItem instanceof tableone) {
                dataService.deleteRecord(((tableone) selectedItem).getId(), getTable1(tableName)); // Вызываем метод deleteRecord через экземпляр
            } else if (selectedItem instanceof tabletwo) {
                dataService.deleteRecord(((tabletwo) selectedItem).getId(), getTable2(tableName)); // Вызываем метод deleteRecord через экземпляр
            }
            // Обновляем таблицы
            switchTable(tableName);
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите строку для удаления.");
        }
    }

    public void createRow(ActionEvent actionEvent) {
        String tableName = tableSelector.getValue();
        if (tableView.getSelectionModel().isEmpty() && tableView1.getSelectionModel().isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, выберите таблицу для создания записи.");
            return;
        }

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            // Создаем новую запись в первой таблице
            dataService.createRecord("", "", "", "", "", "", "", "", "", "", "", "", getTable1(tableName));
            // Обновляем таблицу
            switchTable(tableName);
        } else if (tableView1.getSelectionModel().getSelectedItem() != null) {
            // Создаем новую запись во второй таблице
            dataService.createRecord("", "", "", "", "", "","", "", "", "", "", "", getTable2(tableName));
            // Обновляем таблицу
            switchTable(tableName);
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите строку для создания записи.");
        }
    }

    public void handleOtherClick(javafx.scene.input.MouseEvent mouseEvent) {
        tableView.getSelectionModel().clearSelection();
        tableView1.getSelectionModel().clearSelection();
    }
}

