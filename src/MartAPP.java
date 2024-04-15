import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.MainController;
import sale.SaleView;


public class MartAPP {

    public static void main(String[] args) {

        Connection con = null;

        SaleView saleView = new SaleView();

        String server = "172.16.1.218:3306";
        String database = "pposDB";
        String user_name = "ppos";
        String password = "ppos";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();

            DBConnection dbConnector = new DBConnection();
            Statement stmt = null;
            try {
                stmt = dbConnector.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM User;");
                resultSet.next();
                System.out.println(resultSet.getString(2));
            } catch (SQLException e2) {
                e2.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                dbConnector.closeConnection();
            }
            new main.MainView(new MainController());
        }
    }
}