package SalesHistory;

import java.util.ArrayList;

public class SalesHistoryService {

    SalesHistoryDBRepository salesHistoryDBRepository = new SalesHistoryDBRepository();

    SalesHistoryResDTO getSalesHistory(String searchSource) {
        if(isNumberic(searchSource)){
            return salesHistoryDBRepository.getSalesHistoryByID();
        }else {
            return salesHistoryDBRepository.getSalesHistoryByName();
        }
    };

    ArrayList<SalesHistoryResDTO> getSalesHistories(){
        return salesHistoryDBRepository.getSalesHistories();
    };
    void refundSalesHistory(int transactionID){
        salesHistoryDBRepository.refundSalesHistory(transactionID);
    };

    public boolean isNumberic(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
