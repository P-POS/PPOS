package member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberRepository implements MemberDAO {

    DBConnection dbConnector;
    Statement stmt;

    MemberRepository() {
        dbConnector = new DBConnection();

        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM User;");
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
    }

    @Override
    public void createMember(String memberName) {

    }

    @Override
    public void deleteMember(int memberId) {

    }

    @Override
    public void updateMember(int memberId, String memberName, int memberPoint) {

    }

    @Override
    public String getMemberName(int memberId) {
        return null;
    }

    @Override
    public void setMemberName(int memberId, String memberName) {

    }

    @Override
    public int getMemberPoint(int memberId) {
        return 0;
    }

    @Override
    public void setMemberPoint(int memberId, int memberPoint) {

    }
}
