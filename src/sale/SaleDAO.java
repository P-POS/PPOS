package sale;

import java.util.ArrayList;
import salesHistory.SalesHistoryDTO;

public interface SaleDAO {

    void sellSale(SaleDTO saleDTO);

    String getLatestSaleDate(int memberId);
    boolean refundSalesHistory(int transactionID);
    SalesHistoryDTO getSalesHistoryByMemberName(String memberName);
    ArrayList<SalesHistoryDTO> getSalesHistories();

    SalesHistoryDTO getSalesHistoryByID(int transactionID);
}
