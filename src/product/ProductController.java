package product;

import main.MainController;
import java.util.ArrayList;
import main.MainController;

public class ProductController {
    private MainController mainController;
    private ProductService productService;

    public ProductController(MainController mainController) {
        productService = new ProductService();
        this.mainController = mainController;
        new ProductView(this);
    }

    public void openMainPage(){
        mainController.openMainPage();
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
