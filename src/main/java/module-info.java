module main.basekriteriev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.sql.rowset;


    opens main.basekriteriev to javafx.fxml;
    exports main.basekriteriev;
    exports main.basekriteriev.tables;
    opens main.basekriteriev.tables to javafx.fxml;

}