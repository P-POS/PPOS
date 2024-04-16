package product;

import main.MainController;

import java.util.ArrayList;
import main.MainController;

public class ProductController {

    private ProductView productView;
    private ProductService productService;
    private MainController mainController;

    ProductController(){
        mainController = new MainController();
        productView = new ProductView();
        productService = new ProductService();
    }

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
