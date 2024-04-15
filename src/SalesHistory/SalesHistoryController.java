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

    boolean refundSalesHistory(int transactionID){
        return salesHistoryService.refundSalesHistory(transactionID);
    };
}
