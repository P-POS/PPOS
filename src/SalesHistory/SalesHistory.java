package SalesHistory;

public class SalesHistory {
    int transactionID;
    int memberNum;
    String memberName;
    int totalAmount;
    String date;

    public SalesHistory() {}

    public SalesHistory(SalesHistoryResDTO salesHistoryResDTOS) {
        this.transactionID = salesHistoryResDTOS.transactionID;
        this.memberNum = salesHistoryResDTOS.memberNum;
        this.memberName = salesHistoryResDTOS.memberName;
        this.totalAmount = salesHistoryResDTOS.totalAmount;
        this.date = salesHistoryResDTOS.date;
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
