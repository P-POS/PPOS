package product;

import java.util.ArrayList;

public interface ProductDAO   {
    void insertProduct(ProductReqDTO productReqDTO);
    void updateProduct(ProductReqDTO productReqDTO);
    void deleteProduct(int productNum);
    ArrayList<ProductResDTO> selectProductList();
    Product selectProductByID(int productNum);
}
