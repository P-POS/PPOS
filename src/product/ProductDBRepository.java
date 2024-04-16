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
    public boolean insertProduct(ProductReqDTO productReqDTO) {

        boolean success = false;
        try {
            String productName = productReqDTO.getProductName();
            int productPrice = productReqDTO.getProductPrice();
            int productQuantity = productReqDTO.getProductQuantity();
            int productNum = productReqDTO.getProductNum();

            String query = String.format(
                "INSERT INTO products (product_name, product_price, product_stock, product_id) VALUES ('%s', %d, %d, %d)",
                productName, productPrice, productQuantity, productNum);

            int rowsAffected = statement.executeUpdate(query);
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateProduct(ProductReqDTO productReqDTO) {

        boolean success = false;
        try {
            int productNum = productReqDTO.getProductNum();
            String productName = productReqDTO.getProductName();
            int productPrice = productReqDTO.getProductPrice();
            int productQuantity = productReqDTO.getProductQuantity();

            String query = String.format(
                "UPDATE products SET product_name = '%s', product_price = %d, product_stock = %d WHERE product_id = %d",
                productName, productPrice, productQuantity, productNum);

            int rowsAffected = statement.executeUpdate(query);
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteProduct(int productNum) {

        boolean success = false;
        try {
            String query = String.format("DELETE FROM products WHERE product_id = %d", productNum);
            int rowsAffected = statement.executeUpdate(query);
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
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

                Product product = new Product(productNum, productName, productPrice,
                    productQuantity);
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
            String query = String.format("SELECT * FROM products WHERE product_id = %d",
                productNum);
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


