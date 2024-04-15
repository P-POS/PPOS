package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DBConnection.DBConnection;

public class ProductDBRepository implements ProductDAO {

    DBConnection dbConnection;
    Statement statement;

    public ProductDBRepository() {
        this.dbConnection = new DBConnection();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(ProductReqDTO productReqDTO) {
        try {
            String productName = productReqDTO.getProductName();
            int productPrice = productReqDTO.getProductPrice();
            int productQuantity = productReqDTO.getProductQuantity();

            String query = String.format("INSERT INTO products (product_name, product_price, product_stock) VALUES ('%s', %d, %d)",
                productName, productPrice, productQuantity);

            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(ProductReqDTO productReqDTO) {
        try {
            int productNum = productReqDTO.getProductNum();
            String productName = productReqDTO.getProductName();
            int productPrice = productReqDTO.getProductPrice();
            int productQuantity = productReqDTO.getProductQuantity();

            String query = String.format("UPDATE products SET product_name = '%s', product_price = %d, product_stock = %d WHERE product_id = %d",
                productName, productPrice, productQuantity, productNum);

            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productNum) {
        try {
            String query = String.format("DELETE FROM products WHERE product_num = %d", productNum);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ProductResDTO> selectProductList() {
        ArrayList<ProductResDTO> productResDTOs = new ArrayList<>();
        try {
            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int productNum = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int productPrice = resultSet.getInt("product_price");
                int productQuantity = resultSet.getInt("product_stock");

                Product product = new Product(productNum, productName, productPrice, productQuantity);
                productResDTOs.add(new ProductResDTO(product));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productResDTOs;
    }


    @Override
    public Product selectProductByID(int productNum) {
        Product product = null;
        try {
            String query = String.format("SELECT * FROM products WHERE product_id = %d", productNum);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int productId = resultSet.getInt("product_num");
                String productName = resultSet.getString("product_name");
                int productPrice = resultSet.getInt("product_price");
                int productQuantity = resultSet.getInt("product_quantity");
                product = new Product(productId, productName, productPrice, productQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}


