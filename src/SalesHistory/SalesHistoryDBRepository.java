package SalesHistory;

import java.util.ArrayList;

public class SalesHistoryDBRepository implements SalesHistoryDAO {


    @Override
    public ArrayList<SalesHistoryResDTO> getSalesHistories() {

        ArrayList<SalesHistory> salesHistories = new ArrayList<>();

        ArrayList<SalesHistoryResDTO> salesHistoriesResDTO = new ArrayList<>();


        for (SalesHistory salesHistory : salesHistories) {
            salesHistoriesResDTO.add(new SalesHistoryResDTO(salesHistory));
        }

        return salesHistoriesResDTO;
    }

    @Override
    public SalesHistoryResDTO getSalesHistoryByID() {
        SalesHistory salesHistory =
        SalesHistoryResDTO resDTO = new SalesHistoryResDTO(salesHistory);
        return resDTO;
    }

    @Override
    public SalesHistoryResDTO getSalesHistoryByName() {
        SalesHistory salesHistory = new SalesHistory();
        SalesHistoryResDTO resDTO = new SalesHistoryResDTO(salesHistory);
        return resDTO;
    }

    @Override
    public void refundSalesHistory(int transactionID) {

    }
}
