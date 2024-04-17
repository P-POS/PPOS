package salesHistory;

import java.util.ArrayList;
import sale.SaleRepository;

public class SalesHistoryService {

    private SaleRepository saleRepository;

    private SalesHistoryService() {
        saleRepository = new SaleRepository();
    }

    public SalesHistoryDTO getSalesHistory(String searchSource) {

        if (isNumberic(searchSource)) {
            return saleRepository.getSalesHistoryByID(Integer.parseInt(searchSource));
        } else {
            return saleRepository.getSalesHistoryByMemberName(searchSource);
        }
    }

    public ArrayList<SalesHistoryDTO> getSalesHistories() {

        return saleRepository.getSalesHistories();
    }

    public boolean refundSalesHistory(int transactionID) {

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
