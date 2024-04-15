package product;

import java.util.ArrayList;

public class ProductController {

    ProductService productService = new ProductService();

    public Product getProduct(int productNum) {
       return new Product(productService.getProduct(productNum));
    }

    public ArrayList<Product> getProducts() {

        ArrayList<ProductResDTO> productResDTOs = productService.getProductList();

        ArrayList<Product> products = new ArrayList<>();

        for (ProductResDTO productResDTO : productResDTOs) {
            products.add(new Product(productResDTO));
        }
        return products;
    }

    public void addProduct(Product product) {
//        productService.addProduct(new ProductReqDTO(product));
    }

    public void updateProduct(Product product) {
//        productService.updateProduct(new ProductReqDTO(product));
    }

    public void deleteProduct(int productNum) {
        productService.deleteProduct(productNum);
    }

}
