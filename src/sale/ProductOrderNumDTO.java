package sale;

public class ProductOrderNumDTO {
        ProductDTO productDTO;
        int productOrderNum;

    public ProductOrderNumDTO(ProductDTO productDTO, int productOrderNum) {
        this.productDTO = productDTO;
        this.productOrderNum = productOrderNum;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public int getProductOrderNum() {
        return productOrderNum;
    }
}
