package salesHistory;

import java.util.ArrayList;
import sale.SaleRepository;

public class SalesHistoryService {

    private SaleRepository saleRepository;

    SalesHistoryService() {
        saleRepository = new SaleRepository();
    }

    SalesHistoryDTO getSalesHistory(String searchSource) {

        if (isNumberic(searchSource)) {
            return saleRepository.getSalesHistoryByID(Integer.parseInt(searchSource));
        } else {
            return saleRepository.getSalesHistoryByMemberName(searchSource);
        }
    }

    ArrayList<SalesHistoryDTO> getSalesHistories() {

        return saleRepository.getSalesHistories();
    }

    boolean refundSalesHistory(int transactionID) {

        return saleRepository.refundSalesHistory(transactionID);
    }

    public boolean isNumberic(String str) {

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
