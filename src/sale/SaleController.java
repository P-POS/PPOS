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
            return key;
        }
    }

    public MemberDTO getMemberInfo(int memberId){
        return salesService.getMemberInfo(memberId);
    }

    public String sellSaleUsePoint(int point){
        sellSale();
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
