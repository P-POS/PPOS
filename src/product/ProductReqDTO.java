package product;

public class ProductReqDTO {
    private String productName;
    private int productPrice;
    private int productQuantity;
    private int productNum;

    public ProductReqDTO(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productQuantity = product.getProductQuantity();
        this.productNum = product.getProductNum();
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
