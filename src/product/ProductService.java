package product;

import java.util.ArrayList;

public class ProductService {

    private ProductDBRepository productDBRepository;

    ProductService() {

        productDBRepository = new ProductDBRepository();
    }

    public ProductDTO getProduct(int productNum) {

        return productDBRepository.selectProductByID(productNum);
    }

    public ArrayList<ProductResDTO> getProductList() {

        return productDBRepository.selectProductList();
    }

    public boolean addProduct(ProductDTO productDTO) {

        return productDBRepository.insertProduct(productDTO);
    }

    public boolean updateProduct(ProductDTO productDTO) {

        return productDBRepository.updateProduct(productDTO);
    }

    public boolean deleteProduct(int productNum) {

        return productDBRepository.deleteProduct(productNum);
    }


}
