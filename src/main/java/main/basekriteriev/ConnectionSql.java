package main.basekriteriev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSql {
    private final String dbname = "base_kriteriev";
    private final String dbuser = "root";
    private final String dbpass = "sql1";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, dbuser, dbpass);
    }
}