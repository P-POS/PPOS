package sale;

import java.util.ArrayList;
import main.MainController;

import product.ProductOrderNumDTO;
import product.ProductDTO;
public class SaleController {

    SalesService salesService;
    MainController mainController;

    public SaleController(MainController mainController) {

        this.mainController = mainController;
        this.salesService = new SalesService();
        new SaleView(this);
    }

    public void openMainPage() {

        mainController.openMainPage();
    }

    public String sellSale() {

        String key = salesService.sellSale();
        if (key == "success") {
            return key;
        } else {
            // 재고가 부족해서 못 팔때
            // ex) return String.format("%s의 재고가 부족합니다.",tempProduct.productDTO.getProductName());
            return key;
        }
    }

    public member.MemberDTO getMemberInfo(int memberId) {
        return salesService.getMemberInfo(memberId);
    }

    public String sellSaleUsePoint(int point) {

        String key = salesService.sellSaleUsePoint(point);
        if (key == "pointFail") {
            //포인트가 부족할때
            return key;
        } else if (key == "success") {
            // 성공했을 떄
            return key;
        } else {
            // return String.format("%s의 재고가 부족합니다.",tempProduct.productDTO.getProductName());
            return key;
        }
    }

    public ArrayList<ProductOrderNumDTO> getProductInfo(int productId) {

        return salesService.getProductInfo(productId);
    }

    public ArrayList<ProductOrderNumDTO> cancleProduct(int sequence) {
        return salesService.cancleProduct(sequence);
    }

    public ArrayList<ProductOrderNumDTO> cancleProducts() {

        return salesService.cancleProducts();
    }

    public ArrayList<ProductOrderNumDTO> returnProducts(int sequence) {

        return salesService.returnProduct(sequence);
    }

    public ArrayList<ProductOrderNumDTO> updateOrderNum(int sequence, int orderNum) {

        return salesService.updateOrderNum(sequence, orderNum);
    }

    public int usePoint(int score) {

        return salesService.usePoint(score);
    }

    public int getTotal() {

        return salesService.getTotal();
    }

    public void setMemberDTONull() {

        salesService.setMemberDTONull();
    }

    public String getLatestSaleDate(int memberId) {

        String latestSaleDate = salesService.getLatestSaleDate(memberId);
        if (latestSaleDate != null) {
            return latestSaleDate;
        } else {
            return null;
        }
    }
}