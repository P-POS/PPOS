package sale;

import java.util.ArrayList;


public interface ProductDAO {

    ProductDTO getProductInfo(int productId);

    void sellProduct(ArrayList<ProductOrderNumDTO> productS);
}
