package SalesHistory;

import java.util.ArrayList;

public interface SalesHistoryDAO {
    ArrayList<SalesHistoryResDTO> getSalesHistories();
    SalesHistoryResDTO getSalesHistoryByID();
    SalesHistoryResDTO getSalesHistoryByName();
    void refundSalesHistory(int transactionID);


}
