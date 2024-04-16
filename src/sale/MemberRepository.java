package sale;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MemberRepository implements MemberDAO {

    DBConnection dbConnection;
    Statement statement;

    public MemberRepository() {

        this.dbConnection = new DBConnection();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemberDTO getMemberInfo(int memberId) {

        try {
            String query = String.format("SELECT * FROM members where member_id = %d;", memberId);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            int memeberId = resultSet.getInt(1);
            String memberName = resultSet.getString(2);
            int memberScore = resultSet.getInt(3);

            return new MemberDTO(memberName, memberId, memberScore);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void stackPoint(int memberId, int score) {

        try {
            String query = String.format(
                "update members set member_score = member_score + %d where member_id = %d;", score,
                memberId);
            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void usePoint(MemberDTO memberDTO, int score) {

        try {

            String query = String.format(
                "update members set member_score = member_score - %d where member_id = %d;", score,
                memberDTO.getClientId());
            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        dbConnection.closeConnection();
    }
}
