package salesHistory;

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

    public void openMainPage() {

        mainController.openMainPage();
    }

    SalesHistory getSalesHistory(String searchSource) {

        SalesHistoryDTO salesHistoryDTO = salesHistoryService.getSalesHistory(searchSource);
        if (salesHistoryDTO.getMemberName() == null) {
            salesHistoryDTO.setMemberName("비회원");
        }
        return new SalesHistory(salesHistoryDTO);
    }

    ArrayList<SalesHistory> getSalesHistories() {

        salesHistoryService.getSalesHistories();

        ArrayList<SalesHistoryDTO> salesHistoryDTOS = salesHistoryService.getSalesHistories();

        ArrayList<SalesHistory> salesHistories = new ArrayList<>();

        for (SalesHistoryDTO salesHistoryDTO : salesHistoryDTOS) {
            if (salesHistoryDTO.getMemberName() == null) {
                salesHistoryDTO.setMemberName("비회원");
            }
            salesHistories.add(new SalesHistory(salesHistoryDTO));
        }
        return salesHistories;
    }

    boolean refundSalesHistory(int transactionID) {

        return salesHistoryService.refundSalesHistory(transactionID);
    }
}
