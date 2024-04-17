package product;

import java.util.ArrayList;

public interface ProductDAO {

    boolean insertProduct(ProductDTO productDTO);

    boolean updateProduct(ProductDTO productDTO);

    boolean deleteProduct(int productNum);

    ArrayList<ProductResDTO> selectProductList();

    ProductDTO selectProductByID(int productNum);

    void sellProduct(ArrayList<ProductOrderNumDTO> productS);
}
