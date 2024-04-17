package sale;

import dbConnection.DBConnection;

import salesHistory.SalesHistory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import salesHistory.SalesHistoryDTO;

public class SaleRepository implements SaleDAO {

    DBConnection dbConnection;
    Statement statement;

    public SaleRepository() {
        this.dbConnection = new DBConnection();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sellSale(SaleDTO saleDTO) {

        try {
            if (saleDTO.getMemberId() != -1) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = formatter.format(saleDTO.getDate());
                String query = String.format(
                    "insert into sales(sale_date,sale_total,member_id) values (\"%s\", %d,%d)"
                    , formatDate, saleDTO.getTotalSale(), saleDTO.getMemberId());
                ResultSet resultSet = statement.executeQuery(query);
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = formatter.format(saleDTO.getDate());
                String query = String.format(
                    "insert into sales(sale_date,sale_total) values (\"%s\", %d)"
                    , formatDate, saleDTO.getTotalSale());
                statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLatestSaleDate(int memberId) {

        String query =
            "SELECT * FROM sales WHERE member_id=" + memberId + " ORDER BY sale_date DESC LIMIT 1";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("sale_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean refundSalesHistory(int transactionID) {

        boolean success = false;
        try {
            String query1 = "SELECT * " +
                "FROM sales " +
                "WHERE sale_id = " + transactionID;
            ResultSet resultSet = statement.executeQuery(query1);
            if (resultSet.next()) {
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                totalAmount = -totalAmount;
                String date = resultSet.getString("sale_date");

                String query2 = String.format(
                    "INSERT INTO sales (sale_date, sale_total, member_id) VALUES ('%s', %d, %d)",
                    date, totalAmount, memberNum);

                int rowsAffected = statement.executeUpdate(query2);
                success = rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;

    }

    @Override
    public SalesHistoryDTO getSalesHistoryByMemberName(String memberName) {
        SalesHistoryDTO salesHistoryDTO = null;
        try {
            String query =
                "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id " +
                    "FROM sales " +
                    "LEFT JOIN members ON sales.member_id = members.member_id " +
                    "WHERE member_name = '" + memberName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int transactionID = resultSet.getInt("sale_id");
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName,
                    totalAmount, date);
                salesHistoryDTO = new SalesHistoryDTO(salesHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoryDTO;
    }

    @Override
    public ArrayList<SalesHistoryDTO> getSalesHistories() {
        ArrayList<SalesHistory> salesHistories = new ArrayList<>();
        ArrayList<SalesHistoryDTO> salesHistoriesResDTO = new ArrayList<>();
        try {
            String query = "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id FROM sales LEFT JOIN members ON sales.member_id = members.member_id ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("sale_id");
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                String memberName = resultSet.getString("member_name");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName,
                    totalAmount, date);
                salesHistories.add(salesHistory);
                salesHistoriesResDTO.add(new SalesHistoryDTO(salesHistory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoriesResDTO;
    }

    @Override
    public SalesHistoryDTO getSalesHistoryByID(int transactionID) {
        SalesHistoryDTO salesHistoryDTO = null;
        try {
            String query =
                "SELECT sale_id, sale_total, sale_date, members.member_name, sales.member_id " +
                    "FROM sales " +
                    "LEFT JOIN members ON sales.member_id = members.member_id " +
                    "WHERE sale_id = " + transactionID;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int totalAmount = resultSet.getInt("sale_total");
                int memberNum = resultSet.getInt("member_id");
                String date = resultSet.getString("sale_date");
                String memberName = resultSet.getString("member_name");
                SalesHistory salesHistory = new SalesHistory(transactionID, memberNum, memberName,
                    totalAmount, date);
                salesHistoryDTO = new SalesHistoryDTO(salesHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesHistoryDTO;
    }
}