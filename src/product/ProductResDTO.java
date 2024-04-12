package product;

public class ProductResDTO {
    private String productName;
    private int productPrice;
    private int productQuantity;
    private int productNum;

    public ProductResDTO(Product product) {
        productName = product.getProductName();
        productPrice = product.getProductPrice();
        productQuantity = product.getProductQuantity();
        productNum = product.getProductNum();
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductNum() {
        return productNum;
    }

}
