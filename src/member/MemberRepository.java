package member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public void createMember(MemberDTO member) {
        String query =
            "INSERT INTO members (member_name, member_score) VALUES ('" + member.getMemberName()
                + "', "
                + member.getMemberScore() + ")";
        try {
            stmt.executeUpdate(query);
            System.out.println("Member created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        String query = "DELETE FROM members WHERE member_id=" + memberId;
        try {
            stmt.executeUpdate(query);
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(MemberDTO member) {
        String query =
            "UPDATE members SET member_name='" + member.getMemberName() + "', member_score="
                + member.getMemberScore() + " WHERE member_id=" + member.getMemberId();
        try {
            stmt.executeUpdate(query);
            System.out.println("Member updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemberDTO getMember(int memberId) {
        String query = "SELECT * FROM members WHERE member_id=" + memberId;
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return new MemberDTO(
                    resultSet.getInt("member_id"),
                    resultSet.getString("member_name"),
                    resultSet.getInt("member_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> members = new ArrayList<>();
        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM members;");
            while (resultSet.next()) {
                int memberId = resultSet.getInt("member_id");
                String memberName = resultSet.getString("member_name");
                int memberScore = resultSet.getInt("member_score");
                members.add(new MemberDTO(memberId, memberName, memberScore));
            }
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
        return members;
    }
}
