package product;

public class ProductOrderNumDTO {

    private ProductDTO getProductDTO;
    int productOrderNum;

    public ProductOrderNumDTO(ProductDTO getProductDTO, int productOrderNum) {

        this.getProductDTO = getProductDTO;
        this.productOrderNum = productOrderNum;
    }

    public ProductDTO getGetProductDTO() {
        return getProductDTO;
    }

    public int getProductOrderNum() {
        return productOrderNum;
    }

    public void setProductOrderNum(int num) {

        this.productOrderNum = num;
    }

    public void setRefundProduct() {

        this.getProductDTO.setRefundProduct();
    }

    public void setOrderNum(int orderNum) {
        this.getProductDTO.setProductStock(orderNum);
    }
}
