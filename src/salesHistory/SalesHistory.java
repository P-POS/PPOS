package salesHistory;

public class SalesHistory {

    private int transactionID;
    private int memberNum;
    private String memberName;
    private int totalAmount;
    private String date;

    public SalesHistory(SalesHistoryDTO salesHistoryDTOS) {

        this.transactionID = salesHistoryDTOS.getTransactionID();
        this.memberNum = salesHistoryDTOS.getMemberNum();
        this.memberName = salesHistoryDTOS.getMemberName();
        this.totalAmount = salesHistoryDTOS.getTotalAmount();
        this.date = salesHistoryDTOS.getDate();
    }

    public SalesHistory(int transactionID, int memberNum, String memberName, int totalAmount,
        String date) {

        this.transactionID = transactionID;
        this.memberNum = memberNum;
        this.memberName = memberName;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public int getTransactionID() {
        return transactionID;
    }
}
