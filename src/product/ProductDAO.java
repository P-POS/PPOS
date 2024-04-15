package product;

import java.util.ArrayList;

public interface ProductDAO   {
    boolean insertProduct(ProductReqDTO productReqDTO);
    boolean updateProduct(ProductReqDTO productReqDTO);
    boolean deleteProduct(int productNum);
    ArrayList<ProductResDTO> selectProductList();
    Product selectProductByID(int productNum);
}
