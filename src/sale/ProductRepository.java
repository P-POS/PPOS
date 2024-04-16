package sale;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductRepository implements ProductDAO {

    DBConnection dbConnection;
    Statement statement;

    public ProductRepository() {
        this.dbConnection = new DBConnection();
        try{
            statement = dbConnection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ProductDTO getProductInfo(int productId) {
        try{
            String query = String.format("SELECT * FROM products where product_id = %d",productId);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int productID = resultSet.getInt(1);
            int productPrice = resultSet.getInt(2);
            int productStock = resultSet.getInt(3);
            String productName = resultSet.getString(4);
            return new ProductDTO(productID,productPrice,productStock,productName);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sellProduct(ArrayList<ProductOrderNumDTO> products) {
        try {
            for (int i = 0; i < products.size(); i++) {
                ProductDTO productDTO = products.get(i).getProductDTO();
                int productId = productDTO.getProductId();
                int productOrderNum = products.get(i).getProductOrderNum();
                int productPrice = productDTO.getProductPrice();
                String query;
                if(productPrice>0)
                {query = String.format("update products set product_stock = product_stock - %d where product_id = %d;",productOrderNum, productId);}
                else{
                    query = String.format("update products set product_stock = product_stock + %d where product_id = %d;",productOrderNum, productId);
                }
                statement.executeQuery(query);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
