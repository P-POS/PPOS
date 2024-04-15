
import DBConnection.DBConnection;
import main.MainController;
import java.sql.*;


public class MartAPP {
    public static void main(String[] args) {

//
        DBConnection dbConnector = new DBConnection();
        Statement stmt = null;
        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM members;");
            resultSet.next();
            System.out.println(resultSet.getString(3));
            resultSet.next();
            System.out.println(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        new main.MainView(new MainController());

    }
}