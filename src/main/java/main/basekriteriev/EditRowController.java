package main.basekriteriev;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditRowController {

    @FXML
    private TextField b1Field;
    @FXML
    private TextField b2Field;
    @FXML
    private TextField b3Field;
    @FXML
    private TextField b4Field;
    @FXML
    private TextField b5Field;
    @FXML
    private TextField b6Field;
    @FXML
    public TextField b7Field;
    @FXML
    public TextField b8Field;
    @FXML
    public TextField b9Field;
    @FXML
    public TextField b10Field;
    @FXML
    public TextField b11Field;
    @FXML
    public TextField b12Field;

    private Object selectedItem;
    private String currentTable1;
    private String currentTable2;
    private UpdateTableCallback updateTableCallback;

    private DataService dataService; // Экземпляр DataService

    public interface UpdateTableCallback {
        void updateAndRefreshTables();
    }
    public void setSelectedItem(Object selectedItem, String currentTable1, String currentTable2) {
        this.selectedItem = selectedItem;
        this.currentTable1 = currentTable1;
        this.currentTable2 = currentTable2;

        if (selectedItem instanceof tableone) {
            tableone item = (tableone) selectedItem;
            b1Field.setText(item.getB1());
            b2Field.setText(item.getB2());
            b3Field.setText(item.getB3());
            b4Field.setText(item.getB4());
            b5Field.setText(item.getB5());
            b6Field.setText(item.getB6());
            b7Field.setText(item.getB7());
            b8Field.setText(item.getB8());
            b9Field.setText(item.getB9());
            b10Field.setText(item.getB10());
            b11Field.setText(item.getB11());
            b12Field.setText(item.getB12());


        } else if (selectedItem instanceof tabletwo) {
            tabletwo item = (tabletwo) selectedItem;
            b1Field.setText(item.getB1());
            b2Field.setText(item.getB2());
            b3Field.setText(item.getB3());
            b4Field.setText(item.getB4());
            b5Field.setText(item.getB5());
            b6Field.setText(item.getB6());
            b7Field.setText(item.getB7());
            b8Field.setText(item.getB8());
            b9Field.setText(item.getB9());
            b10Field.setText(item.getB10());
            b11Field.setText(item.getB11());
            b12Field.setText(item.getB12());
        }
    }
    public void setUpdateTableCallback(UpdateTableCallback updateTableCallback) {
        this.updateTableCallback = updateTableCallback;
    }

    @FXML
    private void handleSaveButtonClicked() {
        String b1 = b1Field.getText();
        String b2 = b2Field.getText();
        String b3 = b3Field.getText();
        String b4 = b4Field.getText();
        String b5 = b5Field.getText();
        String b6 = b6Field.getText();
        String b7 = b7Field.getText();
        String b8 = b8Field.getText();
        String b9 = b9Field.getText();
        String b10 = b10Field.getText();
        String b11 = b11Field.getText();
        String b12 = b12Field.getText();

        String tableName = (selectedItem instanceof tableone) ? currentTable1 : currentTable2;

        dataService.updateRecord(selectedItem, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, tableName);

        if (updateTableCallback != null) {
            updateTableCallback.updateAndRefreshTables();
        }
        // Закрыть окно после сохранения
        Stage stage = (Stage) b1Field.getScene().getWindow();
        stage.close();
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}
