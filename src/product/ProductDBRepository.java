package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dbConnection.DBConnection;

public class ProductDBRepository implements ProductDAO {

    private DBConnection dbConnection;
    private Statement statement;

    public ProductDBRepository() {

        this.dbConnection = new DBConnection();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertProduct(ProductDTO productDTO) {

        boolean success = false;
        try {
            String productName = productDTO.getProductName();
            int productPrice = productDTO.getProductPrice();
            int productQuantity = productDTO.getProductQuantity();
            int productNum = productDTO.getProductNum();

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
    public boolean updateProduct(ProductDTO productDTO) {

        boolean success = false;
        try {
            int productNum = productDTO.getProductNum();
            String productName = productDTO.getProductName();
            int productPrice = productDTO.getProductPrice();
            int productQuantity = productDTO.getProductQuantity();

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
    public ProductDTO selectProductByID(int productNum) {

        Product product = null;
        try {
            String query = String.format("SELECT * FROM products WHERE product_id = %d",
                productNum);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int productPrice = resultSet.getInt("product_price");
                int productQuantity = resultSet.getInt("product_stock");
                product = new Product(productId, productName, productPrice, productQuantity);
                ProductDTO productDTO = new ProductDTO(product);
                return productDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sellProduct(ArrayList<ProductOrderNumDTO> products) {

        try {
            for (int i = 0; i < products.size(); i++) {
                ProductDTO productDTO = products.get(i).getGetProductDTO();
                int productId = productDTO.getProductNum();
                int productOrderNum = products.get(i).getProductOrderNum();
                int productPrice = productDTO.getProductPrice();
                String query;
                if (productPrice > 0) {
                    query = String.format(
                        "update products set product_stock = product_stock - %d where product_id = %d;",
                        productOrderNum, productId);
                } else {
                    query = String.format(
                        "update products set product_stock = product_stock + %d where product_id = %d;",
                        productOrderNum, productId);
                }
                statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


