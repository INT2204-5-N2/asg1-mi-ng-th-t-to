package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String path = "jdbc:sqlite:./src/Res/EV_dict.db";
            // create a connection to the database
            conn = DriverManager.getConnection(path);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    public static void main(String[] args) {

        Connection connection = connect();
        //connection.createStatement().executeUpdate()
    }
}
