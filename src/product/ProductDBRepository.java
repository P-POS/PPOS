package product;

import java.util.ArrayList;

public class ProductDBRepository implements ProductDAO {

    public void DBConnectTest(){

    }

    @Override
    public void insertProduct(ProductReqDTO productReqDTO) {

    }

    @Override
    public void updateProduct(ProductReqDTO productReqDTO) {

    }

    @Override
    public void deleteProduct(int productNum) {

    }

    @Override
    public ArrayList<ProductResDTO> selectProductList() {

        ArrayList<Product> products = new ArrayList<>();

        ArrayList<ProductResDTO> productResDTOs = new ArrayList<>();

        for (Product product : products) {
            productResDTOs.add(new ProductResDTO(product));
        }

        return productResDTOs;
    }

    @Override
    public Product selectProductByID(int productNum) {
        Product product = null;
        return null;
    }
}
