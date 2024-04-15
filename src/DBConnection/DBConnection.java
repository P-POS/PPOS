package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private Connection con;

    public DBConnection() {
        String server = "172.16.1.218";
        String database = "pposDB";
        String user_name = "ppos";
        String password = "ppos";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" +
                    server + "/" +
                    database +
                    "?useSSL=false", user_name, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement createStatement() throws SQLException {
        if (con != null) {
            return con.createStatement();
        } else {
            throw new SQLException("Connection is null");
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

