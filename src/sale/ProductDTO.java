package sale;

public class ProductDTO {
    private final String productName;
    private final int productPrice;
    private final int productStock;
    private final int productId;

    public ProductDTO(int productId , int productPrice, int productStock,String productName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public int getProductId() {
        return productId;
    }
}
