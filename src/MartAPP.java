
import DBConnection.DBConnection;
import java.util.ArrayList;
import main.MainController;
import java.sql.*;
import sale.ProductOrderNumDTO;
import sale.SaleController;
import sale.SalesService;

public class MartAPP {
    public static void main(String[] args) {
//      new main.MainView(new MainController());
        SaleController saleController = new SaleController(new SalesService());
        System.out.println(saleController.getMemberInfo(1).getClientName());
        saleController.getProductInfo(1);
        saleController.getProductInfo(2);
        ArrayList<ProductOrderNumDTO> productOrderNumDTOS = saleController.getProductInfo(3);
       saleController.cancleProducts();

        System.out.println("-------");
        System.out.println(saleController.getProductInfo(2));
        saleController.getProductInfo(3);
        saleController.getProductInfo(4);
        System.out.println(saleController.returnProducts(2));
        for(ProductOrderNumDTO productOrderNumDTO : productOrderNumDTOS){
            System.out.println(productOrderNumDTO.getProductDTO().getProductPrice());
        }

    }
}