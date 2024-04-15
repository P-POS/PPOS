package product;

import java.util.ArrayList;

public class ProductService {

    ProductDBRepository productDBRepository = new ProductDBRepository();

    public ProductResDTO getProduct(int productNum){
        return new ProductResDTO(productDBRepository.selectProductByID(productNum));
    }

    public ArrayList<ProductResDTO> getProductList(){
        return productDBRepository.selectProductList();
    }

    public void addProduct(ProductReqDTO productReqDTO){
        productDBRepository.insertProduct(productReqDTO);
    }

    public void updateProduct(ProductReqDTO productReqDTO){
        productDBRepository.updateProduct(productReqDTO);
    }

    public void deleteProduct(int productNum){
        productDBRepository.deleteProduct(productNum);
    }



}
