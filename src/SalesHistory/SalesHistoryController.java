package SalesHistory;

import java.util.ArrayList;


public class SalesHistoryController {

    SalesHistoryService salesHistoryService = new SalesHistoryService();

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

    void refundSalesHistory(int transactionID){
        salesHistoryService.refundSalesHistory(transactionID);
    };
}
