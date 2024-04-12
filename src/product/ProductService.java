package product;

import java.util.ArrayList;

public class ProductService {
    public ArrayList<ProductResDTO> getProductList(ArrayList<Product> products){

        ArrayList<ProductResDTO> resProducts = new ArrayList<>();

        for (Product product : products) {
            resProducts.add(new ProductResDTO(product));
        }

        return resProducts;
    }

    public Product addProduct(ProductResDTO productResDTO){
        return new Product(productResDTO);
    }

    public Product updateProduct(ProductResDTO productResDTO){
        return new Product(productResDTO);
    }

    public Product deleteProduct(ProductResDTO productResDTO){
        return new Product(productResDTO);
    }



}
