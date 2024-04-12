package product;

public class ProductReqDTO {
    private String productName;
    private int productPrice;
    private int productQuantity;
    private int productNum;

    public ProductReqDTO(String productName, int productPrice, int productQuantity, int productNum) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productNum = productNum;
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