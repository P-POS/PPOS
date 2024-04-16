package SalesHistory;

import java.util.ArrayList;
import main.MainController;


public class SalesHistoryController {
    private MainController mainController;
    private SalesHistoryService salesHistoryService;

    public SalesHistoryController(MainController mainController) {
        salesHistoryService = new SalesHistoryService();
        this.mainController = mainController;
        new SalesHistoryView(this);
    }

    public void openMainPage(){
        mainController.openMainPage();
    }

    SalesHistory getSalesHistory(String searchSource) {

        SalesHistoryResDTO salesHistoryResDTOS = salesHistoryService.getSalesHistory(searchSource);
        return new SalesHistory(salesHistoryResDTOS);
    };

    ArrayList<SalesHistory> getSalesHistories(){
        salesHistoryService.getSalesHistories();

        ArrayList<SalesHistoryResDTO> salesHistoryResDTOs = salesHistoryService.getSalesHistories();

        ArrayList<SalesHistory> salesHistories = new ArrayList<>();

        for (SalesHistoryResDTO salesHistoryResDTO : salesHistoryResDTOs) {
            salesHistories.add(new SalesHistory(salesHistoryResDTO));
        }
        return salesHistories;
    };

    boolean refundSalesHistory(int transactionID){
        return salesHistoryService.refundSalesHistory(transactionID);
    };
}
