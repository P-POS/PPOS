package SalesHistory;

import java.util.ArrayList;

public class SalesHistoryService {
    private SalesHistoryDBRepository salesHistoryDBRepository;
    SalesHistoryService() {
        salesHistoryDBRepository = new SalesHistoryDBRepository();
    }

    SalesHistoryResDTO getSalesHistory(String searchSource) {
        if(isNumberic(searchSource)){
            return salesHistoryDBRepository.getSalesHistoryByID(Integer.parseInt(searchSource));
        }else {
            return salesHistoryDBRepository.getSalesHistoryByMemberName(searchSource);
        }
    };

    ArrayList<SalesHistoryResDTO> getSalesHistories(){
        return salesHistoryDBRepository.getSalesHistories();
    };
    boolean refundSalesHistory(int transactionID){
        return salesHistoryDBRepository.refundSalesHistory(transactionID);
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
