package SalesHistory;

import java.util.ArrayList;

public interface SalesHistoryDAO {
    ArrayList<SalesHistoryResDTO> getSalesHistories();

    SalesHistoryResDTO getSalesHistoryByID(int transactionID);

    SalesHistoryResDTO getSalesHistoryByMemberName(String memberName);

    boolean refundSalesHistory(int transactionID);


}
