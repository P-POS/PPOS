package product;

import main.MainController;

import java.util.ArrayList;

public class ProductController {

    private MainController mainController;
    ProductService productService = new ProductService();

//    public ProductController(MainController mainController){
//        this.mainController = mainController;
//    }

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
        productService.addProduct(new ProductReqDTO(product));
    }

    public void updateProduct(Product product) {
        productService.updateProduct(new ProductReqDTO(product));
    }

    public void deleteProduct(int productNum) {
        productService.deleteProduct(productNum);
    }

}
