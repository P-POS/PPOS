package product;

public class Product {
    private int productNum;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public Product(int productNum, String productName, int productPrice, int productQuantity) {
        this.productNum = productNum;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public Product(ProductResDTO productResDTO) {
        this.productNum = productResDTO.getProductNum();
        this.productName = productResDTO.getProductName();
        this.productPrice = productResDTO.getProductPrice();
        this.productQuantity = productResDTO.getProductQuantity();
    }

    public Product(ProductReqDTO productReqDTO) {
        this.productNum = productReqDTO.getProductNum();
        this.productName = productReqDTO.getProductName();
        this.productPrice = productReqDTO.getProductPrice();
        this.productQuantity = productReqDTO.getProductQuantity();
    }

    public int getProductNum() {
        return productNum;
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
}

