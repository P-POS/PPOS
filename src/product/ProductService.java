package product;

import java.util.ArrayList;

public class ProductService {

    private ProductDBRepository productDBRepository;

    ProductService() {

        productDBRepository = new ProductDBRepository();
    }

    public ProductResDTO getProduct(int productNum) {

        return new ProductResDTO(productDBRepository.selectProductByID(productNum));
    }

    public ArrayList<ProductResDTO> getProductList() {

        return productDBRepository.selectProductList();
    }

    public boolean addProduct(ProductReqDTO productReqDTO) {

        return productDBRepository.insertProduct(productReqDTO);
    }

    public boolean updateProduct(ProductReqDTO productReqDTO) {

        return productDBRepository.updateProduct(productReqDTO);
    }

    public boolean deleteProduct(int productNum) {

        return productDBRepository.deleteProduct(productNum);
    }


}
