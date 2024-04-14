package sale;
import java.util.ArrayList;


public class SaleController {
    SalesService salesService;

    SaleController(SalesService salesService){
        this.salesService = salesService;
//        new SaleView(this);
    }

    ProductDTO getProductInfo(int productId){
        return salesService.getProductInfo(productId);

    }

}
