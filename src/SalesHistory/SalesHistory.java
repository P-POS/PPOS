package SalesHistory;

public class SalesHistory {
    private int transactionID;
    private int memberNum;
    private String memberName;
    private int totalAmount;
    private String date;

    public SalesHistory() {}

    public SalesHistory(SalesHistoryResDTO salesHistoryResDTOS) {
        this.transactionID = salesHistoryResDTOS.getTransactionID();
        this.memberNum = salesHistoryResDTOS.getMemberNum();
        this.memberName = salesHistoryResDTOS.getMemberName();
        this.totalAmount = salesHistoryResDTOS.getTotalAmount();
        this.date = salesHistoryResDTOS.getDate();
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
