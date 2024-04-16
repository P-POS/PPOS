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
        new SaleController(new SalesService());
    }
}