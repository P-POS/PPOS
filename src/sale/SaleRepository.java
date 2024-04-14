package sale;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleRepository implements SaleDAO{

    DBConnection dbConnection;
    Statement statement;

    public SaleRepository() {
        this.dbConnection = new DBConnection();
        try{
            statement = dbConnection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void sellSale(SaleDTO saleDTO) {
    try{    String query = String.format("insert into sales(sale_date,sale_total,member_id) values ( %tH, %d,%d"
            ,saleDTO.getDate(),saleDTO.getTotalSale(),saleDTO.getMemberId());
        ResultSet resultSet = statement.executeQuery(query);}
    catch (SQLException e){
        e.printStackTrace();
    }
    }
}
