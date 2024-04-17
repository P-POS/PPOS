package member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dbConnection.DBConnection;

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
            stmt = dbConnector.createStatement();
            stmt.executeUpdate(query);
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
    public void deleteMember(int memberId) {

        dbConnector = new DBConnection();
        String query = "DELETE FROM members WHERE member_id=" + memberId;
        try {
            stmt = dbConnector.createStatement();
            stmt.executeUpdate(query);
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
    public void updateMember(MemberDTO member) {

        dbConnector = new DBConnection();
        String query =
            "UPDATE members SET member_name='" + member.getMemberName() + "', member_score="
                + member.getMemberScore() + " WHERE member_id=" + member.getMemberId();
        try {
            stmt = dbConnector.createStatement();
            stmt.executeUpdate(query);
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
    public ArrayList<MemberDTO> getMember(int memberId) {

        dbConnector = new DBConnection();
        ArrayList<MemberDTO> members = new ArrayList<>();
        String query = "SELECT * FROM members WHERE member_id = " + memberId;
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
    public ArrayList<MemberDTO> getMemberUseId(int memberId) {

        dbConnector = new DBConnection();
        ArrayList<MemberDTO> members = new ArrayList<>();
        String query =
            "SELECT * FROM members WHERE CAST(member_id AS CHAR) LIKE '%" + memberId + "%'";
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
    public MemberDTO getMemberInfo(int memberId) {

        dbConnector = new DBConnection();
        try {
            stmt = dbConnector.createStatement();
            String query = String.format("SELECT * FROM members where member_id = %d;", memberId);
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();

            int memeberId = resultSet.getInt(1);
            String memberName = resultSet.getString(2);
            int memberScore = resultSet.getInt(3);

            return new MemberDTO(memberId, memberName, memberScore);
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

        return null;
    }

    @Override
    public void stackPoint(int memberId, int score) {

        dbConnector = new DBConnection();
        try {
            stmt = dbConnector.createStatement();
            String query = String.format(
                "update members set member_score = member_score + %d where member_id = %d;", score,
                memberId);
            ResultSet resultSet = stmt.executeQuery(query);
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
    public void usePoint(MemberDTO memberDTO, int score) {

        dbConnector = new DBConnection();
        try {
            stmt = dbConnector.createStatement();
            String query = String.format(
                "update members set member_score = member_score - %d where member_id = %d;", score,
                memberDTO.getMemberId());
            ResultSet resultSet = stmt.executeQuery(query);
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
}
