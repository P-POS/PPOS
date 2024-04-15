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

    public boolean addProduct(Product product) {
        return productService.addProduct(new ProductReqDTO(product));
    }

    public boolean updateProduct(Product product) {
        return productService.updateProduct(new ProductReqDTO(product));
    }

    public boolean deleteProduct(int productNum) {
        return productService.deleteProduct(productNum);
    }

}
