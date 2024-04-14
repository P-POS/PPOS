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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM members;");
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
        try {
            String query = "INSERT INTO members (member_name, member_score) VALUES ('" + memberName + "', 0)";
            stmt.executeUpdate(query);
            System.out.println("Member created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        try {
            String query = "DELETE FROM members WHERE member_id=" + memberId;
            stmt.executeUpdate(query);
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(int memberId, String memberName, int memberScore) {
        try {
            String query = "UPDATE members SET member_name='" + memberName + "', member_score=" + memberScore
                    + " WHERE member_id=" + memberId;
            stmt.executeUpdate(query);
            System.out.println("Member updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMemberName(int memberId) {
        try {
            String query = "SELECT member_name FROM members WHERE member_id=" + memberId;
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("member_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setMemberName(int memberId, String memberName) {
        try {
            String query = "UPDATE members SET member_name='" + memberName + "' WHERE member_id=" + memberId;
            stmt.executeUpdate(query);
            System.out.println("Member name updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMemberScore(int memberId) {
        try {
            String query = "SELECT member_score FROM members WHERE member_id=" + memberId;
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt("member_score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setMemberScore(int memberId, int memberScore) {
        try {
            String query = "UPDATE members SET member_score=" + memberScore + " WHERE member_id=" + memberId;
            stmt.executeUpdate(query);
            System.out.println("Member score updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
