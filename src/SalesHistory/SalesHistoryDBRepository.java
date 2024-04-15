package SalesHistory;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DBConnection.DBConnection;

public class SalesHistoryDBRepository implements SalesHistoryDAO {

    DBConnection dbConnection;
    Statement statement;

    public SalesHistoryDBRepository() {
        this.dbConnection = new DBConnection();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<SalesHistoryResDTO> getSalesHistories() {
        ArrayList<SalesHistory> salesHistories = new ArrayList<>();
        ArrayList<SalesHistoryResDTO> salesHistoriesResDTO = new ArrayList<>();
        try {
            String query = "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id FROM sales LEFT JOIN members ON sales.member_id = members.member_id ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("sale_id");
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                String memberName = resultSet.getString("member_name");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName, totalAmount, date);
                salesHistories.add(salesHistory);
                salesHistoriesResDTO.add(new SalesHistoryResDTO(salesHistory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoriesResDTO;
    }

    @Override
    public SalesHistoryResDTO getSalesHistoryByID(int transactionID) {
        SalesHistoryResDTO salesHistoryResDTO = null;
        try {
            String query = "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id"
            + "FROM sales LEFT JOIN members ON sales.member_id = members.member_id"
            + "WHERE sale_id = " + transactionID;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                String memberName = resultSet.getString("member_name");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName, totalAmount, date);
                salesHistoryResDTO = new SalesHistoryResDTO(salesHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoryResDTO;
    }

    @Override
    public SalesHistoryResDTO getSalesHistoryByMemberName(String memberName) {
        SalesHistoryResDTO salesHistoryResDTO = null;
        try {
            String query = "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id"
                + "FROM sales LEFT JOIN members ON sales.member_id = members.member_id"
                + "WHERE member_name = '" + memberName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int transactionID = resultSet.getInt("sale_id");
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName, totalAmount, date);
                salesHistoryResDTO = new SalesHistoryResDTO(salesHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoryResDTO;
    }



    @Override
    public void refundSalesHistory(int transactionID) {
        try {
            String query1 = "SELECT *"
                + "FROM sales"
                + "WHERE sale_id = " + transactionID;
            ResultSet resultSet = statement.executeQuery(query1);
            if (resultSet.next()) {
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                totalAmount = -totalAmount;
                String date = resultSet.getString("sale_date");
                String memberName = resultSet.getString("member_name");

                String query2 = String.format("INSERT INTO sales (sale_date,sale_total,member_id) VALUES (%s, %d, %d)",
                    date, totalAmount, memberNum);

                statement.executeQuery(query2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
