package sale;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    try{
        if(saleDTO.getMemberId()!=-1)
        {SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = formatter.format(saleDTO.getDate());
        String query = String.format("insert into sales(sale_date,sale_total,member_id) values (\"%s\", %d,%d)"
            ,formatDate,saleDTO.getTotalSale(),saleDTO.getMemberId());
        ResultSet resultSet = statement.executeQuery(query);}
        else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = formatter.format(saleDTO.getDate());
            String query = String.format("insert into sales(sale_date,sale_total) values (\"%s\", %d)"
                ,formatDate,saleDTO.getTotalSale());
            ResultSet resultSet = statement.executeQuery(query);
        }
    }
    catch (SQLException e){
        e.printStackTrace();
    }
    }
}
