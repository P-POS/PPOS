package member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DBConnection.DBConnection;

public class MemberRepository implements MemberDAO {

    DBConnection dbConnector;
    Statement stmt;

    @Override
    public void createMember(MemberDTO member) {
        dbConnector = new DBConnection();
        String query =
            "INSERT INTO members (member_name, member_id) VALUES ('" + member.getMemberName()
                + "', "
                + member.getMemberId() + ")";
        try {
            stmt.executeUpdate(query);
            System.out.println("Member created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        dbConnector = new DBConnection();
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
        dbConnector = new DBConnection();
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
    public ArrayList<MemberDTO> getMember(int memberId) {
        dbConnector = new DBConnection();
        ArrayList<MemberDTO> members = new ArrayList<>();
        String query = "SELECT * FROM members WHERE member_id=" + memberId;
        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int rmemberId = resultSet.getInt("member_id");
                String rmemberName = resultSet.getString("member_name");
                int rmemberScore = resultSet.getInt("member_score");
                members.add(new MemberDTO(rmemberId, rmemberName, rmemberScore));
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

    @Override
    public ArrayList<MemberDTO> getMemberUseName(String memberName) {
        dbConnector = new DBConnection();
        ArrayList<MemberDTO> members = new ArrayList<>();
        String query = "SELECT * FROM members WHERE member_name='" + memberName + "'";
        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int rmemberId = resultSet.getInt("member_id");
                String rmemberName = resultSet.getString("member_name");
                int rmemberScore = resultSet.getInt("member_score");
                members.add(new MemberDTO(rmemberId, rmemberName, rmemberScore));
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

    @Override
    public ArrayList<MemberDTO> getAllMembers() {
        dbConnector = new DBConnection();
        ArrayList<MemberDTO> members = new ArrayList<>();
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

    @Override
    public String getLatestSaleDate(int memberId) {
        dbConnector = new DBConnection();
        String query =
            "SELECT * FROM sales WHERE member_id=" + memberId + " ORDER BY sale_date DESC LIMIT 1";
        try {
            stmt = dbConnector.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("sale_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
