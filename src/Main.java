import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) {
        Connection con = null;

        String server = "172.16.1.218:3306";
        String database = "pposDB";
        String user_name = "ppos";
        String password = "ppos";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" +
                server + "/" +
                database +
                "?useSSL=false", user_name, password); // SSL 실행 확인
            System.out.println("연결 성공");

            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM User;");
            resultSet.next();

            System.out.println(resultSet.getString(2));

        } catch (SQLException e) {
            System.err.println("에러 내용 :" + e.getMessage());
            e.printStackTrace();
        }

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }
}