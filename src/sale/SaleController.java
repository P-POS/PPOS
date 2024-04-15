package sale;
import java.util.ArrayList;


public class SaleController {
    SalesService salesService;
    MemberDTO memberDTO;
    SaleController(SalesService salesService){
        this.salesService = salesService;
//        new SaleView(this);
    }

    public String sellSale(){
        String key = salesService.sellSale();
        if(key=="success"){
            return key;
        }
        else{
            // 재고가 부족해서 못 팔때
            // ex) return String.format("%s의 재고가 부족합니다.",tempProduct.productDTO.getProductName());
            return key;
        }
    }

    public MemberDTO getMemberInfo(int memberId){
        return salesService.getMemberInfo(memberId);
    }

    public String sellSaleUsePoint(int point){
        String key = salesService.sellSaleUsePoint(point);
        if(key == "pointFail"){
            //포인트가 부족할때
            return key;
        }
        else if(key == "success"){
            // 성공했을 떄
            return key;
        }
        else{
            // return String.format("%s의 재고가 부족합니다.",tempProduct.productDTO.getProductName());
            return key;
        }
    }
    public ArrayList<ProductOrderNumDTO> getProductInfo(int productId){
        return salesService.getProductInfo(productId);
    }

    public ArrayList<ProductOrderNumDTO> cancleProduct(int sequence){
        return salesService.cancleProduct(sequence);
    }
    public  ArrayList<ProductOrderNumDTO> cancleProducts(){
        return salesService.cancleProducts();
    }
}
