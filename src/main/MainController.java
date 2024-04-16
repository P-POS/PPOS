package main;

import SalesHistory.SalesHistoryController;
import member.MemberController;
import product.ProductController;
import sale.SaleController;

public class MainController {
    public MainController(){
        new MainView(this);
    }

    public void openMainPage(){
        new MainView(this);
    }
    public void openMemberPage(){
        new MemberController(this);
    }
    public void openProductPage(){
        //new ProductController(this);
    }
    public void openSalePage(){
        //new SaleController(this);
    }
    public void openSalesHistoryPage(){
        //new SalesHistoryController(this);
    }


}
